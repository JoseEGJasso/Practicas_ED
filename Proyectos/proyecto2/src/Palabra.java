package proyecto2;


public class Palabra implements Comparable<Palabra>{

  private String palabra;
  private int recurrencia;


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
