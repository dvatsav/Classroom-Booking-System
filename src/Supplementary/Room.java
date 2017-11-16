package Supplementary;

import java.io.Serializable;
import java.util.*;

/**
 * <h1>Room Class</h1>
 * <p>This class store information for a room.</p>
 */
public class Room implements Serializable{
    public Map<String, ArrayList<LinkedHashMap<String, String>>> roomAvailable;
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

    public boolean checkDay(String day) {
        if (roomAvailable.containsKey(day)) return true;
        else return false;
    }

    public String getRoomNumber() {return roomNumber;}

}
