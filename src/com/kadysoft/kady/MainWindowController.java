
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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
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
public class MainWindowController implements Initializable {

    Connection conn = null;
  
    ResultSet rs = null;
  
    PreparedStatement pst = null;
    
    Connection connn = null;
  
    ResultSet rss = null;
  
    PreparedStatement pstt = null;
    
    @FXML
    private MenuItem index;

    @FXML
    private MenuItem leave,about;
    
    @FXML
    private MenuItem addnewitemmenuitem;

    @FXML
    private MenuItem inputitem;

    @FXML
    private MenuItem outputitem;

    @FXML
    private MenuItem updateiteminfo;

    @FXML
    private MenuItem deleteiteminfo,workersmat;

    @FXML
    private MenuItem getallaudititem,matdis;

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
    private JFXTextField newitemmaterialname;

    @FXML
    private JFXTextField newitemunit,qp,tp;

    @FXML
    private JFXTextField newitemunitprice;

    @FXML
    private JFXTextField newitemstock;

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
    private JFXButton deletebtn,addcriti;
    
    @FXML
    private MenuItem auditmenuitem,reportaspdf,undowrong,addcri;
    
    @FXML
    private ComboBox<String> statusbox;

    @FXML
    private ComboBox<String> sectionbox;
    
    @FXML
    private Hyperlink addconsumption;
    
    
    @FXML
    private MenuItem exporttoexcel;
    
    
    @FXML
    private JFXToggleButton openhack;
    
    @FXML
    private MenuItem logout,createpr;
    
   
    public static String criti;
    
