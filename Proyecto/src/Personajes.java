package proyecto1;

/**
 *
 *
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

	public Personajes(String nombre,String personajeAsignar){
		this.nombre=nombre;
		setPersonaje(personajeAsignar);
	}

	public String getNombre(){
		String a="";
		return a;
	}

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

	public boolean estaEncantado(){
		return encantado;
	}

	public boolean estaProtegido(){
		return protegido;
	}

	public boolean estaVivo(){
		return estado;
	}

	public void cambiaEstado(boolean estado){
		this.estado=estado;
	}

	public void cambiaEncantado(boolean encantado){
		this.encantado=encantado;
	}

	public void cambiaProtegido(boolean protegido){
		this.protegido=protegido;
	}

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
