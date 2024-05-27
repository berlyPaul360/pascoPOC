package com.pasco.proof_of_concept.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customers")
@Builder
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
