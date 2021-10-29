/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import Clases_BD.Articulo;
import Clases_BD.Pedido;
import Clases_BD.Provincia;
import Clases_BD.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class Controller_pedidos {
    
    ArrayList<Articulo>  articulos  = new ArrayList<Articulo>();
    ArrayList<Pedido>    pedidos    = new ArrayList<Pedido>();
    ArrayList<Provincia> provincias = new ArrayList<Provincia>();
    
    private final String USER = "root";
    private final String PASS = "";
    private final String IP = "Localhost:3306";
    private final String DATABASE = "mensajeria";
    /*
    private final String USER = "jony";
    private final String PASS = "123456";
    private final String IP = "Localhost:3306";
    private final String DATABASE = "mensajeria";
    */
    
    Connection_BBDD DB = new Connection_BBDD(USER, PASS, IP, DATABASE);
    
    
    
    
    
    
    public ArrayList<Usuario>  get_usuarios(int id_usuario_actual){
        
      ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        
      ResultSet usuarios_tabla =  DB.raw_select("SELECT id_usuario,nombre,apellidos,correo, p.nombre FROM usuario INNER JOIN provincia p on usuario.id_provincia = p.id_provincia WHERE id_usuario != " + id_usuario_actual);
        
        
      try {
          
        while(usuarios_tabla.next()){
        
            Usuario usuario = new Usuario();
            
            usuario.setId_usuario(usuarios_tabla.getInt("id_usuario"));
            usuario.setNombre(usuarios_tabla.getString("nombre"));
            usuario.setCorreo(usuarios_tabla.getString("correo"));
            usuario.setProvincia(usuarios_tabla.getString("p.nombre"));
            
            usuarios.add(usuario);

        } } catch (SQLException ex) {
            Logger.getLogger(Controller_pedidos.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      
      return usuarios;

    }

    
    
    public void update_usuarios(ArrayList<Usuario> usuario_actualizados){
        
        
        
        for (Usuario usuario_actualizado : usuario_actualizados) {
            
            
            
           // DB.update("usuario", registros, filtros);
            
        }
        
        
    }
    
    
    
}
