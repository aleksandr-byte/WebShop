package ua.nure.webshop.domain;


import javax.persistence.*;

@Entity
@Table(name = "Smartphone")
public class Smartphone extends Products {

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "display_type_id")
    private DisplayType displayType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memory_size_id")
    private MemorySize memorySize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id")
    private Cpu cpu;

    public Smartphone() {
    }

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

    public DisplayType getDisplayType() {
        return displayType;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public MemorySize getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(MemorySize memorySize) {
        this.memorySize = memorySize;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id=" + id +
                ", diagonal=" + diagonal.getValue() +
                ", resolution=" + resolution.getValue() +
                ", flashMemorySize=" + flashMemorySize.getValue() +
                ", batteryCapacity=" + batteryCapacity.getValue() +
                ", color=" + color.getValue() +
                ", displayType=" + displayType.getValue() +
                ", memorySize=" + memorySize.getValue() +
                ", cpu=" + cpu.getValue() +
                '}';
    }
}
