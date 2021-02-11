package ua.nure.webshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="categories_id")
    private long categoriesId;
    @Column (name="category_name", nullable = false, length = 20)
    private String categoryName;

    public Categories(String categoryName) {
        this.categoryName = categoryName;
    }

    public Categories() {
    }

    public long getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(long categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
