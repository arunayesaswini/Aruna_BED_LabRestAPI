package com.greatlearning.CollegeFest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.CollegeFest.Entity.Student;
import com.greatlearning.CollegeFest.repository.StudentRepository;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	StudentRepository studentRepository;
	
	//Adding new Student into Database
	@Override
	public Student addStudent(Student student) {
	
		return studentRepository.saveAndFlush(student);
	}
	
	//Editing details of the Student using id and saving the changes
	@Override
	public Student editStudent(long studentId, Student student) 
	{
		Student student_db=getStudent(studentId);
		student_db.setFirstName(student.getFirstName());
		student_db.setLastName(student.getLastName());
		student_db.setCourse(student.getCourse());
		student_db.setCountry(student.getCountry());
		
		return studentRepository.saveAndFlush(student_db);
	}

	@Override
	public Student getStudent(long studentId) {
		
		return studentRepository.findById(studentId).get();
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.findAll();
	}

	@Override
	public List<Student> searchByKeyword(String courseName) {
		
		return studentRepository.findByCourseContainingIgnoreCase(courseName);
	}

	@Override
	public void deleteStudent(long studentId) {
		
		studentRepository.deleteById(studentId);
	}
	

}
