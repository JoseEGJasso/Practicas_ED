/**
 * Clase con varios métodos para ordenar arreglos y listas.
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

        for (T elemento : a) {
            System.out.print(elemento+",");
        }
        System.out.println();

        return i;
    }

    //regresa una lista
    public static <T extends Comparable<T>> Lista<T> mergeSort(Lista<T> l){

        Lista<T> lista1=new Lista<>();
        Lista<T> lista2=new Lista<>();

        if(l.getLongitud()==0 || l.getLongitud()==1)
            return l.copia();

        for(int i=0;i<(l.getLongitud()-1)/2;i++){
            lista1.agregaFinal(l.get(i));
        }

        for(int i=((l.getLongitud()-1)/2)+1;i<l.getLongitud();i++){
            lista2.agregaFinal(l.get(i));
        }

        lista1=mergeSort(lista1);
        lista2=mergeSort(lista2);
        //mezcla(lista1,lista2);

        return l;
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
        
    public static void main(String args[]){
        Integer[] arreglo={6,1,5,10,9,2,2,2,8,7};

        Ordenamientos.quickSort(arreglo,0,arreglo.length);

       // for (Integer e:arreglo) {
         //   System.out.print(e+",");
       // }

        System.out.println(Ordenamientos.busquedaBinaria(arreglo,7));
    }    
}
