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
        historial="BIENVENIDO A TU HISTORIAL, "+nombre.toUpperCase()+" :\n";
    }

    public boolean aumentaSaldo(double saldo){
        if(saldo<=0.0)
            return false;
        this.saldo+=saldo;
        return true;
    }

    public void disminuyeSaldo(double monto){
        if(saldo<monto)
            saldo=0.0;
        saldo-=monto;
    }

    public void agregaMovimiento(String movimiento){
        
    }

    public String obtenerHistorial(){
        return historial;
    }

    public double obtenerSaldo(){
        return saldo;
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
