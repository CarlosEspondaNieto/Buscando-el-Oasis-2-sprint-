# Instalar conector de mongo.

¿Que es un conector?
Para copiar datos entre Apache Kafka y otro sistema de datos se necesita una instancia de kafka connectors, la cual pude extraer o enviar datos a los sistemas de datos.

#### Source y Sink connectors
Source conector basicamente nos sirve para importar datos de otros sistemas y Sink connector el cual exporta datos de kafka a otro sistema de datos.

Para este tutorial se requiere tener instalado confluent-3.1.1 y mongo en su version 3.2 o superior. Haremos uso del conector de [datamountaineer v0.2.4](http://docs.datamountaineer.com/en/0.2.4/install.html#install)

### Set-up
* Descarga y descompimimos el paquete
```
$ mkdir stream-reactor
$ tar xvf stream-reactor-0.2.4-3.1.1.tar.gz -C stream-reactor
$ export CONFLUENT_HOME=~/confluent/confluent-3.1.1
```
* Iniciamos nuestro zookeeper.
```
$ sudo ./bin/zookeeper-server-start ./e/kafka/zookeeper.properties
```
* Iniciamos el servidor de kafka.
```
$ ./bin/kafka-server-start ./etc/kafka/server.properties
```
* Iniciamos el *schema-registry* ya que haremos uso de el.
```
$ ./bin/schema-registry-start ./etc/schema-registry/schema-registry.properties
```

### Configuracion

Una vez descargados y dezempaquetado stream reactor iniciamos nuestro conector.
* Nos dirigimos dentro de la carpeta e iniciamos el conector.
```
$ ./bin/start-connect.sh
```
* Abriremos nuestro archivo de configuracion el cual se encuentra en la ruta *./conf/mongodb-sink.properties*. ESte contiene la uri para la coneccion al servidor de mongo, la base de datos, el topico por el cual se extrae la info, etc.
```yml
#Connector `mongo-sink-orders`:
name=mongo-sink-orders
connector.class=com.datamountaineer.streamreactor.connect.mongodb.sink.MongoSinkConnector
tasks.max=1
topics=orders-topic
connect.mongo.sink.kcql=INSERT INTO orders SELECT * FROM orders-topic
connect.mongo.database=connect
connect.mongo.connection=mongodb://localhost:27017
connect.mongo.sink.batch.size=10
#task ids: 0
```
 * Ahora creamos el conector pasandole las propiedades que especificamos arriba.
 ```
 $ ./bin/cli.sh create mongo-sink < conf/mongodb-sink.properties
 ```
* Ahora creearemos el producer el cual le pasaremos el schema-registry que coincidira con nuestra colleccion.
```
$ bin/kafka-avro-console-producer \
 --broker-list localhost:9092 --topic orders-topic \
 --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"id","type":"int"},
{"name":"created", "type": "string"}, {"name":"product", "type": "string"}, {"name":"price", "type": "double"}]}'
```
 * Dentro del Producer insertaremos dos registros
 ```json
 {"id": 1, "created": "2016-05-06 13:53:00", "product": "OP-DAX-P-20150201-95.7", "price": 94.2}
 {"id": 1, "created": "2016-05-06 13:53:00", "product": "OP-DAX-P-20150201-95.7", "price": 94.2}
 ```

##### ¿Como se que realmente funciona?
Dentro de los log o terminal (si se ejecuta en primer plano) que ejecutamos el conector nos arrojara un mensaje con la siguiente informacion, que los dos mensajes han sido insertados correctamente! :D
```
[XXXX-XX-XX XX:XX:XX,XXX] INFO Setting newly assigned partitions [orders-topic-0] for group connect-mongo-sink-orders (org.apache.kafka.clients.consumer.internals.ConsumerCoordinator:231)
[XXXX-XX-XX XX:XX:XX,XXX] INFO WorkerSinkTask{id=mongo-sink-orders-0} Committing offsets (org.apache.kafka.connect.runtime.WorkerSinkTask:261)
```

AHORA PARA ASEGURARNOS CONSULTAMOS NUESTRA BD Y COLECCION EN MONGODB:
```json
db.orders.find()
    { "_id" : ObjectId("581fb21b09690a24b63b35bd"), "id" : 1, "created" : "2016-05-06 13:53:00", "product" : "OP-DAX-P-20150201-95.7", "price" : 94.2 }
    { "_id" : ObjectId("581fb2f809690a24b63b35c2"), "id" : 2, "created" : "2016-05-06 13:54:00", "product" : "OP-DAX-C-20150201-100", "price" : 99.5 }
```
