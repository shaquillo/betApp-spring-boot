package com.kindredgroup.unibetlivetest.service;

import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.exception.CustomException;
import com.kindredgroup.unibetlivetest.repository.CustomerRepository;
import com.kindredgroup.unibetlivetest.types.ExceptionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findCustomerByPseudo(String pseudo) {
        log.info("CustomerService : finding customer with pseudo = %s", pseudo);
        return customerRepository.getCustomerByPseudo(pseudo).orElseThrow(() -> new CustomException(format("customer %s not found", pseudo), ExceptionType.EXCEPTION_NOT_FOUND));
    }
}
