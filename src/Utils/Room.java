package Utils;

import java.util.Calendar;
import java.util.Map;

public class Room {
    private int totalCapacity;
    private Map<Calendar, Boolean> roomAvailable;
    private String roomNumber;

    public String getRoomNumber() {return roomNumber;}

}
