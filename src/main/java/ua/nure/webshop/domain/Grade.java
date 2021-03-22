package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@IdClass(GradePK.class)
@Table(name = "grade")
public class Grade {

    @Id
    @Column(name = "product_id", nullable = false)
    private Long productID;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userID;

    @Column(name = "grade", nullable = false)
    private double grade;

    public Grade() {
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }


}
