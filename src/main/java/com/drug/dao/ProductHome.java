package com.drug.dao;
// Generated Jul 29, 2016 11:56:47 AM by Hibernate Tools 5.1.0.Beta1

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.drug.infra.HibernateUtil;
import com.drug.model.Product;

/**
 * Home object for domain model class Product.
 * 
 * @see com.drug.model.ProductComp
 * @author Hibernate Tools
 */
public class ProductHome {

  private static final Logger log = Logger.getLogger(ProductHome.class);

  private final SessionFactory sessionFactory;

  private boolean serverContext = false;

  public ProductHome() {
    this.sessionFactory = getSessionFactory();
    serverContext = true;

  }

  public ProductHome(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;

  }

  protected SessionFactory getSessionFactory() {
    try {
      // return (SessionFactory) new InitialContext().lookup("drugfactory");
      return HibernateUtil.getSessionFactory();
    } catch (Exception e) {
      log.error("Could not get SessionFactory ", e);
      throw new IllegalStateException("Could not get SessionFactory");
    }
  }

  public List findByDrugName(String drugName) {
    log.debug("finding Product instance by drug name");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      Criteria criteria = session.createCriteria(com.drug.model.Product.class);
      Criterion criterion = Restrictions.like("proprietaryname", drugName);
      criteria.add(criterion);
      List results = criteria.list();

      log.debug("find by drug name successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by drug name failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }

  public List findByDrugName(String drugName, int pageNumber, int pageSize) {
    log.debug("finding Product instance by drug name");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      Criteria criteria = session.createCriteria(com.drug.model.Product.class);
      Criterion criterion = Restrictions.like("proprietaryname", drugName);
      criteria.add(criterion);
      criteria.setMaxResults(pageSize);
      criteria.setFirstResult((pageNumber - 1) * pageSize);

      List results = criteria.list();

      log.info("find by drug name successful, result size: " + results.size());
      return results;
    } catch (RuntimeException re) {
      log.error("find by drug name failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }

  public Long countByDrugName(String drugName, int pageSize) {
    log.debug("counting Product instances by drug name");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      Criteria criteria = session.createCriteria(com.drug.model.Product.class);
      Criterion criterion = Restrictions.like("proprietaryname", drugName);
      criteria.add(criterion);
      criteria.setProjection(Projections.rowCount());

      Long count = (Long) criteria.uniqueResult();
      Long pageCount = ((Double) Math.ceil((double) count / (double) pageSize)).longValue();

      log.info("count by drug name successful, page size: " + pageCount);
      return pageCount;
    } catch (RuntimeException re) {
      log.error("count by drug name failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
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

  public Product findByNDC(String ndc) {
    log.debug("finding Product instance by NDC");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();
      Criteria criteria = session.createCriteria(com.drug.model.Product.class);
      Criterion criterion = Restrictions.like("productndc", ndc);
      criteria.add(criterion);
      List results = criteria.list();
      log.debug("find by ndc successful, result size: " + results.size());
      if (results.iterator().hasNext())
        return (Product) results.iterator().next();
      else
        return null;
    } catch (RuntimeException re) {
      log.error("find by ndc failed", re);
      throw re;
    } finally {
      if (tx != null)
        tx.commit();
      if (!serverContext)
        session.close();
    }
  }

  public List findByExample(Product instance) {
    log.debug("finding Product instance by example");
    Session session = null;
    Transaction tx = null;
    try {
      session = getSession();
      tx = session.beginTransaction();

      List results = session.createCriteria("com.drug.model.Product").add(Example.create(instance)).list();
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

  public static void main(String[] args) {
    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
    List<String> arguments = runtimeMxBean.getInputArguments();
    for (String arg : arguments)
      System.out.println("x=" + arg);
    String x = System.getProperty("log4j.configuration");
    System.out.println("log=" + x);
    log.info(x);
    log.debug(x);

  }
}
