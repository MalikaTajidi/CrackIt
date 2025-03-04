package com.crackit.crackit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.crackit.crackit.dto.UserResponseDTO;
import com.crackit.crackit.model.User;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO userToUserResponseDTO(User user);
}
