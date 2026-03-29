package com.br.marcus.saborfy.domain.customer.controller;

import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.PhoneDTO;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.domain.customer.service.CustomerPhoneService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerPhoneController {
    private final CustomerPhoneService customerPhoneService;

    public CustomerPhoneController(CustomerPhoneService customerPhoneService) {
        this.customerPhoneService = customerPhoneService;
    }


    @PostMapping(path = "/phone")
    public ResponseEntity<PhoneDTO> createPhone(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestParam Long customerId,
            @Valid @RequestBody CreatePhoneRequest request
    ) {
        CustomerPhone customerPhone = customerPhoneService.create(user, customerId, request);
        PhoneDTO phoneDTO = new PhoneDTO(customerPhone);
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneDTO);

    }


    @DeleteMapping(path = "/phone")
    public ResponseEntity<Void> deletePhone(
            @Valid @RequestParam Long customerId,
            @Valid @RequestParam Long phoneId
    ) {
        customerPhoneService.delete(customerId, phoneId);
        return ResponseEntity.noContent().build();
    }
}
