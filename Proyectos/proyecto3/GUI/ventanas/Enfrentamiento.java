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
public class Enfrentamiento extends javax.swing.JFrame {

    /**
     * Creates new form Enfrentamiento
     */
    //Hay que revisar los argumentos del presunto constructor de Enfrentamiento 
    /*public Enfrentamiento(Pelea enfrentamiento) {
        initComponents();
        // Código para mostrar la información del peleador en los labes 
        // Muestra el nombre, la habilidad, etc. 
        // Cuando pique el boton hay que desaparecerlo para que ya no apueste o hacer ineditable el btón 
        // O cuando ya haya apostado mandar un mensaje 
    }*/
    public Enfrentamiento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonPeleador1 = new javax.swing.JButton();
        jButtonPeleador2 = new javax.swing.JButton();
        jLabelPeleador1 = new javax.swing.JLabel();
        jLabelPeleador2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPeleador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPeleador1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPeleador1ActionPerformed

    private void jButtonPeleador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPeleador2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPeleador2ActionPerformed

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
            java.util.logging.Logger.getLogger(Enfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enfrentamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Enfrentamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPeleador1;
    private javax.swing.JButton jButtonPeleador2;
    private javax.swing.JLabel jLabelPeleador1;
    private javax.swing.JLabel jLabelPeleador2;
    // End of variables declaration//GEN-END:variables
}