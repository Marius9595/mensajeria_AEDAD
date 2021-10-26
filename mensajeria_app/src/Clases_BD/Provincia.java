/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_BD;

/**
 *
 * @author Dizzy
 */
public class Provincia {
    private int id_provincia;
    private String nombre;

    // constructor
    public Provincia(int id_provincia, String nombre) {
        this.id_provincia = id_provincia;
        this.nombre = nombre;
    }

    // getter y setter
    public int getId_provincia() {
        return id_provincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
