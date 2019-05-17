/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Informasi;
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
public class InformasiController implements Initializable {

    @FXML
    private AnchorPane pane02;
    @FXML
    private TextField idtf;
    @FXML
    private TextField namatf;
    @FXML
    private TextField tipetf;
    @FXML
    private TextField kategoritf;
    @FXML
    private TextField kotatf;
    @FXML
    private TextField provinsitf;
    @FXML
    private TextField kodepostf;
    
    @FXML
    private Button simpan;
    @FXML
    private TableView<Informasi> tv2;
    @FXML
    private Button edit;
    @FXML
    private Button hapus;
    @FXML
    private Button back;
    @FXML
    private TableColumn namaview;
    @FXML
    private TableColumn tipeview;
    @FXML
    private TableColumn kategoriview;
    @FXML
    private TableColumn kotaview;
    @FXML
    private TableColumn provinsiview;
    @FXML
    private TableColumn kodeview;
    @FXML
    private TableColumn idview;
    @FXML
    private TableColumn ketegoriview;

    ObservableList<Informasi> tableData = null;
    private static String QUERY = "from Informasi";
     private Integer id1;
     private String nama1;
     private String tipebis1;
     private String kategori1;
     private String kota1;
     private String provinsi1;
     private String kodepos1;
   
  
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
            Query q = session.createQuery("from Informasi");
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
            Informasi informasi = (Informasi)o ;
            //System.out.println(barang.getIdBarang());
            //tableData.add(new Barang(barang.getIdBarang(), barang.getNama(), barang.getHargaAwal(), barang.getDeskripsi(), barang.getIdBarang()));
            tableData.add(informasi);
            //System.out.println(tableData);
        }
        
        //resultTable.setItems(tableData);
        idview.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaview.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        tipeview.setCellValueFactory(new PropertyValueFactory<>("tipe"));
        kategoriview.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        kotaview.setCellValueFactory(new PropertyValueFactory<>("kota"));
        provinsiview.setCellValueFactory(new PropertyValueFactory<>("provinsi"));
        kodeview.setCellValueFactory(new PropertyValueFactory<>("kodepos"));

        
  

        tv2.getColumns().clear();
        tv2.setItems(tableData);
        tv2.getColumns().addAll(idview,namaview,tipeview,kategoriview,kotaview,provinsiview,kodeview); 
        
       tv2.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                onClick();
            }
            
        });
    }
         
          public void onClick(){
        if(tv2.getSelectionModel().getSelectedItem() != null){
           Informasi menuSelect = tv2.getSelectionModel().getSelectedItem();
           this.id1 = (menuSelect.getId());
           this.nama1 = menuSelect.getNama();
           this.tipebis1 = menuSelect.getTipebis();
           this.kategori1 = menuSelect.getKategori();
           this.kota1 = menuSelect.getKota();
           this.provinsi1 = menuSelect.getProvinsi();
           this.kodepos1 = menuSelect.getKodepos();
           
        }
        
    }

    @FXML
    private void doSimpan(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String id = idtf.getText();
        String nama1 = namatf.getText();
        String tipe1 = tipetf.getText();
        String kategori1 = kategoritf.getText();
        String kota1 = kotatf.getText();
        String provinsi1 = provinsitf.getText();
        String kodepos1 = kodepostf.getText();
          
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();

            String sql = "INSERT INTO INFORMASI (id, nama, tipebis, kategori, kota, provinsi, kodepos) VALUES ('"+namatf+"','"+tipetf+"','"+kategoritf+"','"+kotatf+"','"+provinsitf+"','"+kodepostf+"')"; 
            statement.executeUpdate(sql);
            System.out.println("Data Sudah Disimpan ke Database");

                statement.close();

                koneksi.close();
            }catch(SQLException e){

        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Tambahkan!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void doEdit(ActionEvent event) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
       String id = idtf.getText();
        String nama = namatf.getText();
        String tipe = tipetf.getText();
        String kategori = kategoritf.getText();
        String kota = kotatf.getText();
        String provinsi = provinsitf.getText();
        String kodepos = kodepostf.getText();
          
        try{
            Class.forName("org.h2.Driver").newInstance();
            Connection koneksi = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE","","");
            Statement statement = (Statement) koneksi.createStatement();
            
            
            String sql="UPDATE INFORMASI SET NAMA = '"+nama+"', TIPE= '"+tipe+"',KATEGORI = '"+kategori+"',KOTA = '"+kota+"',PROVINSI = '"+provinsi+"',KODEPOS = '"+kodepos+"', WHERE ID LIKE '"+id+"'";
            int executeUpdate = statement.executeUpdate(sql);
            statement.close();
            koneksi.close();
        }catch(SQLException e){
            
        }JOptionPane.showMessageDialog(null,"Data Berhasil Di Update!","Title : Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @FXML
    private void doHapus(ActionEvent event) {
        tv2.getItems().removeAll(tv2.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void doBack(ActionEvent event) {
          try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/Home.fxml"));
            pane02.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }
}
