
import DAO.FilialDAO;
import DAO.UsuarioDAO;
import DAO.VeiculoDAO;
import DTO.FilialDTO;
import DTO.UsuarioDTO;
import DTO.VeiculoDTO;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javax.swing.table.DefaultTableModel;

public final class Dashboard_Principal extends javax.swing.JFrame {

    VeiculoDTO veiculo = new VeiculoDTO();

    VeiculoDAO veiculodao = new VeiculoDAO();
    UsuarioDAO usuariodao = new UsuarioDAO();

    //Classe utilizada para formatar a data
    SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
    //variaveis globais utilizadas;
    private String nome, modelo, placa, tipo, horaentrada;
    Date entrada = new Date();

    public Dashboard_Principal(UsuarioDTO usuariodto) {
        atualizarDivida();
        initComponents();
        InserirIcone(this);
        setarDadosUsuario(usuariodto);
        HoraAtual();
        AtualizarTabelas();
        setarQuantidadenoPatio();

        PainelCadastrarUsuario.setVisible(false);
        PainelCadastrarFiliais.setVisible(false);
        PainelEntradaVeiculo.setVisible(false);
        PainelSaidadeVeiculo.setVisible(false);

    }

    public VeiculoDTO getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(VeiculoDTO veiculo) {
        this.veiculo = veiculo;
    }

    protected void InserirIcone(Dashboard_Principal dashboard) {

        dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/Icon_FSATOOLKIT.png"));
    }

    //SETAR DADOS DO USUARIO NA TELA     
    protected void setarDadosUsuario(UsuarioDTO usuariodto) {

        for (UsuarioDTO usuarios : usuariodao.getTipo(usuariodto)) {
            lblNomedoUsuario.setText(usuarios.getNome());
            lblCargoUsuario.setText(usuarios.getCargo());
            lblTipoUsuario1.setText(usuarios.getTipo());

        }

    }

    //ATUALIZAR TABELA TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void AtualizarTabelas() {

