package proyecto3;

import java.security.SecureRandom;

/**
 * Clase que define tablas de dispersión.
 */
public class TablaDeDispersion<K, V>{

    private class Entrada{

	public K llave;
	public V valor;

	public Entrada(K llave, V valor){
        this.llave=llave;
        this.valor=valor;
	}
    }
 
    private Lista<Entrada>[] tabla;  
    private Dispersor<K> dispersor;
    private int elementos;
    public static final int CAPACIDAD_MINIMA = 64;
    private static final double CARGA_MAXIMA = 0.75;

    
    private Lista<Entrada>[] nuevoArreglo(int tamano){
        @SuppressWarnings("unchecked")
        Lista<Entrada>[] arreglo = (Lista<Entrada>[]) new Lista[tamano];
        return arreglo;
    }

    public TablaDeDispersion() {
        tabla=nuevoArreglo(128);
        dispersor=(K llave) -> llave.hashCode();
        elementos=0;
        
    }
    
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

    public TablaDeDispersion(Dispersor<K> dispersor){
        tabla=nuevoArreglo(128);
        this.dispersor=dispersor;
        elementos=0;
    }

    public TablaDeDispersion(int capacidad, Dispersor<K> dispersor){
        this.dispersor=dispersor;

        double cap=(int)(Math.log10((double)(capacidad*2))/Math.log10(2));

        if(cap-(int)cap>0.0)
            tabla=nuevoArreglo((int)cap+1);
        else
            tabla=nuevoArreglo((int)cap);
        
        elementos=0;
    }

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
 
    public V getValor(K llave){
        if(llave==null)
            return null;
        
        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]!=null){
            for(Entrada e:tabla[pos]){
                if(e.llave==llave)
                    return e.valor;
            }
        }

        return null;
    }

    public boolean contieneLlave(K llave){
        if(llave==null)
            return false;

        int pos=dispersor.dispersa(llave) & tabla.length-1;

        if(tabla[pos]==null)
            return false;

        for(Entrada e:tabla[pos]){
            if(e.llave==llave)
                return true;
        }

        return false;
    }

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

    public boolean esVacia(){
        if(elementos==0)
            return true;
        return false;
    }

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

    public int getElementos(){
        return elementos;
    }

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

    public int getCapacidad(){
        return tabla.length;
    }

    public void longitudHash(){
        for(Lista<Entrada> e:tabla){
            if(e!=null)
                System.out.print(e.getLongitud()+", ");
            else
                System.out.print("null"+", ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        TablaDeDispersion<Integer,String> table=new TablaDeDispersion<>();

        // new Dispersor<String>(){
        //     @Override
        //     public int dispersa(String elemento){
        //         return Dispersiones.dispersionDJB(elemento.getBytes());

        // SecureRandom random=new SecureRandom();

        // for(int i=0;i<129;i++)
        //     table.agrega(Integer.toString(random.nextInt(4577)), random.nextInt(276));

        table.agrega(1,"Datos 1");

        table.agrega(2,"Datos 2");

        table.agrega(2,"Datos 7");

        table.longitudHash();

        System.out.print("llaves: ");
        for (Integer var : table.getLlaves()) {
            System.out.print(var+",");
        }
        System.out.println();

        System.out.print("valores: ");
        for(String var : table.getValores()){
            System.out.print(var+", ");
        }
        System.out.println();

        System.out.println("capacidad: "+table.getCapacidad()+"\nnum elementos: "+table.getElementos()+"\ncontiene v? "+table.contieneValor("")+"\ncontiene k? "+table.contieneLlave(5)+"\nvalor de la k: "+table.getValor(5));
    }
}
