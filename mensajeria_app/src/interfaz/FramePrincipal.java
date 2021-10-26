/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import interfaz.pestanas.TabPanel;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Mario
 */
public class FramePrincipal extends JFrame {
    


    public FramePrincipal() {
        
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize();       
        int height = tamanoPantalla.height;
        int width = tamanoPantalla.width;
        
        //Lo que hay que usar
        
         //Loque hay que borrar
        

        setLocation(width/2 - getWidth()/2 , height/2 -getHeight()/2);
        
        setTitle("Glovo Company S.L");
        
        add(new TabPanel());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        pack();
        setVisible(true);
        
        

    }

}
