package vu.psk.ugems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vu.psk.ugems.entity.UserPrincipal;
import vu.psk.ugems.repository.UserRepository;
import vu.psk.ugems.entity.User;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UsernameNotFoundException("User with email " + email + " not found");
        return new UserPrincipal(user);
    }
}
