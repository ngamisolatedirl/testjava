package org.truongsolo.project.mapper.impl;


import org.springframework.beans.BeanUtils;
import org.truongsolo.project.dto.StudentDto;
import org.truongsolo.project.entity.Student;
import org.truongsolo.project.mapper.StudentMapper;


public class StudentMapperImpl implements StudentMapper {
    @Override
    public Student dtoToEntity(StudentDto dto) {
        Student student  = new Student();
        BeanUtils.copyProperties(dto,student);
        return student;
    }

    @Override
    public StudentDto entityToDto(Student entity) {
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }
}
