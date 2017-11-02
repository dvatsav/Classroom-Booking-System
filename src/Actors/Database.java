package Actors;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Anubhav on 02-Nov-17.
 */
public class Database implements Serializable{
	Map<String, Student> studentsDB;
	List<Faculty> facultyDB;
	List<Admin> adminDB;

	public Map<String, Student> getStudentsDB() {
		return studentsDB;
	}

	public List<Faculty> getFacultyDB() {
		return facultyDB;
	}

	public List<Admin> getAdminDB() {
		return adminDB;
	}

	public Database() {

	}
}
