package Supplementary;

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
    private String intendedAudience;
    private int classStrength;
    private int creditsOffered;

    public Course(String courseCode, String courseName, String instructor, String acronym, String intendedAudience, int classStrength) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.acronym = acronym;
        this.intendedAudience = intendedAudience;
        this.classStrength = classStrength;
    }

    public String getCourseName() {return courseName;}
    public String getCourseCode() {return courseCode;}

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setPostConditions(List<String> postConditions) {
        this.postConditions = postConditions;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setTiming(Calendar timing) {
        this.timing = timing;
    }

    public void setRoomAlloted(Map<Calendar, String> roomAlloted) {
        this.roomAlloted = roomAlloted;
    }

    public void setIntendedAudience(List<String> intendedAudience) {
        this.intendedAudience = intendedAudience;
    }

    public void setClassStrength(int classStrength) {
        this.classStrength = classStrength;
    }

    public void setCreditsOffered(int creditsOffered) {
        this.creditsOffered = creditsOffered;
    }


}
