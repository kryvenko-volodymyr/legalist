package ua.legalist.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = false)
    private String details;

    /**
     * If there are several similar fields for the same process (e.g. several
     * children), such fields are divided into bunches.
     *
     */
    private Integer bunch;

    @JoinTable(name = "node-field", joinColumns = {
        @JoinColumn(name = "field", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "node", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Node> nodeCollection;

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

    public Integer getBunch() {
        return bunch;
    }

    public void setBunch(Integer bunch) {
        this.bunch = bunch;
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
