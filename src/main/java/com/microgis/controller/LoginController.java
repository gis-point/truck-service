package com.microgis.controller;

import com.microgis.controller.dto.Login;
import com.microgis.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/truck-service")
@Validated
@RequiredArgsConstructor
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    /**
     * User authorization verification on client side
     *
     * @param login user authorization information
     * @return result of authorization
     */
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid Login login) {
        LOGGER.info("Trying to login user with username - {} and password - {}", login.getUsername(), login.getPassword());
        loginService.checkLoginInformation(login);
        return ResponseEntity.ok().build();
    }

}