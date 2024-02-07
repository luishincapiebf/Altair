package com.blankfactor.altair.url.repos;

import com.blankfactor.altair.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UrlRepository extends JpaRepository<Url, Long> {
}
