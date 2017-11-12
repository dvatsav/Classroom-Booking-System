package Actors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import Supplementary.Course;

public class Student extends Users implements Serializable{
    private Map<Course, Calendar> courses;
    private String branch;

	@Override
	public String toString() {
		return "Student{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	private String rollNo;

	public Student() {

	}

	public Student(String firstName, String lastName, String phoneNumber, String email, String password, String type, LocalDate dob, String rollNo, String branch) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
        this.rollNo = rollNo;
        this.branch = branch;
    }
}
