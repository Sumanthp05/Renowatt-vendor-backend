package com.RenowattVendor.project.repository;

import com.RenowattVendor.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepositoryIntf extends JpaRepository<Project, Integer> {

}
