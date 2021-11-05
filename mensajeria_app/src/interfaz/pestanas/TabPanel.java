/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz.pestanas;

import Clases_BD.Usuario;
import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.JTabbedPane;

/**
 * @author Jonathan
 * @author Mario
 */
public class TabPanel extends JTabbedPane {
    
    
    private int id_usuario;
    
    private Usuario Usuario_logueado;

    public Usuario getUsuario_logueado() {
        return Usuario_logueado;
    }

    public void setUsuario_logueado(Usuario Usuario_logueado) {
        this.Usuario_logueado = Usuario_logueado;
    }
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
 
    
    public TabPanel() throws SQLException {
        // crea login
        addTab("Login",new Login(this));

        
        id_usuario = 0;
        
        
        // inicialmente que este todo en disabled y cuando loggee activamos
        //para pruebas descomentar esta parte
        
        /*
        for (int i = 0; i < 5; i++) {
            setEnabledAt(i, false);
        }
        */
        
        setPreferredSize(new Dimension(650,500));
        
        

    }
    
    

    
    


}
