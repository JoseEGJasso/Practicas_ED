import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Implementación de colas.
 */
public class Cola<T> implements Iterable<T>{

    private Lista<T> cola;

    public Cola(){

        cola=new Lista<>();
    }
    
    public Cola(Lista<T> l){
        cola=new Lista<>();

        for(T elemento:l)
            cola.agregaFinal(elemento);
    }
    
    public Cola(T[] arreglo){

        cola=new Lista<>(arreglo);
    }

    public boolean esVacia(){
        try{
            cola.getPrimero();
        }catch (NullPointerException e) {
            return true;
        }
        return false;
    }

    public T mira(){
        if(!this.esVacia())
            return cola.getPrimero();
        throw new NoSuchElementException();
    }

    public T saca(){
        if(!this.esVacia())
            return cola.eliminaPrimero();
        throw new NoSuchElementException();
    }

    public void mete(T t){
        if(t!=null)
            cola.agregaFinal(t);
    }

    @Override
    public String toString(){
        return cola.toString();
    }

    @Override
    public boolean equals(Object o){
        return false;
    }

    @Override
    public Iterator<T> iterator(){
        return cola.iterator();
    }

    public static void main(String[] args) {
        Integer[] arreglo={1,2,3,4,5,6};
        Cola<Integer> cola=new Cola<>(arreglo);

        System.out.print(cola.toString());

        System.out.println(cola.esVacia());
    }
}