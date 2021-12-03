/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Bruno
 */
public class DAProdutos{
    
    public Connection conn = null;
    private PreparedStatement psQuery;
    
    public DAProdutos() throws Exception {
       
       // criamos a conexão com o banco, para realizar o manuseio de dados
       conn = DAOMysql.getConnection();        
    }
    
    public void inserirProduto(String produto, String descProduto, double preco, int qtd) throws Exception {
        // query a ser executada
        String sQuery = "INSERT INTO tb_produtos(nm_produto, ds_produto, nr_preco, qtd_estoque) values (?, ?, ?, ?)";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, produto);
        psQuery.setString(2, descProduto);
        psQuery.setDouble(3, preco);
        psQuery.setInt(4, qtd);
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public void editarCliente(int idProduto, String produto, String descProduto, double preco, int qtd) throws Exception {
        // query a ser executada
        String sQuery = "UPDATE tb_produtos set nm_produto = ?, ds_produto = ?, nr_preco = ?, qtd_estoque = ? where id_produto = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, produto);
        psQuery.setString(2, descProduto);
        psQuery.setDouble(3, preco);
        psQuery.setInt(4, qtd);
        psQuery.setInt(5, idProduto);

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
    
    public boolean verificaProduto(String produto) throws Exception {
        ResultSet result;
        
        // query a ser executada
        String sQuery = "select id_produto from tb_produtos where nm_produto = ?";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setString(1, produto);
        
        // executamos o comando no banco, para buscar os dados
        result = psQuery.executeQuery();
        
        return result.next();
    }
    
    public ArrayList<Produto> listarTodosProdutos(String sFiltro) throws Exception {
        ResultSet result;
        Produto produto;
        ArrayList<Produto> arrProduto = new ArrayList<>(); 
        
        // query a ser executada
        String sQuery = "SELECT * FROM TB_PRODUTOS ";
        
        if (sFiltro.trim().length() > 0) {
            sQuery += " WHERE NM_PRODUTO LIKE \"%" + sFiltro + "%\"";
        }
        
        sQuery += " ORDER BY QTD_ESTOQUE, NM_PRODUTO ";
        
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();
        
        // se encontramos dados para ler
        while (result.next())
        {
            // criamos um objeto com os dados do usuário obtido nesta linha
            produto = new Produto();
            produto.setId(result.getInt("id_produto"));
            produto.setProduto(result.getString("nm_produto"));
            produto.setDescProduto(result.getString("ds_produto"));
            produto.setPreco(result.getDouble("nr_preco"));
            produto.setQtdEstoque(result.getInt("qtd_estoque"));

            // adicionamos o objeto com os dados para nossa lista
            arrProduto.add(produto);
        }
        
        return arrProduto;
    }
    
    public void apagarProduto(int idProduto) throws Exception {
        String sQuery = "";
        
        // query a ser executada
        sQuery = " DELETE FROM TB_PRODUTOS " +
                 " WHERE ID_PRODUTO = ? ";
    
        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);
        
        // preenchemos os parametros informados na query
        psQuery.setInt(1, idProduto);
        
        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }
}
