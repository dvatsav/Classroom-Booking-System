package UIControllers;

import Supplementary.Course;
import Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Observable;

public class StudentController {
    @FXML private TableView<Course> table_student;
    @FXML private TextField student_search_course;

    private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);

    @FXML
    public void handleGenerateCourse(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String text = student_search_course.getText();
            TableColumn<Course, String> col1 = new TableColumn<>("Course Name");
            TableColumn<Course, String> col2 = new TableColumn<>("Course Code");
            TableColumn<Course, String> col3 = new TableColumn<>("Instructor");
            TableColumn<Course, String> col4 = new TableColumn<>("Credits");
            table_student.getColumns().add(col1);
            table_student.getColumns().add(col2);
            table_student.getColumns().add(col3);
            table_student.getColumns().add(col4);
            col1.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
            col2.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
            col3.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
            col4.setCellValueFactory(new PropertyValueFactory<Course, String>("creditsOffered"));
            table_student.setItems(courses);
        }
    }
}
