package ventanas;

import javax.swing.JOptionPane;
import sistema.*;

/**
 * Clase principal para modelar la interfaz gráfica de proyecto 3. 
 *  
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Interfaz extends javax.swing.JFrame {
    
    //Atributos 
    /*
    Como atributos de nuestra interfaz, tenemos:
    registro --> objeto de tipo Registro. Sus funciones se describen en la clase Registro 
    torneo --> objeto de tipo Torneo. La clase Torneo es una clase interna de Interfaz que,
               de igual forma, extiende de JFrame.
    logicatorneo --> objeto de tipo logicaTorneo. Sus funciones se describen en la clase logicaTorneo
    sistema --> objeto de tipo SistemaDePauestas. Sus funciones se describen en la clase SistemasDeApuestas
    inicioTorneo --> Un objeto de tipo Thread, que será el hilo principal para simular el transcurso de un torneo,  
                     lo cual nos permitirá simular muchos torneos de forma indefinida.Entre otras cuestiones. 
    contadores --> Un objeto de tipo Thread con el cual podemos simular un contador de tiempo para ayudar al usuario
                   para saber en cuantos segundos inicia la siguiente ronda o incluso el siguiente torneo. 
    */  
    Registro registro;
    Torneo torneo;
    LogicaTorneo logicaTorneo;
    SistemaDeApuestas sistema;
    Thread inicioTorneo;
    Thread contadores;

    /**
     * Constructor.
     */
    public Interfaz() {

        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * Inicializador. Inicializa todos los atributos de Interfaz. Entre los atributos de Interfaz, se encuentran 
     * componentes de la clase swing y atributos de nuestrás clases en el paquete sistemas, como LogicaDeTorneo y 
     * SistemaDeApuestas. 
     */
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

        torneo = new Torneo();
        registro = new Registro();
        sistema = new SistemaDeApuestas();

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
        getContentPane().add(jTextFieldUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 180, -1));
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
    
    /**
     * Acción realizada. Cuando el botón Registro es precionado, la ventana de Interfaz se hace invisible y la ventana   
     * de registro se hace visible. 
     * @param evt 
     */
    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
        this.setVisible(false);
        registro.setVisible(true);
    }//GEN-LAST:event_jButtonRegistroActionPerformed
    
    /**
     * Acción realizada. El boton de Iniciar Sesion es presionado, se obtiene la String del TextField del Usuario y de la Contraseña,
     * después de verifcar que ambos sean distintos a null, entre otros casos(en los cuales lanza ventanas emergentes de aviso),
     * leemos nuestro archivo en donde tenemos guardados los usuarios y generamos nuestra Tabla Hash de nuevo(ver en clase SistemaDeApuestas
     * y Registros).Y finalmente hacemos visible al Torneo e invisible a la ventana de interfaz
     * @param evt 
     */
    private void jButtonInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioSesionActionPerformed

        String nombreDeUsuario = jTextFieldUsuario.getText();
        String contraseña = new String(jPasswordField.getPassword());

        if (nombreDeUsuario.equals("") || contraseña.equals("")) {
            JOptionPane.showMessageDialog(null, "Ingresa tu nombre de usuario y tu contraseña!");
            return;
        }

        char inicio = sistema.iniciarSesion(nombreDeUsuario, contraseña);

        jPasswordField.setText("");

        if (inicio == 'U') {
            JOptionPane.showMessageDialog(null, "Ese usuario no existe! Intente de nuevo");
            return;
        }
        if (inicio == 'C') {
            JOptionPane.showMessageDialog(null, "Contraseña incorrecta! Intente de nuevo");
            return;
        }

        logicaTorneo = new LogicaTorneo(sistema.getApostador());
        inicioTorneo = new Thread(logicaTorneo);
        contadores = new Thread(torneo);
        inicioTorneo.start();
        contadores.start();
        this.setVisible(false);
        torneo.jLabelSaldoNumero.setText(" " + String.format("%.2f", sistema.getApostador().getSaldo()));
        torneo.setVisible(true);
    }//GEN-LAST:event_jButtonInicioSesionActionPerformed

    /**
     * CLase Interna. Clase que modela la ventana de un Registro de nuevo usuario. Al igual que Interza
     * extiende de jFrame. 
     * @author González Jasso José Eduardo
     * @author Dozal Magnani Diego
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
         * Inicializador. Inicializa todas las componenetes de la ventana Registro.  
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
            getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 190, -1));

            jLabel2.setForeground(new java.awt.Color(254, 254, 254));
            jLabel2.setText("Nombre de usuario");
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 190, 20));

            jPasswordFieldContraseña.setText("");

            getContentPane().add(jPasswordFieldContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 190, -1));

            jPasswordFieldConfirmacion.setText("");
            getContentPane().add(jPasswordFieldConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 190, -1));

            jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
            jLabelFondo.setText("jLabel1");
            getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 277, 472));

            pack();
        }// </editor-fold>                        
            
        /**
         * Acción realizada. Cuando se presiona el botón de volver, se hace invisible el registro
         * y visible la interfaz. 
         * @param evt 
         */
        private void jButtonBackActionPerformed(java.awt.event.ActionEvent evt) {
            this.setVisible(false);
            Interfaz.this.setVisible(true);
        }
        
         /**
         * Acción realizada. Cuando el botón de registrar es presionado. Se hace una lectura de los 4 campos de 
         * texto y después se hace una verificación. La verificación toma en cuenta : 
         * Que ningún campo sea nulo
         * Que las contraseñas coincidan 
         * Que la longitud de la contraseña sea de al menos 8 caracteres
         * Que no contengan espacios los bancos de texto. 
         * Si no ocurre alguno de los casos y el registro es existoso, entonces se mandará un mensaje de que así lo fue. 
         * @param evt 
         */
        private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {
            String nombre = jTextFieldNombre.getText();
            String nombreDeUsuario = jTextField1.getText();
            String password = new String(jPasswordFieldContraseña.getPassword());
            String confirmedPassword = new String(jPasswordFieldConfirmacion.getPassword());

            jTextFieldNombre.setText("");
            jTextField1.setText("");
            jPasswordFieldContraseña.setText("");
            jPasswordFieldConfirmacion.setText("");

            if (!password.equals(confirmedPassword)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden! Intenta de nuevo");
                return;
            }
            if (nombreDeUsuario.length() < 8) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener al menos 8 caracteres");
                return;
            }
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres");
                return;
            }
            for (int i = 0; i < nombreDeUsuario.length(); i++) {
                if (nombreDeUsuario.charAt(i) == ' ') {
                    JOptionPane.showMessageDialog(null, "El nombre de usuario no puede contener espacios");
                    return;
                }
            }

            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) == ' ') {
                    JOptionPane.showMessageDialog(null, "La contraseña no puede contener espacios");
                    return;
                }
            }

            boolean error = sistema.registrar(nombreDeUsuario, nombre, password);

            if (error) {
                JOptionPane.showMessageDialog(null, "Registro exitoso! Ahora puedes iniciar sesión");
            } else {
                JOptionPane.showMessageDialog(null, "Ese nombre de usuario ya está en uso, intenta con otro");
            }

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
     * Clase interna. Clase que modela la interfaz principal de un Torneo, que también extiende 
     * de jFrame. 
     * @author González Jasso José Eduardo
     * @author Dozal Magnani Diego
     */
    public class Torneo extends javax.swing.JFrame implements Runnable {

        /**
         * Creates new form Torneo
         */
        public Torneo() {
            initComponents();
            this.setLocationRelativeTo(null);
            jScrollPane1.setVisible(false);
            jButtonCerrarHisto.setVisible(false);
            jTextAreaHistorial.setEditable(false);
            jTextAreaHistorial.setFocusable(false);
            jTextAreaHistorial.setLineWrap(true);

        }

        /**
         * Inicializador. Este método inicializa las componentes, entre ellos se encuentran los 
         * jFrame Enfrentamientos, los cuales modelan los enfrentamientos entre dos peleadores (ver clase interna Enfrentamientos).
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            jButton1 = new javax.swing.JButton();
            jButton2 = new javax.swing.JButton();
            jButton3 = new javax.swing.JButton();
            jButton4 = new javax.swing.JButton();
            jButton5 = new javax.swing.JButton();
            jButton6 = new javax.swing.JButton();
            jButtonFinal = new javax.swing.JButton();
            jLabelFGanador = new javax.swing.JLabel();
            jButtonCerrarHisto = new javax.swing.JButton();
            jButtonHistorial = new javax.swing.JButton();
            jButtonExit = new javax.swing.JButton();
            jLabelRonda = new javax.swing.JLabel();
            jLabelSigno = new javax.swing.JLabel();
            jLabelDeposito = new javax.swing.JLabel();
            jTextFieldDeposito = new javax.swing.JTextField();
            jButtonAceptarDep = new javax.swing.JButton();
            jLabelContRonda = new javax.swing.JLabel();
            jLabelContTorneo = new javax.swing.JLabel();
            jLabelTimeTorneo = new javax.swing.JLabel();
            jLabelSaldoDisponible = new javax.swing.JLabel();
            jLabelSaldoNumero = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            jTextAreaHistorial = new javax.swing.JTextArea();
            jLabelFondo = new javax.swing.JLabel();
            jLabelFondoTotal = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setUndecorated(true);
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jButton1.setBackground(new java.awt.Color(177, 177, 177));
            jButton1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton1.setText("PELEA 1");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 100, 40));

            jButton2.setBackground(new java.awt.Color(177, 177, 177));
            jButton2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton2.setText("PELEA 2");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, 100, 40));

            jButton3.setBackground(new java.awt.Color(177, 177, 177));
            jButton3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton3.setText("PELEA 3");
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton3ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 370, 100, 40));

            jButton4.setBackground(new java.awt.Color(177, 177, 177));
            jButton4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton4.setText("PELEA 4");
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton4ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 100, 40));

            jButton5.setBackground(new java.awt.Color(177, 177, 177));
            jButton5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton5.setText("PELEA 5");
            jButton5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton5ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 200, 100, 40));

            jButton6.setBackground(new java.awt.Color(177, 177, 177));
            jButton6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButton6.setText("PELEA 6");
            jButton6.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton6ActionPerformed(evt);
                }
            });
            getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, 100, 40));

            jButtonFinal.setBackground(new java.awt.Color(177, 177, 177));
            jButtonFinal.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
            jButtonFinal.setText("FINAL");
            jButtonFinal.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonFinalActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 420, 160, 50));
            getContentPane().add(jLabelFGanador, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 160, 50));

            jButtonCerrarHisto.setText("X");
            jButtonCerrarHisto.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCerrarHistoActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonCerrarHisto, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 40, 20));

            jButtonHistorial.setFont(new java.awt.Font("Te X Gyre Heros", 1, 15)); // NOI18N
            jButtonHistorial.setText("Historial");
            jButtonHistorial.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonHistorialActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 100, -1));

            jButtonExit.setText("Exit");
            jButtonExit.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonExitActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, -1));

            jLabelRonda.setFont(new java.awt.Font("Uroob", 0, 20)); // NOI18N
            jLabelRonda.setForeground(new java.awt.Color(237, 214, 96));
            jLabelRonda.setText("Siguiente ronda en : ");
            getContentPane().add(jLabelRonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 230, 30));

            jLabelSigno.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
            jLabelSigno.setForeground(new java.awt.Color(27, 185, 0));
            jLabelSigno.setText("$");
            getContentPane().add(jLabelSigno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 20, 30));

            jLabelDeposito.setBackground(new java.awt.Color(210, 210, 210));
            jLabelDeposito.setFont(new java.awt.Font("Ubuntu Light", 1, 18)); // NOI18N
            jLabelDeposito.setForeground(new java.awt.Color(255, 255, 51));
            jLabelDeposito.setText("Depositar");
            getContentPane().add(jLabelDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 120, 30));
            getContentPane().add(jTextFieldDeposito, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

            jButtonAceptarDep.setFont(new java.awt.Font("Ubuntu", 0, 11)); // NOI18N
            jButtonAceptarDep.setText("Aceptar");
            jButtonAceptarDep.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonAceptarDepActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonAceptarDep, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 70, 30));

            jLabelContRonda.setFont(new java.awt.Font("UnDotum", 1, 18)); // NOI18N
            jLabelContRonda.setForeground(new java.awt.Color(174, 17, 20));
            jLabelContRonda.setText("00 : 00");
            getContentPane().add(jLabelContRonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, 30));

            jLabelContTorneo.setFont(new java.awt.Font("UnYetgul", 0, 18)); // NOI18N
            jLabelContTorneo.setForeground(new java.awt.Color(174, 17, 20));
            jLabelContTorneo.setText("00 : 00");
            getContentPane().add(jLabelContTorneo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 70, 30));

            jLabelTimeTorneo.setFont(new java.awt.Font("Uroob", 0, 20)); // NOI18N
            jLabelTimeTorneo.setForeground(new java.awt.Color(248, 232, 119));
            jLabelTimeTorneo.setText("Siguiente Torneo en: ");
            getContentPane().add(jLabelTimeTorneo, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 240, 30));

            jLabelSaldoDisponible.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
            jLabelSaldoDisponible.setForeground(new java.awt.Color(255, 255, 0));
            jLabelSaldoDisponible.setText("Saldo disponible:  $");
            getContentPane().add(jLabelSaldoDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

            jLabelSaldoNumero.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
            jLabelSaldoNumero.setForeground(new java.awt.Color(255, 255, 0));
            getContentPane().add(jLabelSaldoNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 90, 20));

            jTextAreaHistorial.setColumns(20);
            jTextAreaHistorial.setRows(5);
            jScrollPane1.setViewportView(jTextAreaHistorial);

            getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 290, 320));

            jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/TorneoColor.png"))); // NOI18N
            jLabelFondo.setText("jLabel1");
            getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 640, 470));

            jLabelFondoTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/VerdeCasino.jpg"))); // NOI18N
            getContentPane().add(jLabelFondoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 500));

            pack();
        }// </editor-fold>                        
        
        /**
         * Acción realizada. Los botones:
         * jButton1ActionPerformed  
         * jButton2ActionPerformed
         * jButton3ActionPerformed
         * jButton4ActionPerformed
         * jButton5ActionPerformed
         * jButton6ActionPerformed
         * jButtonFActionPerformed
         * Tienen la misma funcionalidad : Hacer visible a la ventana Enfrentamiento con la que 
         * están asociados. 
         * @param evt 
         */
        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 1) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea1 = new Enfrentamiento(logicaTorneo.obtenerPelea(0));
            pelea1.setVisible(true);
            pelea1.setAlwaysOnTop(true);
            this.setEnabled(false);
        }
        
        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 2) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea2 = new Enfrentamiento(logicaTorneo.obtenerPelea(1));
            pelea2.setVisible(true);
            pelea2.setAlwaysOnTop(true);
            this.setEnabled(false);
        }
        
        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 3) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea3 = new Enfrentamiento(logicaTorneo.obtenerPelea(2));
            pelea3.setVisible(true);
            pelea3.setAlwaysOnTop(true);
            this.setEnabled(false);
        }
        
        private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 4) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea4 = new Enfrentamiento(logicaTorneo.obtenerPelea(3));
            pelea4.setVisible(true);
            pelea4.setAlwaysOnTop(true);
            this.setEnabled(false);
        }

        private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 5) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea5 = new Enfrentamiento(logicaTorneo.obtenerPelea(4));
            pelea5.setVisible(true);
            pelea5.setAlwaysOnTop(true);
            this.setEnabled(false);
        }

        private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 6) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            pelea6 = new Enfrentamiento(logicaTorneo.obtenerPelea(5));
            pelea6.setVisible(true);
            pelea6.setAlwaysOnTop(true);
            this.setEnabled(false);
        }

        private void jButtonFinalActionPerformed(java.awt.event.ActionEvent evt) {
            if (logicaTorneo.getNumPeleas() < 7) {
                JOptionPane.showMessageDialog(null, "Esta pelea está indefinida! Intenta más tarde");
                return;
            }

            peleaFinal = new Enfrentamiento(logicaTorneo.obtenerPelea(6));
            peleaFinal.setVisible(true);
            peleaFinal.setAlwaysOnTop(true);
            this.setEnabled(false);
        }

        private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {
            this.setVisible(false);
            sistema.guardar();
            inicioTorneo.interrupt();
            contadores.interrupt();
            Interfaz.this.setVisible(true);
        }
        
        /**
         * Acción realizada. Cuando el botón Historial es pulsado, hace visible un TextArea que tiene un Scroll, en donde 
         * se van guardando las acciones que el usuario va realizando durante el transcurso del torneo. 
         * @param evt 
         */
        private void jButtonHistorialActionPerformed(java.awt.event.ActionEvent evt) {
            jTextAreaHistorial.setText(sistema.getApostador().obtenerHistorial());
            jScrollPane1.setVisible(true);
            jButtonCerrarHisto.setVisible(true);
        }
        
        /**
         * Acción realizada. Cierra el TextArea de Historial.
         * @param evt 
         */
        private void jButtonCerrarHistoActionPerformed(java.awt.event.ActionEvent evt) {
            jScrollPane1.setVisible(false);
            jButtonCerrarHisto.setVisible(false);

        }
        
        /**
         * Acción realizada. Cuando el botón de Aceptar ubicado debajo de Depositar se presiona,
         * se lee el contenido de la TextField y se hacen diversas verifiaciones (cada una descrita en el constructor de los JOptionPane)
         * @param evt 
         */
        private void jButtonAceptarDepActionPerformed(java.awt.event.ActionEvent evt) {
            String valor = jTextFieldDeposito.getText();

            if (valor.equals("")) {
                JOptionPane.showMessageDialog(null, "Valor inválido! intente de nuevo");
                return;
            }

            for (int i = 0; i < valor.length(); i++) {
                if ((int) (valor.charAt(i)) < 48 | (int) (valor.charAt(i)) > 57) {
                    JOptionPane.showMessageDialog(null, "Valor inválido! intente de nuevo");
                    jTextFieldDeposito.setText("");
                    return;
                }
            }

            double deposito = Double.parseDouble(valor);

            if (deposito <= 0) {
                JOptionPane.showMessageDialog(null, "Valor inválido! intente de nuevo");
                jTextFieldDeposito.setText("");
                return;
            }

            sistema.depositar(deposito);
            sistema.getApostador().agregaMovimiento('D', deposito, null);
            jLabelSaldoNumero.setText(" " + String.format("%.2f", sistema.getApostador().getSaldo()));
            jTextFieldDeposito.setText("");
        }
        
        /**
         * Implementación. Para el método run. Este método se encarga de modificar los relojes de torneo.
         * los cuales tienen como propósito informar al usuario acerca del tiempo restante para el siguiente 
         * torneo y asímismo, para informarle sobre la siguiente ronda. También, sirven para actualizar algunas 
         * etiquetas como la que muestra el saldo total del usuario. 
         */
        @Override
        public void run() {
            int contRondas = 15;

            while (!Thread.interrupted()) {

                int contTorneoSeg = 45;

                while (contTorneoSeg > 0) {

                    if (contRondas >= 1) {
                        jLabelContRonda.setText("00" + " : " + String.format("%02d", contRondas));
                        jLabelSaldoNumero.setText(" " + String.format("%.2f", sistema.getApostador().getSaldo()));
                        jTextAreaHistorial.setText(sistema.getApostador().obtenerHistorial());
                        contRondas--;
                        
                        if (contRondas == 0) {
                            contRondas = 15;
                        }
                    }

                    jLabelContTorneo.setText("00" + " : " + String.format("%02d", contTorneoSeg));

                    contTorneoSeg--;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {}
                    

                }
            }

        }

        // Variables declaration - do not modify                     
        private javax.swing.JButton jButton1;
        private javax.swing.JButton jButton2;
        private javax.swing.JButton jButton3;
        private javax.swing.JButton jButton4;
        private javax.swing.JButton jButton5;
        private javax.swing.JButton jButton6;
        private javax.swing.JButton jButtonAceptarDep;
        private javax.swing.JButton jButtonCerrarHisto;
        private javax.swing.JButton jButtonExit;
        private javax.swing.JButton jButtonFinal;
        private javax.swing.JButton jButtonHistorial;
        private javax.swing.JLabel jLabelContRonda;
        private javax.swing.JLabel jLabelContTorneo;
        private javax.swing.JLabel jLabelDeposito;
        private javax.swing.JLabel jLabelFGanador;
        private javax.swing.JLabel jLabelFondo;
        private javax.swing.JLabel jLabelFondoTotal;
        private javax.swing.JLabel jLabelRonda;
        private javax.swing.JLabel jLabelSigno;
        private javax.swing.JLabel jLabelTimeTorneo;
        private javax.swing.JLabel jLabelSaldoDisponible;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextArea jTextAreaHistorial;
        private javax.swing.JTextField jTextFieldDeposito;
        private javax.swing.JLabel jLabelSaldoNumero;

        private Enfrentamiento pelea1;
        private Enfrentamiento pelea2;
        private Enfrentamiento pelea3;
        private Enfrentamiento pelea4;
        private Enfrentamiento pelea5;
        private Enfrentamiento pelea6;
        private Enfrentamiento peleaFinal;
    }
    
    /**
     * Clase interna. Clase que modela una ventana de Enfrentamineto extendiendo de jFrame. 
     * Cada enfrentamiento tiene un objeto de tipo Pelea, de la cual obtnemos información y en 
     * base a la Pelea, la ventana Enfrentamiento cambia. 
     */
    public class Enfrentamiento extends javax.swing.JFrame {
        
        /**
         * Constructor. 
         * @param peleaAsociada 
         */
        public Enfrentamiento(Pelea peleaAsociada) {
            this.peleaAsociada = peleaAsociada;
            initComponents();
            this.setLocationRelativeTo(null);

            p1 = peleaAsociada.getP1();
            p2 = peleaAsociada.getP2();
            jLabelFondoGanador.setVisible(false);
            jLabelNombreGanador.setVisible(false);

            if (peleaAsociada.getGanador() != null) {
                jButtonPeleador1.setVisible(false);
                jButtonPeleador2.setVisible(false);
                jLabelPeleador1.setVisible(false);
                jLabelPeleador2.setVisible(false);
                jTextFieldApuestaP1.setVisible(false);
                jTextFieldApuestaP2.setVisible(false);
                jLabel1.setVisible(false);
                jLabel2.setVisible(false);

                jLabelFondoGanador.setVisible(true);
                jLabelNombreGanador.setText(peleaAsociada.getGanador().getNombre());
                jLabelNombreGanador.setVisible(true);
            }
        }

        /**
         * Inicializador. La ventana Enfrentamiento tendrá dos jLabel (entre otros componentes).En éstos 
         * se presentarán la información del los peleadores involucrados en la pelea. Además de que en ésta ventana, 
         * es en donde el usuario apostará por el peleador que guste. 
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
        private void initComponents() {

            jLabelNombreGanador = new javax.swing.JLabel();
            jLabelFondoGanador = new javax.swing.JLabel();
            jButtonPeleador1 = new javax.swing.JButton();
            jButtonPeleador2 = new javax.swing.JButton();
            jLabelPeleador1 = new javax.swing.JLabel();
            jLabelPeleador2 = new javax.swing.JLabel();
            jTextFieldApuestaP1 = new javax.swing.JTextField();
            jTextFieldApuestaP2 = new javax.swing.JTextField();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jButtonCerrar = new javax.swing.JButton();

            jLabelPeleador1.setText(peleaAsociada.getP1().getNombre() + "\n" + "$" + String.format("%.2f", peleaAsociada.getCuotaP1()));
            jLabelPeleador2.setText(peleaAsociada.getP2().getNombre() + "\n" + "$" + String.format("%.2f", peleaAsociada.getCuotaP2()));

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            setUndecorated(true);
            getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

            jLabelNombreGanador.setFont(new java.awt.Font("TeXGyreCursor", 3, 23)); // NOI18N
            jLabelNombreGanador.setForeground(new java.awt.Color(155, 107, 10));
            jLabelNombreGanador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            getContentPane().add(jLabelNombreGanador, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 160, 40));

            jLabelFondoGanador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabelFondoGanador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/win.png"))); // NOI18N
            getContentPane().add(jLabelFondoGanador, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 300, 130));

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

            getContentPane().add(jLabelPeleador1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 110, 70));

            getContentPane().add(jLabelPeleador2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 110, 70));

            getContentPane().add(jTextFieldApuestaP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, -1));
            getContentPane().add(jTextFieldApuestaP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 90, -1));

            jLabel1.setFont(new java.awt.Font("STIXGeneral", 1, 18)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(0, 102, 0));
            jLabel1.setText("$");
            getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 20, 30));

            jLabel2.setFont(new java.awt.Font("STIXGeneral", 1, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(0, 102, 0));
            jLabel2.setText("$");
            getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 20, 30));

            jButtonCerrar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
            jButtonCerrar.setText("X");
            jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButtonCerrarActionPerformed(evt);
                }
            });
            getContentPane().add(jButtonCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(262, 0, 30, 30));

            pack();
        }// </editor-fold>                        
        
        /**
         * Acción realizada. Cada Enfrentamiento, tiene un botón de apostar para cada peleador. 
         * Cuando el botón es pulsado, la información que se haya escrito en el TextField ubicado debajo 
         * del botón será guardada en una cadena. El contenido de la cadena será verificado de distintas formas,
         * como revisar que el usuario no haya escrito letras o que el monto a apostar sea mayor que su saldo, entre 
         * otros. Después de que se apuesta, utilizando el método de apostar de SistemaDeApuesta, el monto se apuesta
         * para el peleador Elegido y el saldo del usuario disminuye. Finalmente se guardan los cambios. 
         * Lo mismo ocurre con el botón del Peleador 2. 
         * @param evt 
         */
        private void jButtonPeleador1ActionPerformed(java.awt.event.ActionEvent evt) {

            if (peleaAsociada.getYaExpiro()) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "La pelea ha expirado x.x");
                this.setVisible(false);
                torneo.setEnabled(true);
                return;
            }

            if (peleaAsociada.getElegido() != null) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Ya haz apostado en esta pelea!");
                this.setVisible(false);
                torneo.setEnabled(true);
                return;
            }

            String apuestaP1 = jTextFieldApuestaP1.getText();

            for (int i = 0; i < apuestaP1.length(); i++) {
                if ((int) (apuestaP1.charAt(i)) < 48 | (int) (apuestaP1.charAt(i)) > 57) {
                    JOptionPane.showMessageDialog(null, "Valor inválido! intente de nuevo");
                    jTextFieldApuestaP1.setText("");
                    return;
                }
            }

            if (apuestaP1.equals("                  ") | apuestaP1.equals("")) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Monto inválido! intenta de nuevo");
                this.setAlwaysOnTop(true);
                return;
            }

            if (peleaAsociada.getCuotaP1() > sistema.getApostador().getSaldo()) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "No tienes el suficiente saldo para apostar por este peleador!");
                this.setAlwaysOnTop(true);
                return;
            }

            double montoApostado = Double.parseDouble(apuestaP1);

            if (montoApostado == 0.0) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Monto inválido! intenta de nuevo");
                this.setAlwaysOnTop(true);
                return;
            }

            if (montoApostado > sistema.getApostador().getSaldo()) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "El monto ingresado supera el saldo! intenta de nuevo");
                this.setAlwaysOnTop(true);
                return;
            }

            sistema.apostar(peleaAsociada, peleaAsociada.getP1(), montoApostado);
            torneo.jLabelSaldoNumero.setText(" " + String.format("%.2f", sistema.getApostador().getSaldo()));
            sistema.guardar();

            this.setVisible(false);
            torneo.setEnabled(true);
        }

        private void jButtonPeleador2ActionPerformed(java.awt.event.ActionEvent evt) {

            if (peleaAsociada.getYaExpiro()) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "La pelea ha expirado x.x");
                this.setVisible(false);
                torneo.setEnabled(true);
                return;
            }

            if (peleaAsociada.getElegido() != null) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Ya haz apostado en esta pelea!");
                this.setVisible(false);
                torneo.setEnabled(true);
                return;
            }

            String apuestaP2 = jTextFieldApuestaP2.getText();

            if (apuestaP2.equals("                  ") | apuestaP2.equals("")) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Monto inválido! intenta de nuevo");
                this.setAlwaysOnTop(true);
                return;
            }

            for (int i = 0; i < apuestaP2.length(); i++) {
                if ((int) (apuestaP2.charAt(i)) < 48 | (int) (apuestaP2.charAt(i)) > 57) {
                    this.setAlwaysOnTop(false);
                    JOptionPane.showMessageDialog(null, "Valor inválido! intente de nuevo");
                    this.setAlwaysOnTop(true);
                    jTextFieldApuestaP2.setText("");
                    return;
                }
            }

            if (peleaAsociada.getCuotaP2() > sistema.getApostador().getSaldo()) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "No tienes el suficiente saldo para apostar por este peleador!");
                this.setAlwaysOnTop(true);
                return;
            }

            double montoApostado = Double.parseDouble(apuestaP2);

            if (montoApostado == 0.0) {
                this.setAlwaysOnTop(false);
                JOptionPane.showMessageDialog(null, "Monto inválido! intenta de nuevo");
                this.setAlwaysOnTop(true);
                return;
            }

            sistema.apostar(peleaAsociada, peleaAsociada.getP2(), montoApostado);
            torneo.jLabelSaldoNumero.setText(" " + String.format("%.2f", sistema.getApostador().getSaldo()));
            sistema.guardar();

            this.setVisible(false);
            torneo.setEnabled(true);
        }

        private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
            this.setVisible(false);
            torneo.setEnabled(true);
        }

        // Variables declaration - do not modify                     
        private javax.swing.JButton jButtonCerrar;
        private javax.swing.JButton jButtonPeleador1;
        private javax.swing.JButton jButtonPeleador2;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabelFondoGanador;
        private javax.swing.JLabel jLabelNombreGanador;
        private javax.swing.JLabel jLabelPeleador1;
        private javax.swing.JLabel jLabelPeleador2;
        private javax.swing.JTextField jTextFieldApuestaP1;
        private javax.swing.JTextField jTextFieldApuestaP2;
        private Pelea peleaAsociada;
        private Peleador p1;
        private Peleador p2;
        // End of variables declaration
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
