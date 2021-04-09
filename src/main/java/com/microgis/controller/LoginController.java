package com.microgis.controller;

import com.microgis.controller.dto.JwtResponse;
import com.microgis.service.JwtService;
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
import javax.servlet.http.HttpServletRequest;
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

    /**
     * User authorization and jwt token creation
     *
     * @param domain   volo domain
     * @param email    driver email
     * @param password driver password
     * @return jwt token
     */
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public JwtResponse getToken(@RequestParam(value = "domain", required = false) String domain,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                HttpServletRequest httpServletRequest) {
        UserDetails userDetails;
        String domainName = domain != null ? domain : getClientIp(httpServletRequest);
        LOGGER.info("Trying to login user with domain - {}, email - {} and password - {}", domainName, email, password);
        try {
            userDetails = userDetailsService.loadUserByUsername(email + ";" + password + ";" + domainName);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("login", email);
            claims.put("domain", domainName);

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            claims.put("authorities", authorities);

            String jwt = jwtService.createJwtForClaims(email, claims);
            return new JwtResponse(jwt);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }

    /**
     * Get client ip
     *
     * @param request http request
     * @return real client api
     */
    private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

}