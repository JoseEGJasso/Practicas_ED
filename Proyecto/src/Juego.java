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

		for(Personajes e:registro)
			System.out.println("Nombre: "+e.getNombre()+"    Personaje:"+e.getPersonaje());
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
				System.out.println("Se duerme la Vidente...\n###################################################################################");
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
							sc.nextLine();
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
							sc.nextLine();
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
			System.out.println("Los encantados hasta ahora son: "+imprimeEncantados()+"\n");

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
					System.out.println("\u001B[32mNombre: "+personaje.getNombre()+"  Personaje: "+personaje.getPersonaje()+"\u001B[0m");
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
	 * @return boolean;
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
 	/**
 	 * Este método se ejecuta sólo si el cazador ha muerto y recibe un paramétro que indica
 	 * el nombre del jugador al que disparará
 	 * @param objetivo;
 	 * @return boolean;
 	 */
	public boolean objetivoCazador(String objetivo){

		for(Personajes personaje: registro){
			if(personaje.getNombre().equals(objetivo)){
				if(!personaje.estaVivo()){
					break;
				}else{
					personaje.cambiaEstado(false);
					System.out.println("El cazador ha matado a "+personaje.getNombre()+" que era: "+personaje.getPersonaje());
					return true;//Lo mató
				}
			}
		}
		System.out.println("Ese nombre no existe o el personaje esta muerto!. Intenta de nuevo");
		return false;//Es decir que ya estaba muerto
	}

	/**
	 * Este método sirve para indicar quien va a ser el enjuiciado y condenado a muerte durante el debate
	 * del día, recibe un parámetro que indica la persona que va a morir
	 * @param nombre;
	 * @return boolean;
	 */
	public boolean linchar(String nombre){

	    for(Personajes personaje: registro){
	      if(personaje.getNombre().equals(nombre)){
	        if(!personaje.estaVivo()){
	          break;
	        }
	        if(personaje.getPersonaje().equals("Tonto de la aldea")){
	        	System.out.println("\u001b[33m¡Han linchado a "+personaje.getNombre()+" que era el tonto de la aldea!, ahora todos saben quién es. El tonto se salva por esta ocasion pero ya no tiene derecho a votar en los debates\u001B[0m");
	        	return true;
	        }else{
	          personaje.cambiaEstado(false);//Es decir mata al personaje
	          System.out.println("\u001b[33mSe ha decidido linchar a "+personaje.getNombre()+" que era "+personaje.getPersonaje()+"\u001B[0m");
			  return true;//
	        }
	      }
	    }
	    System.out.println("Ese personaje no existe o está muerto!. Intenta de nuevo");
	    return false; //Es decir que el personaje ya está muerto o es el tonto de la aldea
		}
	/**
	 * Método que se ejecuta cuando es el turno del Flutista, recibe un parametro que indica el personaje
	 * que va a ser encantado
	 * @param nombreEncantado;
	 * @return boolean;
	 */
	public boolean encantar(String nombreEncantado){

	    for(Personajes personaje: registro){
	      if(personaje.getNombre().equals(nombreEncantado)){
	        if(!personaje.estaVivo() || personaje.getPersonaje().equals("Flautista")){
	          break;
	        }
	        else{
	          personaje.cambiaEncantado(true);
	          encantados.agregaFinal(personaje);
	          return true;
	        }
	      }
	    }
	    System.out.println("No puedes encantar a esa persona o no existe!. Intenta de nuevo");
	    return false;
	}
	/**
	 * Recibe un parámetro para referir que mensaje se va a imprimir dependiendo de determinarGanador()
	 * @param resultado;
	 */
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
			break;
		}
	}
	/**
	 * Método que se encarga de contar el numero de Aldeanos, Lobos y Encantados vivos
	 * Si no hay Lobos vivos y hay más de un aldeano, ganan los aldeanos. Si no hay aldeanos
	 * vivos y hay más de un lobo vivo ganan los lobos. Si el flautista sigue vivo y el numero
	 * de encantados es igual a la cantidad de personajes vivos menos uno, gana el flautista
	 * @return char;
	 */
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

			if(personaje.getPersonaje().equals("Flautista") && !personaje.estaVivo()){
				flautistaVivo=false;
				contFlautista=0;
				continue;
			}
			if(personaje.estaEncantado() && flautistaVivo)
				contFlautista++;
		}


		if(contLobos==0 && contAldea>0)
			return 'A';
		if(contAldea==0 && contLobos>0)
		    return 'L';
		if((flautistaVivo && contFlautista==vivos.getLongitud()-1) || (flautistaVivo && contLobos==0 && contAldea==0))
			return 'F';


		return 'N';

	}
	/**
	 * Método que simula usar una pocion venenosa, recibe un parámetro que representa
	 * el objetivo de la poscion. Solo se puede utilizar una vez por partida
	 * @param nomrebreDeObjetivo;
	 * @return boolean;
	 */
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
	/**
	 * Método que simula usar una pocion de resurrección, recibe un parámetro que representa
	 * el objetivo de la pocion. Solo se puede utilizar una vez por partida
	 * @param nomrebreDeObjetivo;
	 * @return boolean;
	 */
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
	/**
	 * Regresa false si la pocion ya se utizó y true si aun no se ha moificado;
	 * @return boolean;
	 */
	public boolean hayPocionVenenosa(){
		return pocionVenenosa;
	}
	/**
	 * Regresa false si la pocion ya se utizó y true si aun no se ha usado;
	 * @return boolean;
	 */
	public boolean hayPocionResurreccion(){
		return pocionResurreccion;
	}

  /**
   * Método que principalmente actualiza las variables de la Lista noche de acuerdo
   * a lo modificado en registro. Esto durante el día y la noche
   */
  	public void actualizar(){
	  	int contLobos=0;
	  	int contEnct=0;

	  	//revisa y modifica los cambios realizados en registro de acuerdo a los personajes de noche
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

	  	for(int i=0;i<registro.getLongitud();i++){
	  		if(registro.get(i).estaEncantado() && !registro.get(i).estaVivo())
	  		for(int j=0;j<encantados.getLongitud();j++){
	  			if(registro.get(i).getNombre().equals(encantados.get(j).getNombre())){
	  				encantados.get(j).cambiaEstado(registro.get(i).estaVivo());

	  				if(!encantados.get(j).estaVivo())
	  					encantados.elimina(encantados.get(j));
	  			}
	  		}
	  	}

	  	//Revisa si los lobos siguen vivos para seguir llamandolos en la noche
	  	for (Personajes e:registro) {
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
  	/**
  	 * Si la bruja ha decidido revivir a alguien  se jecuta este método
  	 * y su principal objetivo es, si el personaje es un personaje nocturno,
  	 * modificha la lista noche y se reoredena;
  	 * @param nombreDelResucitado;
  	 */
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
  	/**
  	 * Método auxiliar que imprime la lista de muertos actuales
  	 * @return String;
  	 */
  	public String imprimeMuertos(){
  		if (muertos.getLongitud()==0) {
  			return "Esta ha sido una noche pacífica, ¡nadie ha muerto!";
  		}
  		String nombre="";
  		String personaje="";
  		String complemento="";

  		if(muertos.getLongitud()<2){
  			complemento=".Y murio inevitablemente";
  		}else{
  			complemento=".Y murieron inevitablemente";
  		}


  		for(int i=0;i<muertos.getLongitud();i++){
  			if(i==muertos.getLongitud()-1)
  				nombre+=muertos.get(i).getNombre()+" que era "+muertos.get(i).getPersonaje();
  			else{
  				nombre+=muertos.get(i).getNombre()+" que era "+muertos.get(i).getPersonaje()+" y ";
  			}
  		}
  		return "\u001b[33mTodos excepto... "+nombre+complemento+"\u001B[0m";
  	}
  	/**
  	 * Método auxiliar que imprime la lista de encantado no muertos actuales
  	 * @return String;
  	 */
  	public String imprimeEncantados(){
  		String encantadoss="";

  		for(int i=0;i<encantados.getLongitud();i++){
  			if(i==encantados.getLongitud()-1)
  				encantadoss+=encantados.get(i).getNombre();
  			else{
  				encantadoss+=encantados.get(i).getNombre()+", ";
  			}
  		}

  		return encantadoss;
  	}
}

