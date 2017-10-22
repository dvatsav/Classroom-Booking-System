package Utils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class Course {
    private String courseName;
    private String courseCode;
    private String instructor;
    private List<String> postConditions;
    private String acronym;
    private Calendar timing;
    private Map<Calendar, String> roomAlloted;
    private List<String> intendedAudience;
    private int semesterOfferedIn;
    private int creditsOffered;

    public String getCourseName() {return courseName;}
    public String getCourseCode() {return courseCode;}
}
