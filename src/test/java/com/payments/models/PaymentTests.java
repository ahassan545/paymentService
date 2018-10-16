package com.payments.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentTests {
    private Payment payment;
    private Account fromAccount;
    private Account toAccount;

    @Before
    public void setUp() throws IOException {
        Account fromAccount = new Account();
        fromAccount.setBalance(new BigDecimal(50));
        fromAccount.setNumber(1);
        fromAccount.setName("fromAccount");
        fromAccount.setOwnerName("owner");
        fromAccount.setSortCode("sortcode");
        Account toAccount = new Account();
        fromAccount.setBalance(new BigDecimal(50));
        fromAccount.setNumber(2);
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

    }

    @Test
    public void Update_null_payments_doesnt_throw_exception() throws Exception {
        payment.Update(null);
    }

    @Test
    public void Update_amount_returns_valid_value() throws Exception {
        Payment updatedPayment = new Payment();
        updatedPayment.setAmount(new BigDecimal(5));

        payment.Update(updatedPayment);

        assertEquals(payment.getAmount(), updatedPayment.getAmount());
    }

    @Test
    public void Update_id_id_not_changed() throws Exception {
        Payment updatedPayment = new Payment();
        updatedPayment.setId(300);

        payment.Update(updatedPayment);

        assertEquals(payment.getId(), 5);
    }

    @Test
    public void Update_notes_returns_new_Notes() throws Exception {
        String note = "updated notes";
        Payment updatedPayment = new Payment();
        updatedPayment.setNotes("updated notes");

        payment.Update(updatedPayment);

        assertEquals(payment.getNotes(), note);
    }

    @Test
    public void Update_fromAccount_returns_new_Account() throws Exception {
        Account account = new Account();

        Payment updatedPayment = new Payment();
        updatedPayment.setFromAccount(account);

        payment.Update(updatedPayment);

        assertEquals(payment.getFromAccount(), account);
    }

    @Test
    public void Update_toAccount_returns_new_Account() throws Exception {
        Account account = new Account();

        Payment updatedPayment = new Payment();
        updatedPayment.setToAccount(account);

        payment.Update(updatedPayment);

        assertEquals(payment.getToAccount(), account);
    }

    @After
    public void tearDown() {
        toAccount = null;
        fromAccount = null;
        payment = null;
    }
}
