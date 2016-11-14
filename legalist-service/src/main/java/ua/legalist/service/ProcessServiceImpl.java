package ua.legalist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Node;
import ua.legalist.model.Process;
import ua.legalist.persistence.ProcessDao;
import ua.legalist.process.factories.ProcessFactory;

@Service("processService")
@Transactional
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    ProcessDao processDao;

    @Autowired
    ProcessFactory processFactory;

    @Autowired
    NodeService nodeService;

    @Override
    public Process getProcessById(int processId) {
        return processDao.read(processId);
    }

    // TODO: this method sould also accept User object to bind the new process to
    // and make Process.user "optional = false"
    @Override
    public Process createProcess(Node detachedNode) {
        return createProcess(detachedNode.getId());
//        Node persistentNode = nodeService.getNodeById(detachedNode.getId());
//        Process transientProcess = processFactory.newProcess(persistentNode);
//        Process persistentProcess = processDao.create(transientProcess);
//        return persistentProcess;
    }

    @Override
    public void processAddNode(int processId, int nodeId) {
        Node persistentNode = nodeService.getNodeById(nodeId);
        Process persistenProcess = getProcessById(processId);
        if (persistentNode.getParentNode() == persistenProcess.getCurrentNode()) {
            persistenProcess.setCurrentNode(persistentNode);
        } else {
            throw new IllegalArgumentException("The offered node is not a child of the current node");
        }
    }

    @Override
    public void update(Process process) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteById(int processId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Node> getProcessPath(int processId) {
        Process persistentProcess = getProcessById(processId);
        Node persistentCurrentNode = persistentProcess.getCurrentNode();
        return nodeService.getNodeGenealogy(persistentCurrentNode);
    }

    @Override
    public Process createProcess(int nodeId) {
        Node persistentNode = nodeService.getNodeById(nodeId);
        Process transientProcess = processFactory.newProcess(persistentNode);
        Process persistentProcess = processDao.create(transientProcess);
        return persistentProcess;
    }

}
