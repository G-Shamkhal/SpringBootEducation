package ru.g_shamkhal.SpringBootEducation.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.g_shamkhal.SpringBootEducation.model.Response;

import java.util.UUID;

@Service
@Qualifier("ModifyOperationUidResponseService")
public class ModifyOperationUidResponseService implements ModifyResponseService{

    public Response modify(Response response){

        UUID uuid = UUID.randomUUID();

        response.setOperationUid(uuid.toString());
        return response;
    }
}
