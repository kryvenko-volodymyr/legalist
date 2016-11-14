package ua.legalist.service;

import java.util.List;
import ua.legalist.model.Node;
import ua.legalist.model.Process;

public interface ProcessService {

    Process getProcessById(int processId);

    Process createProcess(Node node);
    
    Process createProcess(int nodeId);

    void processAddNode(int processId, int nodeId);

    void update(Process process);

    void deleteById(int processId);
    
    List<Node> getProcessPath (int processId);

}
