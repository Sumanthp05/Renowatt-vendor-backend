package com.RenowattVendor.vendor.Services;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.User.Repository.UserRepository;
import com.RenowattVendor.User.dtos.UserDto;
import com.RenowattVendor.login.dtos.LoginDtos;
import com.RenowattVendor.login.dtos.LoginRequestDtos;
import com.RenowattVendor.login.dtos.LoginResponceDtos;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.login.repository.LoginRepository;
import com.RenowattVendor.vendor.Repository.VendorAuthenticationRepository;
import com.RenowattVendor.vendor.dtos.VendorResponceDto;
import com.RenowattVendor.vendor.dtos.VendorSignupDto;
import com.RenowattVendor.vendor.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorAuthenticationService {
    @Autowired
    VendorAuthenticationRepository vendorAuthenticationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public VendorResponceDto SignUp(VendorSignupDto vendorSignupDto){
        //save to vendor table
        Vendor v = vendorAuthenticationRepository.SignUp(vendorSignupDto);

        //save to user table
        UserDto userDto = new UserDto();
        userDto.setUserFirstName(vendorSignupDto.getVendorFirstName());
        userDto.setUserLastName(vendorSignupDto.getVendorLastName());
        userDto.setVendorId(v.getVendorId());
        userDto.setVendor(v);
        User u = userRepository.CreateUser(userDto);
        List<User> userList = new ArrayList<>();
        userList.add(u);
        v.setUsers(userList);

        //save to login table
        String rawPassword = vendorSignupDto.getPassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        LoginDtos loginDtos = new LoginDtos();
        loginDtos.setPassword(hashedPassword);
        loginDtos.setUserId(u.getUserId());
        Login l = loginRepository.createLogin(loginDtos);

        VendorResponceDto responceDto = new VendorResponceDto();
        responceDto.setVendor(v);
        responceDto.setStatus("Success");

        return responceDto;

    }

    public LoginResponceDtos SignIn(LoginRequestDtos loginRequestDtos){
        String email=loginRequestDtos.getEmailId();
        String password = loginRequestDtos.getPassword();

        //vendor object creation by verifying email
        Vendor vend = vendorAuthenticationRepository.SignIn(email);
        if (vend == null) {
            throw new RuntimeException("Invalid email: Vendor not found");
        }

        //user object
        User user = userRepository.ReturnUser(vend.getVendorId());
        if (user == null) {
            throw new RuntimeException("User not found for vendor ID: " + vend.getVendorId());
        }

        //login object
        Login login = loginRepository.EnterLogin(user.getUserId());
        if (login == null) {
            throw new RuntimeException("Login not found for user ID: " + user.getUserId());
        }

        boolean passwordMatch = passwordEncoder.matches(password, login.getPassword());
        if (!passwordMatch) throw new RuntimeException("Incorrect password");

        user.setVendor(vend);

        //Prepare response
        LoginResponceDtos loginResponceDtos = new LoginResponceDtos();
        loginResponceDtos.setStatus("success");
        loginResponceDtos.setUsers(user);
        return loginResponceDtos;



    }

}