package com.gatech.cs6310.coursemgmt.simulation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Action {
	
	public enum ActionType {
		start_sim,
		stop_sim,
		next_term,
		hire,
		take_leave,
		teach_course,
		assign_grade,
		request_course,
		instructor_report,
		student_report
	}
	
	private ActionType action = null;
	private List<String> parameters = null;
	
	public Action(ActionType action) {
		this.action = action;
	}
	
	public Action(ActionType action, List<String> parameters) {
		this.action = action;
		this.parameters = parameters;
	}
	
	public ActionType getActionType() {
		return this.action;
	}
	
	public List<String> getParameters() {
		return parameters;
	}
	
	public String toString() {
		return action.toString();
	}
}
