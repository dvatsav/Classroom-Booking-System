package UIControllers;

import Supplementary.Course;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StudentController {
    @FXML private TableView<Course> table_student;
    @FXML private TextField student_search_course;

    @FXML
    public void handleGenerateCourse(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String text = student_search_course.getText();
            System.out.println(text);
        }
    }
}
