package com.practice.practice_project.service.business;

import com.practice.practice_project.common.enums.SystemCodes;
import com.practice.practice_project.dto.RestResponse;
import com.practice.practice_project.dto.request.ClientRequest;
import com.practice.practice_project.model.ClientEntity;
import com.practice.practice_project.model.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public RestResponse<ClientRequest> createCleint(ClientRequest request){
        ClientEntity entity = ClientEntity.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .build();
        clientRepository.save(entity);
        return RestResponse.getResponse(request, SystemCodes.SUCCESS);
    }
}
