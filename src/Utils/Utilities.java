package Utils;

import Supplementary.Course;
import Supplementary.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<Room>();
    public static void readCoursesCSV() {
        String CSVfile1 = "src/DataFiles/alldata.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplit = ",";

        try {
            br = new BufferedReader(new FileReader(CSVfile1));
            readFile(br, csvSplit, courses);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile(BufferedReader br, String csvSplit, ArrayList<Course> courses) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(csvSplit);
            boolean mandatory;
            if (data[0].equals("Mandatory")) {
                mandatory = true;
            }
            else mandatory = false;
            Course course = new Course(mandatory, data[1], data[2], data[3], Integer.parseInt(data[4]), data[5], data[13]);
            course.addTiming("Monday", data[6]);
            course.addTiming("Tuesday", data[7]);
            course.addTiming("Wednesday", data[8]);
            course.addTiming("Thursday", data[9]);
            course.addTiming("Friday", data[10]);
            course.addTiming("Tutorial", data[11]);
            course.addTiming("Labs", data[12]);
            Room.addTimings("Monday", data[6]);
            Room.addTimings("Tuesday", data[7]);
            Room.addTimings("Wednesday", data[8]);
            Room.addTimings("Thursday", data[9]);
            Room.addTimings("Friday", data[10]);
            Room.addTimings("Tutorial", data[11]);
            Room.addTimings("Labs", data[12]);

            for (int i = 14 ; i < data.length ; ++i) {
                course.addPostCondition(data[i]);
            }
            course.convertPostToString();
            courses.add(course);
        }
    }

    public static void main(String[] args) {
        readCoursesCSV();
    }
}
