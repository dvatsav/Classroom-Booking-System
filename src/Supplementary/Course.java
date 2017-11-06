package Supplementary;

import java.util.*;

public class Course {
    private String courseName;
    private String courseCode;
    private String instructor;
    private ArrayList<String> postConditions = new ArrayList<>();
    private String acronym;
    private Calendar timing;
    private Map<String, String> timeAndRoom = new HashMap<>();
    private String intendedAudience;
    private String classStrength;
    private int creditsOffered;
    private boolean mandatory;
    private String preReq;

    public Course(boolean Mandatory, String courseName, String courseCode, String instructor, int credits, String acronym, String preReq) {
        this.mandatory = Mandatory;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructor = instructor;
        this.acronym = acronym;
        this.creditsOffered = credits;
        this.preReq = preReq;
    }

    public String getCourseName() {return courseName;}
    public String getCourseCode() {return courseCode;}

    public void getPostConditions() {
        for (int i = 0 ; i < postConditions.size() ; ++i) {
            System.out.println(postConditions.get(i));
        }
    }

    public void addTiming(String Day, String timing) {
        timeAndRoom.put(Day, timing);
    }

    public void addPostCondition(String condition) {
        postConditions.add(condition);
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setPostConditions(String postCondition) {
        this.postConditions.add(postCondition);
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setTiming(Calendar timing) {
        this.timing = timing;
    }

    public void setIntendedAudience(String intendedAudience) {
        this.intendedAudience = intendedAudience;
    }

    public void setClassStrength(String classStrength) {
        this.classStrength = classStrength;
    }

    public void setCreditsOffered(int creditsOffered) {
        this.creditsOffered = creditsOffered;
    }

    @Override
    public String toString() {
        return "Course code: " + courseCode + " Course name: " + courseName + " instructor: " + instructor + " precondition " + preReq;
    }
}
