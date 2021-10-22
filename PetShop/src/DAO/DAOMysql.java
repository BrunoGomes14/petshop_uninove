/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bruno
 */
public class DAOMysql {
    
    // Dados de conexao
    private static final String banco = "db_petshop_uninove";
    private static final String urlBanco = "vps34630.publiccloud.com.br";
    private static final String porta = "3306";
    
    // Usuário e senha de acesso
    private static final String usuario = "facudb";
    private static final String senha = "Facudb@21";
    
    
    public static Connection getConnection() throws Exception {
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://"+urlBanco+":"+porta+"/"+banco, usuario, senha);
        }
        catch (Exception erro){
            throw new Exception("Não foi possível realizar a conexão com o banco");
        }
        
        return conn;
    }
    
    public static boolean disposeConnection(Connection conn){
        try{
            if (conn != null){
                conn.close();
            }
            return true;
        }
        catch (Exception erro){
            return false;
        }
        
    }
}
