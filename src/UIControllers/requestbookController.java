package UIControllers;

import Actors.Admin;
import Actors.Database;
import Actors.Faculty;
import Actors.Student;
import Supplementary.Booking;
import Supplementary.BookingRequests;
import Supplementary.CurrentLoggenInUser;
import Utils.Utilities;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.awt.print.Book;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class requestbookController {
    @FXML AnchorPane anchor_with_table;
    @FXML ChoiceBox class_number;
    @FXML JFXDatePicker date_to_book;
    @FXML JFXTimePicker start_time;
    @FXML JFXTimePicker end_time;
    @FXML TextField purpose;

    private static String callingClass;

    public static void setCallingClass(String callingClas) {
        callingClass = callingClas;
    }

    @FXML
    public void handleCurrentBookings(ActionEvent event) {
        anchor_with_table.getChildren().clear();
        TableView tb = new TableView<>(generateDataInMap());
        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        sep.prefHeightProperty().bind(anchor_with_table.heightProperty());
        sep.prefWidth(10.0);
        sep.setLayoutX(-2.0);
        sep.setLayoutY(-2.0);
        tb.prefWidthProperty().bind(anchor_with_table.widthProperty());
        tb.prefHeightProperty().bind(anchor_with_table.heightProperty());
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
        anchor_with_table.getChildren().add(tb);
    }

    private ObservableList<Map> generateDataInMap() {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < Booking.bookings.size() ; ++i) {
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

    public void handleBackToHome(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource(callingClass));
        Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
        Main.primaryStage.show();
    }

    public void handleConfirmBooking(ActionEvent event) throws IOException, ParseException, ClassNotFoundException{
        String startTime = start_time.getValue().toString();
        String endTime= end_time.getValue().toString();
        String dateforbook = date_to_book.getValue().toString();
        String dateOfBook = Utilities.convertDateToDay(dateforbook);
        if (Utilities.determineValidTime(startTime, endTime, (String)class_number.getValue(), dateOfBook)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            if (entryPageController.userType.equals("Student")) {
				alert.setContentText("Your request has been submitted! Kindly wait for approval!");
			} else {
            	alert.setContentText("Your room has been booked.");
			}
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                HashMap<String, String> temp = new HashMap<>();
                temp.put("Day", dateOfBook);
                temp.put("Room Number", (String)class_number.getValue());
                temp.put("Start Time", startTime);
                temp.put("End Time", endTime);
                temp.put("Purpose", purpose.getText());
                temp.put("Requested by", entryPageController.userEmail);
                if (entryPageController.userType.equals("Student")) {
                    File file = new File("./src/DataFiles/bookingreqs.txt");
                    BookingRequests b = new BookingRequests();
                    if (file.exists()) {
                        b.setBookinRequests(b.deserialize());
                        System.out.println("exists");
                    } else {
                        b.newBooking();
                    }
                    b.addElement(temp);
                    b.serialize(b.getBookingrequests());
                }
                else {
                    Booking.bookings.add(temp);
                    Booking.serialize();
                }
                Parent newscene = FXMLLoader.load(getClass().getResource(callingClass));
                Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
                Main.primaryStage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The Requested Time Slot is not Available");
        }
    }

    public void showPreviousPage(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
		Database userDb = readDBFromFile();
		String typeOfUser = CurrentLoggenInUser.getCurrentUserType();
		if (typeOfUser.equals("Student")) {
			Parent newscene = FXMLLoader.load(getClass().getResource("student.fxml"));
			Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
			Main.primaryStage.show();
		} else if (typeOfUser.equals("Faculty")) {
			Parent newscene = FXMLLoader.load(getClass().getResource("faculty.fxml"));
			Main.primaryStage.setScene(new Scene(newscene,  1200, 800));
			Main.primaryStage.show();
		} else if (typeOfUser.equals("Admin")) {
			Parent newscene = FXMLLoader.load(getClass().getResource("admin.fxml"));
			Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
			Main.primaryStage.show();
		}
    }

	private Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./src/db.txt"));
		return ( (Database) oin.readObject() );
	}

	public void showAboutPage(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}
}
