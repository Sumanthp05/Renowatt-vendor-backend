package com.RenowattVendor.vendor.Services;

import com.RenowattVendor.User.Model.User;
import com.RenowattVendor.User.Repository.UserRepository;
import com.RenowattVendor.User.Repository.UserRepositoryIntf;
import com.RenowattVendor.User.dtos.UserDto;
import com.RenowattVendor.errorHandler.ErrorMessage;
import com.RenowattVendor.login.dtos.LoginDtos;
import com.RenowattVendor.login.dtos.LoginRequestDtos;
import com.RenowattVendor.login.dtos.LoginResponceDtos;
import com.RenowattVendor.login.model.Login;
import com.RenowattVendor.login.repository.LoginRepository;
import com.RenowattVendor.utility.JwtUtil;
import com.RenowattVendor.vendor.Repository.VendorAuthenticationRepository;
import com.RenowattVendor.vendor.Repository.VendorRepositoryIntf;
import com.RenowattVendor.vendor.dtos.VendorResponceDto;
import com.RenowattVendor.vendor.dtos.VendorSignupDto;
import com.RenowattVendor.vendor.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendorAuthenticationService{
    @Autowired
    VendorAuthenticationRepository vendorAuthenticationRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositoryIntf userRepositoryImpl;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private VendorRepositoryIntf vendoRepositoryImpl;

    @Autowired
    JwtUtil jwtUtil;

    public VendorResponceDto SignUp(VendorSignupDto vendorSignupDto){
        //save to vendor table
        Vendor v = vendorAuthenticationRepository.SignUp(vendorSignupDto);

        //save to user table
        UserDto userDto = new UserDto();
        userDto.setUserEmailId(vendorSignupDto.getUserEmailId());
        userDto.setUserFirstName(vendorSignupDto.getUserFirstName());
        userDto.setUserLastName(vendorSignupDto.getUserLastName());
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
        Optional<User> userOpt = userRepositoryImpl.findByemailId(email);
        if (userOpt.get() == null) {
            throw new ErrorMessage("Invalid email: Vendor not found");
        }

        //user object
        User user = userOpt.get();//userRepository.ReturnUser(vend.getVendorId());
//        if (user == null) {
//            throw new ErrorMessage("User not found for vendor ID: " + vend.getVendorId());
//        }

        //login object
        Login login = loginRepository.EnterLogin(user.getUserId());
        if (login == null) {
            throw new ErrorMessage("Login not found for user ID: " + user.getUserId());
        }

        boolean passwordMatch = passwordEncoder.matches(password, login.getPassword());
        if (!passwordMatch) throw new ErrorMessage("Incorrect password");

//        user.setVendor(vend);
        String token = jwtUtil.generateToken(loginRequestDtos.getEmailId());
        //Prepare response
        LoginResponceDtos loginResponceDtos = new LoginResponceDtos();
        loginResponceDtos.setStatus("success");
        loginResponceDtos.setUsers(user);
        loginResponceDtos.setAuthToken(token);
        return loginResponceDtos;

    }

    public void SignUpwithjpa(VendorSignupDto vendorSignupDto){
        Vendor vendor = new Vendor();
        vendor.setVendorFirstName(vendorSignupDto.getVendorFirstName());
        vendor.setVendorLastName(vendorSignupDto.getVendorLastName());
        vendor.setEmailId(vendorSignupDto.getEmailId());
        vendor.setPassword(vendorSignupDto.getPassword());
        vendor.setCompanyName(vendorSignupDto.getCompanyName());
        vendor.setCIN(vendorSignupDto.getCIN());
        vendor.setDateOfIncorporation(vendorSignupDto.getDateOfIncorporation());
        vendor.setRegistrationNumber(vendorSignupDto.getRegistrationNumber());
        vendoRepositoryImpl.save(vendor);
    }


}