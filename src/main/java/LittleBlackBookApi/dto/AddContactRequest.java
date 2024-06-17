package LittleBlackBookApi.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AddContactRequest {
    private UUID userUuid;
    private UUID contactUuid;
}
