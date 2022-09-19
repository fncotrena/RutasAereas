package controlador;


import conexion.dao.AeropuertoDaoImpl;
import conexion.dao.VueloDaoImpl;
import logica.CalculoRutaMasCorta;
import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.GraphPath;

import javax.swing.*;

public class Controlador {


    public GraphPath<Aeropuerto, Vuelo> MostrarRutaPorDuracion(String vueloTxtOrigen, String vueloTxtDestino) {
        CalculoRutaMasCorta calculoRutaMasCorta = new CalculoRutaMasCorta();
        return calculoRutaMasCorta.porDuracion(vueloTxtOrigen,vueloTxtDestino);
    }

    public GraphPath<Aeropuerto, Vuelo> MostraRutaPorPrecio(String vueloTxtOrigen, String vueloTxtDestino) {
        CalculoRutaMasCorta calculoRutaMasCorta = new CalculoRutaMasCorta();
        return calculoRutaMasCorta.porPrecio(vueloTxtOrigen,vueloTxtDestino);
    }

    public void modificarAeropuerto(String text, String text1, String text2) {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        Aeropuerto aero = new Aeropuerto(text, text1, text2);
        aeropuertoDao.modificar(aero);
    }

    public void mostrarAeropuertos(JTable jTable2) {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        aeropuertoDao.mostrarAeropuertos(jTable2);
    }

    public void insertar(String text, String text1, String text2) {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        Aeropuerto aeropuerto = new Aeropuerto(text, text1,text2);
        aeropuertoDao.insertar(aeropuerto);
    }

    public void eliminar(String text) {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setCodigoAeropuerto(text);
        aeropuertoDao.eliminar(aeropuerto);
    }

    public void seleccionarAeropuerto(JTable jTable2, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3) {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        aeropuertoDao.seleccionarAeropuerto(jTable2, jTextField1, jTextField2, jTextField3);
    }


    public void mostrarVuelos(JTable jTable2) {
        VueloDaoImpl vueloDao = new VueloDaoImpl();
        vueloDao.mostrarVuelos(jTable2);
    }

    public void insertarVuelo(JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, JTextField jTextField8) {
        VueloDaoImpl vueloDao = new VueloDaoImpl();
        vueloDao.insertarVuelo(jTextField2,jTextField3,jTextField4,jTextField5,jTextField6,jTextField7,jTextField8);
    }


    public void eliminarVuelo(JTextField jTextField4) {
        VueloDaoImpl vueloDao = new VueloDaoImpl();
        vueloDao.elimnarVuelo(jTextField4);

    }

    public void modificarVuelo(JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, JTextField jTextField8) {
        VueloDaoImpl vueloDao = new VueloDaoImpl();
        vueloDao.modificarVuelo(jTextField2, jTextField3,jTextField4,jTextField5,jTextField6,jTextField7,jTextField8);
    }

    public void seleccionarVuelo(JTable jTable2, JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4, JTextField jTextField5, JTextField jTextField6, JTextField jTextField7, JTextField jTextField8) {
        VueloDaoImpl vueloDao = new VueloDaoImpl();
        vueloDao.seleccionarVuelo(jTable2, jTextField1, jTextField2, jTextField3,jTextField4,jTextField5,jTextField6,jTextField7,jTextField8);


    }
}
