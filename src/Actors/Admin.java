package Actors;

import java.io.Serializable;
import java.time.LocalDate;

public class Admin extends Users implements Serializable {

    public Admin(String firstName, String lastName, String phoneNumber, String email, String password, String type, LocalDate dob) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
    }
}
