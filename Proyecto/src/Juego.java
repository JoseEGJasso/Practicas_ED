package proyecto1;

public class Juego{

	private Lista<Personajes> registro=new Lista<>();
	private Lista<Personajes> noche=new Lista<>();
	private String muerto;

	public Juego(String[] nombres){
    registro=new Lista<>(jugadoresRandom(nombres));
    Personajes[] jugadoresAsignados=jugadoresRandom(nombres);
    Lista<Personajes> jugadoresDeNoche=new Lista<>();
    int lobos=0;

    for (int i=0;lobos<1;i++) {
      if(!jugadoresAsignados[i].getPersonaje().equals("Tonto de la aldea") && !jugadoresAsignados[i].getPersonaje().equals("Cazador") && !jugadoresAsignados[i].getPersonaje().equals("Niña pequeña")){
        if(jugadoresAsignados[i].getPersonaje().equals("Lobo"))
          lobos++;
        jugadoresDeNoche.agregaFinal(jugadoresAsignados[i]);
      }
    }

    Personajes[] noches=new Personajes[jugadoresDeNoche.getLongitud()];

    for (int i=0;i<jugadoresDeNoche.toArray().length;i++) {
      noches[i]=(Personajes)jugadoresDeNoche.toArray()[i];
    }

    Ordenamientos.quickSort(noches);

    noche=new Lista<>(noches);
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

	public boolean lobosAsesinan(String nombre){

	for(Personajes personaje: registro){
       if(personaje.getNombre().equals(nombre)){
         if(!personaje.estaVivo() || personaje.estaProtegido())
           break;
         if(personaje.estaVivo() && !personaje.estaProtegido()){
            personaje.cambiaEstado(false);//Matron al personaje
			return true; //Es decir que si lo mató
           }
        }
     }

   return false; //Es decir no lo mató
  }

	public boolean proteger(String nombre){//Revisar si vale la pena que regrese booleano

    for(Personajes personaje: registro){
      if(personaje.getNombre().equals(nombre)){
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

	public String verPersonaje(String nombre){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(nombre)){
				if(!personaje.estaVivo()){
					break;
				}
				else{
					return "Nombre: "+personaje.getNombre()+"Personaje: "+personaje.getTipo();
				}
			}

		}
    return "NOT FOUND CHARACTER";
	}

	/*
     Este método únicamente revisa si el cazador ha muerto
		 de ser así , disparará el método objetivoCazador
	 */
 	public boolean muerteCazador(){

			 for(Personajes personaje: registro){
				 if(personaje.getPersonaje().equals("Cazador") && !personaje.estaVivo())
				 return true;
			 }

		return false;
 	}
  //Básicamente este método recibe a muerteCazador como booleano
	public boolean objetivoCazador(boolean cazadorMuerto, String objetivo){

			 for(Personajes personaje: registro){
				 if(personaje.getNombre().equals(objetivo)){
					 if(!personaje.estaVivo()){
						 break;
					 }else{
						 personaje.cambiaEstado(false);
						 return true;//Lo mató
					 }
				 }
			 }

		return false;//Es decir que ya estaba muerto
	}

	/* Este método es una variante de matar pero utilizada en el día, es decir
    que únicamente se salva de que lo linchen el TONTO_DE_LA_ALDEA;
    NOTA: En el archivo de Pedro de "Lobos de Castronegro" dice que cuando
    el TontoDeLaAldea destape su carta "No habra ninguna otra votacion en este turno."
    [Revisar]
  */
	public boolean linchar(String nombre){

    for(Personajes personaje: registro){
      if(personaje.getNombre().equals(nombre)){
        if(!personaje.estaVivo() || personaje.getPersonaje().equals("Tonto de la aldea")){
          break;
          //Aún no sé si vale la pena poner esta linea, por que no sabemos si es
          //el tonto de la aldea o ya está muerto [Revisar][Suena bien, opino que hay que dejarlo como está]
        }
        else{
          personaje.cambiaEstado(false);//Es decir mata al personaje
					return true;//
        }
      }
    }

    return false; //Es decir que el personaje ya está muerto o es el tonto de la aldea
	}

	public void encantar(String nombreEncantado,String nombreEncantado2){

    for(Personajes personaje: registro){
      if(personaje.getNombre().equals(nombreEncantado)){
        if(!personaje.estaVivo() || personaje.estaEncantado() || personaje.getPersonaje().equals("Flautista")){
          break;
        }
        else{
          personaje.cambiaEncantado(true); //Es decir que ya lo encantó
        }
      }
    }

    for(Personajes personaje: registro){
      if(personaje.getNombre().equals(nombreEncantado2)){
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

   		int contAldea=0;
		int contLobos=0;
		int contFlautista=0;
		boolean flautistaVivo=true;


		for(Personajes personaje: vivos){
			//Es posible que sea un poco redundante verificar que un personaje está
			//vivo en una lista de vivos, pero es sólo para depurar cualquier error
			if(!personaje.estaVivo())
				continue;

			if(personaje.getTipo().equals("Aldeanos")){
				contAldea++;
				continue;
			}
			if(personaje.getTipo().equals("Lobos")){
				contLobos++;
				continue;
			}

			//Esta verificación de que el flautista siga con vida es por que
			//puede llegar el caso en el que el contador aumente y el flautista
			//esté muerto.Por lo que podría llegar a ganar el Flautista estando
			//muerto.
			if(personaje.getPersonaje().equals("Flautista") && !personaje.estaVivo()){
				flautistaVivo=false;
				contFlautista=0;
				continue;
			}
			if(personaje.estaEncantado() && flautistaVivo)
				contFlautista++;
		}


		// La condición del contador del flautista, es decir el -2
		// es contando al mismo flautista y a cualquier otro personaje
		if(contLobos == 0 && contFlautista<vivos.getLongitud()-2)
			return "¡GANAN LOS ALDEANOS!";
		if(contAldea == 0 && contFlautista<vivos.getLongitud()-2)
		  return "¡GANA LA JAURÍA DE LOBOS!";
		if(contFlautista==vivos.getLongitud()-1)
			return "¡EL FLAUTISTA LO HA HECHO DE NUEVO!";


		return "xDxdXDdX";

	}

	public boolean usarPocionVenenosa(String nombreDeObjetivo){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(nombreDeObjetivo)){
				if(!personaje.estaVivo()){
					break;
				}else{
				 	personaje.cambiaEstado(false);
					return true;//Lo acaba de matar la bruja
				}
			}
		}

		return false; //Es decir ya está bien frío el compa
	}

	public boolean usarPocionResurreccion(String nombreDeObjetivo){

		for(Personajes personaje:registro){
			if(personaje.getNombre().equals(nombreDeObjetivo)){
				if(personaje.estaVivo()){
					break;
				}else{
					personaje.cambiaEstado(true);
					return true; //Revivió al personaje
				}
			}
		}

		return false;//No lo revivie

	}
  /**
   * Posible método que actualice principalmente los estados de la lista noche
   * de acuerdo a lo sucedido en el día y la noche. Este método
   * se ejecuta al final de cada noche [REVISAR]
   */
  public void actualizar(){

  }

}
