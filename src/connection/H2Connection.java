/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joni Nababan;
 */
public class H2Connection {
    private Connection conn;
    
    public Connection getConnection(){
        try {
            Class.forName("org.h2.Driver");
            
            conn = (Connection) DriverManager.getConnection("jdbc:h2:D:\\document\\NetBeansProjects\\Project sem 2\\POS\\database;AUTO_SERVER=TRUE", "", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(H2Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(H2Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
