package com.ipap.frauddetect.controller;

import com.ipap.frauddetect.entity.LoanStatus;
import com.ipap.frauddetect.service.FraudDetectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fraud")
@RequiredArgsConstructor
@Slf4j
public class FraudDetectController {

    private final FraudDetectionService fraudDetectionService;

    @GetMapping("/check")
    public LoanStatus checkForFraud(@RequestParam int customerId) {
        log.info("Checking for fraud for customer id: {}", customerId);
        return fraudDetectionService.checkForFraud(customerId);
    }
}
