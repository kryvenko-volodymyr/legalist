package ua.legalist.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.legalist.service.NodeService;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;
import ua.legalist.model.Node;

@RestController
@RequestMapping("/api/nodes")
public class NodesREST {

    @Autowired
    NodeService nodeService;

    @GetMapping("/simple-hierarchy")
    public Map<Node, Map> simpleHierarchyGet() {
        return nodeService.getSimpleHierarchy();
    }

    @GetMapping("/full-hierarchy")
    public Map<Node, Map> fullHierarchyGet() {
        return nodeService.getFullHierarchy();
    }

    @GetMapping("{nodeId}")
    public Node nodeGet(@PathVariable int nodeId) {
        return nodeService.getNodeById(nodeId);
    }
}
