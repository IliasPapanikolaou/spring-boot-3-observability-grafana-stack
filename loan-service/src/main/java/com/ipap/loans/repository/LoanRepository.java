package com.ipap.loans.repository;

import com.ipap.loans.entity.Loan;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Observed
public class LoanRepository {

    private final JdbcClient jdbcClient;

    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        var findAllQuery = "SELECT id, loanId, customerName, customerId, amount, loanStatus FROM loans";
        return jdbcClient.sql(findAllQuery).query(Loan.class).list();
    }

    @Transactional
    public Long save(Loan loan) {
        var saveQuery = "INSERT INTO loans(loanId, customerName, customerId, amount, loanStatus) VALUES (?, ?, ? ,?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(saveQuery)
                .param(1, UUID.randomUUID().toString())
                .param(2, loan.getCustomerName())
                .param(3, loan.getCustomerId())
                .param(4, loan.getAmount())
                .param(5, loan.getLoanStatus().toString())
                .update();
        return keyHolder.getKeyAs(Long.class);
    }
}
