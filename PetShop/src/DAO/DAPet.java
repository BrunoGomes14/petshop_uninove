/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class DAPet {
    
    public Connection conn = null;
    private PreparedStatement psQuery;
    
    public DAPet() throws Exception {
       
       // criamos a conexão com o banco, para realizar o manuseio de dados
       conn = DAOMysql.getConnection();        
    }
    
    public void inserirPet(Pet pet) throws Exception {
        // query a ser executada
        String sQuery = "INSERT INTO tb_pet(nm_pet, ds_rga, id_responsavel, dt_nascimento, ds_complemento) values (?, ?, ?, ?, ?)";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, pet.getNome());
        psQuery.setString(2, pet.getRga());
        psQuery.setInt(3, pet.getResponsavel().getId());
        psQuery.setDate(4, new java.sql.Date(pet.getDtNascimento().getTime()));       
        psQuery.setString(5, pet.getComplemento());

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public void editarPet(Pet pet) throws Exception {
        // query a ser executada
        String sQuery = "UPDATE tb_pet set nm_pet = ?, ds_rga = ?, dt_nascimento = ?, ds_complemento = ? where id_pet = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, pet.getNome());
        psQuery.setString(2, pet.getRga());
        psQuery.setDate(3, new java.sql.Date(pet.getDtNascimento().getTime()));
        psQuery.setString(4, pet.getComplemento());
        psQuery.setInt(5, pet.getId());

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public ArrayList<Pet> listarPets(int idResponsavel) throws Exception {
        ResultSet result;
        Pet pet;
        DACliente daCliente;
        ArrayList<Pet> arrPet = new ArrayList<>(); 
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_PET";
        
        if (idResponsavel > 0)
        {
            sQuery += " WHERE ID_RESPONSAVEL = ? AND FL_ATIVO = 1";
        }
        else 
        {
            sQuery += " WHERE FL_ATIVO = 1";
        }
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        if (idResponsavel > 0)
            psQuery.setInt(1, idResponsavel);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto com os dados do usuário obtido nesta linha
            pet = new Pet();
            pet.setId(result.getInt("id_pet"));
            pet.setNome(result.getString("nm_pet"));
            pet.setComplemento(result.getString("ds_complemento"));
            pet.setDtNascimento(result.getDate("dt_nascimento"));
            pet.setRga(result.getString("ds_rga"));
            
            // instanciamos o objeto que busca no banco os dados do cliente
            daCliente = new DACliente();
                    
            // buscamos os dados do dono do pet
            pet.setResponsavel(daCliente.retornaCliente(result.getInt("id_responsavel")));

            // adicionamos o objeto com os dados para nossa lista
            arrPet.add(pet);
        }
        
        conn.close();
        
        return arrPet;
    }
    
    public Pet retornaPet(int idPet) throws Exception {
        ResultSet result;
        Pet pet = null;
        DACliente daCliente;
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_PET WHERE ID_PET = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        psQuery.setInt(1, idPet);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        pet = new Pet();
        
        // se encontramos dados para ler
        if (result.next())
        {
            // criamos um objeto com os dados do usuário obtido nesta linha
            pet.setId(result.getInt("id_pet"));
            pet.setNome(result.getString("nm_pet"));
            pet.setComplemento(result.getString("ds_complemento"));
            pet.setDtNascimento(result.getDate("dt_nascimento"));
            pet.setRga(result.getString("ds_rga"));
            
            // instanciamos o objeto que busca no banco os dados do cliente
            daCliente = new DACliente();
                    
            // buscamos os dados do dono do pet
            pet.setResponsavel(daCliente.retornaCliente(result.getInt("id_responsavel")));
        }
        
        conn.close();
        
        return pet;
    }
    
    public void excluirPet(int idPet) throws Exception {
        // query a ser executada
        String sQuery = "UPDATE TB_PET SET FL_ATIVO = 0 WHERE ID_PET = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setInt(1, idPet);

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
           
}
