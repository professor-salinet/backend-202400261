package model;
import controller.*;

import java.sql.*;
import java.util.*;

public class TelaDeAtualizacaoModel {
    public static ArrayList<String> popularCbxIdModel() {
        ArrayList<String> ids = new ArrayList<String>();
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlPopularCbxId = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            Statement stmSqlPopularCbxId = conexao.createStatement();
            ResultSet rstSqlPopularCbxId = stmSqlPopularCbxId.executeQuery(strSqlPopularCbxId);
            while (rstSqlPopularCbxId.next()) {
                ids.add(rstSqlPopularCbxId.getString("id"));
            }
            stmSqlPopularCbxId.close();
        } catch (Exception e) {
            TelaDeAtualizacaoController.notificarUsuario("Ops! Ocorreu um problema no servidor e não será possível carregar os ids neste momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
        return ids;
    }

    public static ArrayList<String> atualizarCamposModel(String strId) {
        ArrayList<String> dados = new ArrayList<String>();
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where id = " + strId + ";";
            Statement stmSqlAtualizarCampos = conexao.createStatement();
            ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);
            if (rstSqlAtualizarCampos.next()) {
                dados.add(rstSqlAtualizarCampos.getString("nome"));
                TelaDeAtualizacaoController.txtNomeCarregado = rstSqlAtualizarCampos.getString("nome");
                dados.add(rstSqlAtualizarCampos.getString("email"));
                TelaDeAtualizacaoController.txtEmailCarregado = rstSqlAtualizarCampos.getString("email");
                // txtSenha.setText("");
            } else {
                TelaDeAtualizacaoController.notificarUsuario("Id não encontrado.");
            }
        } catch (Exception e) {
            TelaDeAtualizacaoController.notificarUsuario("Ops! Problema no servidor. Tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
        return dados;
    }

    public static boolean atualizarModel(String id, String nome, String email, String senha) {
        boolean atualizou = false;
        try {
            Connection conexao = MySQLConnector.conectar();
            String atualizarSenha = "";
            if (senha.length() > 0) {
                atualizarSenha = ", `senha` = '" + senha + "'";
            }
            String strSqlAtualizarId = "update `db_senac`.`tbl_senac` set `nome` = '" + nome + "', `email` = '" + email + "'" + atualizarSenha + " where `id` = " + id + ";";
            Statement stmSqlAtualizarId = conexao.createStatement();
            stmSqlAtualizarId.addBatch(strSqlAtualizarId);
            stmSqlAtualizarId.executeBatch();
            atualizou = true;
            TelaDeAtualizacaoController.notificarUsuario("O id " + id + " foi atualizado com sucesso.");
        } catch (Exception e) {
            TelaDeAtualizacaoController.notificarUsuario("Ops! Problema no servidor, tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
        return atualizou;
    }
}
