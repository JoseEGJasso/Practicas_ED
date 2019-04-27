package proyecto2;


public class Palabra implements Comparable<Palabra>{

  private String palabra;
  private int recurrencia;
  private double idf;

  public Palabra(String palabra){

    this.palabra = palabra;
    recurrencia = 1;
  }

  public int getRecurrencia(){
    return recurrencia;
  }

  public String getPalabra(){
    return palabra;
  }

  public void aumentarRecurrencia(){
    recurrencia ++;
  }

  public double calculaTF(){
    return (Math.log10(recurrencia)/Math.log10(2))+1;
  }

  private int obtenerCoincidencias(Lista<Ficheros> listaFicheros){
    int coincidencia=0;
    
    for(Ficheros elemento:listaFicheros){
      if(elemento.obtenerPalabras().contiene(this))
        coincidencia++;
    }

    return coincidencia;
  }

  public void calculaIDF(Lista<Ficheros> listaFicheros){
    if(obtenerCoincidencias(listaFicheros)<=0)
      idf=0;
    else
      idf=Math.log10((((double)listaFicheros.getLongitud()+1)/(double)obtenerCoincidencias(listaFicheros)))/Math.log10(2);
  }

  public double getIDF(){
    return idf;
  }

  @Override
  public int compareTo(Palabra compPalabra){
    return this.palabra.compareTo(compPalabra.palabra);
  }

  @Override
  public boolean equals(Object compObject){
    if(!(compObject instanceof Palabra))
      return false;

    Palabra compPalabra=(Palabra)compObject;
    
    return this.palabra.equalsIgnoreCase(compPalabra.palabra);
  }

}
