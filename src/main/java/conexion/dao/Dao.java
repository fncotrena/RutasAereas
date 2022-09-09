package conexion.dao;

import java.util.List;

public interface Dao<T>{

    void insertar(T k);
    void modificar(T k);
    void eliminar(T k);
    List<T> obtenerTodos();
    void obtener(T k);


}