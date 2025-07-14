package com.RenowattVendor.User.Repository;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.User.dtos.UserDto;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.project.model.Project;
import com.RenowattVendor.servicetype.model.ServiceType;
import com.RenowattVendor.vendor.model.Vendor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User CreateUser(UserDto userDto){
        User user = new User();
        user.setUserFirstName(userDto.getUserFirstName());
        user.setUserLastName(userDto.getUserLastName());
        user.setDesignation(userDto.getDesignation());
        user.setEmployeeId(userDto.getEmployeeId());
        //user.setVendor(userDto.getVendor());
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Vendor.class);
            configuration.addAnnotatedClass(Login.class);
            configuration.addAnnotatedClass(Project.class);
            configuration.addAnnotatedClass(ServiceType.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Vendor attachedVendor = session.get(Vendor.class, userDto.getVendor().getVendorId());
            user.setVendor(attachedVendor);
            session.save(user);
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public User ReturnUser(Integer vendor_id) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Vendor.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Login.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(ServiceType.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        Session session = sessionFactory.openSession();

        User user = (User) session.createQuery("FROM User WHERE vendor.vendorId = :vendorId")
                .setParameter("vendorId", vendor_id)
                .uniqueResult();


        return user;
    }
}
