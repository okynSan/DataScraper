/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.anza.ukrsib.confige.jdbcconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.anza.ukrsib.confige.prop.ProjectProperties;

/**
 *
 * @author andrey_zatvornitskiy
 */
public class MySqlConnection {
    
    static Connection connection = null;
    
    static {
        try {
            Class.forName(ProjectProperties.getPropertyByKey("jdbc_driver")).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MySqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        if (MySqlConnection.connection == null) {
            connection = DriverManager.getConnection(
                    ProjectProperties.getPropertyByKey("databese_url"),
                    ProjectProperties.getPropertyByKey("user"),
                    ProjectProperties.getPropertyByKey("password")
            );
            
            connection.setAutoCommit(false);
        }
        return connection;
    }
    
}
