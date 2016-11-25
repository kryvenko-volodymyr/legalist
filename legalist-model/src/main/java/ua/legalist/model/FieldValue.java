package ua.legalist.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long id;

    /**
     * If there are several similar fields for the same process (e.g. several
     * children), such fields are divided into bunches.
     *
     */
    private Integer bunch;

    private String value;
    
    @ManyToOne
    private Field field;

    @ManyToOne
    private Process process;
}
