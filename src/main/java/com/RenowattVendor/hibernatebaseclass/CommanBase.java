package com.RenowattVendor.hibernatebaseclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public abstract class CommanBase {
    public void hibernatebaseclass(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");


            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("line 2");
            //session.save();
            System.out.println("line 3");
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
