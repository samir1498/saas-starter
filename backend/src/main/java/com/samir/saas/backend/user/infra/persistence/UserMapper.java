package com.samir.saas.backend.user.infra.persistence;

import com.samir.saas.backend.user.domain.User;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toEntity(User user);
    User toDomain(UserEntity entity);
}
