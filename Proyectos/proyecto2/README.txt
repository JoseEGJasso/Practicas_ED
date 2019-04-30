AUTORES: 
- Dozal Magnani Diego
- González Jasso José Eduardo

FUNCIONAMIENTO DEL MOTOR DE BUSQUEDA.
- El motor de búsqueda que implementamos se divide en 4 clases Busqueda, Ficheros, Palabra y GUIProyecto2.

- Para probar se debe ejecutar la clase GUIProyecto2.java

- La clase Busqueda es la que guarda los datos principales para la búsqueda: los archivos en donde se va a buscar y la búsqueda, los archivos se guardan en una lista
  y la búsqueda es dividida por palabras e igualmente almacenada de una lista. Esta clase se encarga de procesar todos los datos propios de cada fichero así como de
  verificar que se pueda añadir a la lista sin ningún problema. Aquí se calcula la similitud, el TF, IDF, el producto de ambos y se define el orden de los archivos por similitud.

- La clase Ficheros posee toda la información escencial y propia de un fichero para su procesamiento en el motor de búsqueda. En esta clase se lee el fichero ingresado mediante
  una ruta y verifica la existencia del mismo, si no existe su contenido es establecido como nulo, en caso de que sí exista contenido en el fichero, se eliminan signos especiales y 
  se divide el contenido por palabras que son añadidas a un arbol de tipo Palabra, en este árbol sólo se añaden palabras diferentes del archivo si se llega a repetir alguna aumenta
  la recurrencia de esa palabra. Aquí se obtiene el valor TF y el producto de TF con IDF de cada término ingresado en la búsqueda y la se asigna la similitud del archivo.

- La clase Palabra se encarga de facilitar el conteo de recurrencia de cada palabra y el calculo del IDF. Esta clase tiene dos atributos, un String que representa la palabra propia
  de cada objeto creado de esta clase y la recurrencia de esta palabra. TODAS las palabras obtenidas de cada arhivo al dividirlo son convertidas a objetos de esta clase, así, si una 
  palabra ya se encuentra en el árbol no se añade si no que aumenta el atributo recurrencia de esa palabra. También las palabras de la búsqueda son de este tipo, esto para calcular
  en cuántos ficheros aparece el término y posteriormente calcular su IDF.

- La clase GUIProyecto2 es la interfaz gráfica del motor de búsqueda, contiene dos botones uno para agregar archivos a la búsqueda y otro para empezar la búsqueda, al lado de cada
  botón hay un área donde se ingresa la ruta del archivo a agregar y la oración o palabra a buscar. Los resultados aparecen listados por similitud de mayor a menor del lado derecho
  de la ventana y justo a la derecha se muestra el nombre del último archivo agregado. Los archivos con similitud 0 no se muestran y si el usuario ingresa valores no válidos se le
  avisa con un mensaje en pantalla.

