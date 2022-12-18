
import DAO.FilialDAO;
import DTO.FilialDTO;
import java.awt.Color;
import java.awt.Toolkit;




public class Tela_AlterarDadosFilial extends javax.swing.JFrame {
    
    FilialDTO filial = new FilialDTO();
    FilialDAO filialdao = new FilialDAO();
    private int mousex, mousey;
    
    public Tela_AlterarDadosFilial() {
        initComponents();
        InserirIcone(this);
        PainelAlterarFilial.setVisible(false);
    }
    
    protected void InserirIcone(Tela_AlterarDadosFilial dashboard){ 
        
        dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/Icon_FSATOOLKIT.png"));
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Btn_Exit = new javax.swing.JLabel();
        btnBuscarCampus = new com.k33ptoo.components.KButton();
        txtBuscarCampus = new javax.swing.JTextField();
        PainelAlterarFilial = new com.k33ptoo.components.KGradientPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtAlterarEndereco = new javax.swing.JTextField();
        txtAltearTelefone = new javax.swing.JTextField();
        txtAlterarCampus = new javax.swing.JTextField();
        txtAlterarCEP1 = new javax.swing.JTextField();
        btnAlterar = new com.k33ptoo.components.KButton();

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
        jLabel6.setText("Digite o Campus:");
        kGradientPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("ALTERAR DADOS DA FILIAL");
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

        txtBuscarCampus.setForeground(new java.awt.Color(153, 153, 153));
        txtBuscarCampus.setText("FSA-N**");
        txtBuscarCampus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBuscarCampusMouseClicked(evt);
            }
        });
        kGradientPanel1.add(txtBuscarCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 370, -1));

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
        jLabel5.setText("Telefone:");
        PainelAlterarFilial.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 270, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Campus:");
        PainelAlterarFilial.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Endereço:");
        PainelAlterarFilial.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Cep:");
        PainelAlterarFilial.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));
        PainelAlterarFilial.add(txtAlterarEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 360, -1));
        PainelAlterarFilial.add(txtAltearTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 170, -1));
        PainelAlterarFilial.add(txtAlterarCampus, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 370, -1));
        PainelAlterarFilial.add(txtAlterarCEP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 160, -1));

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
        
        String campus = txtBuscarCampus.getText();
        
        filial.setCampus(campus);
        
        for(FilialDTO filialdto: filialdao.ListarDadosFilialCampus(filial)) { 
            
            txtAlterarCampus.setText(filialdto.getCampus());
            txtAlterarEndereco.setText(filialdto.getEndereco());
            txtAltearTelefone.setText(filialdto.getTelefone());
            txtAlterarCEP1.setText(filialdto.getCep());
        }
        
        
        
        PainelAlterarFilial.setVisible(true);
    }//GEN-LAST:event_btnBuscarCampusActionPerformed

    private void txtBuscarCampusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBuscarCampusMouseClicked
       txtBuscarCampus.setText("");
       txtBuscarCampus.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_txtBuscarCampusMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        
      
        filial.setCampus(txtAlterarCampus.getText());
        filial.setTelefone(txtAltearTelefone.getText());
        filial.setEndereco(txtAlterarEndereco.getText());
        filial.setCep(txtAlterarCEP1.getText());
        filial.setCampus2(txtAlterarCampus.getText());
        
        filialdao.AlterarDadosFilial(filial);
        
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
                new Tela_AlterarDadosFilial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Btn_Exit;
    private com.k33ptoo.components.KGradientPanel PainelAlterarFilial;
    private com.k33ptoo.components.KButton btnAlterar;
    private com.k33ptoo.components.KButton btnBuscarCampus;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private javax.swing.JTextField txtAltearTelefone;
    private javax.swing.JTextField txtAlterarCEP1;
    private javax.swing.JTextField txtAlterarCampus;
    private javax.swing.JTextField txtAlterarEndereco;
    private javax.swing.JTextField txtBuscarCampus;
    // End of variables declaration//GEN-END:variables
}
