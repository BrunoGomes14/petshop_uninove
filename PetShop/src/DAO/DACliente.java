/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Models.Cliente;
import Models.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Bruno
 */
public class DACliente {

    public Connection conn = null;
    private PreparedStatement psQuery;

    public DACliente() throws Exception {

        // criamos a conexão com o banco, para realizar o manuseio de dados
        conn = DAOMysql.getConnection();
    }

    public void inserirCliente(String cliente, String cpf, Date dtNascimento, Date dtCadastro, String nrTelefone) throws Exception {
        // query a ser executada
        String sQuery = "INSERT INTO tb_cliente(nm_cliente, ds_cpf, dt_nascimento, dt_cadastro, nr_telefone) values (?, ?, ?, ?, ?)";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // preenchemos os parametros informados na query
        psQuery.setString(1, cliente);
        psQuery.setString(2, cpf);
        psQuery.setDate(3, new java.sql.Date(dtNascimento.getTime()));
        psQuery.setDate(4, new java.sql.Date(dtCadastro.getTime()));
        psQuery.setString(5, nrTelefone);

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }

    public void editarCliente(int idCliente, String cliente, String cpf, Date dtNascimento, String nrTelefone) throws Exception {
        // query a ser executada
        String sQuery = "UPDATE tb_cliente set nm_cliente = ?, ds_cpf = ?, dt_nascimento = ?, nr_telefone = ? where id_cliente = ?";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // preenchemos os parametros informados na query
        psQuery.setString(1, cliente);
        psQuery.setString(2, cpf);
        psQuery.setDate(3, new java.sql.Date(dtNascimento.getTime()));
        psQuery.setString(4, nrTelefone);
        psQuery.setInt(5, idCliente);

        // executamos o comando no banco, para efetivar os dados
        psQuery.executeUpdate();
    }

    public boolean verificaCPF(String cpf) throws Exception {
        ResultSet result;

        // query a ser executada
        String sQuery = "select id_cliente from tb_cliente where ds_cpf = ?";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // preenchemos os parametros informados na query
        psQuery.setString(1, cpf);

        // executamos o comando no banco, para buscar os dados
        result = psQuery.executeQuery();

        return result.next();
    }

    public boolean verificaCPF(String cpf, int idCliente) throws Exception {
        ResultSet result;

        // query a ser executada
        String sQuery = "select id_cliente from tb_cliente where ds_cpf = ? and id_cliente != ?";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // preenchemos os parametros informados na query
        psQuery.setString(1, cpf);
        psQuery.setInt(2, idCliente);

        // executamos o comando no banco, para buscar os dados
        result = psQuery.executeQuery();

        return result.next();
    }

    public ArrayList<Cliente> listarTodosUsuarios(String sNome) throws Exception {
        ResultSet result;
        Cliente cliente;
        ArrayList<Cliente> arrCliente = new ArrayList<>();

        // query a ser executada
        String sQuery = " SELECT * FROM TB_CLIENTE ";

        if (sNome.length() > 0) {
            sQuery += " WHERE nm_cliente LIKE '%" + sNome + "%'";
        }

        sQuery += " ORDER BY nm_cliente";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();

        // se encontramos dados para ler
        while (result.next()) {
            // criamos um objeto com os dados do usuário obtido nesta linha
            cliente = new Cliente();
            cliente.setId(result.getInt("ID_CLIENTE"));
            cliente.setCliente(result.getString("NM_CLIENTE"));
            cliente.setDtNascimento(result.getDate("DT_NASCIMENTO"));
            cliente.setDtCadastro(result.getDate("DT_CADASTRO"));
            cliente.setDtCadastro(result.getDate("DT_CADASTRO"));
            cliente.setNrTelefone(result.getString("nr_telefone"));
            cliente.setCpf(result.getString("ds_cpf"));

            // adicionamos o objeto com os dados para nossa lista
            arrCliente.add(cliente);
        }

        return arrCliente;
    }

    public Cliente retornaCliente(int idCliente) throws Exception {
        ResultSet result;
        Cliente cliente;

        // query a ser executada
        String sQuery = "SELECT * FROM TB_CLIENTE WHERE ID_CLIENTE = ?";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // Define o parametro
        psQuery.setInt(1, idCliente);

        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();

        // criamos um objeto para guardar os dados do cliente
        cliente = new Cliente();

        // se encontramos dados para ler
        if (result.next()) {
            // recuperamos o resultado
            cliente.setId(result.getInt("ID_CLIENTE"));
            cliente.setCliente(result.getString("NM_CLIENTE"));
            cliente.setDtNascimento(result.getDate("DT_NASCIMENTO"));
            cliente.setDtCadastro(result.getDate("DT_CADASTRO"));
            cliente.setNrTelefone(result.getString("nr_telefone"));
            cliente.setCpf(result.getString("ds_cpf"));
        }

        return cliente;
    }

        public int retornaUltimoId() throws Exception {
        ResultSet result;
        int id = 0;

        // query a ser executada
        String sQuery = "SELECT max(id_cliente) id_cliente FROM TB_CLIENTE";

        // criamos a query para executar no mysql
        psQuery = conn.prepareStatement(sQuery);

        // executamos o comando no banco, para efetivar os dados
        result = psQuery.executeQuery();

        // se encontramos dados para ler
        if (result.next()) {
            // recuperamos o resultado
            id = result.getInt("id_cliente");
        }

        return id;
    }
}
