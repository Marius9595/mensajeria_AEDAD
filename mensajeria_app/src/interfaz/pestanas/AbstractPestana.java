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
    /**
     * Constante MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"}
     */
    protected final String MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"};
    /**
     * Constante TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"}
     */
    protected final String TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"};     
    
    // cossas usuarios
    protected int permisoUser;
    protected int id_user;
    
    //Cabecera común
    private JPanel panelSuperior;
    
    /**
     * JPanel Cuerpo central. Aqui es donde va el contenido de lso hijos
     */
    protected JPanel panelCentro;
    
    // datos de la cabecera
    private JTextField campo_user_name;
    private JTextField campo_user_apellido;
    private JTextField campo_user_mail; 
    private JTextField campo_user_city;

    protected JButton boton_Edit;
    protected JButton boton_Logout;
    
    public AbstractPestana(int permiso){
        // por defecto siempre que sea cliente
        this.permisoUser = permiso;
        
        setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(490, 100));
        
        
        
        /* cabeceras */
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        
        /* cuerpo */
        // esto es el separador
        JPanel panelSeparator = new JPanel();
        panelSeparator.setLayout(new BorderLayout());
        panelSeparator.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        panelCentro = new JPanel(); // <-- este es el contenido de los hijos
        
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        panelSeparator.add(panelCentro, BorderLayout.CENTER);
        
        add(panelSeparator, BorderLayout.CENTER);
        
        
        /* Hay que definir los tamaños segun se va contruyendo o las imagenes se desbordan por ejemplo  */
        // ----------- superior oeste ---------------
        JLabel imagen = new JLabel();
        /* poner foto genérica no vamos a implementar un cargador de imagenes  */
        Icon imp = new ImageIcon("..\\Recursos\\error.jpg");
        imagen.setIcon(imp);
        
        // ajustar recursivo
        //imagen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelSuperior.add(imagen, BorderLayout.WEST);

        
        // ------------ Superior este ----------

        JPanel panelBotones = new JPanel(new GridLayout(2, 1));
        
        
        boton_Edit = new JButton();
        boton_Edit.setText("Editar Perfil");
        boton_Edit.setName("Editar_Perfil");        
        //botonEdit.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelBotones.add(boton_Edit);

        boton_Logout = new JButton();
        boton_Logout.setText("Salir");
        boton_Logout.setName("Log_out");        
        //botonLogout.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelBotones.add(boton_Logout);
        
        
        panelSuperior.add(panelBotones, BorderLayout.EAST);


        // --------- centro ---------

        JPanel panelSuperiorDatos = new JPanel();
        
        panelSuperiorDatos.setLayout(new BoxLayout(panelSuperiorDatos, BoxLayout.Y_AXIS));

        int tam_textField = 15;  

        JLabel label_user_name = new JLabel("Nombre :   ");  
        //label_user_name.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        campo_user_name=new JTextField(tam_textField);
        campo_user_name.setEditable(false);
        //campo_user_name.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JPanel panel_user_name = new JPanel(new FlowLayout());
        panel_user_name.add(label_user_name);
        panel_user_name.add(campo_user_name);
        //imagen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelSuperiorDatos.add(panel_user_name);
        
        
        JLabel label_user_apellido = new JLabel("Apellidos :");
        //label_user_apellido.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        campo_user_apellido=new JTextField(tam_textField);
        campo_user_apellido.setEditable(false);
        //campo_user_apellido.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JPanel panel_user_apellido = new JPanel(new FlowLayout());
        
        panel_user_apellido.add(label_user_apellido);
        panel_user_apellido.add(campo_user_apellido);        

        panelSuperiorDatos.add(panel_user_apellido);
     

        JLabel label_user_mail = new JLabel("Correo :    "); 
        //label_user_mail.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        campo_user_mail=new JTextField(tam_textField);
        campo_user_mail.setEditable(false);
        //campo_user_mail.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JPanel panel_user_mail = new JPanel(new FlowLayout());
        
        panel_user_mail.add(label_user_mail);
        panel_user_mail.add(campo_user_mail);
        
        panelSuperiorDatos.add(panel_user_mail);

        /* ojo que estoy hay que convertirlo: id_provincia -> provincia_nombre */
        JLabel label_user_city = new JLabel("Provincia :");
        //label_user_city.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        campo_user_city=new JTextField(tam_textField);
        campo_user_city.setEditable(false);
        //campo_user_city.setPreferredSize(new Dimension(WIDTH, HEIGHT));      
        
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

    /**
     * getter del permiso del usuario almacenado en el frame
     * @return int con el permiso
     */
    public int getPermisoUser() {
        return permisoUser;
    }

    /**
     * setter del permiso del usuario almacenado en el frame
     * @param permisoUser int con el permiso
     */
    public void setPermisoUser(int permisoUser) {
        this.permisoUser = permisoUser;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    /**
     * es el método qeu carga los datos en el formulario de cabecera
     */
    public void cargaDatosPermisos(){
        // cargador de datos
        
        campo_user_name.setText("Pepe");
        campo_user_apellido.setText("Apellido1 Apellido2");
        campo_user_mail.setText("mail@dominio.com");
        campo_user_city.setText("Santa Cruz de Tenerife");
    }
}
