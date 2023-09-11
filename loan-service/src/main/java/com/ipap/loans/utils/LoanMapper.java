package com.ipap.loans.utils;

import com.ipap.loans.dto.LoanDto;
import com.ipap.loans.entity.Loan;

public class LoanMapper {

    public static LoanDto from(Loan loan) {
        return new LoanDto(
                loan.getLoanId(),
                loan.getCustomerName(),
                loan.getCustomerId(),
                loan.getAmount(),
                loan.getLoanStatus()
        );
    }

    public static Loan from(LoanDto loanDto) {
        if (loanDto.getLoanId() == null && loanDto.getLoanStatus() == null) {
            return Loan.builder()
                    .amount(loanDto.getAmount())
                    .customerId(loanDto.getCustomerId())
                    .customerName(loanDto.getCustomerName())
                    .build();
        }
        return Loan.builder()
                .loanId(loanDto.getLoanId())
                .amount(loanDto.getAmount())
                .customerId(loanDto.getCustomerId())
                .customerName(loanDto.getCustomerName())
                .loanStatus(loanDto.getLoanStatus())
                .build();
    }
}
