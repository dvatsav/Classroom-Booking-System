package Supplementary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Room {
    public Map<String, ArrayList<HashMap<String, String>>> roomAvailable;
    private String roomNumber;

    public Room(String roomNumber, Map map) {
       this.roomNumber = roomNumber;
       this.roomAvailable = new HashMap<>(map);
    }

    public void addToMap(ArrayList map, String day) {
        roomAvailable.put(day, map);
    }

    public ArrayList getArrayList(String index) {
        return roomAvailable.get(index);
    }

    public void returnIndex(String day) {

    }

    public boolean checkDay(String day) {
        if (roomAvailable.containsKey(day)) return true;
        else return false;
    }


    public String toString() {
        return roomAvailable.get("Monday").get(0).get("start time") + " " + roomAvailable.get("Monday").get(0).get("end time");
    }

    public String getRoomNumber() {return roomNumber;}

}
