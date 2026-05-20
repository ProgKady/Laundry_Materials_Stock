
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
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
 * @author ahmed.elkady
 */
public class FriscaController implements Initializable {

   
    
    
    
    
    
    
    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField material;

    @FXML
    private JFXTextField patch;

    @FXML
    private JFXTextField company,io1,io2,io3,io4,io5;

    @FXML
    private JFXTextField quantity;

    @FXML
    private JFXTextField price,code;

    @FXML
    private JFXButton save,update,delete;

   
    @FXML
    private JFXButton excel,uptodate;

   
    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextArea area;

    
   Connection connn = null;
  
    ResultSet rss = null;
  
    PreparedStatement pstt = null;
    
    @FXML
    private TableView<String> table;
    
    @FXML
    private JFXButton refresh;
    
    
    
    
    
    @FXML
    void mseaction(KeyEvent event) {

        
        String qa=quantity.getText();
        String pr=price.getText();
        
        double rt=Double.parseDouble(io2.getText());  ///
        
        double rty=Double.parseDouble(io1.getText());  ///stock
        
        double qaa=Double.parseDouble(qa);
        double prr=Double.parseDouble(pr);
        
        double ghr=qaa*prr;  ///
        
        double bd=ghr+rt;
        
        double lop=qaa+rty;
        
        io3.setText(Double.toString(ghr));
        
        io4.setText(Double.toString(bd));
        
        io5.setText(Double.toString(lop));
        
    }
    
    
    
    
    
    
    @FXML
    void deleteaction(ActionEvent event) {
        
       
            String idd=  id.getText();
            String datee= date.getText();
            String codee=  code.getText();
            String materiall= material.getText();
            String patchh= patch.getText();
          
            
        
        
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Shipment");
      alert.setHeaderText("Are you sure want to move this shipment to the Recycle Bin?\n\nCaution: You can't undo or restore this shipment again.");
      alert.setContentText("Shipment Name is: "+materiall+", And Its Patch Is: "+patchh+".");
      DialogPane dialogPane = alert.getDialogPane();
      dialogPane.getStylesheets().add(
      getClass().getResource("cupertino-light.css").toExternalForm());
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
      
          
          
            try {
            
            String sql = "delete from Frisca where ID='"+idd+"' and Date='"+datee+"' and  Code='"+codee+"'";
            pstt=connn.prepareStatement(sql);
            pstt.execute();
              
            }catch(Exception e){
              
            }
            finally {
            
            try{
                rss.close();
                pstt.close();
                
            }
            catch(Exception e){
                
            }
        }
            
              Notifications noti = Notifications.create();
              noti.title("Successful");
              noti.text("Successful Deletion");
              noti.hideAfter(Duration.seconds(3));
              noti.position(Pos.CENTER);
              noti.showInformation();
          
              id.clear();
              date.clear();
              code.clear();
              material.clear();
              patch.clear();
              company.clear();
              quantity.clear();
              price.clear();
            
        
          
      }
      else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, Shipment wasn't deleted.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      }else {}
        
        
        //Fire 
        
