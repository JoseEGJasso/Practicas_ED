import java.util.function.Consumer;

/**
 * Clase que representa un Árbol Binario.
 */
public abstract class ArbolBinario<T>{

    protected class Vertice{

	public T elemento;
	public Vertice izquierdo;
	public Vertice derecho;
	public Vertice padre;
	
	public Vertice(T elemento){
	    // Aquí va su código
	}
    }

    protected Vertice raiz;
    protected int elementos;
    
    public ArbolBinario(){
	// Aquí va su código
    }

    public ArbolBinario(T[] arreglo){
	// Aquí va su código
    }

    public abstract void agrega(T elemento);

    public abstract boolean elimina(T elemento);

    public abstract boolean contiene(T elemento);

    public void bfs(Consumer<T> funcion){
	// Aquí va su código
    }

    public void dfs(int tipo, Consumer<T> funcion){
	// Aquí va su código
    }

    public int getElementos(){
	// Aquí va su código
    }
}
