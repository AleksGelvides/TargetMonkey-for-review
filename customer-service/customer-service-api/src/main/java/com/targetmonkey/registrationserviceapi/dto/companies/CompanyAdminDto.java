package com.targetmonkey.registrationserviceapi.dto.companies;

import com.targetmonkey.registrationserviceapi.enums.Status;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CompanyAdminDto {
    private long id;
    private long ownerId;
    private String companyName;
    private String companyGId;
    private String category;
    private String description;
    private Date created;
    private Date updated;
    private long leadsCounts;
    private Status status;
    private String moderationComment;
}
