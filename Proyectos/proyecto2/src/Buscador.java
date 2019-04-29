package proyecto2;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 *
 */

public class Buscador{
    private static boolean verificaBusqueda(String busqueda){
        if(busqueda.length()>200)
            return false;

        for (int i = 0; i < busqueda.length(); i++) {
            if(busqueda.charAt(i)!=',' && busqueda.charAt(i)!='?' && busqueda.charAt(i)!='¿' && busqueda.charAt(i)!='!' && busqueda.charAt(i)!='¡' && (int)(busqueda.charAt(i))!=46 && busqueda.charAt(i)!=' ' && busqueda.charAt(i)!=':' && busqueda.charAt(i)!=';')
                return true;
        }

        return false;
    }
    public static void main(String[] args) {
        Busqueda busqueda=new Busqueda();
        int numArchivos=0;
        boolean estado=false;
        String aBuscar="";
        String ruta="";
        String opcion;
        Scanner in=new Scanner(System.in);

        System.out.println("******************************************************");
        System.out.println("**************BUSCADOR DE COINDCIDENCIAS**************");
        System.out.println("******************************************************\n");

        System.out.println("REGLAS: \n");
        System.out.println("- No se pueden buscar cadenas de más de 200 caracteres");
        System.out.println("- Solo se pueden insertar en archivos de extensión .txt\n");

        System.out.println("INSTRUCCIONES: \n");
        System.out.println("- Primero tendrás que indicar el número de archivos que vas a introducir");
        System.out.println("- Después insertaras la RUTA ABSOLUTA de cada archivo. Si el archivo no existe o está vacío, la ruta está mal escrita o el archivo no es del formato especificado.\n   El programa le pedirá que ingrese de nuevo la ruta");
        System.out.println("- Por ultimo ingresarás la búsqueda tomando en cuenta las reglas mencionadas anteriormente y se imprimirá en pantalla el nombre de cada archivo de acuerdo a su simiitud\n   con la búsqueda (si el archivo tiene similitud 0 no se mostrará)\n");

        System.out.println("[PRESIONE ENTER PARA CONTINUAR]");
        in.nextLine();

        do{

        System.out.println("Que quieres hacer?");
        System.out.println("a. Agregar archivos a la búsqueda");
        System.out.println("b. Empezar busqueda");
        System.out.println("c. Salir\n");
        System.out.print(">: ");

        opcion=in.nextLine();

        switch(opcion){
            case "a":
            while(!estado){
                try {
                    System.out.print("Cuantos archivos quieres ingresar? >: ");
                    numArchivos = in.nextInt();
                    if(numArchivos==0){
                        estado=false;
                        System.out.println("Debes introducir un número entero distinto de cero. Intenta de nuevo\n");
                    }else
                        estado = true;
                } catch (InputMismatchException e) {
                    System.out.println("Debes introducir un número entero distinto de cero. Intenta de nuevo\n");
                    estado=false;
                    in.nextLine();
                }
            }

            estado=false;
            in.nextLine();

            for (int i = 0; i < numArchivos; i++) {
                Ficheros nuevoFichero;
                do{
                    nuevoFichero=null;
                    System.out.println("NOTA: La ruta del archivo a leer debe estar bien escrita y el archivo (obviamente) debe de existir. No puedes agregar archivos que ya haz agregado");
                    System.out.print("Ingresa la ruta absoluta del archivo numero "+(i+1)+"\n>:");
                    ruta=in.nextLine();

                    nuevoFichero=new Ficheros(ruta);

                }while(nuevoFichero.verificarFichero()=='N' || busqueda.yaSeAgrego(nuevoFichero));
                busqueda.agregarFicheros(nuevoFichero);
                ruta="";
            }
            System.out.println("[PRESIONA ENTER PARA CONTINUAR]");
            in.nextLine();
            break;

            case "b":
            if(busqueda.getNumFicheros()==0){
                System.out.println("No haz agregado ningún fichero! Primero agrega un archivo para comenzar la búsqueda\n");
                break;
            }
            do{
                System.out.println("NOTA: La cadena a buscar debe ser menor o igual a 200 caracteres. Caracteres como ¿?¡!,;: no se tomaran en cuenta para la busqueda");
                System.out.print("Ingresa tu busqueda >: ");
                aBuscar=in.nextLine();
            }while(!verificaBusqueda(aBuscar));

            busqueda.procesarBusqueda(aBuscar);

            busqueda.calcularSimilitud();

            System.out.println();

            System.out.println("[PRESIONA ENTER PARA CONTINUAR]\n");

            System.out.println("**********RESULTADO DE LA BUSQUEDA**********");

            busqueda.imprimirResultados();

            System.out.println("[PRESIONA ENTER PARA CONTINUAR]");
            in.nextLine();

            default:
            break;
        }

        if(opcion.equals("c")){
            break;
        }

        }while(opcion!="a" && opcion!="b" && opcion!="c");
    }
}
