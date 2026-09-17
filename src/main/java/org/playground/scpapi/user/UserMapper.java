package org.playground.scpapi.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "userDepartments", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "created", expression = "java(LocalDateTime.now())")
    @Mapping(target = "profile", ignore = true)
    User toEntity(RegisterUserRequest request);

    UserDto toDto(User user);
}
