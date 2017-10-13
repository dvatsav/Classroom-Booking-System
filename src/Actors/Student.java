package Actors;

import java.util.Calendar;
import java.util.Map;
import Utils.Course;

public class Student extends Users {
    private Map<Course, Calendar> courses;

    @Override
    public String getEmail() {
        return email;
    }
}
