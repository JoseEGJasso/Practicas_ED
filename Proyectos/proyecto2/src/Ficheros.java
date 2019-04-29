package proyecto2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class Ficheros implements Comparable<Ficheros>{
    private ArbolRojinegro<Palabra> palabras;
    private Path ruta;
    private StringBuilder contenido;
    private int contPalabras;
    private double[] tf;
    private double[] tf_idf;
    private double similitud;


    public Ficheros(String ruta){
        this.ruta=Paths.get(ruta);
        leerFichero();
        palabras=new ArbolRojinegro<>();

    }


    private void leerFichero(){
        try{
            BufferedReader buffer=Files.newBufferedReader(ruta,StandardCharsets.UTF_8);

            String lectorDeLineas=buffer.readLine();
            contenido=new StringBuilder();

            while(lectorDeLineas!=null){
                contenido.append(lectorDeLineas);
                lectorDeLineas=buffer.readLine();
                if(lectorDeLineas!=null)
                    contenido.append(" ");
            }

            buffer.close();

        }catch(IOException e){
            contenido=null;
        }
    }

    public char verificarFichero(){
        if(this.contenido==null)
            return 'N';
        for(int i=0;i<contenido.length();i++){
            if(contenido.charAt(i)!=' ')
                return 'B';
        }
        return 'N';
    }

    public void setSimilitud(double similitud){
        this.similitud=similitud;
    }

    public String getContenido(){
        return contenido.toString();
    }

    public String getNombre(){
        return ruta.getFileName().toString();
    }

    public ArbolRojinegro<Palabra> obtenerPalabras(){
        return palabras;
    }

    public void eliminarAcentosYEspeciales(){

        for(int i=0;i<contenido.length();i++){

            if((int)(contenido.charAt(i))>=224 && (int)(contenido.charAt(i))<230 || (int)(contenido.charAt(i))>=192 && (int)(contenido.charAt(i))<198){
                contenido.setCharAt(i,'a');
                continue;
            }
            if((int)(contenido.charAt(i))>=232 && (int)(contenido.charAt(i))<236 || (int)(contenido.charAt(i))>=200 && (int)(contenido.charAt(i))<204){
                contenido.setCharAt(i,'e');
                continue;
            }
            if((int)(contenido.charAt(i))>=236 && (int)(contenido.charAt(i))<240 || (int)(contenido.charAt(i))>=204 && (int)(contenido.charAt(i))<208){
                contenido.setCharAt(i,'i');
                continue;
            }
            if((int)(contenido.charAt(i))>=242 && (int)(contenido.charAt(i))<246 || (int)(contenido.charAt(i))>=210 && (int)(contenido.charAt(i))<215){
                contenido.setCharAt(i,'o');
                continue;
            }
            if((int)(contenido.charAt(i))>=249 && (int)(contenido.charAt(i))<252 || (int)(contenido.charAt(i))>=217 && (int)(contenido.charAt(i))<221){
                contenido.setCharAt(i,'u');
                continue;
            }

            if(contenido.charAt(i)==',' || contenido.charAt(i)=='?' || contenido.charAt(i)=='¿' || contenido.charAt(i)=='!' || contenido.charAt(i)=='¡'){
                contenido.setCharAt(i,' ');
                continue;
            }

            if(contenido.charAt(i)=='.' || contenido.charAt(i)==':' || contenido.charAt(i)==';'){
                contenido.setCharAt(i,' ');
                continue;
            }
        }
    }

    public void dividirPalabras(){
        String nuevaPalabra="";

        for(int i=0;i<contenido.length();i++){

            if(contenido.charAt(i)==' '){
                if(!nuevaPalabra.equals("")){
                    Palabra igual=palabras.busca(new Palabra(nuevaPalabra));

                    contPalabras++;

                    if(igual==null)
                        palabras.agrega(new Palabra(nuevaPalabra));
                    else{
                        igual.aumentarRecurrencia();
                    }

                    nuevaPalabra="";
                }
                continue;
            }

            nuevaPalabra+=contenido.charAt(i);
        }

        if(!nuevaPalabra.equals("")){
            Palabra igual=palabras.busca(new Palabra(nuevaPalabra));

            contPalabras++;

            if(igual==null)
                palabras.agrega(new Palabra(nuevaPalabra));
            else{
                igual.aumentarRecurrencia();
            }
        }
    }

    public int getcontPalabras(){
        return contPalabras;
    }

    public void obtenerTF(Lista<Palabra> busquedaDePalabras){
        tf=new double[busquedaDePalabras.getLongitud()];

        for(int i=0;i<busquedaDePalabras.getLongitud();i++){
            Palabra palabraIterada=palabras.busca(busquedaDePalabras.get(i));

            if(palabraIterada!=null){
                tf[i]=palabraIterada.calculaTF();
            }else
                tf[i]=0.0;
        }
    }

    public void productoTF_IDF(Lista<Palabra> palabrasDeBusqueda){
        tf_idf=new double[palabrasDeBusqueda.getLongitud()];

        for(int i=0;i<palabrasDeBusqueda.getLongitud();i++){
            tf_idf[i]=tf[i]*palabrasDeBusqueda.get(i).getIDF();
        }
    }

    public void asignarSimilitud(){
        double suma=0;

        for(double elemento:tf_idf)
            suma+=elemento;

        if(this.contPalabras==0){
            this.similitud=0;
        }else
            this.similitud=suma/this.contPalabras;
    }

    public void limpiar(){
    	tf_idf=null;
    	tf=null;
    }

    public double getSimilitud(){
        return similitud;
    }

    @Override
    public int compareTo(Ficheros archivo){
        if(this.similitud<archivo.similitud)
            return -1;
        if(archivo.similitud<this.similitud)
            return 1;
        return 0;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Ficheros))
            return false;

        Ficheros archivo=(Ficheros)obj;

        return this.ruta.equals(archivo.ruta);
    }
    public static void main(String[] args) {
        Ficheros fichero=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        Ficheros fichero2=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        // Ficheros fichero2=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        // Ficheros fichero3=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");

        fichero.eliminarAcentosYEspeciales();
        fichero.dividirPalabras();
        fichero.palabras.bfs(t -> System.out.print(t.getRecurrencia()+", "));
        System.out.println("\n"+fichero.getNombre()+"\n");

    }
}
