package Utils;

import com.qoppa.pdfViewerFX.PDFViewer;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * <h1>View PDF Class</h1>
 * <p>This class makes use of the jPDFViewerFX Library to select and view PDF files</p>
 */
public class ViewPDF{
    private static PDFViewer pdf;
    private static Stage stage;

    /**
     * This function selects and opens the PDF file
     * @throws Exception thrown when Lang Exception occurs
     */
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
