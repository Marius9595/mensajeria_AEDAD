/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class Administrativo extends AbstractPestana {

    public Administrativo(int permiso) {
        super(permiso);
        
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        
        JPanel panelComandos = new JPanel();
        panelComandos.setLayout(new GridLayout(3,2));
        panelCentro.add(panelComandos);
        
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelCentro.add(panelTabla);
    }
}
