# Como Instalar Confluent Platform

### ¿ Que es Confluent Platform ?
Es una plataforma de streaming que nos permite administrar, organizar y manipular datos de distintas fuentes. Confluent Platform nos brinda distintas herramientas y facilidad para conectar a fuentes distintas de datos, aplicaciones.

Confluent Platform esta disponible como Confluent Open Source y Confluent Enterprise, para este tutorual se usa Confluent Open Source.

## Confluent Open Source
Tiene 4 componentes importantes que nos facilitan iniciar con nuestra plataforma de streaming basada en kafka.

#### Confluent Kafka Connectors
Este componente aprovecha la API de kafka Connect para conectar Kafka con otros sistemas. Entre los distintos conectores se encuentran Confluent JDBC connector, HDFS Connector,  Elasticsearch Connector, S3 Connector.

#### Confluent Kafka Clients
Este componente nos brinda clientes para integrarlos con distintos lenguajes de programacion como c/c++, python, go, .Net.

#### Confluent Schema Registry
Este componente nos brinda dar un formato comun para que los distintos servicios interaccionen entre si. Lo cual permite una evolucion segura de los esquemas centralizando la gestion de esquemas escritos para el sistema de serializacion Avro. Hace un seguimiento de todas las versiones en cada topico y solo permite la evolucion del esquemas de acuerdo con la configuración de compatibilidad definida por el usuario. Lo cual permite a los desarrolladores cambiar los esquemas sin romper algun servicio

#### Confluent Kafka REST Proxy

Este componente nos facilita trabajar con Kafka con cualquier lenguaje de programacion, ya que nos proporciona un servicio HTTP RESTFULL para interactuar con los clusters de kafka. Proporciona toda la funcionalidad de enviar, leer mensajes individualmente o a un consumer group, inspeccionar metadata de los clusters como la lista de topicos y configuraciones. Por ultimo tambien se puede integrar con el Schema Registry.


## Instalacion
Antes de iniciar se requiere tener instalado java en su version 1.7 o mayor.
Confluent Platform hace uso de los siguientes puertos para los respectivos servicios, por lo cual se recomienda tenerlos abiertos o verificar que otro servicio no los este ocupando.

| Componente | Puerto |
|:----------:|:------:|
| Zookeper   |  2181  |
| Apache Kafka Broker     | 9092   |
|Schema Registry REST API| 8081|
|REST proxy| 8082|
|Kafka Connect REST API|8083|



* Descargaremos los binarios [aqui](https://www.confluent.io/download/), una vez terminada la descarga entramos a la terminal y ejecutamos el siguiente comando:
```
$ tar xzf confluent-oss-3.2.2-2.11.tar.gz
```

* Ingresaremos a la carpete donde se descomprimimos los archivos e inciamos Zookeper
```
$ ./bin/zookeeper-server-start ./etc/kafka/zookeeper.properties
```
* Despues iniciaremos nuestro servidor de kafka
```
$ ./bin/kafka-server-start ./etc/kafka/server.properties
```
* Iniciamos el Schema Registry
```
$ ./bin/schema-registry-start ./etc/schema-registry/schema-registry.properties
```

## Pruebas 
* Ahora iniciaremos un *producer*, le decimos que escriba en el *topico* *test* y lea cada linea de entrada como un mensaje *Avro*, validaremos el schema en el *Schema Registry*, y por ultimo indicamos el formato que tendran los datos.
Nota: El *Schema Registry* es lo mas parecido a un *ddl* en base de datos.
```
$ ./bin/kafka-avro-console-producer \
         --broker-list localhost:9092 --topic test \
         --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"}]}'
```
Ingresamos los siguientes mensajes al topico:
```json
{"f1": "value1"}
{"f1": "value2"}
{"f1": "value3"}
```

* Por ultimo podremos ver los mensajes que se encuentran en el *topico test*
```
./bin/kafka-avro-console-consumer --topic test \
         --zookeeper localhost:2181 \
         --from-beginning
```
