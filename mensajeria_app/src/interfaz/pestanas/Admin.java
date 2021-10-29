/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import interfaz.Tabla_dialog;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

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
    
    public Admin(int permiso) {
        /* cosas padre */
        super(permiso);
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
        // deberian sser 3 o 5 lo que se vea en el frame
        String data[][]={ {"101","Amit","670000"},    // los datos
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    
        String column[]={"ID","NAME","SALARY"};    // nombre columnas      
        
        JTable tablaConection=new JTable(data,column);    
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
                // New edit -> formulario edit id_usuario
                JOptionPane.showMessageDialog(null, "Editando", "tester", JOptionPane.PLAIN_MESSAGE);
            } else {
                int opcion_Marcada = -1;
                if(radioButton_New.isSelected()){
                    // New edit -> formulario edit 0
                    opcion_Marcada = 1;
                }
                if(radioButton_Select.isSelected()){
                    //select -> modal tabla modo 0
                    opcion_Marcada = 0;
                }
                if(radioButton_Update.isSelected()){
                    // edit -> modal tabla: modo 1
                    opcion_Marcada = 1;
                }
                if(radioButton_Delete.isSelected()){
                    //  delete -> modal tabla: modo 2                
                    opcion_Marcada = 2;
                }

                // va en este orden
                // Articuloss, Pedidos, Provincias, Usuarios
                int tabla = (int)comboTablas.getSelectedIndex();

                // pruebas
                //JOptionPane.showMessageDialog(null, "funciona: " + opcion_Marcada + ", " + tabla , "Tester", JOptionPane.PLAIN_MESSAGE);

                new Tabla_dialog(tabla, opcion_Marcada, super.id_usuario, super.permisos);
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
