package sistema;

/**
 * Clase que modela un objeto de tipo Pelador.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Peleador {

    //Atributos
    private String nombre;
    private int habilidad;

    /**
     * Constructor de Peleador a partir de una String "nombre" y un entero "habilidad".
     *
     * @param nombre
     * @param habilidad
     */
    public Peleador(String nombre,int habilidad){
        this.nombre=nombre;
        this.habilidad=habilidad;
    }

    /**
     * Método getter de habilidad.
     *
     * @return habilidad
     */
    public int getHabilidad() {
        return habilidad;
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
     * Método para obtener la probabilidad del peleador a paritr de su oponente.
     *
     * @return double
     */
    public double getProbabilidad(Peleador oponente) {
        return habilidad / (double)(habilidad + oponente.getHabilidad());
    }

}
