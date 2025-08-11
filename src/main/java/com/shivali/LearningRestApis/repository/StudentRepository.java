package com.shivali.LearningRestApis.repository;
import com.shivali.LearningRestApis.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Student, Long> : Student : Entity Class and Long : ID Type

public interface StudentRepository extends JpaRepository<Student, Long>{

}
