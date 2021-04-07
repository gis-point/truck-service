package com.microgis.controller;

import com.microgis.controller.dto.JwtResponse;
import com.microgis.controller.dto.LoginRequest;
import com.microgis.service.JwtService;
import com.microgis.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/truck-service")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final JwtService jwtService;

    @Resource(name = "userService")
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final LoginService loginService;

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public JwtResponse getToken(@RequestParam(value = "domain", required = false) String domain,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password) {
        LOGGER.info("Trying to login user with domain - {} ,email - {} and password - {}", domain, email, password);
        UserDetails userDetails;
        try {
            LoginRequest loginRequest = new LoginRequest(domain, email, password);
            var loginResponse = loginService.checkLoginInformation(loginRequest);
            if (loginResponse != null) {
                userDetails = userDetailsService.loadUserByUsername(email + ";" + password);
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
            }
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("login", email);
            claims.put("domain", domain);

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            claims.put("authorities", authorities);

            String jwt = jwtService.createJwtForClaims(email, claims);
            return new JwtResponse(jwt);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }

}