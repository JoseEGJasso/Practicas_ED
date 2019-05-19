package proyecto3;

/**
 *
 *
 *
 */

public class Registros{

    private TablaDeDispersion<String,Usuario> registros;
    private File datos;

    public Registros(){
        leerRegistros();
    }

    private void leerRegistros(){

        datos=new File("registros.dat");

        if(!datos.exists()){
            try{
                datos.createNewFile();
                registros=new TablaDeDispersion<>();
            }catch(IOException e){
                System.out.println("Ha sucedido un error al crear el archivo");
            }
        }else{
            try{
                ObjectInputStream datosRecuperados= new ObjectInputStream(new FileInputStream(datos));

                registros=(TablaDeDispersion<String,Usuario>) datosRecuperados.readObject();

                datosRecuperados.close();
            }catch(IOException e){
                System.out.println("Ha sucedido un error al leer los datos");
            }
        }
    }

    public void actualizarRegistros(){
        try{

            ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(datos));

            escritura.writeObject(registros);

            escritura.close();

        }catch(IOException e){
            System.out.println("Error en escritura");
        }
    }

    public boolean nuevoRegistro(String nombreDeUsuario,String nombre,String contraseña){
        if(nombreDeUsuario==null | nombre==null | contraseña==null)
            return false;

        if(nombreDeUsuario.length()<8 | contraseña.length()<8)
            return false;

        if(buscar(nombreDeUsuario))
            return false;

        registros.agrega(nombreDeUsuario,new Usuario(nombre,contraseña));

        actualizarRegistros();

        return true;
    }

    public boolean buscar(String nombreDeUsuario){
        return registros.contieneLlave(nombreDeUsuario);
    }

    public Usuario obtenerRegistro(String nombreDeUsuario){
        return registros.getValor(nombreDeUsuario);
    }
}
