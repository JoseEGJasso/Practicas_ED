/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

/**
 *
 * @author DiegoDM
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelBienvenida = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldNuevaContraseña = new javax.swing.JTextField();
        jTextFieldConfirmacionContra = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jLabelFondo = new javax.swing.JLabel();
        jLabelConfirmacion = new javax.swing.JLabel();

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

        jButtonRegistrar.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 120, 30));
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

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 277, 472));

        jLabelConfirmacion.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelConfirmacion.setForeground(new java.awt.Color(255, 255, 255));
        jLabelConfirmacion.setText("Confirma tu contraseña");
        getContentPane().add(jLabelConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBackActionPerformed
        this.setVisible(false);
        interfaz inter = new interfaz();
        inter.setVisible(true);
    }//GEN-LAST:event_jButtonBackActionPerformed

    private void jTextFieldConfirmacionContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldConfirmacionContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldConfirmacionContraActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
       String nombre = jTextFieldNombre.getText();
       ///Aquí va lo del código después de obtener el nombre 
       String password = jTextFieldNuevaContraseña.getText();
       String confirmedPassword = jTextFieldConfirmacionContra.getText();
       //Aquí va el código de las contraseñas 
       
       //if(nombre == null)            
       
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JLabel jLabelConfirmacion;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JTextField jTextFieldConfirmacionContra;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNuevaContraseña;
    // End of variables declaration//GEN-END:variables
}