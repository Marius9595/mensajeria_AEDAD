/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajeria_app;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author Jonathan
 * @author Mario
 */
public class Utilidades {
    /**
     * devuelve string fechas como el formato del patron: dd-MM-yyyy HH:mm:ss
     * @param myDateObj
     * @return 
     */
    public static String formatearLocalDateTime(LocalDateTime myDateObj){
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }
    /**
     * devuelve string fechas como el formato del patron - SQL: yyyy-MM-dd HH:mm:ss
     * @param dt
     * @return 
     */
    public static String formatearDatetime(Date dt){
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(dt);
        return formattedDate;
    }
    /**
     * formatea String a LocalDateTime
     * @param fecha
     * @return 
     */
    public static LocalDateTime stringToLocalDateTime(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        return dateTime;
    }
    
    public static void centrarPantalla(JFrame elFrame){
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanoPantalla = pantalla.getScreenSize(); 
       
        int height = tamanoPantalla.height - elFrame.getHeight()/2;
        int width = tamanoPantalla.width - elFrame.getWidth()/2;
        
        elFrame.pack();
        
        elFrame.setLocation(width/2 - elFrame.getWidth()/2 , height/2 - elFrame.getHeight()/2);
    }
}
