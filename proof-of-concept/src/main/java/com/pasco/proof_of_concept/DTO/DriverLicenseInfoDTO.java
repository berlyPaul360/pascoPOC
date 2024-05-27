package com.pasco.proof_of_concept.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DriverLicenseInfoDTO {


    private String driverLicenseNumber;
    private String driverLicenseState;
    private String driverLicenseExpirationDate;
    private String driverLicenseIssueDate;
    private boolean driverLicenseValid;

}
