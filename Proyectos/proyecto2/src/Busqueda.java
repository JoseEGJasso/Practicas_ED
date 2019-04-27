package proyecto2;

/**
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 * 
 *
 */
public class Busqueda{
    private Lista<Ficheros> archivos;
    private Lista<Palabra> palabrasDeBusqueda;

    public Busqueda(){
        palabrasDeBusqueda=new Lista<>();
        archivos=new Lista<>();
    }

    public void procesarFicheros(){
        for(Ficheros elemento:archivos){
            elemento.eliminarAcentosYEspeciales();
            elemento.dividirPalabras();
        }
    }

    public void procesarBusqueda(String busqueda){
        String nuevaPalabra="";

        for(int i=0;i<busqueda.length();i++){

            if(busqueda.charAt(i)==' '){
                if(!nuevaPalabra.equals("")){
                    if(!(palabrasDeBusqueda.contiene(new Palabra(nuevaPalabra))))
                        palabrasDeBusqueda.agregaFinal(new Palabra(nuevaPalabra));

                    nuevaPalabra="";
                }
                continue;
            }

            nuevaPalabra+=busqueda.charAt(i);
        }

        if(!nuevaPalabra.equals("")){

            if(!(palabrasDeBusqueda.contiene(new Palabra(nuevaPalabra))))
                palabrasDeBusqueda.agregaFinal(new Palabra(nuevaPalabra));
        }
    }

    public void agregarFicheros(Ficheros nuevoFichero){
        archivos.agregaFinal(nuevoFichero);
    }

    public void calcularSimilitud(){

        getIDF();

        for(Ficheros elemento:archivos){
            elemento.obtenerTF(palabrasDeBusqueda);
            elemento.productoTF_IDF(palabrasDeBusqueda);
            elemento.asignarSimilitud();
        }
    }

    //IMORTANTE: SE DEBE EJECUTAR ANTES QUE LA SIMILITUD
    private void getIDF(){
        for(Palabra elemento:palabrasDeBusqueda){
            elemento.calculaIDF(archivos);
            System.out.print("IDF: "+ elemento.getIDF());
        }
        System.out.println();
    }


    private Ficheros[] ordenarFicheros(){
        Object[] ficheros=archivos.toArray();
        Ficheros[] temp=new Ficheros[ficheros.length];

        for(int i=0;i<temp.length;i++){
            temp[i]=(Ficheros)ficheros[i];
        }

        Ordenamientos.quickSort(temp);

        return temp;
    }

    public int getNumFicheros(){
        return archivos.getLongitud();
    }

    public boolean yaSeAgrego(Ficheros archivo){
        return archivos.contiene(archivo);
    }

    public void imprimirResultados(){
        Ficheros[] ficherosOrdenados=ordenarFicheros();

        System.out.println();

        for(int i=ficherosOrdenados.length-1;i>=0;i--){
            if(ficherosOrdenados[i].getSimilitud()==0)
                continue;
            System.out.println(ficherosOrdenados[i].getNombre()+": "+ficherosOrdenados[i].getSimilitud());
            System.out.println(((ficherosOrdenados.length-i))+".-"+ficherosOrdenados[i].getNombre()+"\n");
        }
    }

}