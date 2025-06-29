package com.test.controller;

import com.test.dto.BalanceDto;
import com.test.dto.BalanceRequest;
import com.test.service.impl.BalanceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceServiceImpl balanceService;

    @PostMapping
    public BalanceDto create(@RequestBody BalanceRequest balanceRequest) {
        return balanceService.createBalance(balanceRequest.getName());
    }

    @GetMapping("/{id}/amount")
    public BigDecimal getBalance(@PathVariable UUID id) {
        return balanceService.getBalance(id);
    }

}