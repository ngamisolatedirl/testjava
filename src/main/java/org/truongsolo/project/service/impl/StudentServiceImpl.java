package org.truongsolo.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.truongsolo.project.dto.StudentDto;
import org.truongsolo.project.entity.Student;
import org.truongsolo.project.mapper.StudentMapper;
import org.truongsolo.project.mapper.impl.StudentMapperImpl;
import org.truongsolo.project.repository.StudentRepository;
import org.truongsolo.project.repository.StudentRepositoryReflect;
import org.truongsolo.project.repository.impl.StudentRepositoryImpl;
import org.truongsolo.project.service.StudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StudentServiceImpl  implements StudentService {

    // create instance of StudentRepositoryImpl --> not work
    StudentRepository studentRepository = new StudentRepositoryImpl();
    StudentMapper studentMapper = new StudentMapperImpl();
    // StudentService >>> StudentRepository
    // instead of create instance -> create instance somewhere , inject to class depended
    StudentRepositoryReflect studentRepository1 = new StudentRepositoryReflect(Student.class);

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository1.findAll();
//        // mapping list entity -> dto
//        List<StudentDto> studentDtos = new ArrayList<>();
//        for(Student s : students){
//            StudentDto dto  = studentMapper.entityToDto(s);
//            studentDtos.add(dto);
//        }
//        return studentDtos;
        // lambda function
        return students.stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public void addStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        studentRepository1.add(student);
    }
    @Override
    public void updateStudent(StudentDto studentDto) {
        Student student = studentMapper.dtoToEntity(studentDto);
        studentRepository1.update(student);
    }
    @Override
    public void deleteStudent(StudentDto studentDto) {
        Student student =studentMapper.dtoToEntity(studentDto);
                studentRepository1.delete(student);
    }

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("sfdsfd","sfsfsd","sgsdgdsg");
//        for(String s : strs){
//            System.err.println(s);
//        }
        // -> lambda
        strs.forEach(System.err::println);


        List<Student> students = new ArrayList<>();
        // get all name of student -> to list <String>

        List<String> strName = new ArrayList<>();
        for (Student s : students){
            strName.add(s.getFirstname());
        }
        List<String> strName1 = students.stream().map(Student::getFirstname).collect(Collectors.toList());





    }


    @Override
    public StudentDto getById(Long id) {
        Optional<List<Student>> optionalStudents =  studentRepository.getById(String.valueOf(id));
        if(optionalStudents.isPresent()){
            List<Student> students = optionalStudents.get();
            if(!CollectionUtils.isEmpty(students)){
                return studentMapper.entityToDto(students.get(0));
            }
        }
        return null;
    }




//    @Override
//    public List<StudentDto> searchByFirstName(String firstName) {
//        List<Student> students = studentRepository.searchByFirstName(firstName);
//        return students.stream()
//                .map(studentMapper::entityToDto)
//                .collect(Collectors.toList());
//    }



}
