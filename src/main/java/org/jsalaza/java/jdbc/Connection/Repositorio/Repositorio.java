package org.jsalaza.java.jdbc.Connection.Repositorio;
import org.jsalaza.java.jdbc.Connection.model.Producto;

import java.sql.PreparedStatement;
import java.util.List;

public interface Repositorio<T> {
    List<T> listar();
    T porId(Long id);
    void guardar(Producto producto);
    void eliminar(Long id);
}
