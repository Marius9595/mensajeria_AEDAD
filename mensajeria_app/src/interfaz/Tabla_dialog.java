/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Dizzy
 */
public class Tabla_dialog extends JFrame implements ActionListener{

    /**
     * Constante MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"}
     */
    protected final String MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"};
    /**
     * Constante TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"}
     */
    protected final String TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"};
    // las tablas que hay 
    private int tabla;
    private int modo;
    private int id_user;
    private int permiso;
    // para el acceso a los componentes
    private TextField txt_id;
    private TextField txt_campo1;
    private TextField txt_campo2;
    private TextField txt_campo3;
    
    // datos
    private HashMap<String,String> datos;
    
    // para pruebas
    private String data[][]={   {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"},
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}}; 
    
    public Tabla_dialog(int tabla, int modo, int id, int per) {
        this.tabla = tabla;
        this.modo = modo;
        this.id_user = id;
        this.permiso = per;
        
        setTitle(TABLAS[this.tabla]);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //panel operaciones
        add(contructor_Panel_Cabecera(), BorderLayout.NORTH);
        
        //panel tabla datos
        add(contructor_Panel_Tabla(), BorderLayout.CENTER);
        
        // panel botones sur
        add(contructor_Panel_Botones(), BorderLayout.SOUTH);
        
        // para centrar la pantalla
        mensajeria_app.Utilidades.centrarPantalla(this);
        
        setVisible(true);
    }
    
    private JPanel contructor_Panel_Cabecera(){
        // main cabecera
        JPanel panel_Cabecera = new JPanel();
        panel_Cabecera.setLayout(new BorderLayout());
        // texto encima de opciones
        JPanel panel_Cabecera_Label = new JPanel();
        panel_Cabecera_Label.setLayout(new GridBagLayout());
        // opciones
        JPanel panel_Cabecera_Opciones = new JPanel();
        panel_Cabecera_Opciones.setLayout(new GridLayout(2,4));
        // tamaños genéricos
        int tamanoTextField = 10;
        Dimension dim = new Dimension(40, 40);
        
        /* Panel label */
        // mensaje del label
        String textoLabel = "Seleccione un elemento de la tabla o use el buscador";
        
        JLabel lb_Title = new JLabel();
        lb_Title.setText(textoLabel);
        lb_Title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        panel_Cabecera_Label.add(lb_Title);
        
        /* Panel opciones */
        
        JLabel lb_id = new JLabel();
        lb_id.setText("Id");
        lb_id.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_id.setPreferredSize(dim);
        
        txt_id = new TextField(tamanoTextField);
        //txt_id.setPreferredSize(dim);
        
        JLabel lb_campo1 = new JLabel();
        lb_campo1.setText("Campo1");
        lb_campo1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo1.setPreferredSize(dim);
        
        txt_campo1 = new TextField(tamanoTextField);
        //txt_campo1.setPreferredSize(dim);     
        
        JLabel lb_campo2 = new JLabel();
        lb_campo2.setText("Campo2");
        lb_campo2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo2.setPreferredSize(dim);
        
        txt_campo2 = new TextField(tamanoTextField);
        //txt_campo2.setPreferredSize(dim);
        
        JLabel lb_campo3 = new JLabel();
        lb_campo3.setText("Campo3");
        lb_campo3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo3.setPreferredSize(dim);
        
        txt_campo3 = new TextField(tamanoTextField);
        //txt_campo3.setPreferredSize(dim);        
        
        JButton botonClear = new JButton();
        botonClear.setText("Limpiar");
        botonClear.setName("Limpiar");
        botonClear.setPreferredSize(dim);
        botonClear.addActionListener(new click_Find());
        
        JButton botonEnviar = new JButton();
        botonEnviar.setText("Buscar");
        botonEnviar.setName("Buscar");
        botonEnviar.setPreferredSize(dim);
        botonEnviar.addActionListener(new click_Find());
        
        // linea superior
        panel_Cabecera_Opciones.add(lb_id);
        panel_Cabecera_Opciones.add(txt_id);
        panel_Cabecera_Opciones.add(lb_campo1);
        panel_Cabecera_Opciones.add(txt_campo1);
        panel_Cabecera_Opciones.add(botonClear);       
        // linea inferior
        panel_Cabecera_Opciones.add(lb_campo2);
        panel_Cabecera_Opciones.add(txt_campo2);
        panel_Cabecera_Opciones.add(lb_campo3);
        panel_Cabecera_Opciones.add(txt_campo3);        
        panel_Cabecera_Opciones.add(botonEnviar);
        
        
        panel_Cabecera.add(panel_Cabecera_Label, BorderLayout.NORTH);
        panel_Cabecera.add(panel_Cabecera_Opciones, BorderLayout.CENTER);
        
        return panel_Cabecera;
    }
    
