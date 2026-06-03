package com.Paavan.SpringDataJPA.repository;

import com.Paavan.SpringDataJPA.Entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {

    List<Students> findByName(String name);

    List<Students> findByPresentTrue();

    List<Students> findByPresentFalse();

    long deleteByRoll(Integer id);

    long deleteByName(String name);
}
