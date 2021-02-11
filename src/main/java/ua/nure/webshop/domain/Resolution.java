package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity(name = "resolution")
public class Resolution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "resolution_id", nullable = false)
    private Long id;

    @Column(name = "resolution_value", nullable = false, length = 100)
    private String value;

    public Resolution() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
