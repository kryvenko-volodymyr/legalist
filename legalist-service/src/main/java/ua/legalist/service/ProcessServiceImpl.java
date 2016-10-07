package ua.legalist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.legalist.model.Node;
import ua.legalist.model.Process;
import ua.legalist.persistence.ProcessDao;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ProcessDao processDao;
    
    @Override
    public Process getProcessById(int processId) {
        return processDao.read(processId);
    }

    @Override
    public Process createProcess(Node node) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Node processAddNode(int processId, int nodeId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(Process process) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteById(int processId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    

}
