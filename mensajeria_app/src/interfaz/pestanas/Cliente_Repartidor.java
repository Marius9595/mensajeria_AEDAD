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
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * JPanel de los clientes/repartidores
 * @author Jonathan
 * @author Mario
 */
public class Cliente_Repartidor extends AbstractPestana {

    private JPanel panelCentralNorte;
    private JPanel panelCentralCentro;
    private JPanel panelCentralSur;

    public Cliente_Repartidor(int permiso, TabPanel tab, Usuario usuario) throws SQLException {
        /* cosas padre */
        super(permiso, tab, usuario);
        boton_Edit.addActionListener(new click_operar());

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

        if (permisoUser == 0) {
            nombreLista.setText("Últimos pedidos");
        } else {
            nombreLista.setText("Peidos pendientes");
        }

        panelLabelTituloLista.add(nombreLista);

        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        panelSeparator.add(panelLabelTituloLista, BorderLayout.CENTER);
        panelCentralNorte.add(panelSeparator);

        // ---------  centro ----------- 
        JList listaPedidos = new JList<>();

        lista_datos = new ArrayList<String[]>();
        /* ----------- CARGAR DATOS AQUI ------------  */
        // EN AMBOS CASOS DEBERIAMOS TENER SOLO UN ArrayList<String[]>  CON ARTICULOS

        lista_datos = DB.lista_pedidos(usuario.getId_usuario(), super.permisoUser);

        System.out.println(lista_datos);

        DefaultListModel modelo = new DefaultListModel();
        // id artículo
        // nombre articulo
        for (String[] dat : lista_datos) {

            modelo.addElement("Pedido id: " + dat[0] + ", Descripción: " + dat[1]);
        }

        listaPedidos.setModel(modelo);

        listaPedidos.setPreferredSize(new Dimension(100, 100));

        panelCentralCentro.add(listaPedidos);

        // ------- sur ---------
        JButton botonConsultarPedido = new JButton();
        botonConsultarPedido.setText("Consultar Pedidos");
        botonConsultarPedido.setName("Select_pedidos");
        botonConsultarPedido.addActionListener(new click_operar()); // evento 
        panelCentralSur.add(botonConsultarPedido);

        // solo si es cliente
        if (permisoUser == 0) {
            JButton botonCrearPedido = new JButton();
            botonCrearPedido.setText("Realizar Pedidos");
            botonCrearPedido.setName("New_Pedido");
            botonCrearPedido.addActionListener(new click_operar()); // evento 
            panelCentralSur.add(botonCrearPedido);// evento editar
        }
    }

    private class click_operar extends Eventos.Event_Boton_Personalizado {

        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();

            switch (source.getName()) {
                case "Editar_Perfil": {
                    try {
                        // edit -> modal formulario: edit tabla usuario, id_usuario, permisos
                        new Formulario_dialog("Editar perfil", 3, 1, id_user, permisoUser);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cliente_Repartidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                case "New_Pedido": {
                    try {
                        // new edit -> modal formulario: edit tabla pedidos, 0, permisos
                        new Formulario_dialog("Nuevo pedido", 1, 1, 0, getPermisoUser());
                    } catch (SQLException ex) {
                        Logger.getLogger(Cliente_Repartidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                default: {
                    try {
                        // modal tabla: tabla pedidos, modo 0, id_usuario, permiso
                        new Tabla_dialog(1, 0, getId_user(), getPermisoUser());

                        // para saber cual eliminar : System.out.println("metodo:  " + getId_user() + " " + getId_usuario() + " "+ super.id_usuario);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cliente_Repartidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
