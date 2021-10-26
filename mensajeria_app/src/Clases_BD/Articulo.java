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
public class Articulo {
    private int id_articulo;
    private String descripcion;

    // constructor
    public Articulo(int id_articulo, String descripcion) {
        this.id_articulo = id_articulo;
        this.descripcion = descripcion;
    }

    // getter y setter
    public int getId_articulo() {
        return id_articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
