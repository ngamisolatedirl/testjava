package org.truongsolo.project.service;



import org.truongsolo.project.dto.StudentDto;
import org.truongsolo.project.entity.Student;

import java.util.List;

public interface StudentService {
    StudentDto getById(Long id);
    List<StudentDto> findAll();
    void addStudent(StudentDto studentDto);
    void updateStudent(StudentDto studentDto);
    void deleteStudent(StudentDto studentDto );

}
