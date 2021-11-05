/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import mensajeria_app.Controller_pedidos;

/**
 * @author Jonathan
 * @author Mario
 */
public class Formulario_dialog extends JFrame implements ActionListener{
    
   
    private Controller_pedidos DB =  new Controller_pedidos();
    private JPanel container_pricipal = new JPanel();

    /**
     * Constante MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar Repartidor"}
     */
    protected final String MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar Repartidor"};
    /**
     * Constante TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"}
     */
    protected final String TABLAS[] = {"articulo","pedido","provincia","usuario"};
    /**
     * Constante TIPOS_USUARIOS[] = {"Cliente","Repartidor","Administrativo","Admin"}
     */
    protected final String TIPOS_USUARIOS[] = {"Cliente","Repartidor","Administrativo","Admin"};
    
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
        // PARA PRUEBAS
//        String control_ID = "Se recive esto: "+ titulo +", Tabla: "+ tabla +", Modo: "+ modo +", Id: "+ id_consulta +", Permiso: "+ permiso;
//        if(id_consulta == 500)
//            control_ID = "El ID acaba de petar";
//        // mostrando lo que se recibe
//        JOptionPane.showMessageDialog(null, control_ID, "Aviso", JOptionPane.PLAIN_MESSAGE);
        
        // el nuevo repartidor y nuevo cliente son iguales esto lo separa
        boolean nuevo_cliente = false;
        if("Nuevo Cliente".equals(titulo))
            nuevo_cliente = true;
            
        setTitle(titulo);
        
        boolean editable = false; // modo 0 y 2
        
        if(modo == 1 || modo == 3){
            editable = true;
        }        
        
        String tablaString = TABLAS[tabla]; // nombre tabla
        
        HashMap<String,String> datos = new HashMap<String,String>();
        
        click_Execute click_execute_listener = null;
        
        if(tabla == 2 || tabla == 0){ // provincias o articulo
            if(permiso == 3){ // debe ser admin
                if(id_consulta == 0){
                    
                    if(tablaString.equals("provincia")){
                        
                        datos.put("id_provincia", "");
                        datos.put("nombre", "");
                        
                    }else{
                        datos.put("id_articulo", "");
                        datos.put("descripcion", ""); 
                    }
                    
                    click_execute_listener = new click_Execute("insertar", tablaString);
                    
                    
                } else{
                    
                    if(tablaString.equals("provincia")){
                        
                       datos = DB.get_provincia_by_id_(id_consulta);
                       
                       
                        
                    }else{
                        datos = DB.get_articulo_by_id(id_consulta);
                    }
                    
                    
                    if(modo == 2){
                        
                        click_execute_listener = new click_Execute("borrar", tablaString);
                        
                    }else{
                        
                        click_execute_listener = new click_Execute("actualizar", tablaString);
                    }
                }
            } else{
                // no debería entrar aqui nunca
            }
        } else if(tabla == 3){ // usuario
            if(permiso > 1){ // administrativo o admin
                if(id_consulta == 0){
                    
                    String[] columnas = {"id_usuario","nombre","apellidos","correo","id_provincia"};
                    for (String columna : columnas) {
                        datos.put(columna, "");
                    }
                    click_execute_listener = new click_Execute("insertar", tablaString);
                    
                } else{   
                    datos = DB.get_usuario_by_id(id_consulta);
                    
                    if(modo == 2 ){
                        
                        click_execute_listener = new click_Execute("borrar", tablaString);
                    }else{
                        
                        click_execute_listener = new click_Execute("actualizar", tablaString);
                    }
                }
            } else{
                click_execute_listener = new click_Execute("actualizar", tablaString);
                datos = DB.get_usuario_by_id(id_consulta);
            }
        } else { // pedidos
            if(permiso != 1){ // cliente, admin, administrador 
                if(id_consulta != 0){
                    
                     datos = DB.get_pedido_by_id(id_consulta);
                     
                     
                    if(modo == 2 ){
                        
                        click_execute_listener = new click_Execute("borrar", tablaString);
                    }else{
                        
                        click_execute_listener = new click_Execute("actualizar", tablaString);
                    }
                     
                    // SELECT * FROM pedidos WHERE id = <<id_consulta>>  <--- ojo que hay que cargar el tb lso datos de las tablas articulos provincias y usuarios
                } else {
                    String[] columnas = {"id_articulo","id_cliente","id_repartidor","fecha_entrega","num_articulos"};
                    for (String columna : columnas) {
                        datos.put(columna, "");
                    }
                    click_execute_listener = new click_Execute("insertar", tablaString);
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
        
        
        
        
        container_pricipal.setLayout(new GridLayout(datos.size()+1,1));
        
        
        ArrayList<JTextField> valores = new ArrayList<JTextField>();
        
        
        for (String columna:datos.keySet()) {
            
            JPanel flow_container = new JPanel();

            flow_container.add(new JLabel(columna + " :  "));
            
            JTextField valor = new JTextField(15);
            valor.setText(datos.get(columna));
            valor.setEditable(editable);
            
            
            valor.setName(columna);
            
            valores.add(valor);
            
            flow_container.add(valor);
            
            container_pricipal.add(flow_container, CENTER_ALIGNMENT);
       
        }
        
        click_execute_listener.setComponents_toGetData(valores);
        
        
        
        add(container_pricipal);
        
        
        JButton boton_realizar = new JButton("Realizar");
        boton_realizar.addActionListener(click_execute_listener);
        
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
        
        private String modo;
        private String tabla;
        private ArrayList<JTextField> JTFvalores;

        public click_Execute(String modo, String tabla) {
            
            this.modo = modo;
            
            this.tabla = tabla;
        }
        
        public void setComponents_toGetData(ArrayList<JTextField> JTFvalores){
            
            this.JTFvalores = JTFvalores;
        }
        
        
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
            
            HashMap<String,Object> data = new HashMap<String,Object>();
            
            String filtro;
            
            for (JTextField JTF : JTFvalores) {
                
                if(!JTF.getText().equals("")){
                    data.put(JTF.getName(),JTF.getText());
                }
                
            }
            
            switch(tabla){
                
                case "usuario":
                    filtro = "id_usuario = " + data.get("id_usuario");
                    break;
                case "provincia":
                    filtro = "id_provincia = " + data.get("id_provincia");
                    break;
                case "articulo":
                    filtro = "id_articulo = " + data.get("id_articulo");
                    break;               
                case "pedido":
                    filtro = "id_articulo = " + data.get("id_articulo");
                    break;
                default:
                    filtro ="";
                    break;
            }
            
            
            
            if(modo.equals("actualizar")){
                
                DB.update(tabla, data, filtro);
                
            }else if(modo.equals("insertar")) {
                
                DB.insert(tabla, data);
                
            }else if(modo.equals("borrar")) {
                
                DB.delete(tabla, filtro);
                
            }
            
            JOptionPane.showMessageDialog(null, "Se ha realizado la operación", "Aviso", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
