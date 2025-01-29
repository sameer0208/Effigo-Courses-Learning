package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.UserResponseDto;
import com.effigopracticeproject.learning_portal.entity.User;

import com.effigopracticeproject.learning_portal.service.UserService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public interface UserResponseDtoMapper {

//    UserResponseDto userEntityToDto(User user);
}
