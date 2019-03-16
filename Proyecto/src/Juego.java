package proyecto1;

public class Juego{

	private Lista<Personajes> registros=new Lista<>();
	private Lista<Personajes> noche=new Lista<>();
	private String muerto;

	public Juego(String[] nombres){

	}

	private Personajes[] jugadoresRandom(String[] nombres){

	}

	public void despertarPersonajes(){

	}

	public boolean matar(String nombre){

		 for(Personajes personaje: registro){
       if(personaje.getNombre() == nombre){
         if(!personaje.estaVivo() || personaje.estaProtegido())
           break;
         if(personaje.estaVivo() && !personaje.estaProtegido()){
           personaje.cambiaEstado(false);//Mata al personaje
           return true; //Es decir que si lo mató
           }
        }
     }

   return false; //Es decir si lo mató
  }

	public boolean proteger(String nombre){//Revisar si vale la pena que regrese booleano

    for(Personajes personaje: registro){
      if(personaje.getNombre() == nombre){
        if(!personaje.estaVivo() || personaje.estaProtegido())
         break;
        if(personaje.estaVivo() && !personaje.estaProtegido()){
          personaje.cambiaProtegido(true);
          return true;
        }
      }
      // Falta implementar cuando el personaje ya ha sido protegido
    }

    return false;
	}

	public String verPersonaje(String nombre,boolean estado){

	}

	/*El método recibe el estado del cazador
    Si el cazador está muerto, entonces decide matar a
    alguien, es decir llama a matar */
 	public boolean muerteCazador(boolean estadoCazador, String nombre, String nombreDeObjetivo){

     for(Personajes personaje: registro){
       if(personaje.getNombre() == "CAZADOR"){
         if(!personaje.estaVivo()){
           return Juego.matar(nombreDeObjetivo); //Lo mató o no lo mató
         }
       }
     }

     return false;//No lo mató

 	}

	/* Este método es una variante de matar pero utilizada en el día, es decir
    que únicamente se salva de que lo linchen el TONTO_DE_LA_ALDEA;
    NOTA: En el archivo de Pedro de "Lobos de Castronegro" dice que cuando
    el TontoDeLaAldea destape su carta "No habra ninguna otra votacion en este turno."
    [Revisar]
  */
	public boolean linchar(String nombre){

    for(Personajes personaje: registro){
      if(personaje.getNombre()== nombre){
        if(!personaje.estaVivo() || personaje==Personaje.TONTO_DE_LA_ALDEA){
          break;
          //Aún no sé si vale la pena poner esta linea, por que no sabemos si es
          //el tonto de la aldea o ya está muerto [Revisar]
        }
        else{
          personaje.cambiaEstado(false);//Es decir mata al personaje
        }
      }
    }

    return false; //Es decir que el personaje ya está muerto o es el tonto de la aldea
	}

	public void encantar(String nombreEncantado,String nombreEncantado2){

    for(Personajes personaje: registro){
      if(personaje.getNombre() == nombreEncantado){
        if(!personaje.estaVivo() || personaje.estaEncantado() || personaje == Personaje.FLAUTISTA){
          break;
        }
        else{
          personaje.cambiaEncantado(true); //Es decir que ya lo encantó
        }
      }
    }

	}

	public String determinarGanador(Lista<Personajes> vivos){

	}

	public void usarPosicionVenenosa(String nombre){

	}

	public void usarPosicionResurreccíon(String nombre){

	}

}