          refresh.fire();
        
        
        
    }
    
    
    
    
    
    
    @FXML
    void updateaction(ActionEvent event) {
        
            String idd=  id.getText();
            String datee= date.getText();
            String codee=  code.getText();
            String materiall= material.getText();
            String companyy= company.getText();
            String patchh= patch.getText();
            String quantityy= quantity.getText();
            String pricee=price.getText();
            
            
            try {
            
                String sql= "update Frisca set ID='"+idd+"', Date='"+datee+"', Code='"+codee+"', Material='"+materiall+"', PatchNo='"+patchh+"', Company='"+companyy+"', Quantity='"+quantityy+"', Price='"+pricee+"' where ID='"+idd+"' and Date='"+datee+"' and  Code='"+codee+"' ";

                pstt=connn.prepareStatement(sql);
                pstt.execute();
              
            }catch(Exception e){
              //  JOptionPane.showMessageDialog(null, e);
            }
            finally {
            
            try{
                rss.close();
                pstt.close();
                
            }
            catch(Exception e){
                
            }
        }
            
              Notifications noti = Notifications.create();
              noti.title("Successful");
              noti.text("Successful Updation");
              noti.hideAfter(Duration.seconds(3));
              noti.position(Pos.CENTER);
              noti.showInformation();
              
              id.clear();
              date.clear();
              code.clear();
              material.clear();
              patch.clear();
              company.clear();
              quantity.clear();
              price.clear();
        
        
          refresh.fire();
    }
    
    
    
    @FXML
    void tableaction(MouseEvent event) throws IOException {

        
ObservableList<String> selectedItems = table.getSelectionModel().getSelectedItems();
area.clear();
area.appendText(selectedItems.toString().replace("[","").replace("]","").replace(", ","\n"));
String codee=area.getText();
File bnm=new File (System.getProperty("user.home")+"\\Ship.kady");\
bnm.createNewFile();
PrintWriter pw=new PrintWriter (new FileWriter (bnm));
pw.print(area.getText());
pw.close();
BufferedReader bukf = new BufferedReader(new FileReader(bnm));
    id.setText(bukf.readLine());
    date.setText(bukf.readLine());
    code.setText(bukf.readLine());
    material.setText(bukf.readLine());
    patch.setText(bukf.readLine());
    company.setText(bukf.readLine());
    quantity.setText(bukf.readLine());
    price.setText(bukf.readLine());
bukf.close();
bnm.delete();    
        
    }
    
    
    
    @FXML
    void codeaction(KeyEvent event) throws InterruptedException {

    String coco=code.getText();
    
    if (coco.isEmpty()) {
        
        material.clear();
        company.clear();
        
    }
    
    else {
           try {
      String sql = "select * from Indexes where Code=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, coco);
      this.rss = this.pstt.executeQuery();
      String add2 = this.rss.getString("Chemical_Name");
      this.material.setText(add2);
      String add3 = this.rss.getString("Supplier");
      this.company.setText(add3);
      
      this.pstt.execute();
      
           }
              catch (Exception exception) {
    } 
        finally {
      try {
        this.rss.close();
        this.pstt.close();
      
      } catch (Exception exception) {}
    } 
           
           
           Thread.sleep(1000);
           
      ////////////////////What We Added///////////////
    
      
      try {
      
      String sqlio = "select * from Stock where Code=?";
      this.pstt = this.connn.prepareStatement(sqlio);
      this.pstt.setString(1, coco);
      this.rss = this.pstt.executeQuery();
      
      String add4 = this.rss.getString("Stock");
      io1.setText(add4);
      
      String add5 = this.rss.getString("Total_Price");
      io2.setText(add5);
      
      this.pstt.execute();
      
      
      ////////////////////////////////////////////////
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
        this.rss.close();
        this.pstt.close();
      
      } catch (Exception exception) {}
    } 
        
        
        
    }
      
    }
    
    
    
    
    
   
    
    @FXML
    void refreshaction(ActionEvent event) {

        
         
             table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Frisca";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
        ///////////////////////////////////////////////////////////////
            
        for (int i=0;i<rss.getMetaData().getColumnCount();i++) {
            final int j=i;
            TableColumn col=new TableColumn(rss.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                     return new SimpleStringProperty(param.getValue().get(j).toString());
                     
                }
                
            });
            table.getColumns().addAll(col);
            
            
        }
        
        //While getting info
        
        while (rss.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rss.getMetaData().getColumnCount();i++) {
                row.add(rss.getString(i));
            }
            data.add(row);
            
        }
        
        table.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
          
        }
        finally {
            
            try{
                
                rss.close();
                pstt.close();

            }
            catch(Exception e){
                
            }
         } 
         
       
        
          TableFilter filter = new TableFilter(table);

        
        
        
        
        
        
        
    }
    
    
    
    
    
    @FXML
    void excelaction(ActionEvent event) throws FileNotFoundException, IOException {

        
         /////////////////////////////////////////////////////////////////////////
        
        Workbook workbook = new XSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Kadysoft");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if(table.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString()); 
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
    void uptodateaction(ActionEvent event) {
        
      String newitemdatee,newitemcode,newitemname,newitemsupplierr,newitempatch,newitemquant,newitemunitpricee;
      
      newitemdatee=date.getText();
      newitemcode=code.getText();
      newitemname=material.getText();
      newitempatch=patch.getText();
      newitemsupplierr=company.getText();
      newitemquant=quantity.getText();
      newitemunitpricee=price.getText();
      
      String dsa=io3.getText();
      
      String hyr,hye;
      hyr=io4.getText();
      hye=io5.getText();
      
      
        
             
      
        
    }
    
    
    
    
    @FXML
    void saveaction(ActionEvent event) throws InterruptedException {

        
        
        
      String newitemdatee,newitemcode,newitemname,newitemsupplierr,newitempatch,newitemquant,newitemunitpricee;
      
      newitemdatee=date.getText();
      newitemcode=code.getText();
      newitemname=material.getText();
      newitempatch=patch.getText();
      newitemsupplierr=company.getText();
      newitemquant=quantity.getText();
      newitemunitpricee=price.getText();
      
      String dsa=io3.getText();
      
      String hyr,hye;
      hyr=io4.getText();
      hye=io5.getText();
      
      
      
      
      if (newitemname.isEmpty()||newitemsupplierr.isEmpty()||newitempatch.isEmpty()||newitemquant.isEmpty()||newitemunitpricee.isEmpty()) {
      Notifications noti = Notifications.create();
      noti.title("Saving Error!");
      noti.text("We are sorry, we can't add an empty value, some of fields is empty.");
      noti.position(Pos.CENTER);
      noti.showError();
      }
      
      else {
          /////////////////KADY///////////////
          
           try {

            String sql ="insert into Frisca (Date,Code,Material,PatchNo,Company,Quantity,Price) values (?,?,?,?,?,?,?) ";

            pstt=connn.prepareStatement(sql);
            
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcode);
            pstt.setString(3,newitemname);
            pstt.setString(4,newitempatch);
            pstt.setString(5,newitemsupplierr);
            pstt.setString(6,newitemquant);
            pstt.setString(7,newitemunitpricee);
            
            pstt.execute();
            
            
          ///////////Add Audit HEre///////////////
          
          
            String reg = "insert into Audit (Date,Code,Supplier,Chemical_Name,Type,Status,Section,Patch,Notes) values (?,?,?,?,?,?,?,?,?)";
            pstt = connn.prepareStatement(reg);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcode);
            pstt.setString(3,newitemsupplierr);
            pstt.setString(4,newitemname);
            pstt.setString(5,"N/A");
            pstt.setString(6,"Adding Shipment");
            pstt.setString(7,"N/A");
            pstt.setString(8,newitempatch);
            pstt.setString(9,"The user has added a new shipment");
            pstt.execute();
            
           }
            
                catch (Exception e)

        {
         
        }
        finally {

            try{
                rss.close();
                pstt.close();

            }
            catch(Exception e){

            }
        }
           
//           try {
//           
//           
//          String regpp = "insert into Consumption (Date, Supplier, Chemical_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
//          pstt = connn.prepareStatement(regpp);
//          pstt.setString(1,newitemdatee);
//          pstt.setString(2,newitemsupplierr);
//          pstt.setString(3,newitemname);
//          pstt.setString(4,"Adding Shipment");
//          pstt.setString(5,newitemquant);
//          pstt.setString(6,newitemunitpricee);
//          pstt.setString(7,dsa);
//          pstt.execute();
//          
//          
//          String sqluu= "update Stock set Stock='"+hye+"', Total_Price='"+hyr+"' where Code='"+newitemcode+"' and Supplier='"+newitemsupplierr+"' and Chemical_Name='"+newitemname+"' ";
//          pstt=connn.prepareStatement(sqluu);
//          pstt.execute();
//            
//          
//           }
//           
//               catch (Exception e)
//
//        {
//         
//        }
//        finally {
//
//            try{
//                rss.close();
//                pstt.close();
//
//            }
//            catch(Exception e){
//
//            }
//        }
//            
          ////////////////////////////////////////  
            
//      Notifications noti = Notifications.create();
//      noti.title("Successful!");
//      noti.text("We saved it successfully.");
//      noti.position(Pos.CENTER);
//      noti.showInformation();
//      
//      
    

Thread.sleep(2000);

  try {
           
           
          String regpp = "insert into Consumption (Date, Supplier, Chemical_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
          pstt = connn.prepareStatement(regpp);
          pstt.setString(1,newitemdatee);
          pstt.setString(2,newitemsupplierr);
          pstt.setString(3,newitemname);
          pstt.setString(4,"Adding Shipment");
          pstt.setString(5,newitemquant);
          pstt.setString(6,newitemunitpricee);
          pstt.setString(7,dsa);
          pstt.execute();
          
          
          String sqluu= "update Stock set Stock='"+hye+"', Total_Price='"+hyr+"' where Code='"+newitemcode+"' and Supplier='"+newitemsupplierr+"' and Chemical_Name='"+newitemname+"' ";
          pstt=connn.prepareStatement(sqluu);
          pstt.execute();
            
          
           }
           
               catch (Exception e)

        {
         
        }
        finally {

            try{
                rss.close();
                pstt.close();

            }
            catch(Exception e){

            }
        }
            
          ////////////////////////////////////////  
            
      Notifications noti = Notifications.create();
      noti.title("Successful!");
      noti.text("We updated all successfully.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      
      
        
      code.clear();
      material.clear();
      patch.clear();
      company.clear();
      quantity.clear();
      price.clear();
            
            
       io1.clear();
       io2.clear();
       io3.clear();
       io4.clear();
       io5.clear();
       

           
           
       
        
           ////////////////////////////////
           
             table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Frisca";
            pstt=connn.prepareStatement(sql);  
            rss=pstt.executeQuery();
            
        ///////////////////////////////////////////////////////////////
            
        for (int i=0;i<rss.getMetaData().getColumnCount();i++) {
            final int j=i;
            TableColumn col=new TableColumn(rss.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                     return new SimpleStringProperty(param.getValue().get(j).toString());
                     
                }
                
            });
            table.getColumns().addAll(col);
            
            
        }
        
        //While getting info
        
        while (rss.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rss.getMetaData().getColumnCount();i++) {
                row.add(rss.getString(i));
            }
            data.add(row);
            
        }
        
        table.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
          
        }
        finally {
            
            try{
                
                rss.close();
                pstt.close();

            }
            catch(Exception e){
                
            }
         } 
         
       
        
          TableFilter filter = new TableFilter(table);

      
        
        ///////////////////////////////////////////////////////////
        
        
        ////////////////////////////////////
        
    }

    
    
    }
    
    
    
    
    
    
    
  
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
      
        
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    date.setText(value1);
        
       connn = che_db.java_che_db(); 
       
       
         refresh.fire();
        
        
    }    
    
}
