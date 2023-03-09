package com.example.springdataintro.services;

import java.math.BigDecimal;

public interface AccountService {

    void withDrawMoney(BigDecimal amount, Long id);

    void depositMoney(BigDecimal amount,Long id);
}
