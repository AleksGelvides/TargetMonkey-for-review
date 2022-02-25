package com.targetmonkey.companymoderationservice.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Company {
    private long id;
    private String companyName;
    private String companyGId;
    private String moderationComment;
}
