package ru.g_shamkhal.SpringBootEducation.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.g_shamkhal.SpringBootEducation.exception.UnsupportedCodeException;
import ru.g_shamkhal.SpringBootEducation.exception.ValidationFailedException;
import ru.g_shamkhal.SpringBootEducation.model.Request;
import ru.g_shamkhal.SpringBootEducation.model.Response;
import ru.g_shamkhal.SpringBootEducation.service.UnsupportedService;
import ru.g_shamkhal.SpringBootEducation.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MyController {

    private final ValidationService validationService;
    private final UnsupportedService unsupportedCodeException;

    @Autowired
    public MyController(ValidationService validationService, UnsupportedService unsupportedCodeException) {
        this.validationService = validationService;
        this.unsupportedCodeException = unsupportedCodeException;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        try {
            validationService.isValid(bindingResult);
            validationService.isValidRequest(request);
            unsupportedCodeException.isSupportCode(request.getUid());
        } catch (ValidationFailedException e) {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            response.setCode("failed");
            response.setErrorCode("UnsupportedCodeException");
            response.setErrorMessage("Не поддерживаемая ошибка.");
            return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        } catch (Exception e) {
            response.setCode("failed");
            response.setErrorCode("UnknownException");
            response.setErrorMessage("Произошла непредвиденная ошибка.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}