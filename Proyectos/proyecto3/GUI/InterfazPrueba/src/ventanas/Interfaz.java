
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import sistema.*;

/**
 *
 * @author DiegoDM
 */
public class Interfaz extends javax.swing.JFrame {
    
    Registro registro; 
    Torneo torneo;
    LogicaTorneo logicaTorneo;
    SistemaDeApuestas sistema;
 
    /**
     * Creates new form interfaz
     */
    public Interfaz() {
        
        initComponents();
        this.setLocationRelativeTo(null);
    }
    
    public void hacerVisible(){
        this.setVisible(true);
    }
        
    public void hacerInvisible(){
        this.setVisible(false);    
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
        
        torneo= new Torneo();
        registro= new Registro();
        sistema=new SistemaDeApuestas();

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
      registro.setVisible(true);
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void jButtonInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioSesionActionPerformed
        
      String nombreDeUsuario=jTextFieldUsuario.getText();
      String contraseña=new String(jPasswordField.getPassword());
      
      if(nombreDeUsuario.equals("") || contraseña.equals("")){
          JOptionPane.showMessageDialog(null, "Ingresa tu nombre de usuario y tu contraseña!");
          return;
      }
          
      
      char inicio=sistema.iniciarSesion(nombreDeUsuario, contraseña);
      
      jPasswordField.setText("");
      
      if(inicio=='U'){
          JOptionPane.showMessageDialog(null, "Ese usuario no existe! Intente de nuevo");
          return;
      }if(inicio=='C'){
          JOptionPane.showMessageDialog(null, "Contraseña incorrecta! Intente de nuevo");
          return;
      }
      
      logicaTorneo=new LogicaTorneo(sistema.getApostador());
      this.setVisible(false);
      torneo.setVisible(true);
      logicaTorneo.comenzarTorneo();
    }//GEN-LAST:event_jButtonInicioSesionActionPerformed

