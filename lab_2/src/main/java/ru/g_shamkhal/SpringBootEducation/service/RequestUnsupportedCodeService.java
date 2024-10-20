package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.exception.UnsupportedCodeException;

@Service
public class RequestUnsupportedCodeService implements UnsupportedService {
    @Override
    public void isSupportCode(String code) throws UnsupportedCodeException {
        if (code.equals("123")) throw new UnsupportedCodeException("Значение указанное в Uid не поддерживается.");
    }
}