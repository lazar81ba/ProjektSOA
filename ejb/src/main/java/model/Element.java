package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "element")
@NamedQueries({
        @NamedQuery(name="Element.findAll",
                query="SELECT e FROM Element e"),
        @NamedQuery(name="Element.findById",
                query="SELECT e FROM Element e WHERE e.id = :id"),
        @NamedQuery(name="Element.findByCategory",
                query="SELECT e FROM Element e WHERE e.category = :category"),
        @NamedQuery(name="Element.findByLabel",
                query="SELECT e FROM Element e WHERE e.elementLabel = :label"),
        @NamedQuery(name="Element.findByLabelAndCategory",
                query="SELECT e FROM Element e WHERE e.elementLabel = :label AND e.category= :category"),
})
public class Element implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "element_label")
    @NotNull
    private String elementLabel;

    @Column(name = "parameter1_label")
    @NotNull
    private String firstParameterLabel;

    @Column(name = "parameter1_value")
    @NotNull
    private String firstParameterValue;

    @Column(name = "parameter2_label")
    @NotNull
    private String secondParameterLabel;

    @Column(name = "parameter2_value")
    @NotNull
    private Integer secondParameterValue;

    @Column(name = "parameter3_label")
    @NotNull
    private String thirdParameterLabel;

    @Column(name = "parameter3_value")
    @NotNull
    private Integer thirdParameterValue;

    @Column(name = "parameter4_label")
    @NotNull
    private String fourthParameterLabel;

    @Column(name = "parameter4_value")
    @NotNull
    private Integer fourthParameterValue;

    public Element(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(id, element.id) &&
                Objects.equals(elementLabel, element.elementLabel) &&
                Objects.equals(firstParameterLabel, element.firstParameterLabel) &&
                Objects.equals(firstParameterValue, element.firstParameterValue) &&
                Objects.equals(secondParameterLabel, element.secondParameterLabel) &&
                Objects.equals(secondParameterValue, element.secondParameterValue) &&
                Objects.equals(thirdParameterLabel, element.thirdParameterLabel) &&
                Objects.equals(thirdParameterValue, element.thirdParameterValue) &&
                Objects.equals(fourthParameterLabel, element.fourthParameterLabel) &&
                Objects.equals(fourthParameterValue, element.fourthParameterValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, elementLabel, firstParameterLabel, firstParameterValue, secondParameterLabel, secondParameterValue, thirdParameterLabel, thirdParameterValue, fourthParameterLabel, fourthParameterValue);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getElementLabel() {
        return elementLabel;
    }

    public void setElementLabel(String elementLabel) {
        this.elementLabel = elementLabel;
    }

    public String getFirstParameterLabel() {
        return firstParameterLabel;
    }

    public void setFirstParameterLabel(String firstParameterLabel) {
        this.firstParameterLabel = firstParameterLabel;
    }

    public String getFirstParameterValue() {
        return firstParameterValue;
    }

    public void setFirstParameterValue(String firstParameterValue) {
        this.firstParameterValue = firstParameterValue;
    }

    public String getSecondParameterLabel() {
        return secondParameterLabel;
    }

    public void setSecondParameterLabel(String secondParameterLabel) {
        this.secondParameterLabel = secondParameterLabel;
    }

    public Integer getSecondParameterValue() {
        return secondParameterValue;
    }

    public void setSecondParameterValue(Integer secondParameterValue) {
        this.secondParameterValue = secondParameterValue;
    }

    public String getThirdParameterLabel() {
        return thirdParameterLabel;
    }

    public void setThirdParameterLabel(String thirdParameterLabel) {
        this.thirdParameterLabel = thirdParameterLabel;
    }

    public Integer getThirdParameterValue() {
        return thirdParameterValue;
    }

    public void setThirdParameterValue(Integer thirdParameterValue) {
        this.thirdParameterValue = thirdParameterValue;
    }

    public String getFourthParameterLabel() {
        return fourthParameterLabel;
    }

    public void setFourthParameterLabel(String fourthParameterLabel) {
        this.fourthParameterLabel = fourthParameterLabel;
    }

    public Integer getFourthParameterValue() {
        return fourthParameterValue;
    }

    public void setFourthParameterValue(Integer fourthParameterValue) {
        this.fourthParameterValue = fourthParameterValue;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
