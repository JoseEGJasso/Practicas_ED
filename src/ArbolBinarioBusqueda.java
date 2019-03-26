//IMPORTANTE. CUANDO SE TENGA LA VERSION FINAL ELIMINAR ESTE IMPORT, SOLO ES PARA PRUEBAS
import java.util.function.Consumer;
//IMPORTANTE. CUANDO SE TENGA LA VERSION FINAL ELIMINAR ESTE IMPORT, SOLO ES PARA PRUEBAS

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
        if(elemento!=null){
            if(raiz!=null){
                Cola<Vertice> procesar=new Cola<>();
                Vertice actual=raiz;

                procesar.mete(actual);

                while(!procesar.esVacia()){
                    if(actual.elemento.equals(elemento))
                        return true;

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
        }
        return false;
    }

    private Vertice mayorDerecho(Vertice u){
        Vertice actual=u.izquierdo;

        while(actual.derecho!=null){
            actual=actual.derecho;
        }

        return actual;
    }

    @Override
    public boolean elimina(T elemento){
        if(elemento!=null){
            if(raiz!=null){
                Cola<Vertice> procesar=new Cola<>();
                Vertice actual=raiz;

                procesar.mete(actual);

                while(!procesar.esVacia()){
                    if(actual.elemento.equals(elemento)){
                        if(actual.derecho==null && actual.izquierdo==null){
                            if(actual.padre!=null){
                                if(actual.padre.izquierdo!=null){
                                    if(actual.padre.izquierdo.elemento.equals(actual.elemento))
                                        actual.padre.izquierdo=null;
                                }else if(actual.padre.derecho!=null)
                                    actual.padre.derecho=null;
                                actual.padre=null;
                            }else
                                raiz=null;
                        } else if(actual.derecho!=null && actual.izquierdo==null){
                            if(actual.padre!=null){
                                actual.derecho.padre=actual.padre;

                                if(actual.padre.izquierdo!=null){
                                    if(actual.padre.izquierdo.elemento.equals(actual.elemento))
                                        actual.padre.izquierdo=actual.derecho;
                                }else if(actual.padre.derecho!=null)
                                    actual.padre.derecho=actual.derecho;            
                            } else{
                                actual.derecho.padre=null;
                                raiz=actual.derecho;
                            }
                        } else if(actual.izquierdo!=null && actual.derecho==null){
                            if(actual.padre!=null){
                                actual.izquierdo.padre=actual.padre;

                                if(actual.padre.izquierdo!=null){
                                    if(actual.padre.izquierdo.elemento.equals(actual.elemento))
                                        actual.padre.izquierdo=actual.derecho;
                                }else if(actual.padre.derecho!=null)
                                    actual.padre.derecho=actual.derecho;            
                            } else{
                                actual.izquierdo.padre=null;
                                raiz=actual.izquierdo;
                            }
                        } else{
                            Vertice intercambio=mayorDerecho(actual);
                            T auxEle=actual.elemento;

                            actual.elemento=intercambio.elemento;
                            intercambio.elemento=auxEle;

                            if(intercambio.izquierdo!=null){
                                intercambio.izquierdo.padre=intercambio.padre;

                                if(intercambio.padre.izquierdo.elemento.equals(intercambio.elemento))
                                    intercambio.padre.izquierdo=intercambio.izquierdo;
                                else
                                    intercambio.padre.derecho=intercambio.izquierdo; 
                            } else{

                                if(intercambio.padre.izquierdo.elemento.equals(intercambio.elemento))
                                    intercambio.padre.izquierdo=null;
                                else
                                    intercambio.padre.derecho=null; 
                            }
                        }                    
                        elementos--;
                        return true;
                    }

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
        }
        return false;
    }

    public static void main(String[] args) {
        Integer[] a={8,4,12,2,6,10,14,1,3,5,7,9,11,13,15};
        Consumer<Integer> l=t -> System.out.print(t+",");

        ArbolBinarioBusqueda<Integer> arbol=new ArbolBinarioBusqueda<>(a);
        arbol.bfs(l);
        System.out.println(arbol.elimina(4));
        arbol.dfs(1,l);
        System.out.println(arbol.elimina(8));
        arbol.dfs(2,l);
        System.out.println(arbol.elimina(10));
        arbol.dfs(3,l);
        System.out.println("\n"+arbol.getElementos());
    }
}