package com.ipap.loans.client;

import com.ipap.loans.entity.LoanStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionClient {

    private final RestTemplate fraudServiceTemplate;

    public LoanStatus evaluateLoan(int customerId) {
        log.info("Calling Fraud Detection Service for customer id: {}", customerId);
        var response = fraudServiceTemplate.exchange(
                "/fraud/check?customerId=" + customerId, HttpMethod.GET, null, LoanStatus.class
        ).getBody();
        log.info("Fraud Detection Service response: {}", response);
        return response;
    }
}
