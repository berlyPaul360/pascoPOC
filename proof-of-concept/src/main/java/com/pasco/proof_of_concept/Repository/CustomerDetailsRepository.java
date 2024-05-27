package com.pasco.proof_of_concept.Repository;

import com.pasco.proof_of_concept.Entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {
    @Query(value = "SELECT * FROM customers u WHERE u.dl = ?1 AND u.dob_date = ?2", nativeQuery = true)
    CustomerDetails findByDLAndDobDate(String driverLicense, String dateOfBirth);
}
