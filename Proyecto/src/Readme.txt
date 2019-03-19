"PROYECTO 1RO. IMPLEMENTACION DEL JUEGO LOBOS DE CASTONEGRO"

Autores:
  - Gonzalez Jasso José Eduardo
  - Dozal Magnani Diego

FUNCIONAMIENTO DE  LOBOS DE CASTONEGRO.
- La clase principal que se utiliza para ejecutar el programa es LobosDeCastonegro.java.
- La clase Personaje implementa la interfaz Comparable para ordenar a los personajes que despiertan en la noche. Esta clase contiene todos los atributos generales que puede tener un personaje como: el nombre, si esta vivo, si esta encantado, entre otros. Un punto importante de mencionar es la declaracion de dos objetos enum, esto se hizo con el propósito de clasificar a los jugadores con su respectivo personaje y con su respectivo tipoDePersonaje; facilitando así la implementación de varios métodos y elminando la posibilidad de hacer una clase por cada personaje diferente.
- La clase Juego contiene todas las acciones que puede realizar cada peronaje y algunos métodos auxiliares para determinar al ganador o ayudan a actulizar el estado de los personajes.
- Se utilizan varias listas principalmente para guardar informacion específica de cierto tipo de parámetro. Las principales son noche y registro; en registro se guardan todos los cambios que haya cuando algun personaje resulte encantado, protegido, desprotegido,muerto, etc. En el caso de noche, guarda todo lo referido con los personajes que despiertan en la noche y realizan una acción que pueda llegar a afectar a otros personajes, esta lista se mantiene en constante cambio mediante el método actualizar.