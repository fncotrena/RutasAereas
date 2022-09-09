package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static aplicacion.Constante.*;

public class DbManager {
    private  static Connection connection;
    private static DbManager connectInstance;

    private DbManager() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            System.out.println("OK ");
        }
    }

    public static DbManager getInstance(){
        if (connectInstance == null ){
            connectInstance = new DbManager();
        }
        return connectInstance;
    }


    //Implementación del patrón singleton para acceder a una instancia única de la clase

    public Connection getConnection(){
        return connection;
    }

}
