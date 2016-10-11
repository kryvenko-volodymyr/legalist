package ua.legalist.service.util;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Node;
import ua.legalist.service.FieldService;
import ua.legalist.service.NodeService;
import ua.legalist.service.ProcessService;
import ua.legalist.service.UserService;

/*
Это лютая ересь, написанная исключительно для целей тестирования
 */
@Service("stubDataLoader")
@Transactional
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
        createNodes();
    }

    private void createChildNodes(int quantity, Node parentNode) {
        for (int i = 1; i <= quantity; i++) {
            String title = "Node #" + throughCounter++;
            String details = "Child #" + i + " of " + parentNode.getTitle();
            Node newChildNode = nodeService.create(title, details);
            newChildNode.setParentNode(parentNode);
            parentNode.getChildNodes().add(newChildNode);
        }
        nodeService.update(parentNode);
    }

    private void createNodes() {
        Node rootFullNode = nodeService.create("Root Full Node",
                "This is the ROOT FULL node");

        createChildNodes(3, rootFullNode);

        for (Node childNode : rootFullNode.getChildNodes()) {
            createChildNodes(3, childNode);
        }

        Node rootSimpleNode = nodeService.create("Root Simple Node",
                "This is the ROOT SIMPLE node");
        createChildNodes(3, rootSimpleNode);

        for (Node childNode : rootSimpleNode.getChildNodes()) {
            childNode.setReferredNode(rootFullNode);
            rootFullNode.getReferringNodes().add(childNode);
        }
    }

}
