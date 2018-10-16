package com.payments.dataSource.validators;

import com.payments.models.Account;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public class AccountValidator implements Validator<Account> {

    @Override
    public Boolean isValid(Account account) {
        return account != null
                && StringUtils.isNoneBlank(account.getName())
                && account.getNumber() > 0
                && account.getBalance() != null
                && account.getBalance().compareTo(BigDecimal.ZERO) > 0
                && StringUtils.isNoneBlank(account.getOwnerName())
                && StringUtils.isNoneBlank(account.getSortCode());
    }
}
