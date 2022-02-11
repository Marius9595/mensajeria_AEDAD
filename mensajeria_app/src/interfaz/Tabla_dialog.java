/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import mensajeria_app.Controller_pedidos;

/**
 * JFrame modal de la tabla
 * @author Jonathan
 * @author Mario
 */
public class Tabla_dialog extends JFrame implements ActionListener {

    private Controller_pedidos DB = new Controller_pedidos();

    /**
     * Constante MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar
     * Repartidor"}
     */
    protected final String MODO_TABLAS[] = {"Consultar", "Editar", "Borrar", "Editar Repartidor"};
    /**
     * Constante TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"}
     */
    protected final String TABLAS[] = {"articulo", "pedido", "provincia", "usuario"};
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
    private HashMap<String, String> datos;

    private ArrayList<String[]> lista_Datos;

    /**
     * Constructor: TABLAS[] = {"Articulos","Pedidos","Provincias","Usuarios"},
     * MODO_TABLAS[] = {"Select", "Edit", "Delete", "Edit_NoReparto"}
     *
     * @param tabla tabla elegida
     * @param modo modo de "lectura"
     * @param id id del usuario
     * @param per id permiso del usuario
     */
    public Tabla_dialog(int tabla, int modo, int id, int per) throws SQLException {
        // atributos
        this.tabla = tabla;
        this.modo = modo;
        this.id_user = id;
        this.permiso = per;

        // propiedades frame
        setTitle(TABLAS[this.tabla]);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // cargar los datos de las consultas
        cargadorDeDatos();

        /* ---------  empezamos a construir los paneles  --------- */
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

    private JPanel contructor_Panel_Cabecera() {
        // main cabecera
        JPanel panel_Cabecera = new JPanel();
        panel_Cabecera.setLayout(new BorderLayout());
        // texto encima de opciones
        JPanel panel_Cabecera_Label = new JPanel();
        panel_Cabecera_Label.setLayout(new GridBagLayout());
        // opciones
        JPanel panel_Cabecera_Opciones = new JPanel();
        panel_Cabecera_Opciones.setLayout(new GridLayout(2, 4));
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
        // recuperamos las cabeceras
        ArrayList<String> label_String_campos = new ArrayList<>();
        String[] valores = lista_Datos.get(0);

        for (int i = 0; i < valores.length; i++) {
            String valor = valores[i];
            label_String_campos.add(valor);
        }
        int contador_Campos = 0;

        //Campo 1
        JLabel lb_id = new JLabel();
        lb_id.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_id.setPreferredSize(dim);

        txt_id = new TextField(tamanoTextField);
        //txt_id.setPreferredSize(dim);

        if (contador_Campos < valores.length) {
            lb_id.setText(label_String_campos.get(contador_Campos));
        } else {
            lb_id.setText("");
            txt_id.setEnabled(false);
        }
        contador_Campos++;

        JLabel lb_campo1 = new JLabel();
        lb_campo1.setText(label_String_campos.get(1));
        lb_campo1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo1.setPreferredSize(dim);

        txt_campo1 = new TextField(tamanoTextField);
        //txt_campo1.setPreferredSize(dim);     

        if (contador_Campos < valores.length) {
            lb_campo1.setText(label_String_campos.get(contador_Campos));
        } else {
            lb_campo1.setText("");
            txt_campo1.setEnabled(false);
        }
        contador_Campos++;

        JLabel lb_campo2 = new JLabel();
        lb_campo2.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo2.setPreferredSize(dim);

        txt_campo2 = new TextField(tamanoTextField);
        //txt_campo2.setPreferredSize(dim);

        if (contador_Campos < valores.length) {
            lb_campo2.setText(label_String_campos.get(contador_Campos));
        } else {
            lb_id.setText("");
            txt_campo2.setEnabled(false);
        }
        contador_Campos++;

        JLabel lb_campo3 = new JLabel();
        lb_campo3.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        lb_campo3.setPreferredSize(dim);

        txt_campo3 = new TextField(tamanoTextField);
        //txt_campo3.setPreferredSize(dim);        

        if (contador_Campos < valores.length) {
            lb_campo3.setText(label_String_campos.get(contador_Campos));
        } else {
            lb_campo3.setText("");
            txt_campo3.setEnabled(false);
        }
        contador_Campos++;

        JButton botonClear = new JButton();
        botonClear.setText("Limpiar");
        botonClear.setName("Limpiar");
        botonClear.setPreferredSize(dim);
        botonClear.addActionListener(new click_Find());

        JButton botonEnviar = new JButton();
        botonEnviar.setText("Buscar");

        botonEnviar.setPreferredSize(dim);

        // esto faltaba
        if (permiso > 1) {
            botonEnviar.setName("Buscar");
            botonEnviar.addActionListener(new click_Find());
        } else {

            botonEnviar.setName(" ");
            botonEnviar.setEnabled(false);

            txt_id.setEditable(false);
            txt_campo1.setEditable(false);
            txt_campo2.setEditable(false);
            txt_campo3.setEditable(false);
        }

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

    private JPanel contructor_Panel_Tabla() {
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
        DefaultTableModel tableModel = mensajeria_app.Utilidades.ArrayList_to_DefaultTableModel(lista_Datos);

        JTable tabla_datos = new JTable(tableModel);
        // solo que se vea
        tabla_datos.setEnabled(false);
        //tablaConection.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        //evento
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

        JScrollPane sp = new JScrollPane(tabla_datos);

        panelTabla.add(sp, BorderLayout.CENTER);

        return panelTabla;
    }

    private JPanel contructor_Panel_Botones() {
        JPanel botones_container = new JPanel();

        JButton boton_cancelar = new JButton("Volver");
        boton_cancelar.addActionListener(this);

        botones_container.add(boton_cancelar);

        return botones_container;
    }

    /**
     * lee el row busca el indice de la lista de datos recuperandola y
     * volcandola en los textField
     *
     * @param row fila
     * @param col columna
     */
    private void accionEnTabla(int row, int col) {
        String[] dat = lista_Datos.get(row + 1);
        // cargamos los textfield

        if (dat.length > 3) {
            txt_campo3.setText(dat[3]);
        }
        if (dat.length > 2) {
            txt_campo2.setText(dat[2]);
        }

        txt_id.setText(dat[0]);
        txt_campo1.setText(dat[1]);

    }

    private void cargadorDeDatos() throws SQLException {

        lista_Datos = new ArrayList<String[]>();
        //-------------------------- carga de datos --------------

        if (permiso == 3) { // admin siempre select *

            lista_Datos = DB.select_all_tabla(TABLAS[tabla]);
            // Select * From "Tabla"
        } else if (modo == 0 && (permiso == 0 || permiso == 1)) { // select pedidos desde cliente
            lista_Datos = DB.select_cliente_repartidor(TABLAS[tabla], permiso, id_user);
            // Select * From 'pedidos' where id_cliente = id
            // select pedidos desde repartidor
            // select * from pedidos where id_repartidor = id
        } else if (modo == 3 && permiso == 2) {
            lista_Datos = DB.select_cliente_repartidor(TABLAS[tabla], permiso, id_user);
            // select * from pedidos where id_repartidor is not null -> esto va a ir a un edit
        } else if (modo == 0 && permiso == 2) {
            lista_Datos = DB.select_all_tabla("pedido");
        } else {
            JOptionPane.showMessageDialog(null, "Esto no deberias estarlo viendo, contacte con un administrador", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //lista_Datos =  <-----  los datos se deben cargar aqui;
        /*
        //ejemplo
        String column[]={"ID","CAMPO1","CAMPO2"}; //
        lista_Datos.add(column); // <--- indice 0 nombre columnas
        
        for (int i = 0; i < 20; i++) {
            String dat[] = {String.valueOf(100 +i ),"Amit",String.valueOf(i * 10000)};
            lista_Datos.add(dat);
        }  */
    }

    /**
     * cerrado genérico
     *
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
    private class click_Find extends Eventos.Event_Boton_Personalizado {

        @Override
        public void actionPerformed(ActionEvent ae) {
            this.source = (JButton) ae.getSource();

            switch (source.getName()) {
                case "Buscar":
                    //JOptionPane.showMessageDialog(null, "opcion 1 ", "tester", JOptionPane.PLAIN_MESSAGE);
                    if (permiso > 1) { // administrador o admin                       
                        String[] rowEnvio = null;
                        if (!" ".equals(txt_id.getText()) && !txt_id.getText().isEmpty()) { // si id no esta vacio y no es " "                            
                            for (String[] row : lista_Datos) {
                                if (txt_id.getText().equals(row[0])) {
                                    rowEnvio = row;
                                    break; // encontro la row -> salimos
                                }
                            }
                        } else if (!" ".equals(txt_campo1.getText()) && !txt_campo1.getText().isEmpty()) {
                            for (String[] row : lista_Datos) {
                                if (txt_campo1.getText().equals(row[1])) {
                                    rowEnvio = row;
                                    break; // encontro la row -> salimos
                                }
                            }
                        } else if (!" ".equals(txt_campo2.getText()) && !txt_campo2.getText().isEmpty()) {
                            for (String[] row : lista_Datos) {
                                if (row.length > 1 && txt_campo2.getText().equals(row[2])) {
                                    rowEnvio = row;
                                    break; // encontro la row -> salimos
                                }
                            }
                        } else if (!" ".equals(txt_campo3.getText()) && !txt_campo3.getText().isEmpty()) {
                            for (String[] row : lista_Datos) {
                                if (row.length > 2 && txt_campo3.getText().equals(row[3])) {
                                    rowEnvio = row;
                                    break; // encontro la row -> salimos
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Lo sentimos no hemos encontrado ningún registro con esos valores.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        if (rowEnvio != null) {
                            try {
                                envioFormulario(rowEnvio);
                            } catch (SQLException ex) {
                                Logger.getLogger(Tabla_dialog.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                default:
                    txt_id.setText(" ");
                    txt_campo1.setText(" ");
                    txt_campo2.setText(" ");
                    txt_campo3.setText(" ");
            }
        }
    }

    private void envioFormulario(String[] rowEnvio) throws SQLException {
        String titulo = MODO_TABLAS[modo] + " " + TABLAS[tabla];
        int id;
        try {
            id = Integer.parseInt(rowEnvio[0]);
        } catch (Exception e) {
            id = 500; // <- EN TEORIA ESTABA MONTADO PARA QEU EL CAMPO 1 DE LA TABLA SIEMPRE SEA ID

            /* SI NO ES CAMPO ID EL INDICE 0 DE LA ROW HAY QUE PREGUNTAR A LA BASE DE DATOS 
            POR EL REGISTRO Y RECUPERAR SU ID*/
        }

        new Formulario_dialog(titulo, tabla, modo, id, permiso);
    }
}
