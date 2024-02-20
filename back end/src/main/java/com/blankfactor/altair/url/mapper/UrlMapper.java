package com.blankfactor.altair.url.mapper;

import com.blankfactor.altair.url.domain.Url;
import com.blankfactor.altair.url.model.UrlDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Value;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UrlMapper {

    @Value("${app.host-url}")
    String hostUrl = "http://192.168.10.93:8080";

    //@Mapping(target = "user", ignore = true)
    Url toEntity(UrlDTO urlDTO);

   // @Mapping(target = "qrCode", ignore = true)
    UrlDTO toDto(Url url);

    //@Mapping(target = "user", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Url partialUpdate(UrlDTO urlDTO,
                      @MappingTarget
                      Url url);

    @Mapping(target = "qrCode", source = "qrCode")
    @AfterMapping
    default void addHostNameToUrl(Url url,
                                  @MappingTarget
                                  UrlDTO urlDTO) {
        urlDTO.setShortUrl(String.format("%s/%s", hostUrl, url.getShortUrl()));
    }
}