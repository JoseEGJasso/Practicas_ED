import java.util.function.Consumer;

/**
 * Clase que representa un Árbol Binario.
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public abstract class ArbolBinario<T>{

    protected class Vertice{

    public T elemento;
    public Vertice izquierdo;
    public Vertice derecho;
    public Vertice padre;

    /**
     * Constructor de vértices que asigna el elemento recibido
     * al vértice creado
     * @param elemento; elemento a asignar
     */
    public Vertice(T elemento){
        this.elemento=elemento;
    }
    }

    protected Vertice raiz;
    protected int elementos;

    /**
     *
     * Constructor de un árbol binario vacío;
     *
     */
    public ArbolBinario(){
        elementos=0;
    }

    /**
     * Constructor de un arbol binario mediante un arreglo. La raíz se toma
     * como el primer elemento del arreglo.
     * @param arreglo; arreglo del cual se va a crear un arbol binario
     */
    public ArbolBinario(T[] arreglo){
        if (arreglo.length!=0) {            
            for(int i=1;i<arreglo.length;i++)
                agrega(arreglo[i]);
        }
    }

    /**
     * Método para agregar elementos al árbol
     * @param elemento; elemento a agregar
     */
    public abstract void agrega(T elemento);

    /**
     * Método que elimina la primera conincidencia en el arbol del elemento ingresado.
     * @param elemento; elemento a buscar y eliminar
     * @return boolean; si el elemento se eliminó exitosamente regresa true, en caso contraio false
     */
    public abstract boolean elimina(T elemento);

    /**
     * Método que verifica si el árbol contiene el elemento ingresado.
     * @param elemento; elemento a buscar
     * @return boolean; si se encuentra regresa true, en caso contrario false
     */
    public abstract boolean contiene(T elemento);

    /**
     * Método que recorre el árbol por lo ancho de derecha a izquierda
     * @param funcion; lambda que indica la acción a relizar con cada vértice
     */
    public void bfs(Consumer<T> funcion){

        Cola<Vertice> procesar=new Cola<>();
        Vertice actual=raiz;

        if(actual!=null){
            procesar.mete(actual);
        }

        while(!procesar.esVacia()){
            funcion.accept(actual.elemento);

            procesar.saca();

            if(actual.izquierdo!=null)
                procesar.mete(actual.izquierdo);
            if(actual.derecho!=null)
                procesar.mete(actual.derecho);

            actual=procesar.mira();
        }
    }
    /**
     * Método que recorre el árbol por su profundidad. Se recorre de tres maneras diferentes.
     * Si se pone como parámetro tipo 1, el árbol es recorrido pre-order.
     * Si se pone como parámetro tipo 2, el árbol es recorrido in-order.
     * Si se pone como parámetro tipo 3, el árbol es recorrido post-order.
     * En caso cotrario se lanza una excepción.
     * @param tipo,funcion; tipo de recorrido a ejecutar y lambda que indica
     *                      la acción a relizar con cada vértice.
     * @throws IllegalArgumentException.
     */
    public void dfs(int tipo, Consumer<T> funcion){

        if(raiz==null)
            return;

        switch (tipo) {
            case 1:
            auxPre(raiz,funcion);
            break;
            case 2:
            auxIn(raiz,funcion);
            break;
            case 3:
            auxPost(raiz,funcion);
            break;
            default:
            throw new IllegalArgumentException("Sólo puedes ingresar un número entre 1 y 3!");  
        }
    }
    /**
     * Método auxiliar recursivo para el recorrido pre-order.
     * @param u,funcion; vértice actual de la recursión (inicia en la raiz si esta es !=null) y
     *                   lambda que indica la acción a realizar con cada vértice.
     */
    private void auxPre(Vertice u,Consumer<T> funcion){
        if(u==null)
            return;
        funcion.accept(u.elemento);
        auxPre(u.izquierdo,funcion);
        auxPre(u.derecho,funcion);
    }
    /**
     * Método auxiliar recursivo para el recorrido post-order.
     * @param u,funcion; vértice actual de la recursión (inicia en la raiz si esta es !=null) y
     *                   lambda que indica la acción a realizar con cada vértice.
     */
    private void auxPost(Vertice u,Consumer<T> funcion){
        if(u==null)
            return;
        auxPost(u.izquierdo,funcion);
        auxPost(u.derecho,funcion);
        funcion.accept(u.elemento);
    }
    /**
     * Método auxiliar recursivo para el recorrido in-order.
     * @param u,funcion; vértice actual de la recursión (inicia en la raiz si esta es !=null) y
     *                   lambda que indica la acción a realizar con cada vértice.
     */
    private void auxIn(Vertice u,Consumer<T> funcion){
        if(u==null)
            return;
        auxIn(u.izquierdo,funcion);
        funcion.accept(u.elemento);
        auxPre(u.derecho,funcion);
    }
    /**
     * Regresa el número de elementos dentro del árbol.
     * @return int; num de elementos en el árbol.
     */
    public int getElementos(){
        return elementos;
    }
}
