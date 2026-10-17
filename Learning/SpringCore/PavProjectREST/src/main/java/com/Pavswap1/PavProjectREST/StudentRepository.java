package com.Pavswap1.PavProjectREST;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;

@Repository
public class StudentRepository {

    private ArrayList<String> studentList = new ArrayList<>(Arrays.asList("Anand", "Bakshi", "Cherry"));

    public ArrayList<String> getStudentList() {
        return studentList;
    }

    public String getStudentByIndex(int index) {
        return studentList.get(index);
    }

    public void addStudent(String name) {
        this.studentList.add(name);
    }

    public void setStudentListByIndex(int index, String name) {
        this.studentList.set(index, name);
    }

    public void deleteStudentByName(String name) {
        this.studentList.remove(name);
    }
}
