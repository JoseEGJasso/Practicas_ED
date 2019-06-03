package sistema;

/**
 * Interfaz funcional para implementar funciones de dispersión con lambdas.
 * @author González Jasso José Eduardo
 * @author Dozal Magnani Diego
 */
@FunctionalInterface
public interface Dispersor<K>{

    int dispersa(K elemento);
}
