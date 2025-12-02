
package com.kadysoft.kady;

//import com.itextpdf.layout.element.Text;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
//import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
public class ChemicalMainWindowController implements Initializable {

    Connection connn = null;
  
    ResultSet rss = null;
  
    PreparedStatement pstt = null;
    
    
    @FXML
    private MenuItem index;

    @FXML
    private MenuItem leave;
    
    @FXML
    private MenuItem addnewitemmenuitem;

    @FXML
    private MenuItem inputitem;

    @FXML
    private MenuItem outputitem;

    @FXML
    private MenuItem updateiteminfo;

    @FXML
    private MenuItem deleteiteminfo;

    @FXML
    private MenuItem getallaudititem;

    @FXML
    private TableView<Integer> table;

    @FXML
    private Pane newitempane;

    @FXML
    private JFXTextField newitemdate,oldcon;

    @FXML
    private JFXTextField newitemcode;

    @FXML
    private JFXTextField newitemsupplier;

    @FXML
    private JFXTextField newitemmaterialname,qp;

    @FXML
    private JFXTextField newitemunit;

    @FXML
    private JFXTextField newitemunitprice;

    @FXML
    private JFXTextField newitemstock,tp;

    @FXML
    private JFXTextField newitemtotalprice;

    @FXML
    private JFXButton addnewitem;

    @FXML
    private JFXCheckBox addtoindexcheckbox;

    @FXML
    private Pane iteminputpane;

    @FXML
    private JFXTextField inputdate;

    @FXML
    private JFXTextField inputcode;

    @FXML
    private JFXTextField inputmaterialname;

    @FXML
    private JFXTextField inputstock;

    @FXML
    private JFXTextField inputprice;

    @FXML
    private JFXTextField inputtotalprice;

    @FXML
    private JFXTextField inputquantity,inputsupplier,inputunit;

    @FXML
    private JFXButton inputbtn;

    @FXML
    private JFXButton outputbtn;

    @FXML
    private Pane updateanddeletepane;

    @FXML
    private JFXTextField rownumber;

    @FXML
    private JFXTextField columnname;

    @FXML
    private JFXTextField celldata,newstock,oldprice;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton deletebtn;
    
    @FXML
    private MenuItem auditmenuitem,reportaspdf;
    
    @FXML
    private ComboBox<String> statusbox;

    @FXML
    private ComboBox<String> sectionbox;
    
    @FXML
    private Hyperlink addconsumption;
    
    
    @FXML
    private JFXToggleButton openhack;
    
