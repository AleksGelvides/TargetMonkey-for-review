package com.targetmonkey.registrationserviceapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdCompanyRegistrationDTO {
    @Nullable
    private int id;
    private int ownerId;
    private String companyName;
    private String category;
}
