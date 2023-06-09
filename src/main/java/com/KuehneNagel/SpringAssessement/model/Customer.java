package com.KuehneNagel.SpringAssessement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long registrationCode;
    @NotBlank(message = "Customer name must not be blank")
    private String fullName;
    @Email(message = "Make sure that email is correct")
    @Column( unique=true)
    private String email;
    @Column( unique=true)
    private String phone;

}
