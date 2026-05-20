package com.kadysoft.kady;



import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

public class che_db {
   Connection connn = null;
   
   
   
   
   ////////////////////////////////////////////////////////////////////////////////////////////////////////   
        public static String getValueByKey(String filePath, String key) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("=")) {
                continue;
                }
                String[] parts = line.split("=", 2);
                String currentKey = parts[0].trim();
                if (currentKey.equals(key)) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        return null; 
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   
   
   
   
   
   public static String useb,drib;

   public static Connection java_che_db() {
       
       
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
       
       
       ////////////////////////////////////////////////////////////////////////////////////////////////////////   
        public static String getValueByKey(String filePath, String key) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains("=")) {
                    continue;
                }
                String[] parts = line.split("=", 2);
                String currentKey = parts[0].trim();
                if (currentKey.equals(key)) {
                    return parts[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; 
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////// 
       
       
      try {
         Class.forName("org.sqlite.JDBC");
         Connection connn = DriverManager.getConnection("jdbc:sqlite:"+drib+":\\KADINIO\\DATABASES\\Chemicals\\Material\\Stock\\Database\\ChemicalMaterialStock.db");\
         return connn;
      } catch (Exception var1) {
         
          
          
          
          
         return null;
      }
   }
}
