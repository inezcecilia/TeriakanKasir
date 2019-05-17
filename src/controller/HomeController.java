/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author CICASMI;
 */
public class HomeController implements Initializable {

    @FXML
    private Button menu;
    @FXML
    private Button jenis;
    @FXML
    private Button kasir;
    @FXML
    private Button transaksi;
    @FXML
    private Button diskon;
    @FXML
    private AnchorPane pane;
    @FXML
    private Button informasi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doMenu(ActionEvent event) {
         try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/restoran.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }
    

    @FXML
    private void doJenis(ActionEvent event) {
          try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/jenis.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

    @FXML
    private void doKasir(ActionEvent event) {
        try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/kasir.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

    @FXML
    private void doTransaksi(ActionEvent event) {
         try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/transaksi.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

    @FXML
    private void doDiskon(ActionEvent event) {
         try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/login.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

    @FXML
    private void doInformasi(ActionEvent event) {
          try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/informasi.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }

}
