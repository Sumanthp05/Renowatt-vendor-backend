package com.RenowattVendor.project.controllers;

import com.RenowattVendor.project.dtos.CreateProjectDto;
import com.RenowattVendor.project.dtos.Locationdto;
import com.RenowattVendor.project.dtos.ReturnProjectDto;
import com.RenowattVendor.project.model.Project;
import com.RenowattVendor.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/createProject")
    public @ResponseBody ReturnProjectDto CreateProject (@RequestBody CreateProjectDto createProjectDto){
        return projectService.CreateProject(createProjectDto);
    }

    @PostMapping ("/location")
    public @ResponseBody List<Project> ProjectInfo(@RequestBody Locationdto locationdto){
        return projectService.ProjectInfo(locationdto);
    }
}
