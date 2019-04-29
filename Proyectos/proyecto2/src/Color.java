package proyecto2;

public enum Color{
    ROJO, NEGRO;

    @Override
    public String toString(){
	switch(this){
	case ROJO:
	    return "Rojo";
	case NEGRO:
	    return "Negro";
	}
	return null;
    }
}
