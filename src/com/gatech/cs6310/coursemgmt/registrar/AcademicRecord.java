package com.gatech.cs6310.coursemgmt.registrar;

import com.gatech.cs6310.coursemgmt.university.Instructor;

public class AcademicRecord {

	public enum GRADE {
			A,
			B,
			C,
			D,
			F,
			_
		}
		
		private int courseID = 0;
		private Term term = null;
		private int year=0;
		private GRADE grade = GRADE.A;
		private Instructor instructor = null;
		private String instructorComments = "";

		public AcademicRecord(int courseID, 
						String grade, 
						Term term, 
						int year, 
						Instructor instructor, 
						String instructorComments) {
			this.courseID = courseID;
			if (grade.equals('_')) {
				this.grade = GRADE._;
			} else {
				this.grade = GRADE.valueOf(grade.toUpperCase());
			}
			this.term = term;
			this.year = year;
			this.instructor = instructor;
			this.instructorComments = instructorComments;
		}
		
		public int getCourseID () {
			return this.courseID;
		}
		
		public void setCourseID (int courseID){
			this.courseID = courseID;
		}
		public Term getTerm() {
			return this.term;
		}
		
		public int getYear() {
			return this.year;
		}
		
		public void setYear(int year) {
			this.year=year;
		}
		public void setTerm(Term term) {
			this.term = term;
		}
		
		public boolean isPassingGrade() {
			return GRADE.D.equals(grade) || GRADE.F.equals(grade) ? false : true;
		}
		
		public GRADE getGrade () {
			return this.grade;
		}
		
		public void setGrade(GRADE grade) {
			this.grade = grade;
		}
		
		public Instructor getInstructor() {
			return this.instructor;
		}
		
		public void setInstructor(Instructor instructor) {
			this.instructor=instructor;
		}
		
		public String getInstructorComments() {
			return this.instructorComments;
		}
		
		public void setInstructorComments(String instructorComments) {
			this.instructorComments = instructorComments;
		}
		
		public String toString() {
			
			if (instructorComments.isEmpty()) {
				return courseID+", "+grade.toString()+", "+term+"_"+year+", "+instructor.getUUID();
			}
			return courseID+", "+grade.toString()+", "+term+"_"+year+", "+instructor.getUUID()+", "+instructorComments;
		}
}
