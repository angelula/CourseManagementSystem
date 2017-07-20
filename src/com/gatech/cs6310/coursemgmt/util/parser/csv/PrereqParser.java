package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.registrar.Course;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class PrereqParser extends GenericCSVParser<Map<Integer, Course>>{

	private Map<Integer, Course> courses = null;
	@Override
	protected Map<Integer, Course> parse() throws IOException {
	
		try {
			String line = null;
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if (fields.length>0) {
        			int prereq = Integer.parseInt(fields[0]);
        			int id = Integer.parseInt(fields[1]);
        			Course p = courses.get(prereq);
        			Course c = courses.get(id);
        			
        			if (p==null) {
        				System.out.println("Prerequisite not set: The prerequisite "+prereq+" does not exist in course list.");
        				continue;
        			}
        			
        			if (c==null) {
        				System.out.println("Prerequisite not set: The prerequisite "+prereq+" does not exist in course list.");
        				continue;
        			}
        			c.addPrerequisite(p.getCourseID());
        			courses.put(c.getCourseID(), c);
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
		return courses;
	}

	public void setCourseList(Map<Integer, Course> courses) {
		this.courses = courses;
	}
}
