package Supplementary;

import java.util.*;

public class Course {
    private String courseName;
    private String courseCode;
    private String instructor;
    public ArrayList<String> postConditions = new ArrayList<>();
    private String acronym;
    public ArrayList<LinkedHashMap<String, String>> timeAndRoom = new ArrayList<>();
    public String getInstructor() {
        return instructor;
    }
    public int getCreditsOffered() {
        return creditsOffered;
    }
    private String postCondition = "";
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

    public void convertPostToString() {
        for (int i = 0 ; i < postConditions.size() ; ++i) {
            postCondition += postConditions.get(i) + "\n";
        }
    }

    public String getCourseName() {return courseName;}
    public String getCourseCode() {return courseCode;}
    public String getPreReq() {return preReq;}
    public String getPostCondition() {return postCondition;}

    public void getPostConditions() {
        for (int i = 0 ; i < postConditions.size() ; ++i) {
            System.out.println(postConditions.get(i));
        }
    }

    public void addTiming(String Day, String timing) {
        if (timing.equals(" ") || timing.equals("")) return;
        if (!Day.equals("Tutorial") && !Day.equals("Labs")){
            timing = timing.substring(timing.indexOf("@") + 1, timing.indexOf("$"));
            String starttime = timing.substring(timing.indexOf("@") + 1, timing.indexOf("-"));
            timing = timing.substring(timing.indexOf("-") + 1);
            String endtime = timing;
            LinkedHashMap<String, String> temp = new LinkedHashMap<>();
            temp.put("Day", Day);
            temp.put("Start Time", starttime);
            temp.put("End Time", endtime);
            temp.put("Lecture/Tutorial/Lab", "Lecture");
            timeAndRoom.add(temp);
        }

        else if (Day.equals("Tutorial")) {
            if (Day.substring(0, 1).equals("*")) {
                String Day1 = timing.substring(timing.indexOf("^") + 1, timing.indexOf("%"));
                String starttime = timing.substring(timing.indexOf("@") + 1, timing.indexOf("-"));
                timing = timing.substring(timing.indexOf("-") + 1);
                String endtime = timing.substring(0, timing.indexOf("$"));
                LinkedHashMap<String, String> temp = new LinkedHashMap<>();
                temp.put("Day", Day1);
                temp.put("Start Time", starttime);
                temp.put("End Time", endtime);
                temp.put("Lecture/Tutorial/Lab", "Tutorial");
                timeAndRoom.add(temp);
                timing = timing.substring(timing.indexOf("&"));
                String Day2 = timing.substring(timing.indexOf("^") + 1, timing.indexOf("%"));
                starttime = timing.substring(timing.indexOf("@") + 1, timing.indexOf("-"));
                timing = timing.substring(timing.indexOf("-") + 1);
                endtime = timing.substring(0, timing.indexOf("$"));
                LinkedHashMap<String, String> temp2 = new LinkedHashMap<>();
                temp2.put("Day", Day2);
                temp2.put("Start Time", starttime);
                temp2.put("End Time", endtime);
                temp2.put("Lecture/Tutorial/Lab", "Tutorial");
                timeAndRoom.add(temp2);

            } else {
                String Day1 = timing.substring(timing.indexOf("^") + 1, timing.indexOf("%"));
                String starttime = timing.substring(timing.indexOf("@") + 1, timing.indexOf("-"));
                timing = timing.substring(timing.indexOf("-") + 1);
                String endtime = timing.substring(0, timing.indexOf("$"));
                LinkedHashMap<String, String> temp = new LinkedHashMap<>();
                temp.put("Day", Day1);
                temp.put("Start Time", starttime);
                temp.put("End Time", endtime);
                temp.put("Lecture/Tutorial/Lab", "Tutorial");
                timeAndRoom.add(temp);

            }
        } else {
            String Day1 = timing.substring(timing.indexOf("^") + 1, timing.indexOf("%"));
            String starttime = timing.substring(0, timing.indexOf("-"));
            timing = timing.substring(timing.indexOf("-") + 1);
            String endtime = timing.substring(0, timing.indexOf("$"));
            LinkedHashMap<String, String> temp = new LinkedHashMap<>();
            temp.put("Day", Day1);
            temp.put("Start Time", starttime);
            temp.put("End Time", endtime);
            temp.put("Lecture/Tutorial/Lab", "Labs");
            timeAndRoom.add(temp);

        }

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

    public void setCreditsOffered(int creditsOffered) {
        this.creditsOffered = creditsOffered;
    }

    @Override
    public String toString() {
        return "Course code: " + courseCode + " Course name: " + courseName + " instructor: " + instructor + " precondition " + preReq;
    }
}
