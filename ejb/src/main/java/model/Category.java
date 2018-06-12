package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name="Category.findAll",
                query="SELECT c FROM Category c"),
        @NamedQuery(name="Category.findById",
                query="SELECT c FROM Category c WHERE c.id = :id"),
        @NamedQuery(name="Category.findByCategoryLabel",
                query="SELECT c FROM Category c WHERE c.categoryLabel = :categoryLabel"),
        @NamedQuery(name="Category.findByCategoryLabelAndUser",
                query="SELECT c FROM Category c WHERE c.categoryLabel = :categoryLabel AND c.user = :user"),
        @NamedQuery(name="Category.findByUser",
                query="SELECT c FROM Category c WHERE c.user = :user"),
})
public class Category implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "login_user")
    private User user;

    @Column(name = "category_label")
    @NotNull
    private String categoryLabel;

    @Column(name = "parameter_label")
    @NotNull
    private String parameterLabel;

    @Column(name = "parameter_value")
    @NotNull
    private Integer parameterValue;

    @OneToMany(cascade={CascadeType.ALL}, orphanRemoval = true,
            fetch=FetchType.EAGER, mappedBy = "category")
    private Collection<Element> elements;

    public Category(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) &&
                Objects.equals(categoryLabel, category.categoryLabel) &&
                Objects.equals(parameterLabel, category.parameterLabel) &&
                Objects.equals(parameterValue, category.parameterValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, categoryLabel, parameterLabel, parameterValue);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getParameterLabel() {
        return parameterLabel;
    }

    public void setParameterLabel(String parameterLabel) {
        this.parameterLabel = parameterLabel;
    }

    public Integer getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Integer parameterValue) {
        this.parameterValue = parameterValue;
    }

    public Collection<Element> getElements() {
        return elements;
    }

    public void setElements(Collection<Element> elements) {
        this.elements = elements;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
