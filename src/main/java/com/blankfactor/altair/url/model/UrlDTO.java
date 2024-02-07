package com.blankfactor.altair.url.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UrlDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String shortUrl;

    @NotNull
    @Size(max = 255)
    private String longUrl;

    @NotNull
    @Size(max = 255)
    private String createdAt;

    @NotNull
    @Size(max = 255)
    private String expiresAt;

    private Long user;

}