package ua.legalist.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class GenericDaoHibernateImpl<T> implements GenericDao<T> {

    private SessionFactory sessionFactory;
    private Class<T> type;

    public GenericDaoHibernateImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
    
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Serializable create(Object newInstance) {
        return getSession().save((T)newInstance);
    }

    @Override
    public T read(Serializable id) {
        return (T)getSession().get(type, id);
    }

    @Override
    public void update(Object transientObject) {
        getSession().update(transientObject);
    }

    @Override
    public void delete(Object persistentObject) {
        getSession().delete(persistentObject);
    }
    
    @Override
    public Collection<T> getAll (){
        return getSession().createCriteria(type).list();
    }
    
    private Session getSession () {
        return sessionFactory.getCurrentSession();
    }

}
