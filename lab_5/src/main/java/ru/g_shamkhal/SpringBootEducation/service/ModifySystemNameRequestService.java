package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.g_shamkhal.SpringBootEducation.model.Request;
import ru.g_shamkhal.SpringBootEducation.model.Systems;

@Service
public class ModifySystemNameRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request, HttpHeaders headers) {
        request.setSystemName(Systems.valueOf("ERP"));

        HttpEntity<Request> httpEntity = new HttpEntity<>(request, headers);

        new RestTemplate().exchange("http://localhost:8080/feedback",
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<>() {
                });
    }
}
