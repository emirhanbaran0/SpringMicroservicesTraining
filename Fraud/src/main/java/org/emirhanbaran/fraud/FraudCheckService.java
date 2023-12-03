package org.emirhanbaran.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudCheckService {


    private  final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    public boolean isFraudulentCustomer(Integer customerId){
        // It can be controlled by using third party app or by our own system
        log.info("Check for userId: {}",customerId);
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .id(customerId)
                        .isFraudster(false)
                        .build()
        );
          return  false;
    }
}
