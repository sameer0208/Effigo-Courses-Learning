package com.effigopracticeproject.learning_portal.mapper;

import com.effigopracticeproject.learning_portal.dto.CourseResponseDto;
import com.effigopracticeproject.learning_portal.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseResponseDtoMapper {

    @Mapping(target = "courseId", source = "courseId")
    @Mapping(target = "courseCategory", source = "courseCategory")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "title", source = "title")
    CourseResponseDto courseEntityToDto(Course course);
}
