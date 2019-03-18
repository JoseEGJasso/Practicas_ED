import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Implementación de Pilas.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Pila<T> implements Iterable<T>{

    private Lista<T> pila;

    /**
     * Constructor vacío de pilas
     */
    public Pila(){

        pila = new Lista<>();

    }

    /**
     * Constructor de Pila a partir de una lista
     *
     * @param l;
     */
    public Pila(Lista<T> l){

        pila = new Lista<>();

        for(int i=0; i<l.getLongitud();i++)
            pila.agregaInicio(l.get(i));

    }

    /**
     * Constructor de Pila a partir de un arreglo;
     *
     * @param arreglo;
     */
    public Pila(T[] arreglo){
        pila = new Lista<>();

        for (int i=arreglo.length-1;i>=0;i--)
            pila.agregaFinal(arreglo[i]);

    }

    /**
     * Método esVacia que retorna boolean= true si la pila no tiene elementos,
     * en caso contrario retorna false, es decir, tiene elementos.
     *
     * @return true/false;
     */
    public boolean esVacia(){
        try{
            pila.getPrimero();
        }catch (NullPointerException e) {
            return true;
        }
        return false;
    }

    /**
     * Método que regresa el primer elemento de la pila sin afectar la longitud
     * de la pila.
     * @return pila.getPrimero();
     */
    public T mira() {
        if(!this.esVacia())
            return pila.getPrimero();
        throw new EmptyStackException();

    }

    /**
     * Método que regresa el primer elemento de una  y lo elimina de la misma.
     * Muy parecido al método pop.
     *
     * @return pila.eliminaPrimero();
     */
    public T saca(){
        if(!this.esVacia())
            return pila.eliminaPrimero();
        throw new EmptyStackException();
    }

    /**
     * Método que agrega un elemento a la Pila, respetando el orden de FILO.
     *
     * @param t;
     */
    public void mete(T t){
        if(t!=null)
            pila.agregaInicio(t);
    }

    /**
     * Implementacion del método toString, regresa los elementos de la Pila como
     * cadena, es una llamada al métdo toString de Lista.java
     *
     * @return elementos;
     */
    @Override
    public String toString(){
        String elementos="";

        for (T elemento: pila)
            elementos+=elemento+"\n";
        return elementos;
    }

    /**
     * Implementacion del método equals. Recibe un Object , luego si pertenece a la
     * intancia de Pila, realiza un cast a Pila. Después por medio de iteradores, se
     * comparan los elementos de cada pila , si todos coinciden, regresa true y en caso
     * contrario regresa false;
     *
     * @param o;
     * @return estado;
     */
    @Override
    public boolean equals(Object o){

      boolean estado=false;

       if(o instanceof Pila){
           Pila comparado = (Pila)o;
           Iterator iterObjeto = comparado.iterator();
           Iterator iterComparado = this.iterator();
           Object actual;
           Object actual1;

          while(iterObjeto.hasNext() && iterComparado.hasNext()){
            actual=iterObjeto.next();
            actual1=iterComparado.next();

            if(actual.equals(actual1)){
               estado=true;
            }else{
               return false;
           }
          }
          if(iterObjeto.hasNext())
            return false;
          if(iterComparado.hasNext())
            return false;
        }
      return estado;
    }

    /**
     * Implementacion del iterador
     *
     * @return iterator;
     */
    @Override
    public Iterator<T> iterator(){
        return pila.iterator();
    }
}
