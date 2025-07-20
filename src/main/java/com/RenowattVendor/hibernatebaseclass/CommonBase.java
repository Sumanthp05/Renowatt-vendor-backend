package com.RenowattVendor.hibernatebaseclass;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CommonBase {
    protected SessionFactory buildSessionFactory(Class<?>... annotatedClasses) {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            for (Class<?> annotatedClass : annotatedClasses) {
                configuration.addAnnotatedClass(annotatedClass);
            }

            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            return configuration.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            System.err.println("Failed to create SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
