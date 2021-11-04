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
 *
 * @author Mario
 */
public class FramePrincipal extends JFrame {
    


    public FramePrincipal() throws SQLException {
        
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();       
        int height = tamanoPantalla.height;
        int width = tamanoPantalla.width;
        
        setTitle("Glovo Company S.L");
        
        ImageIcon img = new ImageIcon("..\\Recursos\\error.jpg");
        setIconImage(img.getImage());
        
        add(new TabPanel());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        
        pack();
        
        setLocation(width/2 - getWidth()/2 , height/2 -getHeight()/2);
        setVisible(true);
    }

}
