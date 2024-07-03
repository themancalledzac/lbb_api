package LittleBlackBookApi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateUserModel {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
