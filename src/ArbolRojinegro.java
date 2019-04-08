import ArbolBinario.Vertice;

/**
 * Implementaci�n de �rboles rojinegros.
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{

    protected class VerticeRojinegro extends Vertice{
	
        public VerticeRojinegro(T elemento){
            super(elemento);
            color=Color.ROJO;
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
        } else{
            if(getColor(v.padre) != Color.NEGRO){ 
                //Condicion para entrar a los otros casos, es decir cuando su padre es rojo.
                if(buscaTio(v)!=null){
                    if(getColor(buscaTio(v))==Color.ROJO){
                        casoTresRebalanceaAgrega(v);
                        return;
                    }
                }
                if(estanCruzados(v)){
                    casoCuatroRebalanceaAgrega(v);
                }
                casoCincoRebaanceaAgrega(v);
            }
        }

    }

    private Vertice buscaTio(Vertice v){

        if(v.padre.padre!=null){
            if(v.padre.padre.derecho!=null){
                if(v.padre.elemento.equals(v.padre.padre.derecho.elemento)){
                    if(v.padre.padre.izquierdo != null)
                      return v.padre.padre.izquierdo;
                }
            }

            if(v.padre.padre.izquierdo!=null){
                if(v.padre.elemento.equals(v.padre.padre.izquierdo.elemento)){
                    if(v.padre.padre.derecho != null)
                      return v.padre.padre.derecho;
                }
            }
        }

        return null;
    }

    private Vertice buscaHermano(Vertice v){
        if(v.padre!=null){
            if(v.padre.izquierdo!=null){
                if (v.padre.izquierdo.elemento.equals(v.elemento)){
                    if (v.padre.derecho!=null)
                        return v.padre.derecho;
                }
            }

            if (v.padre.derecho!=null) {
                if(v.padre.derecho.elemento.equals(v.elemento)){
                    return v.padre.izquierdo;
                }
            }
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
        if(v.padre.padre.izquierdo!=null){
            if(v.padre.elemento.equals(v.padre.padre.izquierdo.elemento)){
                this.giraIzquierdo(v.padre);
            } else{
                if(v.padre.padre.derecho!=null)
                    this.giraDerecha(v.padre);
            }
        }
    }

    private boolean estanCruzados(Vertice v){
        if(v.padre.padre!=null){
            if(v.padre.padre.derecho!=null){
                if(v.padre.elemento.equals(v.padre.padre.derecho.elemento)){
                    if(v.padre.izquierdo!=null){
                        if(v.padre.izquierdo.elemento.equals(v.elemento))
                            return true;
                    }
                }
            }

            if(v.padre.padre.izquierdo!=null){
                if(v.padre.elemento.equals(v.padre.padre.izquierdo.elemento)){
                    if(v.padre.derecho!=null){
                        if(v.padre.derecho.elemento.equals(v.elemento))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private void casoCincoRebaanceaAgrega(Vertice v){
        setColor(v.padre,Color.NEGRO);

        if(v.padre.padre!=null){
            setColor(v.padre.padre,Color.ROJO);

            if(v.padre.izquierdo!=null){
                if(v.padre.izquierdo.elemento.equals(v.elemento))
                    giraDerecha(v.padre.padre);
            }else
                giraIzquierdo(v.padre.padre);
        }   
    }

    @Override
    //esta mal
    public boolean elimina(T elemento){
        if (elemento == null)
            return false;

        if (raiz == null)
            return false;

        if (buscaVertice(elemento) == null) {
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
            Vertice fantasma=new VerticeRojinegro(null);

            fantasma.padre=actual;
            actual.izquierdo=fantasma;
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

        if(getColor(actual)==Color.ROJO)
            return true;

        if (actual.izquierdo!=null) {
            if (getColor(actual.izquierdo)==Color.ROJO)
                setColor(actual.izquierdo, Color.NEGRO);
            return true;
        } if(actual.derecho!=null){
            if (getColor(actual.derecho)==Color.ROJO)
                setColor(actual.derecho, Color.NEGRO);
            return true;
        }

        if (getColor(actual)==Color.NEGRO) {
            if(actual.derecho!=null){
                if(getColor(actual.derecho)==Color.NEGRO)
                    rebalanceaEliminar(actual.derecho);
            }else{
                if(getColor(actual.izquierdo)==Color.NEGRO)
                    rebalanceaEliminar(actual.izquierdo);
            }
        }

        // if(actual.derecho!=null){
        //     if(actual.derecho.elemento.equals(null)){
        //         actual.derecho.padre=null;
        //     }
                
        // }

        return true;
    }

    private void rebalanceaEliminar(Vertice u){
        if(u.padre==null)
            return;

        if(getColor(buscaHermano(u))==Color.ROJO)
            casoDosRebalanceaElimina(u);

        if(getColor(u.padre)==Color.NEGRO && getColor(buscaHermano(u))==Color.NEGRO){
            if (getColor(buscaHermano(u).derecho)==Color.NEGRO && getColor(buscarHermano(u).izquierdo==Color.NEGRO))
                casoTresRebalanceaElimina(u);
        } 
    }

    private void casoDosRebalanceaElimina(Vertice u){
        setColor(u.padre, Color.ROJO);

        setColor(buscaHermano(u), Color.NEGRO);

        if (u.elemento.equals(u.padre.izquierdo.elemento))
            this.giraIzquierdo(u.padre);
        else
            this.giraDerecha(u.padre);
        
        //Falta actualizar el hermano para que vuelva  aser el hermano de v|    
    }


    public static void main(String[] args) {
        Integer[] arbol={15,20,28,26};
        ArbolRojinegro<Integer> arbol1=new ArbolRojinegro<>(arbol);

        System.out.println(arbol1.toString());
        arbol1.bfs(t->{
            System.out.print(t+", ");
        });
    }
}