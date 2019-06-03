package sistema;

import java.io.Serializable;


/**
 * Clase que modela un Usuario e implementa Serializable.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Usuario implements Serializable{

    //Atributos
    private final String nombre;
    private final String nombreDeUsuario;
    private final String contraseña;
    private double saldo;
    private String historial;
    private static final long serialVersionUID=1L;

    /**
     * Constructor a partir de las cadenas : nombre, nombreDeUsuario y contraseña.
     * Asigna las anteriores tres Strings y después inicializa el saldo en 0 y
     * el historial.
     *
     * @param nombre,nombreDeUsuario,contraseña
     */
    public Usuario(String nombre,String nombreDeUsuario,String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
        this.nombreDeUsuario=nombreDeUsuario;
        saldo=0;
        historial="BIENVENIDO A TU HISTORIAL,\n"+nombre.toUpperCase()+" :\n";
    }

    /**
     * Método setter para el saldo.
     *
     * @@param nuevoSaldo
     */
    public void setSaldo(double nuevoSaldo){
        saldo=nuevoSaldo;

        if(saldo<0.0)
            saldo=0.0;

    }

    /**
     * Método getter para el saldo.
     *
     * @return saldo
     */
    public double getSaldo(){
        return saldo;
    }

    /**
     * Método vació que recibe el movimiento (un char), un montoAfectado y la Pelea de la cual proviene o en la que apostó,
     * ganó o perdió. Dependiendo del caracter, el mensaje que se agregará en el historia cambiará. El historial también se
     * muestra en la Interfaz Gráfica
     *
     * @param movimiento
     * @param montoAfectado
     * @param pelea
     */
    public void agregaMovimiento(char movimiento,double montoAfectado,Pelea pelea){
        if(movimiento=='D'){
            historial+="----------------------------------------------------------------\nSe depositaron $"+montoAfectado+" a la cuenta\n";
        } else if(movimiento=='G'){
            historial+="----------------------------------------------------------------\n"+pelea.getP1().getNombre()+" vs "+pelea.getP2().getNombre()+".\nGanó "+pelea.getGanador().getNombre()+"\nApostaste por "+pelea.getElegido().getNombre()+" obtuviste $"+String.format("%.1f", montoAfectado)+"\n";
        } else if(movimiento=='P'){
            historial+="----------------------------------------------------------------\n"+pelea.getP1().getNombre()+" vs "+pelea.getP2().getNombre()+".\nGanó "+pelea.getGanador().getNombre()+"\nApostaste por "+pelea.getElegido().getNombre()+" perdiste $"+String.format("%.1f", montoAfectado)+"\n";
        }
    }

    /**
     * Método getter para el historial.
     *
     * @return historia
     */
    public String obtenerHistorial(){
        return historial;
    }

    /**
     * Método getter para la contraseña.
     *
     * @return contraseña
     */
    public String getContraseña(){
        return contraseña;
    }

    /**
     * Método getter para el nombre.
     *
     * @return nombre
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método getter para nombreDeUsuario
     *
     * @return nombreDeUsuario
     */
    public String getNombreUsuario(){
        return nombreDeUsuario;
    }

}
