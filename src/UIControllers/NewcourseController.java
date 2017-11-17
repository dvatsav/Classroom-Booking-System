package UIControllers;

import Supplementary.BookingRequests;
import Supplementary.CourseRequests;
import Supplementary.CurrentLoggenInUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class NewcourseController {
    @FXML private TextField course_name;
    @FXML private ChoiceBox credits;
    @FXML private TextArea pre_requisite;
    @FXML private TextArea post_conditions;

    private String courseName, credit, preReq, postCond;

    /**
     * This generates and submits a request for a new course to be launched by a faculty member.
     * @param event Action event handler object
     * @throws IOException exception thrown when input or output stream not initialized
     * @throws ClassNotFoundException when requested class is not found
     */
    @FXML
    public void handleSubmit(ActionEvent event) throws IOException, ClassNotFoundException{
        courseName = course_name.getText();
        credit = credits.getValue().toString();
        preReq = pre_requisite.getText();
        postCond = post_conditions.getText();
        HashMap<String, String> hm = new HashMap<>();
        hm.put("Course Name", courseName);
        hm.put("Credits", credit);
        hm.put("Pre Requisites", preReq);
        hm.put("Post Conditions", postCond);
        hm.put("Proposed By", CurrentLoggenInUser.getCurrentUserEmail());
        File file = new File("./src/DataFiles/coursereqs.txt");
        CourseRequests c = new CourseRequests();
        if (file.exists()) {
            c.setCourseRequests(c.deserialize());
            System.out.println("exists");
        } else {
            c.newCourse();
        }
        c.addElement(hm);
        c.serialize(c.getcourserequests());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
        alert.setHeaderText(null);
        alert.setContentText("Your Request has been successfully submitted");
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);
        if (button == ButtonType.OK)
            FacultyController.stage.close();
    }
}
