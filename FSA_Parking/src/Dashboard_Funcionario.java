
import DAO.UsuarioDAO;
import DAO.VeiculoDAO;
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

public final class Dashboard_Funcionario extends javax.swing.JFrame {

    VeiculoDTO veiculo = new VeiculoDTO();
    VeiculoDAO veiculodao = new VeiculoDAO();
    UsuarioDAO usuariodao = new UsuarioDAO();

    //Classe utilizada para formatar a data
    SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
    //variaveis globais utilizadas;
    private String nome, modelo, placa, tipo, horaentrada;
    Date entrada = new Date();

    public Dashboard_Funcionario(UsuarioDTO usuariodto) {
        initComponents();
        InserirIcone(this);
        veiculodao.resetarID();
        setarDadosUsuario(usuariodto);
        HoraAtual();
        AtualizarTabelaEntrada();
        AtualizarTabelaInicial();
        AtualizarTabelaRetirada();
        setarQuantidadenoPatio();

        
        PainelEntradaVeiculo.setVisible(false);
        PainelSaidadeVeiculo.setVisible(false);

    }
    
     protected void InserirIcone(Dashboard_Funcionario dashboard){ 
        
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
    protected void AtualizarTabelaEntrada() {

        for (VeiculoDTO veiculodto : veiculodao.getVeiculo()) {
            nome = veiculodto.getNome();
            tipo = veiculodto.getTipo();
            modelo = veiculodto.getModelo();
            placa = veiculodto.getPlaca();
            horaentrada = veiculodto.getEntrada();

            String id = veiculodto.getId();
            DefaultTableModel val = (DefaultTableModel) JTabelaEntrada.getModel();
            val.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada});

        }
    }

    //ATUALIZAR TABELA TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void AtualizarTabelaRetirada() {

        for (VeiculoDTO veiculodto : veiculodao.getVeiculo()) {
            nome = veiculodto.getNome();
            tipo = veiculodto.getTipo();
            modelo = veiculodto.getModelo();
            placa = veiculodto.getPlaca();
            horaentrada = veiculodto.getEntrada();
            float saldodevedor = veiculodto.getValor();
            String devedor = Float.toString(saldodevedor);
            String id = veiculodto.getId();
            DefaultTableModel val = (DefaultTableModel) JTabelaRetiradaVeiculo.getModel();
            val.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada, devedor});

        }
    }

    //ATUALIZAR TABELA TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void AtualizarTabelaInicial() {

        for (VeiculoDTO veiculodto : veiculodao.getVeiculo()) {

            String id = veiculodto.getId();
            nome = veiculodto.getNome();
            tipo = veiculodto.getTipo();
            modelo = veiculodto.getModelo();
            placa = veiculodto.getPlaca();
            horaentrada = veiculodto.getEntrada();

            DefaultTableModel val = (DefaultTableModel) JTabelaInicial.getModel();
            val.addRow(new String[]{id, nome, tipo, modelo, placa, horaentrada});

        }
    }

    //ATUALIZAR TABELA TODA VEZ QUE HOUVER UMA MODIFICAÇÃO
    protected void HoraAtual() {

        Timer time = new Timer(1000, new hora());
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

    //CALCULAR VALOR A SER COBRADO
    protected void CalcularValor(VeiculoDTO veiculodto) {

        SimpleDateFormat hora = new SimpleDateFormat("HHmm");

        for (VeiculoDTO veiculo : veiculodao.getDadosProprietarioPorID(veiculodto)) {
            float valorbase = veiculo.getValor();
            System.out.println(valorbase);

            int calcular = 0;

            Date saida = new Date();
            int horaentrada = Integer.parseInt(veiculo.getHoraentrada());
            String horariosaida = hora.format(saida);
            String nome = null;
            int horasaida = Integer.parseInt(horariosaida);

            calcular = horasaida - horaentrada;

            if (calcular < 30) {

                valorbase = veiculo.getValor();
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);

            }

            if (calcular >= 30 && calcular < 60) {

                valorbase = veiculo.getValor() + 3;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);

            }
            if (calcular >= 60 && calcular < 90) {

                valorbase = veiculo.getValor() + 4;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);

            }
            if (calcular >= 90 && calcular < 120) {

                valorbase = veiculo.getValor() + 8;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 120 && calcular < 150) {

                valorbase = veiculo.getValor() + 12;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 150 && calcular < 180) {

                valorbase = veiculo.getValor() + 16;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 180 && calcular < 210) {

                valorbase = veiculo.getValor() + 20;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 210) {

                valorbase = veiculo.getValor() + 24;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorID(veiculodto, Saida, valor);
                telaretira.setVisible(true);

            }
        }
    }
