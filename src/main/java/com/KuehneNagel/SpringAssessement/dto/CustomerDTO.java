package com.KuehneNagel.SpringAssessement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDTO {
    private long id;
    private long registrationCode;
    @NotBlank(message = "Customer name must not be blank")
    private String fullName;
    @Email(message = "Make sure that email is correct")
    private String email;
    private String phone;
}
