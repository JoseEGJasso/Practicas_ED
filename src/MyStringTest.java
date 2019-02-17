/**
 * 
 * @author González Jasso José Eduardo
 * @author Magnani Dozal Diego
 *
 */

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

public class MyStringTest {
	
	/*
	 * caso 1. Se utiliza un objeto instanciado con el primer constructor
	 * caso 2. Se utiliza un objeto instanciado con el segundo constructor
	 * caso 3. Se utiliza un objeto instanciado con el tercer constructor
	 */
	
	private static MyString cadena1;
	private static MyString cadena2;
	private static MyString cadena3;
	
	@BeforeClass
	public static void constructores() {
		char[] arreglo= {'e','s','t','o',' ','e','s',' ','u','n','a',' ','p','r','u','e','b','a'};
		
		cadena1=new MyString();
		cadena2=new MyString("prueba con cadena String");
		cadena3=new MyString(arreglo);
	}

	@Test
	public void testToString() {
		//caso 1
		assertEquals("", cadena1.toString());
		//caso 2
		assertEquals("prueba con cadena String",cadena2.toString());
		//caso 3
		assertEquals("esto es una prueba", cadena3.toString());
	}
	@Test
	public void testSubString() {
		//caso 1
		assertEquals(new MyString(), cadena1.subString(0));
		//caso 2
		//caso 3
		assertEquals(new MyString("prueba"), cadena3.subString(12));
	}

	@Test
	public void testCharAt() {
		
		try {
			//caso 1
			assertEquals(' ', cadena1.charAt(9));
			//caso 2
			assertEquals('c', cadena2.charAt(11));
			//caso 3
			assertEquals('p', cadena3.charAt(5));
			//En caso de excepción
			fail("Se esperaba excepcion IndexOutOfBoundsException");
		}catch(IndexOutOfBoundsException indexoutofboundsexception){}
	}

	@Test
	public void testEqualsObject() {
		
		//caso 1
		assertEquals(true, cadena1.equals(new MyString()));
		//caso 2
		assertEquals(false, cadena2.equals(new MyString("prueba con cadena Strong")));
		//caso 3
		assertEquals(true, cadena3.equals(new MyString("esto es una prueba")));
	}

	@Test
	public void testEndsWith() {
		char[] prueba =new char[] {'u','e','b','a'};
		
		//caso 1
		assertEquals(false, cadena1.endsWith(new MyString("hola cola")));
		//caso 2
		assertEquals(false, cadena2.endsWith(new MyString("Strong")));
		//caso 3
		assertEquals(true, cadena3.endsWith(new MyString(prueba)));
	}

	@Test
	public void testTrim() {
		
		//variables auxiliares con espacios para poner en acción al método
		MyString cadena22=new MyString("         prueba espacios     ");
		char[] prueba33=new char[] {' ',' ',' ','p','r','u','e','b','a',' ',' ',' '};
		MyString cadena33=new MyString(prueba33);
		
		//caso 1
		assertEquals(new MyString(), cadena1.trim());
		//caso 2
		assertEquals(new MyString ("prueba espacios"), cadena22.trim());
		//caso 3
		assertEquals(new MyString("prueba"), cadena33.trim());
	}

	@Test
	public void testIndexOf() {
		//caso 1
		assertEquals(-1, cadena1.indexOf('\u0000'));
		//caso 2
		assertEquals(-1, cadena2.indexOf('x'));
		//caso 3
		assertEquals(3, cadena3.indexOf('o'));
	}

	@Test
	public void testConcat() {
		//caso 1
		assertEquals(new MyString("concat de vacio"), cadena1.concat(new MyString("concat de vacio")));
		//caso 2
		assertEquals(new MyString ("prueba con cadena String de concat"), cadena2.concat(new MyString(" de concat")));
		//caso 3
		assertEquals(new MyString("esto es una prueba de concat"), cadena3.concat(new MyString(" de concat")));
	}

	@Test
	public void testIsEmpty() {
		//caso 1
		assertEquals(true, cadena1.isEmpty());
		//caso 2
		assertEquals(false, cadena2.isEmpty());
		//caso 3
		assertEquals(false, cadena3.isEmpty());
	}

	@Test
	public void testLength() {
		//caso 1
		assertEquals(0, cadena1.length());
		//caso 2
		assertEquals(24, cadena2.length());
		//caso 3
		assertEquals(18, cadena3.length());
	}

}