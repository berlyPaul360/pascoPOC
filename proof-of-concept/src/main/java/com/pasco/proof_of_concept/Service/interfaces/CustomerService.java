package com.pasco.proof_of_concept.Service.interfaces;

import com.pasco.proof_of_concept.DTO.CustomerDetailsDTO;
import com.pasco.proof_of_concept.Entity.CustomerDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerDetailsDTO addCustomer(CustomerDetailsDTO customer);

    void deleteCustomer(Long customerId);

    CustomerDetailsDTO editCustomerInfo(Long customerId, CustomerDetailsDTO customerDetailsDTO);

    CustomerDetailsDTO viewIndividualCustomer(Long customerId);

    List<CustomerDetailsDTO> viewAllCustomers();

    CustomerDetailsDTO searchCustomerByDlAndDob(String driverLicense, String dateOfBirth);
}
