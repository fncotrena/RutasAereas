package conexion.factory;

import conexion.dao.AeropuertoDaoImpl;
import conexion.dao.Dao;
import conexion.dao.VueloDaoImpl;


/**
 *
 * @author federico cotrena
 */
public class DaoFactory {


    public enum DaoType{
        VUELO,AEROPUERTO
    }
    /**
     * @param daoType recibe el tipo de dao que quieres crear. VUELO |
     * @return una instancia del dao que indicamos
     */
    public static Dao  getDao  (DaoType daoType){

        return switch (daoType) {
            case VUELO -> new VueloDaoImpl();
            case AEROPUERTO -> new AeropuertoDaoImpl();
        };
    }


}