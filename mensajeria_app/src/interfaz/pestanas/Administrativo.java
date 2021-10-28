/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import java.awt.*;
import javax.swing.*;
import mensajeria_app.Graficos;
import org.jfree.chart.ChartPanel;


/**
 *
 * @author Mario
 */
public class Administrativo extends AbstractPestana {

    public Administrativo(int permiso) {
        super(permiso);
        
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
        //boton_Pedidos_Select.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Pedidos_New = new JButton();
        boton_Pedidos_New.setText("Crear Pedidos");
        //boton_Pedidos_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Cliente_New = new JButton();
        boton_Cliente_New.setText("Crear Cliente");
        //boton_Cliente_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_New = new JButton();
        boton_Repartidor_New.setText("Crear Repartidor");
        //boton_Repartidor_New.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        JButton boton_Repartidor_Asignar = new JButton();
        boton_Repartidor_Asignar.setText("Asignar Repartidor");
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
}
