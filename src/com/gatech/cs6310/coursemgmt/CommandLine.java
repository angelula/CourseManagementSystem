package com.gatech.cs6310.coursemgmt;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.registrar.Course;
import com.gatech.cs6310.coursemgmt.registrar.Registrar;
import com.gatech.cs6310.coursemgmt.simulation.Action;
import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.university.Student;
import com.gatech.cs6310.coursemgmt.util.parser.csv.ActionParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.CourseParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.EligibleParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.InstructorParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.PrereqParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.StudentParser;
import com.gatech.cs6310.coursemgmt.util.parser.csv.TermParser;



public class CommandLine {

	private static final String actionscsv  	= "actions.csv";
	private static final String coursescsv  	= "courses.csv";
	private static final String eligiblecsv 	= "eligible.csv";
	private static final String instructorscsv 	= "instructors.csv";
	private static final String prereqscsv		= "prereqs.csv";
	private static final String studentscsv		= "students.csv";
	private static final String termscsv		= "terms.csv";
		
	public static void main(String args[]) {
		run(System.out);
	}
	
	public static void run (PrintStream out) {
		        
	    Map<String, File> files = new HashMap<String, File>();
	    File actual = new File(".");
        for( File f : actual.listFiles()){
        	if (f.getName().toLowerCase().endsWith((".csv"))) {
        		files.put(f.getName(),f);
        	}
        }

        if (files.get(actionscsv)==null) {
        	out.println(actionscsv+" file not found in current working directory.");
        	System.exit(0);
        }

        if (files.get(coursescsv)==null) {
        	out.println(coursescsv+" file not found in current working directory.");
        	System.exit(0);
        }

        if (files.get(eligiblecsv)==null) {
        	out.println(eligiblecsv+" file not found in current working directory.");
        	System.exit(0);
        }

        if (files.get(instructorscsv)==null) {
        	out.println(instructorscsv+" file not found in current working directory.");
        	System.exit(0);
        }

        
        if (files.get(prereqscsv)==null) {
        	out.println(prereqscsv+" file not found in current working directory.");
        	System.exit(0);
        }

        if (files.get(studentscsv)==null) {
        	out.println(studentscsv+" file not found in current working directory.");
        	System.exit(0);
        }

        if (files.get(termscsv)==null) {
        	out.println(termscsv+" file not found in current working directory.");
        	System.exit(0);
        }
        
        //Parse all the files
        Map<Integer, Student> students = null;
        StudentParser sParser = new StudentParser();
        try {
        	students = sParser.parse(files.get(studentscsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(studentscsv)+" "+e);
        	System.exit(0);
        }
        
        Map<Integer, Instructor> instructors = null;
        InstructorParser iParser = new InstructorParser();
        try {
        	instructors = iParser.parse(files.get(instructorscsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(instructorscsv)+" "+e);
        	System.exit(0);
        }

	    Map<Integer, Course> courses = null;
        CourseParser cParser = new CourseParser();
        try {
        	courses = cParser.parse(files.get(coursescsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(coursescsv)+" "+e);
        	System.exit(0);
        }
        
        PrereqParser pParser = new PrereqParser();
        pParser.setCourseList(courses);
        try {
        	courses = pParser.parse(files.get(prereqscsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(prereqscsv)+" "+e);
        	System.exit(0);
        }
                
        EligibleParser eParser = new EligibleParser();
        eParser.setCourses(courses);
        eParser.setInstructors(instructors);
        try {
        	instructors = eParser.parse(files.get(eligiblecsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(eligiblecsv)+" "+e);
        	System.exit(0);
        }
                
        TermParser tParser = new TermParser();
        tParser.setCourses(courses);
        try {
        	courses = tParser.parse(files.get(termscsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(termscsv)+" "+e);
        	System.exit(0);
        }
        
        List<Action> actions = null;
        ActionParser aParser = new ActionParser();
        try {
        	actions = aParser.parse(files.get(actionscsv));
        } catch (IOException e) {
        	out.println("FAILED to load "+files.get(actionscsv)+" "+e);
        	System.exit(0);
        }
        
        //Process the system actions
        Registrar registrar = Registrar.getInstance();
        registrar.setStudents(students);
        registrar.setInstructors(instructors);
        registrar.setCourses(courses);
        registrar.setActions(actions);
        registrar.process();
	}
}
