package com.RenowattVendor.login.repository;

import com.RenowattVendor.login.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepositoryIntf extends JpaRepository<Login, Integer> {
    Optional<Login> findByUser_UserId(Integer user_id);

}
