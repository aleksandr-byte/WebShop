package ua.nure.webshop.utilityfunction.model;

import ua.nure.webshop.domain.Products;

import java.util.Objects;

public class FunctionUtility {

    private double p1;
    private double p2;
    private double K;
    private Products product;

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public double getK() {
        return K;
    }

    public void setK(double k) {
        K = k;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctionUtility that = (FunctionUtility) o;
        return Double.compare(that.p1, p1) == 0 &&
                Double.compare(that.p2, p2) == 0 &&
                Double.compare(that.K, K) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p1, p2, K, product);
    }

    @Override
    public String toString() {
        return "LocalUtility{" +
                "ID: " + product.getId() +
                ", name: " + product.getName() +
                ", K: " + K +
                "}\r\n";
    }
}


