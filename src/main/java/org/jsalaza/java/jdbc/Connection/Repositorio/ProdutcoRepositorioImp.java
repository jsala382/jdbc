package org.jsalaza.java.jdbc.Connection.Repositorio;

import org.jsalaza.java.jdbc.Connection.ConexionBaseDatos;
import org.jsalaza.java.jdbc.Connection.model.Categoria;
import org.jsalaza.java.jdbc.Connection.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutcoRepositorioImp implements Repositorio {


    private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }

    @Override
    public List listar() {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT p.*, c.nombre as categoria FROM java_curso.producto as p inner join categorias as c ON (p.categoria_id=c.id)")) {
            while (resultSet.next()) {
                Producto p = crearGetProducto(resultSet);
                productos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    @Override
    public Object porId(Long id) {
        Producto producto = null;
        try (PreparedStatement stm = getConnection().
                prepareStatement("select p.*,c.nombre as categoria FROM  java_curso.producto as p inner join categorias as c ON(p.categoria_id=c.id) where p.id =?")) {
            stm.setLong(1, id);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                producto = crearGetProducto(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            sql = "update java_curso.producto set nombre=?, precio=?,categoria_id=? where id=?";
        } else {
            sql = "Insert into java_curso.producto(nombre,precio,categoria_id,fecha_registro) VALUES(?,?,?,?)";
        }

        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setLong(2, producto.getPrecio());
            stmt.setLong(3, producto.getCategoria().getId());

            if (producto.getId() != null && producto.getId() > 0) {
                stmt.setLong(4, producto.getId());
            } else {
                stmt.setDate(4, new Date(producto.getFechaRegistro().getTime()));
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM java_curso.producto where id=?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Producto crearGetProducto(ResultSet resultSet) throws SQLException {
        Producto p = new Producto();
        p.setId(resultSet.getLong("id"));
        p.setNombre(resultSet.getNString("nombre"));
        p.setPrecio(resultSet.getInt("precio"));
        p.setFechaRegistro(resultSet.getDate("fecha_registro"));
        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getLong("categoria_id"));
        categoria.setNombre(resultSet.getNString("categoria"));
        p.setCategoria(categoria);
        return p;
    }
}
