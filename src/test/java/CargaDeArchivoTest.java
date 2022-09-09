import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.graph.Multigraph;

import java.io.*;
import java.util.TreeMap;

import static aplicacion.Constante.AEROPUERTO_CSV;
import static aplicacion.Constante.VUELO_CSV;

public class CargaDeArchivoTest {
    static String line = "";
    static String cvsSplitBy = ";";

    public static TreeMap<String, Aeropuerto> cargaAeropuerto() throws FileNotFoundException {
        BufferedReader read = null;

        TreeMap<String, Aeropuerto> aeropuertoTreeMap = new TreeMap<String, Aeropuerto>();

        try {
            read = new BufferedReader(new FileReader(AEROPUERTO_CSV));

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

    public static Multigraph<Aeropuerto, Vuelo> cargarGrafo() throws FileNotFoundException {
        BufferedReader read = null;
        Multigraph<Aeropuerto, Vuelo> rutasAereasGrafo = new Multigraph<>(Vuelo.class);
        TreeMap<String, Aeropuerto> aeropuertosCsv = cargaAeropuerto();
        try {
            read = new BufferedReader(new FileReader(VUELO_CSV));

            while ((line = read.readLine()) != null) {

                String[] reglon = line.split(cvsSplitBy);


               Aeropuerto a = aeropuertosCsv.get(reglon[0]);
                Aeropuerto b = aeropuertosCsv.get(reglon[1]);

                rutasAereasGrafo.addVertex(a);
                rutasAereasGrafo.addVertex(b);

                Vuelo vuelo = new Vuelo(reglon[2], reglon[3], Integer.parseInt(reglon[4]),
                        Integer.parseInt(reglon[5]), Integer.parseInt(reglon[6]), 0);

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

    public static void main(String[] args) {
        try {

            Multigraph<Aeropuerto, Vuelo> grafoVuelos=cargarGrafo();
            for (Vuelo v : grafoVuelos.edgeSet()) {

                String x = "";
                System.out.println(v);
                System.out.println(" target " + grafoVuelos.getEdgeTarget(v));
                System.out.println(" source " + grafoVuelos.getEdgeSource(v));

            }
            cargaAeropuerto();
            cargarGrafo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
