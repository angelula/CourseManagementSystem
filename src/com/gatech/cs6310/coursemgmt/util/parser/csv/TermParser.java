package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.registrar.Course;
import com.gatech.cs6310.coursemgmt.registrar.Term;
import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class TermParser extends GenericCSVParser<Map<Integer, Course>>{

	public Map<Integer, Course> courses = null;
	@Override
	protected Map<Integer, Course> parse() throws IOException {
		String line = null;
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if (fields.length>0) {
        			int courseID = Integer.parseInt(fields[0]);
        			String term = fields[1];
        			Course c = courses.get(courseID);
        			if (c==null) {
        				System.out.println("Term not set: The course id "+courseID+" does not exist in course list.");
        				continue;
        			}
        			if (Term.FALL.toString().toLowerCase().equals(term.toLowerCase())) {
        				c.addTerm(Term.FALL);
        			} else if (Term.SPRING.toString().toLowerCase().equals(term.toLowerCase())) {
        				c.addTerm(Term.SPRING);
        			} else if (Term.SUMMER.toString().toLowerCase().equals(term.toLowerCase())) { 
        				c.addTerm(Term.SUMMER);
        			} else if (Term.WINTER.toString().toLowerCase().equals(term.toLowerCase())) {
        				c.addTerm(Term.WINTER);
        			} else {
        				System.out.println("Term not set: The term is not properly formatted.");
        			}
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
	
	public void setCourses(Map<Integer, Course> courses) {
		this.courses = courses;
	}
}
