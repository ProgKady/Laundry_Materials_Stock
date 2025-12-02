package com.kadysoft.kady;



import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class db {
   Connection conn = null;
   
   public static String useb,drib;

   public static Connection java_db() {
       
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
      alert.showAndWait();
          }
       
       
      try {
         Class.forName("org.sqlite.JDBC");
         Connection conn = DriverManager.getConnection("jdbc:sqlite:"+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Database\\LaundryMaterialStock.db");
         //Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Ahmed.ElKady\\Desktop\\KADY\\Important\\Laundry\\Material\\Stock\\Database\\LaundryMaterialStock.db");
         return conn;
      } catch (Exception var1) {
         
          
          
          
         return null;
      }
   }
}
