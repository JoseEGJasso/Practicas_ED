package proyecto2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Clase GUIProyecto2. Modela la interfaz gráfica de un búscador de coincidencias en una lista de
 * archivos, los cuales a su vez tienen asociado un árbol para cada una de sus palabraa.
 * Para modelar esta clase empleamos principalmente la clase awt.
 *
 * @author Gonzalez Jasso Jose Eduardo
 * @author Dozal Magnani Diego
 *
 */
public class GUIProyecto2{


 /**
  * Método principal  "main ". Istancia un objeto de tipo Ventana_Principal y después la hace visible.
  *
  */
 public static void main(String[] args){

   Ventana_Principal primera = new Ventana_Principal();

   primera.setVisible(true);

    }


 }

/**
 * Clase Ventana_Principal que extiende de JFrame. Es la clase más importante de la interfaz, modela
 * una ventana que tienen diversas componentes y que, principalmente, tiene de atributos un objeto de tipo
 * Busqueda y un objeto de tipo String ruta, los cuales nos permiten acceder a nuestras clases Palabra.java,
 * Fichero.java y Busqueda.java, que son escenciales para modelar el buscador de coincidencias.
 *
 * @author Gonzalez Jasso Jose Eduardo
 * @author Dozal Magnani Diego
 */
 class Ventana_Principal extends JFrame{


   //atributos de la clase Ventana_Principal
   private Busqueda busqueda=new Busqueda();
   private String ruta="";

   private JPanel panelPrincipal;

   private JButton buttonSearch;
   private JButton buttonAdd;

   private JTextField boxSearch;
   private JTextField boxAdd;

   private  JTextArea resultArea;
   private  JTextArea ultimoArchivoAgregado;

   //private  JScrollPane scroll;

   /**
    * Constructor de Ventana_Principal que tiene como constructor superior
    * al de JFrame. Llama a los métodos que inicializan los componentes necesarios
    * y los métodos que disparan los eventos de los botones.
    *
    */
   public Ventana_Principal(){

     super();
     configurarVentana();
     inicializarPaneles();
     inicializarBotones();
     inicializarCajas();
     inicializarAreasDeTexto();
     inicializarEtiquetas();
     //inicializarScroll();

     this.setTitle("BUSCADOR");

     interaccionAgrega();
     interaccionBusca();

   }
   /**
    * Inicializador de la ventana, se modifican algunas propiedades de ésta como el tamaño,
    * la localización, el Layaout nulo, que no sea escalable y que al cerrarse también se
    * cierre todo componente interno y termine la ejecución.
    *
    */
   private void configurarVentana(){
     this.setSize(500, 350);
     this.setLocationRelativeTo(null);
     this.setLayout(null);
     this.setResizable(false);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**
    * Inicializador del panel principal que estará sobre la ventana. Se modifican alguna propiedades
    * como el tamaño y la localización (que en este caso es la propia JFrame). Finalmente se le agrega
    * al objeto Ventana_Principal.
    *
    */
   private void inicializarPaneles(){
     panelPrincipal = new JPanel();
     panelPrincipal.setSize(500,350);
     panelPrincipal.setLayout(null);
     this.add(panelPrincipal);

   }

   /**
    * Inicializador de los únicos dos botones de la interfaz. Se modifican algunas propiedades ya antes mencionadas
    * y finalmente se agregan a la JLabel panelPrincipal.
    *
    *
    */
   private void inicializarBotones(){

     buttonSearch = new JButton();
     buttonSearch.setText("Buscar");
     buttonSearch.setSize(150, 35);
     buttonSearch.setLocation(50,25);
     panelPrincipal.add(buttonSearch);

     buttonAdd = new JButton();
     buttonAdd.setText("Agregar Fichero");
     buttonAdd.setSize(150, 35);
     buttonAdd.setLocation(50,70);
     panelPrincipal.add(buttonAdd);
   }

   /**
    * Inicializador de las únicas dos Campos de Texto (JTextField) de la interfaz.
    * Se modifican algunas propiedades ya antes mencionadas
    * y finalmente se agregan a la JLabel panelPrincipal.
    *
    */
   public void inicializarCajas(){

     boxSearch = new JTextField();
     boxSearch.setSize(200, 35);
     boxSearch.setLocation(250,25);
     panelPrincipal.add(boxSearch);


     boxAdd = new JTextField();
     boxAdd.setSize(200, 35);
     boxAdd.setLocation(250,70);
     panelPrincipal.add(boxAdd);
   }

   /**
    * Inicializador de ls únicas dos Areas de Texto(JTextArea) de la interfaz.
    * Se modifican algunas propiedades ya antes mencionadas
    * y finalmente se agregan a la JLabel panelPrincipal.
    *
    */
   public void inicializarAreasDeTexto(){
     resultArea = new JTextArea();
     resultArea.setLineWrap(true);
     resultArea.setSize(250,160);
     resultArea.setLocation(50,150);
     resultArea.setEditable(false);
     panelPrincipal.add(resultArea);

     ultimoArchivoAgregado = new JTextArea();
     ultimoArchivoAgregado.setSize(180,20);
     ultimoArchivoAgregado.setLocation(310,150);
     ultimoArchivoAgregado.setEditable(false);
     panelPrincipal.add(ultimoArchivoAgregado);

    }

    /**
     * Inicializador de ls únicas dos etiquetas de la interfaz.
     * Se modifican algunas propiedades ya antes mencionadas
     * y finalmente se agregan a la JLabel panelPrincipal.
     *
     */
    private void inicializarEtiquetas(){

      JLabel etiquetaResultados = new JLabel();
      etiquetaResultados.setText("RESULTADOS");//Agregar
      etiquetaResultados.setSize(100,20);
      etiquetaResultados.setLocation(130,130);
      panelPrincipal.add(etiquetaResultados);

      JLabel etiquetaAgregados = new JLabel();
      etiquetaAgregados.setText("AGREGADO");
      etiquetaAgregados.setSize(100,20);
      etiquetaAgregados.setLocation(360,130);
      panelPrincipal.add(etiquetaAgregados);
    }

  /*Inicializar Scroll de la caja. El scroll aún no jala
  public void inicializarScroll(){
    scroll = new JScrollPane(resultArea);
    scroll.setSize(5,10);
    scroll.setLocation(100,240);
    this.add(scroll);
  }*/

   /**
    * Método interaccionAgrega. Es el encargado de concetar al buttonAdd con el oyente
    * del evento. En este caso, cuando el boton es presionado, implementando el método
    * actionPerformed, obtenemos el contenido del JTextField boxAdd como un String (usando .getText())
    * para después instanciar un objeto de tipo Fichero a partir de una String. Después procedemos
    * a realizar las verificación pertinenetes (que involucran una ventana emergente)para
    * después agregar el objeto de tipo Fichero a nuestro objeto de tipo Busqueda.
    *
    */
   public void interaccionAgrega(){
     ActionListener oyente = new ActionListener(){
     Ficheros nuevoFichero;

      //Esto es lo que va a ocurrir cuando el boton agregar agregue algo a la lista de búsqueda
       public void actionPerformed(ActionEvent e){

         ruta = boxAdd.getText();
         nuevoFichero = new Ficheros(ruta);

         if(nuevoFichero.verificarFichero()=='B' && !busqueda.yaSeAgrego(nuevoFichero)){
          //Se refresca el JTextArea ultimoArchivoAgregado
          ultimoArchivoAgregado.setText(null);
          busqueda.agregarFicheros(nuevoFichero);
          ultimoArchivoAgregado.append(nuevoFichero.getNombre());
          ruta = "";
        } else{
          JOptionPane.showMessageDialog(boxAdd, "El archivo está vacío o la ruta está mal escrita");
        }
       }
     };
     //Se asigna el objeto oyente de la acción
     buttonAdd.addActionListener(oyente);
   }

   /**
    * Método interaccionBusca. Es el método que se encarga de vincular el buttonSearch con el
    * oyente del evento. En este caso cuando el boton es presionado mplementando el método
    * actionPerformed, obtenemos el contenido del JTextField boxAdd como un String (usando .getText())
    * dicho contenido será el término que buscaremos en la lista de Ficheros de Busqueda, utilizando
    * métodos de la clase busqueda para verificar los correspondiente y realizar los procesos
    * requeridos para comparar el término entre ficheros y dentro de éstos. Finalmente, se ordenan
    * los ficheros para conocer el orden con el cual serán mostrados en el JTextFile: resultArea.
    */
   public void interaccionBusca(){
     ActionListener oyente = new ActionListener(){

       public void actionPerformed(ActionEvent e){

         //Se refresca el JTextArea resultArea
         resultArea.setText(null);
         String aBuscar = boxSearch.getText();

         if(busqueda.getNumFicheros() == 0){
           JOptionPane.showMessageDialog(boxSearch,"No hay archivos añadidos");
           //Se refresca el JButton
           boxSearch.setText(null);
           return;
         }

         if(verificaBusqueda(aBuscar)){

           busqueda.procesarBusqueda(aBuscar);

           busqueda.calcularSimilitud();

           Ficheros[] ficherosOrdenados = busqueda.ordenarFicheros();
           for(int i=ficherosOrdenados.length-1;i>=0;i--){
               if(ficherosOrdenados[i].getSimilitud()==0)
                   break;

               resultArea.append(ficherosOrdenados[i].getNombre());
               resultArea.append("\n");


           }
           //Se refresca el JTextField
           boxSearch.setText(null);
         }else{
           JOptionPane.showMessageDialog(boxSearch, "La búqueda no es válida");
         }

       }
     };
     //Se aigna el objeto oyente de la acción
     buttonSearch.addActionListener(oyente);
   }

   /**
    * Método auxiliar: verificaBusqueda. Con este método nos encargamos de verificar que los
    * términos introducidos a la boxSearch sean máximo 200. Además de que discriminamos los
    * archivos que únicamente contienen caractéres "especiales" como "??!!,,,..".
    *
    */
   private static boolean verificaBusqueda(String busqueda){
       if(busqueda.length()>200)
           return false;

       for (int i = 0; i < busqueda.length(); i++) {
        if (busqueda.charAt(i)!=',' && busqueda.charAt(i)!='?' && busqueda.charAt(i)!='¿' && busqueda.charAt(i)!='!' && busqueda.charAt(i)!='¡' && (int)(busqueda.charAt(i))!=46 && busqueda.charAt(i)!=' '
        && busqueda.charAt(i)!=':' && busqueda.charAt(i)!=';' && busqueda.charAt(i)!='\u0000')
          return true;
       }

       return false;
   }

}
