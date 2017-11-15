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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StudentController {
    @FXML private TextField student_search_course;
    @FXML private AnchorPane anchor_for_table;

    private ObservableList<Course> courses = FXCollections.observableArrayList(Utilities.courses);

	@FXML
	public void initialize() {
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

	private void addCourseToStudentList(Course course) throws IOException, ClassNotFoundException {
		Database userDb = readDBFromFile();
		HashMap<String, Student> mp = (HashMap<String, Student>) userDb.getStudentsDB();
		for (Student student : mp.values()) {
			if (student.getEmail().equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				student.getMyCourses().add(course);
				break;
			}
		}
//		System.out.println("added");
		writeDBToFile(userDb);
	}

	private Database readDBFromFile() throws IOException, ClassNotFoundException {
		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./src/db.txt"));
		return ( (Database) oin.readObject() );
	}

	private void writeDBToFile(Database db) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./src/db.txt", false));
		out.writeObject(db);
		out.flush();
		out.close();
	}

	@FXML
    public void handleGenerateCourse(KeyEvent keyEvent) {
//		initialize();
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
            if (searchedString.equals("") || searchedString == null)
            	table_student.setItems(courses);
            else {
            	ArrayList<Course> filteredCourses = new ArrayList<>();
            	for (Course c : courses){
            		if (c.getPostCondition().contains(searchedString)) {
            			filteredCourses.add(c);
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
        Parent newscene = FXMLLoader.load(getClass().getResource("requestbook.fxml"));
        requestbookController.setCallingClass("student.fxml");
        Main.primaryStage.setScene(new Scene(newscene, 1200, 800));
        Main.primaryStage.show();
    }

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

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
				if (event.getButton() == MouseButton.SECONDARY) {
					cm.show(currentTimeTable, event.getScreenX(), event.getScreenY());
				} else {
					cm.hide();
				}
			}
		});
//		System.out.println(currentUser);
//		System.out.println(currentUser.getMyCourses());
		anchor_for_table.getChildren().add(currentTimeTable);
	}

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

	public void showAboutPage(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}

	public void showMyRequests(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
		anchor_for_table.getChildren().clear();
		BookingRequests br = new BookingRequests();
		ArrayList<HashMap> al = br.deserialize();
		ArrayList<BookingHelper> temp = new ArrayList<>();

		for (HashMap hashMap : al) {
			if (hashMap.get("Requested by").equals(CurrentLoggenInUser.getCurrentUserEmail())) {
				BookingHelper tempObj = new BookingHelper((String) hashMap.get("Day"), (String) hashMap.get("Room Number"),
						(String) hashMap.get("Start Time"), (String) hashMap.get("End Time"),
						(String) hashMap.get("Purpose"), (String) hashMap.get("Requested by"));
//				System.out.println(tempObj);
				temp.add(tempObj);
			}
		}

		ObservableList tempMyCourse = FXCollections.observableArrayList(temp);
		// After getting all requests
		TableView<BookingHelper> tb = new TableView<>();
		tb.prefWidthProperty().bind(anchor_for_table.widthProperty());
		tb.prefHeightProperty().bind(anchor_for_table.heightProperty());
		TableColumn<BookingHelper, String> col1 = new TableColumn<>("Date/Day");
		TableColumn<BookingHelper, String> col2 = new TableColumn<>("Room Number");
		TableColumn<BookingHelper, String> col3 = new TableColumn<>("Start Time");
		TableColumn<BookingHelper, String> col4 = new TableColumn<>("End Time");
		TableColumn<BookingHelper, String> col5 = new TableColumn<>("Purpose");
//		temp.put("Day", dateOfBook);
//		temp.put("Room Number", (String)class_number.getValue());
//		temp.put("Start Time", startTime);
//		temp.put("End Time", endTime);
//		temp.put("Purpose", purpose.getText());
//		temp.put("Requested by", entryPageController.userEmail);
//		tb.getColumns().setAll(col1, col2, col3, col4, col5);
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
		tb.setItems(tempMyCourse);
		anchor_for_table.getChildren().add(tb);
	}
}
