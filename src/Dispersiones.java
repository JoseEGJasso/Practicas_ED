/**
 * Clase Dispersiones que modela tres dispersiones conocidas :
 * XOR
 * BJ
 * DJB
 * @param <K>
 * @param <V>
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Dispersiones{

    /**
     * Método auxiliar que recibe 4 bytes y los combina para regresar un entero.
     *
     * @param byte : a, b, c, d
     * @return int
     */
    private static int combina(byte a,byte b,byte c, byte d){
        return ((a & 0xFF) << 24) | ((b & 0xFF) << 16) | ((c & 0xFF) << 8) |((d & 0xFF));
    }

    /**
     * Implementación de la dispersión XOR.
     * El dipersor se crea a partir de un arreglo de byte "a". Dicho arreglo pasa por una
     * verificación para que su módulo 4 sea distinto de 0. A continuación se iguala "a" a
     * un nuevo arreglo de bytes llamado "nuevo"
     * Después viene el cuerpo principal de XOR en donde se combinan los bytes de  nuevo
     * para poder guardarlos en un entero c, que será el que se retorne.
     *
     * @param a
     * @return r
     */
    public static int dispersionXOR(byte[] a){
        byte[] nuevo;



        if(a.length % 4 != 0){
            nuevo=new byte[a.length+(4 - a.length % 4)];

            for(int i=0;i<a.length;i++)
                nuevo[i]=a[i];


        } else{

            nuevo=a;
        }



        int r=0;

        for (int i = 0; i < nuevo.length; i+=4) {
            int c=combina(nuevo[i],nuevo[i+1],nuevo[i+2],nuevo[i+3]);

            r^=c;
        }

        return r;
    }

    // No implementada
    public static int dispersionBJ(byte[] a){
        return 4;
    }

    /**
     * Implementación de Daniel Jacobs Bernstein
     * Declaramos un entero djb = 5831 y en un for, vamos multiplicando por 33 y sumando la conversión a entero del elemnto i
     * del arreglo de bytes "a". para finalmente regresar djb.
     *
     * @param a
     * @return djb
     */
    public static int dispersionDJB(byte[] a){

        int djb = 5381;

        for(int i = 0 ; i < a.length ; i++)
            djb *= 33 + (a[i] & 0xFF);

        return djb;
    }
}
