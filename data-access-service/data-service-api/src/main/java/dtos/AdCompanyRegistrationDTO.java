package dtos;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdCompanyRegistrationDTO {
    private long id;
    private long ownerId;
    private String companyName;
    private String category;
}
