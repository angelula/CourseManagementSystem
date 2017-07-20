package com.gatech.cs6310.coursemgmt.university;

import java.util.ArrayList;
import java.util.List;

import com.gatech.cs6310.coursemgmt.registrar.AcademicRecord;

public class Student extends UniversityPerson{

	private List<AcademicRecord> transcript = null;
	
	public Student(int UUID, String name, String homeAddress, String phoneNumber) {
		super(UUID, name, homeAddress, phoneNumber);
		transcript = new ArrayList<AcademicRecord>();
	}
	
	public boolean coursePreviouslyPassed(int courseID) {
		List<AcademicRecord> arList = getClassRecord(courseID);
		if (arList == null) {
			return false;
		}
		
		for (AcademicRecord ar : arList) {
			if (ar.isPassingGrade()) {
				return true;
			}
		}
		return false;
	}
	
	public List<AcademicRecord> getClassRecord(int courseID) {
		List<AcademicRecord> arList = new ArrayList<AcademicRecord>();
		for (AcademicRecord ar : transcript) {
			if (ar.getCourseID()==courseID) {
				arList.add(ar);
			}
		}
		
		if (arList.size()==0) {
			return null;
		}
		return arList;
	}

	public List<AcademicRecord> getClassRecord() {
		return this.transcript;
	}
	
	public void addAcademicRecord(AcademicRecord record) {
		transcript.add(record);
	}
}
