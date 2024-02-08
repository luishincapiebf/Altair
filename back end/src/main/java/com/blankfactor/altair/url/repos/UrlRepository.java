package com.blankfactor.altair.url.repos;

import com.blankfactor.altair.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByLongUrl(String originalUrl);
}