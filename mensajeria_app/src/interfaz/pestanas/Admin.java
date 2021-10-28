/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class Admin extends AbstractPestana{

    JRadioButton radioButton_Select;
    JRadioButton radioButton_New;
    JRadioButton radioButton_Update;
    JRadioButton radioButton_Delete;
    
    public Admin(int permiso) {
        super(permiso);
        
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
        
        // aqui van las tablas que usamos
        String tablas[]={"Articulos","Pedidos","Provincias","Usuarios"};        
        JComboBox comboTablas=new JComboBox(tablas);
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
        panelSeparator.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        JPanel panelTituloTabla = new JPanel();
        panelTituloTabla.setLayout(new FlowLayout());
        
        JLabel nombreLista = new JLabel();
        nombreLista.setText("�ltimas conexiones");
        //nombreLista.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelTituloTabla.add(nombreLista);
        
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
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
        
        panelTabla.add(tablaConection, BorderLayout.CENTER);
        
    }
    
    private class click_operar extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            //JOptionPane.showMessageDialog(null, "funciona", "Error", JOptionPane.PLAIN_MESSAGE);
            int opcion_Marcada = 0;
            if(radioButton_New.isSelected()){
                // edit 0
                opcion_Marcada = 1;
            }
            if(radioButton_Select.isSelected()){
                
                opcion_Marcada = 2;
            }
            if(radioButton_Update.isSelected()){
                opcion_Marcada = 3;
            }
            if(radioButton_New.isSelected()){
                opcion_Marcada = 4;
            }
        }
    }
}
