/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DAProdutos;
import DAO.DAOMysql;
import Models.Produto;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Beatriz
 */
public class RNProduto {
    
    DAProdutos daProdutos;
    
    public boolean inserirProdutos(String produto, String descProduto, int qtd, double preco) {
    
        boolean rRetorno = true;
         
            try{
                if(produto.trim().isEmpty()){
                    throw new IllegalArgumentException("O nome do produto é obrigatório!");
                }
                if(preco <0){
                    throw new IllegalArgumentException("Preço inválido!");
                }
                if(descProduto.trim().isEmpty()){
                    throw new IllegalArgumentException("O campo do desconto é obrigatório");
                }
                if (qtd <0) {
                    throw new IllegalArgumentException("Quantidade do estoque inválido!");
                }
                
                daProdutos = new DAProdutos();
                daProdutos.inserirProduto(produto, descProduto, preco, qtd);
                
                JOptionPane.showMessageDialog(null, "Produto inserido com sucesso!");
            }
            catch(IllegalArgumentException erro){
                rRetorno = false;
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
            catch(Exception erro){
                rRetorno = false;
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
            finally {
                  DAOMysql.disposeConnection(daProdutos.conn);
            }
            
             return rRetorno;             
    }
     public boolean editarCliente(String produto, String descProduto, int qtd, double preco) {
        boolean rRetorno = false;
        
            try{
                if(produto.trim().isEmpty()){
                    throw new IllegalArgumentException("O nome do produto é obrigatório!");
                }
                if(preco <0){
                    throw new IllegalArgumentException("Preço inválido!");
                }
                if(descProduto.trim().isEmpty()){
                    throw new IllegalArgumentException("O campo do desconto é obrigatório");
                }
                if (qtd <0) {
                    throw new IllegalArgumentException("Quantidade do estoque inválido!");
                }
                
                daProdutos = new DAProdutos();
                daProdutos.inserirProduto(produto, descProduto, preco, qtd);
                
                JOptionPane.showMessageDialog(null, "Produto editado com sucesso!");
            }
            catch(IllegalArgumentException erro){
                rRetorno = false;
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
            catch(Exception erro){
                rRetorno = false;
                JOptionPane.showMessageDialog(null, erro.getMessage());
            }
            finally {
                  DAOMysql.disposeConnection(daProdutos.conn);
            }
            
             return rRetorno;             
    }  
    public boolean verificarProduto(String produto){
     try {

            if (produto.trim().isEmpty()) {
                throw new IllegalArgumentException("O produto é obrigatório!");
            }
    
            daProdutos = new DAProdutos();
            return daProdutos.verificaProduto(produto);
            
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            DAOMysql.disposeConnection(daProdutos.conn);
        }

        return false;
    }
    public ArrayList<Produto> listarTodos(){
        try{
            return new DAProdutos().listarTodosProdutos();
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        
        return new ArrayList();
    
    }
    public void apagarProduto(int idProduto){
        try {
            daProdutos = new DAProdutos();
            daProdutos.apagarProduto(idProduto);
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daProdutos.conn);
        }
    }
}
