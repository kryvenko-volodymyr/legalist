package ua.legalist.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoHibernateImpl<T> implements GenericDao<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> type;

    public GenericDaoHibernateImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(T newInstance) {
        entityManager.persist(newInstance);
        return newInstance;
    }

    @Override
    public T read(Serializable id) {
        return entityManager.find(type, id);
    }

    @Override
    public T update(T transientObject) {
        return entityManager.merge(transientObject);
    }

    @Override
    public void delete(Object persistentObject) {
        entityManager.remove((entityManager.merge(persistentObject)));
    }

    @Override
    public Collection<T> getAll() {
        CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
        criteriaQuery.select(criteriaQuery.from(type));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
