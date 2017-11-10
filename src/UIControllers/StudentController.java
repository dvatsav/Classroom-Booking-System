package UIControllers;

import Supplementary.Course;
import Supplementary.Room;
import Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.Observable;

public class StudentController {
    @FXML private TableView<Course> table_student;
    @FXML private TextField student_search_course;

    private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);
    private ObservableList<Room> rooms = FXCollections.observableArrayList(Utilities.rooms);

    @FXML
    public void handleGenerateCourse(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TableColumn<Course, String> col1 = new TableColumn<>("Course Name");
            TableColumn<Course, String> col2 = new TableColumn<>("Course Code");
            TableColumn<Course, String> col3 = new TableColumn<>("Instructor");
            TableColumn<Course, String> col4 = new TableColumn<>("Credits");
            TableColumn<Course, String> col5 = new TableColumn<>("Pre Conditions");
            TableColumn<Course, String> col6 = new TableColumn<>("Post Conditions");
            table_student.getColumns().add(col1);
            table_student.getColumns().add(col2);
            table_student.getColumns().add(col3);
            table_student.getColumns().add(col4);
            table_student.getColumns().add(col5);
            table_student.getColumns().add(col6);
            col1.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
            col2.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
            col3.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
            col4.setCellValueFactory(new PropertyValueFactory<Course, String>("creditsOffered"));
            col5.setCellValueFactory(new PropertyValueFactory<Course, String>("preReq"));
            col6.setCellValueFactory(new PropertyValueFactory<Course, String>("postCondition"));
            col6.prefWidthProperty().bind(table_student.widthProperty().multiply(0.9));
            table_student.setItems(courses);
        }
    }

    @FXML
    public void handleCurrentBookings(ActionEvent event) {
        TableColumn<Room, String> col1 = new TableColumn<>("Day");
        //table_student.getColumns().add(col1);

    }

    @FXML
    public void handleBookRoom(ActionEvent event) throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("bookRoom.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }
}
