package Supplementary;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private static Map<String, ArrayList<HashMap<String, LocalTime>>> roomAvailable = new HashMap<>();
    private String roomNumber;

    public static void addTimings(String day, String data) {
        if (data.equals("") || data.equals(" ")) {
            return;
        }
        int occurences = 0;
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
                endAt = data.indexOf("&", startAt); //19, 23
                classNumber = data.substring(startAt, endAt - 1);
            }
            else {
                classNumber = data.substring(startAt);
            }
            try {
                String startTempTime = data.substring(data.indexOf("@", startAt - 13) + 1, data.indexOf("-", startAt - 13));
                String endTempTime = data.substring(data.indexOf("-", startAt - 13) + 1, data.indexOf("$", startAt - 13));
                System.out.println(classNumber + " " + startTempTime + " " + endTempTime);
            } catch(Exception e) {
                System.out.println(data);
                System.exit(1);
            }
            /*
            if (roomAvailable.containsKey(classNumber)) {
                ArrayList<HashMap<String, LocalTime>> temp = new ArrayList<>(roomAvailable.get(classNumber));
                HashMap<String, LocalTime> hmTemp = new HashMap<>();
                hmTemp.put('start time', startTime);
                hmTemp.put('end time', endTime);
                temp.add()
            }*/
        }
    }
    public String getRoomNumber() {return roomNumber;}

}
