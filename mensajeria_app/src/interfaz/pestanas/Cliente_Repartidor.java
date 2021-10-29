/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import interfaz.Formulario_dialog;
import interfaz.Tabla_dialog;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class Cliente_Repartidor extends AbstractPestana {
    
    private JPanel panelCentralNorte;
    private JPanel panelCentralCentro;
    private JPanel panelCentralSur;
    
    public Cliente_Repartidor(int permiso, TabPanel tab) {
        /* cosas padre */
        super(permiso,tab);
        boton_Edit.addActionListener(new click_operar());
        boton_Logout.addActionListener(new click_Logout());
        
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
        // separador
        JPanel panelSeparator = new JPanel();
        panelSeparator.setLayout(new BorderLayout());
        panelSeparator.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        
        JPanel panelLabelTituloLista = new JPanel();
        panelLabelTituloLista.setLayout(new GridBagLayout());
        
        JLabel nombreLista = new JLabel();
        //nombreLista.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        if(permisoUser == 0)
            nombreLista.setText("Últimos pedidos");
        else
            nombreLista.setText("Peidos pendientes");
        
        
        panelLabelTituloLista.add(nombreLista);
        
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        panelSeparator.add(panelLabelTituloLista, BorderLayout.CENTER);
        panelCentralNorte.add(panelSeparator);
        
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
        botonConsultarPedido.setName("Select_pedidos");
        //botonConsultarPedido.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        botonConsultarPedido.addActionListener(new click_operar()); // evento editar
        panelCentralSur.add(botonConsultarPedido);
        
        // solo si es cliente
        if(permisoUser == 0){
            JButton botonCrearPedido = new JButton();
            botonCrearPedido.setText("Realizar Pedidos");
            botonCrearPedido.setName("New_Pedido");
            botonCrearPedido.addActionListener(new click_operar()); // evento logout
            panelCentralSur.add(botonCrearPedido);// evento editar
        }
    }
    
    private class click_operar extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            
            if("Editar_Perfil".equals(source.getName())){
                // New edit -> formulario edit id_usuario
                JOptionPane.showMessageDialog(null, "Editando", "tester", JOptionPane.PLAIN_MESSAGE);
            } else {
                if("New_Pedido".equals(source.getName())){
                    // new edit -> formulario edit usuario, id_usuario = 0
                    
                    //query datos -> HashMap -> new forumalrio
                    
                    new Formulario_dialog("Nuevo pedido", null, false);
                } else{
                    // modal tabla: tabla pedidos, modo 0, id_usuario, permiso
                    new Tabla_dialog(1, 0, super.id_usuario, super.permisos);
                } 
            }
        }
    }
    
    private class click_Logout extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
        }
    }
}
