package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.model.Request;

@Service
public interface ModifyRequestService {
    void modify(Request request, HttpHeaders headers);
}
