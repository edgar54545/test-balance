package com.test.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionRequest {
    private String type;
    @Positive
    private BigDecimal amount;
    private String currency;
}
