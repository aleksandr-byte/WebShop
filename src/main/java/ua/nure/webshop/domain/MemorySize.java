package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity(name = "memory_sizes")
public class MemorySize {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "memory_size_id", nullable = false)
    private Long id;

    @Column(name = "memory_size_value", nullable = false, length = 100)
    private String value;

    public MemorySize() {
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
