/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class DAUsuario {
    
    public Connection conn = null;
    private PreparedStatement psQuery;
    
    public DAUsuario() throws Exception {
        
       // criamos a conexão com o banco, para realizar o manuseio de dados
       conn = DAOMysql.getConnection();        
    }
    
    public void insereUsuario(String usuario, String senha, String cargo) throws Exception {
      
        // query a ser executada
        String sQuery = "INSERT INTO TB_USUARIOS(ds_usuario, ds_senha, ds_cargo, nr_cargo) values (?, ?, ?, 1)";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, usuario.trim());
        psQuery.setString(2, senha);
        psQuery.setString(3, cargo);
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public boolean verificaUsuario(String usuario, String senha) throws Exception {
        ResultSet result;
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_USUARIOS WHERE DS_USUARIO = ? AND DS_SENHA = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, usuario);
        psQuery.setString(2, senha);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        return result.next();
    }
    
    public String obtemCargoUsuario(String usuario) throws Exception {
        ResultSet result;
        String cargo = "";
        
        // query a ser executada
        String sQuery = "SELECT DS_CARGO FROM TB_USUARIOS WHERE DS_USUARIO = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, usuario);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        if (result.next())
        {
            // recuperamos do resultado do nosso select, o valor da coluna desejada
            cargo = result.getString("DS_CARGO");
        }
        
        return cargo;
    }
    
    public ArrayList<Usuario> listarTodosUsuarios() throws Exception {
        ResultSet result;
        Usuario usuario;
        ArrayList<Usuario> arrUsuarios = new ArrayList<>(); 
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_USUARIOS WHERE DS_CARGO != \"ADM\"";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
    
        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto com os dados do usuário obtido nesta linha
            usuario = new Usuario();
            usuario.setId(result.getInt("ID_USUARIO"));
            usuario.setUsuario(result.getString("DS_USUARIO"));
            usuario.setCargo(result.getString("DS_CARGO"));
            usuario.setSenha(result.getString("DS_SENHA"));
            
            // adicionamos o objeto com os dados para nossa lista
            arrUsuarios.add(usuario);
        }
        
        return arrUsuarios;
    }
    
    public void editarUsuario(Usuario usuario) throws Exception {
        String sQuery = "";
        
        // query a ser executada
        sQuery = "UPDATE TB_USUARIOS SET" +
                " DS_USUARIO = ?, " +
                " DS_SENHA = ?," +
                " DS_CARGO = ? " +
                " WHERE ID_USUARIO = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, usuario.getUsuario());
        psQuery.setString(2, usuario.getSenha());
        psQuery.setString(3, usuario.getCargo());
        psQuery.setInt(4, usuario.getId());
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public void excluirUsuario(int idUsuario) throws Exception {
        String sQuery = "";
        
        // query a ser executada
        sQuery = " DELETE FROM TB_USUARIOS " +
                 " WHERE ID_USUARIO = ? ";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setInt(1, idUsuario);
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
}
