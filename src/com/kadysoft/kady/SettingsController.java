package com.kadysoft.kady;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class SettingsController implements Initializable {


    
    
     @FXML
    private JFXTextField db_field;

    @FXML
    private ComboBox<String> db_tables_box;

    @FXML
    private JFXTextArea log_area;

    @FXML
    private TableView<ObservableList<String>> table;

    @FXML
    private JFXTextField tf1;

    @FXML
    private JFXTextField tf2;

    @FXML
    private JFXTextField tf3;

    @FXML
    private JFXTextField tf4;

    @FXML
    private JFXTextField tf5;

    @FXML
    private JFXButton save;
    
    
    
    
    
    
    @FXML
    void browse1action(ActionEvent event) {

      FileChooser fcho = new FileChooser();
      fcho.setTitle("Kady Choose");
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Kady Files", new String[]{"*.kady"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      tf1.setText(dirpathe);
        
    }

    
    
    
    @FXML
    void browse2action(ActionEvent event) {

        
      FileChooser fcho = new FileChooser();
      fcho.setTitle("Kady Choose");
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Kady Files", new String[]{"*.kady"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      tf2.setText(dirpathe);
        
    }

    
    
    
    @FXML
    void browse3action(ActionEvent event) {

      FileChooser fcho = new FileChooser();
      fcho.setTitle("Kady Choose");
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", new String[]{"*.png","*.jpg","*.jpeg"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      tf3.setText(dirpathe);
        
        
    }
    
    
    
    
    

    @FXML
    void browse4action(ActionEvent event) {

        
      FileChooser fcho = new FileChooser();
      fcho.setTitle("Kady Choose");
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Kady Files", new String[]{"*.kady"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      tf4.setText(dirpathe);
        
        
    }

    
    
    
    
    
    
    @FXML
    void browse5action(ActionEvent event) {

        
      FileChooser fcho = new FileChooser();
      fcho.setTitle("Kady Choose");
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Font Files", new String[]{"*.ttf"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      tf5.setText(dirpathe);
        
        
    }

    
    
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @FXML
    void create_dbaction(ActionEvent event) {

        
        try {
            // قراءة الـ base64 من ملف خارجي
            String base64 = readFileToString("lib\\db_base64.txt").trim();
            byte[] dbBytes = Base64.getDecoder().decode(base64);
            String outputPath = System.getProperty("user.home")+"\\Desktop\\System_Materials_DB.db";
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(dbBytes);
            }
            
            Notifications noti = Notifications.create();
            noti.title("Great!");
            noti.text("I created the DataBase Successfully at "+outputPath);
            noti.position(Pos.CENTER);
            noti.hideAfter(Duration.seconds(3));
            noti.showInformation();
            
        } catch (Exception e) {
        Notifications noti = Notifications.create();
        noti.title("Fatal Error!");
        noti.text("We can't continue, the DataBase wasn't created.");
        noti.position(Pos.CENTER);
        noti.hideAfter(Duration.seconds(3));
        noti.showError();
            e.printStackTrace();
        }
        
        
    }

    
        private static String readFileToString(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
    
    
    
    
    
    @FXML
    void db_browseaction(ActionEvent event) {

        
        
      FileChooser fcho = new FileChooser();
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sqlite Files", new String[]{"*.db"}));
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sqlite Files", new String[]{"*.sqlite"}));
      fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sqlite Files", new String[]{"*.sqlite3"}));
      fcho.setTitle("Kady Choose");
      File f = fcho.showOpenDialog((Window)null);
      String dirpathe = f.getAbsolutePath().toString();
      db_field.setText(dirpathe);
        
        
        
    }

    
    
    
    
    
    
    
    @FXML
    void db_tablesaction(ActionEvent event) throws ClassNotFoundException, SQLException {

        loadTables();
        
    }

    
    
    
    
    
    
    
    @FXML
    void discardction(ActionEvent event) {

        
    Stage jk = (Stage)this.save.getScene().getWindow();
    jk.close();
    
        
    }
    
    
    
    
    
    
    
    

    @FXML
    void saveaction(ActionEvent event) throws IOException {

        
        
        
        //Save Data about DB and Times
        
        String dbb=db_field.getText();
        
        String recdb=tf1.getText();
        String recipepathth=tf2.getText();
        String tim=tf3.getText();
        String secc=tf4.getText();
        String mach=tf5.getText();
        
        String settingsfile="lib\\setto.cfg";
        PrintWriter pp=new PrintWriter (new FileWriter (settingsfile));
        pp.println("DataBase="+dbb);
        
        pp.println("Sections="+recdb);
        pp.println("Critical="+recipepathth);
        pp.println("Logo="+tim);
        pp.println("Undo="+secc);
        pp.println("Font="+mach);
     
        //Continue Saving
        
        pp.close();
        
        Notifications noti = Notifications.create();
        noti.title("Successful");
        noti.text("We have updated the settings successfully.");
        noti.hideAfter(Duration.seconds(3));
        noti.position(Pos.CENTER);
        noti.showInformation();
    
        
          ////////////////////////Audit//////////////////////////
      
  
      ///////////////////////////////////////////////////////
          
      // Example: wait 3 seconds then run code
PauseTransition pauset = new PauseTransition(Duration.seconds(2));
pauset.setOnFinished(eventy -> {
    
    Stage jk = (Stage)this.save.getScene().getWindow();
    jk.close();
    
});
pauset.play();
      

        
    }
    
    
    
    

     
    
    public static void runCommand(String command, String workingDir) throws Exception {
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        builder.directory(new java.io.File(workingDir));
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        process.waitFor();
    }
    
    
    
    
    
    
    
    @FXML
    void test_dbaction(ActionEvent event) {

        
        
        
        try {
         Class.forName("org.sqlite.JDBC");
         Connection conn = DriverManager.getConnection("jdbc:sqlite:"+db_field.getText());
         if (conn != null) {
         log_area.clear();
         log_area.appendText("\nSystem DataBase Connected Successfully.\n");
         }
         } catch (Exception var1) {
         log_area.clear();
         log_area.appendText("\nConnection Failed!\n");
         }
        
        
        
        
    }
    
    
private void loadTables() throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + db_field.getText())) {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table'");

        db_tables_box.getItems().clear();

        while (rs.next()) {
            String tableName = rs.getString("name");
            if (tableName.equalsIgnoreCase("Users")) {
                continue;
            }
            db_tables_box.getItems().add(tableName);
        }

        // ────────────── مهم جداً ──────────────
        // نضع الـ listener مرة واحدة فقط خارج اللوب
        db_tables_box.setOnAction(event -> {
            String selectedTable = db_tables_box.getValue();
            if (selectedTable != null) {
                System.out.println("تم اختيار الجدول: " + selectedTable);
                try {
                    loadTableData(selectedTable);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // اختيار أول جدول تلقائياً (اختياري)
        if (!db_tables_box.getItems().isEmpty()) {
            db_tables_box.getSelectionModel().selectFirst();
            // أو يمكنك ترك المستخدم يختار بنفسه
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}  
    
    
    private void loadTableData(String tableName) throws ClassNotFoundException {
        table.getColumns().clear();
        table.getItems().clear();

        Class.forName("org.sqlite.JDBC");
    try {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:"+db_field.getText());
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);

            int columnCount = rs.getMetaData().getColumnCount();

            // Dynamically create columns
            for (int col = 1; col <= columnCount; col++) {
                final int columnIndex = col - 1;
                String columnName = rs.getMetaData().getColumnName(col);

                TableColumn<ObservableList<String>, String> tableColumn =
                        new TableColumn<>(columnName);

                tableColumn.setCellValueFactory(data ->
                        new SimpleStringProperty(data.getValue().get(columnIndex)));

                // Make cells editable
                tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
                tableColumn.setOnEditCommit(event -> {
                    String newValue = event.getNewValue();
                    ObservableList<String> row = event.getRowValue();
                    row.set(columnIndex, newValue);

                    // Update DB
                    //updateCell(tableName, row, columnName, newValue);
                });

                table.getColumns().add(tableColumn);
            }

            // Load data
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            table.setItems(data);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
   db_field.setText(getValueByKey("lib\\setto.cfg", "DataBase"));
   
   tf1.setText(getValueByKey("lib\\setto.cfg", "Sections"));
   tf2.setText(getValueByKey("lib\\setto.cfg", "Critical"));
   tf3.setText(getValueByKey("lib\\setto.cfg", "Logo"));
   tf4.setText(getValueByKey("lib\\setto.cfg", "Undo"));
   tf5.setText(getValueByKey("lib\\setto.cfg", "Font"));
   
        try {
            String fontPath = tf5.getText(); // غيّر المسار حسب مكان الخط عندك
            javafx.scene.text.Font cairoSemiBold = javafx.scene.text.Font.loadFont(new FileInputStream(fontPath), 15);
        } catch (FileNotFoundException ex) {
          
        }
        
        
        
    
        
        
    }    
    
}
