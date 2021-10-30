/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_BD;

import java.time.LocalDateTime;

/**
 *
 * @author Jonathan
 * @author Mario
 */
public class Pedido {
    private int id_articulo;
    private String nombre_articulo;
    private int id_provincia;
    private String nombre_provincia;
    private int id_cliente;
    private String nombre_cliente;
    // int nulleable
    private Integer id_repartidor;
    private String nombre_repartidor;
    private LocalDateTime fecha_entrega;
    private int num_articulos;

    // constructor sin fecha de entrega
    public Pedido(int id_articulo, int id_provincia, int id_cliente, Integer id_repartidor, int num_articulos) {
        this.id_articulo = id_articulo;
        this.id_provincia = id_provincia;
        this.id_cliente = id_cliente;
        this.id_repartidor = id_repartidor;
        this.num_articulos = num_articulos;
        // inicializar pero usar luego los settter
        this.nombre_articulo = "";
        this.nombre_cliente = "";
        this.nombre_provincia = "";
        this.nombre_repartidor = "";
    }
    // contructor con fecha de entrega
    public Pedido(int id_articulo, int id_provincia, int id_cliente, Integer id_repartidor, LocalDateTime fecha_entrega, int num_articulos) {
        this.id_articulo = id_articulo;
        this.id_provincia = id_provincia;
        this.id_cliente = id_cliente;
        this.id_repartidor = id_repartidor;
        this.fecha_entrega = fecha_entrega;
        this.num_articulos = num_articulos;
        // inicializar pero usar luego los settter
        this.nombre_articulo = "";
        this.nombre_cliente = "";
        this.nombre_provincia = "";
        this.nombre_repartidor = "";
    }

    // getter y setter
    public int getId_articulo() {
        return id_articulo;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public Integer getId_repartidor() {
        return id_repartidor;
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public int getNum_articulos() {
        return num_articulos;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_repartidor(Integer id_repartidor) {
        this.id_repartidor = id_repartidor;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public void setNum_articulos(int num_articulos) {
        this.num_articulos = num_articulos;
    }

    public String getNombre_articulo() {
        return nombre_articulo;
    }

    public void setNombre_articulo(String nombre_articulo) {
        this.nombre_articulo = nombre_articulo;
    }

    public String getNombre_provincia() {
        return nombre_provincia;
    }

    public void setNombre_provincia(String nombre_provincia) {
        this.nombre_provincia = nombre_provincia;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getNombre_repartidor() {
        return nombre_repartidor;
    }

    public void setNombre_repartidor(String nombre_repartidor) {
        this.nombre_repartidor = nombre_repartidor;
    }
}
