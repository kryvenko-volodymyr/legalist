package ua.legalist.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    //@Column(name = "title")
    private String title;

    @Basic(optional = false)
    //@Column(name = "details")
    private String details;

//    @JoinTable(name = "node-template",
//            joinColumns = {
//                @JoinColumn(name = "node", referencedColumnName = "id")},
//            inverseJoinColumns = {
//                @JoinColumn(name = "template", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Template> templates;

    @ManyToMany(mappedBy = "nodes")
    private Collection<Field> fields;

    @OneToMany(mappedBy = "parentNode")
    private Collection<Node> childNodes;

    //@JoinColumn(name = "parent_node", referencedColumnName = "id")
    @ManyToOne
    private Node parentNode;

    @OneToMany(mappedBy = "referredNode")
    private Collection<Node> referringNodes;

    //@JoinColumn(name = "referred_node", referencedColumnName = "id")
    @ManyToOne
    private Node referredNode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "node")
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
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Node)) {
            return false;
        }
        Node other = (Node) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.legalist.model.Node[ id=" + id + " ]";
    }

}
