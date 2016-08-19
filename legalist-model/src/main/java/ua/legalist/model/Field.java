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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Kryvenko
 */
@Entity
@Table(name = "field")
@NamedQueries({
    @NamedQuery(name = "Field.findAll", query = "SELECT f FROM Field f")})
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "details")
    private String details;
    @Column(name = "group_id")
    private Integer groupId;
    @JoinTable(name = "node-field", joinColumns = {
        @JoinColumn(name = "field", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "node", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Node> nodeCollection;
    @JoinColumn(name = "process", referencedColumnName = "id")
    @ManyToOne
    private Process process;

    public Field() {
    }

    public Field(Integer id) {
        this.id = id;
    }

    public Field(Integer id, String title, String details) {
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Collection<Node> getNodeCollection() {
        return nodeCollection;
    }

    public void setNodeCollection(Collection<Node> nodeCollection) {
        this.nodeCollection = nodeCollection;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
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
        if (!(object instanceof Field)) {
            return false;
        }
        Field other = (Field) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.legalist.model.Field[ id=" + id + " ]";
    }
    
}
