package converter;

import restModel.Category;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CategoryConverter {

    public static Category convertSingleCategory(model.Category category){
        return new Category(category);
    }

    public static Collection<Category> convertList(Collection<model.Category> categoryCollection){
        List<Category> categories = new LinkedList<Category>();
        for(model.Category model: categoryCollection){
            categories.add(convertSingleCategory(model));
        }
        return categories;
    }

}
