package sistema;


import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 *
 *
 */

public class Registros{

    private TablaDeDispersion<String,Usuario> registros;

    public Registros(){
        leerRegistros();
    }

    private void leerRegistros(){

        File datos=new File("registros.dat");
        
        registros=new TablaDeDispersion<>();
        
        if(!datos.exists()){
            try{
                datos.createNewFile();
                System.out.println("Creo?");
            }catch(IOException e){
                System.out.println("Ha sucedido un error al crear el archivo");
            }
        }else{
            try {
                ObjectInputStream datosRecuperados= new ObjectInputStream(new FileInputStream("registros.dat"));
                
                Usuario[] data=(Usuario[])datosRecuperados.readObject();
                
                datosRecuperados.close();
                
                for(Usuario i:data){
                    registros.agrega(i.getNombreUsuario(), i);
                }
            } catch (FileNotFoundException ex) {
                
            } catch (IOException e){
                
            } catch (ClassNotFoundException e1){
                
            }
        }
    }


    public void actualizarRegistros(){
        try {
            ObjectOutputStream escritura=new ObjectOutputStream(new FileOutputStream("registros.dat"));
            
            Lista<Usuario> usuarios=registros.getValores();
            
            for(Usuario e:usuarios){
                System.out.println(e.getSaldo());
            }
            
            if(usuarios==null)
                return;
            
            Object[] temp=usuarios.toArray();
            
            Usuario[] registrosArray=new Usuario[temp.length];
            
            for(int i=0;i<temp.length;i++)
                registrosArray[i]=(Usuario)temp[i];    
            
            escritura.writeObject(registrosArray);
            
        } catch (IOException ex) {
            
        }
    }

    public void nuevoRegistro(String nombreDeUsuario,String nombre,String contraseña){

        Usuario nuevoUsuario=new Usuario(nombre,nombreDeUsuario,contraseña);
        
        registros.agrega(nuevoUsuario.getNombreUsuario(),nuevoUsuario);

        actualizarRegistros();

    }

    public boolean buscar(String nombreDeUsuario){
        return registros.contieneLlave(nombreDeUsuario);
    }

    public Usuario obtenerRegistro(String nombreDeUsuario){
        return registros.getValor(nombreDeUsuario);
    }
}
