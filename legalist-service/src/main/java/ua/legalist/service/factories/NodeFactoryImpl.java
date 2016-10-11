package ua.legalist.service.factories;

import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Field;
import ua.legalist.model.Node;
import ua.legalist.model.Process;
import ua.legalist.model.Template;
import ua.legalist.process.factories.NodeFactory;

@Service("nodeFactory")
@Transactional
public class NodeFactoryImpl implements NodeFactory {

    @Override
    public Node newNode(String title, String details) {
        Node node = new Node();
        
        node.setTitle(title);
        node.setDetails(details);
        
        node.setChildNodes(new HashSet<Node>());
        node.setReferringNodes(new HashSet<Node>());
        node.setProcesses(new HashSet<Process>());
        node.setFields(new HashSet<Field>());
        node.setTemplates(new HashSet<Template>());
        
        return node;
    }
}
