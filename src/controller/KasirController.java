/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Kasir;
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
public class KasirController implements Initializable {

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
    private TableView<Kasir> tv;
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
    private TextField tfid;
    @FXML
    private AnchorPane pane55;
    
     ObservableList<Kasir> tableData = null;
    private static String QUERY = "from Kasir";
    
    
     private Integer id1;
     private String nama1;
     private String hp1;
     private String alamat1;

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
            Query q = session.createQuery("from Kasir");
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
            Kasir kasir = (Kasir)o ;
            //System.out.println(barang.getIdBarang());
            //tableData.add(new Barang(barang.getIdBarang(), barang.getNama(), barang.getHargaAwal(), barang.getDeskripsi(), barang.getIdBarang()));
            tableData.add(kasir);
            //System.out.println(tableData);
        }
        
        //resultTable.setItems(tableData);
        idview.setCellValueFactory(new PropertyValueFactory<>("id"));
        kategoriview.setCellValueFactory(new PropertyValueFactory<>("nama"));
        jenisvew.setCellValueFactory(new PropertyValueFactory<>("hp"));
        hargaview.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        
  

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
           Kasir kasirSelect = tv.getSelectionModel().getSelectedItem();
           this.id1 = (kasirSelect.getId());
           this.nama1 = kasirSelect.getNama();
           this.hp1 = kasirSelect.getHp();
           this.alamat1 = kasirSelect.getAlamat();
           
        }
        
       
    }

    @FXML
    private void doUpdate(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       String id = tfid.getText();
        String nama = tfkategori.getText();
        String hp = tfjenis.getText();
        String alamat = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();
            
            
            String sql="UPDATE KASIR SET NAMA = '"+nama+"',HP= '"+hp+"', ALAMAT= '"+alamat+"' WHERE ID LIKE '"+id+"'";
            int executeUpdate = statement.executeUpdate(sql);
            statement.close();
            koneksi.close();
        }catch(SQLException e){
            
        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Update!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
            
    
    }

    @FXML
    private void doSave(ActionEvent event)throws ClassNotFoundException, InstantiationException, IllegalAccessException {
          String id = tfid.getText();
        String nama = tfkategori.getText();
        String hp = tfjenis.getText();
        String alamat = tfharga.getText();
        
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();

            String sql = "INSERT INTO KASIR " + "VALUES ('"+id+"','"+nama+"','"+hp+"','"+alamat+"')"; 
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
            pane55.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }
    
}
