package com.Paavan.SpringDataJPA.Service;

import com.Paavan.SpringDataJPA.Entity.Students;
import com.Paavan.SpringDataJPA.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public void addStudent(Students students) {
        studentRepository.save(students);
    }

    public List<Students> findAllStudents() {
        return studentRepository.findAll();
    }

    public List<Students> findStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    public Optional<Students> findStudentsById(Integer id) {
        return studentRepository.findById(id);
    }

    public List<Students> findAllPresentStudents() {
        return studentRepository.findByPresentTrue();
    }

    public List<Students> findAllAbsentPresentStudents() {
        return studentRepository.findByPresentFalse();
    }

    @Transactional
    public void updateStudentsName(Integer id, String newName) {

        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        students.setName(newName);
    }

    public void markPresent(Integer id, Boolean present) {

        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        students.setPresent(present);
    }

    public void removeStudentByRoll(Integer roll) {
        studentRepository.deleteByRoll(roll);
    }

    public void removeStudentByName(String name) {
        studentRepository.deleteByName(name);
    }

    public void removalAllStudents() {
        studentRepository.deleteAll();
    }
}
