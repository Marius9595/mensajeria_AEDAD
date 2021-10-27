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
public class Login extends JPanel {
    
    private JTextField campo_user_name;
    private JTextField campo_password;

    public Login() {
        
        setLayout(new BorderLayout());
        
        JPanel center_container = new JPanel();
        center_container.setLayout(new BoxLayout(center_container,BoxLayout.Y_AXIS));
        
        // panel del login
        JPanel north_container = new JPanel(new FlowLayout());
        // panel del boton
        JPanel south_container = new JPanel(new FlowLayout());
        
        // paneles formularios
        JPanel linea_user_name = new JPanel(new FlowLayout());
        JPanel linea_password= new JPanel(new FlowLayout());
        
        // faltaba un panel

        JLabel titulo = new JLabel("LOGIN");
        
        JLabel label_usuario = new JLabel("Usuario :");
        linea_user_name.add(label_usuario);
        
        campo_user_name=new JTextField();
        linea_user_name.add(campo_user_name);

        JLabel label_password = new JLabel("Contrase�a :");       
        linea_user_name.add(label_password);
        
        campo_password=new JTextField();
        linea_user_name.add(campo_password);

        JButton botonEnviar = new JButton();
        botonEnviar.setText("Acceder");
        //botonLogout.addComponentListener(new ActionListener()); // evento sing in

        north_container.add(titulo);
        
        center_container.add(linea_user_name);
        center_container.add(linea_password);
        
        south_container.add(botonEnviar);
     
        add(north_container, BorderLayout.NORTH );
        add(center_container,BorderLayout.CENTER);
        add(south_container,BorderLayout.SOUTH);
    }
}