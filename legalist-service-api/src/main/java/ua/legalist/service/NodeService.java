package ua.legalist.service;

import java.util.Collection;
import java.util.List;
import ua.legalist.model.Node;

public interface NodeService {

    Collection<Node> getAllNodes();

    NodeDTO getFullHierarchy();
    
//    Map<Node,Map> getSimpleHierarchy();

    Node getNodeById(int nodeId);
    
    Node update (Node node);
    
    Node create (String title, String details);
    
    /*
    * Returns ultimete (the root of refferals chain) referred node.
    * Accordingly, if the argument node does not refer to any node
    * (referredNode == null), returns this node.
    */
    Node getUltimategetReferredNode (Node node);
    
    /*
    * Returns Collection<Node> of nodes
    * from the root node up to the Node argument
    */
    List<Node> getNodeGenealogy (Node node);

    public Collection<Node> getNodeChildNodes(int nodeId);
}
