/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DAOMysql;
import DAO.DAUsuario;
import Models.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class RNUsuario {

    DAUsuario daUsuario = null;

    public boolean inserirUsuario(String usuario, String senha, String confSenha, String cargo) {
        boolean bResultado = false;
        
        try {
            if (usuario.length() == 0) {
                throw new IllegalArgumentException("O nome do usuário é obrigatório!");
            }

            if (senha.length() == 0 || senha.length() < 8) {
                throw new IllegalArgumentException("A senha do usuário precisa possuir no mínimo 8 caracteres!");
            }
            
            if (!senha.equals(confSenha)){
                 throw new IllegalArgumentException("A senha não conferem!");
            }

            // criamos o objeto que manuseia o banco de dados
            daUsuario = new DAUsuario();

            // verificamos se o usuário já existe
            if (!daUsuario.obtemCargoUsuario(usuario).equals("")) {
                throw new IllegalArgumentException("Este usuário já existe!");
            }

            // adiciona o usuário ao banco de dados
            daUsuario.insereUsuario(usuario, senha, cargo);
            bResultado = true;
            
            JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }
        
        return bResultado;
    }

    public boolean verificaUsuario(String usuario, String senha) {
        try {

            if (usuario.trim().isEmpty()) {
                throw new IllegalArgumentException("O usuário é obrigatório!");
            }

            if (senha.trim().isEmpty()) {
                throw new IllegalArgumentException("Digite sua senha!");
            }
           
            daUsuario = new DAUsuario();
            return daUsuario.verificaUsuario(usuario, senha);
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }

        return false;
    }

    public String obtemCargoUsuario(String usuario) {
        try {
            daUsuario = new DAUsuario();
            return daUsuario.obtemCargoUsuario(usuario);
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }

        return "";
    }

    public ArrayList<Usuario> listarTodosUsuarios() {
        try {
            daUsuario = new DAUsuario();
            return daUsuario.listarTodosUsuarios();
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }
        
        return new ArrayList();
    }

    public void editarUsuario(Usuario usuario) throws Exception {
        try {
            
             if (usuario.getUsuario().length() == 0) {
                throw new IllegalArgumentException("O nome do usuário é obrigatório!");
            }

            if (usuario.getSenha().length() == 0 || usuario.getSenha().length() < 8) {
                throw new IllegalArgumentException("A senha do usuário precisa possuir no mínimo 8 caracteres!");
            }
            
            daUsuario = new DAUsuario();
            daUsuario.editarUsuario(usuario);
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }
    }

    public void excluirUsuario(int idUsuario) {
        try {
            daUsuario = new DAUsuario();
            daUsuario.excluirUsuario(idUsuario);
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daUsuario.conn);
        }
    }
}
