package org.truongsolo.project.mapper;


import org.truongsolo.project.dto.StudentDto;
import org.truongsolo.project.entity.Student;

public interface StudentMapper {
    Student dtoToEntity(StudentDto dto);
    StudentDto entityToDto(Student entity);
}
