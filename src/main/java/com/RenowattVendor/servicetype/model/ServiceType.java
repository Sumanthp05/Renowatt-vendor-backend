package com.RenowattVendor.servicetype.model;

import com.RenowattVendor.project.model.Project;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "service")
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer serviceId;
    @Column(name = "name")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "project_has_service",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private ArrayList<Project> projects;

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }
}
