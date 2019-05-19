/**
 * Clase con varias funciones de dispersión.
 */
public class Dispersiones{

    private static int combina(byte a,byte b,byte c, byte d){
        return ((a & 0xFF) << 24) | ((b & 0xFF) << 16) | ((c & 0xFF) << 8) |((d & 0xFF));
    }

    public static int dispersionXOR(byte[] a){
        byte[] nuevo;

        // System.out.println("longitud arreglo: "+a.length);
        // System.out.println("contenido: ");

        if(a.length % 4 != 0){
            nuevo=new byte[a.length+(4 - a.length % 4)];

            for(int i=0;i<a.length;i++)
                nuevo[i]=a[i];

            // System.out.println("ENTRAAA MOD 4");
        } else{
            // System.out.println("ENTRA NADA");
            nuevo=a;
        }

        // System.out.println("longitud arreglo"+a.length);

        int r=0;

        for (int i = 0; i < nuevo.length; i+=4) {
            int c=combina(nuevo[i],nuevo[i+1],nuevo[i+2],nuevo[i+3]);

            r^=c;
        }

        return r;
    }
    
    public static int dispersionBJ(byte[] a){
        return 4;
    }
    
    public static int dispersionDJB(byte[] a){

        int djb = 5381;

        for(int i = 0 ; i < a.length ; i++)
            djb *= 33 + (a[i] & 0xFF);

        return djb;
    }
}