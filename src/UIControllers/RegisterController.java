package UIControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;

import java.io.*;

public class RegisterController implements Serializable{
	// Helper Stuff
	private ObservableList<String> branchList = FXCollections.observableArrayList("CSE", "ECE", "CSAM");

	@FXML
	private ChoiceBox branchChoiceBox;
	@FXML
	private RadioButton register2AdminRadio;
	@FXML
	private RadioButton register2FacultyRadio;
	@FXML
	private RadioButton register2StudentRadio;

    //while registering, ask user to enter what type of user they are. If admin/faculty, then enter a unique hash that can be used to enter as admin or faculty

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
    public void showRegister(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }



	public void showLoginPage(ActionEvent actionEvent) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
	}

	public void showRegister2(ActionEvent actionEvent) throws IOException {
		Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
		Main.primaryStage.setScene(new Scene(newscene, 600, 400));
		Main.primaryStage.show();
	}

	public void showBasedOnAccountType(ActionEvent actionEvent) throws IOException {
    	if (register2StudentRadio.isSelected()){
			Parent newscene = FXMLLoader.load(getClass().getResource("StudentRegister.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 600, 400));
			Main.primaryStage.show();
//			setupChoiceBox();
		} else if (register2AdminRadio.isSelected()) {
			Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 600, 400));
			Main.primaryStage.show();
		}
		else if (register2FacultyRadio.isSelected()) {
			Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 600, 400));
			Main.primaryStage.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("No option selected. Please select one of the three available options.");
			alert.showAndWait();
		}
	}
}
