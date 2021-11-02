/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import Clases_BD.Usuario;
import interfaz.Formulario_dialog;
import interfaz.Tabla_dialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class Admin extends AbstractPestana{

    // para el acceso a los componentes
    private JRadioButton radioButton_Select;
    private JRadioButton radioButton_New;
    private JRadioButton radioButton_Update;
    private JRadioButton radioButton_Delete;
    private JComboBox comboTablas;
    
    public Admin(int permiso,TabPanel tab, Usuario usuario) throws SQLException {
        /* cosas padre */
        super(permiso,tab,usuario);
        boton_Edit.addActionListener(new click_operar());
        boton_Logout.addActionListener(new click_Logout());
        
        panelCentro.setLayout(new BoxLayout(panelCentro,BoxLayout.Y_AXIS));
        
        //panel operaciones
        JPanel panelComandos = new JPanel();
        panelComandos.setLayout(new GridLayout(2,3));
        panelCentro.add(panelComandos);
        
        //panel tabla datos
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        panelCentro.add(panelTabla);
        
        //------------- operaciones ----------
        
        /* radioButton */
        // que por defecto sea siempre un select
        radioButton_Select = new JRadioButton("Consultar");
        radioButton_Select.setSelected(true);
        //radioButton_Select.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        radioButton_New = new JRadioButton("Crear ");
        //radioButton_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        radioButton_Update = new JRadioButton("Actualizar");  
        //radioButton_Update.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        radioButton_Delete = new JRadioButton("Borrar");  
        //radioButton_Delete.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        /* grupo de botones */
        ButtonGroup bg = new ButtonGroup();    
        bg.add(radioButton_New);
        bg.add(radioButton_Select);
        bg.add(radioButton_Update);
        bg.add(radioButton_Delete);
              
        comboTablas=new JComboBox(TABLAS);
        //comboTablas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton botonEnviar = new JButton();
        botonEnviar.setText("OPERAR");
        //botonEnviar.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        botonEnviar.addActionListener(new click_operar()); // evento operar
        
        panelComandos.add(radioButton_New);
        panelComandos.add(radioButton_Select);
        panelComandos.add(comboTablas);
        panelComandos.add(radioButton_Update);
        panelComandos.add(radioButton_Delete);
        panelComandos.add(botonEnviar);
        
        //------------- seccion tabla ----------
        
        /* titulo de la tabla */
        // separador
        JPanel panelSeparator = new JPanel();
        panelSeparator.setLayout(new BorderLayout());
        panelSeparator.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel panelTituloTabla = new JPanel();
        panelTituloTabla.setLayout(new FlowLayout());
        
        JLabel nombreLista = new JLabel();
        nombreLista.setText("Últimas conexiones");
        //nombreLista.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelTituloTabla.add(nombreLista);
        
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        panelSeparator.add(panelTituloTabla, BorderLayout.CENTER);
        

        panelTabla.add(panelSeparator, BorderLayout.NORTH);
        
        /* tabla de datos */     
        
        lista_datos = new ArrayList<String[]>();
        /* ----------- CARGAR DATOS AQUI ------------  */
        //AQUI ES UN ArrayList<String[]> DE USUARIOS CON EL INDICE 0 COMO NOMBRE DE LAS CABECERAS
        
        
        
        // SELECT * FROM mensajeria.usuario ORDER BY mensajeria.usuario.fecha_ultima_conection DESC LIMIT 0, 15
        
        //lista_Datos =  <-----  método de carga de datos;
        // ejemplo

        lista_datos = DB.usuarios_conexion(usuario.getId_usuario());
        

        
        // --------------------------------------------------------
        
        DefaultTableModel tableModel = mensajeria_app.Utilidades.ArrayList_to_DefaultTableModel(lista_datos);
        
        JTable tablaConection = new JTable(tableModel);
           
        // solo que se vea
        tablaConection.setEnabled(false);
        //tablaConection.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JScrollPane sp =new JScrollPane(tablaConection);
        
        panelTabla.add(sp, BorderLayout.CENTER);
    }
    
    private class click_operar extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            
            if("Editar_Perfil".equals(source.getName())){
                try {
                    // edit  -> formulario edit id_usuario
                    new Formulario_dialog("Editar perfil", 3, 1, id_user, permisoUser);
                } catch (SQLException ex) {
                    Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                int opcion_Marcada = 1; // para simplificar por defecto edit
                boolean nuevo = false; // para controlar el nuevo registro
                
                if(radioButton_New.isSelected()){
                    // New edit -> formulario edit 0
                    nuevo = true;
                } else if(radioButton_Select.isSelected()){
                    //select -> modal tabla modo 0
                    opcion_Marcada = 0;
                } else if(radioButton_Delete.isSelected()){
                    //  delete -> modal tabla: modo 2                
                    opcion_Marcada = 2;
                } else{
                    // edit -> modal tabla: modo 1
                }

                int tabla = (int)comboTablas.getSelectedIndex();
                
                if (nuevo) {
                    try {
                        new Formulario_dialog("Nuevo", tabla, 1, 0, getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    try {
                        new Tabla_dialog(tabla, opcion_Marcada, getId_user(), getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private class click_Logout extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            // logout
        }
    }
}
