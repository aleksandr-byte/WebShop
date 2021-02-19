package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "cpus")
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cpu_id", nullable = false)
    private Long id;

    @Column(name = "cpu_value", nullable = false, length = 100)
    private String value;

    public Cpu() {
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
