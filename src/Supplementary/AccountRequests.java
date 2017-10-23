package Supplementary;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccountRequests {
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty emailID = new SimpleStringProperty();
    private StringProperty accType = new SimpleStringProperty();
    private StringProperty rollNumber = new SimpleStringProperty();

    public AccountRequests(String firstName, String lastName, String emailID, String accType, String rollNumber) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.emailID = new SimpleStringProperty(emailID);
        this.accType = new SimpleStringProperty(accType);
        this.rollNumber = new SimpleStringProperty(rollNumber);
    }

    public String getFirstName() {
        return firstName.get();
    }


    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmailID() {
        return emailID.get();
    }

    public void setEmailID(String emailID) {
        this.emailID.set(emailID);
    }

    public String getAccType() {
        return accType.get();
    }

    public void setAccType(String accType) {
        this.accType.set(accType);
    }

    public String getRollNumber() {
        return rollNumber.get();
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber.set(rollNumber);
    }
}
