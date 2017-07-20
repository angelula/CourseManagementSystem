package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.registrar.Course;
import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.university.Student;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class EligibleParser extends GenericCSVParser<Map<Integer, Instructor>>{

	private Map<Integer, Instructor> instructors = null;
	private Map<Integer, Course> courses = null;
	@Override
	protected Map<Integer, Instructor> parse() throws IOException {
		String line = null;
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if (fields.length>0) {
        			int instID = Integer.parseInt(fields[0]);
        			int courseID = Integer.parseInt(fields[1]);
        			
        			Instructor i = instructors.get(instID);
        			Course c = courses.get(courseID);
        			
        			if (i==null) {
        				System.out.println("Prerequisite not set: The instructor "+instID+" does not exist in course list.");
        				continue;
        			}
        			
        			if (c==null) {
        				System.out.println("Prerequisite not set: The course "+courseID+" does not exist in course list.");
        				continue;
        			}
        			i.addSkill(courseID);
        			instructors.put(i.getUUID(), i);
        		}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (getReader() != null) {
                try {
                	closeReader();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return instructors;
	}
	
	public void setInstructors(Map<Integer, Instructor> instructors) {
		this.instructors = instructors;
	}
	
	public void setCourses (Map<Integer, Course> courses) {
		this.courses = courses;
	}
}
