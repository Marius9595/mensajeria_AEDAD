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
    
    
    private int id_usuario;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    

    public TabPanel() {
        /* si lo hacemos así no podremos luego mandarle el permiso de usuario a la pestaña 
        que toque
        CONSULTAR: sacar los tab como private/protected para el posterior setter del permiso*/
        addTab("Login",          new Login(this));
        addTab("Cliente",        new Cliente_Repartidor(0));
        addTab("Repartidor",     new Cliente_Repartidor(1));
        addTab("Administrativo", new Administrativo(2));
        addTab("Admin",          new Admin(3));
        
        
        
        // inicialmente que este todo en disabled y cuando loggee activamos
        //para pruebas comentar esta parte
        setEnabledAt(1, false);
        setEnabledAt(2, false);
        setEnabledAt(3, false);
        setEnabledAt(4, false);
        
        
        //temporal: TODO: HACER RESPONSIVE
        setPreferredSize(new Dimension(650,500));

    }
    


}
