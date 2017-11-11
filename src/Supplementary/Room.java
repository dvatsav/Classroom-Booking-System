package Supplementary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Room {
    private Map<String, ArrayList<HashMap<String, String>>> roomAvailable;
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

    public String mapToString() {
        
    }

    public String toString() {
        return roomNumber +
    }

    public String getRoomNumber() {return roomNumber;}

}
