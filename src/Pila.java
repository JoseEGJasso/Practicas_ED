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

        for(int i=0; i<l.getLongitud();i++)
            pila.agregaInicio(l.get(i));    

    }

    public Pila(T[] arreglo){
        pila = new Lista<>();

        for (int i=arreglo.length-1;i>=0;i--)
            pila.agregaFinal(arreglo[i]);

    }

    public boolean esVacia(){
        try{
            pila.getPrimero();
        }catch (NullPointerException e) {
            return true;
        }
        return false;
    }

    public T mira() {
        if(!this.esVacia())
            return pila.getPrimero();
        throw new EmptyStackException();
	
    }

    public T saca(){
        if(!this.esVacia())
            return pila.eliminaPrimero();
        throw new EmptyStackException();
    }

    public void mete(T t){
        if(t!=null)
            pila.agregaInicio(t);
    }

    @Override
    public String toString(){
        String elementos="";

        for (T elemento: pila)
            elementos+=elemento+"\n";
        return elementos;
    }

    @Override
    public boolean equals(Object o){
     boolean estado=false;

      if(o instanceof Pila){
          Pila comparado=(Pila) o;

//          for(Object elemento:comparado){
//            if(elemento.equals(actual.elemento)){
//                estado=true;
//                actual=actual.siguiente;
//                continue;
//            }
//            estado=false;
//            actual=actual.siguiente;
//        }
//        }
        return estado;
    }

    @Override
    public Iterator<T> iterator(){
        return pila.iterator();
    }

    public static void main(String[] args) {
        Integer[] arreglo={1,2,3,4,5,6};
        Lista<Integer> lista=new Lista<>(arreglo);
        Pila<Integer> pila=new Pila<>(arreglo);

        Integer ori=100;

        System.out.print(pila.toString());
        System.out.println();
        pila.mete(ori);
        System.out.println(pila.mira());
        System.out.println(pila.getClass().getName());
        System.out.println(pila.esVacia());
    }
}