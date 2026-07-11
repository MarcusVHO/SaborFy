package com.br.marcus.saborfy.domain.customer.service.impl;

import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.PhoneDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.domain.customer.mapper.PhoneMapper;
import com.br.marcus.saborfy.domain.customer.query.CustomerFinder;
import com.br.marcus.saborfy.domain.customer.query.PhoneFinder;
import com.br.marcus.saborfy.domain.customer.repository.PhoneRepository;
import com.br.marcus.saborfy.domain.customer.service.PhoneService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneFinder phoneFinder;
    private final CustomerFinder customerFinder;
    private final PhoneMapper mapper;


    public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneFinder phoneFinder, CustomerFinder customerFinder, PhoneMapper mapper) {
        this.phoneRepository = phoneRepository;
        this.phoneFinder = phoneFinder;
        this.customerFinder = customerFinder;
        this.mapper = mapper;
    }

    @Transactional
    public PhoneDTO create(AuthenticatedUser user, Long customerId, CreatePhoneRequest request) {
        Customer customer = customerFinder.findEntityById(customerId);
        CustomerPhone newPhone = new CustomerPhone(request.number(), customer, user.id());
        phoneRepository.save(newPhone);
        return mapper.toResponse(newPhone);
    }

    @Transactional
    public void delete(Long customerId, Long phoneId) {
        CustomerPhone phone = phoneFinder.byIdAndCustomerId(phoneId, customerId);
        phoneRepository.delete(phone);
    }
}
