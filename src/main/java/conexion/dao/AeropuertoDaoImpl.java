package conexion.dao;

import conexion.DbManager;
import modelo.Aeropuerto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AeropuertoDaoImpl implements Dao<Aeropuerto> {
    private final Connection connection;
    //Declaración de variable para insertar valores a consultas
    private PreparedStatement preQuery;
    //Devuelve true si se ejecutó correctamente y false si algo falló
    private final boolean isSuccesfully = false;
    //Objeto que servirá para almacenar información al buscar un registro
    private Aeropuerto aeropuerto;
    //Lista de registros
    //Lista de registros
    private final List<Aeropuerto> aeropuertos;
    private final String[] QUERIES = {
            "INSERT INTO aeropuerto (codigo, ciudad, nombre) VALUES ( ?, ? , ? )",
            "SELECT * FROM aeropuerto WHERE id = ?;",
            "UPDATE aeropuerto SET codigo = ? , ciudad =? , nombre = ? WHERE (codigo = ?);",
            "DELETE FROM aeropuerto WHERE (codigo = ?);",
            "SELECT * FROM aeropuerto;",
            "SELECT * FROM aeropuerto WHERE (codigo = ?);",
    };

    public AeropuertoDaoImpl() {
        connection = DbManager.getInstance().getConnection();
        aeropuerto = new Aeropuerto();
        aeropuertos = new ArrayList<>();
    }

    @Override
    public void insertar(Aeropuerto k) {
        try {
            preQuery = connection.prepareStatement(QUERIES[0]);
            preQuery.setString(1, k.getCodigoAeropuerto());
            preQuery.setString(2, k.getCiudad());
            preQuery.setString(3, k.getNombre());
            preQuery.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se inserto correctamente");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+ e);
        }


    }


    @Override
    public void modificar(Aeropuerto k) {

            try {
                preQuery = connection.prepareStatement(QUERIES[2]);
                preQuery.setString(1, k.getCodigoAeropuerto());
                preQuery.setString(2, k.getCiudad());
                preQuery.setString(3, k.getNombre());
                preQuery.setString(4, k.getCodigoAeropuerto());
                preQuery.executeUpdate();
                JOptionPane.showMessageDialog(null,"Se modifico correctamente");


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error:"+ e);
            }
    }


    @Override
    public void eliminar(Aeropuerto k) {
        try {
            preQuery = this.connection.prepareStatement(QUERIES[3]);
            preQuery.setString(1, k.getCodigoAeropuerto());
            ResultSet rowsDeleted = preQuery.executeQuery();
            while (rowsDeleted.next()) {
                System.out.printf(" %s, %s, %s",
                        rowsDeleted.getInt("cod"),
                        rowsDeleted.getString("nombre"),
                        rowsDeleted.getString("ciudad")
                );

                JOptionPane.showMessageDialog(null,"Se elimino correctamente");

            } } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error:" + e);

        }

    }


    @Override
    public List<Aeropuerto> obtenerTodos() {


        try {
            try {
                preQuery = connection.prepareStatement(QUERIES[4]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                aeropuertos.add(new Aeropuerto(
                        data.getString("codigo"),
                        data.getString("ciudad"), data.getString("nombre")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aeropuertos;
    }

    @Override
    public void obtener(Aeropuerto k) {
        try {
            try {
                preQuery = connection.prepareStatement(QUERIES[5]);
                preQuery.setString(1, k.getCodigoAeropuerto());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                aeropuerto = new Aeropuerto(
                        data.getString("codigo"),
                        data.getString("ciudad"), data.getString("nombre")
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public Aeropuerto obtenerPorCodigo(String codigo) {
        try {
            try {
                preQuery = connection.prepareStatement(QUERIES[5]);
                preQuery.setString(1, codigo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                aeropuerto = new Aeropuerto(
                        data.getString("codigo"),
                        data.getString("ciudad"), data.getString("nombre")
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aeropuerto;
    }


    public void seleccionarAeropuerto(JTable tablaAeropuerto, JTextField cod, JTextField ciudad, JTextField nombre) {

        try {

            int fila = tablaAeropuerto.getSelectedRow();

            if (fila >= 0) {
                cod.setText(tablaAeropuerto.getValueAt(fila, 0).toString());
                ciudad.setText(tablaAeropuerto.getValueAt(fila, 1).toString());
                nombre.setText(tablaAeropuerto.getValueAt(fila, 2).toString());

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error: " + e);
        }


    }




    public void mostrarAeropuertos(JTable tablaDeAeropuertos) {
        DefaultTableModel modelo = new DefaultTableModel();


        modelo.addColumn("cod");
        modelo.addColumn("ciudad");
        modelo.addColumn("nombre");
        tablaDeAeropuertos.setModel(modelo);


        try {

            preQuery = this.connection.prepareStatement(QUERIES[4]);
            ResultSet data = preQuery.executeQuery();

            String[] datos = new String[5];


            while (data.next()) {


                datos[0] = data.getString(1);
                datos[1] = data.getString(2);
                datos[2] = data.getString(3);


                modelo.addRow(datos);


            }

            tablaDeAeropuertos.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error:" + e);

        }
    }


}