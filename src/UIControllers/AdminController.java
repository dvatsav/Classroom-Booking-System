package UIControllers;

import Supplementary.AccountRequests;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class AdminController {

    @FXML private TableView<AccountRequests> bookings_table;
    @FXML private TableColumn<AccountRequests, String> First_name;
    @FXML private TableColumn<AccountRequests, String> Last_name;
    @FXML private TableColumn<AccountRequests, String> Email_id;
    @FXML private TableColumn<AccountRequests, String> Roll_number;
    @FXML private TableColumn<AccountRequests, String> Acc_type;

    private final ObservableList<AccountRequests> data = FXCollections.observableArrayList(
            new AccountRequests("Deepak", "Srivatsav", "deepak16030@iiitd.ac.in", "student", "2016030"),
            new AccountRequests("Dheeraj", "Sanghi", "dsanghi@iiitd.ac.in", "faculty", "-")
    );

    @FXML
    public void handleAccReq(ActionEvent event) throws IOException {
        bookings_table.setEditable(true);
        First_name.setCellValueFactory(new PropertyValueFactory<AccountRequests, String>("firstName"));
        Last_name.setCellValueFactory(new PropertyValueFactory<AccountRequests, String>("lastName"));
        Email_id.setCellValueFactory(new PropertyValueFactory<AccountRequests, String>("emailID"));
        Acc_type.setCellValueFactory(new PropertyValueFactory<AccountRequests, String>("accType"));
        Roll_number.setCellValueFactory(new PropertyValueFactory<AccountRequests, String>("rollNumber"));
        bookings_table.setItems(data);
    }

    @FXML
    public void handleFileSelect(ActionEvent e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File for Upload");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("csv", "*.csv"));
        fileChooser.showOpenDialog(Main.primaryStage);
    }

    @FXML
    private void handleViewAction() {
        System.out.println("worked");
    }
    @FXML
    private void logout() throws IOException{
        Parent newscene = FXMLLoader.load(getClass().getResource("entryPage.fxml"));
        Main.primaryStage.setScene(new Scene(newscene, 600, 400));
        Main.primaryStage.show();
    }
}
