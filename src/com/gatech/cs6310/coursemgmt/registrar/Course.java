package com.gatech.cs6310.coursemgmt.registrar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gatech.cs6310.coursemgmt.university.Student;

public class Course {
	
	 private String title = "";
	 private String description = "";
	 private int courseID = 0;
	 private int seatsPerCourse = 0;
	 private List<Student> students = null;
	 private List<Term> terms;
	 private Set<Integer> prerequisites = null;
	
	 public Course(
				  int id, 
				  String title 
	 ) {
		this.courseID = id;
		this.title = title;
		this.terms = new ArrayList<Term>();
		this.prerequisites = new HashSet<Integer>();
		this.students = new ArrayList<Student>();
	 }

	 public void startOfTerm() {
		 this.seatsPerCourse=0;
		 this.students = new ArrayList<Student>();
	 }
	 
	 public void addPrerequisite(Integer courseID) {
		 prerequisites.add(courseID);
	 }
 
	 public List<Integer> getPrerequisite() {
		 return new ArrayList<Integer>(prerequisites);
	 }
 
	 public void setTitle (String title) {
		 this.title = title;
	 }
	 
	 public String getTitle() {
		 return this.title;
	 }
	 
	 public void setDescritpion(String description) {
		 this.description = description;
	 }
	 
	 public void setCourseID (int courseID) {
		 this.courseID = courseID;
	 }
	 
	 public int getCourseID () {
		 return this.courseID;
	 }
	
	 public void addTerm(Term term) {
		 this.terms.add(term);
	 }
	 
	 public void addTerm(Collection<Term> term) {
		 this.terms = new ArrayList<Term>(term);
	 }
	 
	 public boolean isCourseOffered(Term term) {
		 for(Term t : terms) {
			 if (term.equals(t)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 	 
	 public int getSeatsAvailable() {
		if (students==null) {
			return this.seatsPerCourse;
		}
		return this.seatsPerCourse-students.size();
	 }
	
	 public void setSeatsAvailable(int seatsAvailable) {
		this.seatsPerCourse += seatsAvailable;
	 }
	 
	 public void enrollStudent(Student student) {
		 this.students.add(student);
	 }
	 
	 public boolean isStudentEnrolled(int UUID) {
		 for (Student s : students) {
			 if (s.getUUID() == UUID)  {
				 return true;
			 }
		 }
		 return false;
	 }
	 
}

