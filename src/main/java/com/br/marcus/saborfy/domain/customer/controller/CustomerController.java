package com.br.marcus.saborfy.domain.customer.controller;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.UpdateCustomerRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.CustomerResponseDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.service.CreateCustomerService;
import com.br.marcus.saborfy.domain.customer.service.DeleteCustomerService;
import com.br.marcus.saborfy.domain.customer.service.ListCustomerService;
import com.br.marcus.saborfy.domain.customer.service.UpdateCustomerService;
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
    private final CreateCustomerService createCustomerService;
    private final UpdateCustomerService updateCustomerService;
    private final ListCustomerService listCustomerService;
    private final DeleteCustomerService deleteCustomerService;

    public CustomerController(CreateCustomerService createCustomerService, UpdateCustomerService updateCustomerService, ListCustomerService listCustomerService, DeleteCustomerService deleteCustomerService) {
        this.createCustomerService = createCustomerService;
        this.updateCustomerService = updateCustomerService;
        this.listCustomerService = listCustomerService;
        this.deleteCustomerService = deleteCustomerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> list(
            @RequestParam String name
    ) {
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();
        for (Customer customer : listCustomerService.getCustomerList(name)) {
            customerResponseDTOS.add(new CustomerResponseDTO(customer));
        }
        return ResponseEntity.ok(customerResponseDTOS);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @RequestParam @Valid Long id
    ) {
        deleteCustomerService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody @Valid CreateCustomerRequest request
    ) {

        Customer customer  = createCustomerService.create(request, user);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

    @PutMapping
    public ResponseEntity<CustomerResponseDTO> update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestParam Long id,
            @RequestBody @Valid UpdateCustomerRequest request
    ) {
        Customer customer = updateCustomerService.update(user, id, request);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }






}
