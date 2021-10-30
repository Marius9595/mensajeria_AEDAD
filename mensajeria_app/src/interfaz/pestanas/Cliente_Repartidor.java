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
import javax.swing.*;

/**
 *
 * @author Mario
 */
public class Cliente_Repartidor extends AbstractPestana {
    
    private JPanel panelCentralNorte;
    private JPanel panelCentralCentro;
    private JPanel panelCentralSur;
    
    public Cliente_Repartidor(int permiso, TabPanel tab,Usuario usuario) throws SQLException {
        /* cosas padre */
        super(permiso,tab,usuario);
        boton_Edit.addActionListener(new click_operar());
        //boton_Logout.addActionListener(new click_Logout(super.permisoUser+1));
        
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
        
        lista_datos = new ArrayList<>();
        /* ----------- CARGAR DATOS AQUI ------------  */
        // EN AMBOS CASOS DEBERIAMOS TENER SOLO UN ArrayList<String[]>  CON ARTICULOS
        
        if(super.permisoUser == 1){
            // SELECT art.* from mensajeria.articulo AS art JOIN mensajeria.pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_repartidor = <<super.id_user>> AND ped.fecha_entrega IS NULL ORDER BY art.id_articulo ASC;
        } else{
            // SELECT art.* from mensajeria.articulo AS art JOIN mensajeria.pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_cliente = <<super.id_user>> ORDER BY ped.fecha_entrega DESC LIMIT 15;
        }
        // ejemplo
        for (int i = 0; i < 15; i++) {
            String[] cargaEjemplo = {String.valueOf(i), "Valor " +1};
            lista_datos.add(cargaEjemplo);
        }
        
        
        DefaultListModel modelo = new DefaultListModel();
        // id artículo
        // nombre articulo
        for(String[] dat : lista_datos){
            modelo.addElement("Pedido id: " + dat[0] + ", Descripción: " + dat[1]);
        }

        listaPedidos.setModel(modelo);
        
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
            
            switch(source.getName()){
                case "Editar_Perfil":
                    // edit -> modal formulario: edit tabla usuario, id_usuario, permisos
                    new Formulario_dialog("Editar perfil", 3, 2, super.id_usuario, super.permisos);
                    break;
                case "New_Pedido":
                    // new edit -> modal formulario: edit tabla pedidos, 0, permisos
                    new Formulario_dialog("Nuevo pedido", 1, 1, 0, super.permisos);
                    break;
                default:
                    // modal tabla: tabla pedidos, modo 0, id_usuario, permiso
                    new Tabla_dialog(1, 0, super.id_usuario, super.permisos);
            }
        }
    }
}
