package com.blankfactor.altair.url.service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.mapper.UrlMapper;
import com.blankfactor.altair.url.model.UrlDTO;
import com.blankfactor.altair.url.repos.UrlRepository;
import com.blankfactor.altair.util.NotFoundException;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UrlService {

    @Value("${host-url}")
    private String hostUrl = "http://localhost:8080";

    private final UrlRepository urlRepository;

    private static final List<Character> ALLOWED_CHARS = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '/', '+');
    private final UrlMapper urlMapper;

    public UrlService(final UrlRepository urlRepository, UrlMapper urlMapper) {
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    @Transactional
    public Url getOriginalUrlByShortUrl(String shortUrl) {
        log.info("Getting original URL by short URL: {}", shortUrl);
        Url url = this.urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NotFoundException(String.format("'%s' not found", shortUrl)));
        url.setAccessCounter(url.getAccessCounter() + 1);
        this.urlRepository.save(url);
        this.urlRepository.flush();
        return url;
    }

    public UrlDTO findOrSaveShortUrl(String originalUrl) {
        Url url;
        Optional<Url> optionalUrl = urlRepository.findByLongUrl(originalUrl);
        if (optionalUrl.isPresent()) {
            url = optionalUrl.get();
        } else {
            Url urlToSave = Url.builder()
                    .longUrl(originalUrl)
                    .expiresAt(LocalDate.now().plusMonths(1).toString())
                    .shortUrl(generateShortUrl(originalUrl))
                    .build();
            url = this.urlRepository.save(urlToSave);
        }
        UrlDTO urlDTO = urlMapper.toDto(url);

        urlDTO.setQrCode(generateQRCode(String.format("%s/%s", hostUrl, url.getShortUrl())));

        log.info("Returning short URL: {}", urlDTO.getShortUrl());
        return urlDTO;
    }

    private String generateQRCode(String url) {
        ByteArrayOutputStream outputStream = QRCode.from(url).withSize(250, 250).to(ImageType.PNG).stream();
        byte[] qrCodeBytes = outputStream.toByteArray();
        return Base64.getEncoder().encodeToString(qrCodeBytes);
    }

    public static String generateShortUrl(String originalUrl) {
        byte[] hash = Hashing.sha256().hashString(originalUrl, StandardCharsets.UTF_8).asBytes();
        return IntStream.range(0, 7)
                .map(i -> hash[i] & 0xFF)
                .mapToObj(index -> String.valueOf(ALLOWED_CHARS.get(index % ALLOWED_CHARS.size())))
                .collect(Collectors.joining());
    }
}
