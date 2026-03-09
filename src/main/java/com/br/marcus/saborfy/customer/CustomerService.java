package com.br.marcus.saborfy.customer;

import com.br.marcus.saborfy.customer.dto.AddressRequest;
import com.br.marcus.saborfy.customer.dto.CreateCustomerRequest;
import com.br.marcus.saborfy.customer.dto.DeleteCustomerRequest;
import com.br.marcus.saborfy.customer.dto.UpdateCustomerRequest;
import com.br.marcus.saborfy.customer.entity.Customer;
import com.br.marcus.saborfy.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.exception.exceptions.ConflictException;
import com.br.marcus.saborfy.exception.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        if(customerRepository.existsByNome(request.getNome())) {
            throw new ConflictException("já existe um cliente com esse nome!");
        }

        Customer customer = new Customer();

        customer.setNome(request.getNome());
        customer.setTelefone(request.getTelefone());

        List<CustomerAddress> customerAddresses = new ArrayList<>();

        for (AddressRequest addressRequest : request.getEnderecos()) {

            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setCustomer(customer);
            customerAddress.setRua(addressRequest.getRua());
            customerAddress.setNumero(addressRequest.getNumero());

            customerAddresses.add(customerAddress);
        }

        customer.setEnderecos(customerAddresses);
        customerRepository.save(customer);

        return customer;
    }

    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(DeleteCustomerRequest deleteCustomerRequest) {
        customerRepository.deleteById(deleteCustomerRequest.getId());
    }

    public Customer updateCustomer(Long id, UpdateCustomerRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));

        customer.setNome(request.getNome());
        customer.setTelefone(request.getTelefone());

        customer.getEnderecos().clear();
        for (AddressRequest addressRequest : request.getEnderecos()) {
            CustomerAddress customerAddress = new CustomerAddress();

            customerAddress.setNumero(addressRequest.getNumero());
            customerAddress.setRua(addressRequest.getRua());
            customerAddress.setCustomer(customer);

            customer.getEnderecos().add(customerAddress);
        }
        customerRepository.save(customer);

        return customer;
    }
}
