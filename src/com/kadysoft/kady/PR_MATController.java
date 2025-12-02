
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;


/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */


public class PR_MATController implements Initializable {

    
    Connection conn = null;
  
    ResultSet rs = null;
  
    PreparedStatement pst = null;
    
    
    
    @FXML
    private TableView<String> table;
    
    @FXML
    private JFXListView<String> item;

    @FXML
    private JFXListView<String> stk;
    
    @FXML
    private JFXListView<String> desc;
    
    @FXML
    private JFXListView<String> unittt;

    
    @FXML
    private JFXListView<String> quant;


    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField itemname;

    @FXML
    private JFXTextField stock;

    @FXML
    private JFXTextField description,unit;

    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXButton addbtn;

    @FXML
    private JFXButton clearbtn;

    @FXML
    private JFXButton createprbtn;
    
    @FXML
    private JFXTextArea rowarea;

     public static String useb,drib;
    

    @FXML
    void addbtnaction(ActionEvent event) {

        String oo,pp,ii,uu,bb;
        
        oo=itemname.getText();
        pp=stock.getText();
        ii=description.getText();
        uu=quantity.getText();
        bb=unit.getText();
        
        item.getItems().addAll(oo);
        stk.getItems().addAll(pp);
        desc.getItems().addAll(ii);
        quant.getItems().addAll(uu);
        unittt.getItems().addAll(bb);
        
        
        code.clear();
        itemname.clear();
        stock.clear();
        unit.clear();
        description.clear();
        quantity.clear();
        
    }

    @FXML
    void clearbtnaction(ActionEvent event) {

        item.getItems().clear();
        stk.getItems().clear();
        desc.getItems().clear();
        quant.getItems().clear();
        unittt.getItems().clear();
        
        code.clear();
        itemname.clear();
        stock.clear();
        unit.clear();
        description.clear();
        quantity.clear();
        
    }


    @FXML
    void createprbtnaction(ActionEvent event) throws IOException, InterruptedException {

        
        
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
        
        String source=""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\KADINIO.xlsx";
        String destination=System.getProperty("user.home")+"\\Desktop\\Purchase_Request_Of_"+value1+".xlsx";
        String linet = "cmd /C copy /Y "+source+" "+destination;
        Process p = Runtime.getRuntime().exec(linet);
        p.waitFor(); 
        
        try {

            FileInputStream file = new FileInputStream(new File(destination));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Cell cell0 = sheet.getRow(2).getCell(2);
            cell0.setCellValue(value1);
            int roww=4;
            for (int i = 0; i < item.getItems().size(); i++) {
            Cell cell1 = sheet.getRow(roww).getCell(1);
            cell1.setCellValue(item.getItems().get(i));
            Cell cell2 = sheet.getRow(roww).getCell(2);
            cell2.setCellValue(desc.getItems().get(i));
            Cell cell3 = sheet.getRow(roww).getCell(3);
            cell3.setCellValue(unittt.getItems().get(i));
            Cell cell4 = sheet.getRow(roww).getCell(4);
            cell4.setCellValue(quant.getItems().get(i));
            Cell cell5 = sheet.getRow(roww).getCell(5);
            cell5.setCellValue(stk.getItems().get(i));
            ++roww;  
            }
            FileOutputStream outFile =new FileOutputStream(new File(destination));
            workbook.write(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }          
                       
                       Notifications noti = Notifications.create();
                       noti.title("Successful!");
                       noti.text("Congratulation, PR Was Created!, it will launch now.");
                       noti.position(Pos.CENTER);
                       noti.showInformation();
                       Desktop desk=Desktop.getDesktop();
                       desk.open(new File (destination));
                       //Finish
    }

    
     @FXML
    void codeaction(KeyEvent event) {
        
        
        
      try {
          
      String sql = "select * from Stock where Code=?";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.code.getText());
      this.rs = this.pst.executeQuery();
      String add1 = this.rs.getString("Material_Name");
      this.itemname.setText(add1);
      
      String add2 = this.rs.getString("Unit");
      this.unit.setText(add2);
      
      String add3 = this.rs.getString("Stock");
      this.stock.setText(add3);
      
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
        this.rs.close();
        this.pst.close();
      
      } catch (Exception exception) {}
    } 
          
        
          

    }
    
    
          
      @FXML
    void tableaction(MouseEvent event) {

ObservableList<String> selectedItems = table.getSelectionModel().getSelectedItems();
rowarea.clear();
rowarea.appendText(selectedItems.toString().replace("[","").replace("]","").replace(", ","\n"));
//Read it...
//////////////////////////////TextFields Reading////////////////////////////Haaaaaaaaaaaaaaaaaaaaaacking Codeeeeeeeeeeeeeeeeeeeeeeeeees
try {
File bnm=new File (System.getProperty("user.home")+"\\row.kady");
bnm.createNewFile();
PrintWriter pw=new PrintWriter (new FileWriter (bnm));
pw.print(rowarea.getText());
pw.close();
BufferedReader bukf = new BufferedReader(new FileReader(bnm));
    id.setText(bukf.readLine());
    itemname.setText(bukf.readLine());
    unit.setText(bukf.readLine());
    stock.setText(bukf.readLine());
bukf.close();
bnm.delete();
/////////////////////////////////////////////////////////////////////////////////////    
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
      } catch (Exception exception) {}
    } 
/////////////////////////////////////////////////////////////////////////////////////
        
    }    
      
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        conn = db.java_db();
        
        
        table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Critical";
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
      
      Stage jk = (Stage)this.quantity.getScene().getWindow();
      jk.close();
          }
        
        
    }    
    
}
