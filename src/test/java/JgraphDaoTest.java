package test.java;

import conexion.dao.AeropuertoDaoImpl;
import conexion.dao.VueloDaoImpl;
import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class JgraphDaoTest {
    public static void main(String[] args) {

        VueloDaoImpl v = new VueloDaoImpl();
        AeropuertoDaoImpl a= new AeropuertoDaoImpl();
       // System.out.println(a.obtenerPorCodigo("AEP"));
        DirectedWeightedMultigraph<Aeropuerto, Vuelo> grafo= v.obtenerGrafoCargado();

        for (Vuelo e : grafo.edgeSet()) {
            Aeropuerto origenVer = grafo.getEdgeSource(e);
            Aeropuerto destinoVer = grafo.getEdgeTarget(e);
            System.out.println("metodo get origenVer" + origenVer.toString());
            System.out.println("metodo get destinoVer" + destinoVer.toString());
            System.out.println("arco" + e);

            //carga el nuevo grafo con el precio en el arco
            grafo.addVertex(origenVer);
            grafo.addVertex(destinoVer);
            grafo.addEdge(origenVer, destinoVer, e);

        }

    }
}
