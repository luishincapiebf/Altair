package com.blankfactor.altair.url.repos;

import java.util.Optional;

import com.blankfactor.altair.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortUrl(String shortUrl);

    Optional<Url> findByLongUrl(String originalUrl);


}
