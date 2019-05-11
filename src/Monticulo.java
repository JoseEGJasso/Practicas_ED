
import java.util.Comparator;


/**
 * Clase que implementa montículos mínimos y montículos máximos con enteros.
 * @author Dozal Magnani Diego
 * @author González Jasso José Eduardo
 */
public class Monticulo{

    private int[] elementos;
    private Comparator<Integer> comparador;
    private int ultimoIndice;
    
    /**
     * Constructor de un montículo vacío. Se utiliza como comparador el orden
     * natural de enteros
     */
    public Monticulo(){
        ultimoIndice=-1;
        elementos=new int[100];
        comparador=new Comparator<Integer>() {
            @Override
            public int compare(Integer a,Integer b){
                return Integer.compare(a, b);
            };
        };
    }
    /**
     * Constructor de un montículo que recibe el comparador a usar para la
     * implementacion
     * @param comparador
     */
    public Monticulo(Comparator<Integer> comparador){
        ultimoIndice=-1;
        elementos=new int[100];
        this.comparador=comparador;
    }
    /**
     * Constructor de un montículo en base al arreglo recibido
     * @param elementos
     */
    public Monticulo(int[] elementos){
        ultimoIndice=-1;
        comparador=new Comparator<Integer>() {
            @Override
            public int compare(Integer a,Integer b){
                return Integer.compare(a, b);
            };
        };

        if(elementos.length>0){
            this.elementos=new int[elementos.length];

            for(int i=0;i<elementos.length;i++){
                this.elementos[i]=elementos[i];
                ultimoIndice++;
            }


            for (int i = (elementos.length / 2) - 1; i >= 0; i--)
                acomodaAbajo(i);
            
        }
    }
    /**
     * Constructor de un montículo en base a un arreglo, utilizando el comparador ingresado
     * @param elementos
     * @param comparador
     */
    public Monticulo(int[] elementos, Comparator<Integer> comparador){
        this.comparador=comparador;

        if(elementos.length>0){
            this.elementos=new int[elementos.length];

            this.comparador=comparador;

            for(int i=0;i<elementos.length;i++)
                this.elementos[i]=elementos[i];
            
            for (int i = (elementos.length/2)-1; i < 0; i++)
                acomodaAbajo(i);                
        }
    }
    /**
     * Método que elimina el elemento de mayor prioridad en el montículo
     * @return int; regresa el elemento eliminado
     */
    public int elimina(){
        if(this.esVacio())
            throw new IllegalStateException();
        
        System.out.println("ultimo indice: "+ultimoIndice);            

        swap(0, ultimoIndice);

        ultimoIndice--;

        acomodaAbajo(0);

        if(ultimoIndice==-1)
            return elementos[0];

        return elementos[ultimoIndice+1];
    }
    /**
     * Método que recorre el mónticulo y verifica si existe el elementos ingresado
     * como parámetro
     * @param elemento
     * @return boolean; true si el mónticulo si contiene el elemento, en caso contrario false
     */
    public boolean contiene(int elemento){
        if(this.esVacio())
            return false;

        for(int i=0;i<this.getTamano();i++){
            if(elementos[i]==elemento)
                return true;
        }
        return false;
    }
    /**
     * Verifica si el montículo se encuentra vacío o no
     * @return boolean; true si está vacío en caso contrario false
     */
    public boolean esVacio(){
        if(ultimoIndice==-1)
            return true;
        return false;
    }
    /**
     * Método que regresa el tamaño del montículo
     * @return int; tamaño del montículo
     */
    public int getTamano(){
        return ultimoIndice+1;
    }
    /**
     * Método que agrega el elemento recibido al montículo
     * @param e
     */
    public void agrega(int e){
        ultimoIndice++;

        System.out.print(ultimoIndice+", ");

        if(ultimoIndice==elementos.length)
            aumentaArreglo();

        elementos[ultimoIndice]=e;

        acomodaArriba(ultimoIndice);
    }
    /**
     * Método auxiliar que reacomoda el montículo de acuerdo al elemento correspondiente
     * al indice recibido. Esto es, comparar el elemento con su elemento "padre" mientras
     * exista, y si el elemento es menor a su elemento padre, los intercambia, actualiza
     * indices y continúa, en caso contrario termina
     * @param indice
     */
    private void acomodaArriba(int indice){
        int i=indice;
        int padre;

        if(i==0)
            padre=-1;
        else
            padre=(indice-1)/2;

        while(padre>=0){

            if(comparador.compare(elementos[i], elementos[padre])>=0)
                break;

            swap(i,padre);

            i=padre;

            if (i == 0)
                padre = -1;
            else
                padre = (i-1)/2;
        }
    }
    /**
     * Método auxiliar que reacomoda el montículo de acuerdo al elementos correspondiente al índice
     * ingresado. Esto es, comparar el elemento "padre" con sus elementos "hijos" hasta que no haya "hijos".
     * Si ambos hijos existen y el elemento "padre" es mayor a alguno de los hijos, se intercambia el elemento "padre"
     * con el menor de sus hijos. En caso de que sólo exista el hijo izquierdo, si el elemento padre es mayor a este,
     * se inetercambian.
     * @param indice
     */
    private void acomodaAbajo(int indice){

        if(getHijoIzquierdo(indice)==-1)
            return;

        if (getHijoDerecho(indice) == -1) {
            if(comparador.compare(elementos[indice], elementos[getHijoIzquierdo(indice)])>0)
                swap(indice, getHijoIzquierdo(indice));
            return;
        }

        if(comparador.compare(elementos[indice], elementos[getHijoIzquierdo(indice)])<=0
        && comparador.compare(elementos[indice], elementos[getHijoDerecho(indice)])<=0)
            return;

        if(comparador.compare(elementos[getHijoIzquierdo(indice)],elementos[getHijoDerecho(indice)])<0){
            swap(indice,getHijoIzquierdo(indice));
            acomodaAbajo(getHijoIzquierdo(indice));
        } else{
            swap(indice,getHijoDerecho(indice));
            acomodaAbajo(getHijoDerecho(indice));
        }
    }
    /**
     * Regresa la posición en el arreglo del "hijo izquierdo", de acuerdo al indice ingresado
     * @param index
     * @return
     */
    private int getHijoDerecho(int index){
        if((2*index)+2>ultimoIndice)
            return -1;
        return (2*index)+2;
    }
    /**
     * Regresa la posición en el arreglo del "hijo derecho", de acuerdo al indice ingresado
     * @param index
     * @return
     */
    private int getHijoIzquierdo(int index){
        if((2*index)+1>ultimoIndice)
            return -1;
        return (2*index)+1;
    }

