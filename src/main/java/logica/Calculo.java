package logica;

import conexion.dao.AeropuertoDaoImpl;
import conexion.dao.VueloDaoImpl;
import modelo.Aeropuerto;
import modelo.Vuelo;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

public class Calculo {
    VueloDaoImpl v = new VueloDaoImpl();
    AeropuertoDaoImpl aeropuertoDao = new AeropuertoDaoImpl();


    public Calculo() {

    }

    public GraphPath<Aeropuerto, Vuelo> porPrecio(String origen, String destino) {


        DirectedWeightedMultigraph<Aeropuerto, Vuelo> grafo = v.obtenerGrafoCargado();

        for (Vuelo e : grafo.edgeSet()) {
            grafo.setEdgeWeight(e,e.getPrecio());

        }
        return this.getAeropuertoIntegerGraphPath(origen,destino,grafo);



    }    public GraphPath<Aeropuerto, Vuelo> porDuracion(String origen, String destino) {



        DirectedWeightedMultigraph<Aeropuerto, Vuelo> grafo = v.obtenerGrafoCargado();

        for (Vuelo e : grafo.edgeSet()) {
            grafo.setEdgeWeight(e,e.getHoraLlegada());
        }
        return this.getAeropuertoIntegerGraphPath(origen,destino,grafo);
    }



    private GraphPath<Aeropuerto, Vuelo> getAeropuertoIntegerGraphPath(String origen, String destino, DirectedWeightedMultigraph<Aeropuerto, Vuelo> rutasAereasgrafo) {
        DijkstraShortestPath<Aeropuerto, Vuelo> rutaMasCorta = new DijkstraShortestPath<>(rutasAereasgrafo);

        Aeropuerto aeroOrigen =aeropuertoDao.obtenerPorCodigo(origen);
        Aeropuerto aeroDest =aeropuertoDao.obtenerPorCodigo(destino);
        for(Aeropuerto ae : rutasAereasgrafo.vertexSet()) {
            if (aeroOrigen.equals(ae)){
            aeroOrigen=ae;
            System.out.println("es igual");}

           else if (aeroDest.equals(ae)){
            aeroDest=ae;
            System.out.println("es igual");}

        }
        return rutaMasCorta.getPath(aeroOrigen,aeroDest);
    }


}
