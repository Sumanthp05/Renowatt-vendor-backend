package com.RenowattVendor.User.Model;

import com.RenowattVendor.vendor.model.Vendor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "user_details")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "user_f_name")
    private String userFirstName;
    @Column(name = "user_l_name")
    private String userLastName;
    @Column(name = "e_id")
    private Integer employeeId;
    @Column(name = "designation")
    private String designation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_vendor_id", referencedColumnName = "vendor_id", nullable = false)
    @JsonIgnore
    private Vendor vendor;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
