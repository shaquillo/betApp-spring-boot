package com.kindredgroup.unibetlivetest.api;

import com.kindredgroup.unibetlivetest.entity.Customer;
import com.kindredgroup.unibetlivetest.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerApi {

    private final CustomerService customerService;

    @GetMapping(Urls.CURRENT_CUSTOMER)
    public Customer fetchCustomer() {
        return customerService.findCustomerByPseudo("unibest");
    }


}
