/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_BD;

import java.time.LocalDateTime;


/**
 * Clase usuario de la base de datos
 * @author Jonathan
 * @author Mario
 */
public class Usuario {
    // los permisos
   private final String[] PERMISOS = new String[]{"Cliente", "Reparto", "Administrador", "Admin"};
    
   private int id_usuario;
   private String nombre;
   private String apellidos;
   // la idea es qeu cargue aquí los datos datetime: yyyy-MM-dd HH:mm:ss
   private LocalDateTime  fecha_ultima_conection;
   private String  fecha_ultima_conection_string;
   
   private int id_provincia;
   private String provincia;
   
   private int permisos;
   // faltaban estos dos
   private String correo;
   private String password;

    public Usuario(int id_usuario, String nombre, String apellidos, LocalDateTime fecha_ultima_conection, int id_provincia, int permisos, String correo, String password) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_ultima_conection = fecha_ultima_conection;
        this.id_provincia = id_provincia;
        this.permisos = permisos;
        this.correo = correo;
        this.password = password;
    }

    public Usuario() {
    }
    
    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    /**
     * getter fecha ultima conection formato: yyyy-MM-dd-'T'-HH-mm-ss-ns
     * @return localDateTime
     */
    public LocalDateTime getFecha_ultima_conection() {
        return fecha_ultima_conection;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public int getPermisos() {
        return permisos;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Setter formato localDateTime: yyyy-MM-dd-'T'-HH-mm-ss-ns
     * @param fecha_ultima_conection 
     */
    public void setFecha_ultima_conection(LocalDateTime fecha_ultima_conection) {
        this.fecha_ultima_conection = fecha_ultima_conection;
    }

    /**
     * Setter formato string
     * @param fecha_ultima_conection 
     */
    public void setFecha_ultima_conection(String fecha_ultima_conection) {
        //this.fecha_ultima_conection = Utilidades.stringToLocalDateTime(fecha_ultima_conection);
        this.fecha_ultima_conection_string= fecha_ultima_conection;
    }
    
    public String getFecha_ultima_conection_string() {
        return this.fecha_ultima_conection_string;
    }
    
    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }
    /**
     * funcion que coge el valor del permiso y lo traduce a string
     * @return string del valor de permiso
     */
    public String getPermisosToString(){
        return PERMISOS[permisos];
    }

    // los que faltaban
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
