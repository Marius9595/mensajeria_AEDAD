/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import interfaz.pestanas.TabPanel;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

/**
 * @author Jonathan
 * @author Mario
 */
public class FramePrincipal extends JFrame {

    public FramePrincipal() throws SQLException {
        
        setTitle("Glovo Company S.L");
        setDefaultCloseOperation(EXIT_ON_CLOSE);   
        
        ImageIcon img = new ImageIcon("..\\Recursos\\error.jpg");
        setIconImage(img.getImage());
        
        // el tabpanel
        add(new TabPanel());
             
        // centramos
        mensajeria_app.Utilidades.centrarPantalla(this);
        
        setVisible(true);
    }
}
