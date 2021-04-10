package com.microgis.service;

import com.microgis.controller.dto.LoginRequest;
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

    private final LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.startsWith("vologroup")) {
            return createUser("vologroup", "y89mgPuUpFv1nE"); //dummy user for volo group
        } else {
            String[] result = username.split(";");
            if (result.length == 3) {
                LoginRequest loginRequest = new LoginRequest(result[2], result[1], result[0]);
                var loginResponse = loginService.checkLoginInformation(loginRequest);
                if (loginResponse != null) {
                    return createUser(result[0], result[1]);
                }
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        }
        throw new UsernameNotFoundException("User not found");
    }

    private UserDetails createUser(String username, String password) {
        return User.withUsername(username)
                .authorities("USER")
                .passwordEncoder(passwordEncoder::encode)
                .password(password)
                .build();
    }
}