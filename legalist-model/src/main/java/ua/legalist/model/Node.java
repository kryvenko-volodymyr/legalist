package ua.legalist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
    private Collection<Template> templates;

    @ManyToMany(mappedBy = "nodes", fetch = FetchType.EAGER)
    private Collection<Field> fields;

    @JsonIgnore
    @OneToMany(mappedBy = "parentNode")
    private Collection<Node> childNodes;

    //@JoinColumn(name = "parent_node", referencedColumnName = "id")
    @JsonIgnore
    @ManyToOne
    private Node parentNode;

    @JsonIgnore
    @OneToMany(mappedBy = "referredNode")
    private Collection<Node> referringNodes;

    @ManyToOne
    private Node referredNode;

    /**
     * Processes incorporating the node
     */
//    @JsonIgnore
//    @ManyToMany(mappedBy = "nodes")
//    private Collection<Process> processes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "currentNode")
    private Collection<Process> processes;

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

    public Collection<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(Collection<Template> templates) {
        this.templates = templates;
    }

    public Collection<Field> getFields() {
        return fields;
    }

    public void setFields(Collection<Field> fields) {
        this.fields = fields;
    }

    public Collection<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Collection<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Collection<Node> getReferringNodes() {
        return referringNodes;
    }

    public void setReferringNodes(Collection<Node> referringNodes) {
        this.referringNodes = referringNodes;
    }

    public Node getReferredNode() {
        return referredNode;
    }

    public void setReferredNode(Node referredNode) {
        this.referredNode = referredNode;
    }

    public Collection<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Collection<Process> processes) {
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
