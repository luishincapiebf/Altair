package com.blankfactor.altair.user.domain;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, String name, String email, String lastLogin, String password, String role,
                      String username) implements Serializable {
}