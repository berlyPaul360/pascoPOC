package com.pasco.proof_of_concept.Service;

import com.pasco.proof_of_concept.DTO.DriverLicenseInfoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class DriverLicenseVerificationService {

    private RestTemplate restTemplate;
    /**
     * Verifies a driver's license using the provided license number and date of birth.
     *
     * @param driverLicenseNumber the driver's license number to be verified
     * @param dateOfBirth the date of birth associated with the driver's license
     * @return a {@link DriverLicenseInfoDTO} containing the verification details
     */
    public DriverLicenseInfoDTO verifyDriverLicense(String driverLicenseNumber, String dateOfBirth) {
        String url = "https://api.example.gov/verify?license=" + driverLicenseNumber + "&dob=" + dateOfBirth;
        // Adjust the URL to match the actual API endpoint and parameters
        //Object subject to change after knowing the API documentation????
        DriverLicenseInfoDTO response = restTemplate.getForObject(url, DriverLicenseInfoDTO.class);
        // Handle any exceptions or errors as necessary

        return response;
    }
}
