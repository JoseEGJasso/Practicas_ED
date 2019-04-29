package proyecto2;

/**
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 *
 *
 */
public class Busqueda{
    private Lista<Ficheros> archivos;
    private Lista<Palabra> palabrasDeBusqueda;
    private String busqueda;

    public Busqueda(){
        palabrasDeBusqueda=new Lista<>();
        archivos=new Lista<>();
        busqueda="";
    }

    private void procesarFicheros(Ficheros elemento ){

            elemento.obtenerPalabras().bfs(t->{
                System.out.println("FICHERO= "+elemento.getNombre()+"  p: "+t.getPalabra()+"|"+"  r: "+t.getRecurrencia());
            });

            elemento.eliminarAcentosYEspeciales();
            elemento.dividirPalabras();


    }

    public void procesarBusqueda(String busquedaS){
        if(palabrasDeBusqueda.getLongitud()>0)
            palabrasDeBusqueda.limpia();

        String busqueda=eliminarAcentos(new StringBuilder(busquedaS));

        String nuevaPalabra="";

        for(int i=0;i<busqueda.length();i++){

            if(busqueda.charAt(i)==' '){
                if(!nuevaPalabra.equals("")){
                    if(!(palabrasDeBusqueda.contiene(new Palabra(nuevaPalabra))))
                        palabrasDeBusqueda.agregaFinal(new Palabra(nuevaPalabra));

                    nuevaPalabra="";
                }
                continue;
            }

            nuevaPalabra+=busqueda.charAt(i);
        }

        if(!nuevaPalabra.equals("")){

            if(!(palabrasDeBusqueda.contiene(new Palabra(nuevaPalabra))))
                palabrasDeBusqueda.agregaFinal(new Palabra(nuevaPalabra));
        }
    }

    public void agregarFicheros(Ficheros nuevoFichero){
        archivos.agregaFinal(nuevoFichero);
        procesarFicheros(nuevoFichero);
    }

    public void calcularSimilitud(){

        getIDF();

        for(Ficheros elemento:archivos){
            elemento.obtenerTF(palabrasDeBusqueda);
            elemento.productoTF_IDF(palabrasDeBusqueda);
            elemento.asignarSimilitud();
        }

        for (Ficheros elemento : archivos) {
            elemento.limpiar();
        }

        for (Ficheros elemento : archivos) {
            System.out.print(elemento.getSimilitud()+", ");
        }
        System.out.println();
    }

    //IMORTANTE: SE DEBE EJECUTAR ANTES QUE LA SIMILITUD
    private void getIDF(){
        for(Palabra elemento:palabrasDeBusqueda){
            elemento.calculaIDF(archivos);
            //System.out.print("IDF: "+ elemento.getIDF());
        }
        //System.out.println();
    }


    public Ficheros[] ordenarFicheros(){
        Object[] ficheros=archivos.toArray();
        Ficheros[] temp=new Ficheros[ficheros.length];

        for(int i=0;i<temp.length;i++){
            temp[i]=(Ficheros)ficheros[i];
        }

        Ordenamientos.quickSort(temp);

        return temp;
    }

    public int getNumFicheros(){
        return archivos.getLongitud();
    }

    public boolean yaSeAgrego(Ficheros archivo){
        return archivos.contiene(archivo);
    }

    private String eliminarAcentos(StringBuilder busqueda){


        for(int i=0;i<busqueda.length();i++){

            if((int)(busqueda.charAt(i))>=224 && (int)(busqueda.charAt(i))<230 || (int)(busqueda.charAt(i))>=192 && (int)(busqueda.charAt(i))<198){
                busqueda.setCharAt(i,'a');
                continue;
            }
            if((int)(busqueda.charAt(i))>=232 && (int)(busqueda.charAt(i))<236 || (int)(busqueda.charAt(i))>=200 && (int)(busqueda.charAt(i))<204){
                busqueda.setCharAt(i,'e');
                continue;
            }
            if((int)(busqueda.charAt(i))>=236 && (int)(busqueda.charAt(i))<240 || (int)(busqueda.charAt(i))>=204 && (int)(busqueda.charAt(i))<208){
                busqueda.setCharAt(i,'i');
                continue;
            }
            if((int)(busqueda.charAt(i))>=242 && (int)(busqueda.charAt(i))<246 || (int)(busqueda.charAt(i))>=210 && (int)(busqueda.charAt(i))<215){
                busqueda.setCharAt(i,'o');
                continue;
            }
            if((int)(busqueda.charAt(i))>=249 && (int)(busqueda.charAt(i))<252 || (int)(busqueda.charAt(i))>=217 && (int)(busqueda.charAt(i))<221){
                busqueda.setCharAt(i,'u');
                continue;
            }

            if(busqueda.charAt(i)==',' || busqueda.charAt(i)=='?' || busqueda.charAt(i)=='¿' || busqueda.charAt(i)=='!' || busqueda.charAt(i)=='¡'){
                busqueda.setCharAt(i,' ');
                continue;
            }

            if(busqueda.charAt(i)=='.' || busqueda.charAt(i)==':' || busqueda.charAt(i)==';'){
                busqueda.setCharAt(i,' ');
                continue;
            }
        }

        return busqueda.toString();
    }

    public void imprimirResultados(){
        Ficheros[] ficherosOrdenados=ordenarFicheros();

        System.out.println();

        for(int i=ficherosOrdenados.length-1;i>=0;i--){
            if(ficherosOrdenados[i].getSimilitud()==0)
                continue;
            System.out.println(ficherosOrdenados[i].getNombre()+": "+ficherosOrdenados[i].getSimilitud());
            System.out.println(((ficherosOrdenados.length-i))+".-"+ficherosOrdenados[i].getNombre()+"\n");
        }
    }

}
