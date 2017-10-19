package UIControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.*;

public class RegisterController implements Serializable{
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
    public void handleGoBack(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("register.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }
}
