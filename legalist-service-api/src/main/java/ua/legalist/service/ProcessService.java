package ua.legalist.service;

import ua.legalist.model.Node;
import ua.legalist.model.Process;

public interface ProcessService {

    Process getProcessById(int processId);

    Process createProcess(Node node);

    Node processAddNode(int processId, int nodeId);

    void update(Process process);

    void deleteById(int processId);

}
