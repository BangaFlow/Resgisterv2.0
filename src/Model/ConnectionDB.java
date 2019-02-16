package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Saidi Khaled
 */
public class ConnectionDB {
    /// Attributes
    final String url="jdbc:mysql://localhost:3306/trap?serverTimezone=UTC";
    final String login="root";
    final String mdp="";
    Connection connexion;
    static ConnectionDB instance;

    /// Private Constructor
    private ConnectionDB() {
    try{
        connexion=DriverManager.getConnection(url, login, mdp);
        System.out.println("Connected!");
    } catch (SQLException ex) {
        Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
    }

    }
    ///getters
    static public ConnectionDB getInstance() {
        if (instance==null)
            instance= new ConnectionDB();
        return instance;
    }


    public Connection getConnection() {
        return connexion;
    }
}
