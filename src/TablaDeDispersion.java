/**
 * Clase TablaDeDispersion que modela una Tabla Hash o Tabla de Dispersion. Las coliciones de la tabla se
 * manejan con encadenamiento utilizando listas.
 * @param <K>
 * @param <V>
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class TablaDeDispersion<K, V>{


    /**
     * Clase interna que modela los elementos de una TablaDeDispersion.
     * Tiene dos atributos, la llave K y el valor V, ambos genéricos.
     */
    private class Entrada{

      // Atributos de Entrada
	     public K llave;
       public V valor;

    /**
     * Constructor de un objeto entrada a partir de la llave y un valor.
     *
     * @param llave
     * @param valor
     */
	  public Entrada(K llave, V valor){
        this.llave=llave;
        this.valor=valor;
	     }
    }

    // Atributos de TablaDeDispersion
    private Lista<Entrada>[] tabla;
    private Dispersor<K> dispersor;
    private int elementos;
    public static final int CAPACIDAD_MINIMA = 64;
    private static final double CARGA_MAXIMA = 0.75;

    /**
     * Método privado que regresa un arreglo de listas de entradas a partir de un entero,
     * el cual será la dimensión de la Tabla de Disperión.
     *
     * @param tamano
     * @return nuevoArreglo
     *
     */
    private Lista<Entrada>[] nuevoArreglo(int tamano){
        @SuppressWarnings("unchecked")
        Lista<Entrada>[] arreglo = (Lista<Entrada>[]) new Lista[tamano];
        return arreglo;
    }

    /**
     * Constructor por defecto de una TablaDeDispersion , donde su variable tabla se construye a
     * partir de 128. El dispersor por defecto es obtener aplicar el método hasCode de la clase
     * Object a la llave de la entrada. I su inicialización de elementos es en 0.
     *
     */
    public TablaDeDispersion() {
        tabla=nuevoArreglo(128);
        dispersor=(K llave) -> llave.hashCode();
        elementos=0;

    }

    /**
     * Constructor de una TablaDeDispersion a partir de un entero que llamaremos capacidad. A la cual
     * le aplicaremos una formula y se guardará en la variable cap, que servirá para comparar con la
     * CAPACIDAD_MINIMA de la clase, y otros dos casos en los que tendremos que aumentar la cap para
     * poder modificar nuestra variable tabla utilizando el método nuevoArreglo. Finalmente empleamos
     * el dispersor por defecto como en el anterior constructor e inicializamos los elementos en 0.
     *
     * @param capacidad
     *
     */
    public TablaDeDispersion(int capacidad){
        double cap=(Math.log10((double)(capacidad*2))/Math.log10(2));

        if(capacidad<CAPACIDAD_MINIMA)
            tabla=nuevoArreglo(128);
        else if(cap-(int)cap>0.0)
            tabla=nuevoArreglo((int)(Math.pow(2.0,(int)cap+1)));
        else
            tabla=nuevoArreglo((int)(Math.pow(2.0,cap)));

        dispersor=(K llave) -> llave.hashCode();

        elementos=0;
    }

    /**
     * Constructor de una TablaDeDispersion a partir de un Dispersor. Los atributos de tabla y elementos
     * son los que están por defecto.
     *
     * @param dispersor
     */
    public TablaDeDispersion(Dispersor<K> dispersor){
        tabla=nuevoArreglo(128);
        this.dispersor=dispersor;
        elementos=0;
    }

    /**
     * Constructor de una TablaDeDispersion a partir de la capacidad, y de un Dispersor. Asignamos el
     * dispersor al objeto y despues revisamos los casos para la capacidad de la tabla, como lo hemos
     * hecho en el constructor a partir de la capacidad.
     *
     */
    public TablaDeDispersion(int capacidad, Dispersor<K> dispersor){
        this.dispersor=dispersor;

        double cap=(int)(Math.log10((double)(capacidad*2))/Math.log10(2));

        if(cap-(int)cap>0.0)
            tabla=nuevoArreglo((int)cap+1);
        else
            tabla=nuevoArreglo((int)cap);

        elementos=0;
    }

    /**
     * Método vacío para modelar la operación de agregar en una TablaDeDispersion. Recibe la llave K
     * y el valor V. Primero se realiza la verificación de que ambos sean distintos de nullo para continuar.
     * creamos una variable pos (posicion) que será la posicion del valor V en la tabla después de aplicarle
     * el dispersor a la llave.
     * En el primer if verificamos si el elemento en el índice pos es igual a null (i.e. si no hay una lista de Entradas),
     * si lo es, creamos un objeto Entrada a partir de la lleve K y el valor V que el método agrega recibió, y lo llamaremos
     * nuevaEntrada. Creamos una lista de entradas en el ínidce pos, agregamos nuevaEntrada s la lista y finalmente
     * incrementamos elementos.
     * En el segundo if (es el caso contrario al primero), recorremos la lista de Entradas en el ínidce pos,
     * comparando las llaves de las Entradas con la llave recibida por agrega, si las llaves son la misma
     * entonces el valor de la Entrada actual  es igualado al valor recibido y regresa. Si las llaves no coinciden
     * entonces creamos una Entrada llamada nuevaEntrada, como en el caso anterior, y lo agregamos a la lista en el
     * índice pos.
     * El tercer if es para verificar que, después de que el elemento se haya agregado,el factor de carga no haya superado
     * o igualado a CARGA_MAXIMA.Si lo supera entonces, en resumen, modificaremos las variables de TablaDeDispersion para
     * incrementar su tamaño, para esto duplicamos la longitud de nuestra anterior tabla por 2.Además de pasar todos los elementos
     * a la nueva tabla.
     *
     * @param llave
     * @param valor
     *
     */
    public void agrega(K llave, V valor){
        if(llave==null || valor==null)
            return;

        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]==null){

            Entrada nuevaEntrada=new Entrada(llave,valor);

            tabla[pos]=new Lista<Entrada>();
            tabla[pos].agregaFinal(nuevaEntrada);
            elementos++;

        }else if(tabla[pos]!=null){

            for(Entrada e:tabla[pos]){
                if(e.llave==llave){
                    e.valor=valor;
                    return;
                }
            }

            Entrada nuevaEntrada=new Entrada(llave,valor);

            tabla[pos].agregaFinal(nuevaEntrada);
            elementos++;
        }

        if((elementos/tabla.length)>=CARGA_MAXIMA){

            Lista<Entrada>[] nuevo=nuevoArreglo(tabla.length*2);
            Lista<Entrada> contenedor=new Lista<>();

            for(Lista<Entrada> e:tabla){
                if(e!=null){
                    for(Entrada f:e)
                        contenedor.agregaFinal(f);
                }
            }

            tabla=nuevo;

            elementos=0;

            for(Entrada e : contenedor)
                agrega(e.llave,e.valor);

        }

    }

    /**
     * Método getter que regresa el valor de un elemento Entrada a partir de la llave del elemento.
     * El primer if es una verificación para que la llave sea distinta de null.
     * Después obtenemos el índice en la tabla  a partir de la llave y lo guardamos en la variable pos.
     * Se verifica que la lista en la posicion sea distinta de null para poder continuar y a continuación
     * se recorre la lista comparando llaves y finalmente, cuando la llave recibida coincide con la de
     * una Entrada de la lista, se regresa el valor de esta Entrada.
     *
     * @param llave
     * @return V
     */
    public V getValor(K llave){
        if(llave==null)
            return null;

        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]!=null){
            for(Entrada e:tabla[pos]){
                if(e.llave.equals(llave))
                    return e.valor;
            }
        }

        return null;
    }

    /**
     * Método que recibe una llave y retorna un boolean: true- si la llave  se encuentra en la tabla y
     * false-en cualquier otro caso. Es, en resumen, una iteración sobre la lista en el ínidce pos de la tabla
     * que regresa true si localiza la llave y false e cualquier otro caso.
     *
     * @param llave
     * @return boolean
     */
    public boolean contieneLlave(K llave){
        if(llave==null)
            return false;

        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]==null)
            return false;

        for(Entrada e:tabla[pos]){
            if(e.llave.equals(llave))
                return true;
        }

        return false;
    }

    /**
     * Método que recibe un valor y retorna un boolean: true-si el valor coincide con algun valor de una Entrada
     * en la tabla y false-en cualquier otro caso.En resumen, se recorren los ínidices de la tabla y después se recorre
     * cada lista distinta de null hasta que coincidan los valores.
     *
     * @param valor
     * @return boolean
     */
    public boolean contieneValor(V valor){
        if (valor == null)
            return false;

        for(Lista<Entrada> e: tabla){
            if(e==null)
                continue;
            for(Entrada o:e){
                if(o.valor==valor)
                    return true;
            }
        }

        return false;
    }

    /**
     * Método que regresa un boolean: true- si la tabla es vacía y false-en otro caso.
     *
     * @return boolean
     */
    public boolean esVacia(){
        if(elementos==0)
            return true;
        return false;
    }

    /**
     * Método que recibe una llave y retorna el valor de la Entrada eliminada.
     * En primer lugar, se verifica que la llave no sea nula. Después de localiza el índice en la tabla para
     * que a continuación, se recorra la lista en dicha posicion. Cunado la llave recibida sea igual a la llave
     * de algún elemento en esa lista, simplemente se elimina la entrada y se decrementa la varibale elementos.
     *
     * @param llave
     * @return V
     */
    public V elimina(K llave){
        if(llave==null)
            return null;

        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]==null)
            return null;

        for(Entrada e:tabla[pos]){
            if(e.llave==llave){
                tabla[pos].elimina(e);
                elementos--;
                return e.valor;
            }
        }

        if(tabla[pos].getLongitud()==0)
            tabla[pos]=null;

        return null;
    }

    /**
     * Método getter para obtener el atributo elementos de una TablaDeDispersion.
     *
     * @return elementos
     */
    public int getElementos(){
        return elementos;
    }

    /**
     * Método que regresa una lista de llaves K de la TablaDeDispersion. Recorriendo la tabla
     * y recorriendo las listas, va agregando a una lista de llaves llamada "llaves" las llaves
     * de cada entrada y finalmente la retorna.
     *
     * @return llaves
     */
    public Lista<K> getLlaves(){
        if(this.esVacia()){
            return null;
        }

        Lista<K> llaves=new Lista<>();

        for(Lista<Entrada> e: tabla){
            if(e!=null){
                for(Entrada o:e)
                    llaves.agregaFinal(o.llave);
            }
        }

        return llaves;
    }

    /**
     * Método que regresa una lista de valores V de la TablaDeDispersion. Recorriendo la tabla
     * y recorriendo las listas, va agregando a una lista de valores llamada "valores" los valores
     * de cada entrada y finalmente lo retorna.
     *
     * @return valores
     */
    public Lista<V> getValores(){
        if(this.esVacia())
            return null;

        Lista<V> valores= new Lista<>();

        for (Lista<Entrada> e : tabla) {
            if (e != null) {
                for (Entrada o : e)
                    valores.agregaFinal(o.valor);
            }
        }

        return valores;
    }

    /**
     * Método getter que regresa la capacidad de una TablaDeDispersion(i.e. el tamaño del arreglo tabla).
     *
     * @return tabla.length
     */
    public int getCapacidad(){
        return tabla.length;
    }

    /**
     *
     *
     *
     */
    public void longitudHash(){
        for(Lista<Entrada> e:tabla){
            if(e!=null)
                System.out.print(e.getLongitud()+", ");
            else
                System.out.print("null"+", ");
        }

        System.out.println();
    }

}
