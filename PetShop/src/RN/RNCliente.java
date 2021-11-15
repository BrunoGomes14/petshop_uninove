/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RN;

import DAO.DACliente;
import DAO.DAOMysql;
import Models.Cliente;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno
 */
public class RNCliente {

    DACliente daCliente = null;

    public boolean inserirCliente(String cliente, String cpf, Date dtNascimento, String nrTelefone) {
        boolean bResultado = true;

        try {
            long diferencaTempo;
            long qtdAnos = 0;

            if (cliente.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do cliente é obrigatório");
            }

            if (!isCPF(cpf)) {
                throw new IllegalArgumentException("CPF do cliente é inválido");
            }

            daCliente = new DACliente();

            if (daCliente.verificaCPF(cpf)) {
                throw new IllegalArgumentException("CPF já cadastrado!");
            }

            if (nrTelefone.length() < 13) {
                throw new IllegalArgumentException("Telefone inválido");
            }

            diferencaTempo = Calendar.getInstance().getTime().getTime() - dtNascimento.getTime();
            qtdAnos = diferencaTempo / (1000l * 60 * 60 * 24 * 365);

            if (qtdAnos < 18) {
                throw new IllegalArgumentException("Cliente precisa ter no mínimo 18 anos");
            }

            daCliente.inserirCliente(cliente, cpf, dtNascimento, Calendar.getInstance().getTime(), nrTelefone);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daCliente.conn);
        }

        return bResultado;
    }

    public boolean editarCliente(int idCliente, String cliente, String cpf, Date dtNascimento, String nrTelefone) {
        boolean bResultado = true;

        try {
            long diferencaTempo;
            long qtdAnos = 0;

            if (cliente.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do cliente é obrigatório");
            }

            if (!isCPF(cpf)) {
                throw new IllegalArgumentException("CPF do cliente é inválido");
            }

            daCliente = new DACliente();

            if (daCliente.verificaCPF(cpf, idCliente)) {
                throw new IllegalArgumentException("CPF já cadastrado!");
            }

            if (nrTelefone.length() < 14) {
                throw new IllegalArgumentException("Telefone inválido");
            }

            diferencaTempo = Calendar.getInstance().getTime().getTime() - dtNascimento.getTime();
            qtdAnos = diferencaTempo / (1000l * 60 * 60 * 24 * 365);

            if (qtdAnos < 18) {
                throw new IllegalArgumentException("Cliente precisa ter no mínimo 18 anos");
            }

            daCliente.editarCliente(idCliente, cliente, cpf, dtNascimento, nrTelefone);

            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
        } catch (IllegalArgumentException erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, erro.getMessage());
        } catch (Exception erro) {
            bResultado = false;
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daCliente.conn);
        }

        return bResultado;
    }

    public ArrayList<Cliente> listarTodosUsuarios(String sNome) {

        try {
            daCliente = new DACliente();
            return daCliente.listarTodosUsuarios(sNome);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daCliente.conn);
        }

        return new ArrayList();
    }

    public Cliente retornaCliente(int idCliente) {

        Cliente cliente = new Cliente();

        try {
            daCliente = new DACliente();
            cliente = daCliente.retornaCliente(idCliente);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daCliente.conn);
        }

        return cliente;
    }

    public static boolean isCPF(String CPF) {

        CPF = CPF.replace("-", "").replace(".", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
    
    public int retornaUltimoId(){
       int id = 0;
        
        try {
            daCliente = new DACliente();
            id = daCliente.retornaUltimoId();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro Inesperado!\n\n" + erro.getMessage());
        } finally {
            // fechamos a conexão com o banco de dados
            DAOMysql.disposeConnection(daCliente.conn);
        }
        
        return id;
    }
}
