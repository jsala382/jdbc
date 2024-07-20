package org.jsalaza.java.jdbc.Connection;

import org.jsalaza.java.jdbc.Connection.Repositorio.ProdutcoRepositorioImp;
import org.jsalaza.java.jdbc.Connection.Repositorio.Repositorio;
import org.jsalaza.java.jdbc.Connection.model.*;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcUpdate {
    public static void main(String[] args) {
        try(Connection conn= ConexionBaseDatos.getInstance()){
            Repositorio<Producto> repositorio=new ProdutcoRepositorioImp();
            System.out.println("=======Listar==========");
            repositorio.listar().forEach(System.out::println );

            System.out.println("--------------------Ontener porID---------");
            System.out.println(repositorio.porId(2L));

            System.out.println("---------------Insetrar nuevoo Producto---------------");
            Producto producto=new Producto();
            producto.setId(5L);
            producto.setNombre("Teclado Coair k95 mecanico ");
            producto.setPrecio(350);
            Categoria categoria=new Categoria();
            categoria.setId(2L);
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            System.out.println("Producto editado de forma exitosas");
            repositorio.listar().forEach(System.out::println);
            }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
