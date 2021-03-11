package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "Smartwatch")
public class Smartwatch extends Products {

    @Column(name = "diagonal_id")
    private Long diagonal_id;
    @Column(name = "resolution_id")
    private Long resolution_id;
    @Column(name = "flash_memory_size_id")
    private Long flash_memory_size_id;
    @Column(name = "battery_capacity_id")
    private Long battery_capacity_id;
    @Column(name = "color_id")
    private Long color_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diagonal_id", insertable = false, updatable = false)
    private Diagonal diagonal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "resolution_id", insertable = false, updatable = false)
    private Resolution resolution;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flash_memory_size_id", insertable = false, updatable = false)
    private FlashMemorySize flashMemorySize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "battery_capacity_id", insertable = false, updatable = false)
    private BatteryCapacity batteryCapacity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", insertable = false, updatable = false)
    private Color color;

    public Long getDiagonal_id() {
        return diagonal_id;
    }

    public void setDiagonal_id(Long diagonal_id) {
        this.diagonal_id = diagonal_id;
    }

    public Long getResolution_id() {
        return resolution_id;
    }

    public void setResolution_id(Long resolution_id) {
        this.resolution_id = resolution_id;
    }

    public Long getFlash_memory_size_id() {
        return flash_memory_size_id;
    }

    public void setFlash_memory_size_id(Long flash_memory_size_id) {
        this.flash_memory_size_id = flash_memory_size_id;
    }

    public Long getBattery_capacity_id() {
        return battery_capacity_id;
    }

    public void setBattery_capacity_id(Long battery_capacity_id) {
        this.battery_capacity_id = battery_capacity_id;
    }

    public Long getColor_id() {
        return color_id;
    }

    public void setColor_id(Long color_id) {
        this.color_id = color_id;
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
