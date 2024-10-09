package ru.g_shamkhal.SpringBootEducation.lab_2.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.lab_2.exception.UnsupportedCodeException;

@Service
public class RequestUnsupportedCodeService implements UnsupportedService {
    @Override
    public void isSupportCode(String code) throws UnsupportedCodeException {
        if (code.equals("123")) throw new UnsupportedCodeException("Значение указанное в Uid не поддерживается.");
    }
}
