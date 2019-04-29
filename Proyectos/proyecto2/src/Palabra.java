package proyecto2;

/**
 * Clase Palabra. Tiene como atributos un String que guarda la palabra propia de
 * cada objeto creado de esta clase, la recurrencia de esta palabra y el valor IDF.
 * Esta clase nos ayuda a calcular valores importantes para el motor de búsqueda y
 * tener acceso a ellos de una forma más sencilla. Todas las palabras obtenidas de
 * los archivos y de la busqueda ingresada por el usuario son objetos de esta calse
 * 
 * @author Gonzalez Jasso Jose Eduardo
 * @author Dozal Magnani Diego
 */
public class Palabra implements Comparable<Palabra>{

  private String palabra;
  private int recurrencia;
  private double idf;

  /**
   * Constructor que recibe un String que asigna al atributo palabra
   * @param palabra
   */
  public Palabra(String palabra){

    this.palabra = palabra;
    recurrencia = 1;
  }
  /**
   * Método que regresa el valor de recurrencia de la palabra
   * @return int; valor de recurrencia
   */
  public int getRecurrencia(){
    return recurrencia;
  }
  /**
   * Método que regresa la palabra propia del objeto
   * @return String; palabra del objeto
   */
  public String getPalabra(){
    return palabra;
  }
  /**
   * Método que aumenta el valor de recurrencia propio de ada objeto
   */
  public void aumentarRecurrencia(){
    recurrencia ++;
  }
  /**
   * Método que calcula el valor TF de acuerdo al valor de recurrencia de la palabra
   * @return double; TF de la palabra
   */
  public double calculaTF(){
    return (Math.log10(recurrencia)/Math.log10(2))+1;
  }
  /**
   * Método que verifica la existencia de la palabra en una lista de objetos de tipo Ficheros
   * y regresa el número de ficheros en el que está la palabra. Para esto llama al método 
   * obtenerPalabras() que regresa un árbol con todas las palabras diferentes de cada archivo 
   * y busca si el árbol contiene la palabra
   * @param listaFicheros
   * @return int; número de archivos de listaFicheros que contienen la palabra
   */
  private int obtenerCoincidencias(Lista<Ficheros> listaFicheros){
    int coincidencia=0;
    
    for(Ficheros elemento:listaFicheros){
      if(elemento.obtenerPalabras().contiene(this))
        coincidencia++;
    }

    return coincidencia;
  }
  /**
   * Método que calcula el IDF de la palabra de acuerdo a la lista de Ficheros ingresada
   * como parámetro
   * @param listaFicheros
   */
  public void calculaIDF(Lista<Ficheros> listaFicheros){
    if(obtenerCoincidencias(listaFicheros)<=0)
      idf=0;
    else
      idf=Math.log10((((double)listaFicheros.getLongitud()+1)/(double)obtenerCoincidencias(listaFicheros)))/Math.log10(2);
  }
  /**
   * Regresa el valor de IDF de la palabra
   * @return
   */
  public double getIDF(){
    return idf;
  }
  /**
   * Método que compara dos objetos de tipo Palabra, basándose en su atributo palabra. 
   * Este método sigue los lineamientos de comparación de la clase String.
   * @return int;
   */
  @Override
  public int compareTo(Palabra compPalabra){
    return this.palabra.compareTo(compPalabra.palabra);
  }
  /**
   * Método que verifica si dos objetos Palabra son iguales o no, de acuerdo a su atributo palabra
   * @return boolean; true si son iguales, false si son diferentes
   */
  @Override
  public boolean equals(Object compObject){
    if(!(compObject instanceof Palabra))
      return false;

    Palabra compPalabra=(Palabra)compObject;
    
    return this.palabra.equalsIgnoreCase(compPalabra.palabra);
  }

}