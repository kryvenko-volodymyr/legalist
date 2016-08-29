package ua.legalist.persistence;

import java.io.Serializable;
import java.util.Collection;

public interface GenericDao<T> {

    T create(T newInstance);

    T read(Serializable id);

    T update(T transientObject);

    void delete(T persistentObject);

    Collection<T> getAll();
}
