package com.sapo.dto;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "SapoOrder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Integer id;
    @Column(name = "account_id")
    public Integer accountId;
    @Column(name = "total_price")
    public BigDecimal totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public Order(Integer accountId, BigDecimal totalPrice) {
        this.accountId = accountId;
        this.totalPrice = totalPrice;
    }
}
