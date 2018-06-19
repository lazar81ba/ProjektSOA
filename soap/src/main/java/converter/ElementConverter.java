package converter;



public class ElementConverter {
    public static model.Element convertSingleElement(soapModel.Element element){
        model.Element element1 = new model.Element();
        element1.setElementLabel(element.getElementLabel());
        element1.setFirstParameterLabel(element.getFirstParameterLabel());
        element1.setFirstParameterValue(element.getFirstParameterValue());
        element1.setSecondParameterLabel(element.getSecondParameterLabel());
        element1.setSecondParameterValue(element.getSecondParameterValue());
        element1.setThirdParameterLabel(element.getThirdParameterLabel());
        element1.setThirdParameterValue(element.getThirdParameterValue());
        element1.setFourthParameterLabel(element.getFourthParameterLabel());
        element1.setFourthParameterValue(element.getFourthParameterValue());
        return element1;
    }
}
