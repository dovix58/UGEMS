package vu.psk.ugems.service;

import jakarta.persistence.EntityNotFoundException;
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
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

        if (user == null)
            throw new UsernameNotFoundException("User with email " + email + " not found");
        return new UserPrincipal(user);
    }
}
