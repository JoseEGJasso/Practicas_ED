package proyecto2;

/**
 * Clase Busqueda. Se encarga de guardar todos los archivos ingresados en una lista y
 * contiene métodos para procesarlos y prepararlos para la búsqueda, la funcionalidad 
 * de algunos de ellos son calcular la similitud, el TF de cada término, verificar la 
 * existencia de los archivos, entre otros.
 * 
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Busqueda{
    private Lista<Ficheros> archivos;
    private Lista<Palabra> palabrasDeBusqueda;

    /**
     * Constructor de busqueda
     */
    public Busqueda(){
        palabrasDeBusqueda=new Lista<>();
        archivos=new Lista<>();
    }
    /**
     * Método que elimina símbolos especiales y acentos de cada fichero y divide cada palabra
     * diferente insertándola a un arbol, claramente propio de cada archivo
     */
    public void procesarFicheros(){
        for(Ficheros elemento:archivos){
            elemento.eliminarAcentosYEspeciales();
            elemento.dividirPalabras();
        }
    }
    /**
     * Método que divide la busqueda introducida por palabras y con ellas crea objetos de tipo Palabra
     * @param busqueda
     */
    public void procesarBusqueda(String busqueda){
        if(palabrasDeBusqueda.getLongitud()>0)
            palabrasDeBusqueda.limpia();
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
    /**
     * Agrega un nuevo fichero a la lista de archivos 
     * @param nuevoFichero
     */
    public void agregarFicheros(Ficheros nuevoFichero){
        archivos.agregaFinal(nuevoFichero);
    }
    /**
     * Calcula el IDF de las palabras, TF y el producto de ambos 
     */
    public void calcularSimilitud(){

        getIDF();

        for(Ficheros elemento:archivos){
            elemento.obtenerTF(palabrasDeBusqueda);
            elemento.productoTF_IDF(palabrasDeBusqueda);
            elemento.asignarSimilitud();
        }
    }
    /**
     * Calcula el IDF de cada palabra de la lista palabrasDeBusqueda
     */
    private void getIDF(){
        for(Palabra elemento:palabrasDeBusqueda){
            elemento.calculaIDF(archivos);
            System.out.print("IDF: "+ elemento.getIDF());
        }
        System.out.println();
    }
    /**
     * Ordena los ficheros de la lista de acuerdo a la similitud obtenida
     * @return Ficheros[]; arreglo de ficheros ordenadados de menor a mayor
     */
    public Ficheros[] ordenarFicheros(){
        Object[] ficheros=archivos.toArray();
        Ficheros[] temp=new Ficheros[ficheros.length];

        for(int i=0;i<temp.length;i++){
            temp[i]=(Ficheros)ficheros[i];
        }

        Ordenamientos.quickSort(temp);

        return temp;
    }
    /**
     * Regresa la cantidad de ficheros en la lista de ficheros
     * @return int; cantidad de ficheros en la busqueda
     */
    public int getNumFicheros(){
        return archivos.getLongitud();
    }
    /**
     * Verfifica si el archivo como parámetro ya se agregó a la lista
     * @param archivo
     * @return boolean; true si ya se agregó, false en el caso contrario
     */
    public boolean yaSeAgrego(Ficheros archivo){
        return archivos.contiene(archivo);
    }
    /**
     * Imprime el arreglo con los ficheros ordenados, recorre el arreglo de mayor a menor
     * para imprimirlos ordenados.
     */
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
