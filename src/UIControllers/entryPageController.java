package UIControllers;

import Actors.Admin;
import Actors.Database;
import Actors.Faculty;
import Actors.Student;
import Supplementary.CurrentLoggenInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import Utils.XlsToCsv;

public class entryPageController {

	public static String userType;
	public static String userEmail;
    @FXML private TextField login_email;
    @FXML private PasswordField login_password;
    @FXML private ChoiceBox loginUserTypeChoice;

    String tempEmailLogin = "", tempPasswordLogin = "", tempTypeOfUserLogin = "";

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
    	tempEmailLogin = login_email.getText();
    	tempPasswordLogin = login_password.getText();
    	tempTypeOfUserLogin = loginUserTypeChoice.getSelectionModel().getSelectedItem().toString();
		userType = tempTypeOfUserLogin;
		userEmail = tempEmailLogin;
    	Database userDb = readDBFromFile();
		if (tempTypeOfUserLogin.equals("Student")) {
			HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
			for (Student student : mp.values()) {
				System.out.println(student);
				if (student.getEmail().equals(tempEmailLogin) && student.getPassword().equals(tempPasswordLogin)) {
					Parent newscene = FXMLLoader.load(getClass().getResource("student.fxml"));
					Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
					Main.primaryStage.show();
				}
			}
		} else if (tempTypeOfUserLogin.equals("Faculty")) {
			List<Faculty> fl = userDb.getFacultyDB();
			for (Faculty faculty : fl) {
				if (faculty.getEmail().equals(tempEmailLogin) && faculty.getPassword().equals(tempPasswordLogin)) {
					Parent newscene = FXMLLoader.load(getClass().getResource("faculty.fxml"));
					Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
					Main.primaryStage.show();
				}
			}
		} else if (tempTypeOfUserLogin.equals("Admin")) {
			List<Admin> al = userDb.getAdminDB();
			for (Admin admin : al) {
				if (admin.getEmail().equals(tempEmailLogin) && admin.getPassword().equals(tempPasswordLogin)) {
					Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
					Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
					Main.primaryStage.show();
				}
			}
		}
		CurrentLoggenInUser.setCurrentUserEmail(tempEmailLogin);
		CurrentLoggenInUser.setCurrentUserType(tempTypeOfUserLogin);
    }

    @FXML
    private void handleNewUser(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 1000, 700));
        Main.primaryStage.show();
    }

    @FXML
    public void handleReturnToLogin(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

    @FXML
    public void showForgotPasswordPage(ActionEvent actionEvent) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

	private Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./src/db.txt"));
		return ( (Database) oin.readObject() );
	}

	public void showDB(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
		Database userDb = readDBFromFile();
		List<Faculty> fl = userDb.getFacultyDB();
		for (Faculty faculty : fl) {
			System.out.println(faculty);
		}
	}
}
