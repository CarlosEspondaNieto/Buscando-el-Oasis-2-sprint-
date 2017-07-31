# Apache Kafka

Apache kafka es una plataforma distribuida de streaming de tipo productor y suscriptor, lo que significa:
* Funciona como una cola de mensajes o sistema de mensajeria empresarial
* Permite almacenar los mensajes, lo cual lo hace tolerante a fallos
* Permite procesar la informacion en tiempo real

Kafka nos permite construir tuberias(pipeline) de datos en tiempo real para obtener datos entre sistemas o aplicaciones, ademas kafka nos permite manipular los datos en tiempo real.

Kakfa puede ejecutarse en modo cluster en uno o mas servidores, el cluster almacena los streams de registros en topicos. Cada registro o mensaje cuenta con una clave, valor y un timestamp.

### Zookeper:
Aunque no pertence a los componentes de kafka se necesita zookeper para orquestar las tareas del cluster de kafka.

### Topic:
En el topico se genera la publicacion del mensaje, ademas nos permite particionar y distribuir el flujo de los mensajes.

### Broker
Lo semejante a un nodo o un servidor.

### Producer:
El productor de kafka permite publicar un stream de registros en un o mas topicos de kafka.

### Consumer:
Permite que una aplicacion se suscriba a uno o mas topicos de kafka y procese su flujo de registros(streams).

### Stream Processor:
Este permite que una aplicacion actue como un procesador de flujo, permitiendo manipular el flujo en tiempo real.

### Connector:
Permite utilizar productores o consumidores para conectar topicos de kafka a aplicaciones existentes o algunos sistemas de datos.
