package proyecto2;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Implementación de Colas.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Cola<T> implements Iterable<T>{


    private Lista<T> cola;

    /**
     * Constructor de Cola vacío
     *
     */
    public Cola(){

        cola=new Lista<>();
    }

    /**
     * Constructor de Cola a partir de una lista
     *
     * @param l;
     */
    public Cola(Lista<T> l){
        cola=new Lista<>();

        for(T elemento:l)
            cola.agregaFinal(elemento);
    }

    /**
     * Constructor de Cola a partir de un arreglo
     *
     * @param arreglo;
     */
    public Cola(T[] arreglo){

        cola=new Lista<>(arreglo);
    }

    /**
     * Método esVacia que retorna boolean= true si la cola no tiene elementos,
     * en caso contrario retorna false, es decir, tiene elementos.
     *
     * @return true/false;
     */
    public boolean esVacia(){
        try{
            cola.getPrimero();
        }catch (NoSuchElementException e) {
            return true;
        }
        return false;
    }

    /**
     * Método que regresa el ultimo elemento de la cola sin afectar la longitud
     * de la cola.
     * @return cola.getPrimero();
     */
    public T mira(){
        if(!this.esVacia())
            return cola.getUltimo();
        throw new NoSuchElementException();
    }

    /**
     * Método que regresa el ultimo elemento de una cola y lo elimina de la misma.
     * Muy parecido al método pop.
     *
     * @return cola.eliminaPrimero();
     */
    public T saca(){
        if(!this.esVacia())
            return cola.eliminaUltimo();
        throw new NoSuchElementException();
    }

    /**
     * Método que agrega un elemento a la cola, respetando el orden de FILO.
     *
     * @param t;
     */
    public void mete(T t){
        if(t!=null)
            cola.agregaInicio(t);
    }

    /**
     * Implementacion del método toString, regresa los elementos de la cola como
     * cadena, es una llamada al métdo toString de Lista.java
     *
     * @return cola.String;
     */
    @Override
    public String toString(){
        return cola.toString();
    }

    /**
     * Implementacion del método equals. Recibe un Object , luego si pertenece a la
     * intancia de Cola, realiza un cast a Cola. Después por medio de iteradores, se
     * comparan los elementos de cada cola , si todos coinciden, regresa true y en caso
     * contrario regresa false;
     *
     * @param o;
     * @param estado;
     */
    @Override
    public boolean equals(Object o){

      if(o==null)
        return false;

      boolean estado=false;

      if(o instanceof Cola){

            Cola comparado = (Cola)o;

            if(this.esVacia() || comparado.esVacia())
                return false;

            Iterator iterObjeto = comparado.iterator();
            Iterator iterComparado = this.iterator();

               while(iterObjeto.hasNext() && iterComparado.hasNext()){
                if(iterObjeto.next().equals(iterComparado.next())){
                    estado=true;
                }else{
                    estado=false;
                }
               }
               if(iterObjeto.hasNext() || iterComparado.hasNext())
                estado=false;
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
        return cola.iterator();
    }
}
