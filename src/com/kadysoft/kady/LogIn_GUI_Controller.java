package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

public class LogIn_GUI_Controller implements Initializable {
  @FXML
  private ResourceBundle resources;
  
  @FXML
  private URL location;
  
  
  
  @FXML
  private JFXTextField namefield;
  
  @FXML
  private JFXPasswordField passwordfield;
  
  @FXML
  private JFXButton loginbtn;
  
  
  
  Connection conn = null;
  
  ResultSet rs = null;
  
  PreparedStatement pst = null;
  
  @FXML
  void enternamekeypressed(KeyEvent event) {
    KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)
      this.passwordfield.requestFocus(); 
  }
  
  @FXML
  void enterpasswordkeypressed(KeyEvent event) {
    KeyCode keycode = event.getCode();
    if (keycode == KeyCode.ENTER)
      this.loginbtn.fire(); 
  }
  
  
  
  @FXML
  void loginbtnaction(ActionEvent event) {
      
      
    if (this.namefield.getText().equals("")) {
        
        
        
      Image img = new Image(getClass().getResourceAsStream("kadysoft.png"));
      ImageView imgview = new ImageView();
      imgview.setImage(img);
      Notifications noti = Notifications.create();
      noti.title("LogIn Error");
      noti.text("Username Field is empty.");
      noti.hideAfter(Duration.minutes(1.0D));
      noti.graphic(imgview);
      noti.position(Pos.CENTER);
      noti.show();
      
      
      
    } else if (this.passwordfield.getText().equals("")) {
        
        
        
      Image img = new Image(getClass().getResourceAsStream("kadysoft.png"));
      ImageView imgview = new ImageView();
      imgview.setImage(img);
      Notifications noti = Notifications.create();
      noti.title("LogIn Error");
      noti.text("Password Field is empty.");
      noti.hideAfter(Duration.minutes(1.0D));
      noti.graphic(imgview);
      noti.position(Pos.CENTER);
      noti.show();
      
      
      
    } else {
        
        
        
      String sql = "select ID,UserName,Password from Users Where (UserName =? and Password =?)";
      try {
        int count = 0;
        this.pst = this.conn.prepareStatement(sql);
        this.pst.setString(1, this.namefield.getText());
        this.pst.setString(2, this.passwordfield.getText());
        this.rs = this.pst.executeQuery();
        while (this.rs.next()) {
          int id = this.rs.getInt(1);
          count++;
        } 
        
        
       
          if (count == 1) {
            Alert al = new Alert(Alert.AlertType.INFORMATION);
            al.setTitle("Log In Information");
            al.setHeaderText("LogIn Successful");
            al.setContentText("Sucessful Login, Developed by Kadysoft Ltd.");
            al.setResizable(false);
            Stage jk = (Stage)this.loginbtn.getScene().getWindow();
            jk.close();
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("MainWindow.fxml"));
            Scene sce = new Scene(root);
            //sce.getStylesheets().add("table-cell-color-example.css");
            stg.setTitle("Laundry Material Stock");
            stg.centerOnScreen();
            stg.setResizable(true);
            stg.setMaximized(true);
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.getIcons().add(new Image(Main.class.getResourceAsStream("kadysoft.png")));
            stg.show();
            jk.close();
            
            
            
              
          ///////////////Add in DB to logged users.//////////////////////
          
    String dodo,userrr;  
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    dodo=value1;
    userrr=namefield.getText();
            String reg = "insert into LoggedIn_Users (Date,User_Name,Log) values (?,?,?)";
            pst = conn.prepareStatement(reg);
            pst.setString(1,dodo);
            pst.setString(2,userrr);
            pst.setString(3,userrr+" has logged in successfully.");
            pst.execute();
          
          
          
          ///////////////////////////////////////////////////////////////
            
            
            
            
     
              }
          
          
          
          
          
          
          
          
          
          
          
          
      }
              catch (Exception e) {
        Alert alo = new Alert(Alert.AlertType.INFORMATION);
        alo.setTitle(e.toString());
        alo.setResizable(false);
        alo.setHeaderText(e.toString());
        alo.setContentText("Sorry we face a problem : Database Is Read-Only.\n\nPowered By Kadysoft Ltd - Ahmed Elkady.");
        DialogPane dialogPane = alo.getDialogPane();
        dialogPane.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
        alo.showAndWait();
        } finally {
          try {
            this.rs.close();
            this.pst.close();
          } catch (Exception exception) {}
        }  
          
          
          //////////////////////////////////////////////////////////////////////////////
          //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
          
    
            
          
        
      
       
    } 
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public void initialize(URL url, ResourceBundle rb) {
    Toolkit tool = Toolkit.getDefaultToolkit();
    tool.setLockingKeyState(20, true);
    this.conn = db.java_db();
    this.namefield.requestFocus();
  }
}
