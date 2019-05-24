
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author DiegoDM
 */
public class interfaz extends javax.swing.JFrame {
    
 
    Registro registro; 
    Torneo torneo; 
    //SistemaDeApuestas sistema; 
    /**
     * Creates new form interfaz
     */
    public interfaz() {
        
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSalir = new javax.swing.JButton();
        jLabelUsuario = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jButtonInicioSesion = new javax.swing.JButton();
        jButtonRegistro = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonSalir.setBackground(new java.awt.Color(153, 0, 0));
        jButtonSalir.setFont(new java.awt.Font("Telugu MN", 1, 14)); // NOI18N
        jButtonSalir.setText("Salir");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, -1, -1));

        jLabelUsuario.setBackground(new java.awt.Color(153, 204, 255));
        jLabelUsuario.setFont(new java.awt.Font("Telugu MN", 1, 18)); // NOI18N
        jLabelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUsuario.setText("Usuario:");
        getContentPane().add(jLabelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 90, 20));

        jLabelPass.setFont(new java.awt.Font("Telugu MN", 1, 18)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass.setText("Contraseña: ");
        getContentPane().add(jLabelPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jTextFieldUsuario.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 180, -1));

        jPasswordField.setBackground(new java.awt.Color(204, 204, 204));
        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 180, -1));

        jButtonInicioSesion.setBackground(new java.awt.Color(255, 255, 255));
        jButtonInicioSesion.setFont(new java.awt.Font("Telugu MN", 1, 18)); // NOI18N
        jButtonInicioSesion.setText("Iniciar Sesion");
        jButtonInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioSesionActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        jButtonRegistro.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRegistro.setFont(new java.awt.Font("Telugu MN", 1, 14)); // NOI18N
        jButtonRegistro.setText("Registrarse");
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 430, 140, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-4, -2, 720, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        System.exit(0); /// Boton para salir 
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
      this.setVisible(false);
      registro = new Registro(); 
      registro.setVisible(true);
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void jButtonInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioSesionActionPerformed
       
      this.setVisible(false);
      torneo = new Torneo();
      torneo.setVisible(true);
    }//GEN-LAST:event_jButtonInicioSesionActionPerformed

    /**
     * Clase que modela un JFrame de la ventana de Registro  
     * 
     * 
     * 
     */
    class Registro extends javax.swing.JFrame {
        
        public Registro() {
        initComponents();
        this.setLocationRelativeTo(null);
         }
        
        private void initComponents() {

        jLabelBienvenida = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldNuevaContraseña = new javax.swing.JTextField();
        jTextFieldConfirmacionContra = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jLabelConfirmacion = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBienvenida.setFont(new java.awt.Font("Telugu MN", 1, 18)); // NOI18N
        jLabelBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBienvenida.setText("Bienvenido al Registro");
        getContentPane().add(jLabelBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 30));

        jButtonBack.setBackground(new java.awt.Color(153, 0, 0));
        jButtonBack.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jButtonBack.setText("Regresar");
        jButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBackActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));
        getContentPane().add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, -1));
        getContentPane().add(jTextFieldNuevaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 190, -1));

        jTextFieldConfirmacionContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldConfirmacionContraActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldConfirmacionContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 190, -1));

        jLabelNombre.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setText("Ingresa tu nombre");
        getContentPane().add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 190, -1));

        jLabelPass.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass.setText("Ingresa tu contraseña ");
        getContentPane().add(jLabelPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 170, -1));

        jLabelConfirmacion.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelConfirmacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelConfirmacion.setText("Confirma tu contraseña");
        getContentPane().add(jLabelConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 277, 472));

        pack();
        }
        
        private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.setVisible(false);
        interfaz inter = new interfaz();
        inter.setVisible(true);
         }                                           

        private void jTextFieldConfirmacionContraActionPerformed(java.awt.event.ActionEvent evt) {                                                             
        // TODO add your handling code here:
        }
        
        private javax.swing.JButton jButtonBack;
        private javax.swing.JLabel jLabelBienvenida;
        private javax.swing.JLabel jLabelConfirmacion;
        private javax.swing.JLabel jLabelFondo;
        private javax.swing.JLabel jLabelNombre;
        private javax.swing.JLabel jLabelPass;
        private javax.swing.JTextField jTextFieldConfirmacionContra;
        private javax.swing.JTextField jTextFieldNombre;
        private javax.swing.JTextField jTextFieldNuevaContraseña;
        
    }
    
    /**
     * Clase que modela un JFrame en donde se presenta la interfaz del Torneo  
     * 
     * 
     */
    class Torneo extends javax.swing.JFrame {
        
        public Torneo() {
        initComponents();
        this.setLocationRelativeTo(null);
        }
        
        public void ventanaEnfrentamientos(){
        Enfrentamiento pelea = new Enfrentamiento();
        pelea.setVisible(true);
        }
        
        private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonFinal = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("PELEA 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 100, 40));

        jButton2.setText("PELEA 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 90, 40));

        jButton3.setText("PELEA 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 360, 90, 40));

        jButton4.setText("PELEA 4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 100, 40));

        jButton5.setText("PELEA 5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 90, 40));

        jButton6.setText("PELEA 6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 80, 40));

        jButtonFinal.setText("FINAL");
        jButtonFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 160, 50));

        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, -1, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Torneo.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 694, -1));

        jRadioButton1.setText("jRadioButton1");
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        pack();
        }
        
        private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

         private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        ventanaEnfrentamientos();
        }                                        

        private void jButtonFinalActionPerformed(java.awt.event.ActionEvent evt) {                                             
        ventanaEnfrentamientos();
        }                                            

        private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.setVisible(false);
        interfaz inter = new interfaz();
        inter.setVisible(true);
        }
        
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JButton jButton5;
        private javax.swing.JButton jButton6;
        private javax.swing.JButton jButtonExit;
        private javax.swing.JButton jButtonFinal;
        private javax.swing.JLabel jLabelFondo;
        private javax.swing.JRadioButton jRadioButton1;
    }
    
    class Enfrentamiento extends javax.swing.JFrame {
        
        public Enfrentamiento() {
        initComponents();
        this.setLocationRelativeTo(null);
        }
        
        private void initComponents() {

        jButtonPeleador1 = new javax.swing.JButton();
        jButtonPeleador2 = new javax.swing.JButton();
        jLabelPeleador1 = new javax.swing.JLabel();
        jLabelPeleador2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonPeleador1.setText("Apostar");
        jButtonPeleador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPeleador1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPeleador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jButtonPeleador2.setText("Apostar");
        jButtonPeleador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPeleador2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPeleador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        jLabelPeleador1.setText("Info del peleador");
        getContentPane().add(jLabelPeleador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 90));

        jLabelPeleador2.setText("Info del peleador");
        getContentPane().add(jLabelPeleador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 110, 90));

        pack();
        }
        
        private void jButtonPeleador1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        }                                                

        private void jButtonPeleador2ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        }
        
        private javax.swing.JButton jButtonPeleador1;
        private javax.swing.JButton jButtonPeleador2;
        private javax.swing.JLabel jLabelPeleador1;
        private javax.swing.JLabel jLabelPeleador2;
    }
    
    
    
    
    /*
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
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz().setVisible(true);
            }
        });
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInicioSesion;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldUsuario;
    // End of variables declaration//GEN-END:variables
}
