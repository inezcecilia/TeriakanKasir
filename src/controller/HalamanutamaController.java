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
public class HalamanutamaController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Button click;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doClick(ActionEvent event) {
         try{
            AnchorPane aps = FXMLLoader.load(getClass().getResource("/model/login.fxml"));
            pane.getChildren().setAll(aps);
        }catch(Exception e){     
        }
    }
    
}
