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
    
    
    private ArrayList<Usuario> get_generic_usuarios(String columnas, String filtros) throws SQLException{
        
        
        ArrayList<Usuario> usuarios_consulta = new ArrayList<Usuario>();
        
        String  sentencia = "SELECT " + columnas + " FROM  usuario ";
        
        
        if (filtros != null){
   
            sentencia += " WHERE " + filtros;

        }
        
   
        ResultSet respuesta = DB.raw_select(sentencia);
        
        if (respuesta != null){

            while(respuesta.next()){
                
                Usuario registro_usuario = new Usuario();
                registro_usuario.setId_usuario(Integer.parseInt(respuesta.getString("id_usuario")));
                registro_usuario.setNombre(respuesta.getString("nombre"));
                registro_usuario.setApellidos(respuesta.getString("apellidos"));
                registro_usuario.setCorreo(respuesta.getString("correo"));
                registro_usuario.setPermisos(Integer.parseInt(respuesta.getString("permisos")));
                registro_usuario.setId_provincia(Integer.parseInt(respuesta.getString("id_provincia")));
                registro_usuario.setFecha_ultima_conection(respuesta.getString("fecha_ultima_conection"));                
                usuarios_consulta.add(registro_usuario);
                
            }
            
            DB.close_connection();
            
            
        } else {
            
            
            usuarios_consulta = null;
        }
        
        
        return usuarios_consulta;
        
 
    }
    
    
    
    
    public Usuario Login (String correo, String password) throws SQLException{
        
        String filtros = "correo = '" + correo + "' AND password = '" + password + "'";
        
        
        
        ArrayList<Usuario> usuario_loggin = get_generic_usuarios("*",filtros);

        
        if (!usuario_loggin.isEmpty()){
            return usuario_loggin.get(0);
            
        }else{
            
            return null;
        }
        
        
        
        
    }
    
    
    public String get_provincia_by_id (int id_provincia) throws SQLException{
        
        
        String resultado = null;
        
        ResultSet respuesta = DB.raw_select("SELECT * FROM provincia WHERE id_provincia = " + id_provincia);
        
       
        
        if(respuesta != null){
            
            respuesta.next();
            
            resultado = respuesta.getString("nombre");
        
            DB.close_connection();
        }
        
        
        
        
        return resultado;

    }

    public ArrayList<String[]> lista_pedidos(int id_user, int permiso) throws SQLException {
        
       ResultSet respuesta; 
        
       if (permiso == 1 ){
           
           System.out.println("id: "+ id_user);
           
           respuesta =  DB.raw_select("SELECT art.* from articulo AS art INNER JOIN pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_repartidor = "+ id_user +"  AND ped.fecha_entrega IS NULL ORDER BY art.id_articulo ASC");
        
          
       }else {
           
            respuesta =  DB.raw_select("SELECT art.* from mensajeria.articulo AS art INNER JOIN mensajeria.pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_cliente = "+ id_user +"  ORDER BY ped.fecha_entrega DESC LIMIT 15");
         
           
       }

       ArrayList<String[]> articulos = new ArrayList<String[]>();
       
       
       
       
       if (respuesta != null ){
           
           while(respuesta.next()){
               
               
               String [] articulo = {respuesta.getString("id_articulo"),respuesta.getString("descripcion")};
               
               articulos.add(articulo);
 
           }
           
           DB.close_connection();
           
       }else {
           
           respuesta = null;
       }
        
       
       return articulos;
        
        
    }

    public ArrayList<String[]> usuarios_conexion(int id_usuario) throws SQLException {
        
        String Filtros = " id_usuario != " + id_usuario + " ORDER BY mensajeria.usuario.fecha_ultima_conection DESC LIMIT 0, 15";
        
        ArrayList<Usuario> Usuarios = get_generic_usuarios("*", Filtros);
        
        
        ArrayList<String[]> usuario_enviar = new ArrayList<String[]>();
        
        String column[]={"ID","Nombre y apellidos","Correo","Ultima conexion"};
        
        usuario_enviar.add(column);
        for (Usuario usuario : Usuarios) {
            
            String [] usuario_string = {usuario.getId_usuario()+"", usuario.getNombre() +" "+ usuario.getApellidos(), usuario.getCorreo(), usuario.getFecha_ultima_conection_string()};
            
            usuario_enviar.add(usuario_string);
        }
        
        return usuario_enviar;
        
        
        
    }

 
    
    

    
    
    
}
