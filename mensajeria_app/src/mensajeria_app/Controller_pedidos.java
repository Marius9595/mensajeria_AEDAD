/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import Clases_BD.Articulo;
import Clases_BD.Pedido;
import Clases_BD.Provincia;
import Clases_BD.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Es el controlador que hace de backend para las operaciones de la base de datos
 * @author Jonathan
 * @author Mario
 */
public class Controller_pedidos {

    ArrayList<Articulo> articulos = new ArrayList<Articulo>();
    ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    ArrayList<Provincia> provincias = new ArrayList<Provincia>();

    private final String USER = "root";
    private final String PASS = "";
    private final String IP = "Localhost:3306";
    private final String DATABASE = "mensajeria";
    /*   La otra conection que usaba yo (jonathan)   */
 /*private final String USER = "jony";
    private final String PASS = "123456";
    private final String IP = "Localhost:3306";
    private final String DATABASE = "mensajeria";*/

    Connection_BBDD DB = new Connection_BBDD(USER, PASS, IP, DATABASE);

    private ArrayList<Usuario> get_generic_usuarios(ResultSet respuesta) throws SQLException {

        ArrayList<Usuario> usuarios_consulta = new ArrayList<Usuario>();

        if (respuesta != null) {

            while (respuesta.next()) {

                Usuario registro_usuario = new Usuario();
                registro_usuario.setId_usuario(Integer.parseInt(respuesta.getString("id_usuario")));
                registro_usuario.setNombre(respuesta.getString("nombre"));
                registro_usuario.setApellidos(respuesta.getString("apellidos"));
                registro_usuario.setCorreo(respuesta.getString("correo"));
                registro_usuario.setPermisos(Integer.parseInt(respuesta.getString("permisos")));
                registro_usuario.setId_provincia(Integer.parseInt(respuesta.getString("id_provincia")));

                registro_usuario.setProvincia(get_provincia_by_id(registro_usuario.getId_provincia()));

                registro_usuario.setFecha_ultima_conection(respuesta.getString("fecha_ultima_conection"));
                usuarios_consulta.add(registro_usuario);

            }

            DB.close_connection();

        } else {

            usuarios_consulta = null;
        }

        return usuarios_consulta;

    }

    private ArrayList<Usuario> get_generic_usuarios(String columnas, String filtros) throws SQLException {

        ArrayList<Usuario> usuarios_consulta = new ArrayList<Usuario>();

        String sentencia = "SELECT " + columnas + " FROM  usuario ";

        if (filtros != null) {

            sentencia += " WHERE " + filtros;

        }

        ResultSet respuesta = DB.raw_select(sentencia);

        if (respuesta != null) {

            while (respuesta.next()) {

                Usuario registro_usuario = new Usuario();
                registro_usuario.setId_usuario(Integer.parseInt(respuesta.getString("id_usuario")));
                registro_usuario.setNombre(respuesta.getString("nombre"));
                registro_usuario.setApellidos(respuesta.getString("apellidos"));
                registro_usuario.setCorreo(respuesta.getString("correo"));
                registro_usuario.setPermisos(Integer.parseInt(respuesta.getString("permisos")));
                registro_usuario.setId_provincia(Integer.parseInt(respuesta.getString("id_provincia")));

                registro_usuario.setProvincia(get_provincia_by_id(registro_usuario.getId_provincia()));

                registro_usuario.setFecha_ultima_conection(respuesta.getString("fecha_ultima_conection"));
                usuarios_consulta.add(registro_usuario);

            }

            DB.close_connection();

        } else {

            usuarios_consulta = null;
        }

        return usuarios_consulta;

    }

    public Usuario Login(String correo, String password) throws SQLException {

        String filtros = "correo = '" + correo + "' AND password = '" + password + "'";

        ArrayList<Usuario> usuario_loggin = get_generic_usuarios("*", filtros);

        if (!usuario_loggin.isEmpty()) {
            return usuario_loggin.get(0);

        } else {

            return null;
        }

    }

    public String get_provincia_by_id(int id_provincia) throws SQLException {

        String resultado = null;

        ResultSet respuesta = DB.raw_select("SELECT * FROM provincia WHERE id_provincia = " + id_provincia);

        if (respuesta != null) {

            respuesta.next();

            resultado = respuesta.getString("nombre");

            DB.close_connection();
        }

        return resultado;

    }

