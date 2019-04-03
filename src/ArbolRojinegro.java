
/**
 * Implementación de árboles rojinegros.
 */
public class ArbolRojinegro<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>{

    protected class VerticeRojinegro extends Vertice{

	public VerticeRojinegro(T elemento){
	    // Aquí va su código.
	}

	public Color color;

    }

    public ArbolRojinegro(){
	// Aquí va su código.
    }

    public ArbolRojinegro(T[] a){
	// Aquí va su código.
    }

    protected Color getColor(Vertice v){
	// Aquí va su código.
    }

    protected void setColor(Vertice v, Color c){
	// Aquí va su código.
    }



    @Override
    public void agrega(T elemento){
	// Aquí va su código.
    }

    public void rebalanceaAgregar(VerticeRojinegro v){

      /*if(v.padre = v.padre.padre.derecho){ //Es para designar el tío
        if( v.padre.padre.izquierdo != null){
          Vertice tio = v.padre.padre.izquierdo;
        }
      }

      if(v.padre = v.padre.padre.izquierdo){
        if()
      }*/

      if(v.padre == null){
        setColor(v,Color.NEGRO);
        v = raiz;
      } else{
        if(getColor(v.padre) != Color.NEGRO){ //Condicion para entrar a los otros casos, es decir cuando su padre es rojo.
          ///Preguntar sobre el tio y
          if(getColor(buscaTio(v)) == Color.ROJO){
            casoTresRebalanceaAgrega(v);
          }
        }
      }





    }

    ///Auxiliar para buscar el Tío
    private VerticeRojinegro buscaTio(VerticeRojinegro v){

      if(v.padre.elemento.equals(v.padre.padre.izquierdo.elemento)){
        if(v.padre.padre.derecho != null)
          return v.padre.padre.derecho
      }

      if(v.padre.elemento.equals(v.padre.padre.derecho.elemento)){
        if(v.padre.padre.izquierdo != null)
          return v.padre.padre.izquierdo
      }

      return null;

    }

    ///CASO TRES DE REBALANCEA
    private void casoTresRebalanceaAgrega(VerticeRojinegro v){

       if(v == v.padre.izquierdo || v = v.padre.derecho){ //Esto es necesario??

         if(buscaTio(v).elemento.equals(v.padre.padre.derecho.elemento)){



            }

          }
          if(v.padre = v.padre.padre.izquierdo){
            setColor(v.padre, Color.NEGRO);
            if( v.padre.padre.derecho == null){ //Vale la pena ?
              Vertice tio = v.padre.padre.derecho;
              setColor(tio,Color.NEGRO);
            }
          }
          setColor(v.padre.padre, Color.ROJO);
          rebalanceaAgregar(v.padre.padre);
        }
    }

    @Override
    public boolean elimina(T elemento){
	// Aquí va su código.
    }
}
