package controller;

import view.*;
import model.*;

import java.util.*;

public class TelaDeLoginController extends TelaDeLoginView {
    public static void logarController() {
        ArrayList<String> resultados = new ArrayList<String>(TelaDeLoginModel.logarModel(txtLogin.getText(), String.valueOf(txtSenha.getPassword())));
        resultados.size();
    }
}
