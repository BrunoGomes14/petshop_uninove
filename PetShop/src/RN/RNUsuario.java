/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DAOMysql;
import DAO.DAUsuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class RNUsuario {
    
    DAUsuario daUsuario = null;
    
    public void inserirUsuario(String usuario, String senha, String cargo) {
        
       try{
           if (usuario.length() == 0){
               throw new IllegalArgumentException("O nome do usuário é obrigatório!");
           }
           
           if (senha.length() == 0 || senha.length() < 8){
               throw new IllegalArgumentException("A senha do usuário precisa possuir no mínimo 8 caracteres!");
           }
           
           // criamos o objeto que manuseia o banco de dados
           daUsuario = new DAUsuario();
           
           // verificamos se o usuário já existe
           if (daUsuario.obtemCargoUsuario(usuario).equals("")){
              throw new IllegalArgumentException("Este usuário já existe!");
           }
           
           // adiciona o usuário ao banco de dados
           daUsuario.insereUsuario(usuario, senha, cargo);
       }
       catch (IllegalArgumentException erro){
           JOptionPane.showMessageDialog(null, erro.getMessage());
       }
       catch (Exception erro) {
           JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
       }
       finally {
           // fechamos a conexão com o banco de dados
           DAOMysql.disposeConnection(daUsuario.conn);
       }
    }
}
