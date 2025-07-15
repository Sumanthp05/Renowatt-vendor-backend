package com.RenowattVendor.project.service;

import com.RenowattVendor.errorHandler.ErrorMessage;
import com.RenowattVendor.project.dtos.CreateProjectDto;
import com.RenowattVendor.project.dtos.Locationdto;
import com.RenowattVendor.project.dtos.ReturnProjectDto;
import com.RenowattVendor.project.model.Project;
import com.RenowattVendor.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public ReturnProjectDto CreateProject(CreateProjectDto createProjectDto){


        Project project = projectRepository.CreateProject(createProjectDto);

        ReturnProjectDto returnProjectDto = new ReturnProjectDto();
        returnProjectDto.setProject(project);
        returnProjectDto.setStatus("success");
        return returnProjectDto;
    }

    public List<Project> ProjectInfo(Locationdto locationdto){
        String location = locationdto.getLocation();
        List<Project> project = projectRepository.ProjectInfo(location);
        if (project == null || project.isEmpty()) {
            throw new ErrorMessage("Invalid location: Project not found");
        }

        return project;
    }
}
