package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.User;

import com.effigopracticeproject.learning_portal.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public interface UserResponseDtoMapper {

    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "userRole", source = "userRole")
    @Mapping(target = "registrationDateTime", source = "registrationDateTime")
    UserResponseDto userEntityToDto(User user);
}
