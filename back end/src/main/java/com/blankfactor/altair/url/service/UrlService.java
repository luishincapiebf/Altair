package com.blankfactor.altair.url.service;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.repos.UrlRepository;
import com.blankfactor.altair.util.NotFoundException;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UrlService {

    private final UrlRepository urlRepository;

    private static final List<Character> ALLOWED_CHARS = Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '+'
    );

    public UrlService(final UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url getOriginalUrlByShortUrl(String shortUrl) {
        return this.urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NotFoundException(String.format("'%s' not found", shortUrl)));
    }

    public Url findOrSaveShortUrl(String originalUrl) {
        Url url;
        Optional<Url> optionalUrl = urlRepository.findByLongUrl(originalUrl);
        if (optionalUrl.isPresent()) {
            url = optionalUrl.get();
        } else {
            Url urlToSave = Url.builder()
                    .longUrl(originalUrl)
                    .expiresAt("01-01-2023")
                    .shortUrl(generateShortUrl(originalUrl))
                    .build();
            url = this.urlRepository.save(urlToSave);
        }
        return url;
    }

    public static String generateShortUrl(String originalUrl) {
        byte[] hash = Hashing.sha256()
                .hashString(originalUrl, StandardCharsets.UTF_8)
                .asBytes();
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int index = hash[i] & 0xFF;
            shortUrl.append(ALLOWED_CHARS.get(index % ALLOWED_CHARS.size()));
        }
        return shortUrl.toString();
    }
}