    public static String useb,drib,find;
    
    
    
    
       @FXML
    void managepraction (ActionEvent evt) throws IOException {
        
        
      Stage stg = new Stage();
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("ManagePR.fxml"));
      Scene sce = new Scene(root);
      stg.setTitle("Purchasing Request Manager");
      stg.centerOnScreen();
      stg.setResizable(false);
      stg.setScene(sce);
      stg.show(); 
        
        
    }
    
     @FXML
    void matdisaction (ActionEvent evt) throws IOException {
        
        
      Stage stg = new Stage();
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Materials_Disbursed.fxml"));
      Scene sce = new Scene(root);
      stg.setTitle("Materials Disbursed");
      stg.centerOnScreen();
      stg.setResizable(false);
      stg.setScene(sce);
      stg.show(); 
        
        
        
    }
    
    
    
    
     @FXML
    void itemcriaction (ActionEvent evt) throws IOException {
        
        
        //From FXML menu
        
        
      Stage stg = new Stage();
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Critical_MAT.fxml"));
      Scene sce = new Scene(root);
      stg.setTitle("Add Item Critical");
      stg.centerOnScreen();
      stg.setResizable(false);
      stg.setScene(sce);
      stg.show(); 
        
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Critical_MAT.fxml"));
//        Node customContent = loader.load();
//
//        // Create an alert
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Add Item Critical");
//        alert.setHeaderText(null);
//
//        // Create a DialogPane and set the content
//        DialogPane dialogPane = new DialogPane();
//        dialogPane.setContent(customContent);
//        alert.setDialogPane(dialogPane);
//
//        // Show the alert
//        alert.showAndWait();
//        
        
        
    }
    
    
    @FXML
    void addcritiaction (ActionEvent evt) throws IOException {
        
        //Adding new Item pane
        
        
           
        JFXTextField fssg=new JFXTextField ("");
        fssg.setMinSize(100, 25);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Critical Value");
        alert.setHeaderText("Add Critical Value");
        alert.setGraphic(fssg);
        DialogPane dialogPaneef = alert.getDialogPane();
        dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
        Optional<ButtonType> option = alert.showAndWait();
        
        
        
        String sdfdss=newitemmaterialname.getText();
        String pathhhd=""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Critical.kady";
        
        String wr=fssg.getText();
        String allll=sdfdss+"="+wr;
        
        
        
        try {
    final Path path = Paths.get(pathhhd);
    Files.write(path, Arrays.asList(allll), StandardCharsets.UTF_8,
        Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
} catch (final IOException ioe) {
    // Add your own exception handling...
}
        
        
              Notifications noti = Notifications.create();
              noti.title("Successful");
              noti.text("Added to critical Successfully");
              noti.hideAfter(Duration.seconds(3));
              noti.position(Pos.CENTER);
              noti.showInformation();
         
        
        
    }
    
    
    
    
    
     @FXML
    void workersmataction (ActionEvent evt) throws IOException {
        
        
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("Workers_Materials.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Workers Materials.");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.centerOnScreen();
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.show();
       
    }
    
    
     
    @FXML
    void aboutaction (ActionEvent evt) throws IOException {
        
        
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("About.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Welcome To About By Kadysoft Ltd.");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.setAlwaysOnTop(true);
            stg.centerOnScreen();
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.show();
             
        
        
        
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
      DialogPane dialogPaneef = alert.getDialogPane();
      dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
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
    stg.getIcons().add(new javafx.scene.image.Image(Main.class.getResourceAsStream("kadysoft.png")));
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
      String sql = "select * from Stock where Material_Name=?";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, newitemmaterialnamee);
      this.rs = this.pst.executeQuery();
      String add4 = this.rs.getString("Total_Price");
      oldprice.setText(add4);
      this.pst.execute();
            
           
        }
        
        catch (Exception e)

        {
         
        }
        finally {

            try{
                rs.close();
                pst.close();

            }
            catch(Exception e){

            }
        }
     /////////////////////////////////////////////////////////////////////////////////////////////
      /*    try {
            
      String newitemmaterialnamee;
      newitemmaterialnamee=inputmaterialname.getText();
      String sqlY = "select * from Consumption where Material_Name=?";
      this.pst = this.conn.prepareStatement(sqlY);
      this.pst.setString(1, newitemmaterialnamee);
      this.rs = this.pst.executeQuery();
      String add44 = this.rs.getString("Total_Consumption");
      oldcon.setText(add44);
      this.pst.execute();
            
           
        }
        
        catch (Exception e)

        {
         
        }
        finally {

            try{
                rs.close();
                pst.close();

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
     double newval= bv3;
     String newvall=Double.toString(newval);   
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
   
   
          String inq,inp;
          inq=inputquantity.getText();
          inp=inputprice.getText();
          String datyy=inputdate.getText();
          String reg = "insert into Consumption (Date, Supplier, Material_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
          this.pst = this.conn.prepareStatement(reg);
          pst.setString(1,datyy);
          pst.setString(2,inputsupplierr);
          pst.setString(3,newitemmaterialnamee);
          pst.setString(4,"Output");
          pst.setString(5,inq);
          pst.setString(6,inp);
          pst.setString(7,newvall);
          this.pst.execute();
          
          
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
                rs.close();
                pst.close();

            }
            catch(Exception e){

            }
        }
     
    
     }
     
     
     if (mnb.equals("Input")) {
           try {
            
               
                   double bv11=Double.parseDouble(inputtotalprice.getText());
     double bv22=Double.parseDouble(oldprice.getText());
     double bv33=bv11-bv22;
    
     double newvalL= bv33;
     String newvallL=Double.toString(newvalL); 
               
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
   
   
          
          
          
          String inq,inp;
          inq=inputquantity.getText();
          inp=inputprice.getText();
          String datyy=inputdate.getText();
          String reg = "insert into Consumption (Date, Supplier, Material_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
          this.pst = this.conn.prepareStatement(reg);
          pst.setString(1,datyy);
          pst.setString(2,inputsupplierr);
          pst.setString(3,newitemmaterialnamee);
          pst.setString(4,"Input");
          pst.setString(5,inq);
          pst.setString(6,inp);
          pst.setString(7,newvallL);
          this.pst.execute();
          
          
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
                rs.close();
                pst.close();

            }
            catch(Exception e){

            }
        }
     
    
     }
     
     
     else {
         
     }
     
     
     //////////////////////////////////////hi/////////////////////////////////////////////////////
     
        
        
        outputbtn.setDisable(false);
     //   addconsumption.setDisable(true);
       
       
        
    }
  
    
    
    
    
    
    
    
    
    
     @FXML
    void sumaction(ActionEvent event) throws IOException {

    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("Consumption.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Material Consumption");
    stg.centerOnScreen();
    stg.setResizable(true);
    stg.setMaximized(true);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.getIcons().add(new javafx.scene.image.Image(Main.class.getResourceAsStream("kadysoft.png")));
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
    String repname = "All_Laundry_Material_Stock_Of_" + value00;
    String reppath = System.getProperty("user.home") + "\\Desktop";
    FileChooser dialog = new FileChooser();
    dialog.setInitialDirectory(new File(reppath));
    dialog.setInitialFileName(repname);
    dialog.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", new String[] { "*.pdf" }));
    File dialogResult = dialog.showSaveDialog(null);
    String filePath = dialogResult.getAbsolutePath().toString();
    try {
        
         String sql ="select * from Stock";
            
 
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
        
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
      myDocument.add((com.itextpdf.text.Element)new Paragraph("Laundry Material Stock Report", FontFactory.getFont("Times-Bold", 14.0F, 1)));
      myDocument.add((com.itextpdf.text.Element)new Paragraph(" "));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Date", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Code", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Supplier", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Material Name", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Unit", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Stock", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      table.addCell(new PdfPCell((Phrase)new Paragraph("Total Price", FontFactory.getFont("Times-Roman", 9.0F, 1))));
      double total,newtotal=0.0;
      while (rs.next()) {
        table.addCell(new PdfPCell(new Paragraph(rs.getString(2),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(3),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(4),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(5),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(6),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(7),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           table.addCell(new PdfPCell(new Paragraph(rs.getString(8),FontFactory.getFont(FontFactory.TIMES_ROMAN,7,Font.PLAIN))));
           total=Double.parseDouble(rs.getString(8));
           newtotal=total+newtotal;
      } 
      myDocument.add((com.itextpdf.text.Element)table);
      myDocument.add((com.itextpdf.text.Element)new Paragraph("-------------------------------"));
      myDocument.add((com.itextpdf.text.Element)new Paragraph("All Total Prices Are:      "+newtotal+"     $.",FontFactory.getFont(FontFactory.TIMES_ROMAN,20,Font.BOLD)));
      myDocument.setPageSize(PageSize.A4.rotate());
      myDocument.close();
      Alert alooo = new Alert(Alert.AlertType.CONFIRMATION);
      alooo.setTitle("Info");
      alooo.setHeaderText("Info!");
      alooo.setContentText("Report was generated successfully");
      alooo.setResizable(true);
      DialogPane dialogPaneef = alooo.getDialogPane();
      dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
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
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("Audit_Window.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Audit Manager");
    stg.centerOnScreen();
    stg.setResizable(true);
    stg.setMaximized(true);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.getIcons().add(new javafx.scene.image.Image(Main.class.getResourceAsStream("kadysoft.png")));
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
        
        
        String cox=newitemcode.getText();
        try {
        String sql0 = "select Code from Stock where Code like '"+cox+"' UNION select Code from Indexes where Code like '"+cox+"'";
        pst = conn.prepareStatement(sql0);
        rs = pst.executeQuery();
        if (rs.next()) {
        //Alert Found   
        Notifications noti = Notifications.create();
        noti.title("Saving Error!");
        noti.text("We are sorry, we can't add the item, we found an old item with the same code.");
        noti.position(Pos.CENTER);
        noti.showError();               
        }
        else {            
        //Insert
        
        
        
        
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

            String sql ="insert into Indexes (Code,Supplier,Material_Name,Unit,Unit_Price) values (?,?,?,?,?) ";

            pst=conn.prepareStatement(sql);
            pst.setString(1,newitemcodee);
            pst.setString(2,newitemsupplierr);
            pst.setString(3,newitemmaterialnamee);
            pst.setString(4,newitemunitt);
            pst.setString(5,newitemunitpricee);
            

            pst.execute();
            
            
            /////////////////////////////////
            
            String sqll ="insert into Stock (Date,Code,Supplier,Material_Name,Unit,Stock,Total_Price) values (?,?,?,?,?,?,?) ";

            pst=conn.prepareStatement(sqll);
            pst.setString(1,newitemdatee);
            pst.setString(2,newitemcodee);
            pst.setString(3,newitemsupplierr);
            pst.setString(4,newitemmaterialnamee);
            pst.setString(5,newitemunitt);
            pst.setString(6,newitemstockk);
            pst.setString(7,newitemtotalpricee);
            

            pst.execute();
            
            
          String reg = "insert into Consumption (Date, Supplier, Material_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
          this.pst = this.conn.prepareStatement(reg);
          pst.setString(1,newitemdatee);
          pst.setString(2,newitemsupplierr);
          pst.setString(3,newitemmaterialnamee);
          pst.setString(4,"Adding");
          pst.setString(5,newitemstockk);
          pst.setString(6,newitemunitpricee);
          pst.setString(7,newitemtotalpricee);
          
          
          this.pst.execute();
            
            
            
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
                rs.close();
                pst.close();

            }
            catch(Exception e){

            }
        }
  
      }
      
     
        }
       //////////////////Hello Again/////////////////// 
        
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
            
            String sqll ="insert into Stock (Date,Code,Supplier,Material_Name,Unit,Stock,Total_Price) values (?,?,?,?,?,?,?) ";

            pst=conn.prepareStatement(sqll);
            pst.setString(1,newitemdatee);
            pst.setString(2,newitemcodee);
            pst.setString(3,newitemsupplierr);
            pst.setString(4,newitemmaterialnamee);
            pst.setString(5,newitemunitt);
            pst.setString(6,newitemstockk);
            pst.setString(7,newitemtotalpricee);
            

            pst.execute();
            
          String reg = "insert into Consumption (Date, Supplier, Material_Name, Status, Quantity, Price, Total_Consumption) values (?,?,?,?,?,?,?)";
          this.pst = this.conn.prepareStatement(reg);
          pst.setString(1,newitemdatee);
          pst.setString(2,newitemsupplierr);
          pst.setString(3,newitemmaterialnamee);
          pst.setString(4,"Adding");
          pst.setString(5,newitemstockk);
          pst.setString(6,newitemunitpricee);
          pst.setString(7,newitemtotalpricee);
          
          
          this.pst.execute();
            
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
                rs.close();
                pst.close();

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
            
            String reg = "insert into Audit (Date,Code,Supplier,Material_Name,Unit,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pstt = this.connn.prepareStatement(reg);
            pstt.setString(1,newitemdatee);
            pstt.setString(2,newitemcodee);
            pstt.setString(3,newitemsupplierr);
            pstt.setString(4,newitemmaterialnamee);
            pstt.setString(5,newitemunitt);
            pstt.setString(6,"Adding");
            pstt.setString(7,"LAUNDRY MATERIAL STORE");
            pstt.setString(8,"Added a new item to the Laundry Material Store and maybe index too, it's Stock is "+newitemstockk+"");
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
        }
        catch (Exception exception) {
        } 
        finally {
        try {
        rs.close();
        pst.close();
      
        } catch (Exception exception) {}
        } 
        
      
      
    }

    
    
    
    
    
    
    
    
    
    
    @FXML
    void deletebtnaction(ActionEvent event) {

                    
               
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Index");
      alert.setHeaderText("Are you sure want to Delete this Record ?");
      
DialogPane dialogPaneef = alert.getDialogPane();
      dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         
      } else if (option.get() == ButtonType.OK) {
             try {
               
      String namec=columnname.getText();
      String sql = "delete from Stock where "+namec+"=? ";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.celldata.getText());
      this.pst.execute();
      Notifications noti = Notifications.create();
      noti.title("Delete!");
      noti.text("Record Successfully Deleted");
      noti.position(Pos.CENTER);
      noti.showInformation();
    } catch (Exception e) {
    //  JOptionPane.showMessageDialog(null, e);
    } finally {
      try {
        this.rs.close();
        this.pst.close();
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
            
            String reg = "insert into Audit (Date,Code,Supplier,Material_Name,Unit,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pst = this.conn.prepareStatement(reg);
            pst.setString(1,value1);
            pst.setString(2," ");
            pst.setString(3," ");
            pst.setString(4," ");
            pst.setString(5," ");
            pst.setString(6," ");
            pst.setString(7," ");
            pst.setString(8,"Deleted an item from the Laundry Material Store, its ID is: "+newitemdatee+" and its value on "+newitemcodee+" was "+newitemsupplierr+"");
            this.pst.execute();
        }
        
        catch (Exception e)

        {
         
        }
        finally {

            try{
                rs.close();
                pst.close();

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

      
        
        ///////////////////////////////////////////////////////////////
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void inputbtnaction(ActionEvent event) {

    }

    
    
    
    
    
    
    @FXML
    void quantityrel(KeyEvent event) {
        
       // addconsumption.setDisable(false);
        
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
     if (newstock.getText().contains("-")||inputtotalprice.getText().contains("-")) {
      Notifications noti = Notifications.create();
      noti.title("Status Error");
      noti.text("Values contain minus (Less Than Zero).");
      noti.position(Pos.CENTER);
      noti.showError();
      inputquantity.clear();
      newstock.clear();
      qp.clear();
      inputtotalprice.clear();
     }
     else {
         
     }
     
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
      if (newstock.getText().contains("-")||inputtotalprice.getText().contains("-")) {
      Notifications noti = Notifications.create();
      noti.title("Status Error");
      noti.text("Values contain minus (Less Than Zero).");
      noti.position(Pos.CENTER);
      noti.showError();
      inputquantity.clear();
      newstock.clear();
      qp.clear();
      inputtotalprice.clear();
     }
     else {
         
     }
     
     ///////////////////////////////////////////////////////////////////////
      }
      else {
      Notifications noti = Notifications.create();
      noti.title("Status Error");
      noti.text("Choose Status First.");
      noti.position(Pos.CENTER);
      noti.showError();
      }
        
      
      
      
      KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      addconsumption.requestFocus();
      addconsumption.fire();
//Focus
     
    }
      
      
    }
    
    
    
    
    
    
    
    
    @FXML
    void inputcodekeyaction(KeyEvent event) {

        
        
        ///////////////////////////////////////////////////////////////////////////////////
        
        try {
      String sql = "select * from Stock where Code=?";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.inputcode.getText());
      this.rs = this.pst.executeQuery();
      String add2 = this.rs.getString("Material_Name");
      this.inputmaterialname.setText(add2);
      String add3 = this.rs.getString("Stock");
      this.inputstock.setText(add3);
      
      ////////////////////What We Added///////////////
    
      String add4 = this.rs.getString("Total_Price");
      this.tp.setText(add4);
    
      ////////////////////////////////////////////////
    
    
      
    }
        
        catch (Exception exception) {
            
            inputmaterialname.clear();
            inputstock.clear();
            tp.clear();
    } 
        finally {
      try {
        this.rs.close();
        this.pst.close();
      
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
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.inputcode.getText());
      this.rs = this.pst.executeQuery();
      String add4 = this.rs.getString("Unit_Price");
      this.inputprice.setText(add4);
      
      String add44 = this.rs.getString("Supplier");
      this.inputsupplier.setText(add44);
      
      String add444 = this.rs.getString("Unit");
      this.inputunit.setText(add444);
    
      
    }
        
        catch (Exception exception) {
            
            inputprice.clear();
            inputsupplier.clear();
            inputunit.clear();
            
            
    } 
        finally {
      try {
     
        this.rs.close();
        this.pst.close();
      } catch (Exception exception) {}
    } 
        
           KeyCode keycode = event.getCode();
           if (keycode == KeyCode.ENTER)  {
    //  statusbox.show();
    }

        
    }
    
    
    
    
    
    @FXML
    void statusboxhide(Event event) {
        
      //  sectionbox.show();
       // statusbox.hide();
    }
    
    
    
    @FXML
    void sectionboxhide(Event event) {
       // sectionbox.hide();
        inputquantity.requestFocus();
    }
    
    
    @FXML
    void setreleased(KeyEvent event) {
        KeyCode keycode = event.getCode();
           if (keycode == KeyCode.ENTER)  {
      inputdate.requestFocus();
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
    void outputbtnaction(ActionEvent event) throws IOException {

        //Update via code in Stock and add audit, price, total price, stock
         
          
          String newstok = newstock.getText();
          String totalpric = inputtotalprice.getText();
          String cod = inputcode.getText();
            
       
            try {
            
                String sql= "update Stock set Code="+cod+", Stock="+newstok+", Total_Price="+totalpric+" where Code="+cod+"";
                pst=conn.prepareStatement(sql);
                pst.execute();
              
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
      
            String reg = "insert into Audit (Date,Code,Supplier,Material_Name,Unit,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pst = this.conn.prepareStatement(reg);
            pst.setString(1,newitemdatee);
            pst.setString(2,newitemcodee);
            pst.setString(3,supplierr);
            pst.setString(4,newitemmaterialnamee);
            pst.setString(5,unitt);
            pst.setString(6,statuss);
            pst.setString(7,sectionn);
            pst.setString(8,notey);
            this.pst.execute();
           
        }
        
        catch (Exception e)

        {
         
        }
        finally {

            try{
                rs.close();
                pst.close();

            }
            catch(Exception e){

            }
        }
     
     
      
       ////////////////////////////////////////////////////////////////////////////////////Write in file/////////////////////////////////////////////////////////////////////////////////////
            
            PrintWriter pwc=new PrintWriter(new FileWriter (""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Undo.kady"));
            pwc.println("Date="+inputdate.getText());
            pwc.println("Code="+inputcode.getText());
            pwc.println("Material_Name="+inputmaterialname.getText());
            pwc.println("Status="+statusbox.getSelectionModel().getSelectedItem().toString());
            pwc.println("Section="+sectionbox.getSelectionModel().getSelectedItem().toString());
            pwc.println("Quantity="+inputquantity.getText());
            pwc.println("Supplier="+inputsupplier.getText());
            pwc.println("Unit="+inputunit.getText());
            pwc.println("Stock="+inputstock.getText());
            pwc.println("Total_Price="+tp.getText());
            pwc.close();
            
            undowrong.setDisable(false);
            
            
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
     
      
      
      
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
            //statusbox.getSelectionModel().clearSelection();
            //sectionbox.getSelectionModel().clearSelection();
            
            
            
            
        
    }

    
    
    
    
    
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    @FXML
    void undowrongaction(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        JFXTextField t1=new JFXTextField ();
        t1.setAlignment(Pos.CENTER);
        t1.setEditable(false);
        t1.setEffect(new DropShadow());
        t1.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t1.setLabelFloat(true);
        t1.setPromptText("Date");
        t1.setMinSize(200, 27);
        //t1.setPromptText("");
        
        JFXTextField t2=new JFXTextField ();
        t2.setAlignment(Pos.CENTER);
        t2.setEditable(false);
        t2.setEffect(new DropShadow());
        t2.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t2.setLabelFloat(true);
        t2.setPromptText("Code");
        t2.setMinSize(200, 27);
        //t2.setPromptText("");
        
        JFXTextField t3=new JFXTextField ();
        t3.setAlignment(Pos.CENTER);
        t3.setEditable(false);
        t3.setEffect(new DropShadow());
        t3.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t3.setLabelFloat(true);
        t3.setPromptText("Material_Name");
        t3.setMinSize(200, 27);
        //t3.setPromptText("");
        
        JFXTextField t4=new JFXTextField ();
        t4.setAlignment(Pos.CENTER);
        t4.setEditable(false);
        t4.setEffect(new DropShadow());
        t4.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t4.setLabelFloat(true);
        t4.setPromptText("Status");
        t4.setMinSize(200, 27);
        //t4.setPromptText("");
        
        JFXTextField t5=new JFXTextField ();
        t5.setAlignment(Pos.CENTER);
        t5.setEditable(false);
        t5.setEffect(new DropShadow());
        t5.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t5.setLabelFloat(true);
        t5.setPromptText("Section");
        t5.setMinSize(200, 27);
        //t5.setPromptText("");
        
        JFXTextField t6=new JFXTextField ();
        t6.setAlignment(Pos.CENTER);
        t6.setEditable(false);
        t6.setEffect(new DropShadow());
        t6.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t6.setLabelFloat(true);
        t6.setPromptText("Quantity");
        t6.setMinSize(200, 27);
        //t6.setPromptText("");
        
        JFXTextField t7=new JFXTextField ();
        t7.setAlignment(Pos.CENTER);
        t7.setEditable(false);
        t7.setEffect(new DropShadow());
        t7.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t7.setLabelFloat(true);
        t7.setPromptText("Supplier");
        t7.setMinSize(200, 27);
        //t7.setPromptText("");
        
        JFXTextField t8=new JFXTextField ();
        t8.setAlignment(Pos.CENTER);
        t8.setEditable(false);
        t8.setEffect(new DropShadow());
        t8.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t8.setLabelFloat(true);
        t8.setPromptText("Unit");
        t8.setMinSize(200, 27);
        //t8.setPromptText("");
        
        JFXTextField t9=new JFXTextField ();
        t9.setAlignment(Pos.CENTER);
        t9.setEditable(false);
        t9.setEffect(new DropShadow());
        t9.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t9.setLabelFloat(true);
        t9.setPromptText("Stock");
        t9.setMinSize(200, 27);
        //t9.setPromptText("");
        
        JFXTextField t10=new JFXTextField ();
        t10.setAlignment(Pos.CENTER);
        t10.setEditable(false);
        t10.setEffect(new DropShadow());
        t10.setStyle("-fx-font-weight:bold;-fx-font-size:13;");
        t10.setLabelFloat(true);
        t10.setPromptText("Total_Price");
        t10.setMinSize(200, 27);
        //t10.setPromptText("");
        
        VBox vn=new VBox(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10);
        vn.setSpacing(15);
        vn.setAlignment(Pos.CENTER);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
      BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Undo.kady"));
    
          t1.setText(buf.readLine().replace("Date=", ""));
          
          t2.setText(buf.readLine().replace("Code=", ""));
          
          t3.setText(buf.readLine().replace("Material_Name=", ""));
          
          t4.setText(buf.readLine().replace("Status=", ""));
          
          t5.setText(buf.readLine().replace("Section=", ""));
          
          t6.setText(buf.readLine().replace("Quantity=", ""));
          
          t7.setText(buf.readLine().replace("Supplier=", ""));
          
          t8.setText(buf.readLine().replace("Unit=", ""));
          
          t9.setText(buf.readLine().replace("Stock=", ""));
          
          t10.setText(buf.readLine().replace("Total_Price=", ""));
           
          
  //  }
     buf.close();
     Alert am=new Alert(Alert.AlertType.INFORMATION);
        am.setTitle("Undo wrong operation");
        am.setContentText("This info is wrong?");
        am.setGraphic(vn);
        DialogPane dialogPaneef = am.getDialogPane();
      dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
        am.showAndWait();
      ///////////////////////////Delete Consumption/////////////////////////////////////     
      String sql = "delete from Consumption where Date=? and Material_Name=? and Status=? and Quantity=? ";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, t1.getText());
      this.pst.setString(2, t3.getText());
      this.pst.setString(3, t4.getText());
      this.pst.setString(4, t6.getText());
      this.pst.execute();
      ///////////////////////////////////////////////////////////////////////////////////
      ///////////////////////////Delete Audit/////////////////////////////////////     
      String sqll = "delete from Audit where Date=? and Material_Name=? and Status=? and Section=? and Supplier=? and Unit=? ";
      this.pst = this.conn.prepareStatement(sqll);
      this.pst.setString(1, t1.getText());
      this.pst.setString(2, t3.getText());
      this.pst.setString(3, t4.getText());
      this.pst.setString(4, t5.getText());
      this.pst.setString(5, t7.getText());
      this.pst.setString(6, t8.getText());
      this.pst.execute();
      ///////////////////////////////////////////////////////////////////////////////////
      ///////////////////////////Update Stock/////////////////////////////////////   
      String cood=t2.getText();
      String suppli=t7.getText();
      String chnm=t3.getText();
      String typp=t8.getText();
      String stkk=t9.getText();
      String topp=t10.getText();
      String sqlll = "UPDATE Stock SET Code='"+cood+"', Supplier='"+suppli+"', Material_Name='"+chnm+"', Unit='"+typp+"', Stock='"+stkk+"', Total_Price='"+topp+"' where Code='"+cood+"' ";
      this.pst = this.conn.prepareStatement(sqlll);
      this.pst.executeUpdate();
      ///////////////////////////////////////////////////////////////////////////////////
      
      Notifications noti = Notifications.create();
      noti.title("Undo Operation!");
      noti.text("Record Successfully Undo");
      noti.position(Pos.CENTER);
      noti.showInformation();
     
      undowrong.setDisable(true);
     getallaudititem.fire();
    
             }
    
     //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    void outputitemaction(ActionEvent event) {

        updateanddeletepane.setVisible(false);
        iteminputpane.setVisible(true);
        newitempane.setVisible(false);
        inputbtn.setVisible(false);
        outputbtn.setVisible(true);
        inputdate.requestFocus();
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

                pst=conn.prepareStatement(sql);
                pst.execute();
              
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
            
            String reg = "insert into Audit (Date,Code,Supplier,Material_Name,Unit,Status,Section,Notes) values (?,?,?,?,?,?,?,?)";
            this.pst = this.conn.prepareStatement(reg);
            pst.setString(1,value11);
            pst.setString(2,"");
            pst.setString(3,"");
            pst.setString(4,"");
            pst.setString(5,"");
            pst.setString(6,"");
            pst.setString(7,"");
            pst.setString(8,"Updated an item from the Laundry Material Store, its ID is: "+newitemdatee+" and its value on "+newitemcodee+" was "+newitemsupplierr+"");
            this.pst.execute();
        }
        
        catch (Exception e)

        {
         
        }
        finally {

            try{
                rs.close();
                pst.close();

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
    void indexaction(ActionEvent event) throws IOException {

    Stage stg = new Stage();
    Parent root = FXMLLoader.<Parent>load(getClass().getResource("Indexes.fxml"));
    Scene sce = new Scene(root);
    stg.setTitle("Material Indexes");
    stg.centerOnScreen();
    stg.setResizable(true);
    stg.setMaximized(true);
    stg.setScene(sce);
    stg.centerOnScreen();
    stg.getIcons().add(new javafx.scene.image.Image(Main.class.getResourceAsStream("kadysoft.png")));
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
      BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Sections.kady"));
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
    void addconrel(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      outputbtn.requestFocus();    //Focus
      outputbtn.fire();
    }
    }
    
    
    @FXML
    void datekeyrel(KeyEvent event) {

        KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)  {
      inputcode.requestFocus();    //Focus
     
    }
    else if (keycode == KeyCode.RIGHT) {
      inputcode.requestFocus();    //Focus
    }
    else if (keycode == KeyCode.LEFT) {
        
    }
    
    
    }
    
    
    
    
    
    
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
    
    
    
    
    @FXML
    void createpraction (ActionEvent evt) throws IOException {
        
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("PR_MAT.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Purchase Request Maker.");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.setAlwaysOnTop(true);
            stg.centerOnScreen();
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.show();
       
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
               
    
        
        
        
        
        
    conn = db.java_db();
    connn = db.java_db();
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    newitemdate.setText(value1);
    inputdate.setText(value1);
    statusbox.getItems().addAll("Input", "Output"); 
        
        
         getallaudititem.fire();
         
         
         
         
               
      
                     
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
      Stage jk = (Stage)this.newitemcode.getScene().getWindow();
      jk.close();
      }
         
     
         
         
         /////////////////////////////////////////////Call All Here//////////////////////////////////////////////////
         
         //Delete table data first (We will create a table called 'Critical'.
         //Read All stock and file in same time and compare.
         //if equal or less than the critical value. write to table 'critical'.
         //if not found or greater than ignore.
         //Show alert with data of table to suggest user to create PR or ignore that.
         //if user clicked PR open frame (you can open from file menu too).
         //frame has a table for critical and to select to add from table to pr or add new.
         //then open the pr with name of sheet (date of today).
         
         //Note: when create PR, give to options to get from critical or get from all stock or write another
         
          try {
            String sql = "delete from Critical";
            pst=conn.prepareStatement(sql);
            pst.execute();  
            }catch(Exception e){ 
            }
            finally {
            try{
                rs.close();
                pst.close(); 
            }catch(Exception e){
            
            }}
        // try {Thread.sleep(500);} catch (InterruptedException ex) {}
        
        
    
        try {
        
        
         try {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reading File");
            alert.setContentText("Hello, We are reading critical data now, click 'OK' button to continue.");
            alert.setResizable(false);
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
          getClass().getResource("cupertino-dark.css").toExternalForm());
            alert.showAndWait();
             
            BufferedReader buf = new BufferedReader(new FileReader(""+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Contents\\Critical.kady"));
            String line; 
            String sql0 = "select Material_Name, Unit, Stock from Stock";
            pst = conn.prepareStatement(sql0);
            rs = pst.executeQuery();
            String mat,sto,unitt;
            double stok,lon;
            
            while (rs.next()||(line=buf.readLine())!=null) {
                //Read and compare......
                line=buf.readLine();
                System.out.println(line);
                mat=rs.getString("Material_Name");
                System.out.println(mat);
                unitt=rs.getString("Unit");
                System.out.println(unitt);
                sto=rs.getString("Stock");
                System.out.println(sto);
                stok=Double.parseDouble(sto);
                lon=Double.parseDouble(line.replace(mat+"=",""));
                
                if (stok<=lon) {  
                //Write to Critical table.   
                //System.out.println(mat+"="+sto);
                String reg = "insert into Critical (Material, Unit, Stock) values (?,?,?)";
                pst = conn.prepareStatement(reg);
                pst.setString(1,mat);
                pst.setString(2,unitt);
                pst.setString(3,sto);
                pst.execute(); 
                }
                else {
                }
            }
            }catch(Exception e){ 
            }
            finally {
            try{
                rs.close();
                pst.close(); 
            }catch(Exception e){}}
            
         try {
            String sql0 = "select Material, Stock from Critical";
            pst = conn.prepareStatement(sql0);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                
                criti=criti+"\n"+rs.getString("Material")+"="+rs.getString("Stock");
                
                //System.out.println(rs.getString("Material")+"="+rs.getString("Stock"));
                
            }
            
            }catch(Exception e){ 
            }
            finally {
            try{
                rs.close();
                pst.close();
                
               //Show alert here. 
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Critical Materials");
               JFXTextArea jds=new JFXTextArea (criti.replace("null",""));
               jds.setMinSize(250, 650);
               jds.setEditable(false);
               jds.setLabelFloat(true);
               jds.setPromptText("Critical Materials");
               jds.setStyle("-fx-font-weight:bold;");
               ScrollPane sp=new ScrollPane();
               sp.setContent(jds);
               alert.setHeaderText("We suggest to create Purchase Request to refresh your stock.\n\nPowered By Kadysoft Ltd.");
               alert.setGraphic(sp);
               alert.setResizable(false);
               alert.setWidth(350);
               alert.setHeight(750);
               DialogPane dialogPaneef = alert.getDialogPane();
               dialogPaneef.getStylesheets().add(
             getClass().getResource("primer-dark.css").toExternalForm());
               ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Create PR");
               ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Ignore That");
               Optional<ButtonType> result = alert.showAndWait();
               ButtonType button = result.orElse(ButtonType.CANCEL);
               if (button == ButtonType.OK) {
    
                   //System.out.println("Open PR.");
                   
                   createpr.fire();
    
               }

               else {
    
                   //System.out.println("Ignored It.");
    
               }
               
            }catch(Exception e){}}
         
         
         
        }catch(Exception e){
        
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Fatal Error");
      alert.setContentText("Fatal Error while reading user file.\nWe can't find the specified file.\nOr we can't read from or write to database.\nPlease cheack the problem and fix it.");
      alert.setResizable(false);
      DialogPane dialogPane = alert.getDialogPane();
      dialogPane.getStylesheets().add(
    getClass().getResource("cupertino-dark.css").toExternalForm());
      alert.showAndWait();
        
        }
           
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
     
     
        
            
            
         
    }    
    
}
