package datos;

import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.graph.Multigraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class CargaDeDatos {
    static String line = "";
    static String cvsSplitBy = ";";

    //carga los aeropuerto desde el archivo

    public static TreeMap<String, Aeropuerto> cargaAeropuerto() throws FileNotFoundException {
        BufferedReader read = null;

        TreeMap<String, Aeropuerto> aeropuertoTreeMap = new TreeMap<String, Aeropuerto>();

        try {
            read = new BufferedReader(new FileReader("Aeropuertos.csv"));

            while ((line = read.readLine()) != null) {

                String[] renglon = line.split(cvsSplitBy);
                aeropuertoTreeMap.put(renglon[0], new Aeropuerto(renglon[0], renglon[1], renglon[2]));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return aeropuertoTreeMap;
    }

    //carga el grafo desde el archivo
    public static Multigraph<Aeropuerto, Vuelo> cargarGrafo() throws FileNotFoundException {
        BufferedReader read = null;
        Multigraph<Aeropuerto, Vuelo> rutasAereasGrafo = new Multigraph<>(Vuelo.class);
        TreeMap<String, Aeropuerto> aeropuertosCsv = cargaAeropuerto();
        try {
            read = new BufferedReader(new FileReader("vuelos_prueba1.csv"));

            while ((line = read.readLine()) != null) {

                String[] reglon = line.split(cvsSplitBy);


                Aeropuerto a = aeropuertosCsv.get(reglon[0]);
                Aeropuerto b = aeropuertosCsv.get(reglon[1]);

                rutasAereasGrafo.addVertex(a);
                rutasAereasGrafo.addVertex(b);

                Vuelo vuelo = new Vuelo(reglon[2], reglon[3], Integer.parseInt(reglon[4]),
                        Integer.parseInt(reglon[5]), Integer.parseInt(reglon[6]),0);

                System.out.println(vuelo);
                rutasAereasGrafo.addEdge(a,b, vuelo);
                Aeropuerto x = rutasAereasGrafo.getEdgeSource(vuelo);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rutasAereasGrafo;
    }

    public  String[] getFile(String archivo) throws FileNotFoundException {
        BufferedReader read = null;


        String[] renglon = new String[3];
        try {
            read = new BufferedReader(new FileReader(archivo));

            while ((line = read.readLine()) != null) {

                renglon = line.split(cvsSplitBy);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return renglon;
    }


}
