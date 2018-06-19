package webservices;

import converter.ElementConverter;
import remote.RemoteCategoryService;
import remote.RemoteElementService;
import soapModel.Element;

import javax.naming.NamingException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

public class ElementWS {

  private RemoteElementService remoteElementService;
  private RemoteCategoryService remoteCategoryService;

  public ElementWS(){
    try {
      this.remoteElementService = new RemoteElementService();
      this.remoteCategoryService = new RemoteCategoryService();
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }

  public void createElement(Long idCategory, Element element){
        model.Element elementToAdd = ElementConverter.convertSingleElement(element);
        elementToAdd.setCategory(remoteCategoryService.getCategoryService().getCategoryById(idCategory));
        remoteElementService.getElementService().insertElement(elementToAdd);
      }

  public void modifyElementParameter(Long idElement, Integer parameterValue){
    model.Element elementToModify = remoteElementService.getElementService().getElementById(idElement);
    elementToModify.setSecondParameterValue(parameterValue);
    remoteElementService.getElementService().updateElement(elementToModify);
    executePowerUpdate(elementToModify);
  }

  private void executePowerUpdate(model.Element elementToModify){
      Timer timer = new Timer(10000, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent arg0) {
              int randomNum = ThreadLocalRandom.current().nextInt(-2, 2);
              elementToModify.setFourthParameterValue(randomNum+elementToModify.getFourthParameterValue());
              remoteElementService.getElementService().updateElement(elementToModify);
          }
      });
      timer.setRepeats(false); // Only execute once
      timer.start();
  }
}
