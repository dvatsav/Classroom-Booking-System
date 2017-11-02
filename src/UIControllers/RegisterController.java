package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.*;

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

	private String tempFirstName = "", tempLastName = "", tempEmailID = "", tempPhNumber = "", tempPassword = "", tempRollNumber = "", tempBranch = "";


	@FXML
	public void handlePageTwo(ActionEvent event) throws IOException {
		tempFirstName = register_firstname.getText();
		tempLastName = register_lastname.getText();
		tempEmailID = register_emailid.getText();
		tempPhNumber = register_phnumber.getText();
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	@FXML
	public void handleFinalPage(ActionEvent even) throws IOException {
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
			showLoginPage(actionEvent);
		} else if (register2AdminRadio.isSelected()) {
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
}
