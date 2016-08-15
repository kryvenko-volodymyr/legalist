package ua.legalist.persistence;

import org.springframework.stereotype.Repository;
import ua.legalist.model.Process;

@Repository
public class ProcessDaoHibernateImpl extends GenericDaoHibernateImpl<Process> implements ProcessDao {

}
