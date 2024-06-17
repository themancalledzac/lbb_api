package LittleBlackBookApi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class UserModel {

    private UUID uuid;
    private String name;
    private String username;
    private List<UserModel> contactList;

    public UserModel(UUID uuid, String name, String username, Object o) {
    }
}
