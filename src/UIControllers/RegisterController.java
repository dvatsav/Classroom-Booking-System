package UIControllers;

import Actors.*;
import Supplementary.AccountRequests;
import Supplementary.BookingRequests;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class RegisterController implements Serializable {
	@FXML private TextField register_firstname;
	@FXML private TextField register_lastname;
	@FXML private TextField register_emailid;
	@FXML private DatePicker register_dob;
	@FXML private TextField register_phnumber;
	@FXML private PasswordField register_password;
	@FXML private PasswordField register_repassword;
	@FXML private TextField register_rollnum;
	@FXML private ChoiceBox typeOfUser;
	@FXML private ChoiceBox branchOfUser;
	@FXML private Label rollNumberLabel;
	@FXML private Label branchLabel;

	private String tempFirstName = "", tempLastName = "", tempEmailID = "", tempPhNumber = "", tempPassword = "", tempRollNo = "", tempUserType = "", tempUserBranch = "";
	private String tempDob;

	@FXML
	public void handleFinalPage(ActionEvent even) throws IOException, ClassNotFoundException {
		boolean passwordMatch = false;
		tempFirstName = register_firstname.getText();
		tempLastName = register_lastname.getText();
		tempEmailID = register_emailid.getText();
		tempDob = register_dob.getValue().toString();
		tempPhNumber = register_phnumber.getText();
		tempUserType = typeOfUser.getSelectionModel().getSelectedItem().toString();
		if (register_password.getText().equals(register_repassword.getText())) {
			tempPassword = register_password.getText();
			passwordMatch = true;
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Passwords Don't Match.");
			alert.showAndWait();
			passwordMatch = false;
		}
		if (passwordMatch) {
			if (tempUserType.equals("Student")) {
				tempRollNo = register_rollnum.getText();
				tempUserBranch = branchOfUser.getSelectionModel().getSelectedItem().toString();
				//Student student = new Student(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, tempUserType, tempDob, tempRollNo, tempUserBranch);
				HashMap<String, String> hm = new HashMap<>();
				hm.put("User Type", "Student");
				hm.put("First Name", tempFirstName);
				hm.put("Last Name", tempLastName);
				hm.put("Phone Number", tempPhNumber);
				hm.put("Email ID", tempEmailID);
				hm.put("Password", tempPassword);
				hm.put("Date of Birth", tempDob.toString());
				hm.put("Roll Number", tempRollNo);
				hm.put("Branch", tempUserBranch);
				File file = new File("./src/DataFiles/accountreqs.txt");
				AccountRequests a = new AccountRequests();
				if (file.exists()) {
					a.setAccountRequests(a.deserialize());
					System.out.println("exists");
				} else {
					a.newAccount();
				}
				a.addElement(hm);
				a.serialize(a.getAccountRequests());
				//System.out.println("-------------- " + student);
				//serializeData(student);
			} else if (tempUserType.equals("Faculty")) {
				//Faculty faculty = new Faculty(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, tempUserType, tempDob);
				//System.out.println("-----------------" + faculty);
				HashMap<String, String> hm = new HashMap<>();
				hm.put("User Type", "Faculty");
				hm.put("First Name", tempFirstName);
				hm.put("Last Name", tempLastName);
				hm.put("Phone Number", tempPhNumber);
				hm.put("Email ID", tempEmailID);
				hm.put("Password", tempPassword);
				hm.put("Date of Birth", tempDob.toString());
				hm.put("Roll Number", "-");
				hm.put("Branch", "-");
				File file = new File("./src/DataFiles/accountreqs.txt");
				AccountRequests a = new AccountRequests();
				if (file.exists()) {
					a.setAccountRequests(a.deserialize());
					System.out.println("exists");
				} else {
					a.newAccount();
				}
				a.addElement(hm);
				a.serialize(a.getAccountRequests());
				//serializeData(faculty);
			} else if (tempUserType.equals("Admin")) {
				//Admin admin = new Admin(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, tempUserType, tempDob);
				//.out.println("-----------" + admin);
				HashMap<String, String> hm = new HashMap<>();
				hm.put("User Type", "Admin");
				hm.put("First Name", tempFirstName);
				hm.put("Last Name", tempLastName);
				hm.put("Phone Number", tempPhNumber);
				hm.put("Email ID", tempEmailID);
				hm.put("Password", tempPassword);
				hm.put("Date of Birth", tempDob.toString());
				hm.put("Roll Number", "-");
				hm.put("Branch", "-");
				File file = new File("./src/DataFiles/accountreqs.txt");
				AccountRequests a = new AccountRequests();
				if (file.exists()) {
					a.setAccountRequests(a.deserialize());
					System.out.println("exists");
				} else {
					a.newAccount();
				}
				a.addElement(hm);
				a.serialize(a.getAccountRequests());
				//serializeData(admin);
			}
			Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 600, 400));
			Main.primaryStage.show();
		}
	}

	@FXML
	public void showLoginPage(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void initialize() {
		typeOfUser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if ((int) newValue != 0){
					branchLabel.setVisible(false);
					rollNumberLabel.setVisible(false);
					register_rollnum.setVisible(false);
					branchOfUser.setVisible(false);
				} else {
					branchLabel.setVisible(true);
					rollNumberLabel.setVisible(true);
					register_rollnum.setVisible(true);
					branchOfUser.setVisible(true);
				}
			}
		});
	}


	public static void serializeData(Users user) throws IOException, ClassNotFoundException {
		if (user.getType().equals("Student")) {
			Student student = (Student) user;
			// Check if db.txt exists if not create it else write to that only.
			File file = new File("./src/db.txt");
			if (file.exists()) {
				Database db = readDBFromFile();
				Map<String, Student> mp = db.getStudentsDB();
				if (mp != null) {
					mp.put(student.getRollNo(), student);
				} else {
					mp = new HashMap<String, Student>();
					mp.put(student.getRollNo(), student);
					db.setStudentsDB((HashMap<String, Student>) mp);
				}
				writeDBToFile(db);
			} else {
				file.createNewFile();
				Database db = new Database();
				Map<String, Student> mp = new HashMap<String, Student>();
				mp.put(student.getRollNo(), student);
				db.setStudentsDB((HashMap<String, Student>) mp);
				System.out.println(student);
				writeDBToFile(db);
			}
		} else if (user.getType().equals("Faculty")) {
			Faculty faculty = (Faculty) user;
			File file = new File("./src/db.txt");
			if (file.exists()) {
				Database db = readDBFromFile();
				List<Faculty> fl = db.getFacultyDB();
				if (fl != null){
					fl.add(faculty);
				} else {
					fl = new ArrayList<>();
					fl.add(faculty);
					db.setFacultyDB(fl);
				}
				writeDBToFile(db);
			} else {
				file.createNewFile();
				Database db = new Database();
				List<Faculty> fl = new ArrayList<>();
				fl.add(faculty);
				db.setFacultyDB(fl);
				System.out.println(faculty);
				writeDBToFile(db);
			}
		} else if (user.getType().equals("Admin")) {
			Admin admin = (Admin) user;
			File file = new File("./src/db.txt");
			if (file.exists()) {
				Database db = readDBFromFile();
				List<Admin> al = db.getAdminDB();
				if (al != null) {
					al.add(admin);
				} else {
					al = new ArrayList<>();
					al.add(admin);
					db.setAdminDB(al);
				}
				writeDBToFile(db);
			} else {
				file.createNewFile();
				Database db = new Database();
				List<Admin> al = new ArrayList<>();
				al.add(admin);
				db.setAdminDB(al);
				writeDBToFile(db);
			}
			System.out.println(admin);
		}
	}

	private static void writeDBToFile(Database db) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/db.txt"));
		out.writeObject(db);
		out.flush();
		out.close();
	}

	private static Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./src/db.txt"));
		return ( (Database) oin.readObject() );
	}

	public void showAboutPage(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}
}
