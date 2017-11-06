package Actors;

import java.io.Serializable;
import java.time.LocalDate;

public class Admin extends Users implements Serializable {

    public Admin(String firstName, String lastName, String phoneNumber, String email, String password, String type, LocalDate dob) {
        super(firstName, lastName, phoneNumber, email, password, type, dob);
    }

    public Admin(){}

    @Override
    public String toString() {
        return "Admin{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", dob=" + dob +
                '}';
    }
}
