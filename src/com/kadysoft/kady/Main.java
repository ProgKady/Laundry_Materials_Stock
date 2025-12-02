package com.kadysoft.kady;



import com.jfoenix.controls.JFXPasswordField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.controlsfx.control.Notifications;

public class Main extends Application {
   public void start(Stage stage) throws Exception {
      Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Welcome.fxml"));
      Scene scene = new Scene(root);
      stage.setTitle("Welcome To T&C Laundry Stock Manager, By Kadysoft Ltd.");
      stage.centerOnScreen();
      stage.setResizable(false);
      stage.centerOnScreen();
      stage.setScene(scene);
      stage.getIcons().add(new Image(Main.class.getResourceAsStream("kadysoft.png")));
      stage.show();
      
      
      
       //////////////////////////Add All Codes Here////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Read first and compare then write
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;

    File ff=new File (System.getProperty("user.home")+"\\AppData\\Roaming\\.store_cfg");
    
    if (ff.exists()) {
        //Read then compare if equals 30 or more show alert and exit, if less don't do anything.
      BufferedReader buff=new BufferedReader(new FileReader(ff));
      String line;
      line=buff.readLine();
      buff.close();
      LocalDate d1 = LocalDate.parse(line, DateTimeFormatter.ISO_LOCAL_DATE);
      LocalDate d2 = LocalDate.parse(value1, DateTimeFormatter.ISO_LOCAL_DATE);
      Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
      long diffDays = diff.toDays();
      
      if (diffDays>=30) {
          //Show Alert to register or close.
          ///////////////////////////////////////////////////////////////////////////////////////////////////
          
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Session Expired");
          alert.setResizable(false);
          DialogPane dialogPaneef = alert.getDialogPane();
      dialogPaneef.getStylesheets().add(
      getClass().getResource("primer-dark.css").toExternalForm());
          Label l1=new Label("Sorry, your expiration date has been ended.");
          l1.setEffect(new DropShadow());
          l1.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-font-size:15;-fx-background-radius:3em;");
          
          Label l2=new Label("Please, click register to enter the serial key to start another period.");
          l2.setEffect(new DropShadow());
          l2.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-font-size:15;-fx-background-radius:3em;");
          
          Label l3=new Label("Or click 'OK' or 'EXIT' to exit and cancel the operation.");
          l3.setEffect(new DropShadow());
          l3.setStyle("-fx-background-color:white;-fx-font-weight:bold;-fx-font-size:15;-fx-background-radius:3em;");
          
          Hyperlink hy=new Hyperlink ("Register");
          hy.setEffect(new DropShadow());
          hy.setStyle("-fx-background-color:lightblue;-fx-font-weight:bold;-fx-font-size:12;-fx-background-radius:3em;");
         
          JFXPasswordField jfx=new JFXPasswordField ();
          jfx.setAlignment(Pos.CENTER);
          jfx.setLabelFloat(true);
          jfx.setPromptText("Enter Serial Key!!!.");
          jfx.setMinSize(300, 30);
          jfx.setStyle("-fx-background-color:lightblue;-fx-font-weight:bold;-fx-font-size:15;");
          jfx.setEffect(new DropShadow());
          
          VBox pane=new VBox();
          pane.getChildren().addAll(l1,l2,l3,hy,jfx);
          pane.setSpacing(20);
          
          
          alert.setGraphic(pane);
          
          Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
         stage.close();
      } else if (option.get() == ButtonType.OK) {
      ///////////////////////////
      
      String textt=jfx.getText();
    
          if (textt.equals("WE LOVE KADYSOFT")) {
             //Show noti and change date in file. 
              
             ff.createNewFile();
             PrintWriter pw=new PrintWriter (new FileWriter (ff));
             pw.print(value1);
             pw.close();
             
             Notifications noti = Notifications.create();
             noti.title("Congratulations!");
             noti.text("Congratulations!, you have started a new period\nEnjoy using our software.\n\n\nPowered By Kadysoft Ltd, Ahmed Elkady - CEO.");
             noti.position(Pos.CENTER);
             noti.hideAfter(javafx.util.Duration.minutes(1));
             noti.showInformation();
             
             alert.close();
          }
          
          else {
          Notifications noti = Notifications.create();
          noti.title("Wrong Serial Key!");
          noti.text("Sorry, I will close.");
          noti.position(Pos.CENTER);
          noti.showInformation();
          alert.close();
          stage.close();
          }  
      ///////////////////////////
      }
      else if (option.get() == ButtonType.CANCEL) {
      Notifications noti = Notifications.create();
      noti.title("Cancel!");
      noti.text("Operation Cancelled, nothing will happen.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      stage.close();
      }
      else {
         
      }
         
          ///////////////////////////////////////////////////////////////////////////////////////////////////
          
      }
      
      else {
          // Do Nothing
      }
    }
    else {
        //Write date for the first time.
        ff.createNewFile();
        PrintWriter pw=new PrintWriter (new FileWriter (ff));
        pw.print("2023-10-20");
        pw.close();
    }
      
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      
      
      
      
      
   }

   public static void main(String[] args) {
      launch(args);
   }
}
