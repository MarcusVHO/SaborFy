package com.br.marcus.saborfy.customer;

import com.br.marcus.saborfy.customer.dto.CreateCustomerRequest;
import com.br.marcus.saborfy.customer.dto.DeleteCustomerRequest;
import com.br.marcus.saborfy.customer.dto.DeleteCustomerResponse;
import com.br.marcus.saborfy.customer.dto.UpdateCustomerRequest;
import com.br.marcus.saborfy.customer.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody @Valid CreateCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> select() {
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @DeleteMapping
    public ResponseEntity<DeleteCustomerResponse> delete (@RequestBody @Valid DeleteCustomerRequest deleteCustomerRequest) {
        customerService.deleteCustomer(deleteCustomerRequest);
        return ResponseEntity.ok(new DeleteCustomerResponse("Item removido com sucesso!"));
    }

    @PutMapping
    public ResponseEntity<Customer> update (@RequestParam Long id, @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(customerService.updateCustomer(id, request));
    }
}
