package com.targetmonkey.adclientregistrationlandings.entityMvc;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdCustomerRegistrationMvc {
    @Min(2)
    @Max(10)
    @NotEmpty
    private String name;
    @Min(11)
    @Pattern(regexp = "[8]\\d{10,11}")
    @NotEmpty
    private String phone;
    @Pattern(regexp = "[@].*[.].*")
    @NotEmpty
    private String mail;
    @Min(6)
    @NotEmpty
    private String pass;
}
