package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
       return studentRepository.findAll();
    }


    public void addStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        Boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student id "+studentId+" does not exist");
        }
        studentRepository.deleteById(studentId);
    }


    public void updateStudent(Long studentId, Student student) {
        Student savedStudent = studentRepository.findById(studentId).get();
        savedStudent.setName(student.getName());
        savedStudent.setEmail(student.getEmail());
        savedStudent.setAge(student.getAge());
        studentRepository.save(savedStudent);
    }
}
