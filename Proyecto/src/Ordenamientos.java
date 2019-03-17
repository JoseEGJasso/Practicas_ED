package proyecto1;

import java.util.Iterator;
/**
 * @author Jose Jasso
 * @author Diego Dozal
 * Clase con varios métodos para ordenar arreglos y listas.
 *
 */
public class Ordenamientos{

    public static <T extends Comparable<T>> void quickSort(T[] a){
        quickSort(a,0,a.length);
    }

    private static <T extends Comparable<T>> void quickSort(T[] a,int inicio,int fin){
        if (fin<=inicio)
            return;
        int indicePivote=particion(a,inicio,fin);
        quickSort(a,inicio,indicePivote);
        quickSort(a,indicePivote+1,fin);
    }
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

    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> l){

      Lista<T> l1 = new Lista<T>();
      Lista<T> l2 = new Lista<T>();

      if(l.getLongitud() == 0 || l.getLongitud() == 1)
           return l.copia();


     for(int i=0;i<l.getLongitud()/2;i++){
        l1.agregaFinal(l.get(i));
      }


      for(int j=l.getLongitud()/2;j<l.getLongitud();j++){
        l2.agregaFinal(l.get(j));
      }

      l1 = mergeSort(l1);
      l2 = mergeSort(l2);

      return merge(l1,l2);
    }

    private static <T extends Comparable<T>> Lista<T> merge(Lista<T> l,Lista<T> L){

    Lista<T> listaAuxiliar = new Lista<T>();

    Iterator<T> iter1 = l.iterator();
    Iterator<T> iter2 = L.iterator();

    T aux1 = null;
    T aux2 = null;


    while(iter1.hasNext() && iter2.hasNext()){

      if(aux1 == null)
       aux1=iter1.next();
      if(aux2 == null)
       aux2=iter2.next();

      if(aux1.compareTo(aux2) <= 0){
        listaAuxiliar.agregaFinal(aux1);
        aux1 = null;
       }
      else{
        listaAuxiliar.agregaFinal(aux2);
        aux2 =null;
       }

    }

   if(!iter1.hasNext()){
     listaAuxiliar.agregaFinal(aux2);
   }
   if(!iter2.hasNext()){
     listaAuxiliar.agregaFinal(aux1);
   }

   while(iter1.hasNext()){

     aux1=iter1.next();
     listaAuxiliar.agregaFinal(aux1);
    }

    while(iter2.hasNext()){

     aux2=iter2.next();
     listaAuxiliar.agregaFinal(aux2);
    }

    return listaAuxiliar;
   }

    public static <T extends Comparable<T>> int busquedaBinaria(T[] a, T elemento){
        return busquedaBinaria(a,0,a.length,elemento);
    }

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
}
