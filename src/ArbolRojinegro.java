/**
 * Implementaci�n de �rboles rojinegros.
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{

    /**
     * Clase interna de la clase ArbolRojinegro, la cual extiende de la clase vértice de
     * ArbolBinarioBusqueda. La única modificación es que los vértices tienen color ROJO
     * o  NEGRO.
     */
    protected class VerticeRojinegro extends Vertice{

        /**
         * Constructor del VerticeRojinegro, con la modificación del Color
         *
         * @param elemento
         */
        public VerticeRojinegro(T elemento){
            super(elemento);
            color=Color.NEGRO;
        }
        /**
         * ??????
         *
         */
        public Color color;
    }

    /**
     * Constructor de un árbol rojinegro vacío. El atributo elementos
     * se inicializa en 0.
     *
     */
    public ArbolRojinegro(){
        elementos=0;
    }

    /**
     * Constructor de un árbol rojinegro a partir de un arreglo de tipo
     * genérico.Se hace una llamada a la clase superior con super(),
     * es decir ArbolBinarioBusqueda.
     *
     * @param a
     */
    public ArbolRojinegro(T[] a){
        super(a);
    }

    /**
     * Método protegido para obtener el color de un vértice . Recibe el vértice y retorna
     * el color.
     *
     * @param v
     * @return Color
     */
    protected Color getColor(Vertice v){
        if(v==null)
            return Color.NEGRO;
        if(v instanceof ArbolRojinegro.VerticeRojinegro){
            VerticeRojinegro vrj=(VerticeRojinegro)v;

            return vrj.color;
        }
        return null;
    }

    /**
     * Método protegido para modificar el color de un vértice, recibe el vértice a modificar.
     *
     * @param v
     * @param c
     *
     */
    protected void setColor(Vertice v, Color c){
        if(v==null || c==null){
            return;
        }

        if(v instanceof ArbolRojinegro.VerticeRojinegro){
            VerticeRojinegro vrj=(VerticeRojinegro)v;

            vrj.color=c;
        }
    }

    /**
     * Método para visualizar el árbol rojinegro. Por medio de una cadena de caractéres
     * se imprimen los colores de los vértices del árbol.
     *
     *
     */
    public String toString(){
        Cola<Vertice> procesar=new Cola<>();
        Vertice actual=raiz;
        String colores="";

        if(actual==null){
            return "";
        }

        procesar.mete(actual);

        while(!procesar.esVacia()){
            colores+=getColor(actual).toString()+", ";

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
        return colores;
    }

    /**
     * Implemtación del método abstracto agrega de ArbolBinario. Recibe un elemento y crea
     * un vértice que contiene a dicho elemento, luego lo agrega al árbol rojinegro respetando
     * las cinco reglas de los árboles rojinegros y después rebalancea. Utilizará un método
     * auxiliar para rebalancear el árbol después de haber agregado el vértice llamado
     * rebalanceaAgregar.
     *
     * @param elemento
     */
    @Override
    public void agrega(T elemento){
        if (elemento!=null) {
            if(raiz==null){
                raiz=new VerticeRojinegro(elemento);
                setColor(raiz,Color.ROJO);
                rebalanceaAgregar(raiz);
                elementos++;
                return;
            }

            Vertice actual=raiz;
            Vertice nuevoVertice=new VerticeRojinegro(elemento);
            boolean yaAgrego=false;

            setColor(nuevoVertice,Color.ROJO);

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

            rebalanceaAgregar(nuevoVertice);
        }
    }

    /**
     * Método auxiliar para agrega. El método recibe el vértice a balancear y dependiendo del
     * caso, hace una llamada al método auxiliar correspondiente al caso. Algunos casos son muy
     * simples, por lo que se realizan dentro de éste método, éstos son el caso 1 y el caso 2.
     *
     * @param v
     */
    private void rebalanceaAgregar(Vertice v){

        if(v.padre == null){
            setColor(v,Color.NEGRO);
            return;

        }else{
            if(getColor(v.padre) == Color.ROJO){
                if(buscaTio(v)!=null){
                    if(getColor(buscaTio(v))==Color.ROJO){
                        casoTresRebalanceaAgrega(v);
                        return;
                    }
                }
                if(estanCruzados(v)){
                    casoCuatroRebalanceaAgrega(v);

                    if(v.padre.izquierdo==v){
                        v=v.izquierdo;
                    } else{
                        v=v.derecho;
                    }
                }
                casoCincoRebalanceaAgrega(v);
            }
        }

    }

    /**
     * Método auxiliar para encontrar el vértice "tío" del vértice actual v.
     *
     * @param v
     * @return Vertice
     */
    private Vertice buscaTio(Vertice v){
        if(v.padre.padre!=null){
            if (v.padre == v.padre.padre.derecho)
                return v.padre.padre.izquierdo;
            else
                return v.padre.padre.derecho;
        }
        return null;
    }

    /**
     * Método auxiliar para encontrar al hermano del vértice actual v.
     *
     * @param v
     * @return Vertice
     */
    private Vertice buscaHermano(Vertice v){
        if(v.padre!=null){
            if (v.padre.izquierdo==v){
                return v.padre.derecho;
            }
            return v.padre.izquierdo;
        }
        return null;
    }

    /**
     * Método auxiliar que modela el caso 3 del rebalanceo en agrega.Colorea al vértice "tío" de NEGRO
     * y al vértice "padre" de NEGRO, después al vértice "abuelo" de ROJO y hace recursión sobre el
     * vértice "abuelo".
     *
     * @param v
     */
    private void casoTresRebalanceaAgrega(Vertice v){
        if(buscaTio(v)!=null)
            setColor(buscaTio(v),Color.NEGRO);

        setColor(v.padre,Color.NEGRO);

        setColor(v.padre.padre,Color.ROJO);

        rebalanceaAgregar(v.padre.padre);
    }

    /**
     * Método auxiliar que modela el caso 4 del rebalanceo en agrega.Recordemos que el caso 4 del
     * rebalanceo en agrega, "endereza" a los vértices que se encuentran cruzados para que el caso
     * 5 se encargue de concluir el rebalanceo.
     *
     * @param v
     */
    private void casoCuatroRebalanceaAgrega(Vertice v){
            if(v.padre==v.padre.padre.izquierdo){
                this.giraIzquierdo(v.padre);
            } else{
                this.giraDerecha(v.padre);
            }
    }

    /**
     * Método auxiliar para saber si el vértice actual v y el vértice "padre" de v se encuentran
     * cruzados. Este método se llama en rebalanceaAgregar, esto es para poder entrar al caso 4
     * del rebalanceo en agrega.El método retorna un boolean "true" si los vértices se encuentran
     * cruzados y en caso contrario un false.
     *
     * @param v
     * @return boolean
     */
    private boolean estanCruzados(Vertice v){
        if(v.padre.padre!=null){
            if(v.padre==v.padre.padre.derecho){
                if(v.padre.izquierdo==v)
                    return true;
            }

            if(v.padre==v.padre.padre.izquierdo){
                if(v.padre.derecho==v)
                    return true;
            }
        }
        return false;
    }

    /**
     * Método auxiliar que modela el caso 5 del rebalanceo en agrega. Recordemos que este caso ocurre
     * cuando ambos ya han sido "enderezados" en el caso 4, lo único que resta es modificar los colores.
     * El vértice "padre" de v se recolorea de NEGRO. Si el vértice "abuelo" es distinto a la raíz, entonces
     * se reocolea de ROJO. Finalmente se realiza un giro dependiendo de si el vértice v es derecho o izquierdo
     *
     * @param v
     */
    private void casoCincoRebalanceaAgrega(Vertice v){
        setColor(v.padre,Color.NEGRO);

        if(v.padre.padre!=null){
            setColor(v.padre.padre,Color.ROJO);

            if(v.padre.izquierdo==v)
                giraDerecha(v.padre.padre);
            else
                giraIzquierdo(v.padre.padre);
        }
    }

    /**
     * Implemetación del método abstracto de elimina de ArbolBinario. El método utiliza el mismo
     * criterio de ArbolBinarioBusqueda para eliminar un vértice, sin embargo después de eliminar
     * un vértice hay que rebalancear el árbol respetando las 5 reglas de los árboles rojinegros.
     * Al igual que el método de agrega, utilizará un método auxiliar para rebalancear el árbol
     * rojinegro llamado rebalanceaEliminar.
     * Habrán casos en los que sea necesario crear un vértice "fantasma", el cual será utilizado
     * para que al realizar ciertos movimientos en el árbol no quede un hueco. Al finalizar el rebalanceo
     * el vértice "fantasma" será eliminado.
     * Si el vértice se logro eliminar, el método regresa un boolean "true", en caso contrario
     * retorna un boolean "false".
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

        if (buscaVertice(elemento) == null) {
            return false;
        }

        Vertice actual = buscaVertice(elemento);
        Vertice fantasma=new VerticeRojinegro(null);

        elementos--;

        if (actual.derecho != null && actual.izquierdo != null) {
            System.out.println("Entra doble NO nulos");
            Vertice intercambio = mayorDerecho(actual);
            T auxEle = actual.elemento;


            actual.elemento = intercambio.elemento;
            intercambio.elemento = auxEle;

            actual = intercambio;

        }if (actual.derecho == null && actual.izquierdo == null) {
            if(getColor(actual)==Color.ROJO){

                if(actual.padre.izquierdo==actual)
                    actual.padre.izquierdo=null;
                else
                    actual.padre.derecho=null;
                return true;
            }
            System.out.println("Entra doble nulos nodo negro");
            fantasma.padre=actual;
            actual.izquierdo=fantasma;
        }
        if (actual.derecho != null && actual.izquierdo == null) {
            System.out.println("Entra derecho distinto de nulo y existe izquierdo" );

            if (actual.padre != null) {
                actual.derecho.padre = actual.padre;

                if (actual.padre.izquierdo==actual)
                    actual.padre.izquierdo = actual.derecho;
                else{
                    actual.padre.derecho = actual.derecho;
                }
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

            }else {
                actual.izquierdo.padre = null;
                raiz = actual.izquierdo;
            }
        }

        if(getColor(actual)==Color.ROJO){
            System.out.println("Entra el nodo es rojo");
            return true;
        }

        if (actual.izquierdo!=null) {
            System.out.println("Entra el hijo izq es rojo");
            if (getColor(actual.izquierdo)==Color.ROJO){
                setColor(actual.izquierdo, Color.NEGRO);
                return true;
            }
        } if(actual.derecho!=null){
            System.out.println("Entra el hijo der es rojo");
            if (getColor(actual.derecho)==Color.ROJO){
                setColor(actual.derecho, Color.NEGRO);
                return true;
            }
        }

        if (getColor(actual)==Color.NEGRO) {
            System.out.println("Entra rebalanceo");
            if(actual.derecho!=null){
                if(getColor(actual.derecho)==Color.NEGRO)
                    rebalanceaEliminar(actual.derecho);
            }else{
                if(getColor(actual.izquierdo)==Color.NEGRO)
                    rebalanceaEliminar(actual.izquierdo);
            }
        }

        if(actual.izquierdo==fantasma){
            System.out.println("Elimina fantasma");
            if(fantasma.padre.izquierdo==fantasma){
                fantasma.padre.izquierdo=null;
            } else{
                fantasma.padre.derecho=null;
            }
            fantasma.padre=null;
        }

        return true;
    }

    /**
     * Método auxiliar para rebalancear un árbol rojinegro después de haber eliminado un
     * vértice, éste , al igual que rebalanceaAgregar, utilizará en algunos casos métodos
     * auxiliares para rebalancear. Los casos que no son modelados como métodos auxiliares
     * son el caso 1, caso 3 . En cuanto al caso 5, en este método únicamente se dan las
     * condiciones de entrada para el caso 5. Y si el caso 5 se ejecuta por defecto también
     * el 6.
     *
     * @param v
     */
    private void rebalanceaEliminar(Vertice u){
        if(u.padre==null)
            return;

        if(getColor(buscaHermano(u))==Color.ROJO){
            System.out.println("Entra caso2 rebalanceo");
            casoDosRebalanceaElimina(u);
        }

        //CASO 3.
        if(getColor(u.padre)==Color.NEGRO && getColor(buscaHermano(u))==Color.NEGRO){
            if (getColor(buscaHermano(u).derecho)==Color.NEGRO && getColor(buscaHermano(u).izquierdo)==Color.NEGRO){
                System.out.println("Entra caso3 rebalanceo");
                setColor(buscaHermano(u),Color.ROJO);
                rebalanceaEliminar(u.padre);
                return;
            }
        }

        //CASO 4.
        if(getColor(buscaHermano(u))==Color.NEGRO && getColor(u.padre)==Color.ROJO){
            if(getColor(buscaHermano(u).derecho)==Color.NEGRO && getColor(buscaHermano(u).izquierdo)==Color.NEGRO){
                System.out.println("Entra caso4 rebalanceo");
                setColor(buscaHermano(u), Color.ROJO);
                setColor(u.padre, Color.NEGRO);
                return;
            }
        }

        //CASO 5. CONDICIONES DE ENTRADA
        if(u.padre.izquierdo==u){
            if(getColor(buscaHermano(u).izquierdo)==Color.ROJO && getColor(buscaHermano(u).derecho)==Color.NEGRO)
                casoCincoRebalanceaElimina(u);
        }else{
            if(getColor(buscaHermano(u).izquierdo)==Color.NEGRO && getColor(buscaHermano(u).derecho)==Color.ROJO)
                casoCincoRebalanceaElimina(u);
        }

        casoSeisRebalanceaElimina(u);
    }

    /**
     * Método auxiliar que modela el caso 2 del rebalanceo después de eliminar un vértice.
     * Se modifican los colores del padre a ROJO y del hermano a NEGRO, luego se gira al padre
     * dependiendo de si el vértice u es izquierdo o derecho.
     *
     * @param u
     */
    private void casoDosRebalanceaElimina(Vertice u){
        setColor(u.padre, Color.ROJO);

        setColor(buscaHermano(u), Color.NEGRO);

        if (u==u.padre.izquierdo)
            this.giraIzquierdo(u.padre);
        else
            this.giraDerecha(u.padre);
    }

    /**
     * Método auxiliar que modela el caso 5 del rebalanceo después de eliminar un vértice.
     *
     *
     * @param u
     */
    private void casoCincoRebalanceaElimina(Vertice u){
        System.out.println("Entra caso5 rebalanceo");

        setColor(buscaHermano(u), Color.ROJO);

        if(getColor(buscaHermano(u).izquierdo)==Color.ROJO){
            setColor(buscaHermano(u).izquierdo, Color.NEGRO);
        }else
            setColor(buscaHermano(u).derecho, Color.NEGRO);

        if(u.padre.izquierdo==u){
            // bfs(t->{
            //     System.out.print(t+", ");
            // });
            this.giraDerecha(buscaHermano(u));
        }else{
            this.giraIzquierdo(buscaHermano(u));
        }

    }

    /**
     * Método auxiliar que modela el caso 6 del rebalanceo después de eliminar un vértice.
     *
     *
     * @param u
     */
    private void casoSeisRebalanceaElimina(Vertice u){
        System.out.println("Entra caso6 rebalanceo");

        setColor(buscaHermano(u), getColor(u.padre));
        setColor(u.padre,Color.NEGRO);

        if (u.padre.izquierdo==u && getColor(buscaHermano(u).derecho)==Color.ROJO) {
            setColor(buscaHermano(u).derecho, Color.NEGRO);
            this.giraIzquierdo(u.padre);
        } else{
            setColor(buscaHermano(u).izquierdo, Color.NEGRO);
            // bfs(t->{
            //     System.out.print(t+", ");
            // });
            // System.out.println();
            this.giraDerecha(u.padre);
            // bfs(t->{
            //     System.out.print(t+", ");
            // });
        }
    }

    /**
     * Método main para probar
     *
     *
     */
    public static void main(String[] args) {
        //Integer[] arbol={27,20,28,29,30,31,32,33,34,35,35,36,37,38,27};
        String[] arbol ={"hola", "que", "hace", "que", "que", "que", "que"};
        ArbolRojinegro<Integer> arbol1=new ArbolRojinegro<>(arbol);

        System.out.println(arbol1.elimina(27));

        arbol1.bfs(t->{
            System.out.print(t+", ");
        });

        System.out.println("\n"+arbol1.toString());

        System.out.println(arbol1.elimina(29));

        arbol1.bfs(t->{
            System.out.print(t+", ");
        });

        System.out.println("\n"+arbol1.toString());
    }
}
