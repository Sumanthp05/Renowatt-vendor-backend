package com.RenowattVendor.login.repository;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.login.dtos.LoginDtos;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.vendor.model.Vendor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    public Login createLogin(LoginDtos profileDtos){
        Login profile = new Login();
        profile.setPassword(profileDtos.getPassword());
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Login.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Vendor.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, profileDtos.getUserId());
            profile.setUser(user);
            session.save(profile);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }
    public Login EnterLogin(Integer user_id){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Login.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Vendor.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.openSession();
        Login login = (Login) session.createQuery("FROM Login WHERE user.userId = :userId", Login.class)
                .setParameter("userId", user_id)
                .uniqueResult();


        String password = login.getPassword();
        return login;
    }

}
