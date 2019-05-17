/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Menu;
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
public class RestoranController implements Initializable {


    @FXML
    private TextField tfid;
    @FXML
    private TextField tfkategori;
    @FXML
    private TextField tfharga;
    @FXML
    private Button update;
    @FXML
    private Button save;
    @FXML
    private Button delete;
    @FXML
    private TableView<Menu> tv;
    @FXML
    private TableColumn idview;
    @FXML
    private TableColumn kategoriview;
    @FXML
    private TableColumn jenisvew;
    @FXML
    private TableColumn hargaview;
    @FXML
    private Button back;
    @FXML
    private TextField tfjenis;
    
    @FXML
    private AnchorPane pane60;
    
        ObservableList<Menu> tableData = null;
    private static String QUERY = "from Menu";
    
         private Integer id1;
     private String kategori1;
     private String jenis1;
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
            Query q = session.createQuery("from Menu");
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
            Menu menu = (Menu)o ;
            //System.out.println(barang.getIdBarang());
            //tableData.add(new Barang(barang.getIdBarang(), barang.getNama(), barang.getHargaAwal(), barang.getDeskripsi(), barang.getIdBarang()));
            tableData.add(menu);
            //System.out.println(tableData);
        }
        
        //resultTable.setItems(tableData);
        idview.setCellValueFactory(new PropertyValueFactory<>("id"));
        kategoriview.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        jenisvew.setCellValueFactory(new PropertyValueFactory<>("jenis"));
        hargaview.setCellValueFactory(new PropertyValueFactory<>("harga"));

        
  

        tv.getColumns().clear();
        tv.setItems(tableData);
        tv.getColumns().addAll(idview,kategoriview,jenisvew,hargaview); 
        
       tv.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                onClick();
            }
            
        });
    }
         
          public void onClick(){
        if(tv.getSelectionModel().getSelectedItem() != null){
           Menu menuSelect = tv.getSelectionModel().getSelectedItem();
           this.id1 = (menuSelect.getId());
           this.kategori1 = menuSelect.getKategori();
           this.jenis1 = menuSelect.getJenis();
           this.harga1 = menuSelect.getHarga();
           
        }
        
       
    }


    @FXML
    private void doUpdate(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       String id = tfid.getText();
        String kategori = tfkategori.getText();
        String jenis = tfjenis.getText();
        String harga = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();
            
            
            String sql="UPDATE MENU SET KATEGORI = '"+kategori+"',JENIS= '"+jenis+"', HARGA= '"+harga+"' WHERE ID LIKE '"+id+"'";
            int executeUpdate = statement.executeUpdate(sql);
            statement.close();
            koneksi.close();
        }catch(SQLException e){
            
        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Update!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
            
    
    }
    @FXML
    private void doSave(ActionEvent event)throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String id = tfid.getText();
        String kategori = tfkategori.getText();
        String jenis = tfjenis.getText();
        String harga = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();

            String sql = "INSERT INTO MENU " + "VALUES ('"+id+"','"+kategori+"','"+jenis+"','"+harga+"')"; 
            statement.executeUpdate(sql);
            System.out.println("Data Sudah Disimpan ke Database");

                statement.close();

                koneksi.close();
            }catch(SQLException e){

        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Tambahkan!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
        
    }

    @FXML
    private void doDelete(ActionEvent event) {
        tv.getItems().removeAll(tv.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void doBack(ActionEvent event) {
          try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/Home.fxml"));
            pane60.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }
    
}
