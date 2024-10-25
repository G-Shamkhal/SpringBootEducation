package ru.g_shamkhal.SpringBootEducation.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.g_shamkhal.SpringBootEducation.exception.UnsupportedCodeException;
import ru.g_shamkhal.SpringBootEducation.exception.ValidationFailedException;
import ru.g_shamkhal.SpringBootEducation.model.*;
import ru.g_shamkhal.SpringBootEducation.service.ModifyRequestService;
import ru.g_shamkhal.SpringBootEducation.service.ModifyResponseService;
import ru.g_shamkhal.SpringBootEducation.service.UnsupportedService;
import ru.g_shamkhal.SpringBootEducation.service.ValidationService;
import ru.g_shamkhal.SpringBootEducation.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedService unsupportedService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedService unsupportedService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.unsupportedService = unsupportedService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        long requestTime = System.currentTimeMillis();
        log.info("Received request: {}", request);

        Response response = createBaseResponse(request);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Request-Received-Time", String.valueOf(requestTime));

        try {
            validateRequest(bindingResult, request);
        } catch (ValidationFailedException e) {
            return handleException(response, Codes.FAILED, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, e, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return handleException(response, Codes.FAILED, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, e, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (Exception e) {
            return handleException(response, Codes.FAILED, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request, headers);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    private Response createBaseResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void validateRequest(BindingResult bindingResult, Request request) throws ValidationFailedException, UnsupportedCodeException {
        validationService.isValid(bindingResult);
        validationService.isValidRequest(request);
        unsupportedService.isSupportCode(request.getUid());
    }

    private ResponseEntity<Response> handleException(Response response, Codes code, ErrorCodes errorCode, ErrorMessages errorMessage, Exception e, HttpStatus status) {
        response.setCode(code);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        log.error("Error occurred: ", e);
        return new ResponseEntity<>(response, status);
    }
}
