package ua.legalist.persistence;

import org.springframework.stereotype.Repository;
import ua.legalist.model.Template;

@Repository
public class TemplateDaoHibernateImpl extends GenericDaoHibernateImpl<Template> implements TemplateDao {

}
