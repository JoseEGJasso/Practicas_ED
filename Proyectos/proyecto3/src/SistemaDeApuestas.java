package proyecto3;

/**
 *
 *
 *
 */

public class SistemaDeApuestas{

    private Registros registros;
    private int apuesta;
    private Usuario apostador;

    public SistemaDeApuestas(){
        registros=new Registros();
        apuesta=0;
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

    



     

}
