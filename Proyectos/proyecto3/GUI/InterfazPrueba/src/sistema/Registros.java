package sistema;


import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Clase que modela el registro de una usuario,usando una Tabal de Dsipersión para guardar varios
 * usuarios.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
public class Registros{

    //Atributos
    private TablaDeDispersion<String,Usuario> registros;

    /**
     * Constructor de un registro.Consta únicamente del método vacío
     * leerRegistros que modifica el atributo registros.
     *
     */
    public Registros(){
        leerRegistros();
    }

    /**
     * Método leerRegistros sirve para leer el .dat cada vez que se habrá de nuevo la aplicación. Cada que se llama
     * este método, recuperamos los datos de los usuarios que fueron guardados en registros.dat.
     * El primer if es una verificación, si el File no existe, lo crea.
     * El else, se encarga de obtener los datos de registros.dat y "recrea" la Tabla de Dispersión "registros".
     *
     */
    private void leerRegistros(){

        File datos=new File("registros.dat");

        registros=new TablaDeDispersion<>();

        if(!datos.exists()){
            try{
                datos.createNewFile();
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

    /**
     * Método vacío para actualizar la tabla hash de registros. Se obtienen los valores de la tabla modificada
     * y se sobreescriben en la tabla. Para después guardarla en escritura.
     *
     */
    public void actualizarRegistros(){
        try {
            ObjectOutputStream escritura=new ObjectOutputStream(new FileOutputStream("registros.dat"));

            Lista<Usuario> usuarios=registros.getValores();

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

    /**
     * Método vacío que genera un nuevo usuario a partir de tres cadenas :
     * nombreDeUsuario --> Será la llave K para nuestra tabla Hash
     * nombre --> Es el nombre de la persona
     * contraseña --> La contraseña de la cuenta
     * A partir del nombreDeUsuario (llave K) y y de la contraseña (valor V) generamos
     * elementos Usuario que se agregan en la tabla registros y finalmente se actualzia(actualizarRegistros)
     *
     * @param nombreDeUsuario,nombre,contraseña
     *
     *
     */
    public void nuevoRegistro(String nombreDeUsuario,String nombre,String contraseña){

        Usuario nuevoUsuario=new Usuario(nombre,nombreDeUsuario,contraseña);

        registros.agrega(nuevoUsuario.getNombreUsuario(),nuevoUsuario);

        actualizarRegistros();

    }

    /**
     * Método que recibe la cadena nombreDeUsuario y retorna el boolean que regresa el método
     * contieneLlave de la clase TablaDeDispersion aplicado a nombreDeUsuario.
     *
     * @param nombreDeUsuario
     * @return boolean
     */
    public boolean buscar(String nombreDeUsuario){
        return registros.contieneLlave(nombreDeUsuario);
    }

    /**
     * Método que recibe la cadena nombreDeUsuario y regresa un objeto de tipo Usuario asociado
     * a ese nombre. Para esto, hacemos uso del métdo getValor de la clase TablaDeDispersion.
     *
     * @param nombreDeUsuario
     * @@return Usuario
     */
    public Usuario obtenerRegistro(String nombreDeUsuario){
        return registros.getValor(nombreDeUsuario);
    }
}
