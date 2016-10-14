package ua.legalist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = false)
    private String details;

    @JsonIgnore
    @ManyToMany
    private Set<Template> templates;

    @ManyToMany(mappedBy = "nodes", fetch = FetchType.EAGER)
    private Set<Field> fields;

    @JsonIgnore
    @OneToMany(mappedBy = "parentNode", fetch = FetchType.LAZY)
    private Set<Node> childNodes;

    //@JoinColumn(name = "parent_node", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Node parentNode;

    @JsonIgnore
    @OneToMany(mappedBy = "referredNode")
    private Set<Node> referringNodes;

    @ManyToOne
    private Node referredNode;

    /**
     * Processes incorporating the node
     */
//    @JsonIgnore
//    @ManyToMany(mappedBy = "nodes")
//    private Set<Process> processes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentNode")
    private Set<Process> processes;

    public Node() {
    }

    public Node(Integer id) {
        this.id = id;
    }

    public Node(Integer id, String title, String details) {
        this.id = id;
        this.title = title;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Set<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<Template> templates) {
        this.templates = templates;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Set<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Set<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Set<Node> getReferringNodes() {
        return referringNodes;
    }

    public void setReferringNodes(Set<Node> referringNodes) {
        this.referringNodes = referringNodes;
    }

    public Node getReferredNode() {
        return referredNode;
    }

    public void setReferredNode(Node referredNode) {
        this.referredNode = referredNode;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", title=" + title + ", templates=" + templates + ", fields=" + fields + '}';
    }
}
