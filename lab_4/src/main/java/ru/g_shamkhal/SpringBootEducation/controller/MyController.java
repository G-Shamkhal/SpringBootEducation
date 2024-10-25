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
    private final UnsupportedService unsupportedCodeException;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedService unsupportedCodeException,
                            @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                            ModifyRequestService modifyRequestService) {
        this.validationService = validationService;
        this.unsupportedCodeException = unsupportedCodeException;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        long requestTime = System.currentTimeMillis();

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response: {}", response);

        try {
            validationService.isValid(bindingResult);
            validationService.isValidRequest(request);
            unsupportedCodeException.isSupportCode(request.getUid());
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error(e.toString());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTIO);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.error(e.toString());
            return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (Exception e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error(e.toString());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Request-Received-Time", String.valueOf(requestTime));

        modifyResponseService.modify(response);
        modifyRequestService.modify(request, headers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
