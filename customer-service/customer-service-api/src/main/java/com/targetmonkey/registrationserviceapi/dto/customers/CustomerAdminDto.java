package com.targetmonkey.registrationserviceapi.dto.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class CustomerAdminDto{
     private long id;
     private String name;
     private String surname;
     private String username;
    private Date created;
    private Date updated;
     private String status;

//    private Company company;

}
