/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DAOMysql;
import DAO.DAPet;
import DAO.DAServico;
import Models.Pet;
import Models.Servico;
import Models.ServicoHistorico;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class RNServico {
    
    DAServico daServico;
    
    public boolean inserirServico(Servico servico) {
        boolean bResultado = true;
        
        try{
           
            if (servico.getNome().trim().isEmpty()){
                throw new IllegalArgumentException("O nome do serviço é obrigatório!");
            }
            
            if (servico.getDescricao().trim().isEmpty()){
                throw new IllegalArgumentException("A descrição é obrigatória!");
            }
            
            if (servico.getTempoMedio() < 0){
                throw new IllegalArgumentException("Tempo médio inválido");
            }
            
            if (servico.getPreco() <= 0){
                throw new IllegalArgumentException("Preço inválido!");
            }
            
            servico.setDtCadastro(Calendar.getInstance().getTime());
            
            daServico = new DAServico();
            daServico.inserirServico(servico);
            
            JOptionPane.showMessageDialog(null, "Serviço inserido com sucesso!");
        }
        catch (IllegalArgumentException erro){
            bResultado = false;
            JOptionPane.showMessageDialog(null, erro.getMessage());
        }
        catch (Exception erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        finally {
           // fechamos a conexão com o banco de dados
           DAOMysql.disposeConnection(daServico.conn);
        }
        
        return bResultado;
    }
    
    public boolean editarServico(Servico servico) {
        boolean bResultado = true;
        
        try{
            
             if (servico.getNome().trim().isEmpty()){
                throw new IllegalArgumentException("O nome do serviço é obrigatório!");
            }
            
            if (servico.getDescricao().trim().isEmpty()){
                throw new IllegalArgumentException("A descrição é obrigatória!");
            }
            
            if (servico.getTempoMedio() < 0){
                throw new IllegalArgumentException("Tempo médio inválido");
            }
            
            if (servico.getPreco() <= 0){
                throw new IllegalArgumentException("Preço inválido!");
            }
            
            daServico = new DAServico();
            daServico.editarServico(servico);
            
            JOptionPane.showMessageDialog(null, "Serviço editado com sucesso!");
        }
        catch (IllegalArgumentException erro){
            bResultado = false;
            JOptionPane.showMessageDialog(null, erro.getMessage()); 
        }
        catch (Exception erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        finally {
           // fechamos a conexão com o banco de dados
           DAOMysql.disposeConnection(daServico.conn);
        }
        
        return bResultado;
    }
    
    public ArrayList<Servico> listarServico() {

        try{
            return new DAServico().listarTodosServicos();
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        
        return new ArrayList();
    }
    
    public ArrayList<ServicoHistorico> retornaHistoricoServicos(int idCliente){
         try{
            return new DAServico().retornaHistoricoServicos(idCliente);
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        
        return new ArrayList();
    }
}
