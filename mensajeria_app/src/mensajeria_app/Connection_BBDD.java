/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class Connection_BBDD {
    
    
    String     user;  
    String     password;
    String     ip_connection;       
    String     BBDD;                
    Connection conexion;          
    Statement  query;            

    public Connection_BBDD(String user, String password, String ip_connection, String data_base) {
             
        this.user           = user;
        this.password       = password;
        this.ip_connection  = ip_connection;
        this.BBDD           = "jdbc:mysql://"+this.ip_connection+"/"+data_base;
               
    }

    
    private void connect(){
        

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection_BBDD.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexion = DriverManager.getConnection(BBDD, user, password);
            query = conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Connection_BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void close_connection(){
        
        try {

            if (query != null){
                query.close();
            }
            
            if (conexion != null){
                conexion.close();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Connection_BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    //FUNCIONES INTERNAS PARA HACER INTELIGIBLES LAS OPERACIONES CRUD

    
    private String add_filters (String sentencia, String filtros){
        

        if( filtros != null){
      
            sentencia += " WHERE " + filtros;
        }

        return sentencia;
    }
    
    
    private String parse_register_type (Object value){
        
        String  result = "";
        
        if (value  instanceof Integer || value instanceof Float || value instanceof Double){
            
            result = value+"";
            
        } else if(value instanceof String){
            
          result = "'"+value.toString()+"'";
        }
        
        
     return result;   
        
    }
    
    
    
    private HashMap<String,String> parse_registers_data(HashMap<String,Object> registros){
        
        
        HashMap<String,String> registros_parseados = new HashMap<>();

        for (String columna : registros.keySet()) {
            
            registros_parseados.put(columna, parse_register_type(registros.get(columna)));

        }
        
        return registros_parseados;
        
    }
    
    
    
    private String wirte_registers (ArrayList<String> values, ArrayList<String> columnas, String sentencia){
        
        
        //añadir columnas
        sentencia += "(";

        for (String columna : columnas) {


            sentencia += columna +",";

        }

        sentencia = sentencia.substring(0, sentencia.length()-1);

        sentencia += ") VALUES (";


       //añadir valores


        for (String value : values) {


            sentencia += value +",";

        }

        sentencia = sentencia.substring(0, sentencia.length()-1);


        sentencia += ")";
        
        return sentencia;
    }
    
    
    
    
    
    //OPERACIONES EN LA BBDD

    

    
    public void insert (String tabla, HashMap<String,Object> registros){
        
        
        HashMap<String,String> registros_parseados = parse_registers_data(registros);
        
        ArrayList<String> columnas = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        
        
        for (String columna : registros_parseados.keySet()) {
            
            columnas.add(columna);
            values.add(registros_parseados.get(columna)); 
        }

        String sentencia = "INSERT INTO " + tabla + " ";
        
        
        try {
   
  
            sentencia = wirte_registers(values, columnas, sentencia);

          
            System.out.println(sentencia);

            this.connect();
                      
            
            query.execute(sentencia);
            
            System.out.println("Se ha insertado todo correctamente");
            

        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        } catch (Exception e){
            
            System.out.println(e.toString());
        }
        
        
        
    }
    
    
    public void delete (String tabla, String filtros){
        
        
        String sentencia = "DELETE FROM " + tabla + " ";

        sentencia = add_filters(sentencia, filtros) ;
        
        try {
   
  

            this.connect();
                    
            
            query.execute(sentencia);
            
            System.out.println("Se ha borrado el registro");
            

        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        } catch (Exception e){
            
            System.out.println(e.toString());
        }

        
    }
    
    
    
    public void  update(String tabla, HashMap<String,Object> registros, String filtros){
        
        HashMap<String,String> registros_parseados = parse_registers_data(registros);
        
 

        
        String sentencia = "UPDATE "+ tabla + " SET ";
        
        
        
        for (String columna : registros_parseados.keySet()) {
            
            sentencia += columna + " = " + registros_parseados.get(columna) + ",";
            
        }
        
        sentencia = sentencia.substring(0,sentencia.length()-1);
        
        sentencia = add_filters(sentencia, filtros);
        
        
        
        try {

            this.connect();

            
            query.execute(sentencia);
            
            System.out.println("Se ha actualizado el registro");
            

        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        } catch (Exception e){
            
            System.out.println(e.toString());
        }
        
        
        
        
    }
    
    
    public ResultSet raw_select(String query_sended){
        

         try {

            this.connect();
            
            return query.executeQuery(query_sended);
            

        } catch (SQLException ex) {
            
            System.out.println(ex.toString());
        } catch (Exception e){
            
            System.out.println(e.toString());
        }  
        
         return null;
 
    }

}

    

