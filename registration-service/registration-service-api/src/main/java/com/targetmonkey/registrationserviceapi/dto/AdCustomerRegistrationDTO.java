package com.targetmonkey.registrationserviceapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdCustomerRegistrationDTO {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String pass;
}