    @FXML
    private MenuItem logout;
    
   
    
    
    
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
                       updateiteminfo.setDisable(false);
                       deleteiteminfo.setDisable(false);
                   }
                   else {
                       Notifications noti = Notifications.create();
                       noti.title("Wrong Code!");
                       noti.text("Code Was Wrong, Try Again!.");
                       noti.position(Pos.CENTER);
                       noti.showError();
                       openhack.setText("Open Hack");
                       updateiteminfo.setDisable(true);
                       deleteiteminfo.setDisable(true);
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
      updateiteminfo.setDisable(true);
      deleteiteminfo.setDisable(true);
      openhack.setSelected(false);
      } else {
      openhack.setText("Open Hack");
      updateiteminfo.setDisable(true);
      deleteiteminfo.setDisable(true);
      openhack.setSelected(false);
      }
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
         
         //55555555555555555555555555///////////////////////
     }
     
     else {
         openhack.setText("Open Hack");
         updateiteminfo.setDisable(true);
         deleteiteminfo.setDisable(true);
     }
     
        
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   
        
        
    }
    
    
    
    
    
    
    @FXML
    void logoutaction(ActionEvent event) throws IOException {
        
     
    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("Welcome.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Welcome To T&C Laundry Stock Manager, By Kadysoft Ltd.");
    stg.centerOnScreen();
    stg.setResizable(false);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.show();
    Stage jk = (Stage)this.table.getScene().getWindow();
    jk.close();
        
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    void oldconaction(MouseEvent event) throws IOException {
        
     
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void addconsumptionaction(ActionEvent event) throws IOException {
        //////////////////////////////////////////////GHDSH///////////////////////////////////////
        
        try {
            
      String newitemmaterialnamee;
      newitemmaterialnamee=inputmaterialname.getText();
      String sql = "select * from Stock where Chemical_Name=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, newitemmaterialnamee);
      this.rss = this.pstt.executeQuery();
      String add4 = this.rss.getString("Total_Price");
      oldprice.setText(add4);
      this.pstt.execute();
            
           
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
     /////////////////////////////////////////////////////////////////////////////////////////////
       /*   try {
            
      String newitemmaterialnamee;
      newitemmaterialnamee=inputmaterialname.getText();
      String sqlY = "select * from Consumption where Chemical_Name=?";
      this.pstt = this.connn.prepareStatement(sqlY);
      this.pstt.setString(1, newitemmaterialnamee);
      this.rss = this.pstt.executeQuery();
      String add44 = this.rss.getString("Total_Consumption");
      oldcon.setText(add44);
      this.pstt.execute();
            
           
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
     */
     /////////////////////////////////////////////////////////////////////////////////////////////
     double bv1=Double.parseDouble(inputtotalprice.getText());
     double bv2=Double.parseDouble(oldprice.getText());
     double bv3=bv2-bv1;
     //double bv4=Double.parseDouble(oldcon.getText());
     //double bv5=bv3+bv4;
     int newval=(int) bv3;
     String newvall=Integer.toString(newval);   
     ///////////////////////////////////hi////////////////////////////////////////////////////////
     
     String mnb=statusbox.getSelectionModel().getSelectedItem().toString();
     
     if (mnb.equals("Output")) {
           try {
            
    String newitemmaterialnamee,inputsupplierr;
    newitemmaterialnamee=inputmaterialname.getText();
    inputsupplierr=inputsupplier.getText();
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value1 = timeString;
    
   //   if (bv4==0) {
   
   
          String reg = "insert into Consumption (Date, Supplier, Chemical_Name, Status, Total_Consumption) values (?,?,?,?,?)";
          this.pstt = this.connn.prepareStatement(reg);
          pstt.setString(1,value1);
          pstt.setString(2,inputsupplierr);
          pstt.setString(3,newitemmaterialnamee);
          pstt.setString(4,"Output");
          pstt.setString(5,newvall);
          this.pstt.execute();
          
          
    //  }
//      else {
//          String sql= "update Consumption set Material_Name='"+newitemmaterialnamee+"', Total_Consumption='"+newvall+"' where Material_Name='"+newitemmaterialnamee+"'";
//
//                pst=conn.prepareStatement(sql);
//                pst.execute();
//            
//      }
      
            
            
            
            
            
           
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
     
    
     }
     
     ///////////
      if (mnb.equals("Input")) {
           try {
            
               double bv11=Double.parseDouble(inputtotalprice.getText());
     double bv22=Double.parseDouble(oldprice.getText());
     double bv33=bv11-bv22;
    
     int newvalL=(int) bv33;
     String newvallL=Integer.toString(newvalL); 
               
    String newitemmaterialnamee,inputsupplierr;
    newitemmaterialnamee=inputmaterialname.getText();
    inputsupplierr=inputsupplier.getText();
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value1 = timeString;
    
   //   if (bv4==0) {
   
   
          String reg = "insert into Consumption (Date, Supplier, Chemical_Name, Status, Total_Consumption) values (?,?,?,?,?)";
          this.pstt = this.connn.prepareStatement(reg);
          pstt.setString(1,value1);
          pstt.setString(2,inputsupplierr);
          pstt.setString(3,newitemmaterialnamee);
          pstt.setString(4,"Input");
          pstt.setString(5,newvallL);
          this.pstt.execute();
          
          
    //  }
//      else {
//          String sql= "update Consumption set Material_Name='"+newitemmaterialnamee+"', Total_Consumption='"+newvall+"' where Material_Name='"+newitemmaterialnamee+"'";
//
//                pst=conn.prepareStatement(sql);
//                pst.execute();
//            
//      }
      
            
            
            
            
            
           
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
     
    
     }
    
     
     else {
         
     }
     
     
     //////////////////////////////////////hi/////////////////////////////////////////////////////
     
        
        
        outputbtn.setDisable(false);
        
       
       
        
    }
  
    
    
    
    
    
    
    
    
    
     @FXML
    void sumaction(ActionEvent event) throws IOException {

    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("ChemicalConsumption.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Chemical Consumption");
    stg.centerOnScreen();
    stg.setResizable(false);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.show();
        
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    void reportaspdfaction(ActionEvent event) throws IOException {

        ////////////////Create PDF///////////////
        
        
         ////////////////////////////Start Report//////////////////////////////
        
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value0 = timeString;
    String value00 = value0.replace("/", "_");
    String repname = "All_Laundry_Chemical_Stock_Of_" + value00;
    String reppath = System.getProperty("user.home") + "\\Desktop";
    FileChooser dialog = new FileChooser();
    dialog.setInitialDirectory(new File(reppath));
    dialog.setInitialFileName(repname);
    dialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", new String[] { "*.pdf" }));
    File dialogResult = dialog.showSaveDialog(null);
    String filePath = dialogResult.getAbsolutePath().toString();
    try {
        
         String sql ="select * from Stock";
            
 
            pstt=connn.prepareStatement(sql);
            rss=pstt.executeQuery();
        
      DateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
      Date dd = new Date();
      String todate = dff.format(dd);
      Calendar cal = Calendar.getInstance();
      cal.add(5, -2);
      Date d1 = cal.getTime();
      String fromdate = dff.format(d1);
      com.itextpdf.text.Document myDocument = new com.itextpdf.text.Document();
      PdfWriter myWriter = PdfWriter.getInstance(myDocument, new FileOutputStream(filePath));
      PdfPTable table = new PdfPTable(7);
      table.size();
      //table.setHorizontalAlignment(1);
      myDocument.open();
      float[] columnWidths = { 8.0F, 8.0F, 8.0F, 8.0F, 8.0F, 8.0F, 8.0F };
      table.setWidths(columnWidths);
      table.setWidthPercentage(100.0F);
      
      ColumnText.showTextAligned(myWriter.getDirectContentUnder(),
              
                Element.ALIGN_CENTER, new Phrase("T&C Garments By Kadysoft Ltd.", FontFactory.getFont("Times-Bold", 11.0F, 1)),
                297.5f, 421, myWriter.getPageNumber() % 2 == 1 ? 45 : -45);
      
      //myDocument.add((com.itextpdf.text.Element)new Paragraph("--------------------"));
      myDocument.add((com.itextpdf.text.Element)new Paragraph("Laundry Chemical Stock Report", FontFactory.getFont("Times-Bold", 14.0F, 1)));
      myDocument.add((com.itextpdf.text.Element)new Paragraph(" "));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Date", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Code", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Supplier", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Chemical Name", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Type", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Stock", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Total Price", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      double total,newtotal=0.0;
      while (rss.next()) {
        table.addCell(new PdfPCell(new Paragraph(rss.getString(2),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(3),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(4),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(5),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(6),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(7),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rss.getString(8),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           total=Double.parseDouble(rss.getString(8));
           newtotal=total+newtotal;
      } 
      myDocument.add((com.itextpdf.text.Element)table);
    //  myDocument.add((com.itextpdf.text.Element)new Paragraph("-------------------------------"));
      myDocument.add((com.itextpdf.text.Element)new Paragraph("-------------------------------"));
      myDocument.add((com.itextpdf.text.Element)new Paragraph("All Total Prices Are:      "+newtotal,FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.BOLD)));
      myDocument.setPageSize(PageSize.A4.rotate());
      myDocument.close();
      Alert alooo = new Alert(Alert.AlertType.CONFIRMATION);
      alooo.setTitle("Info");
      alooo.setHeaderText("Info!");
      alooo.setContentText("Report was generated successfully");
      alooo.setResizable(true);
      alooo.showAndWait();
    } catch (Exception e) {
    //  JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
       
      } catch (Exception e) {
     //   JOptionPane.showMessageDialog(null, e);
      } 
    } 
    Desktop de = Desktop.getDesktop();
    de.open(new File(reppath + "\\" + repname + ".pdf"));
    ////
        
        ////////////////////////////End Report////////////////////////////////

        
        
        /////////////////////////////////////////
        
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    void auditmenuitemaction(ActionEvent event) throws IOException {

    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("ChemicalAudit_Window.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Audit Manager");
    stg.centerOnScreen();
    stg.setResizable(false);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.show();
    
    }

    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void addnewitemmenuitemaction(ActionEvent event) {
        updateanddeletepane.setVisible(false);
        iteminputpane.setVisible(false);
        newitempane.setVisible(true);
        newitemcode.requestFocus();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void addcodekeyaction(KeyEvent event) {

        ///////////////////////////////////////////////////////////////////////////////////
     /*   
        try {
      String sql = "select * from Indexes where Code=? ";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.newitemcode.getText());
      this.rs = this.pst.executeQuery();
      
      
      String add1 = this.rs.getString("Supplier");
      this.newitemsupplier.setText(add1);
      String add2 = this.rs.getString("Material_Name");
      this.newitemmaterialname.setText(add2);
      String add3 = this.rs.getString("Unit");
      this.newitemunit.setText(add3);
      String add4 = this.rs.getString("Unit_Price");
      this.newitemunitprice.setText(add4);
      
      
    } catch (Exception exception) {
      try {
        this.rs.close();
        this.pst.close();
      } catch (Exception exception1) {}
    } finally {
      try {
        this.rs.close();
        this.pst.close();
      } catch (Exception exception) {}
    } 
        
        
    
    if (this.newitemcode.getText() == "") {
      this.newitemsupplier.clear();
      this.newitemmaterialname.clear();
      this.newitemunit.clear();
      this.newitemunitprice.clear();
    } 
 
    if (this.newitemcode.getText().isEmpty()) {
      this.newitemsupplier.clear();
      this.newitemmaterialname.clear();
      this.newitemunit.clear();
      this.newitemunitprice.clear();
    } else {
      
    } 
        
        ///////////////////////////////////////////////////////////////////////////////////
        
       */ 
        
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    void totalpricekeyaction(KeyEvent event) {

        String newitemunitpricee,newitemstockk;
      
      newitemunitpricee=newitemunitprice.getText();
      newitemstockk=newitemstock.getText();
      
      
      double d1=Double.parseDouble(newitemunitpricee);
      double d2=Double.parseDouble(newitemstockk);
      double d3=d1*d2;
      
      newitemtotalprice.setText(Double.toString(d3));
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void addnewitemaction(ActionEvent event) {
        
        
        if (addtoindexcheckbox.isSelected()==true) {
            //Add To Index.
            
            
      /////////////////////////////////////////////////////////////////////////////
     
      String newitemdatee,newitemcodee,newitemsupplierr,newitemmaterialnamee,newitemunitt,newitemunitpricee,newitemstockk,newitemtotalpricee;
      newitemdatee=newitemdate.getText();
      newitemcodee=newitemcode.getText();
      newitemsupplierr=newitemsupplier.getText();
      newitemmaterialnamee=newitemmaterialname.getText();
      newitemunitt=newitemunit.getText();
      newitemunitpricee=newitemunitprice.getText();
      newitemstockk=newitemstock.getText();
      newitemtotalpricee=newitemtotalprice.getText();
      
      
      
      if (newitemcodee.isEmpty()||newitemsupplierr.isEmpty()||newitemmaterialnamee.isEmpty()||newitemstockk.isEmpty()||newitemunitpricee.isEmpty()) {
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
            pstt.setString(1,newitemcodee);
            pstt.setString(2,newitemsupplierr);
            pstt.setString(3,newitemmaterialnamee);
            pstt.setString(4,newitemunitt);
            pstt.setString(5,newitemunitpricee);
            

            pstt.execute();
            
            
            /////////////////////////////////
            
            String sqll ="insert into Stock (Date,Code,Supplier,Chemical_Name,Type,Stock,Total_Price) values (?,?,?,?,?,?,?) ";

            pstt=connn.prepareStatement(sqll);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcodee);
            pstt.setString(3,newitemsupplierr);
            pstt.setString(4,newitemmaterialnamee);
            pstt.setString(5,newitemunitt);
            pstt.setString(6,newitemstockk);
            pstt.setString(7,newitemtotalpricee);
            

            pstt.execute();
            
            /////////////////////////////////
           
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
  
      }
      
     
        }
        
        
        else {
            //Don't add to index.
            
                  
      /////////////////////////////////////////////////////////////////////////////
     
      String newitemdatee,newitemcodee,newitemsupplierr,newitemmaterialnamee,newitemunitt,newitemunitpricee,newitemstockk,newitemtotalpricee;
      newitemdatee=newitemdate.getText();
      newitemcodee=newitemcode.getText();
      newitemsupplierr=newitemsupplier.getText();
      newitemmaterialnamee=newitemmaterialname.getText();
      newitemunitt=newitemunit.getText();
      newitemunitpricee=newitemunitprice.getText();
      newitemstockk=newitemstock.getText();
      newitemtotalpricee=newitemtotalprice.getText();
      
      
      
      if (newitemcodee.isEmpty()||newitemsupplierr.isEmpty()||newitemmaterialnamee.isEmpty()||newitemstockk.isEmpty()||newitemunitpricee.isEmpty()) {
      Notifications noti = Notifications.create();
      noti.title("Saving Error!");
      noti.text("We are sorry, we can't add an empty value, some of fields is empty.");
      noti.position(Pos.CENTER);
      noti.showError();
      }
      
      else {
          /////////////////KADY///////////////
          
           try {

            
               
            /////////////////////////////////
            
            String sqll ="insert into Stock (Date,Code,Supplier,Chemical_Name,Type,Stock,Total_Price) values (?,?,?,?,?,?,?) ";

            pstt=connn.prepareStatement(sqll);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcodee);
            pstt.setString(3,newitemsupplierr);
            pstt.setString(4,newitemmaterialnamee);
            pstt.setString(5,newitemunitt);
            pstt.setString(6,newitemstockk);
            pstt.setString(7,newitemtotalpricee);
            

            pstt.execute();
            
            /////////////////////////////////
           
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
   
      }
      
     
        }
        
        
        /////Add Audit for adding new item only.
        
        
        try {
            
      String newitemdatee,newitemcodee,newitemsupplierr,newitemmaterialnamee,newitemunitt,newitemunitpricee,newitemstockk,newitemtotalpricee;
      newitemdatee=newitemdate.getText();
      newitemcodee=newitemcode.getText();
      newitemsupplierr=newitemsupplier.getText();
      newitemmaterialnamee=newitemmaterialname.getText();
      newitemunitt=newitemunit.getText();
      //newitemunitpricee=newitemunitprice.getText();
      newitemstockk=newitemstock.getText();
      newitemtotalpricee=newitemtotalprice.getText();
            
            String reg = "insert into Audit (Date,Code,Supplier,Chemical_Name,Type,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pstt = this.connn.prepareStatement(reg);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcodee);
            pstt.setString(3,newitemsupplierr);
            pstt.setString(4,newitemmaterialnamee);
            pstt.setString(5,newitemunitt);
            pstt.setString(6,"Adding");
            pstt.setString(7,"LAUNDRY CHEMICAL STORE");
            pstt.setString(8,"Added a new item to the Laundry Chemical Store and maybe index too, it's Stock is "+newitemstockk+"");
            //pstt.setString(9,newitemtotalpricee);
            this.pstt.execute();
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
        
        
        
        
      newitemcode.clear();
      newitemsupplier.clear();
      newitemmaterialname.clear();
      newitemunit.clear();
      newitemunitprice.clear();
      newitemstock.clear();
      newitemtotalprice.clear();
      getallaudititem.fire();
      addtoindexcheckbox.setSelected(false);
        
    }

    
    
    
    
    
    
    
    
    
    
    @FXML
    void deletebtnaction(ActionEvent event) {

                    
               
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Index");
      alert.setHeaderText("Are you sure want to Delete this Record ?");
      

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
             try {
               
      String namec=columnname.getText();
      String sql = "delete from Stock where "+namec+"=? ";
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
             
             
       getallaudititem.fire();
    
      } else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, Record wasn't deleted.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      } else {
         
      }
               
               
               
         ///////////////////////////////////////////////// 
         
            try {
                
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value1 = timeString;
            
      String newitemdatee,newitemcodee,newitemsupplierr,newitemmaterialnamee,newitemunitt,newitemunitpricee,newitemstockk,newitemtotalpricee;
      newitemdatee=rownumber.getText();
      newitemcodee=columnname.getText();
      newitemsupplierr=celldata.getText();
//      newitemmaterialnamee=newitemmaterialname.getText();
//      newitemunitt=newitemunit.getText();
//      //newitemunitpricee=newitemunitprice.getText();
//      newitemstockk=newitemstock.getText();
//      newitemtotalpricee=newitemtotalprice.getText();
            
            String reg = "insert into Audit (Date,Code,Supplier,Chemical_Name,Type,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pstt = this.connn.prepareStatement(reg);
            pstt.setString(1,value1);
            pstt.setString(2," ");
            pstt.setString(3," ");
            pstt.setString(4," ");
            pstt.setString(5," ");
            pstt.setString(6," ");
            pstt.setString(7," ");
            pstt.setString(8,"Deleted an item from the Laundry Chemical Store, its ID is: "+newitemdatee+" and its value on "+newitemcodee+" was "+newitemsupplierr+"");
            this.pstt.execute();
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
        
         
         
   
        
    }
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void deleteiteminfoaction(ActionEvent event) {

        updateanddeletepane.setVisible(true);
        iteminputpane.setVisible(false);
        newitempane.setVisible(false);
        deletebtn.setVisible(true);
        updatebtn.setVisible(false);
    }
    
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void getallaudititemaction(ActionEvent event) {

         ///////////////////////////////////////////////////////////////
        
              
        table.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Stock";
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
    void inputbtnaction(ActionEvent event) {

    }

    
    
    
    
    
    
    @FXML
    void quantityrel(KeyEvent event) {
        
      String iop=statusbox.getSelectionModel().getSelectedItem().toString();
      
      if (iop=="Input") {
      double f1=Double.parseDouble(inputstock.getText());
      double f2=Double.parseDouble(inputquantity.getText());
      double f3=f1+f2;
      newstock.setText(Double.toString(f3));
      double f4=Double.parseDouble(inputprice.getText());
      /////////////////////////////////////////////////////////////////////
      double qpp=f2*f4;
      qp.setText(Double.toString(qpp));
      double mx1,mx2,mx3;
      mx1=Double.parseDouble(qp.getText());
      mx2=Double.parseDouble(tp.getText());
      mx3=mx1+mx2;
      String total=Double.toString(mx3);
      inputtotalprice.setText(total);
     ///////////////////////////////////////////////////////////////////////
      
      }
      else if (iop=="Output") {
      double f1=Double.parseDouble(inputstock.getText());
      double f2=Double.parseDouble(inputquantity.getText());
      double f3=f1-f2;
      newstock.setText(Double.toString(f3));
      double f4=Double.parseDouble(inputprice.getText());
      //////////////////////////////////////////////////////////////////////
      double qpp=f2*f4;
      qp.setText(Double.toString(qpp));
      double mx1,mx2,mx3;
      mx1=Double.parseDouble(qp.getText());
      mx2=Double.parseDouble(tp.getText());
      mx3=mx2-mx1;
      String total=Double.toString(mx3);
      inputtotalprice.setText(total);
      //////////////////////////////////////////////////////////////////////
      
      }
      
      
      
      else {
      Notifications noti = Notifications.create();
      noti.title("Status Error");
      noti.text("Choose Status First.");
      noti.position(Pos.CENTER);
      noti.showError();
      }
      
     
      
        
      
    }
    
    
    
    
    
    
    @FXML
    void inputcodekeyaction(KeyEvent event) {

        
        
        ///////////////////////////////////////////////////////////////////////////////////
        
        try {
      String sql = "select * from Stock where Code=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, this.inputcode.getText());
      this.rss = this.pstt.executeQuery();
      String add2 = this.rss.getString("Chemical_Name");
      this.inputmaterialname.setText(add2);
      String add3 = this.rss.getString("Stock");
      this.inputstock.setText(add3);
      ////////////////////What We Added///////////////
    
      String add4 = this.rss.getString("Total_Price");
      this.tp.setText(add4);
    
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
        
        
        ////////////////////////////////////////////////////////////////////
       
        
        /////////////////////////////////////////////////////////////////////
        
        
    if(this.inputcode.getText().equals("")||inputcode.getText().equals(" ")) {
      
      this.inputmaterialname.clear();
      this.inputstock.clear();
      this.inputprice.clear();
      this.inputsupplier.clear();
      this.inputunit.clear();
      
      tp.clear();
      
    } else {
      
    } 
    
    
    
        
        ///////////////////////////////////////////////////////////////////////////////////
           try {
     
      
      String sql = "select * from Indexes where Code=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, this.inputcode.getText());
      this.rss = this.pstt.executeQuery();
      String add4 = this.rss.getString("Unit_Price");
      this.inputprice.setText(add4);
      
      String add44 = this.rss.getString("Supplier");
      this.inputsupplier.setText(add44);
      
      String add444 = this.rss.getString("Type");
      this.inputunit.setText(add444);
    
      
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @FXML
    void inputitemaction(ActionEvent event) {

        updateanddeletepane.setVisible(false);
        iteminputpane.setVisible(true);
        newitempane.setVisible(false);
        inputbtn.setVisible(true);
        outputbtn.setVisible(false);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void outputbtnaction(ActionEvent event) {

        //Update via code in Stock and add audit, price, total price, stock
         
          
          String newstok = newstock.getText();
          String totalpric = inputtotalprice.getText();
          String cod = inputcode.getText();
            
       
            try {
            
                String sql= "update Stock set Code="+cod+", Stock="+newstok+", Total_Price="+totalpric+" where Code="+cod+"";
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
            
     
        getallaudititem.fire();
          
      
         
      Notifications noti = Notifications.create();
      noti.title("Update Stock!");
      noti.text("Stock Updated.");
      noti.position(Pos.CENTER);
      noti.showInformation();
   
        
      try {
            
      String newitemdatee,newitemcodee,newitemmaterialnamee,statuss,sectionn,notey,quantityy,supplierr,unitt;
      newitemdatee=inputdate.getText();
      newitemcodee=inputcode.getText();
      newitemmaterialnamee=inputmaterialname.getText();
      supplierr=inputsupplier.getText();
      unitt=inputunit.getText();
      statuss=statusbox.getSelectionModel().getSelectedItem().toString();
      sectionn=sectionbox.getSelectionModel().getSelectedItem().toString();
      quantityy=inputquantity.getText();
      notey="User Created "+quantityy+" Of The Stock.";
      
      ///////////////////////////////////////////////////////////////////////////////
      
      ///////////////////////////////////////////////////////////////////////////////
      
            String reg = "insert into Audit (Date,Code,Supplier,Chemical_Name,Type,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pstt = this.connn.prepareStatement(reg);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcodee);
            pstt.setString(3,supplierr);
            pstt.setString(4,newitemmaterialnamee);
            pstt.setString(5,unitt);
            pstt.setString(6,statuss);
            pstt.setString(7,sectionn);
            pstt.setString(8,notey);
            this.pstt.execute();
           
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
     
     
            newstock.clear();
            inputtotalprice.clear();
            inputquantity.clear();
            inputprice.clear();
            inputcode.clear();
            inputsupplier.clear();
            inputunit.clear();
            inputmaterialname.clear();
            inputstock.clear();
            oldprice.clear();
            tp.clear();
            qp.clear();
            outputbtn.setDisable(true);
            //oldcon.setText("0");
            statusbox.getSelectionModel().clearSelection();
            sectionbox.getSelectionModel().clearSelection();
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void outputitemaction(ActionEvent event) {

        updateanddeletepane.setVisible(false);
        iteminputpane.setVisible(true);
        newitempane.setVisible(false);
        inputbtn.setVisible(false);
        outputbtn.setVisible(true);
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
    void updatebtnaction(ActionEvent event) {

          
          String value1 = rownumber.getText();
          String value2 = columnname.getText();
          String value3 = celldata.getText();
            
       
            try {
            
                String sql= "update Stock set ID='"+value1+"','"+value2+"'='"+value3+"' where ID='"+value1+"'";

                pstt=connn.prepareStatement(sql);
                pstt.execute();
              
            }catch(Exception e){
               
            }
            
            
            rownumber.clear();
            columnname.clear();
            celldata.clear();
            table.getColumns().clear();
        
       
        
             
        getallaudititem.fire();
          
       
        
        //////////////////////////////////
  
      Notifications noti = Notifications.create();
      noti.title("Update!");
      noti.text("Operation Updated.");
      noti.position(Pos.CENTER);
      noti.showInformation();
     ///////////////////////////////////AIAR////////////////////////////////////////////////////  
        
          
            try {
                
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value11 = timeString;
            
      String newitemdatee,newitemcodee,newitemsupplierr,newitemmaterialnamee,newitemunitt,newitemunitpricee,newitemstockk,newitemtotalpricee;
      newitemdatee=rownumber.getText();
      newitemcodee=columnname.getText();
      newitemsupplierr=celldata.getText();
//      newitemmaterialnamee=newitemmaterialname.getText();
//      newitemunitt=newitemunit.getText();
//      //newitemunitpricee=newitemunitprice.getText();
//      newitemstockk=newitemstock.getText();
//      newitemtotalpricee=newitemtotalprice.getText();
            
            String reg = "insert into Audit (Date,Code,Supplier,Chemical_Name,Type,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pstt = this.connn.prepareStatement(reg);
            pstt.setString(1,value11);
            pstt.setString(2,"");
            pstt.setString(3,"");
            pstt.setString(4,"");
            pstt.setString(5,"");
            pstt.setString(6,"");
            pstt.setString(7,"");
            pstt.setString(8,"Updated an item from the Laundry Chemical Store, its ID is: "+newitemdatee+" and its value on "+newitemcodee+" was "+newitemsupplierr+"");
            this.pstt.execute();
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
        
         
     
     
       
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void updateiteminfoaction(ActionEvent event) {

        updateanddeletepane.setVisible(true);
        iteminputpane.setVisible(false);
        newitempane.setVisible(false);
        deletebtn.setVisible(false);
        updatebtn.setVisible(true);
    }
    
    
    
    
    
    
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
    void indexaction(ActionEvent event) throws IOException {

    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("ChemicalIndexes.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Chemical Indexes");
    stg.centerOnScreen();
    stg.setResizable(false);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.show();
    
    }

    @FXML
    void leaveaction(ActionEvent event) {

        Platform.exit();
    }
    
    
    
    
    
    
    @FXML
    void sectionaction(Event event) {
        
        
        
    this.sectionbox.getItems().clear();
    TextField filter = this.sectionbox.getEditor();
    String filtertext = filter.getText();
    try {
      BufferedReader buf = new BufferedReader(new FileReader("V:\\KADY\\Important\\Chemicals\\Material\\Stock\\Contents\\Sections.kady"));
      String line;
      while ((line = buf.readLine()) != null) {
        String cap = filtertext.toUpperCase();
        if (line.contains(cap))
          this.sectionbox.getItems().addAll(new String[] { line }); 
      } 
      buf.close();
    } 
    catch (FileNotFoundException fileNotFoundException) {
    
    }
    catch (IOException iOException) {}
    //TextField mll = this.sectionbox.getEditor();
    //String chemicall = mll.getText();    
        
/////////////////////////////////////////////////////
//        this.sectionbox.getItems().clear();
//    try {
//      BufferedReader buf = new BufferedReader(new FileReader("Z:\\KADY\\Important\\Laundry\\Material\\Stock\\Contents\\Sections.kady"));
//      String line;
//      while ((line = buf.readLine()) != null) {
//        this.sectionbox.getItems().addAll(new String[] { line });
//      } 
//      buf.close();
//    } catch (FileNotFoundException fileNotFoundException) {
//    
//    } catch (IOException iOException) {}
//    
    
    
    }
    
    /////////////////////////////KeyEvents/////////////////////////////////////
    
    
    @FXML
    void one(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemsupplier.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemsupplier.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        
    }
    
    
    }
    
    
    
    @FXML
    void two(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemmaterialname.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemmaterialname.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
      newitemcode.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    @FXML
    void three(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemunit.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemunit.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        newitemsupplier.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    @FXML
    void four(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemunitprice.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemunitprice.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        newitemmaterialname.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    @FXML
    void five(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemstock.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemstock.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        newitemunit.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    @FXML
    void six(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      newitemtotalprice.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      newitemtotalprice.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        newitemunitprice.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    @FXML
    void seven(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
     // newitemsupplier.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
    //  newitemsupplier.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        newitemstock.requestFocus();    //Focus
    }
    
    
    }
    
    
    
    
    
    
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
    connn = che_db.java_che_db();
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String timeString = sdf.format(d);
    String value1 = timeString;
    newitemdate.setText(value1);
    inputdate.setText(value1);
    statusbox.getItems().addAll("Input", "Output"); 
        
        
        
    }    
    
}
