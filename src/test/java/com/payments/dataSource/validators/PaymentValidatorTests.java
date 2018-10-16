package com.payments.dataSource.validators;

import com.payments.models.Account;
import com.payments.models.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class PaymentValidatorTests {
    private Validator<Account> accountValidator;
    private PaymentValidator validator;
    private Payment payment;

    @Before
    public void setUp() throws IOException {
        accountValidator = Mockito.mock(Validator.class);
        Account fromAccount = new Account();
        fromAccount.setBalance(new BigDecimal(50));
        fromAccount.setId(1);
        fromAccount.setName("fromAccount");
        fromAccount.setOwnerName("owner");
        fromAccount.setSortCode("sortcode");
        Account toAccount = new Account();
        fromAccount.setBalance(new BigDecimal(50));
        fromAccount.setId(2);
        fromAccount.setName("toAccount");
        fromAccount.setOwnerName("owner");
        fromAccount.setSortCode("sortcode");
        payment = new Payment();
        payment.setAmount(new BigDecimal(12));
        ;
        payment.setNotes("note");
        payment.setId(5);
        payment.setFromAccount(fromAccount);
        payment.setToAccount(toAccount);

        when(accountValidator.isValid(any(Account.class))).thenReturn(true);

        validator = new PaymentValidator(accountValidator);
    }

    @Test
    public void validator_null_returns_false() throws Exception {
        Boolean result = validator.isValid(null);

        assertFalse(result);
    }

    @Test
    public void validator_zero_amount_returns_false() throws Exception {
        payment.setAmount(new BigDecimal(0));
        Boolean result = validator.isValid(payment);

        assertFalse(result);
    }

    @Test
    public void validator_negative_amount_returns_false() throws Exception {
        payment.setAmount(new BigDecimal(-5));
        Boolean result = validator.isValid(payment);

        assertFalse(result);
    }

    @Test
    public void validator_amount_greater_than_fromAccount_returns_false() throws Exception {
        payment.setAmount(new BigDecimal(100));
        Boolean result = validator.isValid(payment);

        assertFalse(result);
    }

    @Test
    public void validator_null_amount_returns_false() throws Exception {
        payment.setAmount(null);
        Boolean result = validator.isValid(payment);

        assertFalse(result);
    }

    @Test
    public void validator_invalid_account_returns_false() throws Exception {
        when(accountValidator.isValid(any(Account.class))).thenReturn(false);

        Boolean result = validator.isValid(payment);

        assertFalse(result);
    }

    @Test
    public void validator_valid_payment_returns_true() throws Exception {
        Boolean result = validator.isValid(payment);

        assertTrue(result);
    }

    @After
    public void tearDown() {
        payment = null;
        accountValidator = null;
        validator = null;
    }
}
