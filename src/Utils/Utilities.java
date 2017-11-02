package Utils;

import Supplementary.Course;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utilities {
    public static void readCoursesCSV() {
        String CSVfile1 = "src/DataFiles/corecourses.csv";
        String CSVfile2 = "src/DataFiles/electives.csv";
        String CSVfile3 = "src/DataFiles/postconditions.csv";
        BufferedReader br = null;
        String line = "";
        String csvSplit = ",";
        ArrayList<Course> courses = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(CSVfile1));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplit);

                System.out.println(data[0] + " " + data[1] + " " + data[2] + " " + data[3] + " " + data[4]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readCoursesCSV();
    }
}
