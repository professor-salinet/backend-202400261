package controller;
import view.*;
import model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeRemoverController extends TelaDeRemoverView {
    public static void removerController() {
        TelaDeRemoverModel.removerModel(String.valueOf(cbxId.getSelectedItem()));
    }

    public static void popularCbxIdController() {
        TelaDeRemoverModel.popularCbxIdModel();
    }

    public static void adicionarItemCbxId(String str) {
        cbxId.addItem(str);
    }

    public static void preencherCampos(String nome, String email) {
        txtNome.setText(nome);
        txtEmail.setText(email);
    }

    public static void atualizarCamposController() {
        TelaDeRemoverModel.atualizarCamposModel(String.valueOf(cbxId.getSelectedItem()));
    }
}
