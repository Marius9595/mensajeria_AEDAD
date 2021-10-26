/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_BD;

import java.time.LocalDateTime;


/**
 *
 * @author Dizzy
 */
public class Usuario {
   private final String[] PERMISOS = new String[]{"Cliente", "Reparto", "Administrador", "Admin"};
    
   private int id_usuario;
   private String nombre;
   private String apellidos;
   // la idea es qeu cargue aquí los datos datetime: yyyy-MM-dd HH:mm:ss
   private LocalDateTime  fecha_ultima_conection;
   private int id_provincia;
   private int permisos; 
   //ENUM("0", "1", "2", "3") NOT NULL,

    public Usuario(int id_usuario, String nombre, String apellidos, LocalDateTime fecha_ultima_conection, int id_provincia, int permisos) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_ultima_conection = fecha_ultima_conection;
        this.id_provincia = id_provincia;
        this.permisos = permisos;
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

    // ojo que esto retorna: yyyy-MM-dd-'T'-HH-mm-ss-ns
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

    public void setFecha_ultima_conection(LocalDateTime fecha_ultima_conection) {
        this.fecha_ultima_conection = fecha_ultima_conection;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }
    
    public String getPermisosToString(){
        return PERMISOS[permisos];
    }
}
