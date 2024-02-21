package com.blankfactor.altair.user.rest;

import static com.blankfactor.altair.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;

import com.blankfactor.altair.security.CustomUserDetails;
import com.blankfactor.altair.user.mapper.UserMapper;
import com.blankfactor.altair.user.model.UserDTO;
import com.blankfactor.altair.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserResource(final UserService userService, final UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/me")
    public UserDTO getCurrentUser(
            @AuthenticationPrincipal
            CustomUserDetails currentUser) {
        return userMapper.toDto(userService.validateAndGetUserByUsername(currentUser.getUsername()));
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers().stream().map(userMapper::toDto).toList();
    }

    @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping("/{username}")
    public UserDTO getUser(
            @PathVariable
            String username) {
        return userMapper.toDto(userService.validateAndGetUserByUsername(username));
    }
}
