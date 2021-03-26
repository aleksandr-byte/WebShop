package ua.nure.webshop.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Products")
public class Products extends Characteristics{

    @Column(nullable = false, length = 20, unique = true)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Transient
    private int rating;
    @Transient
    private Double cosineSimilarity;
    @Column(nullable = false, length = 1000)
    private String description;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "category_id")
    private Long category_id;
    @Column(name = "manufacturer_id")
    private Long manufacturer_id;
    @Transient
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Categories category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "manufacturer_id", insertable = false, updatable = false)
    private Manufacturer manufacturer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private List<Grade> gradeList;

    public Products() {
    }

    public Products(String name, BigDecimal price, String description, String imageName, Categories category, Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(Long manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public Double getCosineSimilarity() {
        return cosineSimilarity;
    }

    public void setCosineSimilarity(Double cosineSimilarity) {
        this.cosineSimilarity = cosineSimilarity;
    }

    @Override
    public String toString() {
        return "Products{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", manufacturer=" + manufacturer +
                '}';
    }
}
