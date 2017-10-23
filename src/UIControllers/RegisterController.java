package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;

import java.io.*;

public class RegisterController implements Serializable {
	//while registering, ask user to enter what type of user they are. If admin/faculty, then enter a unique hash that can be used to enter as admin or faculty
	@FXML
	private RadioButton register2AdminRadio;
	@FXML
	private RadioButton register2FacultyRadio;
	@FXML
	private RadioButton register2StudentRadio;


	@FXML
	public void handlePageTwo(ActionEvent event) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("register2.fxml"));
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
