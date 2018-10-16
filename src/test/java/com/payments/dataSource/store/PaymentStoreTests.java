package com.payments.dataSource.store;

import com.payments.dataSource.validators.Validator;
import com.payments.models.Account;
import com.payments.models.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class PaymentStoreTests {

    private Validator<Payment> validator;
    private PaymentStore store;
    private Payment payment;


    @Before
    public void setUp() throws IOException {
        validator = Mockito.mock(Validator.class);
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

        when(validator.isValid(any(Payment.class))).thenReturn(true);

        store = new PaymentStore(validator);
    }

    @Test
    public void create_null_payments_returns_null() throws Exception {
        when(validator.isValid(any(Payment.class))).thenReturn(false);
        Payment result = store.create(payment);

        assertNull(result);
    }

    @Test
    public void create_valid_payment_returns_payment() throws Exception {
        Payment result = store.create(payment);

        assertEquals(result, payment);
    }

    @Test
    public void create_existing_payment_returns_payment() throws Exception {
        store.create(payment);
        payment.setNotes("updated payment");

        Payment result = store.create(payment);

        assertEquals(result.getNotes(), payment.getNotes());
    }

    @Test
    public void findById_invalid_id_returns_Empty() throws Exception {
        store.create(payment);
        Optional<Payment> result = store.findById(10);

        assertFalse(result.isPresent());
    }

    @Test
    public void findById_empty_list_returns_Empty() throws Exception {
        Optional<Payment> result = store.findById(payment.getId());

        assertFalse(result.isPresent());
    }

    @Test
    public void findById_valid_id_returns_Payment() throws Exception {
        store.create(payment);
        Optional<Payment> result = store.findById(payment.getId());

        assertTrue(result.isPresent());
    }

    @Test
    public void getAll_empty_list_returns_empty_list() throws Exception {
        List<Payment> result = store.getAll();

        assertEquals(result.size(), 0);
    }

    @Test
    public void getAll_returns_list() throws Exception {
        store.create(payment);
        List<Payment> result = store.getAll();

        assertEquals(result.size(), 1);
    }

    @After
    public void tearDown() {
        store = null;
        validator = null;
    }
}
