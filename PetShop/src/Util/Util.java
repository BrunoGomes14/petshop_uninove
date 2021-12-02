/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author Bruno
 */
public class Util {
    
    public final static String TIPO_USUARIO_ATENDENTE = "atendente";
    public final static String TIPO_USUARIO_ADMIN = "admin";
    public final static String TIPO_USUARIO_ESTOQUISTA = "estoquista";
    
    private final static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    private final static SimpleDateFormat formatDtHr = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    public static boolean dataValida(String data){
        
        if (data.trim().length() != 10){
            return false;
        }
        
        try {
            Date dt = format.parse(data.trim());
        }
        catch (ParseException err){
            return false;
        }
         
        return true;
    }
    
    public static Date converteData(String data){
        
        try{
            if (dataValida(data)){
                return format.parse(data.trim());
            }
            
            return Calendar.getInstance().getTime();
        }
        catch (Exception erro){
            return Calendar.getInstance().getTime();
        }
    }
    
    public static String dataToString(Date data){
        return format.format(data);
    }
    
    public static String dataHoraToString(Date data){
        return formatDtHr.format(data);
    }
}
