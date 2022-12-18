
import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    
    UsuarioDTO usuariodto = new UsuarioDTO();
    UsuarioDAO usuariodao = new UsuarioDAO();
    private int mouseX, mouseY;
    public Login() {
        InserirIcone(this);
        initComponents();
        
    }
    
    protected void InserirIcone(Login login){ 
        
        login.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/Icon_FSATOOLKIT.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new keeptoo.KGradientPanel();
        btnEntrar = new com.k33ptoo.components.KButton();
        txtUsuario = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        kGradientPanel1.setkEndColor(new java.awt.Color(48, 58, 105));
        kGradientPanel1.setkStartColor(new java.awt.Color(0, 30, 255));
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
        kGradientPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kGradientPanel1KeyPressed(evt);
            }
        });
        kGradientPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEntrar.setText("Entrar");
        btnEntrar.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnEntrar.setkAllowGradient(false);
        btnEntrar.setkBackGroundColor(new java.awt.Color(0, 51, 255));
        btnEntrar.setkBorderRadius(50);
        btnEntrar.setkHoverForeGround(new java.awt.Color(0, 102, 255));
        btnEntrar.setkHoverStartColor(new java.awt.Color(0, 51, 255));
        btnEntrar.setkPressedColor(new java.awt.Color(0, 0, 153));
        btnEntrar.setkSelectedColor(new java.awt.Color(0, 51, 255));
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEntrarKeyPressed(evt);
            }
        });
        kGradientPanel1.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 123, -1));

        txtUsuario.setBackground(new Color(0,0,0,0));
        txtUsuario.setForeground(new java.awt.Color(255, 255, 255));
        txtUsuario.setText("Digite seu usuario...");
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
        });
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });
        kGradientPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 205, 30));

        txtSenha.setBackground(new Color(0,0,0,0));
        txtSenha.setForeground(new java.awt.Color(255, 255, 255));
        txtSenha.setText("senha");
        txtSenha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 255, 255)));
        txtSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSenhaMouseClicked(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });
        kGradientPanel1.add(txtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 205, 30));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Senha:");
        kGradientPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Usuario:");
        kGradientPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/UserIcon.png"))); // NOI18N
        kGradientPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/LogoFSAPARKING.png"))); // NOI18N
        kGradientPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        kGradientPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 13, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        if (txtUsuario.getText().equals("Digite seu usuario...")) {
            txtUsuario.setText("");
        }
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void txtSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSenhaMouseClicked
        if (txtSenha.getText().equals("senha")) {
            txtSenha.setText("");
        }
    }//GEN-LAST:event_txtSenhaMouseClicked

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed

        try {
            String usuario = txtUsuario.getText();
            String senha = new String(txtSenha.getPassword());
            
            
            
            UsuarioDAO usuariodao = new UsuarioDAO();
            
            usuariodto.setUsuario(usuario);
            usuariodto.setSenha(senha);
            usuariodao.getTipo(usuariodto);
            usuariodao.getDados(usuariodto);
            Dashboard_Principal dashboard = new Dashboard_Principal(usuariodto);
            Dashboard_Funcionario dashboardfunci = new Dashboard_Funcionario(usuariodto);
            //dashboard.setarDados(usuariodto);
            
            ResultSet rusuariodao = usuariodao.autenticarUsuario(usuariodto);
            
            
            
            if (rusuariodao.next()) {
     
                for (UsuarioDTO usuarios : usuariodao.getTipo(usuariodto)) {
                    String tipo = usuarios.getTipo();
                    
                    if (tipo.equals("Administrador")) {
                        
                        
                        dashboardfunci.setVisible(false);
                        dashboard.setVisible(true);
                        dispose();
                        
                        
                    } else if(tipo.equals("Padrão")) {
                        
                        
                        dashboard.setVisible(false);
                        dashboardfunci.setVisible(true);
                        dispose();
                        
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario ou senha Invalida!");
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "LOGIN: " + erro);
        }


    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntrarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            UsuarioDTO usuariodto = null;
            Dashboard_Principal dashboard = new Dashboard_Principal(usuariodto);
            dashboard.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btnEntrarKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed


    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnEntrar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnEntrar.requestFocus();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void kGradientPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kGradientPanel1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtUsuario.requestFocus();
            txtUsuario.setText("");
        }
    }//GEN-LAST:event_kGradientPanel1KeyPressed

    private void kGradientPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MousePressed
        mouseX = evt.getX();
        mouseY = evt.getY();
        
    }//GEN-LAST:event_kGradientPanel1MousePressed

    private void kGradientPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kGradientPanel1MouseDragged
        
        this.setLocation(this.getX() + evt.getX() - mouseX, this.getY() + evt.getY() - mouseY);
        
    }//GEN-LAST:event_kGradientPanel1MouseDragged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnEntrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private keeptoo.KGradientPanel kGradientPanel1;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
