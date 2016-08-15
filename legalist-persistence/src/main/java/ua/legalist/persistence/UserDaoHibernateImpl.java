package ua.legalist.persistence;

import org.springframework.stereotype.Repository;
import ua.legalist.model.User;

@Repository
public class UserDaoHibernateImpl extends GenericDaoHibernateImpl<User> implements UserDao {

}
