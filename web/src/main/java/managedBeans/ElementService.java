package managedBeans;

import model.Category;
import model.Element;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class ElementService {


    @ManagedProperty("#{remoteElementService}")
    private RemoteElementService remoteElementService;
    @ManagedProperty("#{remoteCategoryService}")
    private RemoteCategoryService remoteCategoryService;


    private Element panelElement;
    private boolean update;
    private List<SelectItem> selectItems;
    private boolean dataSubmitted;


    @PostConstruct
    public void setUp(){
        Map<String,String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String elementID = params.get("elementID");
        if(elementID!=null && !elementID.equals("")){
            panelElement = remoteElementService.getElementById(Long.parseLong(elementID));
            panelElement.setCategory(new Category());
            update = true;
        }
        else
            panelElement = new Element();
    }


    private void fillSelectItems() {
        selectItems = new ArrayList<SelectItem>();
        for (Category foo : remoteCategoryService.getAllCategories()) {
            selectItems.add(new SelectItem(foo,foo.getCategoryLabel()));
        }
    }

    public String submit(){
        if(update)
            remoteElementService.updateElement(panelElement);
        else
            remoteElementService.insertElement(panelElement);
        return "index.xhmtl?faces-redirect=true";

    }
    public Element getPanelElement() {
        return panelElement;
    }

    public void setPanelElement(Element panelElement) {
        this.panelElement = panelElement;
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

    public boolean isDataSubmitted() {
        return dataSubmitted;
    }

    public void setDataSubmitted(boolean dataSubmitted) {
        this.dataSubmitted = dataSubmitted;
    }
}
