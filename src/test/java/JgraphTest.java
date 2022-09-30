package test.java;

import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.Multigraph;

import java.util.Set;

//prueba de libreria jgraph
public class JgraphTest {
    public static void main(String[] args) {

        DirectedWeightedMultigraph<Aeropuerto, Vuelo> rutasAereasgrafo = new DirectedWeightedMultigraph<Aeropuerto, Vuelo>(Vuelo.class);
        //crea aeropuertos
        Aeropuerto Aep = new Aeropuerto("AEP", "Buenos AIRES", "JORGENEW");
        Aeropuerto Cor = new Aeropuerto("COE", "Cordoba", "CORDOBA");
        Aeropuerto Sla = new Aeropuerto("SLA", "Salta", "Salta");
        Aeropuerto PMY = new Aeropuerto("PMY", "Puerto Madryn", "Madryn");

        //insert aeropuertos en el vertice del grafo
        rutasAereasgrafo.addVertex(Aep);
        rutasAereasgrafo.addVertex(Cor);
        rutasAereasgrafo.addVertex(Sla);
        rutasAereasgrafo.addVertex(PMY);
        //cargo el arco con los vuelos


        Vuelo FL1003 = new Vuelo("Aep100PMY0-","-", 200, 520, 13000, 0);
        rutasAereasgrafo.addEdge(Aep, PMY, FL1003);

        Vuelo FL1001 = new Vuelo("Aep100PMY1","-", 120, 520, 100,0);
        rutasAereasgrafo.addEdge(Aep, PMY, FL1001);
        Vuelo FL1002 = new Vuelo("Aep100PMY2-","-", 100, 720, 9,0);
        rutasAereasgrafo.addEdge(Aep, PMY, FL1002);

        rutasAereasgrafo.setEdgeWeight(FL1001,3);
        rutasAereasgrafo.setEdgeWeight(FL1002,2);
        rutasAereasgrafo.setEdgeWeight(FL1003,1);


        //carga el nuevo grafo en base al precio
        Multigraph<Aeropuerto, Integer> rutasAereasGrafoPrecio = new Multigraph<>(Integer.class);

        Set<Vuelo> edges = rutasAereasgrafo.getAllEdges(Aep, PMY);
        System.out.println("grafo" + rutasAereasgrafo);
        System.out.println("edges" + edges);
        System.out.println("supplir" + rutasAereasgrafo.getAllEdges(PMY, Aep));
        System.out.println("" + rutasAereasgrafo.getAllEdges(PMY, Aep));

        for (Vuelo e : rutasAereasgrafo.edgeSet()) {
            Aeropuerto origenVer = rutasAereasgrafo.getEdgeSource(e);
            Aeropuerto destinoVer = rutasAereasgrafo.getEdgeTarget(e);
            System.out.println("metodo get origenVer" + origenVer.toString());
            System.out.println("metodo get destinoVer" + destinoVer.toString());
            //carga el nuevo grafo con el precio en el arco
            rutasAereasGrafoPrecio.addVertex(origenVer);
            rutasAereasGrafoPrecio.addVertex(destinoVer);
            rutasAereasGrafoPrecio.addEdge(origenVer, destinoVer,e.getPrecio());

        }


        //algoritmo de ruta mas corta
        DijkstraShortestPath<Aeropuerto, Integer> rutaMasCorta = new DijkstraShortestPath<>(rutasAereasGrafoPrecio);
        DijkstraShortestPath<Aeropuerto, Vuelo> rutaMasCorta2 = new DijkstraShortestPath<>(rutasAereasgrafo);

        System.out.println("ruta    " + rutaMasCorta2.getPath(Aep, PMY));






    }
}


