package com.gatech.cs6310.coursemgmt.university;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.gatech.cs6310.coursemgmt.registrar.Course;

public class Instructor extends UniversityPerson {
	
	private Set<Integer> skills = null;
	
	private Course course = null;
	
	private boolean isHired = false;
	
	private int studentsPerInstructor = 3;

	public Instructor( int UUID, String name, String homeAddress, String phoneNumber) {
		super(UUID, name, homeAddress, phoneNumber);
		skills = new HashSet<Integer>();
	}

	
	public void selectCourseToTeach(Course course) {
		if (!isHired) {
			System.out.println("# ERROR: instructor is not working");
			return;
		}
		if (!findSkill(course.getCourseID())) {
			System.out.println("# ERROR: instructor is not eligible to teach this course");
			return;
		}
		if (this.course!=null) {
			System.out.println("# ERROR: instructor is already teaching a different course");
			return;
		}
		System.out.println("# instructor "+UUID+" is assigned to course "+course.getCourseID());
		course.setSeatsAvailable(studentsPerInstructor);
		this.course = course;
	}
		
	public void hireInstructor() {
		isHired = true;
	}
	
	public	void takeLeave() {
		isHired = false;
	}
	
	public boolean isHired() {
		return this.isHired;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Course getCourse() {
		return this.course;
	}
	
	public void clearCourse() {
		this.course = null;
	}
	
	public boolean findSkill(Integer skill) {
		return this.skills.contains(skill);
	}
	
	public void addSkill(Integer skill) {
		this.skills.add(skill);
	}
}
