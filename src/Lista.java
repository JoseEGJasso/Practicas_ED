import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implementación de listas ligadas.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
 public class Lista<T> implements Iterable<T>{

   /**
    * Clase interna para representar los nodos de nuestras listas.
    * @author González Jasso José Eduardo
    * @author Dozal Magnani Diego
    *
    */
    private class Nodo{

        public T elemento;
        public Nodo siguiente;
        public Nodo anterior;


       /**
        * Constructor de Nodo.
        * @param elemento
        *
        */
        public Nodo(T elemento){
            siguiente=null;
            anterior=null;
            this.elemento=elemento;
        }
    }


   /**
    * Clase Iterador que implementa la interfaz Iterator.
    * @author González Jasso José Eduardo
    * @author Dozal Magnani Diego
    *
    */
    private class Iterador implements Iterator<T>{

        private Nodo actual;

       /**
        * Implementacion del método hasNext.
        * @return boolean
        */
        @Override
        public boolean hasNext(){
            if(actual==null){
                if(cabeza!=null)
                    return true;
                return false;
            }
            return actual.siguiente!=null;
        }
       /**
        * Implementacion del método next.
        * @return elemento
        *
        */
        @Override
        public T next() throws NoSuchElementException{
            if(actual!=null){
                actual=actual.siguiente;
                if(actual==null)
                    throw new NoSuchElementException();
                return actual.elemento;
            }
            if(cabeza!=null){
                actual=cabeza;
                return cabeza.elemento;
            }
            throw new NoSuchElementException();
        }
    }


    private Nodo cabeza;
    private Nodo ultimo;
    private int longitud;

   /**
    * Constructor de una lista vacía.
    *
    */
    public Lista(){
       longitud=0;
    }

   /**
    * Constructor de una lista que recibe un arreglo como parámetro.
    * @param arreglo
    *
    */
    public Lista(T[] arreglo){

        if(arreglo.length > 0){
            for(int i=0; i < arreglo.length; i++)
                this.agregaFinal(arreglo[i]);
            longitud=arreglo.length;
        }

    }
   /**
    * Obtener primer elemento de la lista.
    * @return cabeza.elemento
    */
    public T getPrimero(){
        return cabeza.elemento;
    }

   /**
    * Obtener utlimo elemento de la lista.
    * @return ultimo.elemento
    */
    public T getUltimo(){
        return ultimo.elemento;
    }

   /**
    * Método que elimina el primer nodo de una lista
    * y regresa el elemento del nodo eliminado.
    * @return eliminado
    *
    */
    public T eliminaPrimero(){
        T eliminado=null;

        if(longitud!=0){
            eliminado=cabeza.elemento;
            cabeza=cabeza.siguiente;

            if(cabeza!=null)
                cabeza.anterior=null;
            longitud--;
        }
        return eliminado;
    }
   /**
    * Método que elimina el último nodo de una lista y
    * regresa el elemento eliminado.
    * @return eliminado
    *
    */
    public T eliminaUltimo(){
        T eliminado=null;

        if(longitud!=0){
            if(longitud==1){
                cabeza=ultimo=null;
                return eliminado;
            }
            ultimo=ultimo.anterior;
            ultimo.siguiente=null;
            longitud--;
        }
        return eliminado;
    }

   /**
    * Método que recibe un elemento crea un nuevo nodo y lo agrega al inicio
    * de la lista.
    * @param t
    */
    public void agregaInicio(T t){
        if(t!=null){
            Nodo nuevaCabeza=new Nodo(t);
            if(longitud==0)
                cabeza=ultimo=nuevaCabeza;
            else{
                nuevaCabeza.siguiente=cabeza;
                cabeza.anterior=nuevaCabeza;
                cabeza=nuevaCabeza;
            }
        }
        longitud++;
    }

   /**
    * Método que recibe un elemento y crea un nodo para después
    * agregar dicho nodo al final de la lista.
    * @param t
    */
    public void agregaFinal(T t){
        if(t!=null){
            Nodo nuevoRabo=new Nodo(t);

            if(ultimo!=null){
                ultimo.siguiente=nuevoRabo;
                nuevoRabo.anterior=ultimo;
                ultimo=nuevoRabo;
            } else {
                cabeza=nuevoRabo;
                ultimo=nuevoRabo;
            }
            longitud++;
        }
    }

   /**
    * Método que recibe un elemento y busca en la lista
    * por medio de un iterador si está o no el elemento
    * y devuelve un boolean.
    * @param t
    * @return boolean
    */
    public boolean contiene(T t){
        Iterator<T> iterador=this.iterator();

        for(T elemento:this){
            if(t.equals(elemento))
                return true;
        }
        return false;

    }

   /**
    * Método que devuelve la longitud de una lista.
    * @return longitud
    *
    */
    public int getLongitud(){
        return longitud;
    }

   /**
    * Método que recibe un elemento y busca en la lista
    * dicho elemento, si está, lo elimina.
    * @param t
    * @return boolean
    */
    public boolean elimina(T t){

        if(t!=null){
            Nodo actual=cabeza;
            int contador=0;

            if(actual.elemento.equals(t)){
                this.eliminaPrimero();
                return true;
            }

            while(contador<longitud){
                if(actual.elemento.equals(t)){
                    actual.anterior.siguiente=actual.siguiente;
                    actual.siguiente=actual.anterior;
                    longitud--;
                    return true;
                }
                actual=actual.siguiente;
                contador++;
            }
        }

        return false;
    }
   /**
    * Método que vacía la lista y la deja como una lista vacía.
    *
    */
    public void limpia(){
        cabeza=null;
    }

   /**
    * Método que recibe un índice i y regresea el i-ésimo
    * elemnto de la lista. Si el índice es mayor a la longitud
    * de la lista o menor a 0, lanza una excepción. Asimismo
    * @param indx
    * @return elemnto
    */
    public T get(int indx) throws IndexOutOfBoundsException{
        if(indx<0 || indx>longitud)
            throw new IndexOutOfBoundsException();

        int contador=0;

        for(T elemento:this){
            if(contador==indx)
                return elemento;
            contador++;
        }
        return null;
    }

   /**
    * Método que recibe un índice y un elemento y, después de verificar el índice
    * del elemnto, crea un nodo con el elemento adentro y lo inserta el la lista
    * realizando los cambio de referencias necesarios.
    * @param indx
    * @param t
    *
    */
    public void inserta(int indx, T t) throws IndexOutOfBoundsException{
        if(indx<0 || indx>longitud)
            throw new IndexOutOfBoundsException();

        Nodo nuevoElemento=new Nodo(t);
        Nodo posicion=cabeza;

        if(longitud!=0){
            if(indx==0){
                this.agregaInicio(t);
                return;
            }

            if(indx==longitud){
                this.agregaFinal(t);
                return;
            }

            int contador=1;

            while(contador<indx){
                posicion=posicion.siguiente;
                contador++;
            }

            nuevoElemento.siguiente=posicion.siguiente;
            nuevoElemento.anterior=posicion;
            posicion.siguiente=nuevoElemento;

        }else{

            cabeza=ultimo=nuevoElemento;
        }

        longitud++;
    }

  /**
   * Método que devuelve un arreglo de Object con los elemntos de nuestra
   * lista sin haber alterado el orden de éstos.
   * @return listaEnArreglo
   *
   */
    public Object[] toArray(){
        Object[] listaEnArreglo = new Object[this.getLongitud()];

        int contador=0;

        for(T elemento: this){
            listaEnArreglo[contador] = elemento;
            contador ++;
        }

        return listaEnArreglo;
   }

   /**
    * Método que devuelve una lista con los elemtos de la anterior lista
    * pero en inverso.
    * @return reversaLista
    */
    public Lista<T> reversa(){

     Lista<T> reversaLista=new Lista<T>();

          for(T elemento:this)
              reversaLista.agregaInicio(elemento);

    return reversaLista;
    }

   /**
    * Método que devuelve una copia de la lista.
    * @return listaCopia
    *
    */
    public Lista<T> copia(){

        Lista<T> listaCopia = new Lista<>();

        for(T elemento:this)
            listaCopia.agregaFinal(elemento);

        return listaCopia;
    }

   /**
    * Implementacion del método toString a nuestra lista.
    * @return lista
    *
    */
    @Override
    public String toString(){

        String lista="";

        if(longitud==0){
            lista="[]";
            return lista;
        }

        lista="[";

        for(T elemento:this){
            if(elemento==ultimo.elemento){
                lista+=elemento+"]";
                continue;
            }

            lista+=elemento+", ";
        }

        return lista;
    }

   /**
    * Implementacion del método equals, compara dos objetos de tipo lista
    * si son iguales regresa true y en caso contrario false.
    * @param o
    * @return boolean
    */
    @Override
    public boolean equals(Object o){

      boolean estado=false;

      if(o instanceof Lista){
          Lista comparado=(Lista) o;

          if(comparado.getLongitud()!=this.getLongitud())
              return estado;

          Nodo actual=cabeza;

           for(Object elemento:comparado){
               if(elemento.equals(actual.elemento)){
                      estado=true;
                      actual=actual.siguiente;
                      continue;
                }
                estado=false;
                actual=actual.siguiente;
            }
        }
    return estado;

    }

    @Override
    public Iterator<T> iterator(){
        return new Iterador();
    }

    public static void main(String[] args) {
        Integer[] arreglo={1,2,3,4,5,6,7,8,9,10};
        Lista<Integer> lista =new Lista<>(arreglo);

        //lista.eliminaPrimero();
        lista.elimina(0);
        lista.eliminaPrimero();
        lista.eliminaUltimo();
        System.out.print(lista.getLongitud());
    }
}
