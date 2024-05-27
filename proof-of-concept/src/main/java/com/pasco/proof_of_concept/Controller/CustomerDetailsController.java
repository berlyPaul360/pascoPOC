package com.pasco.proof_of_concept.Controller;

import com.pasco.proof_of_concept.DTO.CustomerDetailsDTO;
import com.pasco.proof_of_concept.DTO.DriverLicenseInfoDTO;
import com.pasco.proof_of_concept.Service.DriverLicenseVerificationService;
import com.pasco.proof_of_concept.Service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerDetailsController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverLicenseVerificationService driverLicenseVerificationService;

    //addCustomer
    @PostMapping
    public CustomerDetailsDTO addCustomer(@RequestBody CustomerDetailsDTO customer){
        System.out.println(customer.toString());
        return customerService.addCustomer(customer);
    }

    //deleteCustomer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }


    //editCustomerInfo
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsDTO> editCustomerInfo(
            @PathVariable Long customerId,
            @RequestBody CustomerDetailsDTO customerDetailsDTO) {
        CustomerDetailsDTO updatedCustomer = customerService.editCustomerInfo(customerId, customerDetailsDTO);
        return ResponseEntity.ok(updatedCustomer);
    }


    //viewIndividualCustomer
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsDTO> viewIndividualCustomer(@PathVariable Long customerId) {
        CustomerDetailsDTO customerDetails = customerService.viewIndividualCustomer(customerId);
        return ResponseEntity.ok(customerDetails);
    }

    //viewAllCustomers
    @GetMapping
    public ResponseEntity<List<CustomerDetailsDTO>> viewAllCustomers() {
        List<CustomerDetailsDTO> customers = customerService.viewAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //searchCustomerByDlAndDob
    @GetMapping("/search")
    public ResponseEntity<CustomerDetailsDTO> searchCustomerByDlAndDob(
            @RequestParam String driverLicense,
            @RequestParam String dateOfBirth) {
        CustomerDetailsDTO customer = customerService.searchCustomerByDlAndDob(driverLicense, dateOfBirth);
        return ResponseEntity.ok(customer);
    }


    //@TODO (External API Call not configured yet) checkDLValid
    @GetMapping("/verifyDriverLicense")
    public ResponseEntity<DriverLicenseInfoDTO> verifyDriverLicense(
            @RequestParam String driverLicense,
            @RequestParam String dateOfBirth) {
        DriverLicenseInfoDTO driverLicenseInfo = driverLicenseVerificationService.verifyDriverLicense(driverLicense, dateOfBirth);
        return ResponseEntity.ok(driverLicenseInfo);
    }



}
