package com.RenowattVendor.project.dtos;

public class CreateProjectDto {
    private Integer projectId;
    private String title;
    private String plantCapacity;
    private Integer estimatedCost;
    private String duration;
    private String status;
    private String invertorBrand;
    private String panelBrand;
    private String subsidyAmount;
    private Integer invertorId;
    private Integer authId;
    private String plantHealthStatus;
    private Integer vendor_id;
    private String location;

    public Integer getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Integer vendor_id) {
        this.vendor_id = vendor_id;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
