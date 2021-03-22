package ua.nure.webshop.domain;

import javax.persistence.Column;
import java.io.Serializable;

public class GradePK implements Serializable {

    private Long productID;

    private Long userID;

    public GradePK() {}

    public GradePK(Long productID, Long userID) {
        this.productID = productID;
        this.userID = userID;
    }
}
