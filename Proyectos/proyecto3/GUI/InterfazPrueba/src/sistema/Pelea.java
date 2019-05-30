package sistema;


public class Pelea{

    private Peleador peleador1;
    private Peleador peleador2;
    private Peleador elegido;
    private Peleador ganador;
    private double apuesta;

    public Pelea(Peleador peleador1,Peleador peleador2){
        this.peleador1=peleador1;
        this.peleador2=peleador2;
        elegido=null;
        ganador=null;
    }

    public void determinarGanador(){
                
        java.util.Random azar=new java.util.Random();

        int probP1=(int)peleador1.getProbabilidad(peleador2)*100;
        int probP2=(int)peleador1.getProbabilidad(peleador2)*100;
        int numRandom=azar.nextInt(99);

        if(numRandom<=probP1)
            ganador=peleador1;

        else
            ganador=peleador2;
    }

    public void asignarApuesta(double apuesta){
        this.apuesta=apuesta;
    }

    public double getApuesta(){
        return apuesta;
    }

    public Peleador getGanador(){
        return ganador;
    }

    public Peleador getElegido(){
        return elegido;
    }

    public Peleador getPerdedor(){
        if(ganador==peleador1)
            return peleador2;
        return peleador1;
    }

    public void asignarElegido(Peleador peleador){
        elegido=peleador;
    }

    public double getCuotaP1(){
        return (double)(1/peleador1.getProbabilidad(peleador2));
    }

    public Peleador getP1(){
        return peleador1;
    }

    public Peleador getP2(){
        return peleador2;
    }

    public double getCuotaP2(){
        return (double)(1/peleador2.getProbabilidad(peleador1));
    }
}
