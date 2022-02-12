package dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerRegistrationDto {
    private String name;
    private String surname;
    private String userName;
    private String email;
    private String password;
}
