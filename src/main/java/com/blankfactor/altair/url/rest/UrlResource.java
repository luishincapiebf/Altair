package com.blankfactor.altair.url.rest;

import com.blankfactor.altair.url.model.UrlDTO;
import com.blankfactor.altair.url.service.UrlService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/urls", produces = MediaType.APPLICATION_JSON_VALUE)
public class UrlResource {

    private final UrlService urlService;

    public UrlResource(final UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping
    public ResponseEntity<List<UrlDTO>> getAllUrls() {
        return ResponseEntity.ok(urlService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrlDTO> getUrl(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(urlService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUrl(@RequestBody @Valid final UrlDTO urlDTO) {
        final Long createdId = urlService.create(urlDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUrl(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UrlDTO urlDTO) {
        urlService.update(id, urlDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUrl(@PathVariable(name = "id") final Long id) {
        urlService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
