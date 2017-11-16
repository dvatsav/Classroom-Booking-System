package UIControllers;

import Actors.Database;
import Actors.Faculty;
import Actors.Student;
import Supplementary.Booking;
import Supplementary.BookingHelper;
import Supplementary.Course;
import Supplementary.CurrentLoggenInUser;
import Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.awt.print.Book;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyController {

    @FXML AnchorPane tableanchor;
	public static Stage stage;
	private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);

	@FXML
	public void handleNewCourse(ActionEvent event) throws IOException{
		Parent newscene = FXMLLoader.load(getClass().getResource("newcourse.fxml"));
		stage = new Stage();
		stage.resizableProperty().set(Boolean.FALSE);
		stage.setTitle("Request a New Course");
		stage.setScene(new Scene(newscene, 1200, 800));
		stage.show();
	}

    @FXML
    public void handleBookRoom(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("requestbook.fxml"));
        requestbookController.setCallingClass("faculty.fxml");
        Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
        Main.primaryStage.show();
    }

    @FXML
    public void handleCurrentBookings(ActionEvent event) {
		tableanchor.getChildren().clear();
		TableView tb = new TableView<>(generateDataInMap());
		tb.prefWidthProperty().bind(tableanchor.widthProperty());
		tb.prefHeightProperty().bind(tableanchor.heightProperty());
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
		tableanchor.getChildren().add(tb);
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

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

    public void showCoursesTaught(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
		tableanchor.getChildren().clear();
		Faculty currentUser = null;
		Database userDb = readDBFromFile();
		List<Faculty> fl = userDb.getFacultyDB();
		for (Faculty faculty : fl) {
			if (faculty.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				currentUser = faculty;
				break;
			}
		}
		ArrayList<Course> filteredCourses = new ArrayList<>();
		for (Course c : courses){
			if (c.getInstructor().equals(currentUser.getFirstName() + " " + currentUser.getLastName())) {
				filteredCourses.add(c);
			}
		}
		TableView tb = new TableView<>(generateDataInMap());
		tb.prefWidthProperty().bind(tableanchor.widthProperty());
		tb.prefHeightProperty().bind(tableanchor.heightProperty());
		TableColumn<Course, String> col1 = new TableColumn<>("Course Name");
		TableColumn<Course, String> col2 = new TableColumn<>("Course Code");
		TableColumn<Course, String> col3 = new TableColumn<>("Instructor");
		TableColumn<Course, String> col4 = new TableColumn<>("Credits");
		TableColumn<Course, String> col5 = new TableColumn<>("Pre Conditions");
		TableColumn<Course, String> col6 = new TableColumn<>("Post Conditions");
		tb.getColumns().add(col1);
		tb.getColumns().add(col2);
		tb.getColumns().add(col3);
		tb.getColumns().add(col4);
		tb.getColumns().add(col5);
		tb.getColumns().add(col6);
		col1.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		col2.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
		col3.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
		col4.setCellValueFactory(new PropertyValueFactory<Course, String>("creditsOffered"));
		col5.setCellValueFactory(new PropertyValueFactory<Course, String>("preReq"));
		col6.setCellValueFactory(new PropertyValueFactory<Course, String>("postCondition"));
		col6.prefWidthProperty().bind(tb.widthProperty().multiply(0.9));
		tb.setItems(FXCollections.observableArrayList(filteredCourses));
		tableanchor.getChildren().add(tb);
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

	public void showMyBooking(ActionEvent actionEvent)  {
		tableanchor.getChildren().clear();
		ArrayList<HashMap> bookings = null;
		try {
			bookings = Booking.deserialize();
		} catch (IOException | ClassNotFoundException | NullPointerException e) {
			System.out.println("No confirmedbooking.txt Found!");
//			e.printStackTrace();
		}
		ArrayList<BookingHelper> temp = new ArrayList<>();
		if (bookings != null) {
			for (HashMap booking : bookings) {
				if (booking != null) {
					if (booking.get("Requested by").equals(CurrentLoggenInUser.getCurrentUserEmail())) {
						BookingHelper tempObj = new BookingHelper((String) booking.get("Day"), (String) booking.get("Room Number"),
								(String) booking.get("Start Time"), (String) booking.get("End Time"),
								(String) booking.get("Purpose"), (String) booking.get("Requested by"));
//				System.out.println(tempObj);
						temp.add(tempObj);
					}
				}
			}
		}

		ObservableList tempMyCourse = FXCollections.observableArrayList(temp);
		// After getting all requests
		TableView<BookingHelper> tb = new TableView<>();
		tb.prefWidthProperty().bind(tableanchor.widthProperty());
		tb.prefHeightProperty().bind(tableanchor.heightProperty());
		TableColumn<BookingHelper, String> col1 = new TableColumn<>("Date/Day");
		TableColumn<BookingHelper, String> col2 = new TableColumn<>("Room Number");
		TableColumn<BookingHelper, String> col3 = new TableColumn<>("Start Time");
		TableColumn<BookingHelper, String> col4 = new TableColumn<>("End Time");
		TableColumn<BookingHelper, String> col5 = new TableColumn<>("Purpose");
		tb.getColumns().add(col1);
		tb.getColumns().add(col2);
		tb.getColumns().add(col3);
		tb.getColumns().add(col4);
		tb.getColumns().add(col5);
		col1.setCellValueFactory(new PropertyValueFactory<BookingHelper, String>("day"));
		col2.setCellValueFactory(new PropertyValueFactory<BookingHelper, String>("roomNumber"));
		col3.setCellValueFactory(new PropertyValueFactory<BookingHelper, String>("startTime"));
		col4.setCellValueFactory(new PropertyValueFactory<BookingHelper, String>("endTime"));
		col5.setCellValueFactory(new PropertyValueFactory<BookingHelper, String>("purpose"));
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Remove booking");
		mi1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				BookingHelper bookingHelper = tb.getSelectionModel().getSelectedItem();
				try {
					removeBooking(bookingHelper);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		cm.getItems().add(mi1);
		tb.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY && tb.getSelectionModel().getSelectedItem() != null) {
					cm.show(tb, event.getScreenX(), event.getScreenY());
				} else {
					cm.hide();
				}
			}
		});
		tb.setItems(tempMyCourse);
		tableanchor.getChildren().add(tb);

	}

	private void removeBooking(BookingHelper bookingHelper) throws IOException, ClassNotFoundException {
    	ArrayList<HashMap> booking = Booking.deserialize();

		for (HashMap hashMap : booking) {
			if (hashMap.get("Day").equals(bookingHelper.getDay()) && hashMap.get("Room Number").equals(bookingHelper.getRoomNumber()) &&
					hashMap.get("Start Time").equals(bookingHelper.getStartTime()) && hashMap.get("End Time").equals(bookingHelper.getEndTime()) &&
					hashMap.get("Purpose").equals(bookingHelper.getPurpose()) && hashMap.get("Requested by").equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				booking.remove(hashMap);
				break;
			}
		}
		Booking.serialize();
	}
}