    /**
     * Clase que modela un JFrame de la ventana de Registro  
     * 
     * 
     * 
     */
    class Registro extends javax.swing.JFrame {

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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabelBienvenida = new javax.swing.JLabel();
        jButtonBack = new javax.swing.JButton();
        jButtonRegistrar = new javax.swing.JButton();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordFieldContraseña = new javax.swing.JPasswordField();
        jPasswordFieldConfirmacion = new javax.swing.JPasswordField();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBienvenida.setFont(new java.awt.Font("Telugu MN", 1, 18)); // NOI18N
        jLabelBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBienvenida.setText("Bienvenido al Registro");
        getContentPane().add(jLabelBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 30));

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

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 190, -1));

        jLabelNombre.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombre.setText("Nombre completo");
        getContentPane().add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 190, -1));

        jLabelPass.setFont(new java.awt.Font("Telugu MN", 0, 14)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass.setText("Contraseña");
        getContentPane().add(jLabelPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 170, -1));

        jLabel1.setBackground(new java.awt.Color(254, 254, 254));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Confirma tu contraseña");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 190, 20));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, -1));

        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Nombre de usuario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 190, 20));

        jPasswordFieldContraseña.setText("jPasswordField1");
        jPasswordFieldContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldContraseñaActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordFieldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 190, -1));

        jPasswordFieldConfirmacion.setText("jPasswordField1");
        getContentPane().add(jPasswordFieldConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 190, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
        jLabelFondo.setText("jLabel1");
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 277, 472));

        pack();
    }// </editor-fold>                        

    private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {                                            
        this.setVisible(false);
        Interfaz.this.setVisible(true);
    }                                           

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {                                                 
       String nombre = jTextFieldNombre.getText();
       String nombreDeUsuario = jTextField1.getText();
       String password = new String(jPasswordFieldContraseña.getPassword());
       String confirmedPassword = new String(jPasswordFieldConfirmacion.getPassword());
       
       jTextFieldNombre.setText("");
       jTextField1.setText("");
       jPasswordFieldContraseña.setText("");
       jPasswordFieldConfirmacion.setText("");
       
       if(!password.equals(confirmedPassword)){
           JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden! Intenta de nuevo");
           return;
       } if(nombreDeUsuario.length()<8){
           JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener al menos 8 caracteres");
           return;
       } if(password.length()<8){
           JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres");
           return;
       }
       for(int i=0;i<nombreDeUsuario.length();i++){
           if(nombreDeUsuario.charAt(i)==' '){
               JOptionPane.showMessageDialog(null, "El nombre de usuario no puede contener espacios");
               return;
           }
       }
       
       for(int i=0;i<password.length();i++){
           if(password.charAt(i)==' '){
               JOptionPane.showMessageDialog(null, "La contraseña no puede contener espacios");
               return;
           }
       }
       
       boolean error =sistema.registrar(nombreDeUsuario, nombre, password);
       
       if(error)
           JOptionPane.showMessageDialog(null, "Registro exitoso! Ahora puedes iniciar sesión");
       else
           JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya está en uso, intenta con otro");
       
    }                                                

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
    }                                                

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void jPasswordFieldContraseñaActionPerformed(java.awt.event.ActionEvent evt) {                                                         
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonBack;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBienvenida;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JPasswordField jPasswordFieldConfirmacion;
    private javax.swing.JPasswordField jPasswordFieldContraseña;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration                
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
            
            if(logicaTorneo.getNumPeleas()<5){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea5 = new Enfrentamiento(logicaTorneo.obtenerPelea(5));
            pelea5.setVisible(true);
            
        }                                        

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            if(logicaTorneo.getNumPeleas()<1){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea1 = new Enfrentamiento(logicaTorneo.obtenerPelea(1));
            pelea1.setVisible(true);
        }                                        

        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
            if(logicaTorneo.getNumPeleas()<4){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea4 = new Enfrentamiento(logicaTorneo.obtenerPelea(4));
            pelea4.setVisible(true);
        }                                        

        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
            if(logicaTorneo.getNumPeleas()<6){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea6 = new Enfrentamiento(logicaTorneo.obtenerPelea(6));
            pelea6.setVisible(true);
        }                                        

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            if(logicaTorneo.getNumPeleas()<2){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea2 = new Enfrentamiento(logicaTorneo.obtenerPelea(2));
            pelea2.setVisible(true);
        }                                        

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            System.out.println(logicaTorneo.getNumPeleas());
           
            if(logicaTorneo.getNumPeleas()<7){
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }
            
            pelea3 = new Enfrentamiento(logicaTorneo.obtenerPelea(3));
            System.out.println(pelea3.peleaAsociada.getP1().getHabilidad());
            System.out.println(pelea3.peleaAsociada.getP1().getProbabilidad(pelea3.peleaAsociada.getP2()));
            System.out.println(pelea3.peleaAsociada.getCuotaP1());
            pelea3.setVisible(true);
        }                                        

        private void jButtonFinalActionPerformed(java.awt.event.ActionEvent evt) {                                             
            
        }                                            

        private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {                                            
            this.setVisible(false);
            sistema.guardar();
            Interfaz.this.setVisible(true);
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
        
        private Enfrentamiento pelea1;
        private Enfrentamiento pelea2;
        private Enfrentamiento pelea3;
        private Enfrentamiento pelea4;
        private Enfrentamiento pelea5;
        private Enfrentamiento pelea6;
        private Enfrentamiento peleaFinal;
        
    }
    
    class Enfrentamiento extends javax.swing.JFrame {
        public Enfrentamiento(Pelea pelea) {
        
            peleaAsociada = pelea;
            initComponents();
            this.setLocationRelativeTo(null);
        }
        
        private void initComponents() {

        jButtonPeleador1 = new javax.swing.JButton();
        jButtonPeleador2 = new javax.swing.JButton();
        jLabelPeleador1 = new javax.swing.JLabel();
        jLabelPeleador2 = new javax.swing.JLabel();
        jTextFieldApuestaP1 = new javax.swing.JTextField();
        jTextFieldApuestaP2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        
        
        
        jLabelPeleador1.setText(peleaAsociada.getP1().getNombre()+"\t"+"$"+String.format("%.2f", peleaAsociada.getCuotaP1()));
        jLabelPeleador2.setText(peleaAsociada.getP2().getNombre()+"\t"+"$"+String.format("%.2f", peleaAsociada.getCuotaP2()));
        
        
        
        //jLabelPeleador1.setText("Apostar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonPeleador1.setText("Apostar");
        jButtonPeleador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPeleador1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPeleador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));

        jButtonPeleador2.setText("Apostar");
        jButtonPeleador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPeleador2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPeleador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 100, -1));

        //jLabelPeleador1.setText("Info del peleador");
        getContentPane().add(jLabelPeleador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 110, 70));

        //jLabelPeleador2.setText("Info del peleador");
        getContentPane().add(jLabelPeleador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 110, 70));

        //jTextFieldApuestaP1.setText("jTextField1");
        getContentPane().add(jTextFieldApuestaP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        //jTextFieldApuestaP2.setText("jTextField2");
        getContentPane().add(jTextFieldApuestaP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, -1, -1));

        jLabel1.setFont(new java.awt.Font("STIXGeneral", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("$");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 20, 30));

        jLabel2.setFont(new java.awt.Font("STIXGeneral", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 0));
        jLabel2.setText("$");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 20, 30));

        pack();
        }
        
        private void jButtonPeleador1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        }                                                

        private void jButtonPeleador2ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        // TODO add your handling code here:
        }
        
        public void setPeleaAsociada(Pelea peleaAsocida){
            this.peleaAsociada=peleaAsociada;
        }
       
        
        private javax.swing.JButton jButtonPeleador1;
        private javax.swing.JButton jButtonPeleador2;
        private javax.swing.JLabel jLabelPeleador1;
        private javax.swing.JLabel jLabelPeleador2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JTextField jTextFieldApuestaP1;
        private javax.swing.JTextField jTextFieldApuestaP2;
        public  Pelea peleaAsociada;
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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
  
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
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
