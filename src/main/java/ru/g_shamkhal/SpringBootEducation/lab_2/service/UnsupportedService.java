package ru.g_shamkhal.SpringBootEducation.lab_2.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.lab_2.exception.UnsupportedCodeException;

@Service
public interface UnsupportedService {
    void isSupportCode(String code) throws UnsupportedCodeException;
}
