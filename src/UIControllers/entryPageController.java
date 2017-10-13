package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class entryPageController {

    @FXML private TextField user;
    @FXML private TextField pass;
    @FXML private Button login;

    @FXML
    private void handleButtonClick(ActionEvent event) throws IOException {


            Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Main.primaryStage.setScene(new Scene(newscene, 600, 400));
            Main.primaryStage.show();

    }
}
