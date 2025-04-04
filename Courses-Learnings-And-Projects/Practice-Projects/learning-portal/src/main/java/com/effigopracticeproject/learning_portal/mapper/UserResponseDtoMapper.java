package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface UserResponseDtoMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "userRole", source = "userRole")
    @Mapping(target = "registrationDateTime", source = "registrationDateTime")
    UserResponseDto userEntityToDto(User user);
}
