package controller;
import view.*;
import model.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeAtualizacaoController extends TelaDeAtualizacaoView {
    public static void popularCbxIdController() {
        ArrayList<String> ids = TelaDeAtualizacaoModel.popularCbxIdModel();
        for (int i = 0; i < ids.size(); i++) {
            cbxId.addItem(ids.get(i));
        }
    }

    public static void atualizarCamposController() {
        ArrayList<String> dados = TelaDeAtualizacaoModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));
        txtNome.setText(dados.get(0));
        txtEmail.setText(dados.get(1));
    }

    public static void atualizarController() {
        if (TelaDeAtualizacaoModel.atualizarModel(String.valueOf(cbxId.getSelectedItem()), txtNome.getText().trim(), txtEmail.getText().trim(), String.valueOf(txtSenha.getPassword()).trim())) {
            txtNomeCarregado = txtNome.getText().trim();
            txtEmailCarregado = txtEmail.getText().trim();
            btnAtualizar.setEnabled(false);
        }
    }
}
