package ejb;

import model.Element;

import javax.ejb.Remote;
import java.util.Collection;

@Remote
public interface BestElementService {

    public Collection<Element> getBestDragons() ;
    public Collection<Element> getBestMages();
    public Collection<Element> getBestElves();
    public void updateCollection(String elementLabel);
}