package sistema;

/**
 * Clase que modela un objeto de tipo Pelea, en la cual dos opbjetos de tipo Peleador se "enfrentan"
 * tomando en cuenta su habilidad.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Pelea{

    //Atributos
    private Peleador peleador1;
    private Peleador peleador2;
    private Peleador elegido;
    private Peleador ganador;
    private double apuesta;

    /**
     * Constructor de Pelea a partir de dos objetos peleadores.
     * Inicializa el elegido y ganador como nullos y a los peleadores como los
     * peleadores que recibió.
     *
     * @param peleador1
     * @param peleador2
     */
    public Pelea(Peleador peleador1,Peleador peleador2){
        this.peleador1=peleador1;
        this.peleador2=peleador2;
        elegido=null;
        ganador=null;
    }

    /**
     * Método vacío determinarGanador. Utilizando la clase Random  y obteniendo la getProbabilidad de cada
     * peleador (con operaciones ya acordadas en el proyecto), se modifica el ganador de la pelea comparando
     * la probabilidad de cada peleador entre 0 y 99.
     */
    public void determinarGanador(){

        java.util.Random azar=new java.util.Random();

        int probP1=(int)peleador1.getProbabilidad(peleador2)*100;
        int probP2=(int)peleador1.getProbabilidad(peleador2)*100;
        int numRandom=azar.nextInt(99);

        if(numRandom<=probP1)
            ganador=peleador1;

        else
            ganador=peleador2;
    }

    /**
     * Método vacío que recibe el monto de la apuesta  del usuario para la pelea en curso y lo asigna.
     *
     * @param apuesta
     */
    public void asignarApuesta(double apuesta){
        this.apuesta=apuesta;
    }

    /**
     * Método getter para la apuesta.
     *
     * @return apuesta
     */
    public double getApuesta(){
        return apuesta;
    }

    /**
     * Método getter para el ganador de la pelea.
     *
     * @return ganador
     */
    public Peleador getGanador(){
        return ganador;
    }

    /**
     * Método getter para el peleador elegido por el Usuario.
     *
     * @return elegido
     */
    public Peleador getElegido(){
        return elegido;
    }

    /**
     * Método getter para el peleador perdedor de la Pelea.
     *
     * @return perdedor
     */
    public Peleador getPerdedor(){
        if(ganador==peleador1)
            return peleador2;
        return peleador1;
    }

    /**
     * Método setter para modificar el peleador elegido
     *
     * @param peleador
     **/
    public void asignarElegido(Peleador peleador){
        elegido=peleador;
    }

    /**
     * Método para obtener el mínimo mónto a apostar para el peleador1.
     *
     * @return double
     */
    public double getCuotaP1(){
        return (double)(1/peleador1.getProbabilidad(peleador2));
    }

    /**
     * Método getter para regresar el Peleador 1 de la pelea en curso.
     *
     * @return peleador1
     */
    public Peleador getP1(){
        return peleador1;
    }

    /**
     * Método getter para regresar el Peleador 2 de la pelea en curso.
     *
     * @return peleador2
     */
    public Peleador getP2(){
        return peleador2;
    }

    /**
     * Método para obtener el mínimo mónto a apostar para el peleador2.
     *
     * @return double
     */
    public double getCuotaP2(){
        return (double)(1/peleador2.getProbabilidad(peleador1));
    }
}
