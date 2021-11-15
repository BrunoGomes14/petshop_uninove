/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DACliente;
import DAO.DAOMysql;
import DAO.DAPet;
import Models.Pet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class RNPets {
    
    DAPet daPet;
    
    public boolean inserirPet(Pet pet) throws Exception {
        boolean bResultado = true;
        
        try{
           
            if (pet.getNome().trim().isEmpty()){
                throw new IllegalArgumentException("O nome do pet é obrigatório!");
            }
            
            if (pet.getRga().trim().isEmpty()){
                throw new IllegalArgumentException("O RGA do pet é obrigatório!");
            }
            
            if (pet.getDtNascimento() == null){
                throw new IllegalArgumentException("Data de nascimento inválida");
            }
            
            if (pet.getDtNascimento().after(Calendar.getInstance().getTime())){
                throw new IllegalArgumentException("Data de nascimento inválida");
            }
            
            daPet = new DAPet();
            daPet.inserirPet(pet);
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
           DAOMysql.disposeConnection(daPet.conn);
        }
        
        return bResultado;
    }
    
    public boolean editarPet(Pet pet) throws Exception {
        boolean bResultado = true;
        
        try{
            
            if (pet.getId() <= 0){
               throw new IllegalArgumentException("Edição inválida!");
            }
               
            if (pet.getNome().trim().isEmpty()){
                throw new IllegalArgumentException("O nome do pet é obrigatório!");
            }
            
            if (pet.getRga().trim().isEmpty()){
                throw new IllegalArgumentException("O RGA do pet é obrigatório!");
            }
            
            if (pet.getDtNascimento().after(Calendar.getInstance().getTime())){
                throw new IllegalArgumentException("Data de nascimento inválida");
            }
            
            daPet = new DAPet();
            daPet.editarPet(pet);
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
           DAOMysql.disposeConnection(daPet.conn);
        }
        
        return bResultado;
    }
    
    public ArrayList<Pet> listarPets(int idResponsavel) {

        try{
            return new DAPet().listarPets(idResponsavel);
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        
        return new ArrayList();
    }
    
    public Pet retornaPets(int idPet) {

        try{
            return new DAPet().retornaPet(idPet);
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        
        return new Pet();
    }
    
     public void excluirPet(int idPet) {
        try{
            
            daPet = new DAPet();
            daPet.excluirPet(idPet);
        }
        catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        }
        finally {
           // fechamos a conexão com o banco de dados
           DAOMysql.disposeConnection(daPet.conn);
        }
    }
}