    public ArrayList<String[]> lista_pedidos(int id_user, int permiso) throws SQLException {

        ResultSet respuesta;

        if (permiso == 1) {

            System.out.println("id: " + id_user);

            respuesta = DB.raw_select("SELECT art.* from articulo AS art INNER JOIN pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_repartidor = " + id_user + "  AND ped.fecha_entrega IS NULL ORDER BY art.id_articulo ASC");

        } else {

            respuesta = DB.raw_select("SELECT art.* from mensajeria.articulo AS art INNER JOIN mensajeria.pedido AS ped ON art.id_articulo = ped.id_articulo WHERE ped.id_cliente = " + id_user + "  ORDER BY ped.fecha_entrega DESC LIMIT 15");

        }

        ArrayList<String[]> articulos = new ArrayList<String[]>();

        if (respuesta != null) {

            while (respuesta.next()) {

                String[] articulo = {respuesta.getString("id_articulo"), respuesta.getString("descripcion")};

                articulos.add(articulo);

            }

            DB.close_connection();

        } else {

            respuesta = null;
        }

        return articulos;

    }

    public ArrayList<String[]> usuarios_conexion(int id_usuario) throws SQLException {

        String Filtros = " id_usuario != " + id_usuario + " ORDER BY mensajeria.usuario.fecha_ultima_conection DESC LIMIT 0, 15";

        ArrayList<Usuario> Usuarios = get_generic_usuarios("*", Filtros);

        ArrayList<String[]> usuario_enviar = new ArrayList<String[]>();

        String column[] = {"ID", "Nombre y apellidos", "Correo", "Ultima conexion"};

        usuario_enviar.add(column);
        for (Usuario usuario : Usuarios) {

            String[] usuario_string = {usuario.getId_usuario() + "", usuario.getNombre() + " " + usuario.getApellidos(), usuario.getCorreo(), usuario.getFecha_ultima_conection_string()};

            usuario_enviar.add(usuario_string);
        }

        return usuario_enviar;

    }

    private ArrayList<Pedido> get_generic_pedido(ResultSet respuesta) throws SQLException {

        ArrayList<Pedido> pedidos_consulta = new ArrayList<Pedido>();

        if (respuesta != null) {

            while (respuesta.next()) {

                Pedido registro_pedido = new Pedido();

                registro_pedido.setFecha_entrega_string(respuesta.getString("fecha_entrega"));
                registro_pedido.setId_articulo(respuesta.getInt("id_articulo"));
                registro_pedido.setId_cliente(respuesta.getInt("id_cliente"));
                registro_pedido.setId_provincia(respuesta.getInt("id_provincia"));
                registro_pedido.setId_repartidor(respuesta.getInt("id_repartidor"));
                registro_pedido.setNum_articulos(respuesta.getInt("num_articulos"));

                //derivados
                ResultSet respuesta_articulo = DB.raw_select("select descripcion from articulo where id_articulo = " + registro_pedido.getId_articulo());

                if (respuesta_articulo != null) {

                    if (respuesta_articulo.next()) {
                        registro_pedido.setNombre_articulo(respuesta_articulo.getString("descripcion"));
                    } else {

                        registro_pedido.setNombre_articulo("Sin Descripcion");
                    }

                }

                ResultSet respuesta_cliente = DB.raw_select("select nombre from usuario where id_usuario = " + registro_pedido.getId_cliente());

                if (respuesta_cliente != null) {

                    if (respuesta_cliente.next()) {

                        registro_pedido.setNombre_cliente(respuesta_cliente.getString("nombre"));

                    } else {

                        registro_pedido.setNombre_cliente("Error!");

                    }

                }

                ResultSet respuesta_repartidor = DB.raw_select("select nombre from usuario where id_usuario = " + registro_pedido.getId_repartidor());

                if (respuesta_repartidor != null) {

                    if (respuesta_repartidor.next()) {

                        registro_pedido.setNombre_repartidor(respuesta_repartidor.getString("nombre"));
                    } else {

                        registro_pedido.setNombre_repartidor("Sin asignar");
                    }

                }

                ResultSet respuesta_provincia = DB.raw_select("select nombre from provincia where id_provincia = " + registro_pedido.getId_provincia());

                if (respuesta_provincia != null) {

                    if (respuesta_provincia.next()) {

                        registro_pedido.setNombre_provincia(respuesta_provincia.getString("nombre"));

                    } else {

                        registro_pedido.setNombre_provincia("Desconocido");
                    }

                }

                pedidos_consulta.add(registro_pedido);

            }

            DB.close_connection();

        } else {

            pedidos_consulta = null;
        }

        return pedidos_consulta;

    }

