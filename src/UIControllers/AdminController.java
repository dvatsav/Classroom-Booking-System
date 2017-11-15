package UIControllers;

import Supplementary.AccountRequests;
import Supplementary.Booking;
import Supplementary.BookingRequests;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdminController {

    @FXML AnchorPane tableanchor;
    @FXML
    public void handleAccReq(ActionEvent event) throws IOException, ClassNotFoundException {
        AccountRequests a = new AccountRequests();
        a.setAccountRequests(a.deserialize());
        tableanchor.getChildren().clear();
        TableView tb = new TableView<>(generateDataInMap3(a));
        tb.prefWidthProperty().bind(tableanchor.widthProperty());
        tb.prefHeightProperty().bind(tableanchor.heightProperty());
        TableColumn<Map, String> col1 = new TableColumn<>("User Type");
        TableColumn<Map, String> col2 = new TableColumn<>("First Name");
        TableColumn<Map, String> col3 = new TableColumn<>("Last Name");
        TableColumn<Map, String> col4 = new TableColumn<>("Phone Number");
        TableColumn<Map, String> col5 = new TableColumn<>("Email ID");
        TableColumn<Map, String> col6 = new TableColumn<>("Password");
        TableColumn<Map, String> col7 = new TableColumn<>("Date of Birth");
        TableColumn<Map, String> col8 = new TableColumn<>("Roll Number");
        TableColumn<Map, String> col9 = new TableColumn<>("Branch");

        col1.setCellValueFactory(new MapValueFactory("User Type"));
        col2.setCellValueFactory(new MapValueFactory("First Name"));
        col3.setCellValueFactory(new MapValueFactory("Last Name"));
        col4.setCellValueFactory(new MapValueFactory("Phone Number"));
        col5.setCellValueFactory(new MapValueFactory("Email ID"));
        col6.setCellValueFactory(new MapValueFactory<>("Password"));
        col7.setCellValueFactory(new MapValueFactory<>("Date of Birth"));
        col8.setCellValueFactory(new MapValueFactory<>("Roll Number"));
        col9.setCellValueFactory(new MapValueFactory<>("Branch"));

        //tb.setEditable(true);
        tb.getSelectionModel().setCellSelectionEnabled(true);
        tb.getColumns().setAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);
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
        col6.setCellFactory(cellFactoryForMap);
        col7.setCellFactory(cellFactoryForMap);
        col8.setCellFactory(cellFactoryForMap);
        col9.setCellFactory(cellFactoryForMap);
        tableanchor.getChildren().add(tb);
    }

    public ObservableList<Map> generateDataInMap3(AccountRequests a) {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < a.getAccountRequests().size() ; ++i) {
            Map<String, String> dataRow = new HashMap<>();

            dataRow.put("User Type", (String)a.getAccountRequests().get(i).get("User Type"));
            dataRow.put("First Name", (String)a.getAccountRequests().get(i).get("First Name"));
            dataRow.put("Last Name", (String)a.getAccountRequests().get(i).get("Last Name"));
            dataRow.put("Phone Number", (String)a.getAccountRequests().get(i).get("Phone Number"));
            dataRow.put("Email ID", (String)a.getAccountRequests().get(i).get("Email ID"));
            dataRow.put("Password", (String)a.getAccountRequests().get(i).get("Password"));
            dataRow.put("Date of Birth", (String)a.getAccountRequests().get(i).get("Date of Birth"));
            dataRow.put("Roll Number", (String)a.getAccountRequests().get(i).get("Roll Number"));
            dataRow.put("Branch", (String)a.getAccountRequests().get(i).get("Branch"));
            allData.add(dataRow);
        }
        return allData;
    }

    @FXML
    public void handleBookReq(ActionEvent event) throws ClassNotFoundException, IOException{
        BookingRequests b = new BookingRequests();
        b.setBookinRequests(b.deserialize());
        System.out.println(b.getBookingrequests());
        tableanchor.getChildren().clear();
        TableView tb = new TableView<>(generateDataInMap2(b));
        tb.prefWidthProperty().bind(tableanchor.widthProperty());
        tb.prefHeightProperty().bind(tableanchor.heightProperty());
        TableColumn<Map, String> col1 = new TableColumn<>("Day");
        TableColumn<Map, String> col2 = new TableColumn<>("Room Number");
        TableColumn<Map, String> col3 = new TableColumn<>("Purpose");
        TableColumn<Map, String> col4 = new TableColumn<>("Start Time");
        TableColumn<Map, String> col5 = new TableColumn<>("End Time");
        TableColumn<Map, String> col6 = new TableColumn<>("Requested by");
        col1.setCellValueFactory(new MapValueFactory("Day"));
        col2.setCellValueFactory(new MapValueFactory("Room Number"));
        col3.setCellValueFactory(new MapValueFactory("Purpose"));
        col4.setCellValueFactory(new MapValueFactory("Start Time"));
        col5.setCellValueFactory(new MapValueFactory("End Time"));
        col6.setCellValueFactory(new MapValueFactory<>("Requested by"));
        //tb.setEditable(true);
        tb.getSelectionModel().setCellSelectionEnabled(true);
        tb.getColumns().setAll(col1, col2, col3, col4, col5, col6);
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
        col6.setCellFactory(cellFactoryForMap);
        tableanchor.getChildren().add(tb);
        ContextMenu cm = new ContextMenu();
        MenuItem item1 = new MenuItem("Accept");
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String, String> hm = new HashMap<String, String>((HashMap)tb.getSelectionModel().getSelectedItem());
                Booking.bookings.add(hm);
                tb.getItems().remove(hm);
                File file = new File("./src/DataFiles/bookingreqs.txt");
                BookingRequests b = new BookingRequests();
                if (file.exists()) {
                    try {
                        b.setBookinRequests(b.deserialize());
                        System.out.println(hm);
                        b.removeBooking(hm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    b.newBooking();
                }
                try {
                    b.serialize(b.getBookingrequests());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        MenuItem item2 = new MenuItem("Reject");
        item2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String, String> hm = new HashMap<String, String>((HashMap)tb.getSelectionModel().getSelectedItem());
                tb.getItems().remove(hm);
                File file = new File("./src/DataFiles/bookingreqs.txt");
                BookingRequests b = new BookingRequests();
                if (file.exists()) {
                    try {
                        b.setBookinRequests(b.deserialize());
                        b.removeBooking(hm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    b.newBooking();
                }
                try {
                    b.serialize(b.getBookingrequests());
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
        cm.getItems().add(item1);
        cm.getItems().add(item2);
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

    }

    public ObservableList<Map> generateDataInMap2(BookingRequests b) {
        ObservableList<Map> allData = FXCollections.observableArrayList();
        for (int i = 0; i < b.getBookingrequests().size() ; ++i) {
            Map<String, String> dataRow = new HashMap<>();

            dataRow.put("Day", (String)b.getBookingrequests().get(i).get("Day"));
            dataRow.put("Room Number", (String)b.getBookingrequests().get(i).get("Room Number"));
            dataRow.put("Purpose", (String)b.getBookingrequests().get(i).get("Purpose"));
            dataRow.put("Start Time", (String)b.getBookingrequests().get(i).get("Start Time"));
            dataRow.put("End Time", (String)b.getBookingrequests().get(i).get("End Time"));
            dataRow.put("Requested by", (String)b.getBookingrequests().get(i).get("Requested by"));
            allData.add(dataRow);
        }
        return allData;
    }

    @FXML
    public void handleBookRoom(ActionEvent event) throws IOException {
        Parent newscene = FXMLLoader.load(getClass().getResource("requestbook.fxml"));
        requestbookController.setCallingClass("admin.fxml");
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
    private void handleLogout() throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }

	public void showAboutPage(ActionEvent actionEvent) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.show();
	}
}
