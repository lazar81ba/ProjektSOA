package restModel;


import java.io.Serializable;

public class Element implements Serializable{


    private Long id;
    private String elementLabel;
    private String firstParameterLabel;
    private String firstParameterValue;
    private String secondParameterLabel;
    private Integer secondParameterValue;
    private String thirdParameterLabel;
    private Integer thirdParameterValue;
    private String fourthParameterLabel;
    private Integer fourthParameterValue;

    public Element() {
    }

    public Element(model.Element model) {
        this.id = model.getId();
        this.elementLabel = model.getElementLabel();
        this.firstParameterLabel = model.getFirstParameterLabel();
        this.firstParameterValue = model.getFirstParameterValue();
        this.secondParameterLabel = model.getSecondParameterLabel();
        this.secondParameterValue = model.getSecondParameterValue();
        this.thirdParameterLabel = model.getThirdParameterLabel();
        this.thirdParameterValue = model.getThirdParameterValue();
        this.fourthParameterLabel = model.getFourthParameterLabel();
        this.fourthParameterValue = model.getFourthParameterValue();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
