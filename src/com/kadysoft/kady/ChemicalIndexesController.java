
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class ChemicalIndexesController implements Initializable {

    
    Connection connn = null;
  
    ResultSet rss = null;
  
    PreparedStatement pstt = null;
    
    @FXML
    private MenuItem loadindexes;

    @FXML
    private MenuItem addnewindex;

    @FXML
    private MenuItem modifyindex;

    @FXML
    private MenuItem deleteindex,updateitemprice;

    @FXML
    private TableView<String> table;

    @FXML
    private Pane addpane;

    @FXML
    private JFXTextField addcode;

    @FXML
    private JFXTextField addsupplier;

    @FXML
    private JFXTextField addmaterialname;

    @FXML
    private JFXTextField addunit;

    @FXML
    private JFXTextField addunitprice;

    @FXML
    private JFXButton addindex;

    @FXML
    private Pane modifydeletepane,updatepricepane;

    @FXML
    private JFXTextField rownumber,itemcode,itemname,itemprice;

    @FXML
    private JFXTextField columnname;

    @FXML
    private JFXTextField celldata;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton delete,updatepricebtn;
    
    
    @FXML
    private JFXToggleButton openhack;
    
    
   
    @FXML
    void exporttoexcelaction (ActionEvent event) throws IOException {
        
        
        /////////////////////////////////////////////////////////////////////////
        
        Workbook workbook = new HSSFWorkbook();
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
    void openhackaction(ActionEvent event) throws IOException {
        
       //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     if (openhack.isSelected()==true) {
         //55555555555555555555555555///////////////////////
         
         
     JFXPasswordField code=new JFXPasswordField ();
     code.setEffect(new DropShadow());
     code.setMinSize(150, 25);
     code.setStyle("-fx-font-size:15;-fx-font-weight:bold;");
     code.setPromptText("Write Password Here");
     code.setLabelFloat(true);
     
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Open Hack?");
      alert.setHeaderText("If you wanna open hack, write password then hit 'OK' button?");
      alert.setGraphic(code);
      Optional<ButtonType> option = alert.showAndWait();
      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
          //IF  OK.///////////////////////////////////////////////////////////
          String command="wmic bios get serialnumber";
              StringBuffer output=new StringBuffer();
              try {
                  Process SerNumProcess=Runtime.getRuntime().exec(command);
                   BufferedReader  sNumReader=new BufferedReader(new InputStreamReader(SerNumProcess.getInputStream()));
                   String linee="";
                   while ((linee=sNumReader.readLine())!=null) {
                       output.append(linee+"\n");
                   }
                   String MachineID=output.toString().substring(output.indexOf("\n"),output.length()).trim();
                   
                   if (code.getText().equals(MachineID)) {
                       Notifications noti = Notifications.create();
                       noti.title("Right Code!");
                       noti.text("Congratulation, Code Was Right!.");
                       noti.position(Pos.CENTER);
                       noti.showInformation();
                       openhack.setText("Close Hack");
                       modifyindex.setDisable(false);
                       deleteindex.setDisable(false);
                   }
                   else {
                       Notifications noti = Notifications.create();
                       noti.title("Wrong Code!");
                       noti.text("Code Was Wrong, Try Again!.");
                       noti.position(Pos.CENTER);
                       noti.showError();
                       openhack.setText("Open Hack");
                       modifyindex.setDisable(true);
                       deleteindex.setDisable(true);
                       openhack.setSelected(false);
                   }
                   
          }
              catch (Exception e) {
        //  JOptionPane.showMessageDialog(null, e);
        } finally {
          try {   
          } catch (Exception exception) {}
        } 
              
              
          
          ////////////////////////////////////////////////////////////////////
      }
      else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, Hack Won't Be Opened.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      openhack.setText("Open Hack");
      modifyindex.setDisable(true);
      deleteindex.setDisable(true);
      openhack.setSelected(false);
      } else {
      openhack.setText("Open Hack");
      modifyindex.setDisable(true);
      deleteindex.setDisable(true);
      openhack.setSelected(false);
      }
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
         
         //55555555555555555555555555///////////////////////
     }
     
     else {
         openhack.setText("Open Hack");
         modifyindex.setDisable(true);
         deleteindex.setDisable(true);
     }
     
        
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void addindexaction(ActionEvent event) {
        

      /////////////////////////////////////////////////////////////////////////////
     
      String codee,supplierr,materialnamee,unitt,unitpricee;
      codee=addcode.getText();
      supplierr=addsupplier.getText();
      materialnamee=addmaterialname.getText();
      unitt=addunit.getText();
      unitpricee=addunitprice.getText();
      
      if (codee.isEmpty()||supplierr.isEmpty()||materialnamee.isEmpty()||unitt.isEmpty()||unitpricee.isEmpty()) {
      Notifications noti = Notifications.create();
      noti.title("Saving Error!");
      noti.text("We are sorry, we can't add an empty value, some of fields is empty.");
      noti.position(Pos.CENTER);
      noti.showError();
      }
      
      else {
          /////////////////KADY///////////////
          
           try {

            String sql ="insert into Indexes (Code,Supplier,Chemical_Name,Type,Unit_Price) values (?,?,?,?,?) ";

            pstt=connn.prepareStatement(sql);
            pstt.setString(1,codee);
            pstt.setString(2,supplierr);
            pstt.setString(3,materialnamee);
            pstt.setString(4,unitt);
            pstt.setString(5,unitpricee);
            

            pstt.execute();
           
      Notifications noti = Notifications.create();
      noti.title("Successful Saving!");
      noti.text("Data Saved Successfully.");
      noti.position(Pos.CENTER);
      noti.showInformation();
            

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
        
     addcode.clear();
     addsupplier.clear();
     addmaterialname.clear();
     addunit.clear();
     addunitprice.clear();
     loadindexes.fire();
        
        /////////////////////////////////////////////////////////////////////////////
        
          
          ////////////////////////////////////
      }
      
      
      ////////////////DB//////////////////
      

       
    }

    @FXML
    void addnewindexaction(ActionEvent event) {

        addpane.setVisible(true);
        modifydeletepane.setVisible(false);
        updatepricepane.setVisible(false);
        
    }

    @FXML
    void deleteaction(ActionEvent event) {

        
           ////////////////////////////////////////////////    
               
               
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Index");
      alert.setHeaderText("Are you sure want to Delete this Record ?");
      

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
             try {
               
      String namec=columnname.getText();
      String sql = "delete from Indexes where "+namec+"=? ";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, this.celldata.getText());
      this.pstt.execute();
      Notifications noti = Notifications.create();
      noti.title("Delete!");
      noti.text("Record Successfully Deleted");
      noti.position(Pos.CENTER);
      noti.showInformation();
    } catch (Exception e) {
    //  JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        this.rss.close();
        this.pstt.close();
      } catch (Exception exception) {}
    } 
             
             
       loadindexes.fire();
    
      } else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, Record wasn't deleted.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      } else {
         
      }
               
               
               
         /////////////////////////////////////////////////   
          
         
        
        
    }

    @FXML
    void deleteindexaction(ActionEvent event) {

        addpane.setVisible(false);
        modifydeletepane.setVisible(true);
        updatepricepane.setVisible(false);
        update.setVisible(false);
        delete.setVisible(true);
    }

    @FXML
    void loadindexesaction(ActionEvent event) {

        ///////////////////////////////////////////////////////////////
        
              
        table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Indexes";
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

      
        
        ///////////////////////////////////////////////////////////////
    }

    @FXML
    void modifyindexaction(ActionEvent event) {

        addpane.setVisible(false);
        modifydeletepane.setVisible(true);
        updatepricepane.setVisible(false);
        delete.setVisible(false);
        update.setVisible(true);
    }

    @FXML
    void tableaction(MouseEvent event) {

      TablePosition pos= (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
      int idd=(int) table.getSelectionModel().getSelectedIndices().get(0);
      int iddd=idd+1;
      String idddd=Integer.toString(iddd);
      String h= pos.getTableColumn().getCellData(pos.getRow()).toString();
      String colname=pos.getTableColumn().getText();
      celldata.setText(h);
      columnname.setText(colname);
      rownumber.setText(idddd);
    }

    @FXML
    void updateaction(ActionEvent event) {
        
       
          String value1 = rownumber.getText();
          String value2 = columnname.getText();
          String value3 = celldata.getText();
          
         
          
       
            try {
            
                String sql= "update Indexes set ID='"+value1+"','"+value2+"'='"+value3+"' where ID='"+value1+"'";

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
            
            
          
            rownumber.setText("");
            columnname.setText("");
            celldata.setText("");
            loadindexes.fire();
            
       
     ///////////////////////////////////AIAR////////////////////////////////////////////////////  
        
        
        
        
    }
    
    
    
    
    
    
    
    @FXML
    void itemcoderel(KeyEvent event) {
        
           try {
      String sql = "select * from Indexes where Code=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, this.itemcode.getText());
      this.rss = this.pstt.executeQuery();
      String add2 = this.rss.getString("Chemical_Name");
      this.itemname.setText(add2);
      String add3 = this.rss.getString("Unit_Price");
      this.itemprice.setText(add3);
      
      
    
    
      
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
        this.rss.close();
        this.pstt.close();
      
      } catch (Exception exception) {}
    } 
        
        
        
    if(this.itemcode.getText().equals("")||itemcode.getText().equals(" ")) {
      
      this.itemcode.clear();
      this.itemname.clear();
      this.itemprice.clear();
    
    } else {
      
    } 
    
        
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    void updatepricebtnaction(ActionEvent event) {
        
        ///////////////////////Radio Buttons////////////////////
        ToggleGroup tg=new ToggleGroup();
        JFXRadioButton only,both;
        only=new JFXRadioButton("Update Price In Index Only.");
        only.setSelected(false);
        only.setToggleGroup(tg);
        both=new JFXRadioButton("Update Both Of Index And Stock.");
        both.setSelected(false);
        both.setToggleGroup(tg);
        VBox vbox=new VBox();
        vbox.setSpacing(20);
        vbox.getChildren().addAll(only,both);
        ////////////////////////////////////////////////////////
        
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Update Price");
      alert.setHeaderText("Here, What Will you want to do with price?");
      alert.setGraphic(vbox);

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
          //See What Was Selected Then Make A Decision./////////////////////////////////////////////////////
          
          if (only.isSelected()==true) {
             //////////////////////////////////////// 
              
              //////////////////////////////Update It In Index Too//////////////////////////////////////
       
          String value1 = itemcode.getText();
          String value2 = itemprice.getText();
          
          /////////////////////////////////////////////////////////////////////////////////////////
          
            try {
            
                String sql= "update Indexes set Code='"+value1+"', Unit_Price="+value2+" where Code="+value1+"";

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
        
        /////////////////////////////////////////////////////////////////////
               
        
      loadindexes.fire();
      Notifications noti = Notifications.create();
      noti.title("Great Job!");
      noti.text("Index Price has been updated.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      
              
              ///////////////////////////////////////
          }
          
          else if (both.isSelected()==true) {
              ///////////////////////////////////////////////////
              
               //////////////////////////////Update It In Index Too//////////////////////////////////////
       
          String value1 = itemcode.getText();
          String value2 = itemprice.getText();
          
          /////////////////////////////////////////////////////////////////////////////////////////
          
            try {
            
                String sql= "update Indexes set Code='"+value1+"', Unit_Price="+value2+" where Code="+value1+"";

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
        
        /////////////////////////////////////////////////////////////////////
               
      try {
          
      String sqlo = "select * from Stock where Code=?";
      
      this.pstt = this.connn.prepareStatement(sqlo);
      this.pstt.setString(1, this.itemcode.getText());
      this.rss = this.pstt.executeQuery();
      
      String add2 = this.rss.getString("Stock");
      
      double vb1=Double.parseDouble(add2);
      double vb2=Double.parseDouble(itemprice.getText());
      double vb3=vb1*vb2;
      String newvb=Double.toString(vb3);
      
      String sqly= "update Stock set Code='"+value1+"', Total_Price="+newvb+" where Code='"+value1+"'";
      pstt=connn.prepareStatement(sqly);
      pstt.execute();
      
      
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
        this.rss.close();
        this.pstt.close();
      
      } catch (Exception exception) {}
    } 
        
      loadindexes.fire();
      Notifications noti = Notifications.create();
      noti.title("Great Job!");
      noti.text("Everything has been updated.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      
              
              //////////////////////////////////////////////////
          }
          
          else {
              
              //Notification for choose one.
              
      Notifications noti = Notifications.create();
      noti.title("Error!");
      noti.text("Please Choose One Option First.");
      noti.position(Pos.CENTER);
      noti.showError();
              
             /////////////////////////////// 
          }
          
          //////////////////////////////////////////////8888888888888///////////////////////////////////////
      }
      
      else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, Record wasn't updated.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      } else {
         
      }
      
      
        
       
      
    }
    
    
    
    
    
    
    
    @FXML
    void updateitempriceaction(ActionEvent event) {
        
        addpane.setVisible(false);
        modifydeletepane.setVisible(false);
        updatepricepane.setVisible(true);
        update.setVisible(false);
        delete.setVisible(false);
        
    }
    
    
    
    
    
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        connn = che_db.java_che_db();
        
        
        
    }    
    
}
