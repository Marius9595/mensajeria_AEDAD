/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mensajeria_app.Controller_pedidos;

/**
 *
 * @author Mario
 */
public class Formulario_dialog extends JFrame implements ActionListener{
    
   
    
    private JPanel container_pricipal = new JPanel();

    /**
     * Constante MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar Repartidor"}
     */
    protected final String MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar Repartidor"};
    /**
     * Constante TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"}
     */
    protected final String TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"};
    /**
     * Constante TIPOS_USUARIOS[] = {"Cliente","Repartidor","Administrativo","Admin"}
     */
    protected final String TIPOS_USUARIOS[] = {"Cliente","Repartidor","Administrativo","Admin"};
    
    
    private Controller_pedidos DB =new Controller_pedidos();
    
    /**
     * Contructor: 
     * TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"},
     * MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"}
     * @param titulo
     * @param tabla
     * @param modo
     * @param id_consulta
     * @param permiso 
     */
    public Formulario_dialog(String titulo, int tabla, int modo, int id_consulta, int permiso ) throws SQLException  {
        
        // ya no se me ocurrió una manera de diferenciar si es cliente o repartidor el nuevo usuario
        boolean nuevo_cliente = false;
        if("Nuevo Cliente".equals(titulo))
            nuevo_cliente = true;
            
        setTitle(titulo);
        
        boolean editable = false; // modo 0 y 2
        
        if(modo == 1 || modo == 4){
            editable = true;
        }        
        
        String tablaString = TABLAS[tabla]; // nombre tabla
        
        HashMap<String,String> datos =new HashMap<String,String>();
        
        if(tabla == 2 || tabla == 0){ // provincias o articulo
            if(permiso == 3){ // debe ser admin
                if(id_consulta == 0){
                    
                    if(tablaString == "articulo"){
                        
                        datos.put("id_articulo", "");
                        datos.put("descripcion", "");
                        
                    }else{
                        
                        datos.put("id_provincia", "");
                        datos.put("nombre", "");

                    }
                } else{
                    
                    if(tablaString == "articulo"){
                        
                        
                        
                    }else{
                        
                        datos = DB.editar_provincia(id_consulta);

                    }
                    // SELECT * FROM provincias WHERE id = <<id_consulta>>
                }
            } else{
                // no debería entrar aqui nunca
            }
        } else if(tabla == 3){ // usuario
            if(permiso > 1){ // administrativo o admin
                if(id_consulta == 0){
                        //datos.put("id_usuario", "");
                        datos.put("Nombre", "");
                        datos.put("Apellidos", "");
                        datos.put("Correo", "");
                        datos.put("Password", "");
                        datos.put("id_provincia", "");
                        datos.put("permisos", "");
                        
                } else{   
                    // SELECT * FROM usuario WHERE id = <<id_consulta>>
                }
            } else{
                // SELECT * FROM usuario WHERE id = <<id_consulta>>
            }
        } else { // pedidos
            if(permiso != 1){ // cliente, admin, administrador 
                if(id_consulta != 0){
                    // SELECT * FROM pedidos WHERE id = <<id_consulta>>  <--- ojo que hay que cargar el tb lso datos de las tablas articulos provincias y usuarios
                } else {
                    // new pedido
                    datos.put("id_articulo", "");
                    datos.put("id_provincia", "");
                    datos.put("id_cliente", "");
                    datos.put("id_repartidor", "");
                    datos.put("num_articulos", "");

                }
            } else{
                // SELECT * FROM pedidos WHERE id_repartidor = <<id_consulta>>
            }
        }
        
        /* ------------- ACCESO AL FORMULARIO --------------- */
/*      PERMISOS ESPECIALES Y COSAS
        
        PERMISO 3 (ADMIN): SIEMPRE ENABLE TODO
        
        EDITAR USUARIOS:
            -> ID USUARIO DISABLE
        EDITAR PEDIDO:
            -> ID PEDIDO DISABLE
            -> ID ARTICULO DISABLE
            -> ID CLIENTE DISABLE
            -> PERMISO < 2 ID REPARTIDOR DISABLE
        NEW PEDIDO
            -> NUEVO ARTICULO Y NUEVO PEDIDO
            -> PERMISO 0 -> NO ID REPARTIDOR
        NEW USUARIO 
            PERMISO 2
            -> nuevo_cliente IS TRUE -> NUEVO CLIENTE
            -> nuevo_cliente IS FALSE -> NUEVO REPARTIDOR
        
*/        
        
        /* ------------- Carga datos ---------------- */
        
        
        //HashMap<String,String> datos = new HashMap<String,String>();
        
        container_pricipal.setLayout(new GridLayout(datos.size()+1,1));
        
        
        for (String columna:datos.keySet()) {
            
            JPanel flow_container = new JPanel();

            flow_container.add(new JLabel(columna + " :  "));
            
            JTextField valor = new JTextField(15);
            valor.setText(datos.get(columna));
            valor.setEditable(editable);
            
            
            valor.setName(columna);
            
            flow_container.add(valor);
            
            container_pricipal.add(flow_container, CENTER_ALIGNMENT);
       
        }
        
        add(container_pricipal);
        
        
        JButton boton_realizar = new JButton("Realizar");
        boton_realizar.addActionListener(new click_Execute());
        
        JButton boton_cancelar = new JButton("Cancelar");
        boton_cancelar.addActionListener(this);
        
        
        
        JPanel botones_container = new JPanel();
        
        
        botones_container.add(boton_realizar);
        botones_container.add(boton_cancelar);
                
        
        container_pricipal.add(botones_container,CENTER_ALIGNMENT);
        
        mensajeria_app.Utilidades.centrarPantalla(this);
        
        setVisible(true);
    }
    
    /**
     * cerrado genérico
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        dispose(); 
    }
    
        /**
     * escucha del boton find
     */
    private class click_Execute extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            //JOptionPane.showMessageDialog(null, "opcion 1 ", "tester", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
