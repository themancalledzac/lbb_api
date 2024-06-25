package LittleBlackBookApi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class UserModel {

    private String uuid;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<UserModel> contactList;

    public UserModel(String uuid, String firstName, String lastName, String phoneNumber, String email) {
    }

    public UserModel(UUID uuid, String firstName, String lastName, String phoneNumber, String email) {
    }
}
