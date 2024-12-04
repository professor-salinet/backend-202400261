package view;

import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class TelaDeHistoricoView extends JFrame {
    public static JList<String> lstHistorico;
    public static JButton btnEnviarHistorico;
    public static ArrayList<String> strHistoricos;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeHistoricoView() {
        super("Tela de Histórico");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);

        gbConstraints = new GridBagConstraints();

        String[] strHistoricos = TelaDeHistoricoController.preencherHistorico();
        lstHistorico = new JList<String>(strHistoricos);
        InterfaceView.addComponent(lstHistorico, 0, 0, 1, 1, gbLayout, gbConstraints, this);

        btnEnviarHistorico = new JButton("Enviar Histórico");
        InterfaceView.addComponent(btnEnviarHistorico, 1, 0, 1, 1, gbLayout, gbConstraints, this);

        btnEnviarHistorico.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // o que vai acontecer quando clicar no btnEnviarHistorico
                }
            }
        );

        setSize(200, 200);
        setVisible(true);
    }

    public static TelaDeHistoricoView appTelaDeHistoricoView;
    public static void main(String[] args) {
        appTelaDeHistoricoView = new TelaDeHistoricoView();
        appTelaDeHistoricoView.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
