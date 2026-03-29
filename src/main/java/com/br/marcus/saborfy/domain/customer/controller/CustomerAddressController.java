package com.br.marcus.saborfy.domain.customer.controller;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.AddressDTO;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.service.CustomerAddressService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
public class CustomerAddressController {
    private final CustomerAddressService customerAddressService;

    public CustomerAddressController(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }


    @PostMapping(path = "/address")
    public ResponseEntity<AddressDTO> createAddress(
            @AuthenticationPrincipal AuthenticatedUser user,
            @Valid @RequestParam Long customerId,
            @Valid @RequestBody CreateAddressRequest newAddress
    ) {

        CustomerAddress customerAddress = customerAddressService.create(user, newAddress, customerId);
        AddressDTO addressDTO = new AddressDTO(customerAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDTO);
    }


    @DeleteMapping("/address")
    public ResponseEntity<Void> deleteAddress(
            @Valid @RequestParam Long customerId,
            @Valid @RequestParam Long addressId
    ) {
        customerAddressService.delete(customerId, addressId);

        return ResponseEntity.noContent().build();
    }
}
