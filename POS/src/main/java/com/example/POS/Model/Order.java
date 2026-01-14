package com.example.POS.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem>  orderItems = new ArrayList<OrderItem>();

    private Double totalItemAmount;
    private Double taxAmount;
    private Double totalAmount;
    private String status;
    private String referenceNo;

    public Order() {
    }

    public Order(Long orderId, List<OrderItem> orderItems, Double totalItemAmount, Double taxAmount, Double totalAmount, String status, String referenceNo) {
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.totalItemAmount = totalItemAmount;
        this.taxAmount = taxAmount;
        this.totalAmount = totalAmount;
        this.status = status;
        this.referenceNo = referenceNo;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalItemAmount() {
        return totalItemAmount;
    }

    public void setTotalItemAmount(Double totalItemAmount) {
        this.totalItemAmount = totalItemAmount;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }
}
