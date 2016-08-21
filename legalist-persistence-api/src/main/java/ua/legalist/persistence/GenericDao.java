/*
 */
package ua.legalist.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * http://www.ibm.com/developerworks/library/j-genericdao/
 */
public interface GenericDao<T> {

    Serializable create(T newInstance);

    T read(Serializable id);

    void update(T transientObject);

    void delete(T persistentObject);
    
    Collection<T> getAll ();
}
