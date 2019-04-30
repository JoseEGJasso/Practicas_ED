package proyecto2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 * Clase que modela un objeto de tipo Fichero. Cada fichero tiene de atributos :
 * -  Un ArbolRojinegro de tipo Palabra: Almacena las palabras(String) del fichero
 *    en objetos de tipo palabra, los cuales son vértice del árbol Rojinegro.
 * -  Un objeto de tipo Path ruta , es decir la ruta del fichero.
 * -  Una StringBuilder contenido para poder sustraer el contenido de un archivo .txt.
 * -  Un int contPalabras para llevar la cuenta de palabras en el archivo.
 * -  Un double[] tf, el cual guardará el tf del termino buscado de cada palabra dentro
 *    del archivo.
 * -  Un double[] tf, el cual guadará el producto del idf y el tf de cada palabra.
 * -  Un double similitud, el cuál será la suma del cada índice del atributo tf_idf (que
 *    representa el tf_idf de cada palabra) entre la cantidad total de palabras del
 *    documento.
 */
public class Ficheros implements Comparable<Ficheros>{

    //Atributos
    private ArbolRojinegro<Palabra> palabras;
    private Path ruta;
    private StringBuilder contenido;
    private int contPalabras;
    private double[] tf;
    private double[] tf_idf;
    private double similitud;

    /**
     * Constructor de un Fichero a partir de una ruta.
     * inicializa el atributo palabras como un árbol rojinegro vacio y
     * llama al método leerFichero.
     *
     * @param ruta
     */
    public Ficheros(String ruta){
        this.ruta=Paths.get(ruta);
        leerFichero();
        palabras=new ArbolRojinegro<>();

    }

    /**
     * Método para leer el Fichero.Se encarga de procesar el archivo y de
     * asignar el contenido del mismo.
     *
     */
    private void leerFichero(){
        try{
            BufferedReader buffer=Files.newBufferedReader(ruta,StandardCharsets.UTF_8);

            String lectorDeLineas=buffer.readLine();
            contenido=new StringBuilder();

            while(lectorDeLineas!=null){
                contenido.append(lectorDeLineas);
                lectorDeLineas=buffer.readLine();
                if(lectorDeLineas!=null)
                    contenido.append(" ");
            }

            buffer.close();

        }catch(IOException e){
            contenido=null;
        }
    }

    /**
     * Método que verifica el atributo contenido y en base a eso retorna un caracter para continuar con
     * los porcesos necesarios.El caracter 'N' significa que algo no salió bien y el caracter 'B' significa
     * que se pueden continuar con los procesos.
     *
     * @return char
     */
    public char verificarFichero(){
        if(this.contenido==null)
            return 'N';
        for(int i=0;i<contenido.length();i++){
            if(contenido.charAt(i)!=' ')
                return 'B';
        }
        return 'N';
    }

    /**
     * Método para modificar el atributo similitud
     *
     * @param similitud
     */
    public void setSimilitud(double similitud){
        this.similitud=similitud;
    }

    /**
     * Método para convertir a cadena y retornar la variable contenido.
     *
     * @@return contenido.toString()
     */
    public String getContenido(){
        return contenido.toString();
    }

    /**
     * Método para covertir a String y retornar el nombre del archivo.
     *
     * @return ruta.getFileName().toString
     */
    public String getNombre(){
        return ruta.getFileName().toString();
    }

    /**
     * Método para retornar el árbol de palabras del Fichero .
     *
     * @@return palabras
     */
    public ArbolRojinegro<Palabra> obtenerPalabras(){
        return palabras;
    }

    /**
     * Método vacío para discriminar caracteres "especiales", como las vocales con acento, las comillas,
     * signos de interrogación y admiración, etc.
     *
     */
    public void eliminarAcentosYEspeciales(){

        for(int i=0;i<contenido.length();i++){

            if((int)(contenido.charAt(i))>=224 && (int)(contenido.charAt(i))<230 || (int)(contenido.charAt(i))>=192 && (int)(contenido.charAt(i))<198){
                contenido.setCharAt(i,'a');
                continue;
            }
            if((int)(contenido.charAt(i))>=232 && (int)(contenido.charAt(i))<236 || (int)(contenido.charAt(i))>=200 && (int)(contenido.charAt(i))<204){
                contenido.setCharAt(i,'e');
                continue;
            }
            if((int)(contenido.charAt(i))>=236 && (int)(contenido.charAt(i))<240 || (int)(contenido.charAt(i))>=204 && (int)(contenido.charAt(i))<208){
                contenido.setCharAt(i,'i');
                continue;
            }
            if((int)(contenido.charAt(i))>=242 && (int)(contenido.charAt(i))<246 || (int)(contenido.charAt(i))>=210 && (int)(contenido.charAt(i))<215){
                contenido.setCharAt(i,'o');
                continue;
            }
            if((int)(contenido.charAt(i))>=249 && (int)(contenido.charAt(i))<252 || (int)(contenido.charAt(i))>=217 && (int)(contenido.charAt(i))<221){
                contenido.setCharAt(i,'u');
                continue;
            }

            if(contenido.charAt(i)==',' || contenido.charAt(i)=='?' || contenido.charAt(i)=='¿' || contenido.charAt(i)=='!' || contenido.charAt(i)=='¡'){
                contenido.setCharAt(i,' ');
                continue;
            }

            if(contenido.charAt(i)=='.' || contenido.charAt(i)==':' || contenido.charAt(i)==';'){
                contenido.setCharAt(i,' ');
                continue;
            }
        }
    }

