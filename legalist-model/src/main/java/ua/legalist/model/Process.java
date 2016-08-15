/*
 */
package ua.legalist.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kryvenko
 */
@Entity
@Table(name = "process")
@NamedQueries({
    @NamedQuery(name = "Process.findAll", query = "SELECT p FROM Process p")})
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "node_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Node nodeId;
    @OneToMany(mappedBy = "processId")
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Node getNodeId() {
        return nodeId;
    }

    public void setNodeId(Node nodeId) {
        this.nodeId = nodeId;
    }

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
        return "ua.com.legalist.model.Process[ id=" + id + " ]";
    }
    
}
