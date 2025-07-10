package com.RenowattVendor.vendor.dtos;

import com.RenowattVendor.vendor.model.Vendor;

public class VendorResponceDto {
    private Vendor vendor;
    private String status;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
