package sistema;

//OJO FALTA LA IMPLEMENTACION DE AGREGAR MOVIMIENTOS AL HISTORIAL
/**
 *
 *
 *
 */

public class SistemaDeApuestas{

    private Registros registros;
    private Usuario apostador;

    public SistemaDeApuestas(){
        registros=new Registros();
    }

    public char iniciarSesion(String nombreDeUsuario,String contraseña){

        if(!registros.buscar(nombreDeUsuario))
            return 'U';
        
        if(!registros.obtenerRegistro(nombreDeUsuario).getContraseña().equals(contraseña))
            return 'C';

        apostador=registros.obtenerRegistro(nombreDeUsuario);

        return 'T';
    }

    public boolean registrar(String nombreDeUsuario,String nombre,String contraseña){

        if(registros.buscar(nombreDeUsuario))
            return false;

        registros.nuevoRegistro(nombreDeUsuario, nombre, contraseña);

        return true;
    }

    public void guardar(){
        registros.actualizarRegistros();
    }

    public void apostar(Pelea partida,Peleador peleador,double montoApostar){
        partida.asignarElegido(peleador);
        partida.asignarApuesta(montoApostar);

        if(partida.getElegido()==partida.getP1())
            apostador.setSaldo(apostador.getSaldo()-(montoApostar+partida.getCuotaP1()));
        else
            apostador.setSaldo(apostador.getSaldo()-(montoApostar+partida.getCuotaP2()));
        
    }
    
    public void depositar(double deposito){
        apostador.setSaldo(apostador.getSaldo()+deposito);
    }
    
    public Usuario getApostador(){
        return apostador;
    }
}
