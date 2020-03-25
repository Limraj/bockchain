package com.devwider.blockchain;

import lombok.ToString;

import java.math.BigDecimal;
import java.math.MathContext;

@ToString
@lombok.Data
public class Data {
    private final String name;
    private BigDecimal amount;

    public Data(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public Data add(BigDecimal augend) {
        amount = amount.add(augend);
        return this;
    }

    public Data subtract(BigDecimal subtrahend) {
        amount = amount.subtract(subtrahend);
        return this;
    }
}
