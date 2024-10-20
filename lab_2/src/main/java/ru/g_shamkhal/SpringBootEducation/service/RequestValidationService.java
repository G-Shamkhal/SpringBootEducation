package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.g_shamkhal.SpringBootEducation.exception.ValidationFailedException;
import ru.g_shamkhal.SpringBootEducation.model.Request;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class RequestValidationService implements ValidationService {
    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }

    @Override
    public void isValidRequest(Request request) throws ValidationFailedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(request.getSystemTime());
        } catch (ParseException e) {
            throw new ValidationFailedException("Параметр systemTime не указан или имеет некорректный формат.");
        }
        if (request.getUid().length() > 32) {
            throw new ValidationFailedException("Параметр uid содержит более 32 символов.");
        } else if (request.getOperationUid().length() > 32) {
            throw new ValidationFailedException("Параметр operationId содержит более 32 символов.");
        } else if (request.getCommunicationId() < 1 || request.getCommunicationId() > 100000) {
            throw new ValidationFailedException("Параметр communicationId не входит в интервал 1-100000.");
        }
    }
}