    //QUITAR ESTE MÉTODO EN LA VERSION FINAL    
    public void imprimeElementos(){
        for (int i=0;i<this.getTamano();i++){
            System.out.print(elementos[i]+", ");
        }
    }
    //QUITAR ESTE MÉTODO EN LA VERSION FINAL

    /**
     * Método auxiliar que intercambia los elementos en el arreglo de los índices ingresados
     * @param index1
     * @param index2
     */
    private void swap(int index1,int index2){
        int temp=elementos[index1];

        elementos[index1]=elementos[index2];
        elementos[index2]=temp;
    }
    /**
     * Método privado que copia los elementos de un arreglo a otro arreglo
     * del doble del tamaño
     */
    private void aumentaArreglo(){
        int[] aumento=new int[elementos.length*2];

        for(int i=0;i<elementos.length;i++)
            aumento[i]=elementos[i];
        
        elementos=aumento;
    }

    public static void main(String[] args) {
        int[] arreglo={6,1,5,10,9,2,8,7};

        Monticulo monticulo=new Monticulo(arreglo);

        monticulo.elimina();
        monticulo.elimina();
        monticulo.elimina();

        System.out.println("ultimoIndice para agregar: "+(monticulo.getTamano()-1));
        monticulo.imprimeElementos();
    }
}