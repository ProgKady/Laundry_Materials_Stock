
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author KADY
 */
public class ChemicalConsumptionController implements Initializable {

    Connection connn=null;
    ResultSet rss=null;
    PreparedStatement pstt=null;
    
    
    @FXML
    private TableView<String> datatable;

    @FXML
    private MenuItem getallaudit,print;
    
    @FXML
    private JFXTextField con;
    
    @FXML
    private Pane pany;
    
    @FXML
    private Spinner<Integer> fontsize;
    
    
    @FXML
    void fontsizeaction(MouseEvent event) {
        
        TextField tf=fontsize.getEditor();
        datatable.setStyle("-fx-font-weight:bold;-fx-font-size:"+tf.getText()+";");
    }

    @FXML
    void fontsizerelaction(KeyEvent event) {
   
    
        
    }
    
    
    
    
    @FXML
    void exporttoexcelaction (ActionEvent event) throws IOException {
        
        
        /////////////////////////////////////////////////////////////////////////
        
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Kadysoft");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < datatable.getColumns().size(); j++) {
            row.createCell(j).setCellValue(datatable.getColumns().get(j).getText());
        }

        for (int i = 0; i < datatable.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < datatable.getColumns().size(); j++) {
                if(datatable.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(datatable.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileChooser dialog = new FileChooser();
        dialog.setInitialDirectory(new File(System.getProperty("user.home")+"\\Desktop"));
        dialog.setInitialFileName("Kadysoft");
        dialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", new String[] { "*.xls" }));
        File dialogResult = dialog.showSaveDialog(null);
        String filePath = dialogResult.getAbsolutePath().toString();
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        Desktop desk=Desktop.getDesktop();
        desk.open(new File (filePath));
        
        /////////////////////////////////////////////////////////////////////////
        
    }
    
    
    
    
    @FXML
    void conaction(MouseEvent event) throws JRException  {
        
       double total=0.0;   
       for (int i= 0;i<datatable.getItems().size();i++){
           total = total+Integer.valueOf(String.valueOf(datatable.getColumns().get(5).getCellObservableValue(i).getValue()));
           }
       con.setText(Double.toString(total));
       
       
//       
//          double total=0.0;   
//       for (double i= 0;i<datatable.getItems().size();i++){
//           total = total+Double.valueOf(String.valueOf(datatable.getColumns().get(4).getCellObservableValue(Double.toString(i)).getValue().toString()));
//           }
//       con.setText(Double.toString(total));
       
        
    }

    
@FXML
    void printaction(ActionEvent event) throws JRException  {   
        
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout
    = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX
        = pageLayout.getPrintableWidth() / pany.getBoundsInParent().getWidth();
    double scaleY
        = pageLayout.getPrintableHeight() / pany.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    pany.getTransforms().add(scale);
    
    if (job != null && job.showPrintDialog(pany.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, pany);
      if (success) {
        job.endJob();

      }
    }
    pany.getTransforms().remove(scale);
        
        
    
    
        
    }
   

    @FXML
    void getallauditaction(ActionEvent event)  {
        
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Consumption";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
        ///////////////////////////////////////////////////////////////
            
        for (int i=0;i<rss.getMetaData().getColumnCount();i++) {
            final int j=i;
            TableColumn col=new TableColumn(rss.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                     return new SimpleStringProperty(param.getValue().get(j).toString());
                     
                }
                
            });
            datatable.getColumns().addAll(col);
            
            
        }
        
        //While getting info
        
        while (rss.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rss.getMetaData().getColumnCount();i++) {
                row.add(rss.getString(i));
            }
            data.add(row);
            
        }
        
        datatable.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
      //      JOptionPane.showMessageDialog(null, e);
        }
        finally {
            
            try{
                
                rss.close();
                pstt.close();

            }
            catch(Exception e){
                
            }
         } 
         
        // getauditbtn.setDisable(true);
        
          TableFilter filter = new TableFilter(datatable);

    }
    
    
    
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    connn=che_db.java_che_db();
    
    datatable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value1 = timeString;
    
    
    ////////////////////////////////////////////
    
    datatable.widthProperty().addListener((src, o, n) -> Platform.runLater(() -> {
  if (o != null && o.intValue() > 0) return; // already aligned
  for (Node node: datatable.lookupAll(".column-header > .label")) {
    if (node instanceof Label) ((Label)node).setAlignment(Pos.CENTER);
  }    
}));
    
    ////////////////////////////////////////////
        
    }    
    
}
