package proyecto2;

/**
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 * 
 *
 */
public class Busqueda{
    private Ficheros[] archivos;
    private int[] TF;
    private int[] IDF;
    private int[] similitud;

    public Busqueda(String[] rutas){
        archivos=new Ficheros[rutas.length];

        for(int i=0;i<rutas.length;i++)
            archivos[i]=new Ficheros(rutas[i]);
    }

    //SUGERENCIA: Crea un mejor split() que avance espacios por sí solo

    public char verificarEntrada(){
        return 'n';
    }

    public void calcularSimilitud(){

    }

    private void calcularTF_IDF(){

    }

    private int TF(){
        return 5;
    }

    private int IDF(){
        return 4;
    }





}