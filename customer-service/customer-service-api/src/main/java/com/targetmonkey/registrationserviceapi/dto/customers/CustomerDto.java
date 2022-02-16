package com.targetmonkey.registrationserviceapi.dto.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class CustomerDto{
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
}
