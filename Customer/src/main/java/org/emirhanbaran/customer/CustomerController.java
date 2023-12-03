package org.emirhanbaran.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    private  final  CustomerService customerService;

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistirationRequest customerRegistirationRequest){
        customerService.registerCustomer(customerRegistirationRequest);
        log.info("New Customer Registiration {}",customerRegistirationRequest);
    }

    @GetMapping
    public List<Customer> getAllCustomer(){
        return  customerService.getAllCustomers();
    }

}
