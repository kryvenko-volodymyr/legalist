package ua.legalist.webapp;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.legalist.service.NodeService;
import org.springframework.web.bind.annotation.PathVariable;
import ua.legalist.model.Node;
import ua.legalist.service.NodeDTO;

@RestController
@RequestMapping("/api/nodes")
public class NodesREST {

    @Autowired
    NodeService nodeService;

//    @GetMapping("/simple-hierarchy")
//    public Map<Node, Map> simpleHierarchyGet() {
//        return nodeService.getSimpleHierarchy();
//    }

    @GetMapping("")
    public NodeDTO fullHierarchyGet() {
        NodeDTO fullHierarchy =  nodeService.getFullHierarchy();
        return fullHierarchy;
    }

    @GetMapping("{nodeId}")
    public Node nodeGet(@PathVariable int nodeId) {
        return nodeService.getNodeById(nodeId);
    }
    
    @GetMapping("{nodeId}/nodes")
    public Collection<Node> nodeChildNodesGet(@PathVariable int nodeId) {
        return nodeService.getNodeChildNodes(nodeId);
    }
}
