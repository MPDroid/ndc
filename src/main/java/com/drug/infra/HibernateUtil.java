package com.drug.infra;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Configuration cfg = new Configuration();
      cfg.configure();
      resolvePropertyValue(cfg, "hibernate.connection.url");
      resolvePropertyValue(cfg, "hibernate.connection.username");
      resolvePropertyValue(cfg, "hibernate.connection.password");
      return cfg.buildSessionFactory();
    } catch (Throwable ex) {
      // Make sure you log the exception, as it might be swallowed
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  private static void resolvePropertyValue(Configuration cfg, String property) {
    cfg.setProperty(property, System.getProperty(cfg.getProperty(property)));
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

}