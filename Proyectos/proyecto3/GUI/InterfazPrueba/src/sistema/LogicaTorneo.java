package sistema;


import java.util.Random;

/**
 * Clase LogicaTorneo que modela el desarrollo de un torneo de peleas.
 * Modela la creación de participantes, la resoulcion de un encuentro entre dos peleadores
 * y el transcurso de todo el torneo implementando la interfaz Runnable.
 *
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
 public class LogicaTorneo implements Runnable{

   //Atributos de la clase LogicaTorneo
   /*
    * Constan de una lista de tipo Peleador llamada "participantes"
    * Una lista de tipo Pelea llamada "peleas"
    * Y un objeto de tipo Usuario
    */
    private Lista<Peleador> participantes;
    private Lista<Pelea> peleas;
    private Usuario apostador;

    /**
     * Constructor de LogicaTorneo que recibe un objeto de tipo usuario.
     * Inicializa "participantes" y "peleas" como listas vacías y asigna
     * a su variable usuario como el objeto Usuario que recibió.
     *
     * @param apostador
     */
    public LogicaTorneo(Usuario apostador){
        participantes=new Lista<>();
        peleas=new Lista<>();
        this.apostador=apostador;
    }


    /**
     * Método vacío para llenar la lista "participantes" de LogicaTorneo.
     * Se declara un arreglo tipo String llamado "nombres", el cual consta de 100 distintos nombres masculinos.
     * Utilizando la clase Random, agregamos a la lista de participantes, objetos de tipo Peleador que reciben
     * como parametro uno de los nombres del arreglo(de forma aleatoria) y de habilidad un enteeo gnerado entre 50 y 399.
     */
    private void generaParticipantes(){
        String[] nombres={"Santiago", "Sebastián", "Diego", "Nicolás", "Samuel", "Alejandro", "Daniel", "Mateo", "Ángel", "Matías", "Gabriel", "Tomás", "David", "Emiliano", "Andrés", "Joaquín", "Carlos", "Alexander", "Adrián", "Lucas", "Benjamín", "Leonardo", "Rodrigo", "Felipe", "Francisco", "Pablo", "Martín", "Fernando", "Isaac", "Manuel", "Juan", "Pablo", "Emmanuel", "Emilio", "Vicente", "Eduardo", "Juan", "Javier", "Jorge", "Aaron", "José", "Erick", "Luis", "Cristian", "Ignacio", "Christopher", "Jesús", "Kevin", "Agustín", "Juan", "David", "Simón", "Joshua", "Maximiliano", "Miguel", "Ángel", "Bruno", "Iván", "Gael", "Thiago", "Jerónimo", "Hugo", "Ricardo", "Antonio", "Ian", "Anthony", "Pedro", "Rafael", "Jonathan", "Esteban", "Julián", "Oscar", "Santino", "Axel", "Sergio", "Guillermo", "Matthew", "Valentín", "Bautista", "Álvaro", "Dylan", "Marcos", "Luciano", "Mario","Ringo", "César", "Luca", "Iker", "Juan", "Andrés", "Gonzalo", "Roberto", "Facundo", "Diego", "Alejandro", "Josué", "Franco", "Jeremías", "Primitivo", "Ezequiel", "Abraham", "Bernabé", "Serafín", "Zacarías", "Pascual", "Benjamín", "Eliseo", "Isaías", "Carmelo", "Jonatán", "Cristóbal", "Ismael", "Mariano", "Salvador", "Joaquín", "Domingo", "Patricio", "Inhué", "Mauricio", "Edmundo", "Nico", "Justin", "Kevin", "Dídac", "Lucho", "Alexandre", "Valentino", "Amancio", "Fermín", "Rubén"};
        //EXTRA. Añadir arreglo de alias y concatenarlo aleatoriamente

        Random numAzar=new Random();

        for(int i=0;i<9;i++)
            participantes.agregaFinal(new Peleador(nombres[numAzar.nextInt(nombres.length-1)],numAzar.nextInt(350)+50));

    }

    /**
     * Método vacío iniciaRondas. Este método es crucial para manipular nuestra lista de peleas y participantes.
     * Asimismo para modificar el saldo del usuario.
     * Recibe dos enteros, el inicio y el último.
     * El primer for, itera sobre la lista de peleas y aplica a cada una de las peleas el método de la clase Pelea:
     * determinarGanador.
     * En el segundo for , se elimina al perdedor de la pelea. Luego se verifica que el elegido de cada pelea(aquél
     * peleador por el que apostó el usuario) se distinto de null.
     * A continuación:
     * si el elegido de la pelea es igual al ganador de la pelea, se incrementa el saldo del usuario (con el método
     * setSaldo) y actualizamos el historial con la ganancia del Usuario (con el método agregaMovimiento).
     * si el elegido de la pelea es igual al perdedor de la pelea, se decrementa el saldo del usuario(con el método
     * setSaldo) y actualizamos el historial con la ganancia del Usuario (con el método agregaMovimiento).
     *
     * @param inicio
     * @param ultimo
     */
    private void iniciaRondas(int inicio,int ultimo){

        for(int i=inicio;i<ultimo;i++){
            peleas.get(i).setYaExpiro(true);
            peleas.get(i).determinarGanador();
        }

        for(int i=inicio;i<ultimo;i++){
            participantes.elimina(peleas.get(i).getPerdedor());

            if(peleas.get(i).getElegido()==null)
                continue;

            if(peleas.get(i).getElegido()==peleas.get(i).getGanador()){

                if(peleas.get(i).getGanador()==peleas.get(i).getP1()){
                    apostador.setSaldo(apostador.getSaldo()+peleas.get(i).getApuesta()*peleas.get(i).getCuotaP1());
                    apostador.agregaMovimiento('G',peleas.get(i).getApuesta()*peleas.get(i).getCuotaP1(), peleas.get(i));
                }else{
                    apostador.setSaldo(apostador.getSaldo()+peleas.get(i).getApuesta()*peleas.get(i).getCuotaP2());
                    apostador.agregaMovimiento('G',peleas.get(i).getApuesta()*peleas.get(i).getCuotaP2(), peleas.get(i));
                }
            }else{
                if(peleas.get(i).getPerdedor()==peleas.get(i).getP1())
                    apostador.agregaMovimiento('P',peleas.get(i).getApuesta()+peleas.get(i).getCuotaP1(), peleas.get(i));
                else
                    apostador.agregaMovimiento('P',peleas.get(i).getApuesta()+peleas.get(i).getCuotaP2(), peleas.get(i));
            }
        }


    }

    /**
     * Método auxiliar para tener una referencia al objeto Pelea en la Interfaz Gráfica. Sirve para controlar
     * las peleas en el transcurso de las rondas. La primer ronda tiene 4 peleas, la segunda 2 y la final 1.
     * Ya en la interfaz, nosotros sabemos qué número está asociado a cada objeto Enfrentamiento.
     *
     * @param partida
     * @return Pelea
     */
    public Pelea obtenerPelea(int partida){
        return peleas.get(partida);
    }

    /**
     * Método getter para saber cuántas peleas hay conociendo la longitud de la lista peleas.
     *
     * @return peleas.getLongitud()
     */
    public int getNumPeleas(){
        return peleas.getLongitud();
    }

    /**
     * Implemtación del método run.
     * EL cuerpo del método se va a ejecutar meintras el Thread no se interrumpa:
     * Se generan los participantes. El segundo while se ejecuta mientras la longitud
     * de la lista de los participantes sea mayor a 2. Con un for anidado, se crean las peleas entre dos
     * Peleadores de la lista de participantes.
     * El Thread se duerme durante 15 segundos y a continuación, dependiendo del valor de índice, se inician
     * las rondas con el método iniciaRondas (cuyos parametros ya conocemos).El ínidice incrementa en 1.
     * Finalmente limpia la lista de pleas y participantes para poder comenzar otro torneo.
     */
    @Override
    public void run(){
        while(!Thread.interrupted()){

            int indice=0;
            generaParticipantes();

            while(participantes.getLongitud()>2){
                for(int i=0;i<participantes.getLongitud()-1;i+=2)
                    peleas.agregaFinal(new Pelea(participantes.get(i),participantes.get(i+1)));

                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                }

                if(indice==0)
                    iniciaRondas(0,4);
                else if(indice==1)
                    iniciaRondas(4,6);
                else if(indice==2)
                    iniciaRondas(6,7);


                indice++;
            }
            peleas.limpia();
            participantes.limpia();
        }
    }
}
