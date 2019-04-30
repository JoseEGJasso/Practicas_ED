package proyecto2;

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
            for(int i=0;i<arreglo.length;i++)
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

        if(actual==null){
            return;
        }

        procesar.mete(actual);

        while(!procesar.esVacia()){
            funcion.accept(actual.elemento);

            procesar.saca();

            if(actual.izquierdo!=null){
                procesar.mete(actual.izquierdo);
            }
            if(actual.derecho!=null){
                procesar.mete(actual.derecho);
            }
            if(!procesar.esVacia())
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
        auxIn(u.derecho,funcion);
    }
    /**
     * Regresa el número de elementos dentro del árbol.
     * @return int; num de elementos en el árbol.
     */
    public int getElementos(){
        return elementos;
    }

    protected void giraIzquierdo(Vertice u){

        if(u==null)
            return;

        if(u.derecho==null)
            return;

        if(u.padre!=null){
            if(u.padre.izquierdo==u)
                u.padre.izquierdo=u.derecho;
            else
                u.padre.derecho=u.derecho;

            u.derecho.padre=u.padre;
            u.padre=u.derecho;

            if(u.padre.izquierdo!=null){
                Vertice aux=u.padre.izquierdo;

                u.derecho=aux;
                u.derecho.padre=u;
            }else{
                u.derecho=null;
            }
            u.padre.izquierdo=u;
        } else{
            u.derecho.padre=null;
            u.padre=u.derecho;
            if(u.derecho.izquierdo!=null){
                Vertice aux=u.derecho.izquierdo;

                u.derecho=aux;
                u.derecho.padre=u;
            } else{
                u.derecho=null;
            }
            u.padre.izquierdo=u;

            raiz=u.padre;
        }

    }

    protected void giraDerecha(Vertice u){

        if(u==null)
            return;

        if(u.izquierdo==null){
            return;
        }

        if(u.padre!=null){

            if(u.padre.izquierdo==u)
                u.padre.izquierdo=u.izquierdo;
            else
                u.padre.derecho=u.izquierdo;

            u.izquierdo.padre=u.padre;
            u.padre=u.izquierdo;


            if(u.padre.derecho!=null){
                Vertice aux=u.padre.derecho;

                u.izquierdo=aux;
                u.izquierdo.padre=u;
            }else{
                u.izquierdo=null;
            }

            u.padre.derecho=u;

        } else{
            u.izquierdo.padre=null;
            u.padre=u.izquierdo;
            if(u.padre.derecho!=null){
                Vertice aux=u.izquierdo.derecho;

                u.izquierdo=aux;
                u.izquierdo.padre=u;

            } else{
                u.izquierdo=null;
            }
            u.padre.derecho=u;

            this.raiz=u.padre;
        }

    }
}
