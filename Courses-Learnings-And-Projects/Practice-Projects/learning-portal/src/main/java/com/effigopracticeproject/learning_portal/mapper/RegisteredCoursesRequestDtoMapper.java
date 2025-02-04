package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.RegisteredCoursesRequestDto;
import com.effigopracticeproject.learning_portal.entity.RegisteredCourses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegisteredCoursesRequestDtoMapper {

    @Mapping(target = "user.userId", source = "userId")
    @Mapping(target = "course.courseId", source = "courseId")
    RegisteredCourses requestDtoToEntity(RegisteredCoursesRequestDto registeredCoursesRequestDto);
}
