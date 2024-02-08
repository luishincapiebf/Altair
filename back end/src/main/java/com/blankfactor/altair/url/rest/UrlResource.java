package com.blankfactor.altair.url.rest;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.model.UrlDTO;
import com.blankfactor.altair.url.service.UrlService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/")
@Tag(name = "UrlResource")
public class UrlResource {

    private final UrlService urlService;

    public UrlResource(final UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) throws URISyntaxException {
        Url urlByShortUrl = this.urlService.getOriginalUrlByShortUrl(shortUrl);
        String redirectTo = urlByShortUrl.getLongUrl();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI(redirectTo));
        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping
    public ResponseEntity<Url> returnShortUrlFromOriginalUrl(@RequestParam(value = "longUrl") String longUrl) {
        Url url = this.urlService.findOrSaveShortUrl(longUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

}