        for (VeiculoDTO veiculodto : veiculodao.getVeiculo()) {
            nome = veiculodto.getNome();
            tipo = veiculodto.getTipo();
            modelo = veiculodto.getModelo();
            placa = veiculodto.getPlaca();
            horaentrada = veiculodto.getEntrada();
            float saldodevedor = veiculodto.getValor();
            String devedor = Float.toString(saldodevedor);
            String id = veiculodto.getId();
            DefaultTableModel tabelaentrada = (DefaultTableModel) JTabelaEntrada.getModel();
            tabelaentrada.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada, "R$: " + devedor});

            DefaultTableModel tabelaretirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();
            tabelaretirada.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada, "R$: " + devedor});

            DefaultTableModel tabelainicial = (DefaultTableModel) JTabelaInicial.getModel();
            tabelainicial.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada, "R$: " + devedor});
        }
    }

    //ATUALIZAR TABELA TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void HoraAtual() {

        Timer time = new Timer(1000, new hora());
        time.start();
    }

    private void atualizarDivida() {
        Timer time = new Timer(1000 * 5, new CalcularValorTimer());
        time.start();
    }

    //ATUALIZAR O PAINEL QTPATIO TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void setarQuantidadenoPatio() {

        DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();

        int row = JTableEntrada.getRowCount();

        lblQTPatio.setText(Integer.toString(row));

    }

    class hora implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Calendar now = Calendar.getInstance();
            lblHoraAtual.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        }
    }

    //CONTA A QUANTIDADE DE VEICULOS QUE FOI REMOVIDO
    protected void VeiculosRetirados() {

        int retira = Integer.parseInt(lblVeiculoRetira.getText());

        for (int c = -1; c < retira; c++) {
            int i = retira + 1;

            lblVeiculoRetira.setText(Integer.toString(i));
        }

    }
    //Calcular o Valor a ser cobrado
    class CalcularValorTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat hora = new SimpleDateFormat("HHmm");

            for (VeiculoDTO veiculo : veiculodao.getVeiculo()) {
                float valorbase = veiculo.getValor();
                int calcular = 0;
                Date saida = new Date();
                int horaentrada = Integer.parseInt(veiculo.getHoraentrada());
                String horariosaida = hora.format(saida);
                String nome = null;
                int horasaida = Integer.parseInt(horariosaida);

                calcular = horasaida - horaentrada;


                if (calcular < 30) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor();
                    veiculo.setValor(valorbase);
                    veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    
                }

                if (calcular >= 30 && calcular < 60) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + veiculo.getValor();
                    veiculo.setValor(valorbase);
                    if (valorbase < 7) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                        System.out.println(valorbase);

                    }
                }
                if (calcular >= 60 && calcular < 90) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 10) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }

                }
                if (calcular >= 90 && calcular < 120) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 12) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }
                }
                if (calcular >= 120 && calcular < 150) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 15) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }
                }
                if (calcular >= 150 && calcular < 180) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 18) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }
                }
                if (calcular >= 180 && calcular < 210) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 21) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }
                }
                if (calcular >= 210) {
                    int id = Integer.parseInt(veiculo.getId());
                    valorbase = veiculo.getValor() + 3;
                    veiculo.setValor(valorbase);
                    if (valorbase <= 24) {
                        veiculodao.CadastrarValorAtualizadoID(valorbase, id);
                    }
                }

                //Deletar todas as Linhas da Tabela e Renovar
                DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
                DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
                DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

                //Pegar a quantidade de linhas da tabela
                int rows = JTableEntrada.getRowCount();
                int rows2 = JTableInicial.getRowCount();
                int rows3 = JTableRetirada.getRowCount();

                //excluir as linhas da tabela
                for (int c = rows - 1; c >= 0; c--) {
                    veiculodao.resetarID();
                    JTableEntrada.removeRow(c);
                }
                for (int c = rows2 - 1; c >= 0; c--) {
                    veiculodao.resetarID();
                    JTableInicial.removeRow(c);
                }
                for (int c = rows3 - 1; c >= 0; c--) {
                    veiculodao.resetarID();
                    JTableRetirada.removeRow(c);
                }

                AtualizarTabelas();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        lblCargoUsuario = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblNomedoUsuario = new javax.swing.JLabel();
        Btn_Exit = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblTipoUsuario1 = new javax.swing.JLabel();
        PainelMenu = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnCadastrarUsuario = new com.k33ptoo.components.KButton();
        btnTelaInicial = new com.k33ptoo.components.KButton();
        btnEntradadeVeiculo = new com.k33ptoo.components.KButton();
        btnRetiradaVeiculo = new com.k33ptoo.components.KButton();
        btnCadastrarFilial = new com.k33ptoo.components.KButton();
        btnLogOut = new com.k33ptoo.components.KButton();
        PainelTelaInicial = new keeptoo.KGradientPanel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        jLabel33 = new javax.swing.JLabel();
        lblHoraAtual = new javax.swing.JLabel();
        kGradientPanel4 = new com.k33ptoo.components.KGradientPanel();
        lblQTPatio = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        kGradientPanel5 = new com.k33ptoo.components.KGradientPanel();
        lblVeiculoRetira = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        kGradientPanel6 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTabelaInicial = new javax.swing.JTable();
        PainelCadastrarFiliais = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtEndFilial = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtCepFilial = new javax.swing.JFormattedTextField();
        jLabel29 = new javax.swing.JLabel();
        txtCampus = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtTelefoneFilial1 = new javax.swing.JFormattedTextField();
        btnCadastrarFiliais1 = new com.k33ptoo.components.KButton();
        btnAlterarFilial = new com.k33ptoo.components.KButton();
        PainelEntradaVeiculo = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNomeProprietario = new javax.swing.JTextField();
        txtTelefoneProprietario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMarcaModeloCarro = new javax.swing.JTextField();
        txtAnoCarro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCorCarro = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTipoCarro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPlacaCarro = new javax.swing.JTextField();
        btnCadastrarEntrada = new com.k33ptoo.components.KButton();
        kGradientPanel8 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTabelaEntrada = new javax.swing.JTable();
        btnAlterarDadosVeiculo = new com.k33ptoo.components.KButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnBuscarporID2 = new com.k33ptoo.components.KButton();
        PainelSaidadeVeiculo = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnBuscarporID1 = new com.k33ptoo.components.KButton();
        btnBuscarPorNome = new com.k33ptoo.components.KButton();
        kGradientPanel9 = new com.k33ptoo.components.KGradientPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTabelaRetiradaVeiculo = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtBuscarNome = new javax.swing.JTextField();
        btnBuscarporID = new com.k33ptoo.components.KButton();
        jLabel16 = new javax.swing.JLabel();
        txtBuscarID = new javax.swing.JTextField();
        PainelCadastrarUsuario = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtNomeUsuario = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtEndUsuario = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        btnCadastrar_Usuario = new com.k33ptoo.components.KButton();
        jLabel31 = new javax.swing.JLabel();
        txtCargoUsuario = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtSenhaUsuario = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JFormattedTextField();
        txtTelefoneUsuario = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        kGradientPanel1.setkEndColor(new java.awt.Color(22, 0, 126));
        kGradientPanel1.setkStartColor(new java.awt.Color(81, 0, 255));
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCargoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblCargoUsuario.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblCargoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoUsuario.setText("CargodoUsuario");
        kGradientPanel1.add(lblCargoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Cargo:");
        kGradientPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Usuario:");
        kGradientPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        lblNomedoUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblNomedoUsuario.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblNomedoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNomedoUsuario.setText("NomedoUsuario");
        kGradientPanel1.add(lblNomedoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        Btn_Exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Btn_Exit.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Exit.setText("X");
        Btn_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_ExitMouseClicked(evt);
            }
        });
        kGradientPanel1.add(Btn_Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(985, 10, -1, -1));

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Nivel:");
        kGradientPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, -1, -1));

        lblTipoUsuario1.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoUsuario1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        lblTipoUsuario1.setForeground(new java.awt.Color(255, 255, 255));
        lblTipoUsuario1.setText("TipodoUsuario");
        kGradientPanel1.add(lblTipoUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, -1, -1));

        PainelMenu.setkEndColor(new java.awt.Color(7, 21, 116));
        PainelMenu.setkStartColor(new java.awt.Color(13, 74, 243));
        PainelMenu.setOpaque(false);
        PainelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoFSAPARKING_DashBoard.png"))); // NOI18N
        PainelMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 26, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_RetiradaVeiculo.png"))); // NOI18N
        PainelMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 245, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_TelaInicial.png"))); // NOI18N
        PainelMenu.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 170, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_EntradaVeiculo.png"))); // NOI18N
        PainelMenu.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 210, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_CadastrarFilial.png"))); // NOI18N
        PainelMenu.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 280, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_CadastrarUsuario.png"))); // NOI18N
        PainelMenu.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 315, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_logOut.png"))); // NOI18N
        PainelMenu.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 630, -1, -1));

        btnCadastrarUsuario.setText("Cadastrar Usuario");
        btnCadastrarUsuario.setAlignmentY(0.0F);
        btnCadastrarUsuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnCadastrarUsuario.setIconTextGap(2);
        btnCadastrarUsuario.setkAllowGradient(false);
        btnCadastrarUsuario.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnCadastrarUsuario.setkBorderRadius(0);
        btnCadastrarUsuario.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnCadastrarUsuario.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnCadastrarUsuario.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnCadastrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarUsuarioActionPerformed(evt);
            }
        });
        PainelMenu.add(btnCadastrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 309, 240, 37));

        btnTelaInicial.setText("Tela Inicial");
        btnTelaInicial.setAlignmentY(0.0F);
        btnTelaInicial.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnTelaInicial.setIconTextGap(2);
        btnTelaInicial.setkAllowGradient(false);
        btnTelaInicial.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnTelaInicial.setkBorderRadius(0);
        btnTelaInicial.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnTelaInicial.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnTelaInicial.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnTelaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelaInicialActionPerformed(evt);
            }
        });
        PainelMenu.add(btnTelaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 161, 240, 37));

        btnEntradadeVeiculo.setText("Entrada de Veiculo");
        btnEntradadeVeiculo.setAlignmentY(0.0F);
        btnEntradadeVeiculo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnEntradadeVeiculo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        btnEntradadeVeiculo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEntradadeVeiculo.setIconTextGap(2);
        btnEntradadeVeiculo.setkAllowGradient(false);
        btnEntradadeVeiculo.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnEntradadeVeiculo.setkBorderRadius(0);
        btnEntradadeVeiculo.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnEntradadeVeiculo.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnEntradadeVeiculo.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnEntradadeVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradadeVeiculoActionPerformed(evt);
            }
        });
        PainelMenu.add(btnEntradadeVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 198, 240, 37));

        btnRetiradaVeiculo.setText("Retirada de Veiculo");
        btnRetiradaVeiculo.setAlignmentY(0.0F);
        btnRetiradaVeiculo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnRetiradaVeiculo.setIconTextGap(2);
        btnRetiradaVeiculo.setkAllowGradient(false);
        btnRetiradaVeiculo.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnRetiradaVeiculo.setkBorderRadius(0);
        btnRetiradaVeiculo.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnRetiradaVeiculo.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnRetiradaVeiculo.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnRetiradaVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetiradaVeiculoActionPerformed(evt);
            }
        });
        PainelMenu.add(btnRetiradaVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 235, 240, 37));

        btnCadastrarFilial.setText("Cadastrar Filiais");
        btnCadastrarFilial.setAlignmentY(0.0F);
        btnCadastrarFilial.setFocusPainted(false);
        btnCadastrarFilial.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnCadastrarFilial.setIconTextGap(2);
        btnCadastrarFilial.setkAllowGradient(false);
        btnCadastrarFilial.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnCadastrarFilial.setkBorderRadius(0);
        btnCadastrarFilial.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnCadastrarFilial.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnCadastrarFilial.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnCadastrarFilial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarFilialActionPerformed(evt);
            }
        });
        PainelMenu.add(btnCadastrarFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 272, 240, 37));

        btnLogOut.setText("Log Out");
        btnLogOut.setAlignmentY(0.0F);
        btnLogOut.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnLogOut.setIconTextGap(2);
        btnLogOut.setkAllowGradient(false);
        btnLogOut.setkBackGroundColor(new java.awt.Color(13, 74, 243));
        btnLogOut.setkBorderRadius(0);
        btnLogOut.setkHoverColor(new java.awt.Color(0, 51, 153));
        btnLogOut.setkHoverForeGround(new java.awt.Color(255, 255, 255));
        btnLogOut.setkPressedColor(new java.awt.Color(0, 51, 153));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        PainelMenu.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 622, 240, 37));

        kGradientPanel1.add(PainelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 660));

        PainelTelaInicial.setkEndColor(new java.awt.Color(22, 0, 126));
        PainelTelaInicial.setkStartColor(new java.awt.Color(81, 0, 255));
        PainelTelaInicial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel3.setkBorderRadius(40);
        kGradientPanel3.setkEndColor(new java.awt.Color(246, 246, 246));
        kGradientPanel3.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel3.setOpaque(false);

        jLabel33.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel33.setText("Horario Atual:");

        lblHoraAtual.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lblHoraAtual.setText("00:00:00");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel33)
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHoraAtual)
                .addGap(35, 35, 35))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(lblHoraAtual)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        PainelTelaInicial.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 230, 180));

        kGradientPanel4.setkBorderRadius(40);
        kGradientPanel4.setkEndColor(new java.awt.Color(246, 246, 246));
        kGradientPanel4.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel4.setOpaque(false);

        lblQTPatio.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lblQTPatio.setText("0");

        jLabel39.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel39.setText("Veiculos no Patio:");

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel39))
                    .addGroup(kGradientPanel4Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(lblQTPatio)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblQTPatio)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        PainelTelaInicial.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 240, 180));

        kGradientPanel5.setkBorderRadius(40);
        kGradientPanel5.setkEndColor(new java.awt.Color(246, 246, 246));
        kGradientPanel5.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel5.setOpaque(false);

        lblVeiculoRetira.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        lblVeiculoRetira.setText("0");

        jLabel41.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel41.setText("Veiculos Retirados");

        javax.swing.GroupLayout kGradientPanel5Layout = new javax.swing.GroupLayout(kGradientPanel5);
        kGradientPanel5.setLayout(kGradientPanel5Layout);
        kGradientPanel5Layout.setHorizontalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                        .addComponent(lblVeiculoRetira)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addGap(48, 48, 48))))
        );
        kGradientPanel5Layout.setVerticalGroup(
            kGradientPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblVeiculoRetira)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        PainelTelaInicial.add(kGradientPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 240, -1));

        kGradientPanel6.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel6.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel6.setOpaque(false);

        JTabelaInicial.setAutoCreateRowSorter(true);
        JTabelaInicial.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JTabelaInicial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome do Prop.", "Tipo", "Modelo", "Placa", "Horário de Entrada", "Saldo Devedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTabelaInicial.setGridColor(new java.awt.Color(255, 255, 255));
        JTabelaInicial.setSelectionBackground(new java.awt.Color(204, 204, 204));
        JTabelaInicial.setShowGrid(true);
        JTabelaInicial.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(JTabelaInicial);
        if (JTabelaInicial.getColumnModel().getColumnCount() > 0) {
            JTabelaInicial.getColumnModel().getColumn(0).setMaxWidth(50);
            JTabelaInicial.getColumnModel().getColumn(1).setPreferredWidth(150);
            JTabelaInicial.getColumnModel().getColumn(2).setPreferredWidth(55);
        }

        javax.swing.GroupLayout kGradientPanel6Layout = new javax.swing.GroupLayout(kGradientPanel6);
        kGradientPanel6.setLayout(kGradientPanel6Layout);
        kGradientPanel6Layout.setHorizontalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );
        kGradientPanel6Layout.setVerticalGroup(
            kGradientPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                .addContainerGap())
        );

        PainelTelaInicial.add(kGradientPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, 730, 320));

        kGradientPanel1.add(PainelTelaInicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PainelCadastrarFiliais.setBackground(new java.awt.Color(102, 102, 255));
        PainelCadastrarFiliais.setOpaque(false);
        PainelCadastrarFiliais.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Campus:");
        PainelCadastrarFiliais.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));
        PainelCadastrarFiliais.add(txtEndFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 560, -1));

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("CEP:");
        PainelCadastrarFiliais.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        try {
            txtCepFilial.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PainelCadastrarFiliais.add(txtCepFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 180, -1));

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Endereço:");
        PainelCadastrarFiliais.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        PainelCadastrarFiliais.add(txtCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 350, -1));

        jLabel40.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Telefone Contato:");
        PainelCadastrarFiliais.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, -1, -1));

        try {
            txtTelefoneFilial1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PainelCadastrarFiliais.add(txtTelefoneFilial1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 250, -1));

        btnCadastrarFiliais1.setText("Cadastrar");
        btnCadastrarFiliais1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnCadastrarFiliais1.setkAllowGradient(false);
        btnCadastrarFiliais1.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnCadastrarFiliais1.setkBorderRadius(50);
        btnCadastrarFiliais1.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnCadastrarFiliais1.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnCadastrarFiliais1.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnCadastrarFiliais1.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnCadastrarFiliais1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarFiliais1ActionPerformed(evt);
            }
        });
        PainelCadastrarFiliais.add(btnCadastrarFiliais1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 123, -1));

        btnAlterarFilial.setText("Alterar");
        btnAlterarFilial.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnAlterarFilial.setkAllowGradient(false);
        btnAlterarFilial.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnAlterarFilial.setkBorderRadius(50);
        btnAlterarFilial.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnAlterarFilial.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnAlterarFilial.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnAlterarFilial.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnAlterarFilial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarFilialActionPerformed(evt);
            }
        });
        PainelCadastrarFiliais.add(btnAlterarFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 340, 123, -1));

        kGradientPanel1.add(PainelCadastrarFiliais, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 780, 660));

        PainelEntradaVeiculo.setBackground(new java.awt.Color(102, 51, 255));
        PainelEntradaVeiculo.setOpaque(false);
        PainelEntradaVeiculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nome do Proprietário:");
        PainelEntradaVeiculo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 115, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Telefone:");
        PainelEntradaVeiculo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 153, -1, -1));
        PainelEntradaVeiculo.add(txtNomeProprietario, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 481, -1));
        PainelEntradaVeiculo.add(txtTelefoneProprietario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 161, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Marca/Modelo");
        PainelEntradaVeiculo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 153, -1, -1));
        PainelEntradaVeiculo.add(txtMarcaModeloCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 300, -1));
        PainelEntradaVeiculo.add(txtAnoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 161, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ano:");
        PainelEntradaVeiculo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 185, -1, -1));
        PainelEntradaVeiculo.add(txtCorCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 161, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cor:");
        PainelEntradaVeiculo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 185, -1, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tipo:");
        PainelEntradaVeiculo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 185, -1, -1));
        PainelEntradaVeiculo.add(txtTipoCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 190, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Placa:");
        PainelEntradaVeiculo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 225, -1, -1));
        PainelEntradaVeiculo.add(txtPlacaCarro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 161, -1));

        btnCadastrarEntrada.setText("Cadastrar");
        btnCadastrarEntrada.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnCadastrarEntrada.setkAllowGradient(false);
        btnCadastrarEntrada.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnCadastrarEntrada.setkBorderRadius(50);
        btnCadastrarEntrada.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnCadastrarEntrada.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnCadastrarEntrada.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnCadastrarEntrada.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnCadastrarEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarEntradaActionPerformed(evt);
            }
        });
        PainelEntradaVeiculo.add(btnCadastrarEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 170, -1));

        kGradientPanel8.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel8.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel8.setOpaque(false);

        JTabelaEntrada.setAutoCreateRowSorter(true);
        JTabelaEntrada.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JTabelaEntrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome do Prop.", "Tipo", "Modelo", "Placa", "Horário de Entrada", "Saldo Devedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTabelaEntrada.setGridColor(new java.awt.Color(255, 255, 255));
        JTabelaEntrada.setSelectionBackground(new java.awt.Color(204, 204, 204));
        JTabelaEntrada.setShowGrid(true);
        JTabelaEntrada.getTableHeader().setResizingAllowed(false);
        jScrollPane3.setViewportView(JTabelaEntrada);
        if (JTabelaEntrada.getColumnModel().getColumnCount() > 0) {
            JTabelaEntrada.getColumnModel().getColumn(0).setMaxWidth(50);
            JTabelaEntrada.getColumnModel().getColumn(1).setPreferredWidth(150);
            JTabelaEntrada.getColumnModel().getColumn(2).setPreferredWidth(55);
        }

        javax.swing.GroupLayout kGradientPanel8Layout = new javax.swing.GroupLayout(kGradientPanel8);
        kGradientPanel8.setLayout(kGradientPanel8Layout);
        kGradientPanel8Layout.setHorizontalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        kGradientPanel8Layout.setVerticalGroup(
            kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
            .addGroup(kGradientPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        PainelEntradaVeiculo.add(kGradientPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 730, 320));

        btnAlterarDadosVeiculo.setText("Alterar");
        btnAlterarDadosVeiculo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnAlterarDadosVeiculo.setkAllowGradient(false);
        btnAlterarDadosVeiculo.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnAlterarDadosVeiculo.setkBorderRadius(50);
        btnAlterarDadosVeiculo.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnAlterarDadosVeiculo.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnAlterarDadosVeiculo.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnAlterarDadosVeiculo.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnAlterarDadosVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarDadosVeiculoActionPerformed(evt);
            }
        });
        PainelEntradaVeiculo.add(btnAlterarDadosVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 170, -1));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Atualizar Tabelas:");
        PainelEntradaVeiculo.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 266, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_Atualizar.png"))); // NOI18N
        PainelEntradaVeiculo.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 262, -1, -1));

        btnBuscarporID2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnBuscarporID2.setkAllowGradient(false);
        btnBuscarporID2.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID2.setkBorderRadius(50);
        btnBuscarporID2.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnBuscarporID2.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID2.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnBuscarporID2.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarporID2ActionPerformed(evt);
            }
        });
        PainelEntradaVeiculo.add(btnBuscarporID2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 254, 40, 40));

        kGradientPanel1.add(PainelEntradaVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 780, 660));

        PainelSaidadeVeiculo.setOpaque(false);
        PainelSaidadeVeiculo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Atualizar Tabelas:");
        PainelSaidadeVeiculo.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_Atualizar.png"))); // NOI18N
        PainelSaidadeVeiculo.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 248, -1, -1));

        btnBuscarporID1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnBuscarporID1.setkAllowGradient(false);
        btnBuscarporID1.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID1.setkBorderRadius(50);
        btnBuscarporID1.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnBuscarporID1.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID1.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnBuscarporID1.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarporID1ActionPerformed(evt);
            }
        });
        PainelSaidadeVeiculo.add(btnBuscarporID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 40, 40));

        btnBuscarPorNome.setText("Buscar");
        btnBuscarPorNome.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnBuscarPorNome.setkAllowGradient(false);
        btnBuscarPorNome.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnBuscarPorNome.setkBorderRadius(50);
        btnBuscarPorNome.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnBuscarPorNome.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnBuscarPorNome.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnBuscarPorNome.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnBuscarPorNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPorNomeActionPerformed(evt);
            }
        });
        PainelSaidadeVeiculo.add(btnBuscarPorNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 90, 40));

        kGradientPanel9.setkEndColor(new java.awt.Color(245, 245, 245));
        kGradientPanel9.setkStartColor(new java.awt.Color(245, 245, 245));
        kGradientPanel9.setOpaque(false);

        JTabelaRetiradaVeiculo.setAutoCreateRowSorter(true);
        JTabelaRetiradaVeiculo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        JTabelaRetiradaVeiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome do Prop.", "Tipo", "Modelo", "Placa", "Horário de Entrada", "Saldo Devedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTabelaRetiradaVeiculo.setGridColor(new java.awt.Color(255, 255, 255));
        JTabelaRetiradaVeiculo.setSelectionBackground(new java.awt.Color(204, 204, 204));
        JTabelaRetiradaVeiculo.setShowGrid(true);
        JTabelaRetiradaVeiculo.getTableHeader().setResizingAllowed(false);
        jScrollPane4.setViewportView(JTabelaRetiradaVeiculo);
        if (JTabelaRetiradaVeiculo.getColumnModel().getColumnCount() > 0) {
            JTabelaRetiradaVeiculo.getColumnModel().getColumn(0).setMaxWidth(50);
            JTabelaRetiradaVeiculo.getColumnModel().getColumn(1).setPreferredWidth(150);
            JTabelaRetiradaVeiculo.getColumnModel().getColumn(2).setPreferredWidth(55);
        }

        javax.swing.GroupLayout kGradientPanel9Layout = new javax.swing.GroupLayout(kGradientPanel9);
        kGradientPanel9.setLayout(kGradientPanel9Layout);
        kGradientPanel9Layout.setHorizontalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
            .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        kGradientPanel9Layout.setVerticalGroup(
            kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
            .addGroup(kGradientPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        PainelSaidadeVeiculo.add(kGradientPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 730, 320));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Buscar por Nome:");
        PainelSaidadeVeiculo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));
        PainelSaidadeVeiculo.add(txtBuscarNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 510, -1));

        btnBuscarporID.setText("Buscar");
        btnBuscarporID.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnBuscarporID.setkAllowGradient(false);
        btnBuscarporID.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID.setkBorderRadius(50);
        btnBuscarporID.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnBuscarporID.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnBuscarporID.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnBuscarporID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarporIDActionPerformed(evt);
            }
        });
        PainelSaidadeVeiculo.add(btnBuscarporID, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 90, 40));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Buscar por ID:");
        PainelSaidadeVeiculo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));
        PainelSaidadeVeiculo.add(txtBuscarID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 510, -1));

        kGradientPanel1.add(PainelSaidadeVeiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 780, 660));

        PainelCadastrarUsuario.setBackground(new java.awt.Color(102, 102, 255));
        PainelCadastrarUsuario.setOpaque(false);
        PainelCadastrarUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Nome:");
        PainelCadastrarUsuario.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));
        PainelCadastrarUsuario.add(txtNomeUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 350, -1));

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Endereço:");
        PainelCadastrarUsuario.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        PainelCadastrarUsuario.add(txtEndUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 630, -1));

        jLabel30.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Telefone:");
        PainelCadastrarUsuario.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, -1, -1));

        btnCadastrar_Usuario.setText("Cadastrar");
        btnCadastrar_Usuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnCadastrar_Usuario.setkAllowGradient(false);
        btnCadastrar_Usuario.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnCadastrar_Usuario.setkBorderRadius(50);
        btnCadastrar_Usuario.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnCadastrar_Usuario.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnCadastrar_Usuario.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnCadastrar_Usuario.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnCadastrar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar_UsuarioActionPerformed(evt);
            }
        });
        PainelCadastrarUsuario.add(btnCadastrar_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 140, -1));

        jLabel31.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Cargo");
        PainelCadastrarUsuario.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));
        PainelCadastrarUsuario.add(txtCargoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 180, -1));

        jLabel32.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Tipo");
        PainelCadastrarUsuario.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, -1, -1));

        txtTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Padrão", "Administrador", " " }));
        txtTipo.setToolTipText("Padrão, \nAdministrador");
        PainelCadastrarUsuario.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 170, -1));

        jLabel36.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Usuario:");
        PainelCadastrarUsuario.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));
        PainelCadastrarUsuario.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 230, -1));

        jLabel37.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Senha:");
        PainelCadastrarUsuario.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, -1));
        PainelCadastrarUsuario.add(txtSenhaUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 310, 190, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("CPF:");
        PainelCadastrarUsuario.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        try {
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PainelCadastrarUsuario.add(txtCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 270, -1));

        try {
            txtTelefoneUsuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        PainelCadastrarUsuario.add(txtTelefoneUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 250, 250, -1));

        kGradientPanel1.add(PainelCadastrarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 780, 660));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void Btn_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_ExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_Btn_ExitMouseClicked

    private void btnEntradadeVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradadeVeiculoActionPerformed

        PainelEntradaVeiculo.setVisible(true);
        PainelCadastrarUsuario.setVisible(false);
        PainelCadastrarFiliais.setVisible(false);
        PainelTelaInicial.setVisible(false);
        PainelSaidadeVeiculo.setVisible(false);

    }//GEN-LAST:event_btnEntradadeVeiculoActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnTelaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelaInicialActionPerformed

        PainelTelaInicial.setVisible(true);
        PainelSaidadeVeiculo.setVisible(false);
        PainelEntradaVeiculo.setVisible(false);
        PainelCadastrarUsuario.setVisible(false);
        PainelCadastrarFiliais.setVisible(false);
    }//GEN-LAST:event_btnTelaInicialActionPerformed

    private void btnCadastrarEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarEntradaActionPerformed
        //Instancia da Classe VEICULODAO

        VeiculoDAO veiculodao = new VeiculoDAO();

        //formatar o horário de acordo com a data especificada na Classe SimpleDateFormat(XX)
        String horario = formatar.format(entrada);
        //Formatar o horário de acordo com a data especificada na Classe SimpleDateFormat()
        SimpleDateFormat hora = new SimpleDateFormat("HHmm");

        String horaentrada = hora.format(entrada);

        //RECEBE OS CAMPOS DE ENTRADA DO VEICULO/CLIENTE
        veiculo.setNome(txtNomeProprietario.getText());
        veiculo.setTelefone(txtTelefoneProprietario.getText());
        veiculo.setAno(txtAnoCarro.getText());
        veiculo.setModelo(txtMarcaModeloCarro.getText());
        veiculo.setPlaca(txtPlacaCarro.getText());
        veiculo.setCor(txtCorCarro.getText());
        veiculo.setTipo(txtTipoCarro.getText());
        veiculo.setEntrada(horario);
        veiculo.setHoraentrada(horaentrada);
        veiculo.setValor((float) 3.0);
        setVeiculo(veiculo);

        //Cadastrar dados do Veiculo  
        veiculodao.CadastrarVeiculo(veiculo);
        veiculodao.resetarID();

        //INSTANCIAR TELA COMPROVANTE DE ENTRADA
        Tela_ComprovanteEntrada telaconferir = new Tela_ComprovanteEntrada(veiculo.getNome());
        telaconferir.ComprovanteEntrada(veiculo.getNome());
        telaconferir.setVisible(true);

        txtNomeProprietario.setText("");
        txtTelefoneProprietario.setText("");
        txtAnoCarro.setText("");
        txtMarcaModeloCarro.setText("");
        txtPlacaCarro.setText("");
        txtCorCarro.setText("");
        txtTipoCarro.setText("");

        //Deletar todas as Linhas da Tabela e Renovar
        DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
        DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
        DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

        //Pegar a quantidade de linhas da tabela
        int rows = JTableEntrada.getRowCount();
        int rows2 = JTableInicial.getRowCount();
        int rows3 = JTableRetirada.getRowCount();

        //excluir as linhas da tabela
        for (int c = rows - 1; c >= 0; c--) {
            veiculodao.resetarID();
            JTableEntrada.removeRow(c);
        }
        for (int c = rows2 - 1; c >= 0; c--) {
            veiculodao.resetarID();
            JTableInicial.removeRow(c);
        }
        for (int c = rows3 - 1; c >= 0; c--) {
            veiculodao.resetarID();
            JTableRetirada.removeRow(c);
        }
        //Repaginar a tabela com as novas linhas

        AtualizarTabelas();
        setarQuantidadenoPatio();


    }//GEN-LAST:event_btnCadastrarEntradaActionPerformed

    private void btnBuscarPorNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorNomeActionPerformed

        if (txtBuscarNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algum nome para Pesquisar\n Consulte a tabela abaixo.");
            txtBuscarNome.setText("");
        } else {
            veiculo.setNome(txtBuscarNome.getText());

            CalcularValor(veiculo);

            DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
            DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
            DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

            int rows = JTableEntrada.getRowCount();
            int rows2 = JTableInicial.getRowCount();
            int rows3 = JTableRetirada.getRowCount();

            for (int c = rows - 1; c >= 0; c--) {
                JTableEntrada.removeRow(c);
            }
            for (int c = rows2 - 1; c >= 0; c--) {
                JTableInicial.removeRow(c);
            }
            for (int c = rows3 - 1; c >= 0; c--) {
                JTableRetirada.removeRow(c);
            }

            veiculodao.resetarID();
            AtualizarTabelas();
            setarQuantidadenoPatio();
            VeiculosRetirados();
            txtBuscarNome.setText("");
            
        }
    }//GEN-LAST:event_btnBuscarPorNomeActionPerformed

    private void btnBuscarporIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarporIDActionPerformed
        if (txtBuscarID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algum número para Pesquisar\n Consulte a tabela abaixo.");
            txtBuscarID.setText("");
        } else {
            veiculo.setUltimoid(Integer.parseInt(txtBuscarID.getText()));
            CalcularValor(veiculo);
            //CalcularValor(veiculo);
            DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
            DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
            DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

            int rows = JTableEntrada.getRowCount();
            int rows2 = JTableInicial.getRowCount();
            int rows3 = JTableRetirada.getRowCount();

            for (int c = rows - 1; c >= 0; c--) {
                JTableEntrada.removeRow(c);
            }
            for (int c = rows2 - 1; c >= 0; c--) {
                JTableInicial.removeRow(c);
            }
            for (int c = rows3 - 1; c >= 0; c--) {
                JTableRetirada.removeRow(c);
            }

            veiculodao.resetarID();
            AtualizarTabelas();
            setarQuantidadenoPatio();
            VeiculosRetirados();
            
            txtBuscarID.setText("");
        }

    }//GEN-LAST:event_btnBuscarporIDActionPerformed

    private void btnRetiradaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiradaVeiculoActionPerformed

        PainelSaidadeVeiculo.setVisible(true);
        PainelEntradaVeiculo.setVisible(false);
        PainelTelaInicial.setVisible(false);
        PainelCadastrarUsuario.setVisible(false);
        PainelCadastrarFiliais.setVisible(false);

    }//GEN-LAST:event_btnRetiradaVeiculoActionPerformed

    private void btnCadastrar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar_UsuarioActionPerformed
        UsuarioDTO usuariodto = new UsuarioDTO();

        String nome, cpf, cargo, usuario, senha, tipo, endereco, telefone;

        nome = txtNomeUsuario.getText();
        cpf = txtCPF.getText();
        cargo = txtCargoUsuario.getText();
        usuario = txtUsuario.getText();
        senha = txtSenhaUsuario.getText();
        tipo = String.valueOf(txtTipo.getSelectedItem());
        System.out.println(tipo);
        endereco = txtEndUsuario.getText();
        telefone = txtTelefoneUsuario.getText();

        usuariodto.setNome(nome);
        usuariodto.setCpf(cpf);
        usuariodto.setCargo(cargo);
        usuariodto.setUsuario(usuario);
        usuariodto.setSenha(senha);
        usuariodto.setTipo(tipo);
        usuariodto.setEndereco(endereco);
        usuariodto.setTelefone(telefone);

        usuariodao.CadastrarUsuario(usuariodto);


    }//GEN-LAST:event_btnCadastrar_UsuarioActionPerformed

    private void btnCadastrarFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarFilialActionPerformed

        PainelSaidadeVeiculo.setVisible(false);
        PainelEntradaVeiculo.setVisible(false);
        PainelTelaInicial.setVisible(false);
        PainelCadastrarUsuario.setVisible(false);
        PainelCadastrarFiliais.setVisible(true);

    }//GEN-LAST:event_btnCadastrarFilialActionPerformed

    private void btnCadastrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarUsuarioActionPerformed

        PainelSaidadeVeiculo.setVisible(false);
        PainelEntradaVeiculo.setVisible(false);
        PainelTelaInicial.setVisible(false);
        PainelCadastrarUsuario.setVisible(true);
        PainelCadastrarFiliais.setVisible(false);

    }//GEN-LAST:event_btnCadastrarUsuarioActionPerformed

    private void btnCadastrarFiliais1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarFiliais1ActionPerformed
        String campus, endereco, cep, telefone;
        campus = txtCampus.getText();
        endereco = txtEndFilial.getText();
        cep = txtCepFilial.getText();
        telefone = txtTelefoneFilial1.getText();

        FilialDTO filialdto = new FilialDTO();
        filialdto.setCampus(campus);
        filialdto.setEndereco(endereco);
        filialdto.setTelefone(telefone);
        filialdto.setCep(cep);

        FilialDAO filialdao = new FilialDAO();
        filialdao.CadastrarFilial(filialdto);
        
        txtCampus.setText("");
        txtEndFilial.setText("");
        txtCepFilial.setText("");
        txtTelefoneFilial1.setText("");
    }//GEN-LAST:event_btnCadastrarFiliais1ActionPerformed

    private void btnAlterarFilialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarFilialActionPerformed

        Tela_AlterarDadosFilial telaalterar = new Tela_AlterarDadosFilial();
        telaalterar.setVisible(true);

    }//GEN-LAST:event_btnAlterarFilialActionPerformed

    private void btnAlterarDadosVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarDadosVeiculoActionPerformed
        Tela_AlterarDadosVeiculo alterarveiculo = new Tela_AlterarDadosVeiculo();

        alterarveiculo.setVisible(true);

    }//GEN-LAST:event_btnAlterarDadosVeiculoActionPerformed

    private void btnBuscarporID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarporID1ActionPerformed
        DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
        DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
        DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

        int rows = JTableEntrada.getRowCount();
        int rows2 = JTableInicial.getRowCount();
        int rows3 = JTableRetirada.getRowCount();

        for (int c = rows - 1; c >= 0; c--) {
            JTableEntrada.removeRow(c);
        }
        for (int c = rows2 - 1; c >= 0; c--) {
            JTableInicial.removeRow(c);
        }
        for (int c = rows3 - 1; c >= 0; c--) {
            JTableRetirada.removeRow(c);
        }

        veiculodao.resetarID();
        AtualizarTabelas();
        setarQuantidadenoPatio();
        VeiculosRetirados();
    }//GEN-LAST:event_btnBuscarporID1ActionPerformed

    private void btnBuscarporID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarporID2ActionPerformed
        DefaultTableModel JTableEntrada = (DefaultTableModel) JTabelaEntrada.getModel();
        DefaultTableModel JTableInicial = (DefaultTableModel) JTabelaInicial.getModel();
        DefaultTableModel JTableRetirada = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();

        int rows = JTableEntrada.getRowCount();
        int rows2 = JTableInicial.getRowCount();
        int rows3 = JTableRetirada.getRowCount();

        for (int c = rows - 1; c >= 0; c--) {
            JTableEntrada.removeRow(c);
        }
        for (int c = rows2 - 1; c >= 0; c--) {
            JTableInicial.removeRow(c);
        }
        for (int c = rows3 - 1; c >= 0; c--) {
            JTableRetirada.removeRow(c);
        }

        veiculodao.resetarID();
        AtualizarTabelas();
        setarQuantidadenoPatio();
        VeiculosRetirados();
    }//GEN-LAST:event_btnBuscarporID2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            UsuarioDTO usuariodto = null;
            new Dashboard_Principal(usuariodto).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Btn_Exit;
    private javax.swing.JTable JTabelaEntrada;
    private javax.swing.JTable JTabelaInicial;
    private javax.swing.JTable JTabelaRetiradaVeiculo;
    private javax.swing.JPanel PainelCadastrarFiliais;
    private javax.swing.JPanel PainelCadastrarUsuario;
    private javax.swing.JPanel PainelEntradaVeiculo;
    private keeptoo.KGradientPanel PainelMenu;
    private javax.swing.JPanel PainelSaidadeVeiculo;
    private keeptoo.KGradientPanel PainelTelaInicial;
    private com.k33ptoo.components.KButton btnAlterarDadosVeiculo;
    private com.k33ptoo.components.KButton btnAlterarFilial;
    private com.k33ptoo.components.KButton btnBuscarPorNome;
    private com.k33ptoo.components.KButton btnBuscarporID;
    private com.k33ptoo.components.KButton btnBuscarporID1;
    private com.k33ptoo.components.KButton btnBuscarporID2;
    private com.k33ptoo.components.KButton btnCadastrarEntrada;
    private com.k33ptoo.components.KButton btnCadastrarFiliais1;
    private com.k33ptoo.components.KButton btnCadastrarFilial;
    private com.k33ptoo.components.KButton btnCadastrarUsuario;
    private com.k33ptoo.components.KButton btnCadastrar_Usuario;
    private com.k33ptoo.components.KButton btnEntradadeVeiculo;
    private com.k33ptoo.components.KButton btnLogOut;
    private com.k33ptoo.components.KButton btnRetiradaVeiculo;
    private com.k33ptoo.components.KButton btnTelaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private keeptoo.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private com.k33ptoo.components.KGradientPanel kGradientPanel4;
    private com.k33ptoo.components.KGradientPanel kGradientPanel5;
    private com.k33ptoo.components.KGradientPanel kGradientPanel6;
    private com.k33ptoo.components.KGradientPanel kGradientPanel8;
    private com.k33ptoo.components.KGradientPanel kGradientPanel9;
    private javax.swing.JLabel lblCargoUsuario;
    private javax.swing.JLabel lblHoraAtual;
    private javax.swing.JLabel lblNomedoUsuario;
    private javax.swing.JLabel lblQTPatio;
    private javax.swing.JLabel lblTipoUsuario1;
    private javax.swing.JLabel lblVeiculoRetira;
    private javax.swing.JTextField txtAnoCarro;
    private javax.swing.JTextField txtBuscarID;
    private javax.swing.JTextField txtBuscarNome;
    private javax.swing.JFormattedTextField txtCPF;
    private javax.swing.JTextField txtCampus;
    private javax.swing.JTextField txtCargoUsuario;
    private javax.swing.JFormattedTextField txtCepFilial;
    private javax.swing.JTextField txtCorCarro;
    private javax.swing.JTextField txtEndFilial;
    private javax.swing.JTextField txtEndUsuario;
    private javax.swing.JTextField txtMarcaModeloCarro;
    private javax.swing.JTextField txtNomeProprietario;
    private javax.swing.JTextField txtNomeUsuario;
    private javax.swing.JTextField txtPlacaCarro;
    private javax.swing.JTextField txtSenhaUsuario;
    private javax.swing.JFormattedTextField txtTelefoneFilial1;
    private javax.swing.JTextField txtTelefoneProprietario;
    private javax.swing.JFormattedTextField txtTelefoneUsuario;
    private javax.swing.JComboBox<String> txtTipo;
    private javax.swing.JTextField txtTipoCarro;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    protected void CalcularValor(VeiculoDTO veiculodto) {

        SimpleDateFormat hora = new SimpleDateFormat("HHmm");

        for (VeiculoDTO veiculo : veiculodao.getDadosProprietarioPorID(veiculodto)) {
            float valorbase = veiculo.getValor();
            System.out.println(valorbase);

            Date saida = new Date();
            String horariosaida = hora.format(saida);
            String nome = null;
            int horasaida = Integer.parseInt(horariosaida);

            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
            String Saida = formatar.format(saida);
            String valor = Float.toString(valorbase);
            Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
            telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
            telaretira.setVisible(true);
           

        }
    }
} // FIM DA CLASSE PRINCIPAL
