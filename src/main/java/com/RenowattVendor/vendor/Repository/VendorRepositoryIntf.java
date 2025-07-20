package com.RenowattVendor.vendor.Repository;

import com.RenowattVendor.vendor.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepositoryIntf extends JpaRepository<Vendor, Integer> {
    public Optional<Vendor> findByemailId(String emailId);

}
