package com.payments.dataSource.validators;

import com.payments.models.Account;
import com.payments.models.Payment;

import java.math.BigDecimal;

public class PaymentValidator implements Validator<Payment> {

    private Validator<Account> accountValidator;

    public PaymentValidator(Validator<Account> accountValidator) {
        this.accountValidator = accountValidator;
    }

    @Override
    public Boolean isValid(Payment payment) {

        return payment != null
                && payment.getAmount() != null
                && payment.getAmount().compareTo(BigDecimal.ZERO) > 0
                && accountValidator.isValid(payment.getFromAccount())
                && accountValidator.isValid(payment.getFromAccount())
                && payment.getFromAccount().getBalance().compareTo(payment.getAmount()) > 0;
    }
}
