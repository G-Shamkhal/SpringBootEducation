package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.g_shamkhal.SpringBootEducation.exception.ValidationFailedException;
import ru.g_shamkhal.SpringBootEducation.model.Request;


@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void isValidRequest(Request request) throws ValidationFailedException;
}
