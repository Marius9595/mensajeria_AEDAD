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
        /* si lo hacemos así no podremos luego mandarle el permiso de usuario a la pestaña 
        que toque
        CONSULTAR: sacar los tab como private/protected para el posterior setter del permiso*/
        addTab("Login",          new Login());
        addTab("Cliente",        new Cliente_Repartidor(0));
        addTab("Repartidor",     new Cliente_Repartidor(1));
        addTab("Administrativo", new Administrativo(2));
        addTab("Admin",          new Admin(3));
        
        // inicialmente que este todo en disabled y cuando loggee activamos
        /* para pruebas comentar esta parte
        setEnabledAt(1, false);
        setEnabledAt(2, false);
        setEnabledAt(3, false);
        setEnabledAt(4, false);
        */
        
        //temporal: TODO: HACER RESPONSIVE
        setPreferredSize(new Dimension(500,500));

    }

}
