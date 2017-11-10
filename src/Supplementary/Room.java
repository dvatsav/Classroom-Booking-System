package Supplementary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private Map<String, ArrayList<HashMap<String, Integer>>> roomAvailable = new HashMap<>();
    private String roomNumber;

    public static void addTimings(String day, String data) {
        if (data.equals("") || data.equals(" ")) {
            return;
        }
        int occurences = 0;
        ArrayList<HashMap<String, Integer>> temp = new ArrayList<>();
        for (int i = 0 ; i < data.length() ; ++i) {
            if (data.substring(i, i + 1).equals("@")) {
                occurences += 1;
            }
        }
        int startAt = 0;
        int endAt = 0;
        for (int i = 0 ; i < occurences ; ++i) {
            startAt = data.indexOf("$", startAt) + 1;
            String classNumber = "";
            if (occurences > 1) {
                endAt = data.indexOf("&", startAt);
                classNumber = data.substring(startAt, endAt - 1);
            }
            else {
                classNumber = data.substring(startAt);
            }
            System.out.println(classNumber);
        }
    }
    public String getRoomNumber() {return roomNumber;}

}
