package ua.legalist.service;

import java.util.HashSet;
import java.util.Set;
import ua.legalist.model.Node;

/**
 * A Data-Transfer-Object used to transfer nodes hierarchy data to web-clients.
 */
public class NodeDTO {

    private int id;
    private String title;
    private String details;
    private Set<NodeDTO> childNodes; 

    public NodeDTO(Node node) {
        this.id = node.getId();
        this.title = node.getTitle();
        this.details = node.getDetails();
        
        childNodes = new HashSet<>();
        for (Node subNode : node.getChildNodes()) {
            this.childNodes.add(new NodeDTO(subNode));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<NodeDTO> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<NodeDTO> childNodes) {
        this.childNodes = childNodes;
    }

}
