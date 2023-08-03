package com.greatlearning.CollegeFest.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.CollegeFest.Entity.Student;
import com.greatlearning.CollegeFest.service.CollegeService;

@Controller
@RequestMapping("/CollegeFest")
public class CollegeFestController {

	@Autowired
	CollegeService collegeService;

	@RequestMapping("/studentlist")
	// will map to students-list page and show list of students enrolled

	public String listStudents(Model theModel) {
		List<Student> students_list = collegeService.getAllStudents();
		theModel.addAttribute("students", students_list);
		return "college/students-list";
	}

	// will map to student-form page and will create the new student
	@RequestMapping("/new")
	public String createTicket(Model theModel) {
		Student newStudent = new Student();
		theModel.addAttribute("student", newStudent);
		return "college/student-form";
	}

	// will map to update-student page and will be able to edit the details in the
	// student
	@RequestMapping("/edit")
	public String updateStudent(@RequestParam("studentId") long sid, Model theModel) {

		// getting the student details from the service

		Student student_db = collegeService.getStudent(sid);

		// set student as model attribute to pre populate
		theModel.addAttribute("student", student_db);

		// sending to student form

		return "college/update-student";
	}

	// will save the student details and redirect to students-list page
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student save_student) {
		collegeService.addStudent(save_student);
		return "redirect:/CollegeFest/studentlist";
	}

	// will update the changes made in student and will navigate to students-list
	@PostMapping("/update")
	public String updateTicket(@ModelAttribute("student") Student update_student) {
		collegeService.editStudent(update_student.getId(), update_student);
		return "redirect:/CollegeFest/studentlist";
	}

	// will delete selected student
	@PostMapping("/delete")
	public String deleteTicket(@RequestParam("studentId") long sid) {
		collegeService.deleteStudent(sid);
		return "redirect:/CollegeFest/studentlist";
	}

	// will show the selected student
	@RequestMapping("/view")
	public String viewStudent(@RequestParam("studentId") long sid, Model theModel) {
		Student viewStudent = collegeService.getStudent(sid);
		theModel.addAttribute("student", viewStudent);
		return "college/view-student";
	}

	// will show the students with their course name
	@RequestMapping("/search")
	public String searchStudent(@RequestParam("courseName") String course, Model theModel) {
		List<Student> list = collegeService.searchByKeyword(course);
		theModel.addAttribute("students", list);
		return "college/students-list";
	}
	
	//user cannot access the page
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}


}
