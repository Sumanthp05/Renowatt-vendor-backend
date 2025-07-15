package com.RenowattVendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.RenowattVendor.vendor.model",
		"com.RenowattVendor.project.model",
		"com.RenowattVendor.User.Model",
		"com.RenowattVendor.ServiceType.Model"
})
public class RenowattVendorApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenowattVendorApplication.class, args);
	}
}
