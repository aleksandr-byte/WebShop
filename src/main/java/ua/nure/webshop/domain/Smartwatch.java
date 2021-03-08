package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "Smartwatch")
public class Smartwatch extends Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diagonal_id")
    private Diagonal diagonal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resolution_id")
    private Resolution resolution;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flash_memory_size_id")
    private FlashMemorySize flashMemorySize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "battery_capacity_id")
    private BatteryCapacity batteryCapacity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id")
    private Color color;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Diagonal getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Diagonal diagonal) {
        this.diagonal = diagonal;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public FlashMemorySize getFlashMemorySize() {
        return flashMemorySize;
    }

    public void setFlashMemorySize(FlashMemorySize flashMemorySize) {
        this.flashMemorySize = flashMemorySize;
    }

    public BatteryCapacity getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(BatteryCapacity batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
