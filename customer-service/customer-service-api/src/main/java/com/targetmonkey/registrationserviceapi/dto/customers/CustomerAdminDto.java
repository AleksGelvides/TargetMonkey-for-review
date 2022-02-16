package com.targetmonkey.registrationserviceapi.dto.customers;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CustomerAdminDto{
     private long id;
     private String name;
     private String surname;
     private String email;
     private String username;
    private Date created;
    private Date updated;
     private String status;

//    private Company company;

}
