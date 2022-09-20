package conexion;

import datos.CargaDeDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static aplicacion.Constante.*;

public class DbManager {
    private  static Connection connection;
    private static DbManager connectInstance;
    private CargaDeDatos cargaDeDatos = new CargaDeDatos();


    private DbManager() {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/jdbc.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String pass = prop.getProperty("pass");
            String user = prop.getProperty("user");


            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conectado!");
        } catch (SQLException ex) {
            Logger.getLogger(DbManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
