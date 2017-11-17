package UIControllers;



import Supplementary.Booking;
import Utils.Utilities;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public static Stage primaryStage;

    /**
     * This function starts the application by loading the entryPage.xml
     * @param stage Stage object
     * @throws Exception Exception handler for all exceptions
     */
    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        primaryStage.resizableProperty().setValue(Boolean.FALSE);
        Parent root = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        primaryStage.setTitle("ClassRoom Booking System");
        File file = new File("./src/DataFiles/courses.txt");

        if (file.exists()) {
            Utilities.deSerializeCourses();
            Utilities.deSerializeRooms();
            Booking.deserialize();
        }
        else {
            Utilities.readCoursesCSV();
            Utilities.serializeCourses();
            Utilities.serializeRooms();
        }
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}