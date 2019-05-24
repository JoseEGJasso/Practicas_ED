package proyecto3;


/**
 * 
 * 
 * 
 */
public class Usuario{
   
    private final String nombre;
    private final String contraseña;
    private double saldo;
    private String historial;

    public Usuario(String nombre,String contraseña){
        this.nombre=nombre;
        this.contraseña=contraseña;
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

}
