package com.payments.dataSource.store;

import com.payments.dataSource.validators.Validator;
import com.payments.models.Payment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class PaymentStore implements Store<Payment> {
    private static final AtomicLong NextId = new AtomicLong(1);

    private List<Payment> payments = Collections.synchronizedList(new ArrayList<Payment>());

    private Validator<Payment> paymentsValidator;

    public PaymentStore(Validator<Payment> paymentsValidator) {
        this.paymentsValidator = paymentsValidator;
    }

    public Payment create(Payment payment) {
        if (!paymentsValidator.isValid(payment))
            return null;

        Optional<Payment> value = findById(payment.getId());

        if (value.isPresent()) {
            value.get().Update(payment);
        } else {
            payment.setId(generateId());
            payments.add(payment);
        }

        return payment;
    }

    public Optional<Payment> findById(long id) {
        if (id <= 0)
            return Optional.empty();

        return payments.stream().filter(payment -> payment.getId() == id).findFirst();
    }

    public List<Payment> getAll() {
        return payments;
    }

    private long generateId() {
        return NextId.getAndIncrement();
    }
}