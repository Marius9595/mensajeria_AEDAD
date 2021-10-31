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

    public Event_Boton_Personalizado() {
    }

    public JButton getSource() {
        return source;
    }

    public void setSource(JButton source) {
        this.source = source;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    }
}
