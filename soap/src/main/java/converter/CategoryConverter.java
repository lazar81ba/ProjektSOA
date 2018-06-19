package converter;

import soapModel.Category;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CategoryConverter {

    public static model.Category convertSingleCategory(soapModel.Category category){
        model.Category category1 = new model.Category();
        category1.setParameterLabel(category.getParameterLabel());
        category1.setParameterValue(category.getParameterValue());
        category1.setCategoryLabel(category.getCategoryLabel());
        return category1;
    }



}
