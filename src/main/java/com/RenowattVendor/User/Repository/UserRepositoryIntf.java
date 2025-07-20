package com.RenowattVendor.User.Repository;

import com.RenowattVendor.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryIntf extends JpaRepository<User, Integer> {
    Optional<User> findByemailId(String emailId);

}
