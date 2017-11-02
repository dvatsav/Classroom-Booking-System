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
import java.util.Map;

public class RegisterController implements Serializable {
	@FXML private RadioButton register2AdminRadio;
	@FXML private RadioButton register2FacultyRadio;
	@FXML private RadioButton register2StudentRadio;
	@FXML private TextField register_firstname;
	@FXML private TextField register_lastname;
	@FXML private TextField register_emailid;
	@FXML private DatePicker register_dob;
	@FXML private TextField register_phnumber;
	@FXML private PasswordField register_password;
	@FXML private PasswordField register_repassword;
	@FXML private TextField register_rollnumber;

	private String tempFirstName = "", tempLastName = "", tempEmailID = "", tempPhNumber = "", tempPassword = "", tempRollNo = "";
	private LocalDate tempDob;

	@FXML
	public void handlePageTwo(ActionEvent event) throws IOException {
		tempFirstName = register_firstname.getText();
		tempLastName = register_lastname.getText();
		tempEmailID = register_emailid.getText();
		tempPhNumber = register_phnumber.getText();
		tempDob = register_dob.getValue();
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void handleFinalPage(ActionEvent even) throws IOException {
		tempRollNo = register_rollnumber.getText();
		Student student = new Student(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, "Student", tempDob);
		student.setRollNo(tempRollNo);
		Parent newscene = FXMLLoader.load(getClass().getResource("finalRegister.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void handleReturn(ActionEvent event) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void handleGoBack(ActionEvent event) throws IOException {

		Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();


	}

	@FXML
	public void showLoginPage(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	public void showNextBasedOnAccountType(ActionEvent actionEvent) throws IOException {
		if (!register_password.getText().equals(register_repassword.getText())) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Passwords do not match");
			alert.showAndWait();
		}
		else {
			tempPassword = register_password.getText();
		}
		if (register2StudentRadio.isSelected()) {
			Parent newscene = FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 600, 400));
			Main.primaryStage.show();
		} else if (register2FacultyRadio.isSelected()) {
			Faculty faculty = new Faculty(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, "Faculty", tempDob);
			showLoginPage(actionEvent);
		} else if (register2AdminRadio.isSelected()) {
			Admin admin = new Admin(tempFirstName, tempLastName, tempPhNumber, tempEmailID, tempPassword, "Admin", tempDob);
			showLoginPage(actionEvent);
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Select one option.");
			alert.showAndWait();
		}
	}

	public void showRegister2Page(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	private void serializeData(Users user) throws IOException {
		if (user.getType().equals("Student")) {
			Student student = (Student) user;
			// Check if db.txt exists if not create it else write to that only.
			File file = new File("db.txt");
			if (file.exists()) {

			} else {
				file.createNewFile();
				Database db = new Database();
				Map<String, Student> mp = db.getStudentsDB();
				mp.put(student.get)
			}
		}
	}
}
