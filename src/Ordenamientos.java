import java.util.Iterator;


/**
 * Clase con varios métodos para ordenar arreglos y listas.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Ordenamientos{

    /**
     * Método auxiliar que llama al método quickSort recursivo.
     *
     * @param a; un arreglo de tipo genérico que será ordenado
     *
     */
    public static <T extends Comparable<T>> void quickSort(T[] a){
        quickSort(a,0,a.length);
    }
    /**
     * Implementacion del algoritmo de ordenamiento quickSort
     * recursivamente. Recibe tre parámentros : arreglo a ordenar
     * el inicio y fin. Los dos anteriores parámetros sirven para
     * establecer el pivote y asimiismo, llama al método auxiliar
     * particion.
     *
     * @param a,inicio,fin;
     */
    private static <T extends Comparable<T>> void quickSort(T[] a,int inicio,int fin){
        if (fin<=inicio)
            return;
        int indicePivote=particion(a,inicio,fin);
        quickSort(a,inicio,indicePivote);
        quickSort(a,indicePivote+1,fin);
    }

    /**
     * Método auxiliar para quickSort. Sirve para establecer la particion
     * que se considerará al momento de determinar el pivote en el arreglo
     * a ordenar.
     *
     * @param a,inicio,fin;
     * @return i; el índice del pivote en el arreglo
     */
    private static <T extends Comparable<T>> int particion(T[] a,int inicio,int fin){
        T pivote=(T)a[inicio];
        T aux=null;
        int i=inicio;

        for (int j=inicio+1;j<fin;j++) {
            if (a[j].compareTo(pivote)<=0) {
                aux=(T)a[++i];
                a[i]=a[j];
                a[j]=aux;
            }
        }
        a[inicio]=a[i];
        a[i]=pivote;

        return i;
    }

    /**
      * Implementacion del algoritmo de ordenamiento mergeSort recursivamente
      * Recibe una lista como parámetro para después partirla en listas de longitud
      * 1 ó 0. Después utiliza el método auxiliar merge para ordenar las listas y finalmente
      * retornar la lista ordenada.
      *
      * @param l; lista a ordenar
      * @return merge(l1,l2); lista ordenada con ayuda del método merge
      */
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> l){


           Lista<T> derecha = new Lista<>();
           Lista<T> izquierda = new Lista<>();

          if(l.getLongitud() < 2 ){
             return l.copia();
          }

          if(l.getLongitud() < 2 ){
             return l.copia();
          }

          int mitad = l.getLongitud()/2;
          int contador=0;

         for(T elemento: l){

           if(contador < mitad){
             izquierda.agregaFinal(elemento);
           }else{
             derecha.agregaFinal(elemento);
           }

           contador++;
          }

          derecha = mergeSort(derecha);
          izquierda = mergeSort(izquierda);

          return Ordenamientos.merge(derecha,izquierda);

    }

    /**
     * Implementacion del método auxiliar merge que "mezcla" los elementos
     * de dos listas ordenadas para después, retornar la lista ordenada a
     * mergeSort.
     *
     * @param l,L; Dos listas ordenadas
     * @return listaAuxiliar. La lista ordenada
     */
     private static <T extends Comparable<T>> Lista<T> merge(Lista<T> l,Lista<T> L){

     Lista<T> listaAuxiliar = new Lista<T>();

     int indexOfl=0; //índice de lista l
     int indexOfL=0; //índice de lista L

     int longOfl = l.getLongitud();
     int longOfL = L.getLongitud();

      if(longOfl > longOfL){

       while(indexOfl < longOfl){
         if(l.get(indexOfl).compareTo(L.get(indexOfL)) >= 0){
           listaAuxiliar.agregaFinal(L.get(indexOfL));
           indexOfL++;
         }
         else{
           listaAuxiliar.agregaFinal(l.get(indexOfl));
           indexOfl++;
         }
       }

      int restOfL = indexOfL;
      while(restOfL < longOfL){
        listaAuxiliar.agregaFinal(L.get(restOfL));
        restOfL++;
        }
      }
      else{
        while(indexOfL < longOfL){
          if(L.get(indexOfL).compareTo(l.get(indexOfl)) >= 0){
            listaAuxiliar.agregaFinal(l.get(indexOfl));
            indexOfl++;
          }
          else{
            listaAuxiliar.agregaFinal(L.get(indexOfL));
            indexOfL++;
          }
        }

        int restOfl = indexOfl;
        while(restOfl < longOfl){
         listaAuxiliar.agregaFinal(l.get(restOfl));
         restOfl++;
       }
     }

      return listaAuxiliar;

    }
    /**
     * Método que busca un elemento dentro de un arreglo. Si el elemento se encuentra
     * dentro del arreglo regresa un int que representa su posicion en el arreglo, en
     * caso de que el elemento no exista dentro del arreglo regresa un -1
     * @param a;
     * @param elemento;
     * @return int;
     */
    public static <T extends Comparable<T>> int busquedaBinaria(T[] a, T elemento){
        return busquedaBinaria(a,0,a.length,elemento);
    }
    /**
     * Método auxiliar de busquedaBinaria que realiza las particiones del arreglo recursivamente
     * hasta encontrar o no enconntrar el elemento buscado
     * @param a;
     * @param inicio;
     * @param fin;
     * @param elemento;
     * @return int;
     */
    private static <T extends Comparable<T>> int busquedaBinaria(T[] a,int inicio,int fin, T elemento){
        if(fin>inicio){
            int medio=(inicio+fin)/2;
            T elementoMedio=a[medio];

            if(elemento.compareTo(elementoMedio)==0)
                return medio;
            else if(elemento.compareTo(elementoMedio)<0){
                return busquedaBinaria(a,inicio,medio-1,elemento);
            } else if(elemento.compareTo(elementoMedio)>0){
                return busquedaBinaria(a,medio+1,fin,elemento);
            }
        }
        return -1;
    }

    /**
     * Implementacion del algoritmo de ordenamiento bubbleSort
     * ordena el arreglo "encapsulando" el elemento mayor hacia
     * la derecha del arreglo.
     *
     * @param a; arreglo a ordenar
     */
    public static <T extends Comparable<T>> void bubbleSort(T[] a){
        int longitud=a.length;

        for(int j=0;j<a.length;j++){
            for(int i=0;i<longitud;i++){
                if(i==a.length-1)
                    break;
                if(a[i].compareTo(a[i+1])>=0){
                    T aux=(T) a[i];

                    a[i]=a[i+1];
                    a[i+1]=aux;
                }
            }
            longitud--;
        }
    }

    /**
     * Implementacion del algoritmo de ordenamiento selectionSort.
     * Se establece un mínimo en el arreglo de entre todos los elementos
     * de éste, después se establece otro mínimo que será comparado se
     * itera este proceso hasta que el arreglo quede ordenado.
     *
     * @param a; arreglo a ordenar
     */
    public static <T extends Comparable<T>> void selectionSort(T[] a){
        int min=0;
        T aux=null;

        for (int j=0;j<a.length;j++) {
            min=j;
            if(j==a.length-1)
                break;
            for(int i=j;i<a.length;i++){
                if(a[j].compareTo(a[i])>0)
                    min=i;
            }
            aux=(T)a[min];
            a[min]=a[j];
            a[j]=aux;
        }
    }

    /**
     * Método que calcula el tiempo de ejecución del algoritmo
     * Selection Sort. Si la longitud del arreglo recibido es menor
     * o igual a 10000, calcula el tiempo de ejecución en milisegundos,
     * en caso contrario en segundos.
     * @param a; arreglo a ordenar
     * @return long; representa el tiempo de ejecución calculado
     */
    public static <T extends Comparable<T>> long tiempoSelectionSort(T[] a){
        long ahora=System.currentTimeMillis();

        selectionSort(a);

        long despues=System.currentTimeMillis();

        if(a.length<=10000)
            return (despues-ahora);

        return (despues-ahora)/1000;
    }


    /**
     * Método que calcula el tiempo de ejecución del algoritmo
     * Bubble Sort. Si la longitud del arreglo recibido es menor
     * o igual a 10000, calcula el tiempo de ejecución en milisegundos,
     * en caso contrario en segundos.
     * @param a; arreglo a ordenar
     * @return long; representa el tiempo de ejecución calculado
     */
    public static <T extends Comparable<T>> long tiempoBubbleSort(T[] a){
        long ahora=System.currentTimeMillis();

        bubbleSort(a);

        long despues=System.currentTimeMillis();

        if(a.length<=10000)
            return (despues-ahora);

        return (despues-ahora)/1000;
    }

    /**
     * Método que calcula el tiempo de ejecución del algoritmo
     * quickSort. Si la longitud del arreglo recibido es menor
     * o igual a 10000, calcula el tiempo de ejecución en milisegundos,
     * en caso contrario en segundos.
     * @param a; arreglo a ordenar
     * @return long; representa el tiempo de ejecución calculado
     */
    public static <T extends Comparable<T>> long tiempoQuickSort(T[] a){
        long ahora=System.currentTimeMillis();

        quickSort(a);

        long despues=System.currentTimeMillis();

        if(a.length<=10000)
            return (despues-ahora);

        return (despues-ahora);
    }

    /**
     * Método que calcula el tiempo de ejecución del algoritmo
     * busquedaBinaria. Si la longitud del arreglo recibido es menor
     * o igual a 10000, calcula el tiempo de ejecución en milisegundos,
     * en caso contrario en segundos.
     * @param a; arreglo a ordenar
     * @return long; representa el tiempo de ejecución calculado
     */
    public static <T extends Comparable<T>> long tiempoBusquedaBinaria(T[] a, T elemento){
      long ahora=System.currentTimeMillis();

      busquedaBinaria(a,elemento);

      long despues=System.currentTimeMillis();

      if(a.length<=10000)
        return (despues-ahora);

      return(despues-ahora);
    }

    /**
     * Método auxiliar que genera y regresa un número aleatorio
     * contenido en el intervalo [0,999999].
     *
     * @return (int)(Math.random()*999999);
     */
    public static int numerosIntRandom(){

        return (int)(Math.random()*999999);
    }

    /**
     * Implementacion de método auxiliar que genera un arreglo de dimensión
     * "n" de números enteros aleatorios utilizando el método auxiliar
     *
     * @param n;
     * @return arreglo;
     */
    private static Integer[] generaArreglosInteger(int n){
        Integer[] arreglo=new Integer[n];

        for (int i=0;i<arreglo.length;i++) {
            arreglo[i]=numerosIntRandom();
        }

        return arreglo;
    }
    /**
     * Método auxiiar que genera arreglos de tipo entero ordenados
     * @param n;
     * @return Integer[];
     */
    private static Integer[] generaArreglosIntegerOrdenado(int n){

      Integer[] arreglo=new Integer[n];

      for(int i=0;i<arreglo.length;i++){
        arreglo[i]=i+1;
      }

      return arreglo;
    }

    public static void main(String args[]){
        Integer[] arreglo={6,1,5,10,9,2,2,2,8,7};


        System.out.println("SELECTION SORT.(tiempo calculado en segundos para # elementos > 10000 y en milisegundos para <=10000)");

        System.out.println("Tiempo de ejecución con 100 elementos: "+Ordenamientos.tiempoSelectionSort(Ordenamientos.generaArreglosInteger(100)));

        System.out.println("Tiempo de ejecución con 1000 elementos: "+Ordenamientos.tiempoSelectionSort(Ordenamientos.generaArreglosInteger(1000)));

        System.out.println("Tiempo de ejecución con 10000 elementos: "+Ordenamientos.tiempoSelectionSort(Ordenamientos.generaArreglosInteger(10000)));

        System.out.println("Tiempo de ejecución con 100000 elementos: "+Ordenamientos.tiempoSelectionSort(Ordenamientos.generaArreglosInteger(100000)));

        //RECOMENDACION: No ejecutar el tiempo de ejecución con 300000 elementos, llega a durar más de 5 min.
        //System.out.println("Tiempo de ejecución con 300000 elementos: "+Ordenamientos.tiempoSelectionSort(Ordenamientos.generaArreglosInteger(300000)));

        System.out.println("BUBBLE SORT.(tiempo calculado en segundos para # elementos > 10000 y en milisegundos para <=10000");

        System.out.println("Tiempo de ejecución con 100 elementos: "+Ordenamientos.tiempoBubbleSort(Ordenamientos.generaArreglosInteger(100)));

        System.out.println("Tiempo de ejecución con 1000 elementos: "+Ordenamientos.tiempoBubbleSort(Ordenamientos.generaArreglosInteger(1000)));

        System.out.println("Tiempo de ejecución con 10000 elementos: "+Ordenamientos.tiempoBubbleSort(Ordenamientos.generaArreglosInteger(10000)));

        //RECOMENDACION: No ejecutar la siguiente linea, pues llega a tardar más 10 min.
        //System.out.println("Tiempo de ejecución con 100000 elementos: "+Ordenamientos.tiempoBubbleSort(Ordenamientos.generaArreglosInteger(100000)));

        //RECOMENDACION: No ejecutar la siguiente linea, pues llega a tardar hasta 18 horas.
        //System.out.println("Tiempo de ejecución con 300000 elementos: "+Ordenamientos.tiempoBubbleSort(Ordenamientos.generaArreglosInteger(300000)));


        System.out.println("QUICK SORT.(tiempo calculado en segundos para # elementos > 10000 y en milisegundos para <=10000");

        System.out.println("Tiempo de ejecución con 100 elementos: "+Ordenamientos.tiempoQuickSort(Ordenamientos.generaArreglosInteger(1000)));

        System.out.println("Tiempo de ejecución con 1000 elementos: "+Ordenamientos.tiempoQuickSort(Ordenamientos.generaArreglosInteger(1000)));

        System.out.println("Tiempo de ejecución con 10000 elementos: "+Ordenamientos.tiempoQuickSort(Ordenamientos.generaArreglosInteger(10000)));

        System.out.println("Tiempo de ejecución con 100000 elementos: "+Ordenamientos.tiempoQuickSort(Ordenamientos.generaArreglosInteger(100000)));

        System.out.println("Tiempo de ejecución con 300000 elementos: "+Ordenamientos.tiempoQuickSort(Ordenamientos.generaArreglosInteger(300000)));

        
        System.out.println("BUSQUEDA BINARIA.(tiempo calculado en segundos para # elementos > 10000 y en milisegundos para <=10000)");

        System.out.println("Tiempo de ejecución con 100 elementos: "+Ordenamientos.tiempoBusquedaBinaria(Ordenamientos.generaArreglosIntegerOrdenado(100),90));

        System.out.println("Tiempo de ejecución con 1000 elementos: "+Ordenamientos.tiempoBusquedaBinaria(Ordenamientos.generaArreglosIntegerOrdenado(1000),900));

        System.out.println("Tiempo de ejecución con 10000 elementos: "+Ordenamientos.tiempoBusquedaBinaria(Ordenamientos.generaArreglosIntegerOrdenado(10000),9000));

        System.out.println("Tiempo de ejecución con 100000 elementos: "+Ordenamientos.tiempoBusquedaBinaria(Ordenamientos.generaArreglosIntegerOrdenado(100000),90000));

        System.out.println("Tiempo de ejecución con 300000 elementos: "+Ordenamientos.tiempoBusquedaBinaria(Ordenamientos.generaArreglosIntegerOrdenado(300000),290000));

    }
}
