package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {
    USD(1.0D),
    EUR(1.08D),
    BYN(0.31D),
    RUB(0.011D);

    private final double exchangeRate;
}
