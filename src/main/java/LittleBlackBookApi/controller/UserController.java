package LittleBlackBookApi.controller;

import LittleBlackBookApi.dto.AddContactRequest;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short term Controller for User data.
 * <p>
 * This is not secure, just passing a JSON body of username/password
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getUser/{username}")
    public UserModel getUser(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/createUser")
    public UserModel createUser(@RequestParam("user") UserModel user) {
        return userService.createUser(user);
    }

    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@RequestBody AddContactRequest request) {
        userService.addContact(request.getUserUuid(), request.getContactUuid());
        return ResponseEntity.ok().build();
    }
}
