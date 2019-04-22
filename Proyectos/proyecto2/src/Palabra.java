package proyecto2;


public class Palabra implements Comparable<Palabra>{

  private String palabra;
  private int recurrencia;


  public Palabra(String palabra){

    this.palabra = palabra;
    recurrencia = 0
  }

  public int  getTF(Palabra palabra){
    return palabra.recurrencia;
  }

  public String getPalabra(Palabra palabra){
    return palabra.palabra;
  }

  public void aumentarRecurrencia(Palabra palabra){
    recurrencia ++;
  }

  @Override
  public int compareTo(Palabra palabra){
    return this.palabra.compareTo(palabra.getPalabra());
  }


}
