package com.gatech.cs6310.coursemgmt.util.parser.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gatech.cs6310.coursemgmt.simulation.Action;
import com.gatech.cs6310.coursemgmt.simulation.Action.ActionType;
import com.gatech.cs6310.coursemgmt.util.parser.GenericCSVParser;

public class ActionParser extends GenericCSVParser<List<Action>>{

	@Override
	protected List<Action> parse() throws IOException {
		String line = null;
		List<Action> actions = new ArrayList<Action>();
		try {
            while ((line = readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(cvsSplitBy);
        		if(fields.length>0) {
        			String action = fields[0];
        			List<String> parameters = null;
        			if (fields.length>1) {
        				parameters = new ArrayList<>();
        				for(int i=1; i<fields.length; i++) {
        					parameters.add(fields[i]);
        				}
        			}

        			ActionType type = null;
        			if (ActionType.start_sim.toString().toLowerCase().equals(action)) {
        				type = ActionType.start_sim;
        			} else if (ActionType.stop_sim.toString().toLowerCase().equals(action)) {
        				type = ActionType.stop_sim;
        			} else if (ActionType.next_term.toString().toLowerCase().equals(action)) {
        				type = ActionType.next_term;
        			} else if (ActionType.hire.toString().toLowerCase().equals(action)) {
        				type = ActionType.hire;
        			} else if (ActionType.take_leave.toString().toLowerCase().equals(action)) {
        				type = ActionType.take_leave;
        			} else if (ActionType.teach_course.toString().toLowerCase().equals(action)) {
        				type = ActionType.teach_course;
        			} else if (ActionType.assign_grade.toString().toLowerCase().equals(action)) {
        				type = ActionType.assign_grade;
        			} else if (ActionType.request_course.toString().toLowerCase().equals(action)) {
        				type = ActionType.request_course;
        			} else if (ActionType.instructor_report.toString().toLowerCase().equals(action)) {
        				type = ActionType.instructor_report;
        			} else if (ActionType.student_report.toString().toLowerCase().equals(action)) {
        				type = ActionType.student_report;
        			} else {
        				System.out.println("Action not set: The action "+action+" is not valid.");
        				continue;
        			}
        			actions.add(new Action(type, parameters));
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
		return actions;
	}

}
