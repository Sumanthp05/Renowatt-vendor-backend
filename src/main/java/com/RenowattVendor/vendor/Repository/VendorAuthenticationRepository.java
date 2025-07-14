package com.RenowattVendor.vendor.Repository;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.servicetype.model.ServiceType;
import com.RenowattVendor.vendor.dtos.VendorSignupDto;
import com.RenowattVendor.vendor.model.Vendor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class VendorAuthenticationRepository {

    public Vendor SignUp(VendorSignupDto vendorSignupDto) {
        Vendor vendor = new Vendor();
        vendor.setVendorFirstName(vendorSignupDto.getVendorFirstName());
        vendor.setVendorLastName(vendorSignupDto.getVendorLastName());
        vendor.setEmailId(vendorSignupDto.getEmailId());
        vendor.setPassword(vendorSignupDto.getPassword());
        vendor.setCompanyName(vendorSignupDto.getCompanyName());
        vendor.setCIN(vendorSignupDto.getCIN());
        vendor.setDateOfIncorporation(vendorSignupDto.getDateOfIncorporation());
        vendor.setRegistrationNumber(vendorSignupDto.getRegistrationNumber());
        System.out.println("line 1");
      //  return vendor;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(com.RenowattVendor.project.model.Project.class);
            configuration.addAnnotatedClass(Vendor.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(com.RenowattVendor.servicetype.model.ServiceType.class);
            configuration.addAnnotatedClass(Login.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties());

            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("line 2");
            session.save(vendor);
            System.out.println("line 3");
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vendor;
    }

    public Vendor SignIn(String email){
        Vendor vendor = null;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(com.RenowattVendor.project.model.Project.class);
            configuration.addAnnotatedClass(Vendor.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(ServiceType.class);
            configuration.addAnnotatedClass(Login.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            vendor = (Vendor) session.createQuery("FROM Vendor WHERE emailId = :email")
                    .setParameter("email", email)
                    .uniqueResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return vendor;


    }

}
