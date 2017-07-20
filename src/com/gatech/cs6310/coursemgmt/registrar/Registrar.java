package com.gatech.cs6310.coursemgmt.registrar;

import java.util.List;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.simulation.Action;
import com.gatech.cs6310.coursemgmt.simulation.Action.ActionType;
import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.university.Student;

public class Registrar {

	private static Registrar registrar = null;

	private int year=0;
	private Term term = Term.FALL;
	private Map<Integer, Course> courses = null;
	private Map<Integer, Student> students = null;
	private Map<Integer, Instructor> instructors = null;
	private List<Action> actions = null;
	
	private boolean simulationStarted = false;

	static {
		registrar = new Registrar();
	}
	
	private Registrar() {}
	
	public static Registrar getInstance() {
		return registrar;
	}
	
	public void process() {
		for (Action action: actions) {
			List<String> parameters = action.getParameters();
			if (!simulationStarted && ActionType.start_sim.equals(action.getActionType())) {
				int year = Integer.parseInt(parameters.get(0));
				setYear(year);
				simulationStarted=true;
				System.out.println(action);
				System.out.println("# begin "+term+"_"+year+" term");
			} else if (simulationStarted && ActionType.stop_sim.equals(action.getActionType())) {
				simulationStarted = false;
				System.out.println(action);
				System.out.println("# end "+term+"_"+year+" term");
			} else if (simulationStarted && ActionType.next_term.equals(action.getActionType())) {
				this.term = term.getNextTerm();
				// after summer term increment year.
				if (term.equals(Term.FALL)){
					year+=1;
				}
				for (Instructor inst : instructors.values()) {
					inst.clearCourse();
				}
				for (Course course : courses.values()) {
					course.startOfTerm();
				}
				
				System.out.println(action);
				System.out.println("# begin "+term+"_"+year+" term");
			} else if (simulationStarted && ActionType.hire.equals(action.getActionType())) {
				int instID = Integer.parseInt(parameters.get(0));
				Instructor inst = instructors.get(instID);
				if (inst == null) {
					System.out.println("# ERROR: Instructor ID does not exist");
					continue;
				}
				inst.hireInstructor();
				System.out.println(action+","+instID);
				System.out.println("# instructor "+instID+" now hired");
			} else if (simulationStarted && ActionType.take_leave.equals(action.getActionType())) {
				int instID = Integer.parseInt(parameters.get(0));
				Instructor inst = instructors.get(instID);
				if (inst == null) {
					System.out.println("# ERROR: Instructor ID does not exist");
					continue;
				}
				inst.takeLeave();
				System.out.println(action+","+instID);
				System.out.println("# instructor "+instID+" now on leave");
			} else if (simulationStarted && ActionType.teach_course.equals(action.getActionType())) {
				int instID = Integer.parseInt(parameters.get(0));
				int courseID = Integer.parseInt(parameters.get(1));
				Instructor inst = instructors.get(instID);
				if (inst == null) {
					System.out.println("# ERROR: Instructor ID does not exist");
					continue;
				}
				Course course = courses.get(courseID);
				if (course == null) {
					System.out.println("# ERROR: Course ID does not exist");
					continue;
				}
				System.out.println(action+","+instID+","+courseID);
				inst.selectCourseToTeach(course);
				
			} else if (simulationStarted && ActionType.assign_grade.equals(action.getActionType())) {
				int studID = Integer.parseInt(parameters.get(0));
				Student student = students.get(studID);
				if (student == null) {
					System.out.println("# ERROR: Student ID does not exist");
					continue;
				}
				int courseID = Integer.parseInt(parameters.get(1));
				Course course = courses.get(courseID);
				if (course == null) {
					System.out.println("# ERROR: Course ID does not exist");
					continue;
				}
				String grade = parameters.get(2);
				int instID = Integer.parseInt(parameters.get(3));
				Instructor inst = instructors.get(instID);
				if (inst == null) {
					System.out.println("# ERROR: Instructor ID does not exist");
					continue;
				}

				String instComments = "";
				AcademicRecord record;
				if (parameters.size()>4) {
					instComments = parameters.get(4);
					record = new AcademicRecord(courseID, grade, term, year, inst, instComments);
					System.out.println(action+","+studID+","+courseID+","+grade.toUpperCase()+","+instID+","+instComments);
				} else {
					record = new AcademicRecord(courseID, grade, term, year, inst, instComments);
					System.out.println(action+","+studID+","+courseID+","+grade.toUpperCase()+","+instID);
				}
				student.addAcademicRecord(record);
				System.out.println("# grade recorded");
			} else if (simulationStarted && ActionType.request_course.equals(action.getActionType())) {
				int studID = Integer.parseInt(parameters.get(0));	
				int courseID = Integer.parseInt(parameters.get(1));
				Student student = students.get(studID);
				if (student == null) {
					System.out.println("# ERROR: Student ID does not exist");
					continue;
				}
				Course course = courses.get(courseID);
				if (course == null) {
					System.out.println("# ERROR: Course ID does not exist");
					continue;
				}

				System.out.println(action+","+studID+","+courseID);
				if (requestEnrollment(student, course)) {
					course.enrollStudent(student);
					System.out.println("# enrolled");
				}
			} else if (simulationStarted && ActionType.instructor_report.equals(action.getActionType())) {
				int instID = Integer.parseInt(parameters.get(0));	
				Instructor inst = instructors.get(instID);
				if (inst == null) {
					System.out.println("# ERROR: Instructor ID does not exist");
					continue;
				}
				System.out.println(action+","+instID);
				System.out.println("# instructor, "+inst.getName());
				Course course = inst.getCourse();
				if (course!=null) {
					System.out.println("# "+course.getCourseID()+", "+course.getTitle());
				}
			} else if (simulationStarted && ActionType.student_report.equals(action.getActionType())) {
				int studID = Integer.parseInt(parameters.get(0));	
				Student student = students.get(studID);
				if (student == null) {
					System.out.println("# ERROR: Student ID does not exist");
					continue;
				}
				System.out.println(action+","+studID);
				System.out.println("# student, "+student.getName());
				System.out.println(studID+": "+student.getName()+", "+student.getHomeAddress()+", "+student.getPhoneNumber());
				for (AcademicRecord ar : student.getClassRecord()) {
					System.out.println(ar);
				}
			} else {
				System.out.println("Simulation not started: The action "+action+" is not available.");
				continue;
			}
		}
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
		
	public void setCourses(Map<Integer, Course> courses) {
		this.courses = courses;
	}
	
	public void setStudents(Map<Integer, Student> students) {
		this.students = students;
	}
	
	public void setInstructors(Map<Integer, Instructor> instructors) {
		this.instructors = instructors;
	}
	
	public void setActions (List<Action> actions) {
		this.actions = actions;
	}
	
	private boolean hasAvailableSeats(int courseID) {
		Course course = courses.get(courseID);
		return course.getSeatsAvailable()>0;
	}
	
	private boolean hasPrerequisites(int UUID, int courseID) {
		Student student = students.get(UUID);
		Course course = courses.get(courseID);
		for (Integer prereqID : course.getPrerequisite()) {
			if (!student.coursePreviouslyPassed(prereqID)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean requestEnrollment(Student student, Course course) {
		int UUID = student.getUUID();
		int courseID = course.getCourseID();
		
		if (student.getClassRecord(courseID)!=null 
				&& student.coursePreviouslyPassed(courseID)) {
			System.out.println("# not enrolled: course already passed before");
			return false;
		}
		
		if (course.isStudentEnrolled(UUID)) {
			System.out.println("# student already enrolled in course");
			return false;
		}
		
		if (!course.isCourseOffered(term)){
			System.out.println("# not enrolled: course not being offered this term");
			return false;
		}
		
		if (!hasPrerequisites(UUID, courseID)) {
			System.out.println("# not enrolled: missing prerequisites");
			return false;
		}
		
		if (!hasAvailableSeats(courseID)) {
			System.out.println("# not enrolled: no available seats");
			return false;
		}
		
		return true;
	}	
}
