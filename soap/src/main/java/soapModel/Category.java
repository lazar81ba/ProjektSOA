package soapModel;


import converter.ElementConverter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class Category implements Serializable {
    private String categoryLabel;
    private String parameterLabel;
    private Integer parameterValue;


    public Category() {
    }

    public Category(model.Category model) {
        this.categoryLabel = model.getCategoryLabel();
        this.parameterLabel = model.getParameterLabel();
        this.parameterValue = model.getParameterValue();
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

}
