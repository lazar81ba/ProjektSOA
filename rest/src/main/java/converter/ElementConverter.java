package converter;

import restModel.Category;
import restModel.Element;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ElementConverter {
    public static Element convertSingleElement(model.Element element){
        return new Element(element);
    }

    public static Collection<Element> convertList(Collection<model.Element> elementCollection){
        List<Element> elements = new LinkedList<Element>();
        for(model.Element model: elementCollection){
            elements.add(convertSingleElement(model));
        }
        return elements;
    }

    public static model.Element convertToModelElement(Element element){
        model.Element returnElement = new model.Element();

        returnElement.setElementLabel(element.getElementLabel());
        returnElement.setFirstParameterLabel(element.getFirstParameterLabel());
        returnElement.setFirstParameterValue(element.getFirstParameterValue());
        returnElement.setSecondParameterLabel(element.getSecondParameterLabel());
        returnElement.setSecondParameterValue(element.getSecondParameterValue());
        returnElement.setThirdParameterLabel(element.getThirdParameterLabel());
        returnElement.setThirdParameterValue(element.getThirdParameterValue());
        returnElement.setFourthParameterLabel(element.getFourthParameterLabel());
        returnElement.setFourthParameterValue(element.getFourthParameterValue());

        return returnElement;

    }
}
