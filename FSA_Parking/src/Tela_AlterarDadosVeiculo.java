
import DAO.VeiculoDAO;
import DTO.VeiculoDTO;
import java.awt.Color;
import java.awt.Toolkit;

public class Tela_AlterarDadosVeiculo extends javax.swing.JFrame {

    VeiculoDTO veiculodto = new VeiculoDTO();
    VeiculoDAO veiculodao = new VeiculoDAO();
     private int mousex, mousey;
     
    public Tela_AlterarDadosVeiculo() {
        initComponents();
        InserirIcone(this);
        PainelAlterarFilial.setVisible(false);
    }
    protected void InserirIcone(Tela_AlterarDadosVeiculo dashboard){ 
        
        dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/Icon_FSATOOLKIT.png"));
    }
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Btn_Exit = new javax.swing.JLabel();
        btnBuscarCampus = new com.k33ptoo.components.KButton();
        txtBuscarId = new javax.swing.JTextField();
        PainelAlterarFilial = new com.k33ptoo.components.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        btnAlterar = new com.k33ptoo.components.KButton();
        jLabel12 = new javax.swing.JLabel();
        txtCor = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtAno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Digite o ID:");
        kGradientPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ALTERAR DADOS DO VEICULO");
        kGradientPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        Btn_Exit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Btn_Exit.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Exit.setText("X");
        Btn_Exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_ExitMouseClicked(evt);
            }
        });
        kGradientPanel1.add(Btn_Exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, -1, -1));

        btnBuscarCampus.setText("Buscar");
        btnBuscarCampus.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnBuscarCampus.setkAllowGradient(false);
        btnBuscarCampus.setkBackGroundColor(new java.awt.Color(0, 0, 153));
        btnBuscarCampus.setkBorderRadius(50);
        btnBuscarCampus.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnBuscarCampus.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnBuscarCampus.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnBuscarCampus.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnBuscarCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCampusActionPerformed(evt);
            }
        });
        kGradientPanel1.add(btnBuscarCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 70, 40));

        txtBuscarId.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarId.setText("Digite o ID...");
        txtBuscarId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarIdMouseClicked(evt);
            }
        });
        kGradientPanel1.add(txtBuscarId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 370, -1));

        PainelAlterarFilial.setForeground(new java.awt.Color(153, 153, 153));
        PainelAlterarFilial.setkBorderRadius(0);
        PainelAlterarFilial.setkStartColor(new java.awt.Color(0, 51, 255));
        PainelAlterarFilial.setOpaque(false);
        PainelAlterarFilial.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                PainelAlterarFilialMouseDragged(evt);
            }
        });
        PainelAlterarFilial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PainelAlterarFilialMousePressed(evt);
            }
        });
        PainelAlterarFilial.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Placa:");
        PainelAlterarFilial.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nome:");
        PainelAlterarFilial.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Marca/Modelo:");
        PainelAlterarFilial.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tipo:");
        PainelAlterarFilial.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));
        PainelAlterarFilial.add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 290, -1));
        PainelAlterarFilial.add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 110, -1));
        PainelAlterarFilial.add(txtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 380, -1));
        PainelAlterarFilial.add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 110, -1));

        btnAlterar.setText("Alterar");
        btnAlterar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnAlterar.setkAllowGradient(false);
        btnAlterar.setkBackGroundColor(new java.awt.Color(51, 153, 255));
        btnAlterar.setkBorderRadius(50);
        btnAlterar.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnAlterar.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnAlterar.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnAlterar.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });
        PainelAlterarFilial.add(btnAlterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 140, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cor:");
        PainelAlterarFilial.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, -1, -1));
        PainelAlterarFilial.add(txtCor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 140, -1));
        PainelAlterarFilial.add(txtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 140, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Telefone:");
        PainelAlterarFilial.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Ano:");
        PainelAlterarFilial.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));
        PainelAlterarFilial.add(txtAno, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 80, -1));

        kGradientPanel1.add(PainelAlterarFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 420));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_ExitMouseClicked
        dispose();
    }//GEN-LAST:event_Btn_ExitMouseClicked

    private void btnBuscarCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCampusActionPerformed

        setId(Integer.parseInt(txtBuscarId.getText()));

        veiculodto.setUltimoid(getId());

        for (VeiculoDTO veiculo : veiculodao.getDadosProprietarioPorID(veiculodto)) {

            txtNome.setText(veiculo.getNome());
            txtMarca.setText(veiculo.getModelo());
            txtPlaca.setText(veiculo.getPlaca());
            txtTipo.setText(veiculo.getTipo());
            txtTelefone.setText(veiculo.getTelefone());
            txtCor.setText(veiculo.getCor());
            txtAno.setText(veiculo.getAno());
        }

        PainelAlterarFilial.setVisible(true);
    }//GEN-LAST:event_btnBuscarCampusActionPerformed

    private void txtBuscarIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarIdMouseClicked
        txtBuscarId.setText("");
        txtBuscarId.setForeground(new Color(0, 0, 0));
    }//GEN-LAST:event_txtBuscarIdMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed

        veiculodto.setUltimoid(getId());

        veiculodto.setNome(txtNome.getText());
        veiculodto.setTelefone(txtTelefone.getText());
        veiculodto.setModelo(txtMarca.getText());
        veiculodto.setTipo(txtTipo.getText());
        veiculodto.setAno(txtAno.getText());
        veiculodto.setCor(txtCor.getText());
        veiculodto.setPlaca(txtPlaca.getText());

        veiculodao.AlterarVeiculo(veiculodto);

        txtBuscarId.setText("");
        txtNome.setText("");
        txtMarca.setText("");
        txtPlaca.setText("");
        txtTipo.setText("");
        txtTelefone.setText("");
        txtCor.setText("");
        txtAno.setText("");

    }//GEN-LAST:event_btnAlterarActionPerformed

    private void PainelAlterarFilialMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PainelAlterarFilialMousePressed
        mousex = evt.getX();
        mousey = evt.getY();
        
    }//GEN-LAST:event_PainelAlterarFilialMousePressed

    private void PainelAlterarFilialMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PainelAlterarFilialMouseDragged
        this.setLocation(this.getX() + evt.getX() - mousex, this.getY() + evt.getY() - mousey);
    }//GEN-LAST:event_PainelAlterarFilialMouseDragged

    private void kGradientPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MousePressed
        mousex = evt.getX();
        mousey = evt.getY();
        
    }//GEN-LAST:event_kGradientPanel1MousePressed

    private void kGradientPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MouseDragged
        this.setLocation(this.getX() + evt.getX() - mousex, this.getY() + evt.getY() - mousey);
    }//GEN-LAST:event_kGradientPanel1MouseDragged

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela_AlterarDadosVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Btn_Exit;
    private com.k33ptoo.components.KGradientPanel PainelAlterarFilial;
    private com.k33ptoo.components.KButton btnAlterar;
    private com.k33ptoo.components.KButton btnBuscarCampus;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtBuscarId;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
