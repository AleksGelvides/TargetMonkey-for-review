package com.targetmonkey.companymoderationservice.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Company {
    private long id;
    private long ownerId;
    private String companyName;
    private String Inn;
    private String category;
    private String description;
}
