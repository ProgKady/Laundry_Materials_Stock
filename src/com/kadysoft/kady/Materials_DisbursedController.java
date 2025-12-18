
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class Materials_DisbursedController implements Initializable {

    Connection conn = null;
  
    ResultSet rs = null;
  
    PreparedStatement pst = null;
    
    
    
    @FXML
    private JFXTextField quantity;

    
    
    @FXML
    private JFXTextField date;

    
    
    @FXML
    private ComboBox<String> item;

    @FXML
    private JFXButton add;

    @FXML
    private ComboBox<String> section,forbid;

    @FXML
    private JFXButton refresh;

    @FXML
    private TableView<String> table;

    
    public static String useb,drib;
    
    
    
    
    
    
    
    
    @FXML
    void addaction(ActionEvent event) {

        String dat=date.getText();
        String ite=item.getEditor().getText();
        String quan=quantity.getText();
        String sec=section.getEditor().getText();
       
        try {
            
                String reg = "insert into Mat_Dis (Date, Item, Quantity, Section) values (?,?,?,?)";
                pst = conn.prepareStatement(reg);
                pst.setString(1,dat);
                pst.setString(2,ite);
                pst.setString(3,quan);
                pst.setString(4,sec);
                pst.execute(); 
                }
              
            catch(Exception e){ 
            }
            finally {
            try{
                rs.close();
                pst.close(); 
                
              Notifications noti = Notifications.create();
              noti.title("Successful");
              noti.text("Added to Materials Disbursed Successfully");
              noti.hideAfter(Duration.seconds(3));
              noti.position(Pos.CENTER);
              noti.showInformation();
                
              refresh.fire();
                
            }catch(Exception e){}}    
            
        
            
    }
    
    
     @FXML
    void refreshaction(ActionEvent event) throws JRException, IOException {

    
        
        
     
        table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Mat_Dis";
            pst=conn.prepareStatement(sql);  
            rs=pst.executeQuery();
            
        ///////////////////////////////////////////////////////////////
            
        for (int i=0;i<rs.getMetaData().getColumnCount();i++) {
            final int j=i;
            TableColumn col=new TableColumn(rs.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                     return new SimpleStringProperty(param.getValue().get(j).toString());
                     
                }
                
            });
            table.getColumns().addAll(col);
            
            
        }
        
        //While getting info
        
        while (rs.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
            
        }
        
        table.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
          
        }
        finally {
            
            try{
                
                rs.close();
                pst.close();

            }
            catch(Exception e){
                
            }
         } 
         
       
        
          TableFilter filter = new TableFilter(table);

      
        
        
    }


    
    
    @FXML
    void itemaction(Event event) {

    this.item.getItems().clear();
    try {
      BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Items.kady"));
      String line;
      while ((line = buf.readLine()) != null) {
        this.item.getItems().addAll(new String[] { line });
      } 
      buf.close();
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {}
        
        
    }

    
    
    @FXML
    void sectionaction(Event event) {

    this.section.getItems().clear();
    try {
      BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Sections.kady"));
      String line;
      while ((line = buf.readLine()) != null) {
        this.section.getItems().addAll(new String[] { line });
      } 
      buf.close();
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {}
        
        
    }
    
    
    
     @FXML
    void forbidaction(Event event) {

    this.forbid.getItems().clear();
    try {
      BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Forbidden.kady"));
      String line;
      while ((line = buf.readLine()) != null) {
        this.forbid.getItems().addAll(new String[] { line });
      } 
      buf.close();
    } catch (FileNotFoundException fileNotFoundException) {
    
    } catch (IOException iOException) {}
        
        
    }
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        conn=che_db.java_che_db();
        
      useb=System.getProperty("user.name");
      try {
      BufferedReader buf = new BufferedReader(new FileReader("PCs\\"+useb+".kady"));
      drib=buf.readLine();
      buf.close();   
      } catch (IOException ex) {       
      //Alert
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Fatal Error");
      alert.setContentText("Fatal Error while reading user file.\nWe can't find the specified file.");
      alert.setResizable(false);
      DialogPane dialogPane = alert.getDialogPane();
      dialogPane.getStylesheets().add(
    getClass().getResource("cupertino-dark.css").toExternalForm());
      alert.showAndWait();
      Stage jk = (Stage)this.item.getScene().getWindow();
      jk.close();
      }
      
      
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    date.setText(value1);
        
        
    }    
    
}
