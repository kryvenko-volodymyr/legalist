package ua.legalist.service.util;

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

    @Override
    public void load() {
        if (!dataLoaded) {
            loadStubDataToDB();
            dataLoaded = true;
        }
    }

    private void loadStubDataToDB() {
        createRootFullNode();
    }
    
    private void createRootFullNode () {
        Node newRootFullNode = new Node();
        newRootFullNode.setTitle("Root Full Node");
        newRootFullNode.setDetails("This is the ROOT FULL node");
        newRootFullNode = nodeService.create(newRootFullNode);
    }
}
