package ua.legalist.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.legalist.model.Node;
import ua.legalist.persistence.NodeDao;
import ua.legalist.process.factories.NodeFactory;

@Service("nodeService")
@Transactional
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private NodeFactory nodeFactory;

    @Override
    public Collection<Node> getAllNodes() {
        return nodeDao.getAll();
    }

    @Override
    public NodeDTO getFullHierarchy() { //TODO needs some cashing
        Node rootFullNode = getRootFullNode();
        NodeDTO hierarchy = new NodeDTO(rootFullNode);
        return hierarchy;
    }

    private Node getRootFullNode() {
        Node result = null;

        int rootFullNodesNum = 0;

        for (Node node : getAllNodes()) {
            if (node.getParentNode() == null && node.getReferredNode() == null) {
                if (++rootFullNodesNum > 1) {
                    throw new RuntimeException("More that one root full node in the full nodes hierarchy");
                }
                result = node;
            }
        }
        if (result == null) {
            throw new RuntimeException("No root full node found");
        }
        return result;
    }

    @Override
    public Node getNodeById(int nodeId) {
        return nodeDao.read(nodeId);
    }

    @Override
    public Node create(String title, String details) {
        Node node = nodeFactory.newNode(title, details);
        return nodeDao.create(node);
    }

    @Override
    public Node update(Node node) {
        return nodeDao.update(node);
    }

    @Override
    public Node getUltimategetReferredNode(Node node) {
        while (node.getReferredNode() != null) {
            node = node.getReferredNode();
        }
        return node;
    }

    @Override
    public List<Node> getNodeGenealogy(Node node) {
        List<Node> nodes = new LinkedList<>();

        while (node.getParentNode() != null) {
            nodes.add(node);
            node = node.getParentNode();
        }
        return nodes;
    }

    @Override
    public Set<Node> getNodeChildNodes(int nodeId) {
        Node persistentNode = getNodeById(nodeId);
        Set<Node> childNodesDTO = new HashSet();
        for (Node node : persistentNode.getChildNodes()) {
            childNodesDTO.add(node);
        }
        return childNodesDTO;
    }
}
