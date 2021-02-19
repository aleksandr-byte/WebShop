package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "battery_capacities")
public class BatteryCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "battery_capacity_id", nullable = false)
    private Long id;

    @Column(name = "battery_capacity_value", nullable = false, length = 100)
    private String value;

    public BatteryCapacity() {
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
