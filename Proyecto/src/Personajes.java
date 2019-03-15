package proyecto1;

/**
 *
 *
 *
 */
public class Personajes{

	enum Personaje {ALDEANO,LOBO,BRUJA,FLAUTISTA,VIDENTE,NIÑA_PEQUEÑA,PROTECTOR,TONTO_DE_LA_ALDEA,CAZADOR,PREDETERMINADO};
	enum TipoDePersonaje {ALDEANOS,LOBOS,FLAUTISTA,PREDETERMINADO};
	private final String nombre;
	private Personaje personaje;
	private TipoDePersonaje tipo;
	private boolean estado=true;
	private boolean encantado=false;
	private boolean protegido=false;

	public Personajes(String nombre){
		this.nombre=nombre;
	}

	public String getNombre(){
		String a="";
		return a;
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
				break;
			case "BRUJA":
				personaje=Personaje.BRUJA;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "FLAUTISTA":
				personaje=Personaje.FLAUTISTA;
				tipo=TipoDePersonaje.FLAUTISTA;
				break;
			case "NIÑA PEQUEÑA":
				personaje=Personaje.NIÑA_PEQUEÑA;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "VIDENTE":
				personaje=Personaje.VIDENTE;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "CAZADOR":
				personaje=Personaje.CAZADOR;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			case "TONTO DE LA ALDEA":
				personaje=Personaje.TONTO_DE_LA_ALDEA;
				tipo=TipoDePersonaje.ALDEANOS;
				break;
			default:
				personaje=Personaje.PREDETERMINADO;
				tipo=TipoDePersonaje.PREDETERMINADO;
				break;
		}
	}

}

/*class Aldeano extends Personajes{

	public Aldeano(String nombre){
		this.nombre=nombre;
	}
}

class Bruja extends Personajes{

	private boolean pocionMuerte=true;
	private boolean pocionVida=true;

	public Bruja(String nombre){
		this.nombre=nombre;
	}

	public void usarPocionMuerte(){
		pocionMuerte=false;
	}

	public void usarPocionVida(){
		poscionMuerte=false;
	}


}

class Vidente extends Personajes{

	public Vidente(String nombre){
		this.nombre=nombre;
	}
}

class NiñaPequeña extends Personajes{

}

class Protector extends Personajes{

}

class TontoDeLaAldea extends Personajes{

}

class Cazador extends Personajes{



}

class Lobo extends Personajes{

	public Lobo(String nombre){
		this.nombre=nombre;
	}

	public void usarPocionMuerte(){

	}
}

class Flautista extends Personajes{

}*/