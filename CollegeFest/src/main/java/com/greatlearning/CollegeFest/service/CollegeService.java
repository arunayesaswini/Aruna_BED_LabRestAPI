package com.greatlearning.CollegeFest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.CollegeFest.Entity.Student;


@Service
public interface CollegeService {

	Student addStudent(Student student);

	Student editStudent(long studentId, Student student);

	Student getStudent(long studentId);

	List<Student> getAllStudents();

	List<Student> searchByKeyword(String keyword);

	void deleteStudent(long studentId);

}
