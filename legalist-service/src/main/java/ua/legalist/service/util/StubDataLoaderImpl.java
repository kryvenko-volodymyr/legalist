package ua.legalist.service.util;

import org.springframework.stereotype.Service;

@Service("stubDataLoader")
public class StubDataLoaderImpl implements StubDataLoader {

    private boolean dataLoaded = false;
    
    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
