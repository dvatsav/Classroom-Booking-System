package Actors;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anubhav on 02-Nov-17.
 */
public class Database implements Serializable{
	HashMap<String, Student> studentsDB;
	List<Faculty> facultyDB;
	List<Admin> adminDB;

	public void setStudentsDB(HashMap<String, Student> studentsDB) {
		this.studentsDB = studentsDB;
	}

	public void setFacultyDB(List<Faculty> facultyDB) {
		this.facultyDB = facultyDB;
	}

	public void setAdminDB(List<Admin> adminDB) {
		this.adminDB = adminDB;
	}

	public Map<String, Student> getStudentsDB() {
		return studentsDB;
	}

	public List<Faculty> getFacultyDB() {
		return facultyDB;
	}

	public List<Admin> getAdminDB() {
		return adminDB;
	}


}
