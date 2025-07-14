package com.RenowattVendor.project.dtos;

import com.RenowattVendor.project.model.Project;

public class ReturnProjectDto {
    private Project project;
    private String status;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
