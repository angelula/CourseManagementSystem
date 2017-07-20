package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gatech.cs6310.coursemgmt.university.Instructor;
import com.gatech.cs6310.coursemgmt.university.Student;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class InstructorParser extends GenericCSVParser<Map<Integer, Instructor>>{

	private Map<Integer, Instructor> instructorList = new HashMap<Integer, Instructor>();

	@Override
	protected Map<Integer, Instructor> parse() throws IOException {
		Instructor instructor = null;
		String line = null;
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if(fields.length>0) {
        			instructor = new Instructor(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3]);
        			instructorList.put(Integer.parseInt(fields[0]),instructor);
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
		return instructorList;
	}
}
