package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.registrar.Course;
import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class CourseParser extends GenericCSVParser<Map<Integer,Course>>{

	private Map<Integer, Course> courses = new HashMap<Integer, Course>();
	
	@Override
	protected Map<Integer, Course> parse() throws IOException {
		String line = null;
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if (fields.length>0) {
        			Course course = new Course(Integer.parseInt(fields[0]), fields[1]);
        			courses.put(Integer.parseInt(fields[0]), course);
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

}
