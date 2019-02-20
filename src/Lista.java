import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Implementación de listas ligadas.
 */
public class Lista<T> implements Iterable<T>{

    // Clase interna para representar los nodos de nuestras listas.
    private class Nodo{

    public T elemento;
    public Nodo siguiente;
    public Nodo anterior;

    public Nodo(T elemento){
        siguiente=null;
        anterior=null;
        this.elemento=elemento;
    }
    }

    // Clase para iterar la lista.
    private class Iterador implements Iterator<T>{

    private Nodo actual;

    @Override
    public boolean hasNext(){
        if(actual==null){
            if(cabeza!=null)
                return true;
            return false;
        }
        return actual.siguiente!=null;
    }

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

    public Lista(){
       longitud=0;
    }

    public Lista(T[] arreglo){

      if(arreglo.length > 0){


      for(int i=0; i < arreglo.length; i++){

      this.agregaFinal(arreglo[i]);

       }

     }

    }

    public T getPrimero(){
        return cabeza.elemento;
    }

    public T getUltimo(){
        return ultimo.elemento;
    }

    public T eliminaPrimero(){
        T eliminado=null;

        if(longitud!=0){
            eliminado=cabeza.elemento;
            cabeza=cabeza.siguiente;

            if(cabeza!=null)
                cabeza.anterior=null;
        }
        return eliminado;
    }

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

    public boolean contiene(T t){
        Iterator<T> iterador=this.iterator();

        for(T elemento:this){
            if(t.equals(elemento))
                return true;
        }
        return false;

    }

     public int getLongitud(){
        return longitud;
    }

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

    public void limpia(){
        cabeza=null;
    }

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

   public Object[] toArray(){

   Object[] listaEnArreglo = new Object[this.getLongitud()];

   int contador=0;

    for(T elemento: this){

    listaEnArreglo[contador] = elemento;
    contador ++;

     }

    return listaEnArreglo;


   }

//    public Lista<T> reversa(){
    // Aquí va su código. IDEA IMPORTANTE: Se puede hacer con el método agregaInicio()
//    }


//    public Lista<T> copia(){
    // Aquí va su código.
//    }
    @Override
    public String toString(){
        //Iterator<T> iterador=this.iterator();
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

//    @Override
//    public boolean equals(Object o){
        // Aquí va su código.
//    }

    @Override
    public Iterator<T> iterator(){
        return new Iterador();
    }

    public static void main(String[] args) {
        Integer[] arreglo= {1,2,3,4,5};



        Lista<Integer> milista=new Lista<Integer>();

        Iterator<Integer> iterador=milista.iterator();

        //System.out.println(milista.getLongitud());

        Lista<Integer> arregloLista = new Lista<Integer>(arreglo);

        System.out.println(arregloLista.getPrimero());

        System.out.println(arregloLista.getUltimo());



        for(Object elemento: arregloLista.toArray()){

          System.out.println(elemento);

        }




        /*milista.agregaInicio(1);

        milista.agregaFinal(2);

        milista.agregaFinal(3);

        milista.inserta(3,9);

        milista.inserta(4,2);

        milista.inserta(5,5);

        milista.inserta(6,8);*/

        //System.out.println(milista.getUltimo());

        //System.out.println(milista.elimina(11));

        //System.out.println( milista.toString());
    }
}
