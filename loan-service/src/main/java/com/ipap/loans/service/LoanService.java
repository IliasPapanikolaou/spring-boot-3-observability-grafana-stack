package com.ipap.loans.service;

import com.ipap.loans.client.FraudDetectionClient;
import com.ipap.loans.dto.LoanDto;
import com.ipap.loans.entity.LoanStatus;
import com.ipap.loans.repository.LoanRepository;
import com.ipap.loans.utils.LoanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final FraudDetectionClient fraudDetectionClient;
    private final LoanRepository loanRepository;


    public List<LoanDto> listAllLoans() {
        return loanRepository.findAll().stream().map(LoanMapper::from).toList();
    }

    public String applyLoan(LoanDto loanDto) {
        var loan = LoanMapper.from(loanDto);
        LoanStatus loanStatus = fraudDetectionClient.evaluateLoan(loan.getCustomerId());
        loan.setLoanStatus(loanStatus);
        if (loanStatus.equals(LoanStatus.APPROVED)) {
            loanRepository.save(loan);
            return "Loan applied successfully";
        }
        return "Loan not approved";
    }
}
