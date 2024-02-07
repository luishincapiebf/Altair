package com.blankfactor.altair.url.service;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.model.UrlDTO;
import com.blankfactor.altair.url.repos.UrlRepository;
import com.blankfactor.altair.user.domain.User;
import com.blankfactor.altair.user.repos.UserRepository;
import com.blankfactor.altair.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UrlService {

    private final UrlRepository urlRepository;
    private final UserRepository userRepository;

    public UrlService(final UrlRepository urlRepository, final UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.userRepository = userRepository;
    }

    public List<UrlDTO> findAll() {
        final List<Url> urls = urlRepository.findAll(Sort.by("id"));
        return urls.stream()
                .map(url -> mapToDTO(url, new UrlDTO()))
                .toList();
    }

    public UrlDTO get(final Long id) {
        return urlRepository.findById(id)
                .map(url -> mapToDTO(url, new UrlDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UrlDTO urlDTO) {
        final Url url = new Url();
        mapToEntity(urlDTO, url);
        return urlRepository.save(url).getId();
    }

    public void update(final Long id, final UrlDTO urlDTO) {
        final Url url = urlRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(urlDTO, url);
        urlRepository.save(url);
    }

    public void delete(final Long id) {
        urlRepository.deleteById(id);
    }

    private UrlDTO mapToDTO(final Url url, final UrlDTO urlDTO) {
        urlDTO.setId(url.getId());
        urlDTO.setShortUrl(url.getShortUrl());
        urlDTO.setLongUrl(url.getLongUrl());
        urlDTO.setCreatedAt(url.getCreatedAt());
        urlDTO.setExpiresAt(url.getExpiresAt());
        urlDTO.setUser(url.getUser() == null ? null : url.getUser().getId());
        return urlDTO;
    }

    private Url mapToEntity(final UrlDTO urlDTO, final Url url) {
        url.setShortUrl(urlDTO.getShortUrl());
        url.setLongUrl(urlDTO.getLongUrl());
        url.setCreatedAt(urlDTO.getCreatedAt());
        url.setExpiresAt(urlDTO.getExpiresAt());
        final User user = urlDTO.getUser() == null ? null : userRepository.findById(urlDTO.getUser())
                .orElseThrow(() -> new NotFoundException("user not found"));
        url.setUser(user);
        return url;
    }

}
