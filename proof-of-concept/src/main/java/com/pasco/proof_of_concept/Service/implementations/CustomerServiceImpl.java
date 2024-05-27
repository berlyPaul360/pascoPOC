package com.pasco.proof_of_concept.Service.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pasco.proof_of_concept.DTO.CustomerDetailsDTO;
import com.pasco.proof_of_concept.Entity.CustomerDetails;
import com.pasco.proof_of_concept.Exceptions.ResourceNotFoundException;
import com.pasco.proof_of_concept.Mapper.CustomerMapper;
import com.pasco.proof_of_concept.Repository.CustomerDetailsRepository;
import com.pasco.proof_of_concept.Service.interfaces.CustomerService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDetailsRepository customerDetailsRepo;

    /**
     * Adds a new customer to the repository.
     *
     * This method takes a CustomerDetailsDTO object, maps it to a CustomerDetails entity,
     * saves it to the repository, and then maps the saved entity back to a CustomerDetailsDTO.
     *
     * @param customer the CustomerDetailsDTO object containing the details of the customer to be added
     * @return the CustomerDetailsDTO object of the saved customer entity
     */
    @Override
    public CustomerDetailsDTO addCustomer(CustomerDetailsDTO customer) {
        //To Test
        System.out.println(customer.toString());
        CustomerDetailsDTO customerDetailsDto = CustomerDetailsDTO.builder()
                .customerId(1L)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .city("Springfield")
                .state("IL")
                .zipCode("62701")
                .dobDate("05-25-2024")
                .DL("D12345678")
                .DLValid(true)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(customerDetailsDto);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //Mapping the incoming DTO to an entity
        //CustomerDetails customerEntity = CustomerMapper.mapCustomerDetailsDtoToEntity(customer);
        CustomerDetails customerEntity = CustomerMapper.mapCustomerDetailsDtoToEntity(customer);
        CustomerDetails savedCustomerEntity = customerDetailsRepo.save(customerEntity);

        return CustomerMapper.mapEntityToCustomerDetailsDto(savedCustomerEntity);
    }

    /**
     * Deletes a customer from the repository based on the provided customer ID.
     *
     * @param customerId the ID of the customer to be deleted
     */
    @Override
    public void deleteCustomer(Long customerId) {
        customerDetailsRepo.deleteById(customerId);
    }

    /**
     * Edits the information of an existing customer based on the provided customer ID and new details.
     *
     * This method retrieves the existing customer from the repository using the provided customer ID.
     * If the customer is found, it updates the customer's details with the new information provided in the
     * CustomerDetailsDTO object. If a field in the CustomerDetailsDTO object is null, the existing value
     * for that field is retained. The updated customer entity is then saved back to the repository.
     *
     * @param customerId the ID of the customer to be edited
     * @param customerDetailsDTO the CustomerDetailsDTO object containing the new details of the customer
     * @return the CustomerDetailsDTO object of the updated customer entity
     * @throws ResourceNotFoundException if the customer with the provided ID is not found
     */
    @Override
    public CustomerDetailsDTO editCustomerInfo(Long customerId, CustomerDetailsDTO customerDetailsDTO) {
        // Find the existing customer
        CustomerDetails existingCustomer = customerDetailsRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found", "id", customerId.toString()));

        // Update the customer details
        CustomerDetails updatedCustomer = CustomerDetails.builder()
                .customerId(existingCustomer.getCustomerId())
                .firstName(customerDetailsDTO.getFirstName() != null ? customerDetailsDTO.getFirstName() : existingCustomer.getFirstName())
                .lastName(customerDetailsDTO.getLastName()!= null? customerDetailsDTO.getLastName() : existingCustomer.getLastName())
                .address(customerDetailsDTO.getAddress()!= null? customerDetailsDTO.getAddress() : existingCustomer.getAddress())
                .city(customerDetailsDTO.getCity()!= null? customerDetailsDTO.getCity() : existingCustomer.getCity())
                .state(customerDetailsDTO.getState()!= null? customerDetailsDTO.getState() : existingCustomer.getState())
                .zipCode(customerDetailsDTO.getZipCode()!= null? customerDetailsDTO.getZipCode() : existingCustomer.getZipCode())
                .dobDate(customerDetailsDTO.getDobDate()!= null? customerDetailsDTO.getDobDate() : existingCustomer.getDobDate())
                .DL(customerDetailsDTO.getDL()!= null? customerDetailsDTO.getDL() : existingCustomer.getDL())
                .DLValid(existingCustomer.isDLValid())
                .build();


        // Save the updated customer
        customerDetailsRepo.save(updatedCustomer);

        return CustomerMapper.mapEntityToCustomerDetailsDto(updatedCustomer);
    }

    /**
     * Retrieves the details of an individual customer based on the provided customer ID.
     *
     * This method fetches the customer entity from the repository using the provided customer ID.
     * If the customer is found, it maps the customer entity to a CustomerDetailsDTO object.
     * If the customer is not found, it throws a ResourceNotFoundException.
     *
     * @param customerId the ID of the customer whose details are to be retrieved
     * @return the CustomerDetailsDTO object containing the details of the retrieved customer
     * @throws ResourceNotFoundException if the customer with the provided ID is not found
     */
    @Override
    public CustomerDetailsDTO viewIndividualCustomer(Long customerId) {
        CustomerDetails customer = customerDetailsRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found", "id", customerId.toString()));
        return CustomerMapper.mapEntityToCustomerDetailsDto(customer);
    }

    /**
     * Retrieves a list of all customers from the repository.
     *
     * This method fetches all customer entities from the repository,
     * maps each entity to a CustomerDetailsDTO object, and returns a list of these DTOs.
     *
     * @return a list of CustomerDetailsDTO objects containing the details of all customers
     */
    @Override
    public List<CustomerDetailsDTO> viewAllCustomers() {

        List<CustomerDetails> customers = customerDetailsRepo.findAll();
        return customers.stream()
                .map(CustomerMapper::mapEntityToCustomerDetailsDto)
                .collect(Collectors.toList());

    }

    /**
     * Searches for a customer in the repository based on the provided driver's license and date of birth.
     *
     * This method takes a driver's license number and a date of birth as strings, parses the date of birth
     * into a Date object, and then searches the repository for a customer with the matching driver's license
     * and date of birth. If a matching customer is found, it maps the customer entity to a CustomerDetailsDTO object.
     * If the date of birth string cannot be parsed, it throws a RuntimeException.
     *
     * @param driverLicense the driver's license number of the customer to be searched
     * @param dateOfBirth the date of birth of the customer to be searched, in the format "yyyy-MM-dd"
     * @return the CustomerDetailsDTO object containing the details of the found customer
     * @throws RuntimeException if the date of birth string cannot be parsed
     */
    @Override
    public CustomerDetailsDTO searchCustomerByDlAndDob(String driverLicense, String dateOfBirth) {

        // Parse the string into a Date object
        String dl = driverLicense.trim();
        String dob = dateOfBirth.trim();
        CustomerDetails customer = customerDetailsRepo.findByDLAndDobDate(dl, dob);

        System.out.println("Found customer: " + customer);
        return CustomerMapper.mapEntityToCustomerDetailsDto(customer);
    }


}
