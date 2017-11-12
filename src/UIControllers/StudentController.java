package UIControllers;

import Supplementary.Booking;
import Supplementary.Course;
import Supplementary.Room;
import Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class StudentController {
    @FXML private TextField student_search_course;
    @FXML private AnchorPane anchor_for_table;

    private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);


    @FXML
    public void handleGenerateCourse(KeyEvent keyEvent) {
        anchor_for_table.getChildren().clear();
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.prefHeightProperty().bind(anchor_for_table.heightProperty());
        sep.prefWidth(10.0);
        sep.setLayoutX(-2.0);
        sep.setLayoutY(-2.0);
        TableView<Course> table_student = new TableView<>();
        table_student.prefWidthProperty().bind(anchor_for_table.widthProperty());
        table_student.prefHeightProperty().bind(anchor_for_table.heightProperty());
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
        anchor_for_table.getChildren().add(table_student);
        anchor_for_table.getChildren().add(sep);
    }

    @FXML
    public void handleCurrentBookings(ActionEvent event) {
        anchor_for_table.getChildren().clear();
        TableView tb = new TableView<>(generateDataInMap());
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.prefHeightProperty().bind(anchor_for_table.heightProperty());
        sep.prefWidth(10.0);
        sep.setLayoutX(-2.0);
        sep.setLayoutY(-2.0);
        tb.prefWidthProperty().bind(anchor_for_table.widthProperty());
        tb.prefHeightProperty().bind(anchor_for_table.heightProperty());
        TableColumn<Map, String> col1 = new TableColumn<>("Day");
        TableColumn<Map, String> col2 = new TableColumn<>("Room Number");
        TableColumn<Map, String> col3 = new TableColumn<>("Purpose");
        TableColumn<Map, String> col4 = new TableColumn<>("Start Time");
        TableColumn<Map, String> col5 = new TableColumn<>("End Time");
        col1.setCellValueFactory(new MapValueFactory("Day"));
        col2.setCellValueFactory(new MapValueFactory("Room Number"));
        col3.setCellValueFactory(new MapValueFactory("Purpose"));
        col4.setCellValueFactory(new MapValueFactory("Start Time"));
        col5.setCellValueFactory(new MapValueFactory("End Time"));
        tb.setEditable(true);
        tb.getSelectionModel().setCellSelectionEnabled(true);
        tb.getColumns().setAll(col1, col2, col3, col4, col5);
        Callback<TableColumn<Map, String>,TableCell<Map, String>>
                cellFactoryForMap = new Callback<TableColumn<Map, String>, TableCell<Map, String>>() {
            @Override
            public TableCell<Map, String> call(TableColumn<Map, String> p) {
                return new TextFieldTableCell<>(new StringConverter() {
                    @Override
                    public String toString(Object t) {
                        return t.toString();
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                });
            }
        };
        col1.setCellFactory(cellFactoryForMap);
        col2.setCellFactory(cellFactoryForMap);
        col3.setCellFactory(cellFactoryForMap);
        col4.setCellFactory(cellFactoryForMap);
        col5.setCellFactory(cellFactoryForMap);
        anchor_for_table.getChildren().add(tb);
        anchor_for_table.getChildren().add(sep);
    }

    private ObservableList<Map> generateDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0 ; i < Booking.bookings.size() ; ++i) {
            Map<String, String> dataRow = new HashMap<>();

            dataRow.put("Day", (String)Booking.bookings.get(i).get("Day"));
            dataRow.put("Room Number", (String)Booking.bookings.get(i).get("Room Number"));
            dataRow.put("Purpose", (String)Booking.bookings.get(i).get("Purpose"));
            dataRow.put("Start Time", (String)Booking.bookings.get(i).get("Start Time"));
            dataRow.put("End Time", (String)Booking.bookings.get(i).get("End Time"));
            allData.add(dataRow);
        }
        return allData;
    }

    @FXML
    public void handleBookRoom(ActionEvent event) throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("bookRoom.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }
}
