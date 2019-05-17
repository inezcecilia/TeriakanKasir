/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.H2Connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author CICASMI;
 */
public class LoginController implements Initializable {

    @FXML
    private Button login;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    
    H2Connection h2 = new H2Connection();
    @FXML
    private AnchorPane pane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doLogin(ActionEvent event) {
       String username = tfusername.getText();
        String password = tfpassword.getText();
        
     try {
            Connection conn = h2.getConnection();
            String sql = "SELECT * FROM USER WHERE USERNAME='" + username + "'";
           ResultSet rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                if ((username.equals(rs.getString("username"))) && (password.equals(rs.getString("password")))) {
                    if (rs.getInt("role") == 1) {
                        try {
                            AnchorPane pane = FXMLLoader.load(getClass().getResource("/model/Home.fxml"));
                            pane1.getChildren().setAll(pane);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "Invalid username or password", ButtonType.OK);
                    alert.setTitle("Invalid username or password");
                    alert.showAndWait();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }

