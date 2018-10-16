package com.payments.models;

import java.math.BigDecimal;

public class Account {

    private long number;
    private String ownerName;
    private String name;
    private BigDecimal balance;
    private String sortCode;


    public void setNumber(long number) {
        this.number = number;
    }

    public long getNumber() {
        return this.number;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSortCode() {
        return this.sortCode;
    }
}
