package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.model.Response;

@Service
public interface ModifyResponseService {

    Response modify(Response response);
}
