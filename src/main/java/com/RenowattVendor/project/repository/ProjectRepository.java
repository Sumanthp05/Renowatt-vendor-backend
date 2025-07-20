package com.RenowattVendor.project.repository;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.project.dtos.CreateProjectDto;
import com.RenowattVendor.project.dtos.UpdateStatusDto;
import com.RenowattVendor.project.model.Project;
import com.RenowattVendor.servicetype.model.ServiceType;
import com.RenowattVendor.vendor.model.Vendor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {
    public Project CreateProject(CreateProjectDto createProjectDto){
        Project project = new Project();
        project.setAuthId(createProjectDto.getAuthId());
        project.setDuration(createProjectDto.getDuration());
        project.setEstimatedCost(createProjectDto.getEstimatedCost());
        project.setStatus(createProjectDto.getStatus());
        project.setInvertorBrand(createProjectDto.getInvertorBrand());
        project.setPanelBrand(project.getInvertorBrand());
        project.setPlantCapacity(createProjectDto.getPlantCapacity());
        project.setPlantHealthStatus(createProjectDto.getPlantHealthStatus());
        project.setInvertorId(createProjectDto.getInvertorId());
        project.setSubsidyAmount(createProjectDto.getSubsidyAmount());
        project.setTitle(createProjectDto.getTitle());
        project.setLocation(createProjectDto.getLocation());
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Project.class);
            configuration.addAnnotatedClass(Vendor.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(ServiceType.class);
            configuration.addAnnotatedClass(Login.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Integer vendorId = createProjectDto.getVendor_id();
            Vendor vendor = session.get(Vendor.class, vendorId);
            project.setVendor(vendor);
            System.out.println("line 2");
            session.save(project);
            System.out.println("line 3");
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return project;
    }

    public List<Project> ProjectInfo(String location){
        List<Project> project = new ArrayList<>();
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Project.class);
            configuration.addAnnotatedClass(Vendor.class);
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(ServiceType.class);
            configuration.addAnnotatedClass(Login.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
            Session session = sessionFactory.openSession();
            project = session.createQuery("FROM Project WHERE location = :location", Project.class)
                    .setParameter("location", location)
                    .list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return project;
    }

    public void UpdateStatus(UpdateStatusDto updateStatusDto){

    }
}
