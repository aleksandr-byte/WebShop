package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "display_types")
public class DisplayType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "display_type_id", nullable = false)
    private Long id;

    @Column(name = "display_type_value", nullable = false, length = 100)
    private String value;

    public DisplayType() {
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
