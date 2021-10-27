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
        setPreferredSize(new Dimension(490, 100));
        
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
        Icon imp = new ImageIcon("D:/ic_email.png");
        imagen.setIcon(imp);
        panelSuperior.add(imagen, BorderLayout.WEST);

        // ------------ Superior este ----------
        /* Comprobar si necesita tb un layout para ordenar los botones, que no los dos en la misma coordenada */

        JButton botonEdit = new JButton();
        botonEdit.setText("Editar Perfil");
        //botonEdit.addComponentListener(new ActionListener()); // evento editar
        panelSuperior.add(botonEdit, BorderLayout.EAST);

        JButton botonLogout = new JButton();
        botonLogout.setText("Salir");
        //botonLogout.addComponentListener(new ActionListener()); // evento logout
        panelSuperior.add(botonLogout, BorderLayout.EAST);

        // --------- centro ---------

        JPanel panelSuperiorDatos = new JPanel();
        /* REVISAR: quizas va mejor un grid de 2,4 */
        panelSuperiorDatos.setLayout(new FlowLayout());        

        JLabel label_user_name = new JLabel("Nombre :");       
        panelSuperiorDatos.add(label_user_name);
        
        campo_user_name=new JTextField();
        panelSuperiorDatos.add(campo_user_name);

        JLabel label_user_apellido = new JLabel("Apellidos :");       
        panelSuperiorDatos.add(label_user_apellido);
        
        campo_user_apellido=new JTextField();
        panelSuperiorDatos.add(campo_user_apellido);

        JLabel label_user_mail = new JLabel("Correo :");       
        panelSuperiorDatos.add(label_user_mail);
        
        campo_user_mail=new JTextField();
        panelSuperiorDatos.add(campo_user_mail);

        /* ojo que estoy hay que convertirlo: id_provincia -> provincia_nombre */

        JLabel label_user_city = new JLabel("Provincia :");       
        panelSuperiorDatos.add(label_user_city);
        
        campo_user_city=new JTextField();
        panelSuperiorDatos.add(campo_user_city);
        
        panelSuperior.add(panelSuperiorDatos);

        // una vez todo montado llamamos a la cargaDatosPermisos
        cargaDatosPermisos();
        
        // -----     panel central -----------

        JPanel panelCentral = new JPanel();

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
