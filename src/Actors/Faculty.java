package Actors;
import Supplementary.Course;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * <h1>Faculty Class</h1>
 * <p>This type of user group represents the faculty which can introduce new courses.</p>
 */
public class Faculty extends Users implements Serializable {
    private ArrayList<Course> coursesTaught;

    public Faculty(String firstName, String lastName, String phoneNumber, String email, String password, String type, String dob) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
    }

    public Faculty(){}

    @Override
    public String toString() {
        return "Faculty{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