    /**
     * Método vacío para dividir las palabras del contenido del fichero. Este método en específico
     * recorre el contenido ignorando espacios y únicamente leyendo aquello distinto a un espacio.
     * Además, para facilitar la lectura, cada que se encuentre con un salto de linea, procederá
     * a concatenar las palabras en líneas posteriores en la primera, de este modo puede recorrer
     * todo el contenido del Fichero ininterrumpidamente.
     * Cuando logra leer algo distinto a un espacio, entonces genera un objeto de tipo Palabra con
     * argumento de la nuevaPalabra y a continuación lo agrega al árbol de palabras.En cada iteración
     * en la que agrega un nuevo elemento al árbol, el atributo contPalabras aumenta en uno.
     *
     */
    public void dividirPalabras(){
        String nuevaPalabra="";

        for(int i=0;i<contenido.length();i++){

            if(contenido.charAt(i)==' '){
                if(!nuevaPalabra.equals("")){
                    Palabra igual=palabras.busca(new Palabra(nuevaPalabra));

                    contPalabras++;

                    if(igual==null)
                        palabras.agrega(new Palabra(nuevaPalabra));
                    else{
                        igual.aumentarRecurrencia();
                    }

                    nuevaPalabra="";
                }
                continue;
            }

            nuevaPalabra+=contenido.charAt(i);
        }

        if(!nuevaPalabra.equals("")){
            Palabra igual=palabras.busca(new Palabra(nuevaPalabra));

            contPalabras++;

            if(igual==null)
                palabras.agrega(new Palabra(nuevaPalabra));
            else{
                igual.aumentarRecurrencia();
            }
        }
    }

    /**
     * Método para retornar contPalabras
     *
     * @@return contPalabras
     */
    public int getcontPalabras(){
        return contPalabras;
    }

    /**
     * Método para alamcenar el tf de cada palabra manteniendo el orden de la lista de palabras
     * búscadas.
     *
     * @param busquedaDePalabras
     */
    public void obtenerTF(Lista<Palabra> busquedaDePalabras){
        tf=new double[busquedaDePalabras.getLongitud()];

        for(int i=0;i<busquedaDePalabras.getLongitud();i++){
            Palabra palabraIterada=palabras.busca(busquedaDePalabras.get(i));

            if(palabraIterada!=null){
                tf[i]=palabraIterada.calculaTF();
            }else
                tf[i]=0.0;
        }
    }

    /**
     * Método para almacenar el producto del tf y el idf de cada palabra en el atributo tf_idf.Respetando
     * el orden de la lista de palabras recibida.
     *
     * @param palabrasDeBusqueda
     */
    public void productoTF_IDF(Lista<Palabra> palabrasDeBusqueda){
        tf_idf=new double[palabrasDeBusqueda.getLongitud()];

        for(int i=0;i<palabrasDeBusqueda.getLongitud();i++){
            tf_idf[i]=tf[i]*palabrasDeBusqueda.get(i).getIDF();
        }
    }

    /**
     * Método para determinar la similitud a partir de la suma de tf_idf de cada palabra que coincide con la
     * búsqueda y entre el total de palabras en el fichero.
     *
     */
    public void asignarSimilitud(){
        double suma=0;

        for(double elemento:tf_idf)
            suma+=elemento;

        if(this.contPalabras==0){
            this.similitud=0;
        }else
            this.similitud=suma/this.contPalabras;
    }

    /**
     * Método vacío que refresca los atributos tf y tf_idf(el producto de éstos) de cada
     * archivo.
     *
     */
    public void limpiar(){
    	tf_idf=null;
    	tf=null;
    }

    /**
     * Método para retornar la similitud del fichero con las palabras buscadas.
     *
     * @return similitud
     */
    public double getSimilitud(){
        return similitud;
    }

    /**
     * Implementación del método compareTo de la interfaz Comparable.
     * Se comparan las similitudes entre dos archivos.
     *
     * @param archivo
     * @return int
     */
    @Override
    public int compareTo(Ficheros archivo){
        if(this.similitud<archivo.similitud)
            return -1;
        if(archivo.similitud<this.similitud)
            return 1;
        return 0;
    }

    /**
     * Implementación del método equals.El cual a su vez utiliza el equals de cadenas
     * para comparar las rutas de los archivos.
     *
     * @param obj
     * @return boolean 
     */
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ficheros))
            return false;

        Ficheros archivo=(Ficheros)obj;

        return this.ruta.equals(archivo.ruta);
    }
}
