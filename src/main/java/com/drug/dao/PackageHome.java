package com.drug.dao;
// Generated Jul 29, 2016 11:56:47 AM by Hibernate Tools 5.1.0.Beta1

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.drug.infra.HibernateUtil;

/**
 * Home object for domain model class Package.
 * 
 * @see com.drug.model.PackageComp
 * @author Hibernate Tools
 */
public class PackageHome {

  private static final Logger log = Logger.getLogger(ProductHome.class);

  private final SessionFactory sessionFactory;

  private boolean serverContext = false;

  public PackageHome() {
    this.sessionFactory = getSessionFactory();
    serverContext = true;

  }

  public PackageHome(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;

  }

  protected SessionFactory getSessionFactory() {
    try {
      // return (SessionFactory) new InitialContext().lookup("SessionFactory");
      return HibernateUtil.getSessionFactory();
    } catch (Exception e) {
      log.error("Could not locate SessionFactory in JNDI", e);
      throw new IllegalStateException("Could not locate SessionFactory in JNDI");
    }
  }

  private Session getSession() {
    Session session;
    if (this.serverContext) {
      session = sessionFactory.getCurrentSession();
    } else
      session = sessionFactory.openSession();
    return session;
  }

  public List findByExample(Package instance) {
    log.debug("finding Package instance by example");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();
      List results = session.createCriteria("com.drug.model.Package").add(Example.create(instance)).list();
      log.debug("find by example successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by example failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }

  public List findByPackageDescription(String packageDescription) {
    log.debug("finding Product instance by package description");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      Criteria criteria = session.createCriteria(com.drug.model.Package.class);
      Criterion criterion = Restrictions.like("packagedescription", packageDescription);
      criteria.add(criterion);
      List results = criteria.list();

      log.debug("find by package description successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by package description failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }

  public List findByProductNdc(String ndc) {
    log.debug("finding Product instance by product ndc");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      Criteria criteria = session.createCriteria(com.drug.model.Package.class);
      Criterion criterion = Restrictions.like("productndc", ndc);
      criteria.add(criterion);
      List results = criteria.list();

      log.debug("find by product ndc successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by product ndc failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }
}
