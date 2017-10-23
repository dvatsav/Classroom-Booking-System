package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import Utils.XlsToCsv;

public class entryPageController {

    @FXML private TextField user;
    @FXML private TextField pass;
    @FXML private Button login;



    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
            Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Main.primaryStage.setScene(new Scene(newscene, 800, 600));
            Main.primaryStage.show();
    }

    @FXML
    private void handleNewUser(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
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
