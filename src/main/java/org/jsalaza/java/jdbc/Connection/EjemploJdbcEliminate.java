package org.jsalaza.java.jdbc.Connection;

import org.jsalaza.java.jdbc.Connection.Repositorio.ProdutcoRepositorioImp;
import org.jsalaza.java.jdbc.Connection.Repositorio.Repositorio;
import org.jsalaza.java.jdbc.Connection.model.Producto;

import java.sql.Connection;
import java.sql.SQLException;

public class EjemploJdbcEliminate {
    public static void main(String[] args) {
        try(Connection conn= ConexionBaseDatos.getInstance()){
            Repositorio<Producto> repositorio=new ProdutcoRepositorioImp();
            System.out.println("=======Listar==========");
            repositorio.listar().forEach(System.out::println );

            System.out.println("--------------------Obtener porID---------");
            System.out.println(repositorio.porId(2L));

            System.out.println("---------------Editar nuevoo Producto---------------");
            Producto producto=new Producto();
            repositorio.eliminar(4L);
            System.out.println("Producto eliminado de forma exitosas");
            repositorio.listar().forEach(System.out::println);
            }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
