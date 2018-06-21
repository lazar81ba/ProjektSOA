package interceptors;

import model.Element;
import repository.ElementDAO;
import repository.implementation.ElementDAOImpl;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Interceptor
@AddElement
public class AddElementInterceptor {
    private ElementDAO elementDAO = new ElementDAOImpl();


    @AroundInvoke
    public Object addElementEntry(InvocationContext ctx) throws Exception {
        Element element = (Element) ctx.getParameters()[0];
        List<Element> elementList = elementDAO.getByLabel(element.getElementLabel())
                                              .stream()
                                              .sorted(Comparator.comparing(Element::getFourthParameterValue).reversed())
                                              .collect(Collectors.toList());
        Integer highestPower = elementList.get(0).getFourthParameterValue();
        if(element.getFourthParameterValue()>highestPower)
            element.setFourthParameterValue(highestPower);

        Element[] elements = {element};
        ctx.setParameters(elements);
        return ctx.proceed();
    }
}
