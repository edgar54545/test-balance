package com.test.controller;

import com.test.dto.TransactionDto;
import com.test.dto.TransactionRequest;
import com.test.entity.Transaction;
import com.test.service.impl.BalanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/balance/{id}")
@RequiredArgsConstructor
public class TransactionController {

    private final BalanceServiceImpl balanceService;

    @PostMapping("/transaction")
    public TransactionDto transact(@PathVariable UUID id, @RequestBody TransactionRequest request) {
        return balanceService.performTransaction(id, request);
    }
}
