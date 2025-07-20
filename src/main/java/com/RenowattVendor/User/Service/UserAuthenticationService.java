package com.RenowattVendor.User.Service;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.User.Repository.UserRepositoryIntf;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.login.repository.LoginRepositoryIntf;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthenticationService implements UserDetailsService {


    private final UserRepositoryIntf userRepository;
    private final LoginRepositoryIntf loginRepository;

    public UserAuthenticationService(UserRepositoryIntf userRepository,
                                     LoginRepositoryIntf loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the User by email
        User user = userRepository.findByemailId(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        //  Find the Login using user ID
        Login login = loginRepository.findByUser_UserId(user.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException("Login not found for user ID: " + user.getUserId()));

        //  Return Spring Security UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(email)
                .password(login.getPassword()) // Should already be encoded!
                .authorities(Collections.emptyList()) // Add roles if needed
                .build();
    }

}
