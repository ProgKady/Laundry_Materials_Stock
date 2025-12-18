
package com.kadysoft.kady;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.table.TableFilter;

/**
 * FXML Controller class
 *
 * @author ahmed.elkady
 */
public class Workers_MaterialsController implements Initializable {

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    
    
    public static int pboott,goggless,pflaskk,pbuckett,pcoveralll,gloves3mm,filter3mm,mask3mm,fabricglovess,uniformm;
    
    
    @FXML
    private JFXTextField date;

    @FXML
    private JFXTextField code;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXCheckBox pboot;

    @FXML
    private JFXCheckBox goggles;

    @FXML
    private JFXCheckBox pflask;

    @FXML
    private JFXCheckBox pbucket;

    @FXML
    private JFXCheckBox pcoverall;

    @FXML
    private JFXCheckBox gloves3m;

    @FXML
    private JFXCheckBox filter3m;

    @FXML
    private JFXCheckBox mask3m,uniform;

    @FXML
    private JFXButton add;

    @FXML
    private JFXCheckBox fabricgloves;

    @FXML
    private TableView<String> table;

    
    
    @FXML
    void uniformaction(ActionEvent event) {
        
          if (uniform.isSelected()==true) {
            
            uniformm=1;
            
        }
        
        else {
            
            uniformm=0;
            
        }
        
    }
    
    
    
    @FXML
    void addaction(ActionEvent event) {

          try {
            
      String newitemdatee,newitemcodee,newitemmaterialnamee,statuss;
      
      newitemdatee=date.getText();
      newitemcodee=code.getText();
      newitemmaterialnamee=name.getText();
      
      ///////////////////////////////////////////////////////////////////////////////
     
      ///////////////////////////////////////////////////////////////////////////////
      
            String reg = "insert into Workers_Materials (Date,Code,Name,Plastic_Boot,Safety_Goggles,Flask_2L,Plastic_Bucket,Plastic_Coverall,Gloves_3M,Filter_3M,Mask_3M,Fabric_Gloves,Uniform) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(reg);
            
            pst.setString(1,newitemdatee);
            pst.setString(2,newitemcodee);
            pst.setString(3,newitemmaterialnamee);
            
            pst.setString(4,Integer.toString(pboott));
            pst.setString(5,Integer.toString(goggless));
            pst.setString(6,Integer.toString(pflaskk));
            pst.setString(7,Integer.toString(pbuckett));
            pst.setString(8,Integer.toString(pcoveralll));
            pst.setString(9,Integer.toString(gloves3mm));
            pst.setString(10,Integer.toString(filter3mm));
            pst.setString(11,Integer.toString(mask3mm));
            pst.setString(12,Integer.toString(fabricglovess));
            pst.setString(13,Integer.toString(uniformm));
            
            pst.execute();
           
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
          
          
          
      Notifications noti = Notifications.create();
      noti.title("Successful!");
      noti.text("Operation Successfully Added.");
      noti.position(Pos.CENTER);
      noti.showInformation();
      
      code.clear();
      name.clear();
        
         table.getItems().clear();
         ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Workers_Materials";
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
      //      JOptionPane.showMessageDialog(null, e);
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
        
          TableFilter filter = new TableFilter(table);

          
        
    }
    
    
    
    
    

    @FXML
    void fabricglovesaction(ActionEvent event) {

        if (fabricgloves.isSelected()==true) {
            
            fabricglovess=1;
            
        }
        
        else {
            
            fabricglovess=0;
            
        }
        
    }

    @FXML
    void filter3maction(ActionEvent event) {

        if (filter3m.isSelected()==true) {
            
            filter3mm=1;
            
        }
        
        else {
            
            filter3mm=0;
            
        }
        
    }

    @FXML
    void gloves3maction(ActionEvent event) {

         if (gloves3m.isSelected()==true) {
            
            gloves3mm=1;
            
        }
        
        else {
            
            gloves3mm=0;
            
        }
        
    }

    @FXML
    void gogglesaction(ActionEvent event) {

         if (goggles.isSelected()==true) {
            
            goggless=1;
            
        }
        
        else {
            
            goggless=0;
            
        }
        
    }

    @FXML
    void mask3maction(ActionEvent event) {

        if (mask3m.isSelected()==true) {
            
            mask3mm=1;
            
        }
        
        else {
            
            mask3mm=0;
            
        }
        
        
    }

    @FXML
    void pbootaction(ActionEvent event) {

         if (pboot.isSelected()==true) {
            
            pboott=1;
            
        }
        
        else {
            
            pboott=0;
            
        }
        
        
    }

    @FXML
    void pbucketaction(ActionEvent event) {

          if (pbucket.isSelected()==true) {
            
            pbuckett=1;
            
        }
        
        else {
            
            pbuckett=0;
            
        }
        
    }

    @FXML
    void pcoverallaction(ActionEvent event) {

          if (pcoverall.isSelected()==true) {
            
            pcoveralll=1;
            
        }
        
        else {
            
            pcoveralll=0;
            
        }
        
    }

    @FXML
    void pflaskaction(ActionEvent event) {

          if (pflask.isSelected()==true) {
            
            pflaskk=1;
            
        }
        
        else {
            
            pflaskk=0;
            
        }
        
    }
    
    
    
    
    
    @FXML
    void inputcode(KeyEvent event) {
        
        
        if (event.getCode()==KeyCode.RIGHT) {
            
            name.requestFocus();
            
        }
        
        else {
            
              if (code.getText().isEmpty()==true) {
            
    name.clear();
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    date.setText(value1);
            
        }
        
        else {
            
            
              try {
          
      String sql = "select * from Workers_Materials where Code=?";
      this.pst = this.conn.prepareStatement(sql);
      this.pst.setString(1, this.code.getText());
      this.rs = this.pst.executeQuery();
      String add1 = this.rs.getString("Name");
      this.name.setText(add1);
      String add2 = this.rs.getString("Date");
      //this.date.setText(add2);
    
    }
        
        catch (Exception exception) {
    } 
        finally {
      try {
        this.rs.close();
        this.pst.close();
      
      } catch (Exception exception) {}
    } 
            
        }
            
        }

        
      
        
       
        
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
         conn=che_db.java_che_db();
        
         
         table.getItems().clear();
        
         ////////////////////////////////////////////////////////////////////
        ObservableList <ObservableList> data;
        data=FXCollections.observableArrayList();
        
        ////////////////////////////////////////////////////////////////////
        
        
         try{
            
            String sql ="select * from Workers_Materials";
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
      //      JOptionPane.showMessageDialog(null, e);
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
        
          TableFilter filter = new TableFilter(table);

          
    Date currentDate = GregorianCalendar.getInstance().getTime();
    DateFormat df = DateFormat.getDateInstance();
    String dateString = df.format(currentDate);
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String timeString = sdf.format(d);
    String value1 = timeString;
    date.setText(value1);
         
         
        
    }    
    
}
