package com.drug.infra;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateListener implements ServletContextListener {
  private ServletContext context = null;

  public void contextInitialized(ServletContextEvent event) {
    context = event.getServletContext();

    Enumeration<String> params = context.getInitParameterNames();

    while (params.hasMoreElements()) {
      String param = (String) params.nextElement();
      String value = context.getInitParameter(param);
      if (param.startsWith("env_")) {
        System.setProperty(param, value);
      }
    }
    HibernateUtil.getSessionFactory(); // Just call the static initializer of
                                       // that class
  }

  public void contextDestroyed(ServletContextEvent event) {
    HibernateUtil.getSessionFactory().close(); // Free all resources
  }
}