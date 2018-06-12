package managedBeans;

import model.Category;
import model.Element;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ElementService {


    @ManagedProperty("#{remoteElementService}")
    private RemoteElementService remoteElementService;
    @ManagedProperty("#{remoteCategoryService}")
    private RemoteCategoryService remoteCategoryService;


    private Element panelElement;
    private boolean update;
    private List<SelectItem> selectItems;


    private void fillSelectItems() {
        selectItems = new ArrayList<SelectItem>();
        for (Category foo : remoteCategoryService.getAllCategories()) {
            selectItems.add(new SelectItem(foo,foo.getCategoryLabel()));
        }
    }

    public String insert(){
        panelElement = new Element();
        update = false;
        return "elementPanel";
    }


    public String update(Element element){
        panelElement = element;
        update = true;
        return "elementPanel";
    }

    public String submit(){
        if(update)
            remoteElementService.getElementService().updateElement(panelElement);
        else
            remoteElementService.getElementService().insertElement(panelElement);
        return "index.xhmtl?faces-redirect=true";

    }
    public Element getPanelElement() {
        return panelElement;
    }

    public void setPanelElement(Element panelElement) {
        this.panelElement = panelElement;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }


    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }

    public void setRemoteElementService(RemoteElementService remoteElementService) {
        this.remoteElementService = remoteElementService;
    }

    public void setRemoteCategoryService(RemoteCategoryService remoteCategoryService) {
        this.remoteCategoryService = remoteCategoryService;
        fillSelectItems();
    }
}
