package webservices;

import converter.CategoryConverter;
import soapModel.Category;
import remote.RemoteCategoryService;

import javax.naming.NamingException;

public class CategoryWS {
  private RemoteCategoryService remoteCategoryService;

  public CategoryWS(){
    try {
      remoteCategoryService = new RemoteCategoryService();
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }


  public void createCategory(String login, Category category){
    remoteCategoryService.getCategoryService().insertCategory(login, CategoryConverter.convertSingleCategory(category));
  }


}
