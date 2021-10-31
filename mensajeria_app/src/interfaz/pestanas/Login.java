/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import Clases_BD.Usuario;
import Eventos.Event_Boton_Personalizado;
import interfaz.Formulario_dialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static java.lang.Thread.sleep;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import mensajeria_app.Controller_pedidos;

/**
 *
 * @author Mario
 */
public class Login extends JPanel {
    
    private JTextField campo_user_name;
    private JPasswordField campo_password;
    private TabPanel tab;
    


    public Login(TabPanel tab) {
        

        
        this.tab = tab;
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel center_container = new JPanel();       
        center_container.setLayout(new GridLayout(3, 1));
        

        
        // panel del login
        JPanel north_container = new JPanel(new FlowLayout());
        // panel del boton
        JPanel south_container = new JPanel(new FlowLayout());
        
        // paneles formularios
        JPanel linea_user_name = new JPanel(new FlowLayout());
        JPanel linea_password= new JPanel(new FlowLayout());
        
        // para obligar a cargar un espacio en lso textfield
        int tam_textField = 15;          

        JLabel titulo = new JLabel("LOGIN");
        //titulo.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JLabel label_usuario = new JLabel("Usuario :   ");
        //label_usuario.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        linea_user_name.add(label_usuario);
        
        campo_user_name=new JTextField(tam_textField);
        campo_user_name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                String emailStr = campo_user_name.getText();
                
                Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
                if(matcher.find()){
                    campo_password.setEditable(true);
                } else{
                    JOptionPane.showMessageDialog(null, "Debes introducir un email correcto.", "Error", JOptionPane.ERROR_MESSAGE);
                    campo_user_name.setText(" ");
                    campo_password.setEditable(false); 
                }
            }
        });
        //campo_user_name.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        linea_user_name.add(campo_user_name);

        JLabel label_password = new JLabel("Contraseña :");       
        //label_password.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        linea_password.add(label_password);
        
        campo_password=new JPasswordField(tam_textField);
        campo_password.setEditable(false); 
        //campo_password.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        linea_password.add(campo_password);

        JButton botonEnviar = new JButton();
        
        botonEnviar.addActionListener(new click_acceder());
        
        botonEnviar.setText("Acceder");
        //botonEnviar.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //botonLogout.addComponentListener(new ActionListener()); // evento sing in

        north_container.add(titulo);
        
        center_container.add(north_container);
        center_container.add(linea_user_name);
        center_container.add(linea_password);

        south_container.add(botonEnviar);
     

        
        add(north_container);
        add(center_container);
        add(south_container);
    }
    
    
    private class click_acceder extends Event_Boton_Personalizado{
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            //COMPROBAR EXISTENCIA
            
            Controller_pedidos DB = new Controller_pedidos();
            
            
            try {
                Usuario usuario_login = DB.Login(campo_user_name.getText(), campo_password.getText());
                
                
                if (usuario_login != null  ){
                    

                    tab.setEnabledAt(0, false);

                    int pestana_redirigida = 1;
                    
                    System.out.println(usuario_login.getPermisos());
                    
                    switch (usuario_login.getPermisos()){
                        
                        case 0:
                            
                            tab.addTab("Cliente", new Cliente_Repartidor(0, tab,usuario_login));
                        
                            break;
                        case 1:
                            tab.addTab("Repartidor", new Cliente_Repartidor(1, tab,usuario_login));
                            break;                         
                        case 2:
                            tab.addTab("Administrativo", new Administrativo(2, tab,usuario_login));
                            break;                        
                        case 3:
                            tab.addTab("Admin", new Admin(3, tab,usuario_login));
                            break;                        
                    }
                    
                    tab.setSelectedIndex(pestana_redirigida);
    
                    
                    

                    
                   
                    
                    tab.setUsuario_logueado(usuario_login); 
                }else  {
                    
                    
                    JOptionPane.showMessageDialog(null,"No se ha encontrado  al usuario");
                }
                
                

            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            // OBTENER PERMISO Y PASARLO
            

            
            
            //pestana.setId_user(1);
        }

    }
}
