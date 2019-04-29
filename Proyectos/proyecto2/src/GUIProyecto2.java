package proyecto2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUIProyecto2 {



 public static void main(String[] args){

   Ventana_Principal primera = new Ventana_Principal();

   primera.setVisible(true);

  }
 }

 class Ventana_Principal extends JFrame{



   private Busqueda busqueda=new Busqueda();
   private String ruta="";

   private JButton buttonSearch;
   private JButton buttonAdd;

   private JTextField boxSearch;
   private JTextField boxAdd;



   private  JTextArea resultArea;
   private  JTextArea ultimoArchivoAgregado;


   private  JScrollPane scroll;



   public Ventana_Principal(){

     super();
     configurarVentana();
     inicializarBotones();
     inicializarCajas();
     inicializarAreasDeTexto();
     //inicializarScroll();

     interaccionAgrega();
     interaccionBusca();

   }

   public void configurarVentana(){
     this.setSize(500, 350);
     this.setLocationRelativeTo(null);
     this.setLayout(null);
     this.setResizable(false);
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void inicializarBotones(){

     buttonSearch = new JButton();
     buttonSearch.setText("Buscar");
     buttonSearch.setSize(150, 35);
     buttonSearch.setLocation(50,25);
     this.add(buttonSearch);

     buttonAdd = new JButton();
     buttonAdd.setText("Agregar Fichero");
     buttonAdd.setSize(150, 35);
     buttonAdd.setLocation(50,70);
     this.add(buttonAdd);
   }

   //Se agregan las cajas de texto a la ventana
   public void inicializarCajas(){

     boxSearch = new JTextField();
     boxSearch.setSize(200, 35);
     boxSearch.setLocation(250,25);
     this.add(boxSearch);


     boxAdd = new JTextField();
     boxAdd.setSize(200, 35);
     boxAdd.setLocation(250,70);
     this.add(boxAdd);
   }

   // Aquí es donde hay que inicir el JTextArea
   public void inicializarAreasDeTexto(){
     resultArea = new JTextArea("Resultados:\n");
     resultArea.setLineWrap(true);
     resultArea.setSize(250,150);
     resultArea.setLocation(50,120);
     resultArea.setEditable(false);
     this.add(resultArea);

     ultimoArchivoAgregado = new JTextArea("Agregado:\t");
     ultimoArchivoAgregado.setSize(180,20);
     ultimoArchivoAgregado.setLocation(300,120);
     ultimoArchivoAgregado.setEditable(false);
     this.add(ultimoArchivoAgregado);


        }

  //Inicializar Scroll de la caja. El scroll aún no jala
  public void inicializarScroll(){
    scroll = new JScrollPane(resultArea);
    scroll.setSize(5,10);
    scroll.setLocation(100,240);
    this.add(scroll);
  }

   public void interaccionAgrega(){
     ActionListener oyente = new ActionListener(){
     Ficheros nuevoFichero;

      //Esto es lo que va a ocurrir cuando el boton agregar agregue algo a la lista de búsqueda
       public void actionPerformed(ActionEvent e){

         ruta = boxAdd.getText();
         nuevoFichero = new Ficheros(ruta);

         if(nuevoFichero.verificarFichero()=='B' && !busqueda.yaSeAgrego(nuevoFichero)){
          ultimoArchivoAgregado.setText(null);
          busqueda.agregarFicheros(nuevoFichero);
          ultimoArchivoAgregado.append(nuevoFichero.getNombre());
          ruta = "";
        } else{
          JOptionPane.showMessageDialog(boxAdd, "El archivo está vacío o la ruta está mal escrita");          
        }
       }
     };

     buttonAdd.addActionListener(oyente);
   }

   public void interaccionBusca(){
     ActionListener oyente = new ActionListener(){

       public void actionPerformed(ActionEvent e){

         String aBuscar = boxSearch.getText();

         if(busqueda.getNumFicheros() == 0){
           JOptionPane.showMessageDialog(boxSearch,"No hay archivos añadidos");
           return;
         }

         if(verificaBusqueda(aBuscar)){

           busqueda.procesarBusqueda(aBuscar);

           busqueda.procesarFicheros();

           busqueda.calcularSimilitud();

           Ficheros[] ficherosOrdenados = busqueda.ordenarFicheros();
           for(int i=ficherosOrdenados.length-1;i>=0;i--){
               if(ficherosOrdenados[i].getSimilitud()==0)
                   continue;
               resultArea.append(ficherosOrdenados[i].getNombre());
               resultArea.append("\n");
           }
         }

       }
     };

     buttonSearch.addActionListener(oyente);
   }

   private static boolean verificaBusqueda(String busqueda){
       if(busqueda.length()>200)
           return false;

       for (int i = 0; i < busqueda.length(); i++) {
        if (busqueda.charAt(i)!=',' && busqueda.charAt(i)!='?' && busqueda.charAt(i)!='¿' && busqueda.charAt(i)!='!' && busqueda.charAt(i)!='¡' && (int)(busqueda.charAt(i))!=46 && busqueda.charAt(i)!=' ' && busqueda.charAt(i)!=':' && busqueda.charAt(i)!=';' && busqueda.charAt(i)!='\u0000')
          return true;
       }

       return false;
   }

}
