package com.test.service.impl;

import com.test.dto.BalanceDto;
import com.test.dto.TransactionDto;
import com.test.dto.TransactionRequest;
import com.test.entity.Balance;
import com.test.entity.Transaction;
import com.test.mapper.BalanceMapper;
import com.test.mapper.TransactionMapper;
import com.test.repository.BalanceRepository;
import com.test.service.BalanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapper balanceMapper;
    private final TransactionMapper transactionMapper;

    public BalanceDto createBalance(String name) {
        Balance balance = Balance.builder()
                .name(name)
                .amount(BigDecimal.ZERO)
                .transactions(Collections.emptyList())
                .build();

        Balance savedBalance = balanceRepository.save(balance);
        log.info("Balance saved: " + savedBalance.getName());
        return balanceMapper.toDto(savedBalance);
    }

    public BigDecimal getBalance(UUID balanceId) {
        return balanceRepository.findById(balanceId).map(Balance::getAmount)
                .orElseThrow(() -> new EntityNotFoundException("Balance with id not found: " + balanceId));
    }

    @Transactional
    public TransactionDto performTransaction(UUID balanceId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest, balanceId);

        Balance balance = balanceRepository.findByIdForUpdate(balanceId)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find balance with id: " + balanceId));

        balance.getTransactions().add(transaction);
        balance.calculateBalance(transaction);
        balanceRepository.save(balance);
        log.info("Balance updated with transaction balanceName: {}, transaction: {}", balance.getName(), transaction);
        return transactionMapper.toDto(transaction);
    }

}
