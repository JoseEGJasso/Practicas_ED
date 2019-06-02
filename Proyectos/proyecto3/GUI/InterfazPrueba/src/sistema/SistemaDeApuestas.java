package sistema;


/**
 * Clase que modela la lógica de SistemaDeApuestas.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class SistemaDeApuestas{

    //Atributos
    private Registros registros;
    private Usuario apostador;

    /**
     * Constructor que únicamente inicializa registros
     *
     */
    public SistemaDeApuestas(){
        registros=new Registros();
    }

    /**
     * Método que sirve de referencia en la Interfaz Gráfica para que, dependiendo de ¡l caso, se pueda
     * desplegar una ventana de aviso. En la Interfaz Gráfica, el mensaje depende del caractér que este método
     * devuelva.
     *
     * @param nombreDeUsuario,contraseña
     * @return char
     */
    public char iniciarSesion(String nombreDeUsuario,String contraseña){

        if(!registros.buscar(nombreDeUsuario))
            return 'U';

        if(!registros.obtenerRegistro(nombreDeUsuario).getContraseña().equals(contraseña))
            return 'C';

        apostador=registros.obtenerRegistro(nombreDeUsuario);

        return 'T';
    }

    /**
     * Método que recibe tres cadenas : nombreDeUsuario, nombre y contraseña
     * para registras un nuevo usuario en registro. El método sirve de referencia para la Interfaz Gráfica, ya que
     * regresa un boolean, true-si el registro se concluyó con éxito y false- en otro caso.
     *
     * @param nombreDeUsuario,nombre,contraseña
     * @return boolena
     *
     */
    public boolean registrar(String nombreDeUsuario,String nombre,String contraseña){

        if(registros.buscar(nombreDeUsuario))
            return false;

        registros.nuevoRegistro(nombreDeUsuario, nombre, contraseña);

        return true;
    }

     /**
      * Método auxiliar para poder llamar al método actualizarRegistros de la clase Registros.
      * Se aplica al atributo registro de la clase.
      *
      */
    public void guardar(){
        registros.actualizarRegistros();
    }

    /**
     * Método vacío que recibe una Pelea, un Peleador y un montoApostar.
     * Este método es crucial para conectar al Usuario con el peleador por el que apostó.
     * Primero , se asigna el Elegido en la partida que se recibió, al igual que la apuesta.
     * Depués se sustrae el monto de la cuenta del usuario y se apuesta(utilizando los métodos
     * de setSaldo de usuario y getCuotaP1/getCuotaP2 )
     *
     * @param partida
     * @param peleador
     * @param  montoApostar
     */
    public void apostar(Pelea partida,Peleador peleador,double montoApostar){
        partida.asignarElegido(peleador);
        partida.asignarApuesta(montoApostar);

        if(partida.getElegido()==partida.getP1())
            apostador.setSaldo(apostador.getSaldo()-(montoApostar+partida.getCuotaP1()));
        else
            apostador.setSaldo(apostador.getSaldo()-(montoApostar+partida.getCuotaP2()));

    }

    /**
     * Método para poder depositar (cualquier cantidad) al saldo del usuario, únicamente
     * recibe un double y utiliza el método del Usuario setSaldo para incrementar su saldo.
     *
     * @param deposito
     */
    public void depositar(double deposito){
        apostador.setSaldo(apostador.getSaldo()+deposito);
    }

    /**
     * Método getter para obtener al Usuario apostador .
     *
     * @return apostador
     */
    public Usuario getApostador(){
        return apostador;
    }
}
