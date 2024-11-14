package model;
import controller.*;

import java.sql.*;

public class TelaDeRemoverModel {
    public static void removerModel(String id) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlRemoverId = "delete from `db_senac`.`tbl_senac` where `id` = " + id + ";";
            Statement stmSqlRemoverId = conexao.createStatement();
            stmSqlRemoverId.addBatch(strSqlRemoverId);
            stmSqlRemoverId.executeBatch();
            TelaDeRemoverController.notificarUsuario("O id " + id + " foi removido com sucesso.");
        } catch (Exception e) {
            TelaDeRemoverController.notificarUsuario("Ops! Problema no servidor, tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void popularCbxIdModel() {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlPopularCbxId = "select * from `db_senac`.`tbl_senac` order by `id` asc;";
            Statement stmSqlPopularCbxId = conexao.createStatement();
            ResultSet rstSqlPopularCbxId = stmSqlPopularCbxId.executeQuery(strSqlPopularCbxId);
            while (rstSqlPopularCbxId.next()) {
                TelaDeRemoverController.adicionarItemCbxId(rstSqlPopularCbxId.getString("id"));
            }
            stmSqlPopularCbxId.close();
        } catch (Exception e) {
            TelaDeRemoverController.notificarUsuario("Ops! Ocorreu um problema no servidor e não será possível carregar os ids neste momento. Por favor, retorne novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }

    public static void atualizarCamposModel(String strId) {
        try {
            Connection conexao = MySQLConnector.conectar();
            String strSqlAtualizarCampos = "select * from `db_senac`.`tbl_senac` where id = " + strId + ";";
            Statement stmSqlAtualizarCampos = conexao.createStatement();
            ResultSet rstSqlAtualizarCampos = stmSqlAtualizarCampos.executeQuery(strSqlAtualizarCampos);
            if (rstSqlAtualizarCampos.next()) {
                TelaDeRemoverController.preencherCampos(rstSqlAtualizarCampos.getString("nome"), rstSqlAtualizarCampos.getString("email"));
            } else {
                TelaDeRemoverController.notificarUsuario("Id não encontrado.");
            }
        } catch (Exception e) {
            TelaDeRemoverController.notificarUsuario("Ops! Problema no servidor. Tente novamente mais tarde.");
            System.err.println("Erro: " + e);
        }
    }
}
