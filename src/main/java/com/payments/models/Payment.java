package com.payments.models;

import java.math.BigDecimal;

public class Payment {

    private long id;
    private Account fromAccount;
    private Account toAccount;
    private BigDecimal amount;
    private String notes;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setFromAccount(Account account) {
        this.fromAccount = account;
    }

    public Account getFromAccount() {
        return this.fromAccount;
    }

    public void setToAccount(Account account) {
        this.toAccount = account;
    }

    public Account getToAccount() {
        return this.toAccount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return this.notes;
    }

    public void Update(Payment payment) {
        if (payment == null)
            return;

        this.fromAccount = payment.getFromAccount();
        this.toAccount = payment.getToAccount();
        this.notes = payment.getNotes();
        this.amount = payment.getAmount();
    }
}
