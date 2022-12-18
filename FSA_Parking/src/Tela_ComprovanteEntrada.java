
import DAO.VeiculoDAO;
import DTO.UsuarioDTO;
import DTO.VeiculoDTO;
import java.awt.Toolkit;


public class Tela_ComprovanteEntrada extends javax.swing.JFrame {

    UsuarioDTO usuariodto = new UsuarioDTO();
    Dashboard_Principal dashboardprincipal = new Dashboard_Principal(usuariodto);
    private int mousex, mousey;

    public Tela_ComprovanteEntrada(String nome) {
        initComponents();
        InserirIcone(this);
        ComprovanteEntrada(nome);
        
    }
    protected void InserirIcone(Tela_ComprovanteEntrada dashboard){ 
        
        dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/Icon_FSATOOLKIT.png"));
    }

    protected void ComprovanteEntrada(String nome) {

        VeiculoDAO veiculodao = new VeiculoDAO();

        for (VeiculoDTO veiculo : veiculodao.PegarDadosPeloNome(nome)) {

            lblId.setText(veiculo.getId());
            lblNome1.setText(veiculo.getNome());
            lblModelo1.setText(veiculo.getModelo());
            lblCor.setText(veiculo.getCor());
            lblAno.setText(veiculo.getAno());
            lblPlaca.setText(veiculo.getPlaca());
            lblDataEntrada.setText(veiculo.getEntrada());

        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        lblNome1 = new javax.swing.JLabel();
        lblModelo1 = new javax.swing.JLabel();
        lblPlaca = new javax.swing.JLabel();
        lblCor = new javax.swing.JLabel();
        btnImprimirComprovante = new com.k33ptoo.components.KButton();
        jLabel7 = new javax.swing.JLabel();
        Btn_Exit = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblDataEntrada = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 51, 255));
        kGradientPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                kGradientPanel1MouseDragged(evt);
            }
        });
        kGradientPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                kGradientPanel1MousePressed(evt);
            }
        });
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DE ENTRADA");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ano:");
        kGradientPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome:");
        kGradientPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Marca/Modelo:");
        kGradientPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Placa:");
        kGradientPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cor:");
        kGradientPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        lblAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAno.setForeground(new java.awt.Color(255, 255, 255));
        lblAno.setText("lblModelo");
        kGradientPanel1.add(lblAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        lblNome1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNome1.setForeground(new java.awt.Color(255, 255, 255));
        lblNome1.setText("lblNome");
        kGradientPanel1.add(lblNome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, -1));

        lblModelo1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblModelo1.setForeground(new java.awt.Color(255, 255, 255));
        lblModelo1.setText("lblModelo");
        kGradientPanel1.add(lblModelo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        lblPlaca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPlaca.setForeground(new java.awt.Color(255, 255, 255));
        lblPlaca.setText("lblPlaca");
        kGradientPanel1.add(lblPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        lblCor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCor.setForeground(new java.awt.Color(255, 255, 255));
        lblCor.setText("lblModelo");
        kGradientPanel1.add(lblCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        btnImprimirComprovante.setText("IMPRIMIR");
        btnImprimirComprovante.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnImprimirComprovante.setkAllowGradient(false);
        btnImprimirComprovante.setkBackGroundColor(new java.awt.Color(51, 153, 255));
        btnImprimirComprovante.setkBorderRadius(50);
        btnImprimirComprovante.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnImprimirComprovante.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnImprimirComprovante.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnImprimirComprovante.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnImprimirComprovante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirComprovanteActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btnImprimirComprovante, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 140, 40));

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("IMPRIMIR COMPROVANTE");
        kGradientPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        Btn_Exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Btn_Exit.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Exit.setText("X");
        Btn_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_ExitMouseClicked(evt);
            }
        });
        kGradientPanel1.add(Btn_Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Id:");
        kGradientPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, -1, -1));

        lblId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblId.setForeground(new java.awt.Color(255, 255, 255));
        lblId.setText("lblId");
        kGradientPanel1.add(lblId, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Entrada:");
        kGradientPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));

        lblDataEntrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDataEntrada.setForeground(new java.awt.Color(255, 255, 255));
        lblDataEntrada.setText("DataEntrada");
        kGradientPanel1.add(lblDataEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
   
    private void Btn_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_ExitMouseClicked
        dispose();
    }//GEN-LAST:event_Btn_ExitMouseClicked

    private void btnImprimirComprovanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirComprovanteActionPerformed
        
        dispose();

    }//GEN-LAST:event_btnImprimirComprovanteActionPerformed

    private void kGradientPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MousePressed
        mousex = evt.getX();
        mousey = evt.getY();
        
        
    }//GEN-LAST:event_kGradientPanel1MousePressed

    private void kGradientPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MouseDragged
        this.setLocation(this.getX() + evt.getX() - mousex, this.getY() + evt.getY() - mousey);
    }//GEN-LAST:event_kGradientPanel1MouseDragged

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            VeiculoDTO veiculodto = new VeiculoDTO();
            String nome = null;
            new Tela_ComprovanteEntrada(nome).setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Btn_Exit;
    private com.k33ptoo.components.KButton btnImprimirComprovante;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JLabel lblAno;
    private javax.swing.JLabel lblCor;
    private javax.swing.JLabel lblDataEntrada;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblModelo1;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblPlaca;
    // End of variables declaration//GEN-END:variables
}
