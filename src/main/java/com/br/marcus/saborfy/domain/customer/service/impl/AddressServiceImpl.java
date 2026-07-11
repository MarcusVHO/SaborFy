package com.br.marcus.saborfy.domain.customer.service.impl;
import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.dto.response.AddressDTO;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.mapper.AddressMapper;
import com.br.marcus.saborfy.domain.customer.query.AddressFinder;
import com.br.marcus.saborfy.domain.customer.query.CustomerFinder;
import com.br.marcus.saborfy.domain.customer.repository.AddressRepository;
import com.br.marcus.saborfy.domain.customer.service.AddressService;
import com.br.marcus.saborfy.infra.security.authenticated.AuthenticatedUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;


@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final CustomerFinder customerFinder;
    private final AddressFinder addressFinder;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, CustomerFinder customerFinder, AddressFinder addressFinder, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.customerFinder = customerFinder;
        this.addressFinder = addressFinder;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public AddressDTO create(AuthenticatedUser user, CreateAddressRequest request, Long customerId) {
        Customer customer = customerFinder.findEntityById(customerId);
        CustomerAddress newAddress = new CustomerAddress(
                request.address(),
                request.number(),
                request.complement(),
                customer,
                user.id()
        );
        addressRepository.save(newAddress);
        return addressMapper.toResponse(newAddress);
    }

    @Transactional
    public void delete(Long customerId, Long addressId) {
        CustomerAddress address = addressFinder.byIdAndCustomerId(addressId, customerId);
        addressRepository.delete(address);
    }
}
