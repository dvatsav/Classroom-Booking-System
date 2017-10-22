package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class entryPageController {

    @FXML private TextField user;
    @FXML private TextField pass;
    @FXML private Button login;

    @FXML
    public void handleFileSelect(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File for Upload");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fileChooser.showOpenDialog(Main.primaryStage);
    }

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {
            Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Main.primaryStage.setScene(new Scene(newscene, 600, 400));
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
}
