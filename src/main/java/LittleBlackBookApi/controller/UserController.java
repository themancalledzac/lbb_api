package LittleBlackBookApi.controller;

import LittleBlackBookApi.model.CreateUserModel;
import LittleBlackBookApi.model.UserModel;
import LittleBlackBookApi.model.createNewContact;
import LittleBlackBookApi.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @PostMapping("/createUser")
    public UserModel createUser(@RequestBody CreateUserModel user) {
        return userService.createUser(user);
    }

    @GetMapping("/getUser/{uuid}")
    public UserModel getUser(@PathVariable("uuid") String uuid) {
        return userService.getUserByUuid(uuid);
    }

    @PostMapping("/updateUser/")
    public UserModel updateUser(@RequestBody UserModel user) {
        return userService.updateUser(user);
    }


    @PostMapping("/createAndAddContact")
    public UserModel createAndAddContact(@RequestBody createNewContact createNewContact) {
        return userService.createAndAddContact(createNewContact);
    }
}
