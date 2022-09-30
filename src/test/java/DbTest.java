package test.java;

import conexion.dao.Dao;
import conexion.factory.DaoFactory;


public class DbTest {
    public static void main(String[] args) {
       Dao vuelos = DaoFactory.getDao(DaoFactory.DaoType.valueOf("VUELO"));
       Dao aeropuertos = DaoFactory.getDao(DaoFactory.DaoType.valueOf("AEROPUERTO"));

       System.out.println(vuelos.obtenerTodos());
       System.out.println(aeropuertos.obtenerTodos().toString());

    }
}
