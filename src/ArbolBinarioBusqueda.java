import java.util.function.Consumer;
/**
 * Clase que representa un árbol binario de búsqueda.
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T>{

    public ArbolBinarioBusqueda(){
        elementos=0;
    }

    public ArbolBinarioBusqueda(T[] a){
        super(a);
    }

    @Override
    public void agrega(T elemento){
        if (elemento!=null) {
            if(raiz==null){
                raiz=new Vertice(elemento);
                elementos++;
                return;
            }

            Vertice actual=raiz;
            Vertice nuevoVertice=new Vertice(elemento);
            boolean yaAgrego=false;

            while(!yaAgrego){
                if(elemento.compareTo(actual.elemento)<=0){
                    if(actual.izquierdo==null){
                        actual.izquierdo=nuevoVertice;
                        actual.izquierdo.padre=actual;
                        yaAgrego=true;
                    } else
                        actual=actual.izquierdo;
                } else{
                    if(actual.derecho==null){
                        actual.derecho=nuevoVertice;
                        actual.derecho.padre=actual;
                        yaAgrego=true;
                    } else
                        actual=actual.derecho;
                }
            }
            elementos++;
        }
    }

    @Override
    public boolean contiene(T elemento){
        if(elemento==null)
            return false;
        if(raiz==null)
            return false;
        if(buscaVertice(elemento)==null)
            return false;
        return true;
    }

    @Override
    public boolean elimina(T elemento){
        if (elemento == null)
            return false;

        if (raiz == null)
            return false;

        if (buscaVertice(elemento) == null){
            return false;
        }

        Vertice actual = buscaVertice(elemento);

        elementos--;

        if (actual.derecho != null && actual.izquierdo != null) {
            Vertice intercambio = mayorDerecho(actual);
            T auxEle = actual.elemento;

            actual.elemento = intercambio.elemento;
            intercambio.elemento = auxEle;

            actual = intercambio;

        }
        if (actual.derecho == null && actual.izquierdo == null) {
            if (actual.padre != null) {
                if (actual.padre.izquierdo != null) {
                    if (actual.padre.izquierdo.elemento.equals(actual.elemento))
                        actual.padre.izquierdo = null;
                }if (actual.padre.derecho != null){
                    if (actual.padre.derecho.elemento.equals(actual.elemento))
                        actual.padre.derecho = null;
                }
            } else
                raiz = null;
        }
        if (actual.derecho != null && actual.izquierdo == null) {
            if (actual.padre != null) {
                actual.derecho.padre = actual.padre;

                if (actual.padre.izquierdo != null) {
                    if (actual.padre.izquierdo.elemento.equals(actual.elemento))
                        actual.padre.izquierdo = actual.derecho;
                }
                if (actual.padre.derecho != null)
                    actual.padre.derecho = actual.derecho;
            } else {
                actual.derecho.padre = null;
                raiz = actual.derecho;
            }
        }
        if (actual.izquierdo != null && actual.derecho == null) {
            if (actual.padre != null) {
                actual.izquierdo.padre = actual.padre;

                if (actual.padre.izquierdo != null) {
                    if (actual.padre.izquierdo.elemento.equals(actual.elemento))
                        actual.padre.izquierdo = actual.izquierdo;
                }
                if (actual.padre.derecho != null)
                    actual.padre.derecho = actual.izquierdo;
            } else {
                actual.izquierdo.padre = null;
                raiz = actual.izquierdo;
            }
        }

        System.out.println("\n"+actual.derecho.elemento);

        return true;
    }

    protected Vertice buscaVertice(T elemento){
        Vertice actual=raiz;

        while(actual!=null){

            if(elemento.equals(actual.elemento)){
                return actual;
            }

            if(elemento.compareTo(actual.elemento)<=0){
                if(actual.izquierdo!=null){
                    actual=actual.izquierdo;
                }else
                    break;
            }else{
                if(actual.derecho!=null){
                    actual=actual.derecho;
                }else
                    break;
            }
        }
        return null;
    }

    protected Vertice mayorDerecho(Vertice u){
        Vertice actual=u.izquierdo;

        while(actual.derecho!=null){
            actual=actual.derecho;
        }

        return actual;
    }

    public static void main(String[] args) {
        Integer[] a={10,12};
        Consumer<Integer> l=t -> {
            System.out.print(t+", ");
        };

        ArbolBinarioBusqueda<Integer> arbol=new ArbolBinarioBusqueda<>(a);
        arbol.bfs(l);
        System.out.println(arbol.elimina(10));
        arbol.bfs(l);
        // System.out.println(arbol.elimina(4));
        // System.out.println(arbol.elimina(5));
        // arbol.bfs(l);
    }
}