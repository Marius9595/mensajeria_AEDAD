/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import java.awt.Dimension;
import javax.swing.JTabbedPane;

/**
 *
 * @author Mario
 */
public class TabPanel extends JTabbedPane {

    public TabPanel() {

        addTab("Login",          new Login());
        addTab("Cliente",        new Cliente_Repartidor());
        addTab("Repartidor",     new Cliente_Repartidor());
        addTab("Administrativo", new Administrativo());
        addTab("Admin",          new Admin());
        
        
        //temporal: TODO: HACER RESPONSIVE
        setPreferredSize(new Dimension(500,500));

    }

}
