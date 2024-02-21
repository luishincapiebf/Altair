package com.blankfactor.altair.user.model;

import java.io.Serializable;

import com.blankfactor.altair.user.domain.User;

/**
 * DTO for {@link User}
 */
public record UserDTO(Long id, String name, String email, String lastLogin, String password, String role,
                      String username) implements Serializable {
}