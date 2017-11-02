package Actors;
import Supplementary.Course;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Faculty extends Users implements Serializable {
    private ArrayList<Course> coursesTaught;

    public Faculty(String firstName, String lastName, String phoneNumber, String email, String password, String type, LocalDate dob) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
    }


    //can also check whether a course can be selected by viewing a faculty profile and selecting course

}
