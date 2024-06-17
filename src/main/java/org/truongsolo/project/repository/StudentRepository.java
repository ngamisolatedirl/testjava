package org.truongsolo.project.repository;



import org.truongsolo.project.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    /**
     *
     * @param id : String
     * @return : list Dto query in table with id  =  ????
     */
    Optional<List<Student>> getById(String id);
    List<Student> getAll();
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);
}
