package org.playground.scpapi.media;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MediaMapper {
    MediaDto toDto(Media media);
}