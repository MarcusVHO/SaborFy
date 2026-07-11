package com.br.marcus.saborfy.domain.customer.query;

import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.domain.customer.repository.PhoneRepository;
import com.br.marcus.saborfy.exceptions.PhoneNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PhoneFinder {
    private final PhoneRepository phoneRepository;

    public PhoneFinder(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public CustomerPhone byIdAndCustomerId(Long id, Long customerId) {
        return phoneRepository.findByIdAndCustomerId(id, customerId).orElseThrow(PhoneNotFoundException::new);
    }
}
