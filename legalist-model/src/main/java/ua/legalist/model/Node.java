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
    
    @JoinTable(name = "node-template", joinColumns = {
        @JoinColumn(name = "node", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "template", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Template> templateCollection;
    
    @ManyToMany(mappedBy = "nodeCollection")
    private Collection<Field> fieldCollection;
    
    @OneToMany(mappedBy = "parentNode")
    private Collection<Node> nodeCollection;
    
    //@JoinColumn(name = "parent_node", referencedColumnName = "id")
    @ManyToOne
    private Node parentNode;
    
    @OneToMany(mappedBy = "referredNode")
    private Collection<Node> nodeCollection1;
    
    //@JoinColumn(name = "referred_node", referencedColumnName = "id")
    @ManyToOne
    private Node referredNode;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "node")
    private Collection<Process> processCollection;

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

    public Collection<Template> getTemplateCollection() {
        return templateCollection;
    }

    public void setTemplateCollection(Collection<Template> templateCollection) {
        this.templateCollection = templateCollection;
    }

    public Collection<Field> getFieldCollection() {
        return fieldCollection;
    }

    public void setFieldCollection(Collection<Field> fieldCollection) {
        this.fieldCollection = fieldCollection;
    }

    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Collection<Node> getNodeCollection1() {
        return nodeCollection1;
    }

    public void setNodeCollection1(Collection<Node> nodeCollection1) {
        this.nodeCollection1 = nodeCollection1;
    }

    public Node getReferredNode() {
        return referredNode;
    }

    public void setReferredNode(Node referredNode) {
        this.referredNode = referredNode;
    }

    public Collection<Process> getProcessCollection() {
        return processCollection;
    }

    public void setProcessCollection(Collection<Process> processCollection) {
        this.processCollection = processCollection;
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
