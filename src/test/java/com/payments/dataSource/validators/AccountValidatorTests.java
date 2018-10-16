package com.payments.dataSource.validators;

import com.payments.models.Account;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountValidatorTests {
    private AccountValidator validator;
    private Account fromAccount;

    @Before
    public void setUp() throws IOException {
        fromAccount = new Account();
        fromAccount.setBalance(new BigDecimal(50));
        fromAccount.setNumber(1);
        fromAccount.setName("fromAccount");
        fromAccount.setOwnerName("owner");
        fromAccount.setSortCode("sortcode");

        validator = new AccountValidator();
    }

    @Test
    public void validator_null_returns_false() throws Exception {
        Boolean result = validator.isValid(null);

        assertFalse(result);
    }

    @Test
    public void validator_empty_name_returns_false() throws Exception {
        fromAccount.setName(StringUtils.EMPTY);
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_empty_sortCode_returns_false() throws Exception {
        fromAccount.setSortCode(StringUtils.EMPTY);
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_negative_balance_returns_false() throws Exception {
        fromAccount.setBalance(new BigDecimal(-5));
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_zero_balance_returns_false() throws Exception {
        fromAccount.setBalance(new BigDecimal(0));
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_null_balance_returns_false() throws Exception {
        fromAccount.setBalance(null);
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_empty_ownerName_returns_false() throws Exception {
        fromAccount.setOwnerName(StringUtils.EMPTY);
        Boolean result = validator.isValid(fromAccount);

        assertFalse(result);
    }

    @Test
    public void validator_valid_account_returns_true() throws Exception {
        Boolean result = validator.isValid(fromAccount);

        assertTrue(result);
    }

    @After
    public void tearDown() {
        fromAccount = null;
        validator = null;
    }
}
