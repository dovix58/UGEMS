package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vu.psk.ugems.dto.ChangePasswordRequest;
import vu.psk.ugems.dto.LoginDTO;
import vu.psk.ugems.dto.UserDTO;
import vu.psk.ugems.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void createUser(@RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody UserDTO userDTO) {
        return userService.verifyUser(userDTO);
    }

    @PostMapping("/password")
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changeUserPassword(changePasswordRequest);
    }
}
