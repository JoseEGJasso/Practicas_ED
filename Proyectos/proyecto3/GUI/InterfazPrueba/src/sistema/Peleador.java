package sistema;


public class Peleador {

    private String nombre;
    private int habilidad;

    public Peleador(String nombre,int habilidad){
        this.nombre=nombre;
        this.habilidad=habilidad;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public String getNombre(){
        return nombre;
    }

    public double getProbabilidad(Peleador oponente) {
        return habilidad / (habilidad + oponente.getHabilidad());
    }

}
