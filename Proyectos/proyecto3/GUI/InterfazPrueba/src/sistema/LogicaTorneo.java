package sistema;


import java.util.Random;
/**
 *
 *
 *
 *
 */

public class LogicaTorneo implements Runnable{

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

    private void iniciaRondas(int inicio,int ultimo){
        
        for(int i=inicio;i<ultimo;i++)
            peleas.get(i).determinarGanador();
            
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
                    apostador.agregaMovimiento('G',apostador.getSaldo()-(peleas.get(i).getApuesta()+peleas.get(i).getCuotaP1()), peleas.get(i));
                else
                    apostador.agregaMovimiento('G',apostador.getSaldo()-(peleas.get(i).getApuesta()+peleas.get(i).getCuotaP2()), peleas.get(i));
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
    
    @Override
    public void run(){
        while(!Thread.interrupted()){
            
            int indice=0;
            generaParticipantes();
            
            System.out.println("QUe rayos!!!!!");
            
            while(participantes.getLongitud()>2){
                for(int i=0;i<participantes.getLongitud()-1;i+=2)
                    peleas.agregaFinal(new Pelea(participantes.get(i),participantes.get(i+1)));
           
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException ex) {
                }
                
                System.out.println(participantes.getLongitud());
                
                if(indice==0)
                    iniciaRondas(0,4);
                else if(indice==1)
                    iniciaRondas(4,6);
                else if(indice==2)
                    iniciaRondas(6,7);
                
                System.out.println(participantes.getLongitud());
                
                indice++;
            }
            peleas.limpia();
            participantes.limpia();
        }
    }
}
