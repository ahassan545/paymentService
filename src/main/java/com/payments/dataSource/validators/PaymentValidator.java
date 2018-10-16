package com.payments.dataSource.validators;

import com.payments.models.Account;
import com.payments.models.Payment;
import org.apache.commons.lang3.StringUtils;

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
                && isValidToAccount(payment.getToAccount())
                && payment.getFromAccount().getBalance().compareTo(payment.getAmount()) > 0;
    }

    private boolean isValidToAccount(Account account){
        return account != null
                && account.getNumber() > 0
                && StringUtils.isNoneBlank(account.getSortCode());
    }
}
