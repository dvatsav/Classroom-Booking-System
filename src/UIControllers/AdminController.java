package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class AdminController {

    @FXML
    public void handleFileSelect(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File for Upload");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fileChooser.showOpenDialog(Main.primaryStage);
    }

    @FXML
    private void handleViewAction() {
        System.out.println("worked");
    }
    @FXML
    private void logout() throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }
}
