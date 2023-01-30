package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
//      return List.of(
//              new Student("Dero","dero@gmail.com", 24),
//              new Student("Dero","dero@gmail.com", 24),
//              new Student("Dero","dero@gmail.com", 24)
//      );
        return studentService.getStudents();
    }

    @PostMapping("/student")
    public void registerStudents(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @DeleteMapping(path = "/student/delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "/student/update/{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId,
                              @RequestBody Student student){
        studentService.updateStudent(studentId, student);
    }

}
