package com.microgis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("vologroup")) {
            return createUser("vologroup", "y89mgPuUpFv1nE");
        } else {
            String[] result = username.split(";");
            if (result.length == 2) {
                return createUser(result[0], result[1]);
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        }
    }

    private UserDetails createUser(String username, String password) {
        return User.withUsername(username)
                .authorities("USER")
                .passwordEncoder(passwordEncoder::encode)
                .password(password)
                .build();
    }
}