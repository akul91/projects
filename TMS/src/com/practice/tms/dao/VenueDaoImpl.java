package com.practice.tms.dao;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.practice.tms.domain.Location;
import com.practice.tms.exception.TMSException;
/**
 *
 * @author jyothitamma
 *
 */
public class VenueDaoImpl implements IVenueDao {
/**.
 * declaration of hibernate template
 */
private HibernateTemplate hibernateTemplate;
/**.
 * setter injection of session factory
 * @param sessionFactory { @link SessionFactory}
 */
public final void setSessionFactory(final SessionFactory sessionFactory) {
this.hibernateTemplate = new HibernateTemplate(sessionFactory);
}
/**.
 *method to insert the location
 * @param venue {@link Location}
 * @throws TMSException {@link TMSException}
 */
@Override
public final void saveVenue(final Location venue) throws TMSException {
try {
hibernateTemplate.save(venue);
} catch (Exception e) {
throw new TMSException(e.toString());
}
}
}