    public ArrayList<String[]> to_string_pedidos(ArrayList<Pedido> pedidos) {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        String column[] = {"ID", "Cliente", "Repartidor", "Lugar de Entrega", "Articulo", "N� articulos"};

        resultado.add(column);
        for (Pedido pedido : pedidos) {

            String[] pedido_string = {pedido.getId_articulo() + "", pedido.getNombre_cliente(), pedido.getNombre_repartidor(), pedido.getNombre_provincia(), pedido.getNombre_articulo(), pedido.getNum_articulos() + ""};

            resultado.add(pedido_string);
        }

        return resultado;

    }

    private ArrayList<String[]> to_string_usuarios(ArrayList<Usuario> usuarios) {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        String column[] = {"ID", "Nombre", "Apellidos", "Correo", "Residencia"};

        resultado.add(column);
        for (Usuario usuario : usuarios) {

            String[] usuario_string = {usuario.getId_usuario() + "", usuario.getNombre(), usuario.getApellidos(), usuario.getCorreo(), usuario.getProvincia()};

            resultado.add(usuario_string);
        }

        return resultado;

    }

    private ArrayList<Articulo> get_generic_articulos(ResultSet respuesta) throws SQLException {

        ArrayList<Articulo> articulos_consulta = new ArrayList<Articulo>();

        if (respuesta != null) {

            while (respuesta.next()) {

                Articulo registro_articulo = new Articulo();

                registro_articulo.setId_articulo(respuesta.getInt("id_articulo"));
                registro_articulo.setDescripcion(respuesta.getString("descripcion"));

                articulos_consulta.add(registro_articulo);

            }

            DB.close_connection();

        } else {

            articulos_consulta = null;
        }

        return articulos_consulta;

    }

    public ArrayList<String[]> to_string_articulos(ArrayList<Articulo> articulos) {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        String column[] = {"ID", "Descripcion"};

        resultado.add(column);
        for (Articulo articulo : articulos) {

            String[] articulo_string = {articulo.getId_articulo() + "", articulo.getDescripcion()};

            resultado.add(articulo_string);
        }

        return resultado;

    }

    private ArrayList<Provincia> get_generic_provincias(ResultSet respuesta) throws SQLException {

        ArrayList<Provincia> provincias_consulta = new ArrayList<Provincia>();

        if (respuesta != null) {

            while (respuesta.next()) {

                Provincia registro_provincia = new Provincia();

                registro_provincia.setId_provincia(respuesta.getInt("id_provincia"));
                registro_provincia.setNombre(respuesta.getString("nombre"));

                provincias_consulta.add(registro_provincia);

            }

            DB.close_connection();

        } else {

            provincias_consulta = null;
        }

        return provincias_consulta;

    }

    public ArrayList<String[]> to_string_provincias(ArrayList<Provincia> provincias) {

        ArrayList<String[]> resultado = new ArrayList<String[]>();

        String column[] = {"ID", "Provincia"};

        resultado.add(column);
        for (Provincia provincia : provincias) {

            String[] provincia_string = {provincia.getId_provincia() + "", provincia.getNombre()};

            resultado.add(provincia_string);
        }

        return resultado;

    }

    public ArrayList<String[]> select_all_tabla(String tabla) throws SQLException {

        ArrayList<String[]> resultados = new ArrayList<String[]>();

        String sentencia = "SELECT *  FROM " + tabla;

        ResultSet respuesta = DB.raw_select(sentencia);

        switch (tabla) {

            case "pedido":

                resultados = to_string_pedidos(get_generic_pedido(respuesta));

                break;
            case "usuario":

                resultados = to_string_usuarios(get_generic_usuarios("*", null));
                break;

            case "articulo":

                resultados = to_string_articulos(get_generic_articulos(respuesta));
                break;

            case "provincia":

                resultados = to_string_provincias(get_generic_provincias(respuesta));

        }

        return resultados;

    }

    public ArrayList<String[]> select_cliente_repartidor(String tabla, int permiso, int id_user) throws SQLException {

        ArrayList<String[]> resultados = new ArrayList<String[]>();

        String sentencia = "SELECT * FROM " + tabla + " WHERE ";

        if (permiso == 0) {

            sentencia += " id_cliente = " + id_user;

        } else if (permiso == 1) {

            sentencia += " id_repartidor = " + id_user;

        } else if (permiso == 2) {

            sentencia += " id_repartidor is  null";
        }

        ResultSet respuesta = DB.raw_select(sentencia);

        resultados = to_string_pedidos(get_generic_pedido(respuesta));

        return resultados;

    }

