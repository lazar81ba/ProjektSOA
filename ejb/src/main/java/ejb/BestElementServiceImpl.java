package ejb;

import model.Element;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import java.util.Collection;

@Singleton
@Remote(BestElementService.class)
public class BestElementServiceImpl implements BestElementService {
    private ElementService elementService = new ElementServiceImpl();
    private Collection<Element> bestDragons;
    private Collection<Element> bestMages;
    private Collection<Element> bestElves;

    @PostConstruct
    public void setup() {
        bestDragons = elementService.getBestElements("smok");
        bestMages = elementService.getBestElements("mag");
        bestElves = elementService.getBestElements("elf");
    }

    @Lock(LockType.READ)
    public Collection<Element> getBestDragons() {
        return bestDragons;
    }
    @Lock(LockType.READ)
    public Collection<Element> getBestMages() {
        return bestMages;
    }
    @Lock(LockType.READ)
    public Collection<Element> getBestElves() {
        return bestElves;
    }

    @Lock(LockType.WRITE)
    public void updateCollection(String elementLabel){
        if(elementLabel.equals("smok"))
            bestDragons = elementService.getBestElements(elementLabel);
        if(elementLabel.equals("mag"))
            bestMages = elementService.getBestElements(elementLabel);
        if(elementLabel.equals("elf"))
            bestElves = elementService.getBestElements(elementLabel);

    }

}
