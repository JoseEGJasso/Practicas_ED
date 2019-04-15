
/**
 * Clase que representa árboles AVL.
 */
public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{

    protected class VerticeAVL extends Vertice{

	public int altura;

	public VerticeAVL(T elemento){
        super(elemento);
        altura=0;
	}
    }
    
    public ArbolAVL(){
        elementos=0;
    }

    public ArbolAVL(T[] elementos){
        super(elementos);
    }


    protected int getAltura(Vertice v){
        if(v==null)
            return -1;
        if(v instanceof ArbolAVL.VerticeAVL){
            VerticeAVL avl=(VerticeAVL)v;

            return avl.altura;
        }
        return 1+(Math.max(getAltura(v.izquierdo),getAltura(v.derecho)));
    }

    protected void actualizaAltura(Vertice v){
        if(!(v instanceof ArbolAVL.VerticeAVL)){
            return;
        }
        VerticeAVL avl=(VerticeAVL)v;

        avl.altura=1+(Math.max(getAltura(v.izquierdo),getAltura(v.derecho)));
    }

    
    protected int getBalance(Vertice v){
        if(v==null)
            return 0;
        return getAltura(v.izquierdo)-getAltura(v.derecho);
    }


    @Override
    public void agrega(T elemento){
        Vertice verticeNuevo= new VerticeAVL(elemento);

        elementos++;

        if(raiz==null){
            raiz=verticeNuevo;
            return;
        }

        auxAgrega(raiz,verticeNuevo);
        rebalanceo(verticeNuevo.padre);
    }

    private void auxAgrega(Vertice v,Vertice verticeNuevo){
        if(verticeNuevo.elemento.compareTo(v.elemento)<=0){
            if(v.izquierdo==null){
                v.izquierdo=verticeNuevo;
                v.izquierdo.padre=v;
            } else
                auxAgrega(v.izquierdo, verticeNuevo);
        }else{
            if(v.derecho==null){
                v.derecho=verticeNuevo;
                v.derecho.padre=v;
            } else
                auxAgrega(v.derecho, verticeNuevo);
        }
    }

    @Override
    public boolean elimina(T elemento){
        Vertice actual = buscaVertice(elemento);

        if (elemento == null)
            return false;

        if (raiz == null)
            return false;

        if (actual == null){
            return false;
        }

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

                if (actual.padre.izquierdo == actual)
                    actual.padre.izquierdo = actual.izquierdo;
                else
                    actual.padre.derecho = actual.izquierdo;
            } else {
                actual.izquierdo.padre = null;
                raiz = actual.izquierdo;
            }
        }

        rebalanceo(actual.padre);

        return true;
    }

    @Override
    protected void giraIzquierdo(Vertice v){
        super.giraIzquierdo(v);
        actualizaAltura(v);
        actualizaAltura(v.padre);
    }

    @Override
    protected void giraDerecha(Vertice v){
        super.giraDerecha(v);
        actualizaAltura(v);
        actualizaAltura(v.padre);
    }

    private void rebalanceo(Vertice v){
        if(v==null)
            return;

        actualizaAltura(v);

        System.out.print("altura hijo derecho,hijo izquierdo,v,del elemento:  "+getAltura(v.derecho)+", "+getAltura(v.izquierdo)+", "+getAltura(v)+", "+v.elemento+"\n");
        System.out.print("balances hijo derecho,hijo izquierdo,v,del elemento:  "+getBalance(v.derecho)+", "+getBalance(v.izquierdo)+", "+getBalance(v)+", "+v.elemento+"\n");

        //CASO 2. Valor del balanceo es -2 (uno de los dos unicos casos de desbalanceo)
        if(getBalance(v)==-2){
            //CASO 2 a). Valor del balanceo del vertice derecho es 1 
            System.out.print("hace algo caso del elemento(-2):  "+v.elemento+"\n");
            if(getBalance(v.derecho)==1){
                giraDerecha(v.derecho);
                //Falta renombrar vertices OJO            
            }
            //CASO 2 b). Valor del balanceo del vertice derecho es -1, 0 o -2
            giraIzquierdo(v);
        }
        //CASO 3. Inverso al caso 2 y segundo y último caso de desbalanceo
        if(getBalance(v)==2){
            //CASO 3 a). Valor del balanceo del vertice izquierdo es -1
            System.out.print("hace algo caso del elemento(2):  "+v.elemento+"\n");
            if(getBalance(v.izquierdo)==-1){
                giraIzquierdo(v.izquierdo);
                //Falta renombrar vertices OJO 
            }
            //CASO 3 b). Valor del balanceo del vertice izquierdo es 1, 0 o 2
            giraDerecha(v);
        }
        System.out.print("padre del vertice nuevo:  "+v.elemento+"\n");
        bfs(t->{
            System.out.print(t+", ");
        });
        System.out.println();
        rebalanceo(v.padre);
    }

    public String toString(){
        Cola<Vertice> procesar=new Cola<>();
        Vertice actual=raiz;
        String balanceos="";

        if(actual==null){
            return "";
        }

        procesar.mete(actual);

        while(!procesar.esVacia()){
            balanceos+=getAltura(actual)+", ";

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
        return balanceos;
    }

    public static void main(String[] args) {
        Integer[] avl={10,3,20,21,9,2,11,12,22,1};
        ArbolAVL<Integer> arbolAvl=new ArbolAVL<>(avl);

        arbolAvl.elimina(1);

        arbolAvl.elimina(3);

        arbolAvl.elimina(9);

        arbolAvl.elimina(21);

        arbolAvl.agrega(2);

        System.out.println(arbolAvl.toString());
        arbolAvl.bfs(t->{
            System.out.print(t+", ");
        });
    }
}