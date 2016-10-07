package ua.legalist.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        }
    }

    private void loadStubDataToDB() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
