package sistema;

import java.io.Serializable;


/**
 * 
 * 
 * 
 */
public class Usuario implements Serializable{
   
    private final String nombre;
    private final String nombreDeUsuario;
    private final String contraseña;
    private double saldo;
    private String historial;
    private static final long serialVersionUID=1L;

    public Usuario(String nombre,String nombreDeUsuario,String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
        this.nombreDeUsuario=nombreDeUsuario;
        saldo=0;
        historial="BIENVENIDO A TU HISTORIAL,\n"+nombre.toUpperCase()+" :\n";
    }

    public void setSaldo(double nuevoSaldo){
        saldo=nuevoSaldo;
        
        if(saldo<0.0)
            saldo=0.0;
         
    }
    
    public double getSaldo(){
        return saldo;
    }

    public void agregaMovimiento(char movimiento,double montoAfectado,Pelea pelea){
        if(movimiento=='D'){
            historial+="-------------------------------------------------------------------\nSe depositaron $"+montoAfectado+" a la cuenta\n";
        } else if(movimiento=='G'){
            historial+="-------------------------------------------------------------------\n"+pelea.getP1().getNombre()+" vs "+pelea.getP2().getNombre()+".\nGanó "+pelea.getGanador().getNombre()+"\nApostaste por "+pelea.getElegido().getNombre()+" obtuviste $"+String.format("%.1f", montoAfectado)+"\n";
        } else{
            historial+="-------------------------------------------------------------------\n"+pelea.getP1().getNombre()+" vs "+pelea.getP2().getNombre()+".\nGanó "+pelea.getGanador().getNombre()+"\nApostaste por "+pelea.getElegido().getNombre()+" perdiste $"+String.format("%.1f", montoAfectado)+"\n";
        }
    }

    public String obtenerHistorial(){
        return historial;
    }

    public String getContraseña(){
        return contraseña;
    }

    public String getNombre(){
        return nombre;
    }
    
    public String getNombreUsuario(){
        return nombreDeUsuario;
    }

}
