package controlador;


import conexion.dao.AeropuertoDaoImpl;
import conexion.dao.VueloDaoImpl;
import logica.Calculo;
import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.GraphPath;
import vista.VentanaDetalleVuelo;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Controlador {


    public GraphPath<Aeropuerto, Vuelo> MostrarRutaPorDuracion(String vueloTxtOrigen, String vueloTxtDestino) {
        Calculo calculo = new Calculo();
        return calculo.porDuracion(vueloTxtOrigen,vueloTxtDestino);
    }

    public GraphPath<Aeropuerto, Vuelo> MostraRutaPorPrecio(String vueloTxtOrigen, String vueloTxtDestino) {
        Calculo calculo = new Calculo();
        return calculo.porPrecio(vueloTxtOrigen,vueloTxtDestino);
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

    public String[] aeropuestosComboBox() {
        AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();
        int i = 0;
        String[] AeropuertosList = new String[50];

        for (Aeropuerto a : aeropuertoDao.obtenerTodos()) {
            AeropuertosList[i]= a.getCiudad() +","+ a.getCodigoAeropuerto();
            i++;
        }
        return AeropuertosList;

    }


    public void escribirDatos(GraphPath<Aeropuerto, Vuelo> mostrarRuta, String vueloTxtOrigen, String vueloTxtDestino, VentanaDetalleVuelo ventana) {
        int duracion=0;
        JTextField textFprecio= new JTextField();
         TextArea textfdetalles = new TextArea();
         JTextField textFtiempo=new JTextField();
         int precio=0;
        ArrayList<Vuelo> vuelos= new ArrayList<>();
        for (Vuelo vuelo  :mostrarRuta.getEdgeList()){
            vuelos.add(vuelo);
            textfdetalles.append(vuelo.toString() + '\n');
            precio += vuelo.getPrecio();


        }
        textFtiempo.setText(LocalTime.MIN.plus(Duration.ofMinutes(vuelos.get(0).getHoraSalida())).toString() + "-"
                + LocalTime.MIN.plus(Duration.ofMinutes(vuelos.get(vuelos.size()-1).getHoraLlegada())).toString());
        textFprecio.setText("$" + Double.toString(precio));
        duracion=vuelos.get(vuelos.size()-1).getHoraLlegada()-vuelos.get(0).getHoraSalida();

        ventana.setTextEscribir(mostrarRuta.getLength(),textFprecio,textfdetalles,textFtiempo,duracion,vueloTxtOrigen,vueloTxtDestino);
    }
}
