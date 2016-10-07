package ua.legalist.service;

import java.util.Collection;
import java.util.Map;
import ua.legalist.model.Node;

public interface NodeService {

    Collection<Node> getAll();

    Map<Node,Map> getFullHierarchy();
    
    Map<Node,Map> getSimpleHierarchy();

    Node getNodeById(int nodeId);
    
    Node create (Node node);
}
