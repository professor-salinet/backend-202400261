package view;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaDePesquisaView extends JFrame {
    public static JLabel lblFoto;
    public static final JTextField txtPesquisa = new JTextField(20);
    public final JButton btnPesquisar;
    public final JButton btnReiniciarPesquisa;

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

    public final JLabel lblId;
    public static final JTextField txtId = new JTextField(10);

    public final JLabel lblNome;
    public static final JTextField txtNome = new JTextField(10);

    public final JLabel lblEmail;
    public static final JTextField txtEmail = new JTextField(10);

    public static final JButton btnPrimeiro = new JButton("<<");
    public static final JButton btnAnterior = new JButton("<");
    public static final JButton btnProximo = new JButton(">");
    public static final JButton btnUltimo = new JButton(">>");

    public static final JLabel lblNotificacoes = new JLabel("Notificações", SwingConstants.CENTER);

    public static GridBagLayout gbLayout;
    public static GridBagConstraints gbConstraints;

    public TelaDePesquisaView() {
        super("Tela de Pesquisa");
        gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        gbConstraints = new GridBagConstraints();

        lblFoto = new JLabel("", SwingConstants.CENTER);
        lblFoto.setIcon(new ImageIcon(new ImageIcon(localViewFolder + "\\imagem-padrao.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
        addComponent(lblFoto, 0, 0, 4, 2);

        addComponent(txtPesquisa, 2, 0, 4, 1);

        btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.setEnabled(false);
        addComponent(btnPesquisar, 3, 0, 2, 1);

        btnReiniciarPesquisa = new JButton("Reiniciar Pesquisa");
        addComponent(btnReiniciarPesquisa, 3, 2, 2, 1);

        lblId = new JLabel("Id:", SwingConstants.RIGHT);
        addComponent(lblId, 4, 0, 2, 1);

        txtId.setEditable(false);
        addComponent(txtId, 4, 2, 2, 1);

        lblNome = new JLabel("Nome:", SwingConstants.RIGHT);
        addComponent(lblNome, 5, 0, 2, 1);

        txtNome.setEditable(false);
        addComponent(txtNome, 5, 2, 2, 1);

        lblEmail = new JLabel("Email:", SwingConstants.RIGHT);
        addComponent(lblEmail, 6, 0, 2, 1);

        txtEmail.setEditable(false);
        addComponent(txtEmail, 6, 2, 2, 1);

        addComponent(btnPrimeiro, 7, 0, 1, 1);
        addComponent(btnAnterior, 7, 1, 1, 1);
        addComponent(btnProximo, 7, 2, 1, 1);
        addComponent(btnUltimo, 7, 3, 1, 1);

        addComponent(lblNotificacoes, 8, 0, 4, 1);

        btnPrimeiro.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaPrimeiroRegistro();
                }
            }
        );

        btnAnterior.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaRegistroAnterior();
                }
            }
        );

        btnProximo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaProximoRegistro();
                }
            }
        );

        btnUltimo.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.vaParaUltimoRegistro();
                }
            }
        );

        btnPesquisar.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    TelaDePesquisaController.registrarPesquisa();
                    txtPesquisa.setText(txtPesquisa.getText().trim());
                }
            }
        );

        txtPesquisa.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    btnPesquisar.setEnabled(detectarPesquisa());
                    if (e.getKeyCode() == 10 && txtPesquisa.getText().trim().length() > 0) {
                        TelaDePesquisaController.registrarPesquisa();
                        txtPesquisa.setText(txtPesquisa.getText().trim());
                    }
                    // if (e.getKeyCode() == 39) { // seta para direita
                    //     TelaDePesquisaController.vaParaProximoRegistro();
                    // }
                    // if (e.getKeyCode() == 40) { // seta para baixo
                    //     TelaDePesquisaController.vaParaPrimeiroRegistro();
                    // }
                    // if (e.getKeyCode() == 37) { // seta para esquerda
                    //     TelaDePesquisaController.vaParaRegistroAnterior();
                    // }
                    // if (e.getKeyCode() == 38) { // seta para cima
                    //     TelaDePesquisaController.vaParaUltimoRegistro();
                    // }
                }
            }
        );

        btnReiniciarPesquisa.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtPesquisa.setText("");
                    TelaDePesquisaController.registroDePesquisa = "";
                    TelaDePesquisaController.clausulasDePesquisaComWhere = "";
                    TelaDePesquisaController.clausulasDePesquisaSemWhere = "";
                    TelaDePesquisaController.vaParaPrimeiroRegistro();
                    btnPesquisar.setEnabled(false);
                    txtPesquisa.requestFocus();
                }
            }
        );

        setSize(300,340);
        setVisible(true);

        TelaDePesquisaController.inicializacaoDeRegistros();
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

    public static void notificarUsuario(String strTexto) {
        lblNotificacoes.setText(setHtmlFormat(strTexto));
    }

    public static String setHtmlFormat(String strTexto) {
        return "<html><body>" + strTexto + "</body></html>";
    }

    public static void habilitarVoltar() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    public static void habilitarAvancar() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void habilitarTodos() {
        btnPrimeiro.setEnabled(true);
        btnAnterior.setEnabled(true);
        btnProximo.setEnabled(true);
        btnUltimo.setEnabled(true);
    }

    public static void desabilitarTodos() {
        btnPrimeiro.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnProximo.setEnabled(false);
        btnUltimo.setEnabled(false);
    }

    public static boolean detectarPesquisa() {
        if (txtPesquisa.getText().trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtEmail.setText("");
    }

    public static void verificarLarguraEAltura() { // checkFrameWidthHeight()
        appTelaDePesquisaView.getRootPane().addComponentListener(
            new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    int larguraTela = appTelaDePesquisaView.getWidth();
                    int alturaTela = appTelaDePesquisaView.getHeight();
                    notificarUsuario(String.format("Largura: %s, Altura: %s", larguraTela, alturaTela));
                }
            }
        );
    }

    public static TelaDePesquisaView appTelaDePesquisaView;
    public static void main(String[] args) {
        appTelaDePesquisaView = new TelaDePesquisaView();
        appTelaDePesquisaView.setDefaultCloseOperation(EXIT_ON_CLOSE);
        verificarLarguraEAltura();
    }
}