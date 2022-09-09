package modelo;

import conexion.DbManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Vuelo {

    private String nroVuelo;
    private String nombreAerolinea;
    private int horaSalida;
    private int horaLlegada;
    private int precio;

    public Vuelo(String nroVuelo, String nombreAerolinea, int horaSalida, int horaLlegada, int precio, int escala) {
        this.nroVuelo = nroVuelo;
        this.nombreAerolinea = nombreAerolinea;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.precio = precio;
    }

    public Vuelo() {

    }

    public String getNroVuelo() {
        return nroVuelo;
    }

    public void setNroVuelo(String nroVuelo) {
        this.nroVuelo = nroVuelo;
    }

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "nroVuelo='" + nroVuelo + '\'' +
                ", NombreAerolinea='" + nombreAerolinea + '\'' +
                ", horaSalida=" + horaSalida +
                ", horaLlegada=" + horaLlegada +
                ", precio=" + precio +
                '}';
    }


}