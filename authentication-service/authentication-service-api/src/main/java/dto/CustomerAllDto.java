package dto;

import enums.Role;
import enums.Status;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class CustomerAllDto {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Date created;
    private Date updated;
    private Status status;
    List<String> roles;
}
