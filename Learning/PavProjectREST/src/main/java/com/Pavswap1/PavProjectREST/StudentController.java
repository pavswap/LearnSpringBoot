package com.Pavswap1.PavProjectREST;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/StudentDatabase")
public class StudentController {

    StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Home Page : http://localhost:8080/StudentDatabase/home
    @GetMapping("/home")
    public String home() {
        return "This is a Simple Student Database REST API";
    }

    @GetMapping("/studentList")
    ArrayList<String> getAllStudent() {
        return studentRepository.getStudentList();
    }

    @GetMapping("/{index}")
    String getStudent(@PathVariable int index) {

        Optional<String> name = Optional.ofNullable(studentRepository.getStudentByIndex(index));

        if(name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return studentRepository.getStudentByIndex(index);
    }

    @PostMapping("")
    void addStudent(@RequestBody String name) {
        studentRepository.addStudent(name);
    }

    @PutMapping("/{index}")
    void setStudent(@PathVariable int index, @RequestBody String name) {
        studentRepository.setStudentListByIndex(index, name);
    }
}
