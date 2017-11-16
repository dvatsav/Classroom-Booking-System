package Supplementary;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <h1>Booking Class</h1>
 * <p>This class is used to serialize the various booking requests once they are confirmed.</p>
 */
public class Booking implements Serializable{
    public static ArrayList<HashMap> bookings = new ArrayList<>();

    public static void serialize() throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("./src/DataFiles/confirmedbooking.txt"));
            out.writeObject(bookings);
        } finally {
            out.close();
        }
    }

    public static ArrayList<HashMap> deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("./src/DataFiles/confirmedbooking.txt"));
            bookings = (ArrayList<HashMap>) in.readObject();
        } finally {
            in.close();
        }
        return bookings;
    }

}
