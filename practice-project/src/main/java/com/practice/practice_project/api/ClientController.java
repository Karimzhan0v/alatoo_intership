package com.practice.practice_project.api;

import com.practice.practice_project.common.statics.Endpoints;
import com.practice.practice_project.dto.RestResponse;
import com.practice.practice_project.dto.request.ClientRequest;
import com.practice.practice_project.service.business.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Endpoints.CLIENT_API)
@Validated
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<RestResponse<ClientRequest>> createClient(
            @RequestBody @Valid ClientRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createCleint(request));
    }
}
