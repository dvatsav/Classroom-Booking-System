package Utils;

import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewPDF{
    private static PDFViewer pdf;
    private static Stage stage;
    public static void openPDF() throws Exception{
        stage = new Stage();
        pdf = new PDFViewer();
        BorderPane borderPane = new BorderPane(pdf);
        Scene scene = new Scene(borderPane);
        stage.setTitle("PDFViewer");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
