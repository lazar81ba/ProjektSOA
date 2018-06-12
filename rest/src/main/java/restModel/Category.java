package restModel;


import converter.ElementConverter;
import restModel.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Category implements Serializable {
    private Long id;
    private String categoryLabel;
    private String parameterLabel;
    private Integer parameterValue;
    private Collection<Element> elements;


    public Category() {
    }

    public Category(model.Category model) {
        this.id = model.getId();
        this.categoryLabel = model.getCategoryLabel();
        this.parameterLabel = model.getParameterLabel();
        this.parameterValue = model.getParameterValue();
        this.elements = new ArrayList<Element>(ElementConverter.convertList(model.getElements()));
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
}
