package ua.legalist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Primary key in database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    
    /**
     * User who owns the process
     */
    @ManyToOne //(optional = false) TODO: this must me uncommented in production
    private User user;
    
    /**
     * Nodes constituting the process path
     */
    @ManyToMany (fetch=FetchType.EAGER)
    private List<Node> nodes;
    
//    @ManyToOne(optional = false)
//    private Node node;
    
    /**
     * Fields specific to this process
     */
    @JsonIgnore
    @OneToMany(mappedBy = "process")
    private Collection<Field> fieldCollection;
    
    public Process() {
    }

    public Process(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Node getNode() {
//        return node;
//    }
//
//    public void setNode(Node node) {
//        this.node = node;
//    }

    public Collection<Field> getFieldCollection() {
        return fieldCollection;
    }

    public void setFieldCollection(Collection<Field> fieldCollection) {
        this.fieldCollection = fieldCollection;
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
        if (!(object instanceof Process)) {
            return false;
        }
        Process other = (Process) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.legalist.model.Process[ id=" + id + " ]";
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }
    
}
