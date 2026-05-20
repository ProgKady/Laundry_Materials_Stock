
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.StringConverter;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author KADY
 */
public class AMR_ChemicalConsumptionController implements Initializable {

    Connection connn=null;
    ResultSet rss=null;
    PreparedStatement pstt=null;
    
    Connection connn1=null;
    ResultSet rss1=null;
    PreparedStatement pstt1=null;
    
    
    @FXML
    private TableView<String> datatable;

    @FXML
    private MenuItem getallaudit,print,consum;
    
    @FXML
    private Hyperlink con,con1,quaa,quaa1;
    
    @FXML
    private VBox pany;
    
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
    void consumaction(ActionEvent event) {
        
        
        
        
      con.setVisible(false);
      con1.setVisible(true);
      quaa.setVisible(false);
      quaa1.setVisible(true);
      
        
     
      ComboBox myuser1=new ComboBox ();
      myuser1.setMinSize(150, 30);
      myuser1.setStyle("-fx-font-size:15;-fx-font-weight:bold;");
      myuser1.setPromptText("Choose One");
      myuser1.getItems().addAll("Input","Output","Adding","Adding Shipment");
              
      ComboBox myuser2=new ComboBox ();
      myuser2.setMinSize(150, 30);
      myuser2.setStyle("-fx-font-size:15;-fx-font-weight:bold;");
      myuser2.setPromptText("Choose One");
      myuser2.getItems().addAll("ASC","DESC");
      
      ToggleGroup tg=new ToggleGroup();
      
      JFXDatePicker dp1=new JFXDatePicker ();
      dp1.setDisable(true);
      JFXDatePicker dp2=new JFXDatePicker ();
      dp2.setDisable(true);
      
      JFXTextField tf11=new JFXTextField ("");
      tf11.setPromptText("Write Month.");
      tf11.setDisable(true);
      JFXTextField tf22=new JFXTextField ("");
      tf22.setPromptText("Write Year.");
      tf22.setDisable(true);
      
      JFXTextField tf33=new JFXTextField ("");
      tf33.setPromptText("Write Year.");
      tf33.setDisable(true);
      
      JFXRadioButton r1=new JFXRadioButton("Week");
      r1.setToggleGroup(tg);
      r1.setOnAction( op ->{
          dp1.setDisable(false);
          dp2.setDisable(false);
          
          tf11.setDisable(true);
          tf22.setDisable(true);
          tf33.setDisable(true);
      });
      
      
      
      JFXRadioButton r2=new JFXRadioButton("Month");
      r2.setToggleGroup(tg);
      r2.setOnAction( op ->{
          tf11.setDisable(false);
          tf22.setDisable(false);
          
          tf33.setDisable(true);
          dp1.setDisable(true);
          dp2.setDisable(true);
      });
      
      
      
      JFXRadioButton r3=new JFXRadioButton("Year");
      r3.setToggleGroup(tg);
      r3.setOnAction( op ->{
          tf33.setDisable(false);
          
          tf11.setDisable(true);
          tf22.setDisable(true);
          dp1.setDisable(true);
          dp2.setDisable(true);
      });
      
      JFXTextField tf44=new JFXTextField ("");
      tf44.setPromptText("Write Limit.");
      tf44.setDisable(true);
      
      JFXCheckBox ch=new JFXCheckBox ("Limit");
      ch.setOnAction( op ->{
          if (ch.isSelected()) {
              tf44.setDisable(false);
          }
          else {
              tf44.setDisable(true);
          }
          
      });
      
      
 dp1.setConverter(new StringConverter<LocalDate>() {
 String pattern = "yyyy-MM-dd";
 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

 {
     dp1.setPromptText(pattern.toLowerCase());
 }

 @Override public String toString(LocalDate date) {
     if (date != null) {
         return dateFormatter.format(date);
     } else {
         return "";
     }
 }

 @Override public LocalDate fromString(String string) {
     if (string != null && !string.isEmpty()) {
         return LocalDate.parse(string, dateFormatter);
     } else {
         return null;
     }
 }
}); 
 
 
 dp2.setConverter(new StringConverter<LocalDate>() {
 String pattern = "yyyy-MM-dd";
 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

 {
     dp2.setPromptText(pattern.toLowerCase());
 }

 @Override public String toString(LocalDate date) {
     if (date != null) {
         return dateFormatter.format(date);
     } else {
         return "";
     }
 }

 @Override public LocalDate fromString(String string) {
     if (string != null && !string.isEmpty()) {
         return LocalDate.parse(string, dateFormatter);
     } else {
         return null;
     }
 }
}); 
      
      
      VBox vboxy=new VBox ();
      vboxy.setSpacing(10);
      vboxy.getChildren().addAll(myuser1,myuser2,r1,dp1,dp2,r2,tf11,tf22,r3,tf33,ch,tf44);
      Alert alert2 = new Alert(Alert.AlertType.WARNING);
      alert2.setTitle("Fill Me Please");
      alert2.setHeaderText("Please be careful, these info are important.");
      alert2.setContentText("Hello, Please tell me: What are your options?.");
      alert2.setGraphic(vboxy);
      alert2.setResizable(false);
      DialogPane dialogPane2 = alert2.getDialogPane();
      dialogPane2.getStylesheets().add(
    getClass().getResource("cupertino-light.css").toExternalForm());
      Optional<ButtonType> option2 = alert2.showAndWait();
      if (option2.get() == null) {con.setVisible(true);
        con1.setVisible(false);} 
      else if (option2.get() == ButtonType.OK) {
          if (myuser2.getSelectionModel().getSelectedItem()==null) {
              Notifications noti = Notifications.create();
              noti.title("Fatal Error!");
              noti.text("We Can't continue, Please choose one option.");
              noti.position(Pos.CENTER);
              noti.hideAfter(Duration.seconds(3));
              noti.showError();
              con.setVisible(true);
              con1.setVisible(false);
          }
          else {
              ///////////////////////Your Statements////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
              //Everything
              
              
              if (r1.isSelected()==true) {
                  
                  if (ch.isSelected()==true) {  //////WEEK
                      //Continue if selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              String limt=tf44.getText();
              String do1,do2;
              do1=dp1.getEditor().getText();
              do2=dp2.getEditor().getText();
              
              try{
                  
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND (Date) BETWEEN ('"+do1+"') AND ('"+do2+"') GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+" LIMIT "+limt+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  else {
                      //same but Continue not selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              String limt=tf44.getText();
              String do1,do2;
              do1=dp1.getEditor().getText();
              do2=dp2.getEditor().getText();
              
              try{
            
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND (Date) BETWEEN ('"+do1+"') AND ('"+do2+"') GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  
              }
              else if (r2.isSelected()==true) { //////MONTH
                  
                  if (ch.isSelected()==true) {
                      //Continue if selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              String limt=tf44.getText();
              String doo1,doo2,he1;
              he1=tf11.getText();
              int he2=Integer.parseInt(he1);
              if (he2>=1&&he2<=9) {
                  doo1="0"+tf11.getText();
              }
              else {
                  doo1=tf11.getText();
              }
              
              doo2=tf22.getText();
              
              
              try{
            
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND strftime('%Y-%m', Date) = '"+doo2+"-"+doo1+"' GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+" LIMIT "+limt+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;               
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  else {
                      //same but Continue not selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              
              String limt=tf44.getText();
              String doo1,doo2,he1;
              he1=tf11.getText();
              int he2=Integer.parseInt(he1);
              if (he2>=1&&he2<=9) {
                  doo1="0"+tf11.getText();
              }
              else {
                  doo1=tf11.getText();
              }
              doo2=tf22.getText();
              
              
              try{
            
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND strftime('%Y-%m', Date) = '"+doo2+"-"+doo1+"' GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  
              }
              else if (r3.isSelected()==true) { //////YEAR
                  
                  if (ch.isSelected()==true) {
                      //Continue if selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              
              String limt=tf44.getText();
              String dooo1;
              dooo1=tf33.getText();
              
              
              try{
            
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND Date >='"+dooo1+"-01-01' AND Date <='"+dooo1+"-12-31' GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+" LIMIT "+limt+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  else {
                      //same but Continue not selected
                      
                        
             //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
              String mu1=myuser1.getSelectionModel().getSelectedItem().toString();   // input, output
              String mu2=myuser2.getSelectionModel().getSelectedItem().toString();   // asc, desc
              
              String limt=tf44.getText();
              String dooo1;
              dooo1=tf33.getText();
              
              
              try{
            
            String sql ="SELECT Date, Chemical_Name, SUM(Quantity),Price , SUM(Total_Consumption) FROM Consumption WHERE Status='"+mu1+"' AND Date >='"+dooo1+"-01-01' AND Date <='"+dooo1+"-12-31' GROUP BY Chemical_Name ORDER BY SUM(Total_Consumption) "+mu2+"";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
            
        datatable.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
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
              
                TableFilter filter = new TableFilter(datatable);
              
              
              ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
                      
                  }
                  
              }
              
              else {
                  
                  //Noti to Choose one filter via week or month or year
                  Notifications noti = Notifications.create();
                  noti.title("Fatal Error");
                  noti.text("You must choose one filter via week or month or year.");
                  noti.hideAfter(Duration.seconds(3));
                  noti.position(Pos.CENTER);
                  noti.showError(); 
                  
                  
              }
              
           }
          }
      else if (option2.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, We can't continue.");
      noti.position(Pos.CENTER);
      noti.hideAfter(Duration.seconds(3));
      noti.showInformation();con.setVisible(true);
        con1.setVisible(false);}else {con.setVisible(true);
        con1.setVisible(false);}
              
              
              
              /////////////////////////////////////////////////////////////
          
       
              /////////////////////////////////////////////////////////////
          

        
        
        
        
    }
    
    
    
    
    
    @FXML
    void exporttoexcelaction (ActionEvent event) throws IOException {
        
        
        /////////////////////////////////////////////////////////////////////////
        
        Workbook workbook = new XSSFWorkbook();
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
        dialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", new String[] { "*.xlsx" }));
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
    void quaaaction(MouseEvent event) throws JRException  {
        
        
       double total=0.0;   
       for (int i= 0;i<datatable.getItems().size();i++){
           total = total+Double.valueOf(String.valueOf(datatable.getColumns().get(5).getCellObservableValue(i).getValue()));
           }
       quaa.setText(Double.toString(total));
        
    }
     
      @FXML
    void quaa1action(MouseEvent event) throws JRException  {
        
          double total=0.0;   
       for (int i= 0;i<datatable.getItems().size();i++){
           total = total+Double.valueOf(String.valueOf(datatable.getColumns().get(2).getCellObservableValue(i).getValue()));
           }
       quaa1.setText(Double.toString(total));
        
        
    }
    
    
    @FXML
    void con1action(MouseEvent event) throws JRException  {
        
      
       
       double total1=0.0;   
       for (int i= 0;i<datatable.getItems().size();i++){
           total1 = total1+Double.valueOf(String.valueOf(datatable.getColumns().get(4).getCellObservableValue(i).getValue()));
           }
       con1.setText(Double.toString(total1)+"      $");
       
 
    }
    
     
    
    @FXML
    void conaction(MouseEvent event) throws JRException  {
        
       double total=0.0;   
       for (int i= 0;i<datatable.getItems().size();i++){
           total = total+Double.valueOf(String.valueOf(datatable.getColumns().get(7).getCellObservableValue(i).getValue()));
           }
       con.setText(Double.toString(total)+"      $");
       
//       double total1=0.0;   
//       for (int i= 0;i<datatable.getItems().size();i++){
//           total1 = total1+Double.valueOf(String.valueOf(datatable.getColumns().get(1).getCellObservableValue(i).getValue()));
//           }
//       con.setText(Double.toString(total1));
   
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
        
        
        
        
        con.setVisible(true);
        con1.setVisible(false);
        quaa.setVisible(true);
        quaa1.setVisible(false);
        
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
    
    connn1=che_db.java_che_db();
    
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
    
    getallaudit.fire();
    
    
   
    
    
    
    
        
    }    
    
}
