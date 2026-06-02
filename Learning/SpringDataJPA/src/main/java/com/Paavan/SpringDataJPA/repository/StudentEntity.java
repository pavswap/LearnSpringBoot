package com.Paavan.SpringDataJPA.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_new")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;
}

/*
CREATE TABLE student_new (id INT PRIMARY KEY, name VARCHAR(20) NOT NULL)
 */