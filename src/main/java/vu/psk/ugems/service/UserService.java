package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vu.psk.ugems.dto.ChangePasswordRequest;
import vu.psk.ugems.dto.LoginDTO;
import vu.psk.ugems.dto.UserDTO;
import vu.psk.ugems.mapper.UserMapper;
import vu.psk.ugems.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public void createUser(UserDTO userDTO) {
        var user = userMapper.toEntity(userDTO);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public LoginDTO verifyUser(UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid email or password");
        }
        var user = userRepository.findByEmail(userDTO.getEmail());
        var token = jwtService.generateToken(userDTO.getEmail());

        return LoginDTO
                .builder()
                .token(token)
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public void changeUserPassword(ChangePasswordRequest changePasswordRequest) {
        var user = userRepository.findByEmail(changePasswordRequest.getEmail());
        if (!bCryptPasswordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword()))
            throw new BadCredentialsException("Old password is incorrect when trying to change password for " + changePasswordRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
    }
}
