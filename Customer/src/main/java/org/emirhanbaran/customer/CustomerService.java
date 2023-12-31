package org.emirhanbaran.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final RestTemplate restTemplate;
    private  final CustomerRepository customerRepository;
    private final  FraudOpenFeign fraudOpenFeign;
    public  void registerCustomer(CustomerRegistirationRequest request){
        Customer customer=Customer.builder()
                .firstName(request.firstName())
                .email(request.email())
                .lastName(request.lastName())
                .build();


        // todo: check if email valid
        // todo: check if email not taken
        // todo: check if fraudster
        customerRepository.saveAndFlush(customer);
        /* REQUEST WİTH REST TEMPLATE
        FraudCheckResponse fraudCheckResponse=restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

         */
        //REQUEST WİTH OPEN FEİGN
        FraudCheckResponse fraudCheckResponse=fraudOpenFeign.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()){
            customerRepository.deleteById(customer.getId());
            throw new IllegalStateException("The Customer is Fraudster");

        }

        // todo: send notification
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
