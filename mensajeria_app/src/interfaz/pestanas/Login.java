/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
        
        JPanel linea_user_name = new JPanel(new FlowLayout());
        JPanel linea_password= new JPanel(new FlowLayout());
        
        
        JLabel titulo = new JLabel("LOGIN");        
        add(titulo, BorderLayout.NORTH );

        JLabel label_usuario = new JLabel("Usuario :");
        linea_user_name.add(label_usuario);
        
        campo_user_name=new JTextField();
        linea_user_name.add(campo_user_name);


        JLabel label_password = new JLabel("Contraseña :");       
        linea_user_name.add(label_password);
        
        campo_password=new JTextField();
        linea_user_name.add(campo_password);
        

        
        center_container.add(linea_user_name);
        center_container.add(linea_password);
        
        add(center_container,BorderLayout.CENTER);
        
        
    }
    

    
    
}
