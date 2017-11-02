package Actors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;
import Supplementary.Course;

public class Student extends Users implements Serializable{
    private Map<Course, Calendar> courses;

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	private String rollNo;

    public Student(String firstName, String lastName, String phoneNumber, String email, String password, String type, LocalDate dob) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
    }
}
