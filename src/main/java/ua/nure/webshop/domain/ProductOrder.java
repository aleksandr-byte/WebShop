package ua.nure.webshop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "product_orders")
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(nullable = false, name = "product_orders_id")
    private Long productOrderID;
    @Column(nullable = false)
    private BigDecimal totalPrice;
    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id")
    private Products products;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Order order;

    public ProductOrder() {
    }

    public Products getProduct() {
        return products;
    }

    public Long getProductOrderID() {
        return productOrderID;
    }

    public void setProductOrderID(Long productOrderID) {
        this.productOrderID = productOrderID;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public void setProduct(Products products) {
        this.products = products;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
