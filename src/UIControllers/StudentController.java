package UIControllers;

import Actors.Database;
import Actors.Student;
import Supplementary.*;
import Utils.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.apache.poi.ss.usermodel.Table;

import javax.rmi.CORBA.Util;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Student Controller Class</h1>
 * <p>This class controls all activities of the students GUI page</p>
 */
public class StudentController {
    @FXML private TextField student_search_course;
    @FXML private AnchorPane anchor_for_table;

    private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);

	/**
	 * This function initializes a view of all the courses that are available in the institute
	 * This is the first page that comes up when a student logs in
	 * @throws ParseException throws exception when there is a parsing error
	 */
	@FXML
	public void initialize() throws ParseException{
		anchor_for_table.getChildren().clear();
		TableView<Course> table_student = new TableView<>();
		table_student.prefWidthProperty().bind(anchor_for_table.widthProperty());
		table_student.prefHeightProperty().bind(anchor_for_table.heightProperty());
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
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Add to my List Of Courses");
		mi1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Course course = table_student.getSelectionModel().getSelectedItem();
				try {
					addCourseToStudentList(course);
					table_student.getItems().remove(course);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		cm.getItems().add(mi1);
		table_student.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					cm.show(table_student, event.getScreenX(), event.getScreenY());
				} else {
					cm.hide();
				}
			}
		});
		anchor_for_table.getChildren().add(table_student);
	}

	/**
	 * This Function adds a specific course to the course list of a student
	 * @param course object of course containing details about that course
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 */
	private void addCourseToStudentList(Course course) throws IOException, ClassNotFoundException {
		Database userDb = readDBFromFile();
		HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
		for (Student student : mp.values()) {
			if (student.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				if (!student.getMyCourses().contains(course)) {
					student.getMyCourses().add(course);
				}
				break;
			}
		}
		writeDBToFile(userDb);
	}

	/**
	 * This function reads the database
	 * @return returns the database object
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 */
	private Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./src/db.txt"));
		return ( (Database) oin.readObject() );
	}

	/**
	 * This function wrties to the database object and the serializes the file
	 * @param db database object
	 * @throws IOException when input or output stream is not initialized
	 */
	private void writeDBToFile(Database db) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/db.txt", false));
		out.writeObject(db);
		out.flush();
		out.close();
	}

	/**
	 * This function Generates and displays all pertinant courses to a student
	 * It filters out based on search parameter, non clashing courses and those that the student has
	 * not yet selected
 	 * @param keyEvent key event, checks whether the enter key has been pressed
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 * @throws ParseException Exception thrown when there is a string parsing error
	 */
	@FXML
    public void handleGenerateCourse(KeyEvent keyEvent) throws IOException, ClassNotFoundException, ParseException{

		if (keyEvent.getCode() == KeyCode.ENTER) {
			anchor_for_table.getChildren().clear();
			TableView<Course> table_student = new TableView<>();
			table_student.prefWidthProperty().bind(anchor_for_table.widthProperty());
			table_student.prefHeightProperty().bind(anchor_for_table.heightProperty());
			String searchedString = student_search_course.getText();
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

            Database userDb = readDBFromFile();
			HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
			Student student = null;
			ArrayList<Course> studCourses;
			for (Student s : mp.values())
				if (s.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail()))
					student = s;
			studCourses = new ArrayList<>(student.getMyCourses());
			ArrayList<Course> filteredCourses = new ArrayList<>(Utilities.courses);
			if (studCourses.size() > 0) {
				for (Course d : courses) {
					for (Course c : studCourses) {
						if (!Utilities.checkValidCourseTime(c, d) || c.equals(d)) {
							filteredCourses.remove(d);
						}
					}
				}
			}
			if (searchedString.equals("") || searchedString == null) {
				table_student.setItems(FXCollections.observableArrayList(filteredCourses));
			} else {
            	for (Course c : courses){
            		if (!c.getPostCondition().toLowerCase().replace(".", "").replace(",", "").contains(searchedString.toLowerCase())) {
						filteredCourses.remove(c);
					}
				}
				table_student.setItems(FXCollections.observableArrayList(filteredCourses));
			}

			ContextMenu cm = new ContextMenu();
			MenuItem mi1 = new MenuItem("Add to my List Of Courses");
			mi1.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Course course = table_student.getSelectionModel().getSelectedItem();
					try {
						addCourseToStudentList(course);
						table_student.getItems().remove(course);
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			cm.getItems().add(mi1);
			table_student.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if (event.getButton() == MouseButton.SECONDARY) {
						cm.show(table_student, event.getScreenX(), event.getScreenY());
					} else {
						cm.hide();
					}
				}
			});
			anchor_for_table.getChildren().add(table_student);
		}
    }

	/**
	 * This function generates the display of all currently booked rooms
	 * @param event Action event, on click of the button
	 */
    @FXML
    public void handleCurrentBookings(ActionEvent event) {
        anchor_for_table.getChildren().clear();
        TableView tb = new TableView<>(generateDataInMap());
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
    }

	/**
	 * This function is used to generate a Map object that will be used to populate the table
	 * @return returns an Observable list used to populate the map
	 */
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

	/**
	 * This function handles the request for booking a room
	 * @param event, On click event
	 * @throws IOException when input or output stream is not initialized
	 */
	@FXML
    public void handleBookRoom(ActionEvent event) throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("requestbook.fxml"));
        requestbookController.setCallingClass("student.fxml");
        Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
        Main.primaryStage.show();
    }

	/**
	 * This Function handles logging out of the user
	 * @param event, on click event
	 * @throws IOException when input or output stream is not initialized
	 */
    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

	/**
	 * This function handles the showing of all the courses selected by the user. The selected courses
	 * are retrieved from a database stored in the student class
	 * @param actionEvent action event handling object
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 */
	public void showMyCourses(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
		anchor_for_table.getChildren().clear();
		Student currentUser = null;
		Database userDb = readDBFromFile();
		HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
		for (Student student : mp.values()) {
			if (student.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				currentUser = student;
//				System.out.println("Here Boi");
				break;
			}
		}
		ObservableList<Course> tempMyCourse = FXCollections.observableArrayList(currentUser.getMyCourses());
		TableView<Course> currentTimeTable = new TableView<>();
		currentTimeTable.prefWidthProperty().bind(anchor_for_table.widthProperty());
		currentTimeTable.prefHeightProperty().bind(anchor_for_table.heightProperty());
		TableColumn<Course, String> col1 = new TableColumn<>("Course Name");
		TableColumn<Course, String> col2 = new TableColumn<>("Course Code");
		TableColumn<Course, String> col3 = new TableColumn<>("Instructor");
		TableColumn<Course, String> col4 = new TableColumn<>("Credits");
		TableColumn<Course, String> col5 = new TableColumn<>("Pre Conditions");
		TableColumn<Course, String> col6 = new TableColumn<>("Post Conditions");
		currentTimeTable.getColumns().add(col1);
		currentTimeTable.getColumns().add(col2);
		currentTimeTable.getColumns().add(col3);
		currentTimeTable.getColumns().add(col4);
		currentTimeTable.getColumns().add(col5);
		currentTimeTable.getColumns().add(col6);
		col1.setCellValueFactory(new PropertyValueFactory<Course, String>("courseName"));
		col2.setCellValueFactory(new PropertyValueFactory<Course, String>("courseCode"));
		col3.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
		col4.setCellValueFactory(new PropertyValueFactory<Course, String>("creditsOffered"));
		col5.setCellValueFactory(new PropertyValueFactory<Course, String>("preReq"));
		col6.setCellValueFactory(new PropertyValueFactory<Course, String>("postCondition"));
		col6.prefWidthProperty().bind(currentTimeTable.widthProperty().multiply(0.9));
		currentTimeTable.setItems(tempMyCourse);
		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("Remove from my List Of Courses");
		mi1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Course course = currentTimeTable.getSelectionModel().getSelectedItem();
				try {
					removeCourseFromMyCourses(course);
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		cm.getItems().add(mi1);
		currentTimeTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY && currentTimeTable.getSelectionModel().getSelectedItem() != null) {
					cm.show(currentTimeTable, event.getScreenX(), event.getScreenY());
				} else {
					cm.hide();
				}
			}
		});
		anchor_for_table.getChildren().add(currentTimeTable);
	}

	/**
	 * This function handles the removal of a course from the current timetable of a student by removing the element from t
	 * the respective database in its student object
	 * @param course Course object
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 */
	private void removeCourseFromMyCourses(Course course) throws IOException, ClassNotFoundException {
		Database userDb = readDBFromFile();
		HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
		for (Student student : mp.values()) {
			if (student.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				ArrayList<Course> temp = student.getMyCourses();
				int index = 0;
				for (Course couresIter : temp) {
					if (couresIter.getCourseName().equals(course.getCourseName())) {
						break;
					}
					++index;
				}
				temp.remove(index);
				student.setMyCourses(temp);
				mp.replace(student.getRollNo(), student);
				break;
			}
		}
		userDb.setStudentsDB(mp);
		writeDBToFile(userDb);
	}

	/**
	 * This function shows a table with a list of all the faculty members
	 * @param actionEvent action event handling object
	 */
	public void showFaculty(ActionEvent actionEvent) {
		anchor_for_table.getChildren().clear();
		TableView<Course> tb = new TableView<>();
		tb.prefWidthProperty().bind(anchor_for_table.widthProperty());
		tb.prefHeightProperty().bind(anchor_for_table.heightProperty());
		TableColumn<Course, String> col1 = new TableColumn<>("Instructor Name");
		tb.getColumns().add(col1);
		col1.setCellValueFactory(new PropertyValueFactory<Course, String>("instructor"));
		tb.setItems(courses);
		anchor_for_table.getChildren().add(tb);
	}

	/**
	 * This function handles displaying the about and help page
	 * @param actionEvent action event handling object
	 * @throws IOException when input or output stream is not initialized
	 */
	public void showAboutPage(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}

	/**
	 * This function handles the showing of all booking request made by a particular student
	 * It its retrieved from the database stored in the respective student object
	 * @param actionEvent action event handling object
	 */
	public void showMyRequests(ActionEvent actionEvent) {
		anchor_for_table.getChildren().clear();
		BookingRequests br = new BookingRequests();
		ArrayList<HashMap> al = null;
		try {
			al = br.deserialize();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("No bookingreqs.txt Found!");
		}
		ArrayList<BookingHelper> temp = new ArrayList<>();

		for (HashMap hashMap : al) {
			if (hashMap != null && hashMap.get("Requested by").equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				BookingHelper tempObj = new BookingHelper((String) hashMap.get("Day"), (String) hashMap.get("Room Number"),
						(String) hashMap.get("Start Time"), (String) hashMap.get("End Time"),
						(String) hashMap.get("Purpose"), (String) hashMap.get("Requested by"));
				temp.add(tempObj);
			}
		}

		ObservableList tempMyCourse = FXCollections.observableArrayList(temp);
		TableView<BookingHelper> tb = new TableView<>();
		tb.prefWidthProperty().bind(anchor_for_table.widthProperty());
		tb.prefHeightProperty().bind(anchor_for_table.heightProperty());
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
				if (event.getButton() == MouseButton.SECONDARY) {
					cm.show(tb, event.getScreenX(), event.getScreenY());
				} else {
					cm.hide();
				}
			}
		});
		tb.setItems(tempMyCourse);
		anchor_for_table.getChildren().add(tb);
	}

	/**
	 * This function removes a particular booking from the bookinglist of a student
	 * @param bookingHelper contains an object containing data about current data row in table
	 * @throws IOException when input or output stream is not initialized
	 * @throws ClassNotFoundException exception thrown when requested class not found
	 */
	private void removeBooking(BookingHelper bookingHelper) throws IOException, ClassNotFoundException {
		BookingRequests br = new BookingRequests();
		ArrayList<HashMap> al = br.deserialize();

		for (HashMap hashMap : al) {
			if (hashMap.get("Day").equals(bookingHelper.getDay()) && hashMap.get("Room Number").equals(bookingHelper.getRoomNumber()) &&
					hashMap.get("Start Time").equals(bookingHelper.getStartTime()) && hashMap.get("End Time").equals(bookingHelper.getEndTime()) &&
					hashMap.get("Purpose").equals(bookingHelper.getPurpose()) && hashMap.get("Requested by").equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				al.remove(hashMap);
				break;
			}
		}
		br.setBookinRequests(al);
		br.serialize(al);

	}
}
