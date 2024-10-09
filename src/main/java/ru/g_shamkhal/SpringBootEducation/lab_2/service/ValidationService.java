package ru.g_shamkhal.SpringBootEducation.lab_2.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.g_shamkhal.SpringBootEducation.lab_2.exception.ValidationFailedException;
import ru.g_shamkhal.SpringBootEducation.lab_2.model.Request;


@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void isValidRequest(Request request) throws ValidationFailedException;
}
