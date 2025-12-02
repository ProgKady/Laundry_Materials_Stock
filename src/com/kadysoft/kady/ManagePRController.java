
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import static com.kadysoft.kady.db.drib;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class ManagePRController implements Initializable {

   Connection conn = null;
  
    ResultSet rs = null;
  
    PreparedStatement pst = null;
    
     @FXML
    private JFXButton requestbtn;

    @FXML
    private JFXButton responsebtn;

    @FXML
    private Pane requestpanel;

    @FXML
    private JFXTextField pathtofile;

    @FXML
    private JFXButton browsebtn;

    @FXML
    private TableView<ItemData> table1;

    @FXML
    private JFXButton savetodb;

    @FXML
    private Pane responsepanel;

    //@FXML
    //private TableView<?> table2;

    @FXML
    private JFXButton refreshbtn;

    @FXML
    private JFXButton updatebtn;

    @FXML
    private JFXButton filterbtn;
    
    @FXML
    private TableView<ObservableList<String>> table2;
    
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
    //static String databaseUrl; // Path to your SQLite DB
    private ObservableList<String> columnNames = FXCollections.observableArrayList();

 
    

private String getCellValue(Cell cell) {
    if (cell == null) return "";

    switch (cell.getCellTypeEnum()) { // Correct method for POI 3.17
        case STRING:
            return cell.getStringCellValue();
        case NUMERIC:
            return String.valueOf(cell.getNumericCellValue());
        case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
        default:
            return "";
    }
}





public static class ItemData {
    private final SimpleStringProperty item;
    private final SimpleStringProperty description;
    private final SimpleStringProperty unit;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty comming;

    public ItemData(String item, String description, String unit, String quantity, String comming) {
        this.item = new SimpleStringProperty(item);
        this.description = new SimpleStringProperty(description);
        this.unit = new SimpleStringProperty(unit);
        this.quantity = new SimpleStringProperty(quantity);
        this.comming = new SimpleStringProperty(comming);
    }

    // Getters to fetch actual values
    public String getItem() { return item.get(); }
    public String getDescription() { return description.get(); }
    public String getUnit() { return unit.get(); }
    public String getQuantity() { return quantity.get(); }
    public String getComming() { return comming.get(); }

    // Property methods for TableView bindings
    public SimpleStringProperty itemProperty() { return item; }
    public SimpleStringProperty descriptionProperty() { return description; }
    public SimpleStringProperty unitProperty() { return unit; }
    public SimpleStringProperty quantityProperty() { return quantity; }
    public SimpleStringProperty commingProperty() { return comming; }
}

    
    
    @FXML
    void browsebtnaction(ActionEvent event) throws IOException, InvalidFormatException {

        
        FileChooser fcho=new FileChooser ();
        fcho.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xls", "*.xlsx"));
        fcho.setTitle("Choose Excel File");
        File file=fcho.showOpenDialog(null);
        String path=file.getAbsolutePath().toString();
        pathtofile.setText(path);
        
        if (file != null) {
            ObservableList<ItemData> data = FXCollections.observableArrayList();
            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = WorkbookFactory.create(fis)) {

                Sheet sheet = workbook.getSheetAt(0); // Get first sheet

                for (int i = 4; i < 19; i++) { // Rows (5-19, zero-based index)
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        String item = getCellValue(row.getCell(1)); // Column B
                        String description = getCellValue(row.getCell(2)); // Column C
                        String unit = getCellValue(row.getCell(3)); // Column D
                        String quantity = getCellValue(row.getCell(4)); // Column E
                        String comming = "0";

                        data.add(new ItemData(item, description, unit, quantity, comming));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            table1.setItems(data);
            
        }
        
        
        TableColumn<ItemData, String> colItem = new TableColumn<>("Item");
        colItem.setCellValueFactory(data -> data.getValue().itemProperty());

        TableColumn<ItemData, String> colDescription = new TableColumn<>("Description");
        colDescription.setCellValueFactory(data -> data.getValue().descriptionProperty());

        TableColumn<ItemData, String> colUnit = new TableColumn<>("Unit");
        colUnit.setCellValueFactory(data -> data.getValue().unitProperty());

        TableColumn<ItemData, String> colQuantity = new TableColumn<>("Quantity");
        colQuantity.setCellValueFactory(data -> data.getValue().quantityProperty());

        TableColumn<ItemData, String> colComingQty = new TableColumn<>("Comming Quantities");
        
        table1.getColumns().clear();
        
        table1.getColumns().addAll(colItem, colDescription, colUnit, colQuantity, colComingQty);
        
        
        
        
        
    }

    
    
    
    
    @FXML
    void filterbtnaction(ActionEvent event) {

         table2.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
       
         try{
            
            String sql ="select * from Purchasing";
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
            table2.getColumns().addAll(col);
            
        }
        
        //While getting info
        
        while (rs.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
            
        }
        
        table2.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            
            try{
                
                rs.close();
                pst.close();

            }
            catch(Exception e){
                
            }
         } 
         
        // getauditbtn.setDisable(true);
        
          TableFilter filter = new TableFilter(table2);
        
        
        
        
    }

    
    
    
    
    @FXML
    void refreshbtn(ActionEvent event) {

        
        
        table2.getColumns().clear();
        
        ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
       
         try{
            
            String sql ="select * from Purchasing";
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
            table2.getColumns().addAll(col);
            
        }
        
        //While getting info
        
        while (rs.next()) {
            ObservableList<String> row=FXCollections.observableArrayList();
            for (int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
            
        }
        
        table2.setItems((ObservableList)data);
          
       ////////////////////////////////////////////////////////////////
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        finally {
            
            try{
                
                rs.close();
                pst.close();

            }
            catch(Exception e){
                
            }
         } 
         
        // getauditbtn.setDisable(true);
        
          TableFilter filter = new TableFilter(table2);
          editt();
          
        
        
    }

    
    
    
    @FXML
    void requestbtnaction(ActionEvent event) {

        
        requestpanel.setVisible(true);
        responsepanel.setVisible(false);
        
    }

    
    
    
    @FXML
    void responsebtnaction(ActionEvent event) {

        
        requestpanel.setVisible(false);
        responsepanel.setVisible(true);
        
        
    }

    
    
    
    @FXML
    void savetodbaction(ActionEvent event) {

        
       String sql = "INSERT INTO Purchasing (Material, Description, Unit, Quantity, Coming_Quantities) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            ObservableList<ItemData> itemList = table1.getItems(); // Get all rows from TableView
            boolean dataInserted = false; // Flag to check if any data is saved

            for (ItemData item : itemList) {
                // Skip row if it's fully empty OR if all fields except "Coming Quantities" are empty AND "Coming Quantities" = 0
                if (shouldSkipRow(item)) {
                    continue; // Skip this row
                }

                pst.setString(1, item.getItem()); // Material
                pst.setString(2, item.getDescription()); // Description
                pst.setString(3, item.getUnit()); // Unit
                pst.setString(4, item.getQuantity()); // Quantity
                pst.setString(5, item.getComming()); // Coming Quantities

                pst.addBatch(); // Add each row to batch
                dataInserted = true; // Mark that data was inserted
            }

            if (dataInserted) {
                pst.executeBatch(); // Execute batch insertion if valid data exists
                Notifications.create()
                        .title("Successful")
                        .text("Filtered data saved successfully into 'Purchasing'!")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.CENTER)
                        .showInformation();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        
        
    }
    
   // Method to check if a row should be skipped
    private static boolean shouldSkipRow(ItemData item) {
        boolean materialEmpty = item.getItem().trim().isEmpty();
        boolean descriptionEmpty = item.getDescription().trim().isEmpty();
        boolean unitEmpty = item.getUnit().trim().isEmpty();
        boolean quantityEmpty = item.getQuantity().trim().isEmpty();
        boolean comingQuantitiesEmpty = item.getComming().trim().isEmpty();

        // Check if all fields are empty
        boolean allFieldsEmpty = materialEmpty && descriptionEmpty && unitEmpty && quantityEmpty && comingQuantitiesEmpty;

        // Check if all fields except "Coming Quantities" are empty, and "Coming Quantities" = 0
        boolean onlyComingQuantitiesExistsAndZero = materialEmpty && descriptionEmpty && unitEmpty && quantityEmpty &&
                                                    (!comingQuantitiesEmpty && item.getComming().trim().equals("0"));

        return allFieldsEmpty || onlyComingQuantitiesExistsAndZero; // Skip row if either condition is true
    }
    
    
    
    
    @FXML
    void updatebtnaction(ActionEvent event) {

         try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            // Connect to the database
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Database\\LaundryMaterialStock.db");
                 Statement stmt = conn.createStatement()) {

                conn.setAutoCommit(false); // Disable auto-commit for batch updates
                stmt.executeUpdate("DELETE FROM Purchasing"); // Clear existing data

                // Reinsert updated data
                for (ObservableList<String> row : data) {
                    StringBuilder values = new StringBuilder();
                    for (String cell : row) {
                        values.append("'").append(cell.replace("'", "''")).append("',");
                    }
                    values.setLength(values.length() - 1); // Remove trailing comma
                    String insertQuery = String.format("INSERT INTO Purchasing (%s) VALUES (%s)",
                            String.join(", ", columnNames), values.toString());
                    stmt.executeUpdate(insertQuery);
                }

                conn.commit(); // Commit changes
               // showAlert("Success", "Data saved successfully!");
               Notifications noti = Notifications.create();
    noti.title("Successful");
    noti.text("Successful Update");
    noti.hideAfter(Duration.seconds(3));
    noti.position(Pos.CENTER);
    noti.showInformation();
    
    refreshbtn.fire();
    

            }
        } catch (ClassNotFoundException e) {
         //   showAlert("Error", "SQLite JDBC driver not found.");
        } catch (SQLException e) {
          //  showAlert("Error", "Failed to save data to the database: " + e.getMessage());
              Notifications noti = Notifications.create();
              noti.title("Unsuccessful");
              noti.text("Unsuccessful update");
              noti.hideAfter(Duration.seconds(3));
              noti.position(Pos.CENTER);
              noti.showError();
              
              refreshbtn.fire();
        }
        
        
    }
    
    
    
    
    
    public void editt()   {
        
        
        
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            // Connect to the database
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:"+drib+":\\KADINIO\\DATABASES\\Material\\Stock\\Database\\LaundryMaterialStock.db");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Purchasing")) {

                // Clear previous data
                table2.getColumns().clear();
                data.clear();
                columnNames.clear();

                // Define the columns that should use ComboBox (example: column index 1)
                //Set<Integer> comboBoxColumns = Set.of(1); // Adjust as needed
                
                Set<Integer> comboBoxColumns = new HashSet<>();
                comboBoxColumns.add(1); // Add column index 1 as a ComboBox


                // Get column names
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int colIndex = i;
                    String colName = rs.getMetaData().getColumnName(i + 1);
                    columnNames.add(colName);

                    TableColumn<ObservableList<String>, String> column = new TableColumn<>(colName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));

                    // Check if the column should use a ComboBox
                    if (comboBoxColumns.contains(colIndex)) {
                        
                        column.setCellFactory(TextFieldTableCell.forTableColumn());
                       // column.setCellFactory(col -> new ComboBoxTableCell<>(
                           //     FXCollections.observableArrayList("Option 1", "Option 2", "Option 3")));
                    } else {
                        column.setCellFactory(TextFieldTableCell.forTableColumn());
                    }

                    column.setOnEditCommit(eventt -> {
                        ObservableList<String> row = eventt.getRowValue();
                        row.set(colIndex, eventt.getNewValue());
                    });

                    column.setContextMenu(createColumnContextMenu(colIndex));
                    table2.getColumns().add(column);
                }

                // Get data rows
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                table2.setItems(data);
                table2.setEditable(true);

                // Add row context menu
                table2.setRowFactory(tv -> {
                    TableRow<ObservableList<String>> row = new TableRow<>();
                    row.setContextMenu(createRowContextMenu(row));
                    return row;
                });

            }
        } catch (ClassNotFoundException e) {
          //  showAlert("Error", "SQLite JDBC driver not found.");
        } catch (SQLException e) {
          //  showAlert("Error", "Failed to load data from the database: " + e.getMessage());
        }
        
        
    }
    
    
    
        
     private ContextMenu createColumnContextMenu(int colIndex) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem changeColor = new MenuItem("Change Column Color");

        changeColor.setOnAction(e -> {
            ObservableList<String> selectedRow = table2.getSelectionModel().getSelectedItem();
            if (selectedRow != null) {
                JFXColorPicker colorPicker = new JFXColorPicker(Color.WHITE);
                Dialog<Void> dialog = new Dialog<>();
                dialog.setTitle("Select Column Color");
                dialog.getDialogPane().setContent(colorPicker);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
                dialog.showAndWait();

                Color selectedColor = colorPicker.getValue();
                String colorStyle = "-fx-background-color: " + toRgbCode(selectedColor) + ";";
                TableColumn<ObservableList<String>, String> column = (TableColumn<ObservableList<String>, String>) table2.getColumns().get(colIndex);
                column.setStyle(colorStyle);
            }
        });

        contextMenu.getItems().add(changeColor);
        return contextMenu;
    }

    
      private ContextMenu createRowContextMenu(TableRow<ObservableList<String>> row) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem changeRowColor = new MenuItem("Change Row Color");
        
        changeRowColor.setOnAction(e -> {
            JFXColorPicker colorPicker = new JFXColorPicker(Color.WHITE);
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Select Row Color");
            dialog.getDialogPane().setContent(colorPicker);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.showAndWait();

            Color selectedColor = colorPicker.getValue();
            String colorStyle = "-fx-background-color: " + toRgbCode(selectedColor) + ";";
            row.setStyle(colorStyle);
        });
    
    
     contextMenu.getItems().addAll(changeRowColor);
        return contextMenu;
    }

    private String toRgbCode(Color color) {
        return String.format("rgb(%d, %d, %d)",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
    
    
     private void addRow() {
        ObservableList<String> newRow = FXCollections.observableArrayList();
        for (int i = 0; i < columnNames.size(); i++) {
            newRow.add(""); // Add empty values for each column
        }
        data.add(newRow);
    }

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        conn = db.java_db();
        refreshbtn.fire();
        editt();
    }    
    
}
