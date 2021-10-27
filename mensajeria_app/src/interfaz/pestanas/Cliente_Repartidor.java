/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import Clases_BD.Pedido;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mario
 */
public class Cliente_Repartidor extends AbstractPestana {
    
    private JPanel panelCentralNorte;
    private JPanel panelCentralCentro;
    private JPanel panelCentralSur;
    
    public Cliente_Repartidor(int permiso) {
        // todo lo del padre
        super(permiso);
        
        // esto es el "body" que cambia en los hijos     
        panelCentro.setLayout(new BorderLayout());
        
        // los paneles
        panelCentralNorte = new JPanel();
        panelCentralNorte.setLayout(new BorderLayout());
        panelCentro.add(panelCentralNorte, BorderLayout.NORTH);

        panelCentralCentro = new JPanel();
        panelCentralCentro.setLayout(new BorderLayout());
        panelCentro.add(panelCentralCentro, BorderLayout.CENTER);

        panelCentralSur = new JPanel();
        panelCentralSur.setLayout(new GridBagLayout());
        panelCentro.add(panelCentralSur, BorderLayout.SOUTH);
        
        // -------  norte ----------
        JPanel panelLabelTituloLista = new JPanel();
        panelLabelTituloLista.setLayout(new GridBagLayout());
        
        JLabel nombreLista = new JLabel();
        
        if(permisoUser == 0)
            nombreLista.setText("Últimos pedidos");
        else
            nombreLista.setText("Peidos pendientes");
        
        panelLabelTituloLista.add(nombreLista);
        panelCentralNorte.add(panelLabelTituloLista);
        
        // ---------  centro ----------- 
        JList listaPedidos = new JList<>();
        /* solicitar query a manager:
        if permiso 1 solicitar 3-5 ultimos pedidos pendientes 
        elsse -> 3-5 ultimos pedidos del user
        */
        DefaultListModel modelo = new DefaultListModel();
        // carga de elementos en la lista iteración
        modelo.addElement("Elemento1");
        modelo.addElement("Elemento2");
        modelo.addElement("Elemento3");
        listaPedidos.setModel(modelo);
        /* cosas qeu pueden ser utiles sino descartar*/
        //listaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION ); // <- solo marque 1 para luego inssertar un escucha
        //listaPedidos.addListSelectionListener(new ListSelectionListener()); // <- escuha para el cambio de selector
        
        // no queremos que marque nada solo que sea visual
        listaPedidos.setEnabled(false);
        listaPedidos.setPreferredSize(new Dimension(100,100));
        
        panelCentralCentro.add(listaPedidos);

        
        // ------- sur ---------
        JButton botonConsultarPedido = new JButton();
        botonConsultarPedido.setText("Consultar Pedidos");
        //botonEdit.addComponentListener(new ActionListener()); // evento editar
        panelCentralSur.add(botonConsultarPedido);
        
        // solo si es cliente
        if(permisoUser == 0){
            JButton botonCrearPedido = new JButton();
            botonCrearPedido.setText("Realizar Pedidos");
            //botonLogout.addComponentListener(new ActionListener()); // evento logout
            panelCentralSur.add(botonCrearPedido);// evento editar
        }
    }
}
