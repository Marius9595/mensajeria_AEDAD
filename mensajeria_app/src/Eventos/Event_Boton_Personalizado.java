/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class Event_Boton_Personalizado implements ActionListener {
    protected JButton source;
    protected int id_usuario;
    protected int permisos;
    protected int id_pedido;

    public Event_Boton_Personalizado() {
    }
    
    public Event_Boton_Personalizado(int id_usuario, int permisos) {
        this.id_usuario = id_usuario;
        this.permisos = permisos;
    }

    public JButton getSource() {
        return source;
    }

    public void setSource(JButton source) {
        this.source = source;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPermisos() {
        return permisos;
    }

    public void setPermisos(int permisos) {
        this.permisos = permisos;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
