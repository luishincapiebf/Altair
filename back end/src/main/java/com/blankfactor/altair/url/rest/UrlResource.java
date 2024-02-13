package com.blankfactor.altair.url.rest;

import java.net.URI;
import java.net.URISyntaxException;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.model.UrlDTO;
import com.blankfactor.altair.url.service.UrlService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@Tag(name = "UrlResource")
public class UrlResource {

    private final UrlService urlService;

    public UrlResource(final UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable
            String shortUrl) throws URISyntaxException {
        Url urlByShortUrl = this.urlService.getOriginalUrlByShortUrl(shortUrl);
        String redirectTo = urlByShortUrl.getLongUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(redirectTo));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping
    public ResponseEntity<UrlDTO> returnShortUrlFromOriginalUrl(
            @RequestParam(value = "longUrl")
            String longUrl) {
        UrlDTO urlDTO = this.urlService.findOrSaveShortUrl(longUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(urlDTO);
    }

}
