package proyecto2;
/**
 * Clase que representa un árbol binario de búsqueda.
 *
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> extends ArbolBinario<T>{

    /**
     * Constructor de un árbol binario de búsqueda vacío
     *
     *
     */
    public ArbolBinarioBusqueda(){
        elementos=0;
    }

    /**
     * Constructor de un árbol binario de búsqueda a partir de una arreglo
     * @param a
     *
     */
    public ArbolBinarioBusqueda(T[] a){
        super(a);
    }

    /**
     * Método para agregar vértices al árbol binario de búsqueda. Recibe un elemento
     * y después crea un vértice que tiene "adentro" al elemento, después lo agrega al
     * árbol siguiendo los criterios de un árbol binario de búsqueda.
     *
     * @param elemento
     */
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
                if(elemento.compareTo(actual.elemento)<0){
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

    /**
     * Implementación del método abstracto "contiene" de árbol binario .
     * Recibe un elemento genérico y regresa un boolean "true" si el vértice
     * contiene al elemento y en caso contrario un "false".
     *
     * @param elemento
     * @return boolean
     */
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

    /**
     * Implementación del método abstracto "elimina" de árbol binario.
     * Recibe un elemento a eliminar, después si no lo encuentra el método termina. En caso
     * de que encuentre el elemento, elimina el vértice que lo contiene y reordena el árbo
     * respetando las propiedades de un árbol binario de búsqueda.
     *
     * @param elemento
     * @return boolean
     */
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
                if (actual.padre.izquierdo==actual)
                    actual.padre.izquierdo = null;
                else
                    actual.padre.derecho = null;
            } else
                raiz = null;
        }
        if (actual.derecho != null && actual.izquierdo == null) {
            if (actual.padre != null) {
                actual.derecho.padre = actual.padre;

                if (actual.padre.izquierdo==actual)
                    actual.padre.izquierdo = actual.derecho;
                else
                    actual.padre.derecho = actual.derecho;
            } else {
                actual.derecho.padre = null;
                raiz = actual.derecho;
            }
        }
        if (actual.izquierdo != null && actual.derecho == null) {
            if (actual.padre != null) {
                actual.izquierdo.padre = actual.padre;

                    if (actual.padre.izquierdo==actual)
                        actual.padre.izquierdo = actual.izquierdo;
                    else
                        actual.padre.derecho = actual.izquierdo;
            } else {
                actual.izquierdo.padre = null;
                raiz = actual.izquierdo;
            }
        }

        return true;
    }

    /**
     * Método protegido para poder buscar un vértice que contenga al elemento genérico.
     * Si lo encuentra regresa el vértice.
     *
     * @param elemento
     * @return Vertice
     */
    protected Vertice buscaVertice(T elemento){
        Vertice actual=raiz;

        while(actual!=null){

            if(elemento.equals(actual.elemento)){
                return actual;
            }

            if(elemento.compareTo(actual.elemento)<0){
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

    /**
     * Busca en el arbol una coincidencia con el elemento que recibe como parámetro
     *
     * @return T; elemento que se busó
     */
    public T busca(T elemento){
        Vertice busqueda=buscaVertice(elemento);

        if(busqueda!=null)
            return busqueda.elemento;
        return null;
    }

    /**
     * Método protegido que nos permite saber cuál es el mayor elemento del subárbol izquierdo
     * de un árbol binario de búsqueda, será utilizado en elimina para hacer el intercambio
     * de vértices cuando uno sea eliminado.
     *
     * @param u
     * @return Vertice
     */
    protected Vertice mayorDerecho(Vertice u){
        Vertice actual=u.izquierdo;

        while(actual.derecho!=null){
            actual=actual.derecho;
        }

        return actual;
    }
}
