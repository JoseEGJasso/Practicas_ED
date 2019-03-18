package proyecto1;

import java.util.Scanner;

/**
 * Clase Juego. En esta clase se desarrolla la mayoría de la lógica del juego.
 * Utilizando la clase Personaje creamos a los distintos y posibles personajes
 * del juego Lobos de CASTONEGRO y las habilidades que conllevan. Como también
 * el desarrollo de la noche y el registro de los jugadores.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Juego{

	// Atributos de la clase
	private Lista<Personajes> noche; // Lista de personajes que guarda a los personajes vivos que se despiertan en la noche
	private Lista<Personajes> registro;// Lista de todos los personajes del juego. Nos facilita el acceso a los estados de cada jugador
	private Lista<Personajes> muertos;// Lista de personajes muertos, que se usará para obtener el resultado de los personajes muertos en cada ronda
	private Lista<Personajes> encantados;//Lista de personajes encantados, para determinar si el FLAUTISTA ha ganado.
	private String protegido;//Cadena que guarda al último jugador protegido, para que no pueda ser protegido dos veces seguidas
	private boolean pocionVenenosa; //Atributo propia del personaje BRUJA, guarda si tiene o no la pocion venenosa.
	private boolean pocionResurreccion; //Atributo propia del personaje BRUJA, guarda si tiene o no la pocion de resurreccion.
	private boolean disparo;//Atributo propio del personaje CAZADOR, guarda si tiene o no un disparo al momento de morir.

	/**
	 * Constructor de Juego. Recibe una cadena de nombres, la cual representa los nombre de los jugadores.
	 * Ordena a los personajes dependiendo de la variable orden asociada en Personajes.
	 *
	 * @param nombres
	 */
	public Juego(String[] nombres){
		Personajes[] jugadoresAsignados=jugadoresRandom(nombres);
		Lista<Personajes> jugadoresDeNoche=new Lista<>();
		registro=new Lista<>(jugadoresAsignados);
		encantados=new Lista<>();
		muertos=new Lista<>();
		disparo=true;
		pocionVenenosa=true;
		pocionResurreccion=true;

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

	/**
	 * Método para asignar de forma aleatoria el personaje de que tendrá cada jugador. Hay una cantidad limitada
	 * de personajes especiales. Después de que éstos hayan sido asignados, se asignan lobos y aldeanos, donde la
	 * cantidad de los lobos es 1/2 de la cantidad de aldeanos sin habilidades.
	 *
	 * @param nombres;
	 * @return salida;
	 */
	public Personajes[] jugadoresRandom(String[] nombres){
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

	/**
	 * Método que modela el orden en el que despiertan los jugadores en la noche. Itera sobre la lista noche, de personajes
 	 * aún con vida y que tienen que despertar y, dependiendo del personaje, Se ejecuta una serie de System.out.printlns para
 	 * guiar al narrador con cada habilidad de cada personaje, pidiendo que ingrese nombre según sea el caso del personaje.
	 *
	 *
	 */
	public void despertarJugadores(){
		Scanner sc=new Scanner(System.in);
		String elegido;
		String elegido1;
		int decision;

		for(Personajes e:noche){
			switch(e.getPersonaje()){

				case "Vidente":
				System.out.println("Se despierta la Vidente...");
				do{
					System.out.print("De quien quieres ver la carta?... \n>: ");
					elegido=sc.nextLine();
				}while(!verPersonaje(elegido));
				System.out.println("Se duerme la Vidente...\n");
				break;


				case "Protector":
				System.out.println("Se despierta el Protector...");
				do{
					System.out.print("A quién quieres proteger esta noche?... \n>: ");
					elegido=sc.nextLine();
				}while(!proteger(elegido));
				System.out.println("Se duerme el Protector...\n#################################################################################");
				break;


				case "Lobo":
				System.out.println("Se despiertan los Lobos... **(NO MENCIONAR DURANTE EL JUEGO) La niña pequeña puede entre abrir los ojos** ");
				do{
					System.out.print("A quién van a matar esta noche?... \n>: ");
					elegido=sc.nextLine();
				}while(!lobosAsesinan(elegido));
				System.out.println("Se duermen los Lobos...\n###################################################################################");
				break;


				case "Bruja":
				System.out.println("Se despierta la Bruja...");
				if(hayPocionVenenosa()){
					do{
						System.out.print("Deseas matar a alguien?...[1=si/0=no] \n>: ");
						decision=sc.nextInt();
					}while(decision!=1 && decision!=0);

					if(decision==1){
						do{
							System.out.print("A quien quieres matar?... \n>:");
							elegido=sc.nextLine();
						}while(!usarPocionVenenosa(elegido));
					}
				}
				if(hayPocionResurreccion()){
					do{
						System.out.print("Deseas revivir a alguien?...[1=si/0=no] \n>: ");
						decision=sc.nextInt();
					}while(decision!=1 && decision!=0);

					if(decision==1){
						do{
							System.out.print("A quien quieres revivir?... \n>:");
							elegido=sc.nextLine();
						}while(!usarPocionResurreccion(elegido));
						hayResurreccion(elegido);
					}
				}
				System.out.println("Se duerme la Bruja...\n#####################################################################################");
				break;


				case "Flautista":
				System.out.println("Se despierta el Flautista...");
				do{
					sc.nextLine();
					System.out.print("Ingresa la persona a encantar... \n>: ");
					elegido=sc.nextLine();
				}while(!encantar(elegido));

				do{
					System.out.print("Ingresa la otra persona a encantar... \n>: ");
					elegido1=sc.nextLine();
				}while(!encantar(elegido1));
				System.out.println("Se duerme el Flautista...\n#################################################################################");
				break;


				default:
				System.out.println("Algo anda mal");
				break;
			}
		}
		System.out.println("[PRESIONA ENTER PARA CONTINUAR]\n");
		sc.nextLine();
		if(encantados.getLongitud()!=0){
			System.out.println("Despierten unicamente los encantados. Miren quien está encantado y aún más importante quien no está encatado pues entre ellos está el Flautista\n");

		}

	}

	/**
	 * Método que modela lo que ocurre cuando los perosnajes lobos deciden matar a un personaje en la noche. En
	 * este método ya está considerado cuando un personaje está protegido. Cuando un perosnaje es asisesinado,
	 * modificamos la lista de muertos, agregando al último personaje asisesinado.Regresa un booleano para confirmar
	 * la muerte del personaje
	 * @param nombre
   * @return boolena;
	 */
	public boolean lobosAsesinan(String nombre){

		for(Personajes personaje: registro){
	       if(personaje.getNombre().equals(nombre)){
	         if(!personaje.estaVivo()){
	         	break;
             }
             if(personaje.estaProtegido())
             	return true;
	         if(personaje.estaVivo() && !personaje.estaProtegido()){
	         	muertos.agregaFinal(personaje);
	            personaje.cambiaEstado(false);//Matron al personaje
	            return true;
	           }
	        }
	    }
	    System.out.println("Ese nombre no existe! Intenta de nuevo");
	    return false;
  	}

	/**
	 * Método qu modela la habilidad de proteger propia del PROTECTOR. Primero verifica que el peronajes no haya sido
	 * protegido. Luego que el personaje esté vivo o no esté protegido, en caso contrario regresa false junto con un mensaje.
	 * Finalmente al pasar estos filtros, se cambia la variable protegido a true. Finalmente regresa un boolean para confirmar
	 * si fue o no protegido.
	 *
	 * @param nombre;
	 * @return boolean.
	 */
	public boolean proteger(String nombre){

		if(protegido==null || !nombre.equals(protegido)){
		    for(Personajes personaje: registro){
		      if(personaje.getNombre().equals(nombre)){
		        if(!personaje.estaVivo() || personaje.estaProtegido())
		         	break;
		        if(personaje.estaVivo() && !personaje.estaProtegido()){
		          personaje.cambiaProtegido(true);
		          if(protegido!=null){
		          	for(Personajes e:registro){
		          		if(e.getNombre().equals(protegido))
		          			e.cambiaProtegido(false);
		          	}
		          }
		          protegido=personaje.getNombre();
		          return true;
		        }
		       }
	        }
    	}
    	System.out.println("El personaje no existe o lo protegiste en la ronda anterior!. Intenta de nuevo");
    	return false;
	}

	/**
	 * Método que modela la habilidad de ver la carta de un jugador propia de la VIDENTE. Primero verifica que el jugador
	 * esté vivo, en caso contrario se sale del ciclo que itera a registro.Si el personaje está vivo, envía un mensaje y
	 * luego regresa un booleano para confirmar la acción.
	 *
	 * @param nombre;
	 * @return boolean;
	 */
	public boolean verPersonaje(String nombre){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(nombre)){
				if(!personaje.estaVivo()){
					break;
				}
				else{
					System.out.println("Nombre: "+personaje.getNombre()+"  Personaje: "+personaje.getPersonaje());
					return true;
				}
			}
		}
		System.out.println("Ese nombre no existe o el personaje esta muerto!. Intenta de nuevo");
    	return false;
	}

  /**
	 * Método que revisa continuamente el estadod de cazador. En caso de que el
	 * cazador esté muerto, regresa true para confirmar la muerte del cazador y cambia
	 * el estado de disparo para modelar que ya usó su disparo el jugador.
	 *
	 * @return boolen;
	 */
	public boolean muerteCazador(){

 		if(disparo==true){
			for(Personajes personaje: registro){
				if(personaje.getPersonaje().equals("Cazador") && !personaje.estaVivo()){
					disparo=false;
					return true;
				}
			}
		}

		return false;
 	}
  //Básicamente este método recibe a muerteCazador como booleano
	public boolean objetivoCazador(String objetivo){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(objetivo)){
				if(!personaje.estaVivo()){
					break;
				}else{
					personaje.cambiaEstado(false);
					//[REVISAR] Es importante guardarlo o simplemente hay que decir quien murió EN EL DÍA
					return true;//Lo mató
				}
			}
		}
		System.out.println("Ese nombre no existe o el personaje esta muerto!. Intenta de nuevo");
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
	        if(!personaje.estaVivo()){
	          break;
	        }
	        if(personaje.getPersonaje().equals("Tonto de la aldea")){
	        	System.out.println("\033[31m¡Han linchado a "+personaje.getNombre()+" que era el tonto de la aldea!, ahora todos saben quién es. El tonto se salva por esta ocasion pero ya no tiene derecho a votar en los debates");
	        	return true;
	        }else{
	          personaje.cambiaEstado(false);//Es decir mata al personaje
	          System.out.println("\033[31mSe ha decidido linchar a "+personaje.getNombre()+" que era "+personaje.getPersonaje());
			  return true;//
	        }
	      }
	    }
	    System.out.println("Ese personaje no existe o está muerto!. Intenta de nuevo");
	    return false; //Es decir que el personaje ya está muerto o es el tonto de la aldea
		}

	public boolean encantar(String nombreEncantado){

	    for(Personajes personaje: registro){
	      if(personaje.getNombre().equals(nombreEncantado)){
	        if(!personaje.estaVivo() || personaje.getPersonaje().equals("Flautista")){
	          break;
	        }
	        else{
	          personaje.cambiaEncantado(true); //Es decir que ya lo encantó
	          return true;
	        }
	      }
	    }
	    System.out.println("No puedes encantar a esa persona o no existe!. Intenta de nuevo");
	    return false;
	}

	public void avisaGanador(char resultado){
		switch(resultado){
			case 'A':
			System.out.println("¡HAN GANADO LOS ALDEANOS!");
			break;
			case 'L':
			System.out.println("¡LOS LOBOS HAN MASACRADO TODA LA ALDEA");
			break;
			case 'F':
			System.out.println("¡EL FLAUTISTA HA GANADO, ENCANTÓ A TODOS");
		}
	}

	public char determinarGanador(){

   		int contAldea=0;
		int contLobos=0;
		int contFlautista=0;
		boolean flautistaVivo=true;
		Lista<Personajes> vivos=new Lista<>();


		for(Personajes personaje: registro){
			if(!personaje.estaVivo())
				continue;

			vivos.agregaFinal(personaje);

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
		//if(contLobos == 0 && contFlautista<vivos.getLongitud()-2)
		if(contLobos==0 && contAldea>0)
			return 'A';
		//if(contAldea == 0 && contFlautista<vivos.getLongitud()-2)
		if(contAldea==0 && contLobos>0)
		    return 'L';
		//if(contFlautista==vivos.getLongitud()-1)
		if(flautistaVivo && contFlautista==vivos.getLongitud()-1)
			return 'F';


		return 'N';

	}

	public boolean usarPocionVenenosa(String nombreDeObjetivo){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(nombreDeObjetivo)){
				if(!personaje.estaVivo()){
					break;
				}else{
				 	personaje.cambiaEstado(false);
				 	muertos.agregaFinal(personaje);
				 	pocionVenenosa=false;
					return true;//Lo acaba de matar la bruja
				}
			}
		}
		System.out.println("Ese nombre no existe o ese personaje ya esta muerto!. Intenta de nuevo.");
		return false; //Es decir ya está bien frío el compa
	}

	public boolean usarPocionResurreccion(String nombreDeObjetivo){

		for(Personajes personaje:registro){
			if(personaje.getNombre().equals(nombreDeObjetivo)){
				pocionResurreccion=false;
				if(personaje.estaVivo()){
					return true;
				}else{
					personaje.cambiaEstado(true);
					return true; //Revivió al personaje
				}
			}
		}
		System.out.println("Ese nombre no existe! Intenta de nuevo");
		return false;//No lo revivie

	}

	public boolean hayPocionVenenosa(){
		return pocionVenenosa;
	}

	public boolean hayPocionResurreccion(){
		return pocionResurreccion;
	}
  /**
   * Posible método que actualice principalmente los estados de la lista noche
   * de acuerdo a lo sucedido en el día y la noche. Este método
   * se ejecuta al final de cada día [REVISAR]
   */
  	public void actualizar(){
	  	int contLobos=0;
	  	int contEnct=0;

	  	//revisa y modifica los cambios realizados en registro de acuerdo a los personajes de noche
	  	//[IDEA] Hay que poner un caso donde el registro sea bruja, tenga pocion de resurreccion y esté muerta, en el cual no se eliminará el nodo de noche
	  	for (int i=0;!registro.get(i).getPersonaje().equals("Lobo");i++) {
			if(!registro.get(i).getPersonaje().equals("Tonto de la aldea") && !registro.get(i).getPersonaje().equals("Cazador") && !registro.get(i).getPersonaje().equals("Niña pequeña")){
	  			for (int j=0;j<noche.getLongitud();j++) {
	  				if(registro.get(i).getNombre().equals(noche.get(j).getNombre())){
	  					noche.get(j).cambiaEstado(registro.get(i).estaVivo());
	  					noche.get(j).cambiaEncantado(registro.get(i).estaEncantado());
	  					noche.get(j).cambiaProtegido(registro.get(i).estaProtegido());

	  					if (!noche.get(j).estaVivo() && noche.get(j).getPersonaje().equals("Bruja") && hayPocionResurreccion()) {
	  						continue;
	  					}

	  					if(!noche.get(j).estaVivo())
	  						noche.elimina(noche.get(j));
	  				}
	  			}
	  		}
	  	}

	  	//revisa a los encantados y los añade a encantados. También revisa si los lobos siguen vivos para seguir llamandolos en la noche
	  	for (Personajes e:registro) {
	  		if(e.estaEncantado())
	  			encantados.agregaFinal(e);
	  		if(e.getTipo().equals("Lobos"))
	  			contLobos++;
	  	}

	  	//elimina al unico "representante" de los Lobos en noche
	  	if(contLobos==0){
	  		for(int i=0;i<noche.getLongitud();i++){
	  			if(noche.get(i).getPersonaje().equals("Lobo"))
	  				noche.elimina(noche.get(i));
	  		}
	  	}
	  	//actualiza la lista de muertos por noche
	  	muertos.limpia();

  }

  	public void hayResurreccion(String nombreDelResucitado){
  		for(Personajes e:registro){
  			if(e.getNombre().equals(nombreDelResucitado) && !e.getPersonaje().equals("Tonto de la aldea") && !e.getPersonaje().equals("Cazador") && !e.getPersonaje().equals("Niña pequeña") && !e.getPersonaje().equals("Aldeano") && !e.getPersonaje().equals("Lobo")){
  				noche.agregaFinal(e);
  			}
  		}
  		Personajes[] aux=new Personajes[noche.getLongitud()];

  		for (int j=0;j<noche.toArray().length;j++) {
  			aux[j]=(Personajes)(noche.toArray()[j]);
  		}

  		Ordenamientos.quickSort(aux);

  		Lista<Personajes> nocheNueva=new Lista<>(aux);

  		noche=nocheNueva;
  	}

  	public String imprimeMuertos(){
  		if (muertos.getLongitud()==0) {
  			return "Esta ha sido una noche pacífica, ¡nadie ha muerto!";
  		}
  		String nombre="";
  		String personaje="";

  		for(int i=0;i<muertos.getLongitud();i++){
  			if(i==muertos.getLongitud()-1)
  				nombre+=muertos.get(i).getNombre()+" que era "+muertos.get(i).getPersonaje();
  			else{
  				nombre+=muertos.get(i).getNombre()+" que era "+muertos.get(i).getPersonaje()+" y ";
  			}
  		}
  		return "Todos excepto... "+nombre;
  	}

  	public String imprimeEncantados(){
  		return "Huele a cola";
  	}

}
