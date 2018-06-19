package translator;

import restModel.Category;
import restModel.Element;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ElementTranslator {
    public static Element translateElement(Element element){
        Element element1 = new Element();
        element1.setId(element.getId());
        element1.setElementLabel("[Translate] " + element.getElementLabel());
        element1.setFirstParameterLabel("[Translate] " + element.getFirstParameterLabel());
        element1.setSecondParameterLabel("[Translate] " + element.getSecondParameterLabel());
        element1.setThirdParameterLabel("[Translate] " + element.getThirdParameterLabel());
        element1.setFourthParameterLabel("[Translate] " + element.getFourthParameterLabel());
        element1.setFirstParameterValue("[Translate] " + element.getFirstParameterValue());
        element1.setSecondParameterValue(element.getSecondParameterValue());
        element1.setThirdParameterValue(element.getThirdParameterValue());
        element1.setFourthParameterValue(element.getFourthParameterValue());
        return element1;
    }

    public static List<Element> translateElements(Collection<Element> elements){
        List<Element> elementsToReturn = new LinkedList<>();
        for (Element element :  elements){
            elementsToReturn.add(translateElement(element));
        }
        return elementsToReturn;
    }
}
