package com.br.marcus.saborfy.domain.customer.service;

import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.domain.customer.repository.CustomerPhoneRepository;
import com.br.marcus.saborfy.domain.customer.repository.CustomerRepository;
import com.br.marcus.saborfy.exceptions.CustomerMismatchException;
import com.br.marcus.saborfy.exceptions.CustomerNotFoundException;
import com.br.marcus.saborfy.exceptions.PhoneNotFoundException;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import com.br.marcus.saborfy.domain.user.entity.User;
import org.springframework.stereotype.Service;


@Service
public class CustomerPhoneService {
    private final CustomerPhoneRepository customerPhoneRepository;
    private final CustomerRepository customerRepository;

    public CustomerPhoneService(CustomerPhoneRepository customerPhoneRepository, CustomerRepository customerRepository) {
        this.customerPhoneRepository = customerPhoneRepository;
        this.customerRepository = customerRepository;
    }

    public CustomerPhone create(AuthenticatedUser user, Long customerId, CreatePhoneRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new
                );

        User currentUser = new User();
        currentUser.setId(user.id());

        CustomerPhone newPhone = new CustomerPhone();
        newPhone.setCustomer(customer);
        newPhone.setUser(currentUser);
        newPhone.setNumber(request.number());

        customerPhoneRepository.save(newPhone);
        return newPhone;
    }


    public void delete(Long customerId, Long phoneId) {
        CustomerPhone phone = customerPhoneRepository.findById(phoneId)
                .orElseThrow(PhoneNotFoundException::new);

        if (!phone.getCustomer().getId().equals(customerId)) {
            throw new CustomerMismatchException("Phone does not belong to this customer");
        }

        customerPhoneRepository.delete(phone);
    }
}
