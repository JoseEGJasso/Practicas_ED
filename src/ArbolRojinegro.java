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
		if(v instanceof ArbolRojinegro.VerticeRojinegro){
			VerticeRojinegro vrj=(VerticeRojinegro)v;
  
			return vrj.color;
		}
		return null;
	}
  
	protected void setColor(Vertice v, Color c){
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
					if(estanCruzados(v)){
						casoCuatroRebalanceaAgrega(v);
					}
					casoCincoRebaanceaAgrega(v);
				}
			}
		}
  
	}
  
	///Auxiliar para buscar el T�o
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
  
			if(v.padre.izquierdo.elemento.equals(v.elemento))
				giraDerecha(v.padre.padre);
			else
				giraIzquierdo(v.padre.padre);
		}   
	}
  
	@Override
	public boolean elimina(T elemento){
		return true;
	}
  
	public static void main(String[] args) {
		Integer[] arbol={26,17,41,14,21,30,47,10,16,19,23,28,38,7,12,15,20,35,39,3};
		ArbolRojinegro<Integer> arbol1=new ArbolRojinegro<>(arbol);
  
		System.out.println(arbol1.toString());
	}
  }