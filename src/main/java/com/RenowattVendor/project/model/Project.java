package com.RenowattVendor.project.model;

import com.RenowattVendor.servicetype.model.ServiceType;
import com.RenowattVendor.vendor.model.Vendor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer projectId;
    @Column(name = "title")
    private String title;
    @Column(name = "plant_capacity")
    private String plantCapacity;
    @Column(name = "estimated_cost")
    private Integer estimatedCost;
    @Column(name = "duration")
    private String duration;
    @Column(name = "status")
    private String status;
    @Column(name = "invertor_brand")
    private String invertorBrand;
    @Column(name = "panel_brand")
    private String panelBrand;
    @Column(name = "subsidy_amount")
    private String subsidyAmount;
    @Column(name = "invertor_id")
    private Integer invertorId;
    @Column(name = "auth_id")
    private Integer authId;
    @Column(name = "plant_health_status")
    private String plantHealthStatus;
    @Column(name = "location")
    private String location;
    @ManyToMany(mappedBy = "projects")
    private ArrayList<ServiceType> serviceTypes  ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", referencedColumnName = "vendor_id", nullable = false)
    @JsonIgnore
    private Vendor vendor;

    public ArrayList<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(ArrayList<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlantCapacity() {
        return plantCapacity;
    }

    public void setPlantCapacity(String plantCapacity) {
        this.plantCapacity = plantCapacity;
    }

    public Integer getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(Integer estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvertorBrand() {
        return invertorBrand;
    }

    public void setInvertorBrand(String invertorBrand) {
        this.invertorBrand = invertorBrand;
    }

    public String getPanelBrand() {
        return panelBrand;
    }

    public void setPanelBrand(String panelBrand) {
        this.panelBrand = panelBrand;
    }

    public String getSubsidyAmount() {
        return subsidyAmount;
    }

    public void setSubsidyAmount(String subsidyAmount) {
        this.subsidyAmount = subsidyAmount;
    }

    public Integer getInvertorId() {
        return invertorId;
    }

    public void setInvertorId(Integer invertorId) {
        this.invertorId = invertorId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public String getPlantHealthStatus() {
        return plantHealthStatus;
    }

    public void setPlantHealthStatus(String plantHealthStatus) {
        this.plantHealthStatus = plantHealthStatus;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
