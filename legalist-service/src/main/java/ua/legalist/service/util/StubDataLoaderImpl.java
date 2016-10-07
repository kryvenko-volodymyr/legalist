package ua.legalist.service.util;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.legalist.model.Node;
import ua.legalist.service.FieldService;
import ua.legalist.service.NodeService;
import ua.legalist.service.ProcessService;
import ua.legalist.service.UserService;

@Service("stubDataLoader")
public class StubDataLoaderImpl implements StubDataLoader {
    
    @Autowired
    UserService userService;
    
    @Autowired
    NodeService nodeService;
    
    @Autowired
    ProcessService processService;
    
    @Autowired
    FieldService fieldService;
    
    private boolean dataLoaded = false;
    private int throughCounter = 1;

    @Override
    public void load() {
        if (!dataLoaded) {
            loadStubDataToDB();
            dataLoaded = true;
        }
    }

    private void loadStubDataToDB() {
        Node rootFullNode = createRootFullNode();
        createChildNodes(3, rootFullNode);
        for (Node childNode : rootFullNode.getChildNodes()) {
            createChildNodes(3, childNode);
        }
    }
    
    private Node createRootFullNode () {
        Node newRootFullNode = new Node();
        newRootFullNode.setTitle("Root Full Node");
        newRootFullNode.setDetails("This is the ROOT FULL node");
        newRootFullNode.setChildNodes(new HashSet<Node>());
        return nodeService.create(newRootFullNode);
    }

    private void createChildNodes(int quantity, Node parentNode) {
        Collection<Node> childNodes = parentNode.getChildNodes();
        for (int i = 1; i <= quantity; i++) {
            Node newChildNode = new Node();
            newChildNode.setTitle("Full Node #" + throughCounter++);
            newChildNode.setDetails("Child #" + i + " of " + parentNode.getTitle());
            newChildNode.setParentNode(parentNode);
            newChildNode.setChildNodes(new HashSet<Node>());
            newChildNode = nodeService.create(newChildNode);
            childNodes.add(newChildNode);
        }
        nodeService.update(parentNode);
    }
}
