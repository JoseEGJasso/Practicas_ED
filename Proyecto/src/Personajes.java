package proyecto1;

/**
 * Clase Personajes, en la que usaremos variables enum en vez de clases internas con los nombre de cada
 * personaje, de esta forma optimizamos el guardado de Personaje y TipoDePersonaje para cada jugador.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Personajes implements Comparable<Personajes>{

	enum Personaje {ALDEANO,LOBO,BRUJA,FLAUTISTA,VIDENTE,NIÑA_PEQUEÑA,PROTECTOR,TONTO_DE_LA_ALDEA,CAZADOR,PREDETERMINADO};
	enum TipoDePersonaje {ALDEANOS,LOBOS,FLAUTISTA,PREDETERMINADO};
	private final String nombre;
	private Personaje personaje;
	private TipoDePersonaje tipo;
	private int orden=-1;
	private boolean estado=true;
	private boolean encantado=false;
	private boolean protegido=false;

 	/**
	 * Constructor de Personaje, recibe dos cadenas de caracterter.La primera recibe el nombre del jugador,
	 * La segunda el personaje que se le asignará de forma aleatoria en la clase Juego, que dependiendo de la cadena
	 * se le será modificado con el métdoo setPersonaje las variables enum Personaje y TipoDePersonaje.
	 *
	 * @param nombre,personajeAsignar;
	 *
	 */
	public Personajes(String nombre,String personajeAsignar){
		this.nombre=nombre;
		this.setPersonaje(personajeAsignar);
	}

	/**
	 * Método Getter de nomnbre. Devuelve el nombre del jugador.
	 *
	 * @return a;
	 */
	public String getNombre(){
		return nombre;
	}

	/**
	 * Método Getter de Personaje. Dependiendo de la variable enum que el personaje tenga , se regresa
	 * una cadeana con el nombre del personaje.
	 *
	 * @return cadena de personajeL;
	 */
	public String getPersonaje(){
		if(personaje==Personaje.ALDEANO)
			return "Aldeano";
		if(personaje==Personaje.BRUJA)
			return "Bruja";
		if(personaje==Personaje.LOBO)
			return "Lobo";
		if(personaje==Personaje.FLAUTISTA)
			return "Flautista";
		if(personaje==Personaje.VIDENTE)
			return "Vidente";
		if(personaje==Personaje.NIÑA_PEQUEÑA)
			return "Niña pequeña";
		if(personaje==Personaje.PROTECTOR)
			return "Protector";
		if(personaje==Personaje.TONTO_DE_LA_ALDEA)
			return "Tonto de la aldea";	
		if(personaje==Personaje.CAZADOR)
			return "Cazador";
		return "Predeterminado";
	}
	/**
	 * Método Getter de TipoDePersonaje. Retorna el tipo como cadena dependiendo de la variable
	 * enum TipoDePersonaje.
	 *
	 * @return cadena de tipo de personaje;
	 */
	public String getTipo(){
		if(tipo==TipoDePersonaje.ALDEANOS)
			return "Aldeanos";
		if(tipo==TipoDePersonaje.LOBOS)
			return "Lobos";
		if(tipo==TipoDePersonaje.FLAUTISTA)
			return "Flautista";
		return "Predeterminado";
	}
	/**
	 * Método Getter del booleano encantado.
	 *
	 * @return encatado;
	 */
	public boolean estaEncantado(){
		return encantado;
	}
	/**
	 * Método Getter del booleano protegido.
	 *
	 * @return protegido;
	 */
	public boolean estaProtegido(){
		return protegido;
	}
	/**
	 * Método Getter del estado.
	 *
	 * @return estado;
	 */
	public boolean estaVivo(){
		return estado;
	}
	/**
	 * Método Setter de estado.
	 *
	 * @param estado;
	 */
	public void cambiaEstado(boolean estado){
		this.estado=estado;
	}
	/**
	 * Método Setter de encatado.
	 *
	 * @param encantado;
	 */
	public void cambiaEncantado(boolean encantado){
		this.encantado=encantado;
	}
	/**
	 * Método Setter de protegido
	 *
	 * @param protegido;
	 */
	public void cambiaProtegido(boolean protegido){
		this.protegido=protegido;
	}
  /**
	 * Método Setter de las variables personaje y tipo. Dependiendo de la cadena "personajeAsignar", determina el tipo
	 * y personaje tomando valores de los atributos eum de Personaje y TipoDePersonaje.Asimismo establece el orden del
	 * personaje que será empleado para determinar el orden de jugadores que despiertan en la noche.
	 *
	 * @param personajeAsignar;
	 */
	public void setPersonaje(String personajeAsignar){
		switch(personajeAsignar.toUpperCase()){
			case "ALDEANO":
				personaje=Personaje.ALDEANO;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "LOBO":
				personaje=Personaje.LOBO;
				tipo=TipoDePersonaje.LOBOS;
				orden=2;
				break;
			case "BRUJA":
				personaje=Personaje.BRUJA;
				tipo=TipoDePersonaje.ALDEANOS;
				orden=3;
				break;
			case "FLAUTISTA":
				personaje=Personaje.FLAUTISTA;
				tipo=TipoDePersonaje.FLAUTISTA;
				orden=4;
				break;
			case "NIÑA PEQUEÑA":
				personaje=Personaje.NIÑA_PEQUEÑA;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "VIDENTE":
				personaje=Personaje.VIDENTE;
				tipo=TipoDePersonaje.ALDEANOS;
				orden=0;
				break;
			case "CAZADOR":
				personaje=Personaje.CAZADOR;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "TONTO DE LA ALDEA":
				personaje=Personaje.TONTO_DE_LA_ALDEA;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "PROTECTOR":
				personaje=Personaje.PROTECTOR;
				tipo=TipoDePersonaje.ALDEANOS;
				orden=1;
				break;
			default:
				personaje=Personaje.PREDETERMINADO;
				tipo=TipoDePersonaje.PREDETERMINADO;
				break;
		}
	}

	@Override
	public int compareTo(Personajes o){
		if(orden<o.orden)
			return -1;
		if(orden>o.orden)
			return 1;
		return 0;
	}

}