    private JPanel contructor_Panel_Tabla(){
        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(new BorderLayout());
        
        JPanel panelSeparator = new JPanel();
        panelSeparator.setLayout(new BorderLayout());
        panelSeparator.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        
        JPanel panelTituloTabla = new JPanel();
        panelTituloTabla.setLayout(new FlowLayout());
        
        JLabel nombreLista = new JLabel();
        nombreLista.setText("Tabla " + TABLAS[this.tabla]);
        //nombreLista.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        panelTituloTabla.add(nombreLista);
        
        panelSeparator.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.NORTH);
        panelSeparator.add(panelTituloTabla, BorderLayout.CENTER);
        

        panelTabla.add(panelSeparator, BorderLayout.NORTH);
        
        /* tabla de datos */
        // deberian sser 3 o 5 lo que se vea en el frame
        String data[][]={   {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"},
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}, 
                            {"101","Amit","670000"},    // los datos
                            {"102","Jai","780000"},    
                            {"101","Sachin","700000"}}; 
        String column[]={"ID","CAMPO1","CAMPO2"};    // nombre columnas      
        
        JTable tabla_datos=new JTable(data,column);    
        // solo que se vea
        tabla_datos.setEnabled(false);
        //tablaConection.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        //evento
        //tablaConection.getModel().addTableModelListener(new TableModelListener() {
        tabla_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla_datos.rowAtPoint(evt.getPoint());
                int col = tabla_datos.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    accionEnTabla(row, col);
                }
            }
        });
        
        JScrollPane sp =new JScrollPane(tabla_datos);
        
        panelTabla.add(sp, BorderLayout.CENTER);
        
        return panelTabla;
    }
    
    private JPanel contructor_Panel_Botones(){
        JPanel botones_container = new JPanel();
        
        JButton boton_cancelar = new JButton("Volver");
        boton_cancelar.addActionListener(this);
        
        botones_container.add(boton_cancelar);
                
//        boton_realizar.addActionListener();
//        boton_cancelar.addActionListener();
        
        return botones_container;
    }
    
    private void accionEnTabla(int row, int col){
        // la idea es recuperar el row ver qeu valor es y del hasmap coger el row que toca y ponelro en los textField        
        ArrayList<String> listaDatosRow = new ArrayList<>();
        for (int i = 0; i < data[row].length; i++) {
            listaDatosRow.add(data[row][i]);
        }
        // cargamos los textfield
        txt_id.setText(listaDatosRow.get(0));
        txt_campo1.setText(listaDatosRow.get(1));
        txt_campo2.setText(listaDatosRow.get(2));
        txt_campo3.setText("");
        
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
    private class click_Find extends Eventos.Event_Boton_Personalizado{
        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();
            
            if("Buscar".equals(source.getName())){
                //JOptionPane.showMessageDialog(null, "opcion 1 ", "tester", JOptionPane.PLAIN_MESSAGE);
            } else {
                txt_id.setText(" ");
                txt_campo1.setText(" ");
                txt_campo2.setText(" ");
                txt_campo3.setText(" ");
            }
        }
    }
}
