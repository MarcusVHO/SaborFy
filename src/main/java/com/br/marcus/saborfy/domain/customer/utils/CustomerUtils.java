package com.br.marcus.saborfy.domain.customer.utils;

import com.br.marcus.saborfy.domain.customer.dto.request.CreateAddressRequest;
import com.br.marcus.saborfy.domain.customer.dto.request.CreatePhoneRequest;
import com.br.marcus.saborfy.domain.customer.entity.Customer;
import com.br.marcus.saborfy.domain.customer.entity.CustomerAddress;
import com.br.marcus.saborfy.domain.customer.entity.CustomerPhone;
import com.br.marcus.saborfy.domain.user.entity.User;

import java.util.List;

public class CustomerUtils {

    public static void addPhonesInCustomer(List<CreatePhoneRequest> phones, Customer customer, User user) {
        for(CreatePhoneRequest createPhoneRequest : phones) {
            CustomerPhone customerPhone = new CustomerPhone();
            customerPhone.setCustomer(customer);
            customerPhone.setUser(user);
            customerPhone.setNumber(createPhoneRequest.number());
            customer.getPhones().add(customerPhone);
        }

    }

    public static void addAddressesInCustomer(List<CreateAddressRequest> addressRequests, Customer customer, User user) {
        for(CreateAddressRequest createAddressRequest : addressRequests) {
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setCustomer(customer);
            customerAddress.setUser(user);
            customerAddress.setNumber(createAddressRequest.number());
            customerAddress.setAddress(createAddressRequest.address());
            customerAddress.setComplement(createAddressRequest.complement());
            customer.getAddresses().add(customerAddress);
        }

    }
}
