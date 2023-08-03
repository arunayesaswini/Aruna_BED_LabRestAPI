package com.greatlearning.CollegeFest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.CollegeFest.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

	List<Student> findByCourseContainingIgnoreCase(String courseName);
	
	

}
