package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesResponseDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisteredCoursesResponseDtoMapper {

    @Mapping(target = "registrationId", source = "registrationId")
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "courseId", source = "course.courseId")
    RegisteredCoursesResponseDto entityToResponseDto(RegisteredCourses registeredCourses);
}
