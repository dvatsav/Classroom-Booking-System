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
import javafx.scene.control.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;

public class entryPageController {

	public static String userType;
	public static String userEmail;
    @FXML private TextField login_email;
    @FXML private PasswordField login_password;
    @FXML private ChoiceBox loginUserTypeChoice;

    String tempEmailLogin = "", tempPasswordLogin = "", tempTypeOfUserLogin = "";

	/**
	 * This function is responsible for searching the information entered by the user in our database and check if such
	 * user exists. If it does it logs him/her in, otherwise will display an alert box.
	 * @param event
	 * @throws IOException
	 */
	@FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
    	tempEmailLogin = login_email.getText();
    	tempPasswordLogin = login_password.getText();
    	tempTypeOfUserLogin = loginUserTypeChoice.getSelectionModel().getSelectedItem().toString();
		userType = tempTypeOfUserLogin;
		userEmail = tempEmailLogin;
		Database userDb = null;
		try {
			userDb = readDBFromFile();
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			System.out.println("No database of users found!");
//			e.printStackTrace();
		}
		if (tempTypeOfUserLogin.equals("Student")) {
			HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
			int flag = 0;
			if (mp == null || mp.size() == 0) {
				nouser();
				return;
			}
			for (Student student : mp.values()) {
				System.out.println(student);
				if (student.getEmail().equals(tempEmailLogin) && student.getPassword().equals(tempPasswordLogin)) {
					Parent newscene = FXMLLoader.load(getClass().getResource("student.fxml"));
					flag = 1;
					setUser(tempEmailLogin, tempTypeOfUserLogin);
					Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
					Main.primaryStage.show();
				}
			}
			if (flag == 0) {
				nouser();
			}
		} else if (tempTypeOfUserLogin.equals("Faculty")) {
			List<Faculty> fl = userDb.getFacultyDB();
			int flag = 0;
			if (fl == null || fl.size() == 0) {
				nouser();
				return;
			}
			for (Faculty faculty : fl) {
				if (faculty.getEmail().equals(tempEmailLogin) && faculty.getPassword().equals(tempPasswordLogin)) {
					Parent newscene = FXMLLoader.load(getClass().getResource("faculty.fxml"));
					flag = 1;
					setUser(tempEmailLogin, tempTypeOfUserLogin);
					Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
					Main.primaryStage.show();
				}
			}
			if (flag == 0) {
				nouser();
			}
		} else if (tempTypeOfUserLogin.equals("Admin")) {
			if (tempEmailLogin.equals("admin@iiitd.ac.in") && tempPasswordLogin.equals("hello")) {
				Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
				setUser(tempEmailLogin, tempTypeOfUserLogin);
				Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
				Main.primaryStage.show();
			} else {
				List<Admin> al = userDb.getAdminDB();
				if (al == null || al.size() == 0) {
					nouser();
					return;
				}
				int flag = 0;
				for (Admin admin : al) {
					if (admin.getEmail().equals(tempEmailLogin) && admin.getPassword().equals(tempPasswordLogin)) {
						Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
						flag = 1;
						setUser(tempEmailLogin, tempTypeOfUserLogin);
						Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
						Main.primaryStage.show();
					}
				}
				if (flag == 0) {
					nouser();
				}
			}
		}
    }

	/**
	 * This function sets values of email and user type for the current user
	 * @param tempEmailLogin email of user
	 * @param tempTypeOfUserLogin user type
	 */
	public void setUser(String tempEmailLogin, String tempTypeOfUserLogin) {
		CurrentLoggenInUser.setCurrentUserEmail(tempEmailLogin);
		CurrentLoggenInUser.setCurrentUserType(tempTypeOfUserLogin);
	}

	/**
	 * Launchs the noUser dialog box.
	 */
	private void nouser() {

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("User Does not Exist");
		alert.show();

	}

	/**
	 * Loads the register page.
	 * @param event Action event handler object
	 * @throws IOException when input or output stream not initialized
	 */
    @FXML
    private void handleNewUser(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 1000, 700));
        Main.primaryStage.show();
    }


	/**
	 * Shows the forgot password page.
	 * @param actionEvent Action event handler object
	 * @throws IOException when input or output stream not initialized
	 */
	@FXML
    public void showForgotPasswordPage(ActionEvent actionEvent) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("forgotPassword.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

	/**
	 * Helper function to read database.
	 * @return return a Database object
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException when the requested class is not found
	 */
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
