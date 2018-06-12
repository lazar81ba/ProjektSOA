import org.apache.commons.codec.digest.DigestUtils;
import repository.CategoryDAO;
import repository.ElementDAO;
import repository.implementation.CategoryDAOImpl;
import repository.implementation.ElementDAOImpl;

public class Application {

    private static CategoryDAO categoryDAO = new CategoryDAOImpl();
    private static ElementDAO elementDAO = new ElementDAOImpl();

    public static void main(String[] args){

        String s1 = DigestUtils.sha256Hex("admin");

        String s2 = DigestUtils.sha256Hex("user");

//        String sha256hex = Hashing.sha256()
//                .hashString("admin", StandardCharsets.UTF_8)
//                .toString();
//
//        String sha256hex2 = Hashing.sha256()
//                .hashString("user", StandardCharsets.UTF_8)
//                .toString();

//        Collection<Category> categoryCollection = categoryDAO.getAll();
//        Category category = categoryDAO.getById(categoryCollection.stream().findAny().get().getId());
//        Collection<Category> categoryCollection1 = categoryDAO.getByCategoryLabel("las");
//        Collection<Element> elements = category.getElements();
//
//        category.setCategoryLabel("test");
//        categoryDAO.update(category);
//        categoryDAO.delete(category);
//        categoryDAO.insert(category);
//        Category category1 =  new Category();
//        category1.setCategoryLabel("test");
//        category1.setLoginUser("223322");
//        category1.setParameterLabel("test");
//        category1.setParameterValue(12341);
//        categoryDAO.insert(category1);

//    Collection<Element> elementCollection = elementDAO.getAll();
//    Element element = elementDAO.getById(elementCollection.stream().findAny().get().getId());
//    Collection<Element> elementCollection1 = elementDAO.getByCategory(category);
//
//    element.setElementLabel("test");
//    elementDAO.update(element);
//    elementDAO.delete(element);
//    elementDAO.insert(element);
//    Element element1 = new Element();
//        element1.setElementLabel("test");
//        element1.setFirstParameterLabel("test");
//        element1.setFirstParameterValue("test");
//        element1.setSecondParameterLabel("test");
//        element1.setSecondParameterValue(1);
//
//        element1.setThirdParameterLabel("test");
//        element1.setThirdParameterValue(1);
//
//
//        element1.setFourthParameterLabel("test");
//        element1.setFourthParameterValue(1);
//
//        element1.setCategory(category);
//        elementDAO.insert(element1);
    }
}
