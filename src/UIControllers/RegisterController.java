package UIControllers;

import Actors.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RegisterController implements Serializable {
	@FXML private TextField register_firstname;
	@FXML private TextField register_lastname;
	@FXML private TextField register_emailid;
	@FXML private DatePicker register_dob;
	@FXML private TextField register_phnumber;
	@FXML private PasswordField register_password;
	@FXML private PasswordField register_repassword;
	@FXML private TextField register_rollnum;

	private String tempFirstName = "", tempLastName = "", tempEmailID = "", tempPhNumber = "", tempPassword = "", tempRollNo = "";
	private LocalDate tempDob;

	@FXML
	public void handlePageTwo(ActionEvent event) throws IOException {
		tempFirstName = register_firstname.getText();
		tempLastName = register_lastname.getText();
		tempEmailID = register_emailid.getText();
		tempPhNumber = register_phnumber.getText();
		tempDob = register_dob.getValue();
		System.out.println("tempName " + tempFirstName);
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void handleFinalPage(ActionEvent even) throws IOException, ClassNotFoundException {
		tempRollNo = register_rollnum.getText();
		Student student = new Student(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, "Student", tempDob);
		student.setRollNo(tempRollNo);
		System.out.println("-------------- " + student);
		serializeData(student);
		Parent newscene = FXMLLoader.load(getClass().getResource("finalRegister.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void showLoginPage(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	public void showRegister2Page(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	private void serializeData(Users user) throws IOException, ClassNotFoundException {
		if (user.getType().equals("Student")) {
			Student student = (Student) user;
			// Check if db.txt exists if not create it else write to that only.
			File file = new File("./src/db.txt");
			if (file.exists()) {
				Database db = readDBFromFile();
				Map<String, Student> mp = db.getStudentsDB();
				mp.put(student.getRollNo(), student);
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
		}
	}

	private void writeDBToFile(Database db) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("db.txt"));
		out.writeObject(db);
		out.flush();
		out.close();
	}

	private Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("db.txt"));
		return ( (Database) oin.readObject() );
	}
}
