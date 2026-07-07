package com.br.marcus.saborfy.domain.customer.controller;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.service.CustomerService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> list(
            @RequestParam String name
    ) {
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        for (Customer customer : customerService.getCustomerList(name)) {
            customerResponseDTOS.add(new CustomerResponseDTO(customer));
        }
        return ResponseEntity.ok(customerResponseDTOS);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam @Valid Long id
    ) {
        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody @Valid CreateCustomerRequest request
    ) {

        Customer customer  = customerService.create(request, user);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam Long id,
            @RequestBody @Valid UpdateCustomerRequest request
    ) {
        Customer customer = customerService.update(user, id, request);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }






}
