package Actors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <h1>Users Class</h1>
 * <p>This class is the basic structure for all the user groups in our app. It has basic fields which will be shared
 * across all user groups.</p>
 */
public class Users implements Serializable{
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
    protected String email;
    protected String password;
    protected String type;
    protected String dob;

    public Users(){}

	public Users(String firstName, String lastName, String phoneNumber, String email, String password, String type, String dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.type = type;
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
