package com.RenowattVendor.project.controllers;

import com.RenowattVendor.project.dtos.CreateProjectDto;
import com.RenowattVendor.project.dtos.ReturnProjectDto;
import com.RenowattVendor.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/project")
    public @ResponseBody ReturnProjectDto CreateProject (@RequestBody CreateProjectDto createProjectDto){
        return projectService.CreateProject(createProjectDto);
    }
}
