### Qué es Avro?

Es una herramienta que provee presentación en episodios de la información y servicios de intercambio de información  para Hadoop. 
Gandes volúmnes de información pueden ser intercambiados entre programas escritos en cualquier lenguaje.
Entiendo que es un formato basado en llave-valor. 

- ¿Cómo funciona?

El almacenamiento es compacto  y eficiente.
Avro almacena los metadatos de la información (data definition) y la  propia información juntos en un archivo o mensaje para que los programas puedan entenderlos facilmente.

Los metadatos son guardados en formato json y la información en binarios.
Soporta tipos de datos primitivos: numericos, binarios y cadenas; así como datos complejos como arrays,maps, enumeraciones.... La información se puede ordenar. 

El esquema o formato puede evolucionar  y también avro permite eso.


Para mas información consultar la documentacion en la página de: [Avro](https://avro.apache.org/docs/1.7.7/)


[Avro tools. convertir de json a avro y viceversa](http://www.michael-noll.com/blog/2013/03/17/reading-and-writing-avro-files-from-the-command-line/)
