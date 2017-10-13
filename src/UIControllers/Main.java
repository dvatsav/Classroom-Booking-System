package UIControllers;



import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        primaryStage.setTitle("ClassRoom Booking System");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        //entryPageController ent = new entryPageController();
        //ent.setStage(primaryStage);
        //TimeUnit.MINUTES.sleep(4);
        /*
        root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}