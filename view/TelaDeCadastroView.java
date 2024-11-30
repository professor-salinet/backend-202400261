package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDeCadastroView extends JFrame {
    public static JLabel lblFoto;
    public static JButton btnCarregarFoto;
    public static JButton btnRemoverFoto;
    public static String nomeArquivoFoto = "";

    public static final String localViewImgFolder = System.getProperty("user.dir") 
        + "\\" 
        + "src" 
        + "\\" 
        + "view" 
        + "\\" 
        + "img";

    public static final String localViewFolder = System.getProperty("user.dir") 
        + "\\" 
        + "src" 
        + "\\" 
        + "view";

    public static JLabel lblNome;
    public static JTextField txtNome;

    public static JLabel lblEmail;
    public static JTextField txtEmail;

    public static JLabel lblSenha;
    public static JPasswordField txtSenha;

    public static JButton btnCadastrar;
    public static JButton btnCancelar;

    public static JLabel lblNotificacoes;

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDeCadastroView() {
        super("Tela de Cadastro");

        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        addComponent(lblFoto, 0, 0, 2, 2);

        btnCarregarFoto = new JButton("Carregar foto");
        addComponent(btnCarregarFoto, 2, 0, 1, 1);

        btnRemoverFoto = new JButton("Remover foto");
        addComponent(btnRemoverFoto, 2, 1, 1, 1);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        addComponent(lblNome, 3, 0, 1, 1);

        txtNome = new JTextField(10);
        addComponent(txtNome, 3, 1, 1, 1);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        addComponent(lblEmail, 4, 0, 1, 1);

        txtEmail = new JTextField(10);
        addComponent(txtEmail, 4, 1, 1, 1);

        lblSenha = new JLabel("Senha:", SwingConstants.RIGHT);
        addComponent(lblSenha, 5, 0, 1, 1);

        txtSenha = new JPasswordField(10);
        addComponent(txtSenha, 5, 1, 1, 1);

        btnCadastrar = new JButton("Cadastrar");
        addComponent(btnCadastrar, 6, 0, 1, 1);

        btnCancelar = new JButton("Cancelar");
        addComponent(btnCancelar, 6, 1, 1, 1);

        lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);
        addComponent(lblNotificacoes, 7, 0, 2, 1);

        btnCadastrar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    if (txtNome.getText().trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Nome. Por favor, digite um caracter válido no campo Nome para prosseguir.");
                        txtNome.requestFocus();
                        return;
                    }

                    if (txtEmail.getText().trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Email. Por favor, digite um caracter válido no campo Email para prosseguir.");
                        txtEmail.requestFocus();
                        return;
                    }

                    if (String.valueOf(txtSenha.getPassword()).trim().length() == 0) {
                        lblNotificacoes.setText("É necessário digitar alguma coisa no campo Senha. Por favor, digite um caracter válido no campo Senha para prosseguir.");
                        txtSenha.requestFocus();
                        return;
                    }

                    TelaDeCadastroController.cadastrarController();
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

        btnCarregarFoto.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeCadastroController.carregarFoto();
                }
            }
        );

        btnRemoverFoto.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDeCadastroController.removerFoto();
                }
            }
        );

        setSize(280,280);
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
        // gbConstraints.insets = new Insets(1, 1, 1, 1); // sugestão de margem de elemento, crédios pro aluno Fernando
        gbLayout.setConstraints(component, gbConstraints);
        add(component);
    }

    public static void notificarUsuario(String txt) {
        lblNotificacoes.setText(setHtmlFormat(txt));
    }

    public static String setHtmlFormat(String str) {
        return "<html><body>" + str + "</body></html>";
    }

    public static void verificarLarguraEAltura() { // checkFrameWidthHeight()
        appTelaDeCadastroView.getRootPane().addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int larguraTela = appTelaDeCadastroView.getWidth();
                    int alturaTela = appTelaDeCadastroView.getHeight();
                    notificarUsuario(String.format("Largura: %s, Altura: %s", larguraTela, alturaTela));
                }
            }
        );
    }

    public static TelaDeCadastroView appTelaDeCadastroView;
    public static void main(String[] args) {
        appTelaDeCadastroView = new TelaDeCadastroView();
        appTelaDeCadastroView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // verificarLarguraEAltura();
    }
}