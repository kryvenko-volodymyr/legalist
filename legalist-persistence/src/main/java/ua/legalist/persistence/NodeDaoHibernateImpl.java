package ua.legalist.persistence;

import org.springframework.stereotype.Repository;
import ua.legalist.model.Node;

@Repository
public class NodeDaoHibernateImpl extends GenericDaoHibernateImpl<Node> implements NodeDao {

}
