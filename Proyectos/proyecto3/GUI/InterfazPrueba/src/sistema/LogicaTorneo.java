package sistema;


import java.util.Random;
/**
 *
 *
 *
 *
 */

public class LogicaTorneo{

    private Lista<Peleador> participantes;
    private Lista<Pelea> peleas;
    private Usuario apostador;

    public LogicaTorneo(Usuario apostador){
        participantes=new Lista<>();
        peleas=new Lista<>();
        this.apostador=apostador;
    }

    //Genera participantes con nombres aleatorios y habilidad aleatoria
    private void generaParticipantes(){
        String[] nombres={"Santiago", "Sebastián", "Diego", "Nicolás", "Samuel", "Alejandro", "Daniel", "Mateo", "Ángel", "Matías", "Gabriel", "Tomás", "David", "Emiliano", "Andrés", "Joaquín", "Carlos", "Alexander", "Adrián", "Lucas", "Benjamín", "Leonardo", "Rodrigo", "Felipe", "Francisco", "Pablo", "Martín", "Fernando", "Isaac", "Manuel", "Juan", "Pablo", "Emmanuel", "Emilio", "Vicente", "Eduardo", "Juan", "Javier", "Jorge", "Aaron", "José", "Erick", "Luis", "Cristian", "Ignacio", "Christopher", "Jesús", "Kevin", "Agustín", "Juan", "David", "Simón", "Joshua", "Maximiliano", "Miguel", "Ángel", "Bruno", "Iván", "Gael", "Thiago", "Jerónimo", "Hugo", "Ricardo", "Antonio", "Ian", "Anthony", "Pedro", "Rafael", "Jonathan", "Esteban", "Julián", "Oscar", "Santino", "Axel", "Sergio", "Guillermo", "Matthew", "Valentín", "Bautista", "Álvaro", "Dylan", "Marcos", "Luciano", "Mario","Ringo", "César", "Luca", "Iker", "Juan", "Andrés", "Gonzalo", "Roberto", "Facundo", "Diego", "Alejandro", "Josué", "Franco", "Jeremías", "Primitivo", "Ezequiel", "Abraham", "Bernabé", "Serafín", "Zacarías", "Pascual", "Benjamín", "Eliseo", "Isaías", "Carmelo", "Jonatán", "Cristóbal", "Ismael", "Mariano", "Salvador", "Joaquín", "Domingo", "Patricio", "Inhué", "Mauricio", "Edmundo", "Nico", "Justin", "Kevin", "Dídac", "Lucho", "Alexandre", "Valentino", "Amancio", "Fermín", "Rubén"};
        //EXTRA. Añadir arreglo de alias y concatenarlo aleatoriamente

        Random numAzar=new Random();

        for(int i=0;i<9;i++)
            participantes.agregaFinal(new Peleador(nombres[numAzar.nextInt(nombres.length-1)],numAzar.nextInt(350)+50));
        
    }

    private void iniciaRondas(){
        Lista<Thread> hilos=new Lista<>();

        for(int i=0;i<participantes.getLongitud()-1;i+=2)
            peleas.agregaFinal(new Pelea(participantes.get(i),participantes.get(i+1)));

        for(Pelea e:peleas){
            Runnable r= (Runnable)e;

            hilos.agregaFinal(new Thread(r));
        }

        for(Thread partida:hilos){
            partida.start();
            /*try {
            Thread.sleep(15000);
            } catch (Exception e) {}*/
        }
            
        for(Pelea e:peleas){
            participantes.elimina(e.getPerdedor());
            if(e.getElegido()==e.getGanador()){

                if(e.getGanador()==e.getP1())
                    apostador.aumentaSaldo(e.getApuesta()*e.getCuotaP1());
                else
                    apostador.aumentaSaldo(e.getApuesta()*e.getCuotaP2());
            }
        }

    }

    //ESte metodo es para controlar desde la interfaz el transcurso de las rondas comprendidas de 4, 2 y 1 pelea. 

    public Pelea obtenerPelea(int partida){
        return peleas.get(partida);
    }
    
    public int getNumPeleas(){
        return peleas.getLongitud();
    }

    //Método que inicia el torneo y las partidas se empiezan a ejecutar
    public void comenzarTorneo(){
     //   while(true){
            generaParticipantes();
            //Este ciclo siempre se repite 4 veces, lo que genera una constante en la complejidad evitando que sea cuadrático
            while(participantes.getLongitud()>1){
                iniciaRondas();
            }
            participantes.limpia();
       //s }

    }
}