// Calcular Valor pelo Nome

    protected void CalcularValorporNome(VeiculoDTO veiculodto) {

        SimpleDateFormat hora = new SimpleDateFormat("HHmm");

        for (VeiculoDTO veiculo : veiculodao.getDadosProprietarioPorNome(veiculodto)) {
            float valorbase = veiculo.getValor();
            System.out.println(valorbase);

            int calcular = 0;

            Date saida = new Date();
            int horaentrada = Integer.parseInt(veiculo.getHoraentrada());
            String horariosaida = hora.format(saida);
            String nome = null;
            int horasaida = Integer.parseInt(horariosaida);

            calcular = horasaida - horaentrada;
            System.out.println(calcular);
            if (calcular < 30) {

                valorbase = veiculo.getValor();
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);

            }

            if (calcular >= 30 && calcular < 60) {

                valorbase = veiculo.getValor() + 3;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);

            }
            if (calcular >= 60 && calcular < 90) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 4;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);

            }
            if (calcular >= 90 && calcular < 120) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 8;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 120 && calcular < 150) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 12;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 150 && calcular < 180) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 16;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 180 && calcular < 210) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 20;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);
            }
            if (calcular >= 210) {

                veiculo.setValor(veiculo.getValor() + 3);
                valorbase = veiculo.getValor() + 24;
                veiculo.setValor(valorbase);
                veiculodao.CadastrarNovoValor(veiculodto);

                SimpleDateFormat formatar = new SimpleDateFormat("dd/MM - HH:mm:ss");
                String Saida = formatar.format(saida);
                String valor = Float.toString(valorbase);
                System.out.println(valorbase);
                nome = veiculo.getNome();
                Tela_ComprovanteRetirada telaretira = new Tela_ComprovanteRetirada(veiculodto, Saida, valor);
                telaretira.ComprovanteEntregaPorNome(veiculodto, nome, Saida, valor);
                telaretira.setVisible(true);

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
        jLabel8 = new javax.swing.JLabel();
        btnTelaInicial = new com.k33ptoo.components.KButton();
        btnEntradadeVeiculo = new com.k33ptoo.components.KButton();
        btnRetiradaVeiculo = new com.k33ptoo.components.KButton();
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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Icon_logOut.png"))); // NOI18N
        PainelMenu.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 630, -1, -1));

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
                "Id", "Nome do Prop.", "Tipo", "Modelo", "Placa", "Horário de Entrada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
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
                true, false, false, false, false, false, false
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
        //Instaciar a Tela Conferir Dados

        AtualizarTabelaEntrada();
        AtualizarTabelaInicial();
        AtualizarTabelaRetirada();
        setarQuantidadenoPatio();


    }//GEN-LAST:event_btnCadastrarEntradaActionPerformed

    private void btnBuscarPorNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPorNomeActionPerformed

        if (txtBuscarNome.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algum nome para Pesquisar\n Consulte a tabela abaixo.");
        } else {
            veiculo.setNome(txtBuscarNome.getText());

            CalcularValorporNome(veiculo);

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
            AtualizarTabelaEntrada();
            AtualizarTabelaInicial();
            AtualizarTabelaRetirada();
            setarQuantidadenoPatio();
            VeiculosRetirados();
        }
    }//GEN-LAST:event_btnBuscarPorNomeActionPerformed

    private void btnBuscarporIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarporIDActionPerformed
        if (txtBuscarID.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite algum número para Pesquisar\n Consulte a tabela abaixo.");
        } else {
            veiculo.setUltimoid(Integer.parseInt(txtBuscarID.getText()));

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
            AtualizarTabelaEntrada();
            AtualizarTabelaInicial();
            AtualizarTabelaRetirada();
            setarQuantidadenoPatio();
            VeiculosRetirados();
        }

    }//GEN-LAST:event_btnBuscarporIDActionPerformed

    private void btnRetiradaVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetiradaVeiculoActionPerformed

        PainelSaidadeVeiculo.setVisible(true);
        PainelEntradaVeiculo.setVisible(false);
        PainelTelaInicial.setVisible(false);
      

    }//GEN-LAST:event_btnRetiradaVeiculoActionPerformed

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
        AtualizarTabelaEntrada();
        AtualizarTabelaInicial();
        AtualizarTabelaRetirada();
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
        AtualizarTabelaEntrada();
        AtualizarTabelaInicial();
        AtualizarTabelaRetirada();
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
    private javax.swing.JPanel PainelEntradaVeiculo;
    private keeptoo.KGradientPanel PainelMenu;
    private javax.swing.JPanel PainelSaidadeVeiculo;
    private keeptoo.KGradientPanel PainelTelaInicial;
    private com.k33ptoo.components.KButton btnAlterarDadosVeiculo;
    private com.k33ptoo.components.KButton btnBuscarPorNome;
    private com.k33ptoo.components.KButton btnBuscarporID;
    private com.k33ptoo.components.KButton btnBuscarporID1;
    private com.k33ptoo.components.KButton btnBuscarporID2;
    private com.k33ptoo.components.KButton btnCadastrarEntrada;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField txtCorCarro;
    private javax.swing.JTextField txtMarcaModeloCarro;
    private javax.swing.JTextField txtNomeProprietario;
    private javax.swing.JTextField txtPlacaCarro;
    private javax.swing.JTextField txtTelefoneProprietario;
    private javax.swing.JTextField txtTipoCarro;
    // End of variables declaration//GEN-END:variables

} // FIM DA CLASSE PRINCIPAL
