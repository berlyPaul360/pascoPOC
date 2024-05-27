package com.pasco.proof_of_concept.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerDetailsDTO {

    private Long customerId;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String state;

    private String zipCode;


    private String dobDate;

    private String DL;

    private boolean DLValid;

}