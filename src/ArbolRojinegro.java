/**
 * Implementaci�n de �rboles rojinegros.
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{

    protected class VerticeRojinegro extends Vertice{
	
        public VerticeRojinegro(T elemento){
            super(elemento);
            color=Color.NEGRO;
        }
        
        public Color color;
    }

    public ArbolRojinegro(){
        elementos=0;
    }

    public ArbolRojinegro(T[] a){
        super(a);
    }

    protected Color getColor(Vertice v){
        if(v==null)
            return Color.NEGRO;
        if(v instanceof ArbolRojinegro.VerticeRojinegro){
            VerticeRojinegro vrj=(VerticeRojinegro)v;

            return vrj.color;
        }
        return null;
    }

    protected void setColor(Vertice v, Color c){
        if(v==null || c==null){
            return;
        }

        if(v instanceof ArbolRojinegro.VerticeRojinegro){        
            VerticeRojinegro vrj=(VerticeRojinegro)v;

            vrj.color=c;
        }
    }

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

    private Vertice buscaTio(Vertice v){
        if(v.padre.padre!=null){
            if (v.padre == v.padre.padre.derecho)
                return v.padre.padre.izquierdo;
            else
                return v.padre.padre.derecho;
        }
        return null;
    }

    private Vertice buscaHermano(Vertice v){
        if(v.padre!=null){
            if (v.padre.izquierdo==v){
                return v.padre.derecho;
            }
            return v.padre.izquierdo;
        }
        return null;
    }

    ///CASO TRES DE REBALANCEA
    private void casoTresRebalanceaAgrega(Vertice v){
        if(buscaTio(v)!=null)
            setColor(buscaTio(v),Color.NEGRO);

        setColor(v.padre,Color.NEGRO);

        setColor(v.padre.padre,Color.ROJO);

        rebalanceaAgregar(v.padre.padre);
    }

    private void casoCuatroRebalanceaAgrega(Vertice v){
            if(v.padre==v.padre.padre.izquierdo){
                this.giraIzquierdo(v.padre);
            } else{
                this.giraDerecha(v.padre);
            }
    }

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

    private void casoDosRebalanceaElimina(Vertice u){
        setColor(u.padre, Color.ROJO);

        setColor(buscaHermano(u), Color.NEGRO);

        if (u==u.padre.izquierdo)
            this.giraIzquierdo(u.padre);
        else
            this.giraDerecha(u.padre); 
    }

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


    public static void main(String[] args) {
        Integer[] arbol={27,20,28,29,30,31,32,33,34,35,35,36,37,38,27};
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