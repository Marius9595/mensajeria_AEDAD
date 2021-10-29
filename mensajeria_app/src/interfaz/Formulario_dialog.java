/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Mario
 */
public class Formulario_dialog extends JFrame implements ActionListener{
    
   
    
    private JPanel container_pricipal = new JPanel();

    

    public Formulario_dialog(String titulo, HashMap<String,String> datos, boolean editable)  {
        
        setTitle(titulo);
        
        container_pricipal.setLayout(new GridLayout(datos.size()+1,1));
        
        
        for (String columna:datos.keySet()) {
            
            JPanel flow_container = new JPanel();

            flow_container.add(new JLabel(columna + " :  "));
            
            JTextField valor = new JTextField(15);
            valor.setText(datos.get(columna));
            valor.setEditable(editable);
            
            
            valor.setName(columna);
            
            flow_container.add(valor);
            
            container_pricipal.add(flow_container, CENTER_ALIGNMENT);
       
        }
        
        add(container_pricipal);
        
        
        JButton boton_realizar = new JButton("Realizar");
        boton_realizar.addActionListener(new click_Execute());
        
        JButton boton_cancelar = new JButton("Cancelar");
        boton_cancelar.addActionListener(this);
        
        
        
        JPanel botones_container = new JPanel();
        
        
        botones_container.add(boton_realizar);
        botones_container.add(boton_cancelar);
                
        
        container_pricipal.add(botones_container,CENTER_ALIGNMENT);
        
        mensajeria_app.Utilidades.centrarPantalla(this);
        
        setVisible(true);
    }
    
    /**
     * cerrado genérico
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose(); 
    }
    
        /**
     * escucha del boton find
     */
    private class click_Execute extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            //JOptionPane.showMessageDialog(null, "opcion 1 ", "tester", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
