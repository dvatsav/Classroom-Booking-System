package Supplementary;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CourseRequests implements Serializable{
    private ArrayList<HashMap> courserequests;
    public void serialize(ArrayList<HashMap> ar) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./src/DataFiles/coursereqs.txt"));
            out.writeObject(courserequests);
        } finally {
            out.close();
        }
    }

    public ArrayList<HashMap> deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        ArrayList<HashMap> course = null;
        try {
            in = new ObjectInputStream(new FileInputStream("./src/DataFiles/coursereqs.txt"));
            course = (ArrayList<HashMap>) in.readObject();
        } finally {
            in.close();
        }
        return course;
    }

    public void addElement(HashMap<String, String> hm) {
        courserequests.add(hm);
    }

    public void setCourseRequests(ArrayList<HashMap> hm) {
        courserequests = new ArrayList<>(hm);
    }

    public void newCourse() {
        courserequests = new ArrayList<>();
    }

    public void removeCourse(HashMap<String, String> hm) {courserequests.remove(hm);}

    public ArrayList<HashMap> getcourserequests() {
        return courserequests;
    }
}
