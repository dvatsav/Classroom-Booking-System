package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import Utils.XlsToCsv;

public class entryPageController {

    @FXML private TextField login_email;
    @FXML private PasswordField login_password;
    @FXML private Button login_button;

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
            if (login_email.getText().equals("deepak16030@iiitd.ac.in") && login_password.getText().equals("n00b"))
            {
                Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
                Main.primaryStage.setScene(new Scene(newscene, 800, 600));
                Main.primaryStage.show();
            }


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
}
