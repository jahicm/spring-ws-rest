package com.spring.boot.run.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.run.models.Student;

public interface StudentRepository  extends JpaRepository<Student, Long>  {

}
