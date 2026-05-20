
package com.kadysoft.kady;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.glisten.animation.PulseTransition;
import com.gluonhq.charm.glisten.animation.SwingTransition;
import com.gluonhq.charm.glisten.animation.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class WelcomeController implements Initializable {

    
    @FXML
    private JFXButton laundrybtn;

    @FXML
    private JFXButton chemicalbtn;

    @FXML
    private JFXButton exitbtn;
    
    
    
    PulseTransition pt1;
    SwingTransition pt2;
    PulseTransition pt3;
    HingeTransition pt4;
    RollOutTransition pt5;
    
    
    
    
    @FXML
    void exitaction(MouseEvent event) {

        
        
    }
    
    
        @FXML
    void settingsaction(MouseEvent event) throws IOException {

        
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("Settings.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Settings");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.setScene(sce);
            stg.show();
    
        
    }
    
    
    @FXML
    void chemicalbtnaction(ActionEvent event) throws IOException {

        pt4=new HingeTransition (chemicalbtn);
        pt4.setAutoReverse(true);
        pt4.setCycleCount(1);
        pt4.play();
        
        
            Stage jk = (Stage)this.exitbtn.getScene().getWindow();
            jk.close();
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("ChemicalLogIn_GUI.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Chemicals Material Stock");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.show();
            jk.close();
        
    }

    @FXML
    void exitbtnaction(ActionEvent event) throws InterruptedException {
        
        
        pt5=new RollOutTransition (exitbtn);
        //pt5.setAutoReverse(true);
        pt5.setCycleCount(10);
        pt5.play();
        
        pt4=new HingeTransition (exitbtn);
        //pt4.setAutoReverse(true);
        //pt4.setCycleCount(1);
        pt4.play();
        pt4.setOnFinished(Event ->{
            pt4.stop();
        });
        
        //Thread.sleep(1000);
        
        System.exit(0);

    }

    @FXML
    void laundrybtnaction(ActionEvent event) throws IOException {
        
        pt4=new HingeTransition (laundrybtn);
        pt4.setAutoReverse(true);
        pt4.setCycleCount(10);
        pt4.play();
        
            Stage jk = (Stage)this.exitbtn.getScene().getWindow();
            jk.close();
            Stage stg = new Stage();
            Parent root = FXMLLoader.<Parent>load(getClass().getResource("LogIn_GUI.fxml"));
            Scene sce = new Scene(root);
            stg.setTitle("Laundry Material Stock");
            stg.centerOnScreen();
            stg.setResizable(false);
            stg.setScene(sce);
            stg.centerOnScreen();
            stg.show();
            jk.close();
        
        

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        pt2=new SwingTransition (exitbtn);
        pt2.setAutoReverse(true);
        pt2.setCycleCount(1000);
        pt2.play();
        
        pt1=new PulseTransition (laundrybtn);
        pt1.setAutoReverse(true);
        pt1.setCycleCount(1000);
        pt1.play();
        
        pt3=new PulseTransition (chemicalbtn);
        pt3.setAutoReverse(true);
        pt3.setCycleCount(1000);
        pt3.play();
        
//        pt4=new HingeTransition (laundrybtn);
//        pt4.setAutoReverse(true);
//        pt4.setCycleCount(1000);
//        pt4.play();
//        
        
    }    
    
}
