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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mensajeria_app.Graficos;
import org.jfree.chart.ChartPanel;


/**
 *
 * @author Mario
 */
public class Administrativo extends AbstractPestana {

    /**
     * Constante OPCIONES[] = {"Select_Pedidos", "New_Pedidos", "New_Cliente", "New_Repartidor", "Edit_Pedidos"}
     */
    private final String OPCIONES[] = {"Select_Pedidos", "New_Pedidos", "New_Cliente", "New_Repartidor", "Edit_Pedidos"};
    
    public Administrativo(int permiso,TabPanel tab,Usuario usuario) throws SQLException {
        /* cosas padre */
        super(permiso,tab,usuario);
        boton_Edit.addActionListener(new click_operar());
       
        
        panelCentro.setLayout(new BorderLayout());      
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1));
        
        JPanel panelGraficos = new JPanel();
        panelGraficos.setLayout(new BorderLayout());
        
        // panel grafico norte
        JPanel panelGraficosNorte = new JPanel();
        
        // paneles grafico centro-oeste y centro-este
        JPanel panelGraficosCentro = new JPanel();
        panelGraficosCentro.setLayout(new GridLayout(1, 2));
        
        panelGraficos.add(panelGraficosNorte, BorderLayout.NORTH);
        panelGraficos.add(panelGraficosCentro, BorderLayout.CENTER);
        
        panelCentro.add(panelBotones, BorderLayout.WEST);
        panelCentro.add(panelGraficos, BorderLayout.CENTER);
        
        
        
        //------------ panel botones -----------------
        
        // añadir padding a los botones -> el pading no lo coge, seguramente es por el pack
        //label.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        
        JButton boton_Pedidos_Select = new JButton();
        boton_Pedidos_Select.setText("Consultar Pedidos");
        boton_Pedidos_Select.setName(OPCIONES[0]);
        boton_Pedidos_Select.addActionListener(new click_operar());
        //boton_Pedidos_Select.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Pedidos_New = new JButton();
        boton_Pedidos_New.setText("Crear Pedidos");
        boton_Pedidos_New.setName(OPCIONES[1]);
        boton_Pedidos_New.addActionListener(new click_operar());
        //boton_Pedidos_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Cliente_New = new JButton();
        boton_Cliente_New.setText("Crear Cliente");
        boton_Cliente_New.setName(OPCIONES[2]);
        boton_Cliente_New.addActionListener(new click_operar());
        //boton_Cliente_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_New = new JButton();
        boton_Repartidor_New.setText("Crear Repartidor");
        boton_Repartidor_New.setName(OPCIONES[3]);
        boton_Repartidor_New.addActionListener(new click_operar());
        //boton_Repartidor_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_Asignar = new JButton();
        boton_Repartidor_Asignar.setText("Asignar Repartidor");
        boton_Repartidor_Asignar.setName(OPCIONES[4]);
        boton_Repartidor_Asignar.addActionListener(new click_operar());
        //boton_Repartidor_Asignar.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelBotones.add(boton_Pedidos_Select);
        panelBotones.add(boton_Pedidos_New);
        panelBotones.add(boton_Cliente_New);
        panelBotones.add(boton_Repartidor_New);
        panelBotones.add(boton_Repartidor_Asignar);
        
        /* RECORDATORIO: hay que personalizar las gráficas */
        
        // ------------- panel graficos norte ----------
        
        // panel con el grafico de barras acostado
        
        ChartPanel chartPanel_Barras = Graficos.grafica_Barras("horizontal");
        chartPanel_Barras.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel_Barras.setBackground(Color.white);
        chartPanel_Barras.setPreferredSize(new Dimension(300, 100)); // ajustar tamaño
        panelGraficosNorte.add(chartPanel_Barras);

        
        //--------- panel graficos centro - oeste ------
        
        // panel con el grafico de rayas enfrentadas (las dos líneas)
        
        ChartPanel chartPanel_Líneas = Graficos.grafica_Rayas(2, "vertical");
        chartPanel_Líneas.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel_Líneas.setBackground(Color.white);
        chartPanel_Líneas.setPreferredSize(new Dimension(50, 100)); // ajustar tamaño
        panelGraficosCentro.add(chartPanel_Líneas);
        
        
        //--------- panel graficos centro - este -------
        
        // panel con el gráfico de quesito
        
        ChartPanel chartPanel_Queso = Graficos.grafica_Queso();
        chartPanel_Queso.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel_Queso.setBackground(Color.white);
        chartPanel_Queso.setPreferredSize(new Dimension(50, 100)); // ajustar tamaño
        panelGraficosCentro.add(chartPanel_Queso);
        
    }
    
    private class click_operar extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            
            if("Editar_Perfil".equals(source.getName())){
                try {
                    // edit  -> formulario edit id_usuario
                    new Formulario_dialog("Editar perfil", 3, 2, getId_user(), getPermisoUser());
                } catch (SQLException ex) {
                    Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if(OPCIONES[0].equals(source.getName()) ){
                    try {
                        // select pedidos -> modal tabla: select tabla pedidos
                        new Tabla_dialog(1, 0, getId_user(), getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(OPCIONES[1].equals(source.getName())){
                    try {
                        // new pedido -> mandar a modal formulario: edit tabla pedidos, id_pedido = 0
                        new Formulario_dialog("Nuevo pedido", 1, 1, 0, getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(OPCIONES[2].equals(source.getName())){
                    try {
                        // new user 0 -> mandar a modal formulario: edit tabla user, id_user = 0, permisos = 0
                        new Formulario_dialog("Nuevo Cliente", 3, 1, 0, getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(OPCIONES[3].equals(source.getName())){
                    try {
                        // new user 0 -> mandar a modal formulario: edit tabla user, id_user = 0, permisos = 1
                        new Formulario_dialog("Nuevo Repartidor", 3, 1, 0, getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(OPCIONES[4].equals(source.getName())){
                    try {
                        // select pedidos -> modal tabla: edit tabla pedidos, no repartidor
                        new Tabla_dialog(1, 3, getId_user(), getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrativo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "No deberias estar viendo esto....", "Error", JOptionPane.ERROR_MESSAGE);   
                }
            }
        }
    }
    

}
