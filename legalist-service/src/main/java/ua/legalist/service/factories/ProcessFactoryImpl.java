package ua.legalist.service.factories;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Field;
import ua.legalist.model.Node;
import ua.legalist.process.factories.ProcessFactory;
import ua.legalist.model.Process;
import ua.legalist.service.NodeService;

@Service("processFactory")
@Transactional
public class ProcessFactoryImpl implements ProcessFactory {

    @Autowired
    NodeService nodeService;
    
    public Process newProcess() {
        Process process = new Process();
        process.setFieldCollection(new HashSet<Field>());
        process.setNodes(new LinkedList<Node>());
        return process;
    }

    @Override
    public Process newProcess(Node node) {
        Process process = newProcess();
        node = nodeService.getUltimategetReferredNode(node);
        List<Node> nodes = nodeService.getNodeGenealogy(node);
        for (Node memberNode : nodes) {
            memberNode.getProcesses().add(process);
        }
        process.getNodes().addAll(nodes);
        return process;
    }

}
