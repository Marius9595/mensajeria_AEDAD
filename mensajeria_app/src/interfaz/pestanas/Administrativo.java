/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import interfaz.Formulario_dialog;
import interfaz.Tabla_dialog;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    
    public Administrativo(int permiso,TabPanel tab) {
        /* cosas padre */
        super(permiso,tab);
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
        //boton_Pedidos_Select.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Pedidos_New = new JButton();
        boton_Pedidos_New.setText("Crear Pedidos");
        boton_Pedidos_New.setName(OPCIONES[1]);
        //boton_Pedidos_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Cliente_New = new JButton();
        boton_Cliente_New.setText("Crear Cliente");
        boton_Cliente_New.setName(OPCIONES[2]);
        //boton_Cliente_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_New = new JButton();
        boton_Repartidor_New.setText("Crear Repartidor");
        boton_Repartidor_New.setName(OPCIONES[3]);
        //boton_Repartidor_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_Asignar = new JButton();
        boton_Repartidor_Asignar.setText("Asignar Repartidor");
        boton_Repartidor_Asignar.setName(OPCIONES[4]);
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
                // edit  -> formulario edit id_usuario
                new Formulario_dialog("Editar perfil", 3, 2, super.id_usuario, super.permisos);
            } else {
                int opcion_Marcada = -1;
                int tabla = 1;
                if(OPCIONES[0].equals(source.getText()) ){
                    // select pedidos -> modal tabla: select tabla pedidos
                    opcion_Marcada = 0;
                }
                if(OPCIONES[1].equals(source.getText())){
                    // new pedido -> mandar a modal formulario: edit tabla pedidos, id_pedido = 0                   
                    opcion_Marcada = 1;
                    // edit  -> formulario edit id_usuario
                    new Formulario_dialog("Nuevo pedido", 1, 1, 0, super.permisos);
                }
                if(OPCIONES[2].equals(source.getText())){
                    // new user 0 -> mandar a modal formulario: edit tabla user, id_user = 0, permisos = 0
                    opcion_Marcada = 2;
                }
                if(OPCIONES[3].equals(source.getText())){
                    // new user 0 -> mandar a modal formulario: edit tabla user, id_user = 0, permisos = 1
                    opcion_Marcada = 3;
                }
                if(OPCIONES[4].equals(source.getText())){
                    // select pedidos -> modal tabla: edit tabla pedidos, no repartidor
                    opcion_Marcada = 3;
                }

                // pruebas
                //JOptionPane.showMessageDialog(null, "funciona: " + opcion_Marcada + ", " + tabla , "Tester", JOptionPane.PLAIN_MESSAGE);

                new Tabla_dialog(tabla, opcion_Marcada, super.id_usuario, super.permisos); 
            }
        }
    }
    

}
