package org.truongsolo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.truongsolo.project.dto.StudentDto;
import org.truongsolo.project.entity.Student;
import org.truongsolo.project.service.StudentService;
import org.truongsolo.project.service.impl.StudentServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    StudentService studentService = new StudentServiceImpl();
    // api/v1/student/1  .. and httpMethod  = GET
    @GetMapping(value = "/student/{studentId}")
    public StudentDto get(@PathVariable Long studentId){
        return studentService.getById(studentId);
    }
    @GetMapping(value = "/students")
    public List<StudentDto> finadall(){
        return studentService.findAll();
    }
    @PostMapping(value = "/addStudent")
    public void addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);}

    @PutMapping(value = "/updateStudent/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        studentDto.setId(id); // Ensure ID is set correctly
        studentService.updateStudent(studentDto);}

//    @DeleteMapping("/deleteStudent/{id}")
//    public ResponseEntity<String> deleteStudent(@PathVariable Long id,@RequestBody StudentDto studentDto) {
//        try {studentDto.setId(id);
//            studentService.deleteStudent(studentDto);
//            return ResponseEntity.ok("Student with ID " + id + " deleted successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to delete student with ID " + id + ": " + e.getMessage());
//        }
//    }
    @GetMapping(value ="/delete/{id}")
    public void deleteStudent(@PathVariable Long id,@RequestBody StudentDto studentDto){
        studentDto.setId(id);
        studentService.updateStudent(studentDto);
    }

}




