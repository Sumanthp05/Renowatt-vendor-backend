package com.RenowattVendor.vendor.Controllers;

import com.RenowattVendor.login.dtos.LoginRequestDtos;
import com.RenowattVendor.login.dtos.LoginResponceDtos;
import com.RenowattVendor.vendor.Services.VendorAuthenticationService;
import com.RenowattVendor.vendor.dtos.VendorResponceDto;
import com.RenowattVendor.vendor.dtos.VendorSignupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor")
public class VendorAuthenticationController {
    @Autowired
    VendorAuthenticationService vendorAuthenticationService;

    @PostMapping("/auth/signup")
    public @ResponseBody VendorResponceDto SignUp(@RequestBody VendorSignupDto vendorSignupDto){
        return vendorAuthenticationService.SignUp(vendorSignupDto);
    }

    @PostMapping("/auth/signin")
    public @ResponseBody LoginResponceDtos SignIn(@RequestBody LoginRequestDtos loginRequestDtos){
        return vendorAuthenticationService.SignIn(loginRequestDtos);
    }
}
