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
public class AbstractPestana extends JPanel {
    // almacenar permiso
    protected int permisoUser;
    //marco inicial
    protected JPanel panelSuperior;
    protected JPanel panelCentro;
    // datoss de usuario
    protected JTextField campo_user_name;
    protected JTextField campo_user_apellido;
    protected JTextField campo_user_mail; 
    protected JTextField campo_user_city;

    
    public AbstractPestana(int permiso){
        // por defecto siempre que sea cliente
        this.permisoUser = permiso;
        
        setLayout(new BorderLayout());
        // temporal
        //setPreferredSize(new Dimension(490, 100));
        
        
        
        /* cabeceras */
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        /* cuerpo */
        panelCentro = new JPanel();
        add(panelCentro, BorderLayout.CENTER);

        
        
        
        /* Hay que definir los tamaños segun se va contruyendo o las imagenes se desbordan por ejemplo  */
        // ----------- superior oeste ---------------
        JLabel imagen = new JLabel();
        /* poner foto genérica no vamos a implementar un cargador de imagenes  */
        Icon imp = new ImageIcon("..\\Recursos\\error.jpg");
        imagen.setIcon(imp);
        // ajustar recursivo
        //imagen.setSize(100, 100);
        panelSuperior.add(imagen, BorderLayout.WEST);

        
        
        
        
        // ------------ Superior este ----------
        /* Comprobar si necesita tb un layout para ordenar los botones, que no los dos en la misma coordenada */

        JPanel panelBotones = new JPanel(new GridLayout(2, 1));
        
        
        JButton botonEdit = new JButton();
        botonEdit.setText("Editar Perfil");
        //botonEdit.addComponentListener(new ActionListener()); // evento editar
        panelBotones.add(botonEdit);

        JButton botonLogout = new JButton();
        botonLogout.setText("Salir");
        panelBotones.add(botonLogout);
        //botonLogout.addComponentListener(new ActionListener()); // evento logout
        
    
        panelSuperior.add(panelBotones, BorderLayout.EAST);


        // --------- centro ---------

        JPanel panelSuperiorDatos = new JPanel();
        
        panelSuperiorDatos.setLayout(new BoxLayout(panelSuperiorDatos, BoxLayout.Y_AXIS));
        /* REVISAR: quizas va mejor un grid de 2,4 */
        

        int tam_textField = 15;  

        JLabel label_user_name = new JLabel("Nombre :   ");       
        campo_user_name=new JTextField(tam_textField);
        campo_user_name.setEditable(false);
        
        
        JPanel panel_user_name = new JPanel(new FlowLayout());
        panel_user_name.add(label_user_name);
        panel_user_name.add(campo_user_name);
        
        
        panelSuperiorDatos.add(panel_user_name);
        
        
        JLabel label_user_apellido = new JLabel("Apellidos :");
        campo_user_apellido=new JTextField(tam_textField);
        campo_user_apellido.setEditable(false);
        
        JPanel panel_user_apellido = new JPanel(new FlowLayout());
        panel_user_apellido.add(label_user_apellido);
        panel_user_apellido.add(campo_user_apellido);
        

        panelSuperiorDatos.add(panel_user_apellido);

     

        JLabel label_user_mail = new JLabel("Correo :    "); 
        campo_user_mail=new JTextField(tam_textField);
        campo_user_mail.setEditable(false);
        
        JPanel panel_user_mail = new JPanel(new FlowLayout());
        panel_user_mail.add(label_user_mail);
        panel_user_mail.add(campo_user_mail);
        
        panelSuperiorDatos.add(panel_user_mail);


        /* ojo que estoy hay que convertirlo: id_provincia -> provincia_nombre */
        JLabel label_user_city = new JLabel("Provincia :");
        campo_user_city=new JTextField(tam_textField);
        campo_user_city.setEditable(false);
        
        
        JPanel panel_user_city = new JPanel(new FlowLayout());
        panel_user_city.add(label_user_city);
        panel_user_city.add(campo_user_city);
        
        
        panelSuperiorDatos.add(panel_user_city);
        
        
        
        
        panelSuperior.add(panelSuperiorDatos);

        // una vez todo montado llamamos a la cargaDatosPermisos
        cargaDatosPermisos();
        
        // -----     panel central -----------

        /* las propiedades y demás para los hijos */
    }

    // getter y setter de permisos, puede ser util si no ya lo quitamos
    public int getPermisoUser() {
        return permisoUser;
    }

    public void setPermisoUser(int permisoUser) {
        this.permisoUser = permisoUser;
    }
    
    public void cargaDatosPermisos(){
        /* esta es la carga de datos para la cabecera */
    }
}
