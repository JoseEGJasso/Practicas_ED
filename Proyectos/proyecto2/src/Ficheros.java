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
public class Ficheros{
    private ArbolRojinegro<Palabra> palabras;
    private Path ruta;
    private StringBuilder contenido;

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

            if(igual==null)
                palabras.agrega(new Palabra(nuevaPalabra));
            else{
                igual.aumentarRecurrencia();
            }
        }
    }

    public static void main(String[] args) {
        Ficheros fichero=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        Ficheros fichero1=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        Ficheros fichero2=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");
        Ficheros fichero3=new Ficheros("/home/josejasso2000/Escritorio/Estructuras de Datos/PruebasBasicas.txt");

        fichero.eliminarAcentosYEspeciales();
        fichero.dividirPalabras();
        fichero.palabras.bfs(t -> System.out.print(t.getRecurrencia()+", "));
        System.out.println("\n"+fichero.getNombre()+"\n");

        fichero1.eliminarAcentosYEspeciales();
        fichero1.dividirPalabras();

        fichero2.eliminarAcentosYEspeciales();
        fichero2.dividirPalabras();

        fichero3.eliminarAcentosYEspeciales();
        fichero3.dividirPalabras();
    }
}