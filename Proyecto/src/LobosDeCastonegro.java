package proyecto1;

import java.util.Scanner;
import proyecto1.Juego;

/**
 *
 *
 *
 */

 public class LobosDeCastonegro{

  	private static boolean nombresIguales(String[] nombres){
  		if(nombres==null)
  			return true;
  		for(int i=0;i<nombres.length;i++){
  			if(nombres[i]==null)
  				return true;
  			for(int j=0;j<nombres.length;j++){
  				if(j==i)
  					continue;
  				if(nombres[i].equalsIgnoreCase(nombres[j]))
  					return true;
  			}
  		}
  		return false;
  	}

 	public static void main(String[] args) {

 		Scanner sc=new Scanner(System.in);
 		String[] jugadores;
 		String linchado;
 		String victimaCazador;
 		int numJugadores;

 		System.out.println("**************************************");
 		System.out.println("***BIENVENIDO A LOBOS DE CASTONEGRO***");
 		System.out.println("**************************************\n");


 		System.out.println("ANUNCIO. Este programa es una herramienta de apoyo al narrador del juego Lobos de Casttonegro\n");
 		System.out.println("DESARROLLO DEL JUEGO: ");
 		System.out.println("A. Al inicio de la partida el narrador ingresará los nombres de los jugadores para que el programa les asigne un personaje.");
 		System.out.println("B. El narrador duerme a la aldea. Para ello dice: 'Se hace de noche. La aldea duerme. Los jugadores cierran los ojos' Todos los jugadores bajan la cabeza y cierran los ojos.\n  Tras esto el narrador va llamando a los distintos personajes nocturnos que se hayan elegido para la partida");
 		System.out.println("C. Durante la noche el narrador ingresará el nombre las victimas de los Hombres Lobo y de la Bruja.");
 		System.out.println("D. Mientras el Flautista viva, el narrador seleccionará a los jugadores encantados.");
 		System.out.println("E. Cada que alguien muera, el narrador seleccionará el nombre del jugador muerto.");
 		System.out.println("F. Una vez que los ha llamado a todos, el narrador despierta a la aldea. Para ello dice: ”¡Amanece en la aldea! Todo el mundo despierta y abre los ojos. Todos excepto...”.\n  El narrador anuncia en ese momento el o los jugadores que han perecido durante la noche.");
 		System.out.println("G. Cuando le parezca oportuno, el narrador da paso a la votacion (la cual el programa no cubre). Cuando lo indique el narrador, cada jugador señala con el dedo a su sospechoso.");

 		System.out.println("[PRESIONA ENTER PARA INICIAR LA PARTIDA]");
 		sc.nextLine();
 		do{
	 		System.out.println("NOTA: Debes ingresar mínimo 12 jugadores");
	 		System.out.print("Ingresa el numero de jugadores: ");
	 		numJugadores=sc.nextInt();
	 		jugadores=new String[numJugadores];
 		}while(numJugadores<=12);

 		do{
	 		System.out.println("NOTA: Se deben ingresar nombres estrictamente diferentes (Ejemplo: juan y Juan se consideren iguales)");
	 		System.out.println("NOTA IMPORTANTE: Se debe presionar dos veces ENTER para pasar a la siguiente asignación de nombre");

	 		for(int i=0;i<jugadores.length;i++){
	 			sc.nextLine();
	 			System.out.print("Ingresa el el nombre del jugador "+(i+1)+": ");
	 			jugadores[i]=sc.nextLine();
	 		}
 		}while(nombresIguales(jugadores));

 		Juego juego=new Juego(jugadores);

 		System.out.println("\n*******BIENVENIDOS SEAN A LA ALDEA DE CASTONEGRO******\n");
 		System.out.println("¡Es hora de empezar la cacería!");
 		do{
	 		System.out.println("\n'Se hace de noche. La aldea duerme. Los jugadores cierran los ojos'");
	 		juego.despertarJugadores();
	 		System.out.println("\n'¡Amanece en la aldea! Todo el mundo despierta y abre los ojos. "+juego.imprimeMuertos());
	 		juego.actualizar();
	 		if(juego.muerteCazador()){
	 			do{
	 				System.out.print("Desangrandose apunto de morir está el cazador y con su último esfuerzo decide disparar a ...\n>: ");
	 				victimaCazador=sc.nextLine();
	 			}while(!juego.objetivoCazador(victimaCazador));
	 		}
	 		do{
	 			System.out.print("'Es hora de la votación, ¿a quién se decidió linchar?...' \n>:");
	 			linchado=sc.nextLine();
	 		}while(!juego.linchar(linchado));
	 		juego.actualizar();
	 		if(juego.muerteCazador()){
	 			do{
	 				System.out.print("Desangrandose apunto de morir está el cazador y con su último esfuerzo decide disparar a ...\n>: ");
	 				victimaCazador=sc.nextLine();
	 			}while(!juego.objetivoCazador(victimaCazador));
	 		}
 		}while(juego.determinarGanador()!='A' && juego.determinarGanador()!='L' && juego.determinarGanador()!='F');
 		juego.avisaGanador(juego.determinarGanador());
 	}
 }