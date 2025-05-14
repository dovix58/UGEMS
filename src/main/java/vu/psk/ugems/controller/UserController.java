package vu.psk.ugems.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vu.psk.ugems.dto.UserDTO;
import vu.psk.ugems.entity.User;
import vu.psk.ugems.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {

    }
}
