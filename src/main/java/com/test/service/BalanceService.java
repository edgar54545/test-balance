package com.test.service;

import com.test.dto.BalanceDto;
import com.test.dto.TransactionDto;
import com.test.dto.TransactionRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceService {

    BalanceDto createBalance(String name);

    BigDecimal getBalance(UUID id);

    TransactionDto performTransaction(UUID balanceId, TransactionRequest transactionRequest);
}
