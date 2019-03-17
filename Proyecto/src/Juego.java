package proyecto1;

public class Juego{

	private Lista<Personajes> registro=new Lista<>();
	private Lista<Personajes> noche=new Lista<>();
	private String muerto;

	public Juego(String[] nombres){
    registro=new Lista<>(jugadoresRandom(nombres));
	}

	private Personajes[] jugadoresRandom(String[] nombres){
    String[] personajesU={"vidente","protector","niña pequeña","bruja","flautista","tonto de la aldea","cazador"};
    Personajes[] salida=new Personajes[nombres.length];
    Lista<String> personajesUnicos=new Lista<>(personajesU);
    Lista<String> personajesNom=new Lista<>(nombres);

    for (int i=0;i<personajesU.length;i++) {
      int pAzar=(int)(Math.random()*personajesUnicos.getLongitud());
      int nAzar=(int)(Math.random()*personajesNom.getLongitud());

      salida[i]=new Personajes(personajesNom.get(nAzar),personajesUnicos.get(pAzar));

      personajesNom.elimina(personajesNom.get(nAzar));
      personajesUnicos.elimina(personajesUnicos.get(pAzar));
    }

    int numAldeanos=(int)personajesNom.getLongitud()/2;
    int contAldeanos=0;

    for(int i=7;i<salida.length;i++){
      if(contAldeanos<numAldeanos){
        salida[i]=new Personajes(personajesNom.getPrimero(),"lobo");
        personajesNom.eliminaPrimero();
        contAldeanos++;
      } else{
        salida[i]=new Personajes(personajesNom.getPrimero(),"aldeano");
        personajesNom.eliminaPrimero();
      }
    }

    return salida;
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
           muerto=personaje.getNombre();
           return true; //Es decir que si lo mató
           }
        }
     }

   return false; //Es decir no lo mató
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
    return "a";
	}

	/*El método recibe el estado del cazador
    Si el cazador está muerto, entonces decide matar a
    alguien, es decir llama a matar */
 	public boolean muerteCazador(String nombreDeObjetivo){

     for(Personajes personaje: registro){
       if(personaje.getNombre() == "CAZADOR"){
         if(!personaje.estaVivo()){
           return matar(nombreDeObjetivo); //Lo mató o no lo mató
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
        if(!personaje.estaVivo() || personaje.getPersonaje().equals("Tonto de la aldea")){
          break;
          //Aún no sé si vale la pena poner esta linea, por que no sabemos si es
          //el tonto de la aldea o ya está muerto [Revisar][Suena bien, opino que hay que dejarlo como está]
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
        if(!personaje.estaVivo() || personaje.estaEncantado() || personaje.getPersonaje().equals("Flautista")){
          break;
        }
        else{
          personaje.cambiaEncantado(true); //Es decir que ya lo encantó
        }
      }
    }

	}

	public String determinarGanador(Lista<Personajes> vivos){
    return "xDxdXDdX";
	}

	public void usarPosicionVenenosa(String nombre){

	}

	public void usarPosicionResurreccíon(String nombre){

	}
  /**
   * Posible método que actualice principalmente los estados de la lista noche
   * de acuerdo a lo sucedido en el día y la noche. Este método
   * se ejecuta al final de cada noche [REVISAR]
   */
  public void actualizar(){

  }

}
