/**
 * 
 * @author González Jasso José Eduardo
 * @author Magnani Dozal Diego
 * 
 */

public class MyString {
	
	private char[] myString;
	
	/**
	 * Constructor predeterminado que genera una cadena vacía
	 */
	public MyString() {
		
		myString=new char[0];
	}
	/**
	 * Constructor que recibe un parámetro de tipo String y
	 * asigna cada caracter del parámetro a un objeto MyString.
	 * 
	 * @param cadena; Variable de tipo String que se va a asignar al atributo myString
	 */
	public MyString(String cadena) {
		
		myString=new char[cadena.length()];
		
		for(int i=0;i<cadena.length();i++) {
			myString[i]=cadena.charAt(i);
		}
	}
	/**
	 * Constructor que recibe un arreglo de caracteres y asigna cada uno
	 * a un objeto MyString
	 * 
	 * @param cadenaCaracteres; Arreglo de caracteres que se van a asignar 
	 * al atributo myString
	 */
	public MyString(char[] cadenaCaracteres) {
		
		myString=new char[cadenaCaracteres.length];
		
		for(int i=0;i<cadenaCaracteres.length;i++) {
			myString[i]=cadenaCaracteres[i];
		}
	}
	/**
	 * Asigna cada valor del atributo myString propio del objeto
	 * a una variable String
	 * 
	 * @return String; Representación en tipo String del objeto MyString
	 */
	@Override
	public String toString() {
		
		String cadenaMS="";
		
		for(int i=0;i<myString.length;i++) {
			cadenaMS+=myString[i];
		}
		
		return cadenaMS;
	}
	/**
	 * Extrae una subcadena del obejeto MyString desde el indice indicado
	 * 
	 * @param indice; Parámetro de tipo int que indica el índice desde 
	 * el cual se va a extraer la subcadena
	 * @return MyString; Subcadena que se extrajo del objeto MyString
	 */
	public MyString subString(int indice) {
		MyString subMyString;
		
		if(indice<0 || indice>myString.length) {
			subMyString=new MyString();
		}else {
			int cont=0;
			char[] subCadena=new char[myString.length-indice];
			
			for(int i=indice;i<myString.length;i++)
				subCadena[cont++]=myString[i];
			
			subMyString=new MyString(subCadena);
		}
		
		return subMyString;
	}
	/**
	 * Busca y devuelve el caracter indicado por el parametro indice; en caso de que
	 * el valor del parámetro sea menor a cero o supere la longitud del objeto MyString,
	 * se lanza una excepcion 
	 * 
	 * @param indice; Parámetro de tipo int que indica la posición que se va a regresar 
	 * del objeto MyString
	 * @return char; Se devuelve el caracter indicado de acuerdo al indice
	 * @throws IndexOutOfBoundsException
	 */
	public char charAt(int indice) throws IndexOutOfBoundsException{
		
		if(indice<0 || indice>myString.length)
			throw new IndexOutOfBoundsException();
		
		return myString[indice];
	}
	/**
	 * Evalúa si el objeto recibido como parámetro es igual al objeto MyString, analizando
	 * si su atributo myString es completamente idéntico
	 * 
	 * @param algunObjeto; Parámetro que indica el objeto a evaluar
	 * @return boolean; Devuelve si el objeto del parámetro es igual al objeto MyString o no
	 */
	@Override
	public boolean equals(Object algunObjeto) {
		boolean iguales=true;

		
		if(algunObjeto instanceof MyString) {
			
			MyString objectMyString=(MyString)algunObjeto;
			
			for(int i=0;i<myString.length && i<objectMyString.length();i++) {
				if(myString[i]!=objectMyString.charAt(i)) {
					iguales=false;
					break;
				}
			}
		}
			
		return iguales;
	}
	/**
	 * Evalúa si el parámetro es sufijo del objeto MyString
	 * 
	 * @param miCadena; Variable que indica la cadena a evaluar como sufijo del objeto MyString
	 * @return boolean; Devuelve si el parámetro es o no sufijo del objeto MyString
	 */
	public boolean endsWith(MyString miCadena) {
		int longitud=miCadena.length();
		int contador=0;
		int contIguales=0;
		
		if(miCadena.length()<=myString.length) {

			for(int i=myString.length-longitud;i<myString.length;i++) {
				if(myString[i]==miCadena.charAt(contador++))
					contIguales++;
			}
			if(contIguales==miCadena.length())
				return true;	
		}
		return false;
	}
	/**
	 * Busca si existen o no espacios alrededor del atributo del objeto MyString,
	 * y en todo caso de que existan devuelve el atributo pero sin los espacios
	 * que lo rodean
	 * 
	 * NOTA DE IMPLEMENTACION: Utilizamos dos estructuras for, una para determinar la posición 
	 * del último caracter (no espacio) del atributo del objeto MyString y la otra comenzando desde
	 * el final del atributo para determinar la posición del primer caracter (no espacio). Estos
	 * dos valores son importantes para determinar si el atributo está vacío y en todo caso para indicar el 
	 * tamaño de la parte no espaciada del atributo
	 * 
	 * @return MyString; Atributo del objeto MyString sin espacios que lo rodeen
	 */
	public MyString trim() {
		int primerCaracter=0,ultimoCaracter=0;
		int contador=0;
		char[] cadenaSEspacio;
		MyString cadEspacio;
		
		for(int i=0;i<myString.length;i++) {
			if((int)myString[i]>=33)
				ultimoCaracter=i;
		}
		
		if(ultimoCaracter==0)
			return cadEspacio=new MyString();
		
		for(int j=myString.length-1;j>=0;j--) {
			if((int)myString[j]>=33)
				primerCaracter=j;
		}
		
		cadenaSEspacio=new char[ultimoCaracter-primerCaracter+1];
		
		for(int i=primerCaracter;i<=ultimoCaracter;i++)
			cadenaSEspacio[contador++]=myString[i];
		
		cadEspacio=new MyString(cadenaSEspacio);
		
		return cadEspacio;
			
	}
	/**
	 * Busca si el caracter ingresado como parámetro existe en el objeto MyString
	 * y en caso de que exista devuelve su posición de acuerdo al atributo myString del objeto
	 * en caso contrario devuelve un -1
	 * 
	 * @param caracter
	 * @return int; Devuelve la posición en donde se encuentra el caracter o devuelve un -1 si no existe 
	 */
	public int indexOf(char caracter) {
		for(int i=0;i<myString.length;i++) {
			if(myString[i]==caracter)
				return i;
		}
		return -1;
	}
	/**
	 * Concatena la cadena ingresada con el objeto MyString
	 * 
	 * @param cadena; Variable MyString que se va a concatenar
	 * @return MyString; Devuelve un objeto MyString debidamente concatenado
	 */
	public MyString concat(MyString cadena) {
		if(cadena.length()==0)
			return cadena;
		
		char[] cadenaConcat=new char[myString.length+cadena.length()];
		int cont=0;
		
		for(int i=0;i<myString.length;i++)
			cadenaConcat[i]=myString[i];
		for(int i=myString.length;i<cadenaConcat.length;i++)
			cadenaConcat[i]=cadena.charAt(cont++);
		
		MyString concatMyString=new MyString(cadenaConcat);
		
		return concatMyString;
	}
	/**
	 * Verifica si el atributo del objeto MyString está vacío o no
	 * 
	 * @return boolean; Se devuelve true si el objeto esta vacío o en caso contrario false
	 */
	public boolean isEmpty() {
		if(this.length()==0)
			return true;
		return false;
	}
	/**
	 * Devuelve la longitud del objeto MyString
	 * 
	 * @return int; longitud del objeto MyString
	 */
	public int length() {
		return myString.length;
	}
}

