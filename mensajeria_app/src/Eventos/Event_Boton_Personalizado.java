/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eventos;

import java.awt.event.*;
import javax.swing.*;

/**
 * Evento personalizado del boton
 * @author Jonathan
 * @author Mario
 */
public class Event_Boton_Personalizado implements ActionListener {
    // solo se usa la propiedad de source antes tenía más atributos pero se simplifico

    protected JButton source;

    // constructor
    public Event_Boton_Personalizado() {
    }

    // getter / setter
    public JButton getSource() {
        return source;
    }

    public void setSource(JButton source) {
        this.source = source;
    }

    // obligamos que tenga un action
    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
