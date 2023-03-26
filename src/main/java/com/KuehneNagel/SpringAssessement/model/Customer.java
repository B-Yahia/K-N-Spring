package com.KuehneNagel.SpringAssessement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long registrationCode;
    private String fullName;
    @Email(message = "Make sure that email is correct")
    @Column( unique=true)
    private String email;
    @Column( unique=true)
    private String phone;

}
