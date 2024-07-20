package org.jsalaza.java.jdbc.Connection.util;

import org.jsalaza.java.jdbc.Connection.ConexionBaseDatos;
import org.jsalaza.java.jdbc.Connection.Repositorio.ProdutcoRepositorioImp;
import org.jsalaza.java.jdbc.Connection.Repositorio.Repositorio;
import org.jsalaza.java.jdbc.Connection.model.Categoria;
import org.jsalaza.java.jdbc.Connection.model.Producto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class EjemploJdbc {
    public static void main(String[] args) {
        try(Connection conn= ConexionBaseDatos.getInstance()){
            Repositorio<Producto> repositorio=new ProdutcoRepositorioImp();
            System.out.println("=======Listar==========");
            repositorio.listar().forEach(System.out::println );

            System.out.println("--------------------Ontener porID---------");
            System.out.println(repositorio.porId(2L));

            System.out.println("---------------Insetar nuevoo Producto---------------");
            Producto producto=new Producto();
            producto.setNombre("Teclado Razer mecacnico");
            producto.setPrecio(550);
            producto.setFechaRegistro(new Date());
            Categoria categoria=new Categoria();
            categoria.setId(3L);
            producto.setCategoria(categoria);
            repositorio.guardar(producto);
            System.out.println("Producto guardado de forma exitosas");
            repositorio.listar().forEach(System.out::println);
            }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