    private ResultSet get_object_by_id(String tabla, String filtros) {

        String setencia = "SELECT *  FROM " + tabla + " WHERE " + filtros;

        ResultSet respuesta = DB.raw_select(setencia);

        return respuesta;

    }

    private HashMap<String, String> get_object_data_by_id(String[] columnas, String tabla, int id_object) throws SQLException {

        HashMap<String, String> data = new HashMap<String, String>();

        ResultSet respuesta = get_object_by_id(tabla, columnas[0] + " = " + id_object);

        if (respuesta.next()) {

            for (String columna : columnas) {

                data.put(columna, respuesta.getString(columna));

            }

        } else {
            data = null;
        }

        return data;

    }

    public HashMap<String, String> get_provincia_by_id_(int id_consulta) throws SQLException {

        String[] columnas = {"id_provincia", "nombre"};

        return get_object_data_by_id(columnas, "provincia", id_consulta);

    }

    public HashMap<String, String> get_articulo_by_id(int id_consulta) throws SQLException {

        String[] columnas = {"id_articulo", "descripcion"};

        return get_object_data_by_id(columnas, "articulo", id_consulta);

    }

    public HashMap<String, String> get_usuario_by_id(int id_consulta) throws SQLException {

        String[] columnas = {"id_usuario", "nombre", "apellidos", "correo", "id_provincia"};

        return get_object_data_by_id(columnas, "usuario", id_consulta);

    }

    public HashMap<String, String> get_pedido_by_id(int id_consulta) throws SQLException {

        String[] columnas = {"id_articulo", "id_cliente", "id_repartidor", "fecha_entrega", "num_articulos"};

        return get_object_data_by_id(columnas, "pedido", id_consulta);

    }

    public void update(String tabla, HashMap<String, Object> registros, String filtros) {

        DB.update(tabla, registros, filtros);

        DB.close_connection();

    }

    public void insert(String tabla, HashMap<String, Object> registros) {

        DB.insert(tabla, registros);
        DB.close_connection();

    }

    public void delete(String tabla, String filtros) {

        DB.delete(tabla, filtros);
        DB.close_connection();

    }

    public double get_pedidos_periodo(String fecha_Last_1Enero, String fecha_Last_31Diciembre) throws SQLException {

        ResultSet respuesta = DB.raw_select("SELECT sum(mensajeria.pedido.id_articulo) as 'sum_art' FROM mensajeria.pedido WHERE mensajeria.pedido.fecha_entrega BETWEEN '" + fecha_Last_1Enero + "' AND '" + fecha_Last_31Diciembre + "'");

        Double valor = 0.0;

        if (respuesta != null) {

            if (respuesta.next()) {

                try {
                    valor = Double.parseDouble(respuesta.getString("sum_art"));
                } catch (Exception e) {

                    valor = 0.0;
                }

            }

        }

        DB.close_connection();

        return valor;

    }

    public Integer[] get_estadistica_pedidos() throws SQLException {

        int valor_entregados = -1;
        int valor_transito = -1;
        int valor_sin_salir = -1;

        Integer[] valores = {valor_entregados, valor_transito, valor_sin_salir};

        ResultSet respuesta;

        respuesta = DB.raw_select("SELECT SUM(mensajeria.pedido.id_articulo) as 'sum_art' FROM mensajeria.pedido WHERE fecha_entrega IS NOT NULL");

        if (respuesta.next()) {

            try {

                valor_entregados = Integer.parseInt(respuesta.getString("sum_art"));

            } catch (Exception e) {

                valor_entregados = 0;
            }

        }

        DB.close_connection();

        respuesta = DB.raw_select("SELECT SUM(mensajeria.pedido.id_articulo) as 'sum_art' FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NOT NULL");

        if (respuesta.next()) {

            try {

                valor_transito = Integer.parseInt(respuesta.getString("sum_art"));

            } catch (Exception e) {

                valor_transito = 0;
            }
        }

        DB.close_connection();

        respuesta = DB.raw_select("SELECT SUM(mensajeria.pedido.id_articulo) as 'sum_art' FROM mensajeria.pedido WHERE fecha_entrega IS NULL AND id_repartidor IS NULL");

        if (respuesta.next()) {

            try {

                valor_sin_salir = Integer.parseInt(respuesta.getString("sum_art"));

            } catch (Exception e) {

                valor_sin_salir = 0;
            }
        }

        DB.close_connection();

        return valores;

    }
}
