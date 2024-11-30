package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeAtualizacaoView extends JFrame {
    public static JLabel lblId;
    public static JComboBox<String> cbxId;

    public static JLabel lblNome;
    public static JTextField txtNome;
    public static String txtNomeCarregado;

    public static JLabel lblEmail;
    public static JTextField txtEmail;
    public static String txtEmailCarregado;

    public static JLabel lblSenha;
    public static JPasswordField txtSenha;

    public static JButton btnAtualizar;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeAtualizacaoView() {
        super("Tela de Atualização");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        addComponent(lblId, 0, 0, 1, 1);

        cbxId = new JComboBox<String>();
        TelaDeAtualizacaoController.popularCbxIdController();
        addComponent(cbxId, 0, 1, 1, 1);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        addComponent(lblNome, 1, 0, 1, 1);

        txtNome = new JTextField(10);
        addComponent(txtNome, 1, 1, 1, 1);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        addComponent(lblEmail, 2, 0, 1, 1);

        txtEmail = new JTextField(10);
        addComponent(txtEmail, 2, 1, 1, 1);

        TelaDeAtualizacaoController.atualizarCamposController();

        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        addComponent(lblSenha, 3, 0, 1, 1);

        txtSenha = new JPasswordField(10);
        addComponent(txtSenha, 3, 1, 1, 1);

        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setEnabled(false);
        addComponent(btnAtualizar, 4, 0, 1, 1);

        btnCancelar = new JButton("Cancelar");
        addComponent(btnCancelar, 4, 1, 1, 1);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        addComponent(lblNotificacoes, 5, 0, 2, 1);

        cbxId.addItemListener(
            new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        TelaDeAtualizacaoController.atualizarCamposController();
                    }
                }
            }
        );

        // cbxId.addActionListener(
        //     new ActionListener() {
        //         @Override
        //         public void actionPerformed(ActionEvent event) {
        //             if (!txtNome.getText().trim().equals(txtNomeCarregado.trim()) && JOptionPane.showConfirmDialog(null, "Nome modificado! Deseja alternar para outro id?") == JOptionPane.CANCEL_OPTION) {
        //                 return;
        //             }
        //             System.out.println("aqui ok");
        //         }
        //     }
        // );

        btnAtualizar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeAtualizacaoController.atualizarController();
                }
            }
        );

        txtNome.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (txtNomeCarregado.trim().equals(txtNome.getText().trim())) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        txtEmail.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (txtEmailCarregado.trim().equals(txtEmail.getText().trim())) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        txtSenha.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent event) {
                    if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                        btnAtualizar.setEnabled(false);
                    } else {
                        btnAtualizar.setEnabled(true);
                    }
                }
            }
        );

        btnCancelar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeMenuView.appTelaDeMenuView.setVisible(true);
                    dispose();
                }
            }
        );

        setSize(206,200);
        setVisible(true);
    }

    public void addComponent(Component component, int row, int column, int width, int height) {
        if (height > 1 && width > 1) {
            gbConstraints.fill = GridBagConstraints.BOTH;
        } else if (height > 1) {
            gbConstraints.fill = GridBagConstraints.VERTICAL;
        } else {
            gbConstraints.fill = GridBagConstraints.HORIZONTAL;
        }
        gbConstraints.gridy = row;
        gbConstraints.gridx = column;
        gbConstraints.gridwidth = width;
        gbConstraints.gridheight = height;
        gbLayout.setConstraints(component, gbConstraints);
        add(component);
    }

    public static void notificarUsuario(String str) {
        lblNotificacoes.setText(setHtmlFormat(str));
    }

    public static String setHtmlFormat(String str) {
        return "<html><body>" + str + "</body></html>";
    }

    public static TelaDeAtualizacaoView appTelaDeAtualizacaoView;
    public static void main(String[] args) {
        appTelaDeAtualizacaoView = new TelaDeAtualizacaoView();
        appTelaDeAtualizacaoView.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}