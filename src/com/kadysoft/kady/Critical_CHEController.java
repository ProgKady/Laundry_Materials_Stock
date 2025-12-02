
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class Critical_CHEController implements Initializable {

    
    @FXML
    private JFXButton critical;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField chemical;

    @FXML
    private JFXTextField value;
    
    
    Connection connn = null;
  
    ResultSet rss = null;
  
    PreparedStatement pstt = null;
    
     public static String useb,drib;
    
    
    

    @FXML
    void codeaction(KeyEvent event) {

           try {
      String sql = "select * from Stock where Code=?";
      this.pstt = this.connn.prepareStatement(sql);
      this.pstt.setString(1, this.code.getText());
      this.rss = this.pstt.executeQuery();
      String add2 = this.rss.getString("Chemical_Name");
      this.chemical.setText(add2);
      //////////////////////
    
    
      
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
    void criticalaction(ActionEvent event) {

        
        String che=chemical.getText();
        String equal="=";
        String val=value.getText();
        
        String all=che+equal+val;
        
        
          String pathhhd=""+drib+":\\KADINIO\\DATABASES\\Chemicals\\Material\\Stock\\Contents\\Critical.kady";
        
        try {
    final Path path = Paths.get(pathhhd);
    Files.write(path, Arrays.asList(all), StandardCharsets.UTF_8,
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         
      connn = che_db.java_che_db();
    
    
        
        
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
      Stage jk = (Stage)this.code.getScene().getWindow();
      jk.close();
      }
         
        
        
        
        
        
    }    
    
}
