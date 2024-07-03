package LittleBlackBookApi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class createNewContact {

    private String userUuid;
    private String contactFirstName;
    private String contactLastName;
    private String contactPhoneNumber;
    private String contactEmail;
}
