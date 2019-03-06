import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Implementación de pilas.
 */
public class Pila<T> implements Iterable<T>{

    private Lista<T> pila;
    

    public Pila(){   
        pila = new Lista<>();
      
    }
    
    public Pila(Lista<T> l){
	 
        pila = new Lista<>();

        for(int i=0; i<l.getLongtud();i++)
            pila.agregaPrimero(l.get(i));    

    }

    public Pila(T[] arreglo){

        pila = new Lista<>(arreglo);
    }

    public boolean esVacia(){
        return pila.esVacia();
    }

    public T mira() throw EmptyStackException{

        if(!pila.esVacia())
            return pila.getPrimero();
        throw new EmptyStackException();
	
    }

    public T saca(){
	//Aquí va su código.
    }

    public void mete(T t){
	//Aquí va su código.
    }

    @Override
    public String toString(){
	//Aquí va su código.
    }

    @Override
    public boolean equals(Object o){
	//Aquí va su código.
    }

    @Override
    public Iterator<T> iterator(){
	//Aquí va su código.
    }
}