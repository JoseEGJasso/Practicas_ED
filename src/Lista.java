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

    public Nodo actual;

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
        Nodo siguientes=null;
        Nodo anteriores=null;

        for(int i=0;i<arreglo.length;i++){
            Nodo nuevo=new Nodo(arreglo[i]);
            
            if(i<arreglo.length-1)
                siguientes=new Nodo(arreglo[i++]);

            if(i==0){
                cabeza=nuevo;
                cabeza.siguiente=siguientes;
            } if(i==arreglo.length-1){
                ultimo=nuevo;
                ultimo.anterior=anteriores;
            }else{
                nuevo.siguiente=siguientes;
                nuevo.anterior=anteriores;
                if(i<arreglo.length-1)
                    nuevo.siguiente=new Nodo(arreglo[i++]);
            }
            anteriores=nuevo;
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
            if(cabeza!=null){
                cabeza.anterior=null;
                return cabeza.elemento;
            }
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

//    public boolean contiene(T t){
	// Aquí va su código.
//  }

     public int getLongitud(){
        return longitud;
    }

//    public boolean elimina(T t){
	// Aquí va su código.
//    }

    public void limpia(){
        cabeza=null;
    }

//    public T get(int indx){
	// Aquí va su código.
//    }
 
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

            int contador=1;

            while(contador<indx-1){
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

//    public Object[] toArray(){
	// Aquí va su código.
//    }

//    public Lista<T> reversa(){
	// Aquí va su código.
//    }


//    public Lista<T> copia(){
	// Aquí va su código.
//    }
    
//    @Override
    //    public String toString(){
       	// Aquí va su código.
//    }

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

        System.out.println(milista.getLongitud());

        //System.out.println(milista.getUltimo());
            System.out.print(iterador.next()+",");
            System.out.print(iterador.next());
        System.out.println();
    }
}
