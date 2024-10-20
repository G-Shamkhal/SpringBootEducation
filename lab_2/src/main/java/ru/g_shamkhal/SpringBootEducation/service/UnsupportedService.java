package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.exception.UnsupportedCodeException;

@Service
public interface UnsupportedService {
    void isSupportCode(String code) throws UnsupportedCodeException;
}
