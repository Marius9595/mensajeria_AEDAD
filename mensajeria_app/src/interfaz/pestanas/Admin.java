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
public class Admin extends AbstractPestana{

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
        JRadioButton radioButton_Select = new JRadioButton("Consultar");
        radioButton_Select.setSelected(true);
        JRadioButton radioButton_New = new JRadioButton("Crear "); 
        JRadioButton radioButton_Update = new JRadioButton("Actualizar");    
        JRadioButton radioButton_Delete = new JRadioButton("Borrar");  
        // faltan las escuchas
        
        /* grupo de botones */
        ButtonGroup bg = new ButtonGroup();    
        bg.add(radioButton_New);
        bg.add(radioButton_Select);
        bg.add(radioButton_Update);
        bg.add(radioButton_Delete);
        
        
        
        // aqui van las tablas que usamos
        String tablas[]={"Articulos","Pedidos","Provincias","Usuarios"};        
        JComboBox comboTablas=new JComboBox(tablas);    
        
        JButton botonEnviar = new JButton();
        botonEnviar.setText("OPERAR");
        //botonEdit.addComponentListener(new ActionListener()); // evento operar
        
        panelComandos.add(radioButton_New);
        panelComandos.add(radioButton_Select);
        panelComandos.add(comboTablas);
        panelComandos.add(radioButton_Update);
        panelComandos.add(radioButton_Delete);
        panelComandos.add(botonEnviar);
        
        //------------- seccion tabla ----------
        
        /* titulo de la tabla */
        JPanel panelTituloTabla = new JPanel();
        panelTituloTabla.setLayout(new GridBagLayout());
        
        JLabel nombreLista = new JLabel();
        nombreLista.setText("Últimos conexiones");
        
        panelTabla.add(panelTituloTabla, BorderLayout.NORTH);
        
        /* tabla de datos */
        String data[][]={ {"101","Amit","670000"},    // los datos
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};    
        String column[]={"ID","NAME","SALARY"};    // nombre columnas      
        
        JTable tablaConection=new JTable(data,column);    
        // solo que se vea
        tablaConection.setEnabled(false);
        
        panelTabla.add(tablaConection, BorderLayout.CENTER);
        
    }
}
