package ua.legalist.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Node;
import ua.legalist.persistence.NodeDao;

@Transactional
@Service("nodeService")
public class NodeServiceImpl implements NodeService {

    @Autowired
    NodeDao nodeDao;

    @Override
    public Collection<Node> getAll() {
        return nodeDao.getAll();
    }

    @Override
    public Map<Node, Map> getFullHierarchy() {
        Collection<Node> allNodes = nodeDao.getAll();
        Node rootFullNode = getRootFullNode(allNodes);
        return buildHierarchy(allNodes, rootFullNode);
    }

    @Override
    public Map<Node, Map> getSimpleHierarchy() {
        Collection<Node> allNodes = nodeDao.getAll();
        Node rootSimpleNode = getRootSimpleNode(allNodes);
        return buildHierarchy(allNodes, rootSimpleNode);
    }

    private Map<Node, Map> buildHierarchy(Collection<Node> allNodes, Node rootNode) {
        return getLowerHierarchLevel(rootNode);
    }

    private Node getRootFullNode(Collection<Node> allNodes) {
        Node result = null;
        int count = 0;

        for (Node node : allNodes) {
            if (node.getParentNode() == null
                    && node.getReferredNode() == null) {
                if (++count > 1) {
                    throw new RuntimeException(
                            "More that one root full node in the full nodes hierarchy"
                    );
                }
                result = node;
            }
        }
        if (result == null) throw new RuntimeException ("No root full node found");
        return result;
    }

    private Node getRootSimpleNode(Collection<Node> allNodes) {
        Node result = null;
        int count = 0;

        for (Node node : allNodes) {         
            if (node.getParentNode() == null
                    && node.getReferredNode() != null) {
                if (++count > 1) {
                    throw new RuntimeException(
                            "More that one root simple node in the simple nodes hierarchy"
                    );
                }
                result = node;
            }
        }
        if (result == null) throw new RuntimeException ("No root simple node found");
        return result;
    }

    private Map<Node, Map> getLowerHierarchLevel(Node node) {
        Map<Node, Map> result = new HashMap<>();

        if (node.getNodeCollection() != null) {
            for (Node childNode : node.getNodeCollection()) {
                result.put(childNode, this.getLowerHierarchLevel(childNode));
            }
        }
        return result;

    }

}
