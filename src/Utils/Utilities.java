package Utils;

import Supplementary.Course;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    public static void readCoursesCSV() {
        String CSVfile1 = "src/DataFiles/corecourse.csv";
        String CSVfile2 = "src/DataFiles/electives.csv";
        String CSVfile3 = "src/DataFiles/postconditions.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplit = ",";
        ArrayList<Course> courses = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(CSVfile1));
            readFile(br, csvSplit, courses);

            br = new BufferedReader(new FileReader(CSVfile2));
            readFile(br, csvSplit, courses);

            br = new BufferedReader(new FileReader(CSVfile3));
            addPostConditions(br, csvSplit, courses);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0 ; i < courses.size() ; ++i) {
            System.out.println(courses.get(i));
            courses.get(i).getPostConditions();
        }
    }

    private static void readFile(BufferedReader br, String csvSplit, ArrayList<Course> courses) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(csvSplit);
            Course course = new Course(data[0], data[1], data[2], data[3], data[4], data[5]);
            courses.add(course);
        }
    }

    private static void addPostConditions(BufferedReader br, String csvSplit, ArrayList<Course> courses) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(csvSplit);
            for (int i = 0 ; i < courses.size() ; ++i) {
                if (data[0].equals(courses.get(i).getCourseCode())) {
                    for (int j = 3 ; j < data.length ; ++j) {
                        courses.get(i).setPostConditions(data[j]);
                    }
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        readCoursesCSV();
    }
}
