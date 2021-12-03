/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Pet;
import Models.Servico;
import Models.ServicoHistorico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bruno
 */
public class DAServico {
    public Connection conn = null;
    private PreparedStatement psQuery;
    
    public DAServico() throws Exception {
        
       // criamos a conexão com o banco, para realizar o manuseio de dados
       conn = DAOMysql.getConnection();        
    }
    
    public void inserirServico(Servico servico) throws Exception {
        // query a ser executada
        String sQuery = "INSERT INTO tb_servicos (nm_servico, ds_servico, nr_tempo_medio_min, nr_preco, dt_cadastro) values (?, ?, ?, ?, ?)";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, servico.getNome());
        psQuery.setString(2, servico.getDescricao());
        psQuery.setDouble(3, servico.getTempoMedio());
        psQuery.setDouble(4, servico.getPreco());
        psQuery.setDate(5, new java.sql.Date(servico.getDtCadastro().getTime()));
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public void editarServico(Servico servico) throws Exception {
        // query a ser executada
        String sQuery = "UPDATE tb_servicos set nm_servico = ?, ds_servico = ?, nr_tempo_medio_min = ?, nr_preco = ? where id_servicos = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, servico.getNome());
        psQuery.setString(2, servico.getDescricao());
        psQuery.setDouble(3, servico.getTempoMedio());
        psQuery.setDouble(4, servico.getPreco());
        psQuery.setInt(5, servico.getId());
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public ArrayList<Servico> listarTodosServicos() throws Exception {
        ResultSet result;
        Servico servico;
        ArrayList<Servico> arrServico = new ArrayList<>(); 
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_SERVICOS;";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto com os dados do usuário obtido nesta linha
            servico = new Servico();
            servico.setId(result.getInt("id_servicos"));
            servico.setNome(result.getString("nm_servico"));
            servico.setDescricao(result.getString("ds_servico"));
            servico.setDtCadastro(result.getDate("DT_CADASTRO"));
            servico.setPreco(result.getDouble("nr_preco"));
            servico.setTempoMedio(result.getDouble("nr_tempo_medio_min"));
            
            // adicionamos o objeto com os dados para nossa lista
            arrServico.add(servico);
        }
        
        return arrServico;
    }
    
    public Servico retornaServico(int idServico) throws Exception {
        ResultSet result;
        Servico servico;
        
        // query a ser executada
        String sQuery = "SELECT * FROM tb_servicos WHERE ID_CLIENTE = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // Define o parametro
        psQuery.setInt(0, idServico);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // criamos um objeto para guardar os dados do cliente
        servico = new Servico();
        
        // se encontramos dados para ler
        if (result.next())
        {
            // recuperamos o resultado
            servico.setId(result.getInt("id_servicos"));
            servico.setNome(result.getString("nm_servico"));
            servico.setDescricao(result.getString("ds_servico"));
            servico.setDtCadastro(result.getDate("DT_CADASTRO"));
            servico.setPreco(result.getDouble("nr_preco"));
        }
        
        return servico;
    }
    
    public void registraServico(int idServico, int idPet, int idUsuario, Date dtCadastro) throws Exception
    {
        ResultSet result;
        
        // query a ser executada
        String sQuery = "INSERT INTO TB_SERVICOS_HISTORICO(ID_SERVICO, ID_USUARIO, ID_PET, DT_SERVICO_PRESTADO) values (?, ?, ?, ?)";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // Define o parametro
        psQuery.setInt(1, idServico);
        psQuery.setInt(2, idUsuario);
        psQuery.setInt(3, idPet);
        psQuery.setObject(4, new java.sql.Timestamp(new java.sql.Date(dtCadastro.getTime()).getTime()));
        
        // executamos o comando no banco, para efetivar os dados
       psQuery.executeUpdate();
    }
    
    public ArrayList<ServicoHistorico> retornaHistoricoServicos(int idCliente) throws Exception{
        ResultSet result;
        ServicoHistorico servicoHistorico;
        ArrayList<ServicoHistorico> arrServicos = new ArrayList<>();
        
        // query a ser executada
        String sQuery = " SELECT * FROM tb_servicos_historico h ";
        
        if (idCliente > 0){
            sQuery += " where p.id_responsavel = ? ";
        }
        
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        if (idCliente > 0){ 
            // Define o parametro
            psQuery.setInt(1, idCliente);
        }
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        

        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto para guardar os dados do cliente
            servicoHistorico = new ServicoHistorico();
            
            // recuperamos o resultado
            servicoHistorico.setId(result.getInt("id_historico"));
            servicoHistorico.setDtPrestacaoServico(result.getDate("dt_servico_prestado"));
            
            arrServicos.add(servicoHistorico);
        }
        
        return arrServicos;
    }
    
     public ArrayList<ServicoHistorico> retornaHistoricoServicos(int idCliente, Date dtInicio, Date dtFim, int idUsuario) throws Exception{
        ResultSet result;
        ServicoHistorico servicoHistorico;
        ArrayList<ServicoHistorico> arrServicos = new ArrayList<>();
        
        // query a ser executada
        String sQuery  = " SELECT * FROM tb_servicos_historico h ";
               sQuery += " inner join tb_servicos s on s.id_servicos = h.id_servico ";
               sQuery += " inner join tb_pet p on p.id_pet = h.id_pet ";
               sQuery += " inner join tb_cliente c on p.id_responsavel = c.id_cliente ";
               sQuery += " left join tb_usuarios u on u.id_usuario = h.id_usuario ";
               sQuery += " where date(dt_servico_prestado) between date(?) and date(?) ";
        
        if (idCliente > 0){
            sQuery += " and p.id_responsavel = ? ";
        }
        
        if (idUsuario > 0){
            sQuery += " and h.id_usuario = ? ";
        }
        
        sQuery += " order by dt_servico_prestado desc ";
        
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        psQuery.setObject(1, new java.sql.Timestamp(new java.sql.Date(dtInicio.getTime()).getTime()));
        psQuery.setObject(2, new java.sql.Timestamp(new java.sql.Date(dtFim.getTime()).getTime()));
        
        if (idCliente > 0){ 
            // Define o parametro
            psQuery.setInt(3, idCliente);
            
            if (idUsuario > 0){
                psQuery.setInt(4, idUsuario);
            }
        }
        else if (idUsuario > 0) {
            psQuery.setInt(3, idUsuario);
        }
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto para guardar os dados do cliente
            servicoHistorico = new ServicoHistorico();
            
            // recuperamos o resultado
            servicoHistorico.setId(result.getInt("id_historico"));
            servicoHistorico.setNomeServico(result.getString("nm_servico"));
            servicoHistorico.setDtPrestacaoServico(result.getTimestamp("dt_servico_prestado"));
            servicoHistorico.setCliente(result.getString("nm_cliente"));
            
            Pet pet = new Pet();
            if (result.getInt("id_pet") > 0){
                pet.setId(result.getInt("id_pet"));
                pet.setNome(result.getString("nm_pet"));
                pet.setComplemento(result.getString("ds_complemento"));
                pet.setDtNascimento(result.getDate("dt_nascimento"));
                pet.setRga(result.getString("ds_rga"));
                servicoHistorico.setPet(pet);
            }
            
            if (result.getInt("h.id_usuario") > 0){
                servicoHistorico.setUsuario(result.getString("u.ds_usuario"));
            }
            else {
                servicoHistorico.setUsuario("adm");
            }
            
            arrServicos.add(servicoHistorico);
        }
        
        return arrServicos;
    }
     
}
