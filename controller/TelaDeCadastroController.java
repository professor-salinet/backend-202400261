package controller;
import view.*;
import model.*;

public class TelaDeCadastroController extends TelaDeCadastroView {
    public static void cadastrarController() {
        TelaDeCadastroModel.cadastrarModel(txtNome.getText(), txtEmail.getText(), String.valueOf(txtSenha.getPassword()));
    }
}
