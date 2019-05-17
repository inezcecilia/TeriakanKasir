/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Menu;
import entity.Transaksi;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import util.NewHibernateUtil;

/**
 * FXML Controller class
 *
 * @author CICASMI;
 */
public class TransaksiController implements Initializable {

    @FXML
    private AnchorPane pane01;
    @FXML
    private TableView<Transaksi> transaksiTV;
    @FXML
    private Button tambahB;
    @FXML
    private Button editB;
    @FXML
    private Button hapusB;
    @FXML
    private Button kembaliB;
     @FXML
    private TextField tfid;
    @FXML
    private TextField tfharga;
    @FXML
    private TextField tfmenu;
    @FXML
    private TableColumn idview;
    @FXML
    private TableColumn menuview;
    @FXML
    private TableColumn hargaview;
   
    ObservableList<Transaksi> tableData = null;
    private static String QUERY = "from Transaksi";
    
     private Integer id1;
     private String menu1;
     private String harga1;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          tampil();
    }    
    
      public void tampil(){
        executeHQLQuery(QUERY);
    }  
    
        private void executeHQLQuery(String sql){
        try{
            Session session = NewHibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Transaksi");
            List resultList = q.list();
            displayList(resultList);
            //session.getTransaction().commit();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
        
         private void displayList(List ResultList){
        tableData = FXCollections.observableArrayList();
        for(Object o : ResultList){    
            Transaksi transaksi = (Transaksi)o ;
            //System.out.println(barang.getIdBarang());
            //tableData.add(new Barang(barang.getIdBarang(), barang.getNama(), barang.getHargaAwal(), barang.getDeskripsi(), barang.getIdBarang()));
            tableData.add(transaksi);
            //System.out.println(tableData);
        }
        
        //resultTable.setItems(tableData);
        idview.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuview.setCellValueFactory(new PropertyValueFactory<>("menu"));
        hargaview.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
  

        transaksiTV.getColumns().clear();
        transaksiTV.setItems(tableData);
        transaksiTV.getColumns().addAll(idview,menuview,hargaview); 
        
       transaksiTV.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                onClick();
            }
            
        });
    }
         
          public void onClick(){
        if(transaksiTV.getSelectionModel().getSelectedItem() != null){
           Transaksi transaksiSelect = transaksiTV.getSelectionModel().getSelectedItem();
           this.id1 = (transaksiSelect.getId());
           this.menu1 = transaksiSelect.getMenu();
           this.harga1 = transaksiSelect.getHarga();
           
        }
       
    }

    @FXML
    private void doTambah(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         String id = tfid.getText();
        String menu = tfmenu.getText();
        String harga = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();

            String sql = "INSERT INTO TRANSAKSI " + "VALUES ('"+id+"','"+menu+"','"+harga+"')"; 
            statement.executeUpdate(sql);
            System.out.println("Data Sudah Disimpan ke Database");

                statement.close();

                koneksi.close();
            }catch(SQLException e){

        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Tambahkan!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @FXML
    private void doHapus(ActionEvent event) {
          transaksiTV.getItems().removeAll(transaksiTV.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void doKembali(ActionEvent event) {
             try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/Home.fxml"));
            pane01.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

    @FXML
    private void doEdit(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
         String id = tfid.getText();
        String menu = tfmenu.getText();
        String harga = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();
            
            
            String sql="UPDATE TRANSAKSI SET MENU = '"+menu+"', HARGA= '"+harga+"' WHERE ID LIKE '"+id+"'";
            int executeUpdate = statement.executeUpdate(sql);
            statement.close();
            koneksi.close();
        }catch(SQLException e){
            
        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Update!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
