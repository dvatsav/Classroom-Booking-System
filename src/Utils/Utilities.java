package Utils;

import Supplementary.Booking;
import Supplementary.Course;
import Supplementary.Room;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {
    public static ArrayList<Course> courses = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
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
            addTimings("Monday", data[6], data[5] + " Lecture");
            addTimings("Tuesday", data[7], data[5] + " Lecture");
            addTimings("Wednesday", data[8], data[5] + " Lecture");
            addTimings("Thursday", data[9], data[5] + " Lecture");
            addTimings("Friday", data[10], data[5] + " Lecture");
            if (!data[11].equals("")){
                if (data[11].substring(0, 1).equals("*")) {
                    String Day1 = data[11].substring(data[11].indexOf("^") + 1, data[11].indexOf("%"));
                    addTimings(Day1, data[11].substring(0, data[11].indexOf("&")), data[5] + " Tutorial");
                    data[11] = data[11].substring(data[11].indexOf("&"));
                    String Day2 = data[11].substring(data[11].indexOf("^") + 1, data[11].indexOf("%"));
                    addTimings(Day2, data[11].substring(data[11].indexOf("&")), data[5] + " Tutorial");
                } else {
                    String Day1 = data[11].substring(data[11].indexOf("^") + 1, data[11].indexOf("%"));
                    addTimings(Day1, data[11], data[5] + " Tutorial");
                }
            }
            if (!data[12].equals("")){
                    String Day = data[12].substring(data[12].indexOf("^") + 1, data[12].indexOf("%"));
                    addTimings(Day, data[12], data[5] + " Lab");
            }

            for (int i = 14 ; i < data.length ; ++i) {
                course.addPostCondition(data[i]);
            }
            course.convertPostToString();
            courses.add(course);
        }
        addRoomsToBooking();
        //printBookings();
        printAllCourses();
    }

    public static void addTimings(String day, String data, String subject) {
        if (data.equals("") || data.equals(" ")) {
            return;
        }
        //Find the number of times some timing occurs on a particular data string
        int occurences = 0;
        for (int i = 0 ; i < data.length() ; ++i) {
            if (data.substring(i, i + 1).equals("@")) {
                occurences += 1;
            }
        }
        int startAt = 0;
        int endAt = 0;
        //For each time occurence (for eg DM tutes)
        for (int i = 0 ; i < occurences ; ++i) {
            startAt = data.indexOf("$", startAt) + 1;
            String classNumber = "";
            if (occurences > 1) {
                endAt = data.indexOf("&", startAt); //19, 23
                classNumber = data.substring(startAt, endAt - 1);
            }
            else {
                classNumber = data.substring(startAt);
            }
            String startTempTime = data.substring(data.indexOf("@", startAt - 13) + 1, data.indexOf("-", startAt - 13));
            String endTempTime = data.substring(data.indexOf("-", startAt - 13) + 1, data.indexOf("$", startAt - 13));
            int numOfClasses = 1;
            //Suppose tutes takes place in multiple classes, then handle using an arraylist
            ArrayList<String> classArray = new ArrayList<>();
            for (int j = 0 ; j < classNumber.length() ; ++j) {
                if (classNumber.substring(j, j + 1).equals(";"))
                    numOfClasses += 1;
            }
            if (numOfClasses > 1) {
                for (int j = 0 ; j < numOfClasses - 1; ++j) {
                    classArray.add(classNumber.substring(0, classNumber.indexOf(";")));
                    classNumber = classNumber.substring(classNumber.indexOf(";") + 1);
                }
            } else {
                classArray.add(classNumber);
            }
            for (int j = 0 ; j < classArray.size() ; ++j) {
                ArrayList<HashMap<String, String>> temp;
                boolean checkExistence = false;
                int index = 0;
                for (int k = 0 ; k < rooms.size() ; ++k) {
                    if (rooms.get(k).getRoomNumber().equals(classArray.get(j))) {
                        checkExistence = true;
                        index = k;
                        break;
                    }
                }
                if (!checkExistence) {
                    Room room = new Room(classArray.get(j), new HashMap());
                    temp = new ArrayList<>();
                    LinkedHashMap<String, String> hmTemp = new LinkedHashMap<>();
                    hmTemp.put("Day", day);
                    hmTemp.put("Room Number", classArray.get(j));
                    hmTemp.put("Purpose", subject);
                    hmTemp.put("Start Time", startTempTime);
                    hmTemp.put("End Time", endTempTime);

                    temp.add(hmTemp);
                    room.addToMap(temp, day);
                    rooms.add(room);
                } else {
                    if (rooms.get(index).checkDay(day)) {
                        temp = new ArrayList<>(rooms.get(index).getArrayList(day));
                        LinkedHashMap<String, String> hmTemp = new LinkedHashMap<>();
                        hmTemp.put("Day", day);
                        hmTemp.put("Room Number", classArray.get(j));
                        hmTemp.put("Purpose", subject);
                        hmTemp.put("Start Time", startTempTime);
                        hmTemp.put("End Time", endTempTime);
                        temp.add(hmTemp);
                        rooms.get(index).addToMap(temp, day);
                    } else {
                        temp = new ArrayList<>();
                        LinkedHashMap<String, String> hmTemp = new LinkedHashMap<>();
                        hmTemp.put("Day", day);
                        hmTemp.put("Room Number", classArray.get(j));
                        hmTemp.put("Purpose", subject);
                        hmTemp.put("Start Time", startTempTime);
                        hmTemp.put("End Time", endTempTime);
                        temp.add(hmTemp);
                        rooms.get(index).addToMap(temp, day);
                    }
                }
            }
        }
    }

    public static void addRoomsToBooking() {
        for (int i = 0 ; i < rooms.size() ; ++i) {
            for (Map.Entry<String, ArrayList<LinkedHashMap<String, String>>> entry : rooms.get(i).roomAvailable.entrySet()) {
                ArrayList<HashMap<String, String>> temp = new ArrayList<>(entry.getValue());
                String key = entry.getKey();
                for (int j = 0 ; j < temp.size() ; ++j) {
                    Booking.bookings.add(temp.get(j));
                }
            }
        }
    }

    public static void printBookings() {
        for (int i = 0 ; i < Booking.bookings.size() ; ++i) {
            System.out.println(Booking.bookings.get(i));
        }
    }

    public static void printAllRooms() {
        for (int i = 0 ; i < rooms.size() ; ++i) {
            System.out.println(rooms.get(i).getRoomNumber());
            System.out.println(rooms.get(i).roomAvailable);
        }
    }

    public static void printAllCourses() {
        for (int i = 0 ; i < courses.size() ; ++i) {
            System.out.println(courses.get(i).getCourseName());
            System.out.println(courses.get(i).timeAndRoom);
        }
    }
    public static String convertDateToDay(String date) {
        int year = Integer.parseInt(date.substring(0, date.indexOf("-")));
        date = date.substring(date.indexOf("-") + 1);
        int month = Integer.parseInt(date.substring(0, date.indexOf("-")));
        date = date.substring(date.indexOf("-") + 1);
        int day = Integer.parseInt(date);
        int t[] = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "Monday");
        hm.put(2, "Tuesday");
        hm.put(3, "Wednesday");
        hm.put(4, "Thursday");
        hm.put(5, "Friday");
        hm.put(6, "Saturday");
        hm.put(0, "Sunday");
        if (month < 3)
            year -= month;
        return hm.get((year + year/4 - year/100 + year/400 + t[month - 1] + day) % 7);

    }

    public static boolean determineValidTime(String start, String end, String classroom, String Day) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date d1 = sdf.parse(start);
        Date d2 = sdf.parse(end);
        if (d2.getTime() <= d1.getTime()) return false;
        for (int i = 0 ; i < Booking.bookings.size() ; ++i) {
            if (Day.equals((String)Booking.bookings.get(i).get("Day")) && classroom.equals((String)Booking.bookings.get(i).get("Room Number"))) {
                Date d3 = sdf.parse((String)Booking.bookings.get(i).get("Start Time"));
                Date d4 = sdf.parse((String)Booking.bookings.get(i).get("End Time"));
                if (d3.getTime() <= d1.getTime() && d1.getTime() <= d4.getTime()) {
                    return false;
                } else if (d2.getTime() > d3.getTime() && d2.getTime() < d4.getTime()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        readCoursesCSV();
    }
}
