package translator;

import restModel.Category;

import java.util.LinkedList;
import java.util.List;

public class CategoryTranslator {

    public static Category translateCategory(Category category){
        Category category1 = new Category();
        category1.setId(category.getId());
        category1.setCategoryLabel("[Translate] "+category.getCategoryLabel());
        category1.setParameterLabel("[Translate] " + category.getParameterLabel());
        category1.setElements(ElementTranslator.translateElements(category.getElements()));
        category1.setParameterValue(category.getParameterValue());
        return category1;
    }

    public static List<Category> translateCategories(List<Category> categories){
        List<Category> categoriesToReturn = new LinkedList<>();
        for (Category category :  categories){
            categoriesToReturn.add(translateCategory(category));
        }
        return categoriesToReturn;
    }
}
