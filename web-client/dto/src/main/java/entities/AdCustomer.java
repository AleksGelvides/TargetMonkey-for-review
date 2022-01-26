package entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdCustomer {
    private long id;
    private String name;
    private String phone;
    private String eMail;
    private String pass;
}
