package com.pasco.proof_of_concept.Mapper;

import com.pasco.proof_of_concept.DTO.CustomerDetailsDTO;
import com.pasco.proof_of_concept.Entity.CustomerDetails;

public class CustomerMapper {

    public static CustomerDetailsDTO mapEntityToCustomerDetailsDto(CustomerDetails customer){

        return CustomerDetailsDTO.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .address(customer.getAddress())
                .dobDate(customer.getDobDate())
                .city(customer.getCity())
                .state(customer.getState())
                .zipCode(customer.getZipCode())
                .DL(customer.getDL())
                .DLValid(customer.isDLValid())
                .build();
    }

    public static CustomerDetails mapCustomerDetailsDtoToEntity(CustomerDetailsDTO customer){

        return CustomerDetails.builder()
               .customerId(customer.getCustomerId())
               .firstName(customer.getFirstName())
               .lastName(customer.getLastName())
               .address(customer.getAddress())
               .dobDate(customer.getDobDate())
               .city(customer.getCity())
               .state(customer.getState())
               .zipCode(customer.getZipCode())
                .DL(customer.getDL())
                .DLValid(customer.isDLValid())
               .build();
    }
}
