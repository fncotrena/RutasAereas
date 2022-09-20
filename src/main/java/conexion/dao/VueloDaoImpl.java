package conexion.dao;

import conexion.DbManager;
import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.Multigraph;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VueloDaoImpl implements Dao<Vuelo> {
    private Connection connection;
    //Declaración de variable para insertar valores a consultas
    private PreparedStatement preQuery;
    DirectedWeightedMultigraph<Aeropuerto, Vuelo> rutasAereasgrafo;

    //Devuelve true si se ejecutó correctamente y false si algo falló
    private boolean isSuccesfully = false;
    //Objeto que servirá para almacenar información al buscar un registro
    private Vuelo vuelo;
    //Lista de registros
    private List<Vuelo> vuelos;

    private final String[] QUERIES = {
            "INSERT INTO vuelo (origen,destino,nrovuelo,nombreaerolinea, horallegada, horasalida,precio) VALUES (?,?,?,?,?,?,?);",
            "SELECT * FROM vuelo WHERE id = ?;",
            "UPDATE vuelo SET origen = ?,destino = ?,nrovuelo = ? ,nombreaerolinea = ?, horallegada = ?, horasalida = ? ,precio = ? WHERE (nrovuelo = ?);",
            "DELETE FROM vuelo WHERE (nrovuelo = ?);",
            "SELECT * FROM vuelo;",
            "SELECT * FROM aeropuerto origen INNER JOIN vuelo v ON (origen.codigo = v.origen)" +
                    " INNER JOIN aeropuerto destino ON (destino.codigo = v.destino);",


    };

    public VueloDaoImpl() {
        connection = DbManager.getInstance().getConnection();
        vuelo = new Vuelo();
        vuelos = new ArrayList<>();
        rutasAereasgrafo = new DirectedWeightedMultigraph<>(Vuelo.class);


    }

    @Override
    public void insertar(Vuelo a) {




            try {

                preQuery = this.connection.prepareStatement(QUERIES[0]);

                preQuery.setString(1, a.getNroVuelo());
                preQuery.setString(2,a.getNombreAerolinea());
                preQuery.setInt(3, a.getHoraSalida());
                preQuery.setInt(4, a.getHoraSalida());
                preQuery.setInt(5,a.getPrecio());

                JOptionPane.showMessageDialog(null,"Se insertó correctamente");


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error:"+ e.toString());
            }


    }



    @Override
    public void modificar(Vuelo k) {
/*
        Vuelo vuelo = new Vuelo();
        vuelo.setNroVuelo(nroVuelo.getText());
        vuelo.setNombreAerolinea(nombreAerolinea.getText());
        vuelo.setHoraSalida(Integer.parseInt(horaSalida.getText()));
        vuelo.setHoraLlegada(Integer.parseInt((horaLlegada.getText())));
        vuelo.setPrecio(Integer.parseInt(precio.getText()));*/

        try {
            preQuery = this.connection.prepareStatement(QUERIES[2]);

            preQuery.setString(1, k.getNroVuelo());
            preQuery.setString(2, k.getNombreAerolinea());
            preQuery.setInt(3, k.getHoraSalida());
            preQuery.setInt(4, k.getHoraLlegada());
            preQuery.setInt(5,k.getPrecio());

            int executeUpdate = preQuery.executeUpdate();

            if (executeUpdate == 1) {
                System.out.println("modificó correctamente ..");
            }
            JOptionPane.showMessageDialog(null, "Se modificó correctamente");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Se modificó correctamente");


        }

    }

    @Override
    public void eliminar(Vuelo k) {


        try {

            preQuery = this.connection.prepareStatement(QUERIES[3]);
            //String [] datos = new String[5];

            preQuery.setString(1, k.getNroVuelo());

            preQuery.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }


    }

    @Override
    public List<Vuelo> obtenerTodos() {


        try {
            try {
                preQuery = this.connection.prepareStatement(QUERIES[4]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                vuelos.add(new Vuelo(
                        data.getString("nroVuelo"),
                        data.getString("nombreAerolinea"), data.getInt("horaSalida")
                        , data.getInt("horaLlegada"), data.getInt("precio"), data.getInt("escala")
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vuelos;
    }

    @Override
    public void obtener(Vuelo k) {

    }






    public DirectedWeightedMultigraph<Aeropuerto, Vuelo> obtenerGrafoCargado() {

        AeropuertoDaoImpl daoAero = new AeropuertoDaoImpl();
        Aeropuerto origenVerx;
        Aeropuerto destinoVerx;
        try {
            try {
                preQuery = this.connection.prepareStatement(
                        "SELECT * FROM aeropuerto origen INNER JOIN vuelo v ON (origen.codigo = v.origen) INNER JOIN aeropuerto destino ON (destino.codigo = v.destino);"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet data = preQuery.executeQuery();

            while (data.next()) {
                //System.out.println();
                origenVerx = new Aeropuerto(data.getString(1),
                        data.getString(2),
                        data.getString(3));
                destinoVerx = new Aeropuerto(data.getString(13),
                        data.getString(14),
                        data.getString(15));

                rutasAereasgrafo.addVertex(origenVerx);
                rutasAereasgrafo.addVertex(destinoVerx);


                rutasAereasgrafo.addEdge(origenVerx,
                        destinoVerx
                        , new Vuelo(
                                data.getString("nroVuelo"),
                                data.getString("nombreAerolinea"), data.getInt("horaSalida")
                                , data.getInt("horaLlegada"), data.getInt("precio"), data.getInt("escala")
                        ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(VueloDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rutasAereasgrafo;
    }


    public void mostrarVuelos(JTable paramTablaTotalVuelos) {


        DefaultTableModel modelo = new DefaultTableModel();


        modelo.addColumn("id");
        modelo.addColumn("origen");
        modelo.addColumn("destino");
        modelo.addColumn("nroVuelo");
        modelo.addColumn("nombreAerolinea");
        modelo.addColumn("horaSalida");
        modelo.addColumn("horaLlegada");
        modelo.addColumn("precio");
        paramTablaTotalVuelos.setModel(modelo);


        try {

            preQuery = this.connection.prepareStatement(QUERIES[4]);
            ResultSet data = preQuery.executeQuery();

            String[] datos = new String[10];


            while (data.next()) {


                datos[0] = data.getString(1);
                datos[1] = data.getString(2);
                datos[2] = data.getString(3);
                datos[3] = data.getString(4);
                datos[4] = data.getString(5);
                datos[5] = data.getString(6);
                datos[6] = data.getString(7);
                datos[7] = data.getString(8);

                modelo.addRow(datos);


            }

            paramTablaTotalVuelos.setModel(modelo);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error:" + e.toString());

        }


    }

    public void seleccionarVuelo(JTable tablaVuelos,JTextField id, JTextField origen, JTextField destino, JTextField nroVuelo,
                                 JTextField nombreAerolinea,JTextField horaLlegada,JTextField horaSalida,JTextField precio
                                 ){

        try {

            int fila =tablaVuelos.getSelectedRow();

            if (fila>=0) {
                id.setText(tablaVuelos.getValueAt(fila, 0).toString());
                origen.setText(tablaVuelos.getValueAt(fila, 1).toString());
                destino.setText(tablaVuelos.getValueAt(fila, 2).toString());
                nroVuelo.setText(tablaVuelos.getValueAt(fila, 3).toString());
                nombreAerolinea.setText(tablaVuelos.getValueAt(fila, 4).toString());
                horaLlegada.setText(tablaVuelos.getValueAt(fila, 5).toString());
                horaSalida.setText(tablaVuelos.getValueAt(fila, 6).toString());
                precio.setText(tablaVuelos.getValueAt(fila, 7).toString());
            }

            else
            {
                JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Error: "+ e.toString());
        }

    }



    public void insertarVuelo(JTextField origen, JTextField destino, JTextField nroVuelo, JTextField nombreAerolinea, JTextField horaSalida,
                         JTextField horaLlegada, JTextField precio) {
        try {

            preQuery = this.connection.prepareStatement(QUERIES[0]);

            preQuery.setString(1,origen.getText());
            preQuery.setString(2, destino.getText());
            preQuery.setString(3, nroVuelo.getText());
            preQuery.setString(4, nombreAerolinea.getText());
            preQuery.setInt(5, Integer.parseInt(horaSalida.getText()));
            preQuery.setInt(6, Integer.parseInt(horaLlegada.getText()));
            preQuery.setInt(7, Integer.parseInt(precio.getText()));
            preQuery.executeUpdate();

            JOptionPane.showMessageDialog(null,"Se insertó correctamente");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+ e);
        }

}

    public void modificarVuelo(JTextField origen, JTextField destino, JTextField nroVuelo, JTextField nombreAerolinea, JTextField horaSalida,
                              JTextField horaLlegada, JTextField precio) {
        try {

            preQuery = this.connection.prepareStatement(QUERIES[2]);

            preQuery.setString(1,origen.getText());
            preQuery.setString(2, destino.getText());
            preQuery.setString(3, nroVuelo.getText());
            preQuery.setString(4, nombreAerolinea.getText());
            preQuery.setInt(5, Integer.parseInt(horaSalida.getText()));
            preQuery.setInt(6, Integer.parseInt(horaLlegada.getText()));
            preQuery.setInt(7, Integer.parseInt(precio.getText()));
            preQuery.setString(8, nroVuelo.getText());
            preQuery.executeUpdate();

            JOptionPane.showMessageDialog(null,"Se modifico correctamente");


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error:"+ e);
        }

    }
    public void elimnarVuelo( JTextField nroVuelo) {
            try {
        preQuery = this.connection.prepareStatement(QUERIES[3]);
        preQuery.setString(1, nroVuelo.getText());
        ResultSet rowsDeleted = preQuery.executeQuery();
        while (rowsDeleted.next()) {
            System.out.printf(" %s, %s, %s",
                    rowsDeleted.getInt("cod"),
                    rowsDeleted.getString("nombre"),
                    rowsDeleted.getString("ciudad")
            );

            JOptionPane.showMessageDialog(null,"Se elimino correctamente");

        }
            } catch (Exception e) {

        JOptionPane.showMessageDialog(null, "Error:" + e);

    }

}



}

