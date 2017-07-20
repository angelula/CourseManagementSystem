package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.university.Student;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class StudentParser extends GenericCSVParser <Map<Integer, Student>>{
	
	private Map<Integer, Student> studentList = new HashMap<Integer, Student>();
	@Override
	protected Map<Integer, Student> parse() throws IOException {
		Student student = null;
		String line = null;
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if(fields.length>0) {
        			student = new Student(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
        			studentList.put(Integer.parseInt(fields[0]), student);
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
		return studentList;
	}
}
