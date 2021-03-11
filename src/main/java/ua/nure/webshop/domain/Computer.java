package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "Computer")
public class Computer extends Products {

    @Column(name = "flash_memory_size_id")
    private Long flash_memory_size_id;
    @Column(name = "color_id")
    private Long color_id;
    @Column(name = "memory_size_id")
    private Long memory_size_id;
    @Column(name = "cpu_id")
    private Long cpu_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "flash_memory_size_id", insertable = false, updatable = false)
    private FlashMemorySize flashMemorySize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "color_id", insertable = false, updatable = false)
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "memory_size_id", insertable = false, updatable = false)
    private MemorySize memorySize;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cpu_id", insertable = false, updatable = false)
    private Cpu cpu;

    public Computer() {
    }

    public Long getFlash_memory_size_id() {
        return flash_memory_size_id;
    }

    public void setFlash_memory_size_id(Long flash_memory_size_id) {
        this.flash_memory_size_id = flash_memory_size_id;
    }

    public Long getColor_id() {
        return color_id;
    }

    public void setColor_id(Long color_id) {
        this.color_id = color_id;
    }

    public Long getMemory_size_id() {
        return memory_size_id;
    }

    public void setMemory_size_id(Long memory_size_id) {
        this.memory_size_id = memory_size_id;
    }

    public Long getCpu_id() {
        return cpu_id;
    }

    public void setCpu_id(Long cpu_id) {
        this.cpu_id = cpu_id;
    }

    public FlashMemorySize getFlashMemorySize() {
        return flashMemorySize;
    }

    public void setFlashMemorySize(FlashMemorySize flashMemorySize) {
        this.flashMemorySize = flashMemorySize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
}
